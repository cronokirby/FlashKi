package com.cronokirby.flashki.views

import com.cronokirby.flashki.models.Card
import com.github.thomasnield.rxkotlinfx.actionEvents
import com.github.thomasnield.rxkotlinfx.toObservable
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.withLatestFrom
import io.reactivex.subjects.PublishSubject
import javafx.scene.control.Button
import javafx.scene.control.TextField
import tornadofx.*


class EditView : View() {
    private val startIndex = 0
    private val cards = mutableListOf<Card>()
    private val cardSubj = PublishSubject.create<Card>()
    private val canAdd = PublishSubject.create<Boolean>()
    private val index = PublishSubject.create<Pair<Int, Int>>()

    // nodes that we need to have globally available
    private var leftButton: Button by singleAssign()
    private var rightButton: Button by singleAssign()
    private var front: TextField by singleAssign()
    private var back: TextField by singleAssign()

    init {
        // whether or not a card can be added or not
        cardSubj.map { it.front == "" || it.back == "" }
                .subscribe(canAdd)
        // keeping track of adding cards to the backing list
        index.map { it.first }
                .withLatestFrom(cardSubj, {a, b -> Pair(a, b)})
                .subscribe { (ind, card) ->
           if (ind in 0.until(cards.size)) {
               cards[ind] = card
           } else {
               cards.add(card)
           }
        }
    }

    override val root = borderpane {
        top {
            label("Name Here")
        }

        left {
            leftButton = button("Move Left") {
                index.map { it.second <= 0 }.subscribe { this.disableProperty().set(it) }
            }
        }
        right {
            rightButton = button("Move Right") {
                canAdd.subscribe { this.disableProperty().set(it) }
            }
        }
        // keeping track of navigation
        Observable.merge(
                leftButton.actionEvents().map {-1},
                rightButton.actionEvents().map {1}
        ).scan(Pair(startIndex, startIndex)) { (_, acc), x ->
            Pair(acc, Math.max(0, acc + x))
        }.subscribe(index)

        center {
            vbox {
                label("Front Side")
                front = textfield {
                    this.actionEvents().subscribe { back.requestFocus() }
                }
                label("Back Side")
                back = textfield {
                    this.actionEvents().withLatestFrom(canAdd, { _, bool -> bool })
                            .subscribe {
                                front.requestFocus()
                                rightButton.fire()
                            }
                }
                index.map { it.second }.subscribe {
                    val newCard = cards.getOrNull(it)
                    front.text = newCard?.front ?: ""
                    back.text = newCard?.back ?: ""
                }
                Observables.combineLatest(
                    front.textProperty().toObservable(),
                    back.textProperty().toObservable(), ::Card
                ).subscribe(cardSubj)
            }
        }
    }
}