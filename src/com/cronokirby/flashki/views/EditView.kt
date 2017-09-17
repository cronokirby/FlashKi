package com.cronokirby.flashki.views

import com.cronokirby.flashki.events.ChangeViewEvent
import com.cronokirby.flashki.events.ViewPages
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
    // the signal of current cards
    private val cardSubj = PublishSubject.create<Card>()
    // the past, and current navigation index
    private var index = PublishSubject.create<Pair<Int, Int>>()

    // nodes that we need to have globally available
    private var leftButton: Button by singleAssign()
    private var rightButton: Button by singleAssign()
    private var front: TextField by singleAssign()
    private var back: TextField by singleAssign()

    init {
        // keeping track of adding cards to the backing list
        index.withLatestFrom(cardSubj, { a, b -> Pair(a, b)})
                .subscribe { (ind, card) ->
           val (past, now) = ind
           if (past in 0.until(cards.size)) {
               cards[past] = card
           } else if (past < now){
               cards.add(card)
           }
        }
    }

    override val root = borderpane {
        val isNew = index.map { it.second >= cards.size }
        // only allow us to add cards if they're not empty and new
        val cannotAdd = Observables.combineLatest(cardSubj, isNew, { card, new ->
            !card.isFull() || (new && cards.contains(card))
        })

        top {
            hbox {
                textfield {
                    promptText = "Deck Name"
                }
                button("Save") {
                    action {
                        fire(ChangeViewEvent(ViewPages.NotEditing))
                    }
                }
            }
        }

        left {
            leftButton = button("Previous Card") {
                index.map { it.second <= 0 }.subscribe { this.disableProperty().set(it) }
            }
        }
        right {
            rightButton = button {
                isNew.map { if (it) "Add Card" else "Next Card" }
                        .subscribe { this.textProperty().set(it) }
                cannotAdd.subscribe { this.disableProperty().set(it) }
            }
        }
        // keeping track of navigation
        Observable.merge(
                leftButton.actionEvents().map { -1 },
                rightButton.actionEvents().map { 1 }
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
                    this.actionEvents().subscribe {
                        front.requestFocus()
                        // this will be protected from advances by the button
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