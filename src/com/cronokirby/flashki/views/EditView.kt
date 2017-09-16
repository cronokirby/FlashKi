package com.cronokirby.flashki.views

import com.cronokirby.flashki.controllers.DeckEditor
import com.cronokirby.flashki.models.CardModel
import com.github.thomasnield.rxkotlinfx.actionEvents
import javafx.scene.control.TextField
import tornadofx.*


class EditView : View() {
    val editor: DeckEditor by inject()
    val model = CardModel(editor.cardProp)
    var back: TextField by singleAssign()
    var front: TextField by singleAssign()

    override val root = borderpane {
        top {
            label(editor.deck.metaData.name)
        }
        left {
            button("Move Left") {
                action {
                    editor.moveLeft()
                }
            }
        }
        right {
            button("Move Right").actionEvents().subscribe {println("foo")}
        }
        center {
            vbox {
                vbox {
                    label("Front Side")
                    front = textfield {
                        bind(model.front)
                        action {
                            back.requestFocus()
                        }
                    }
                }
                vbox {
                    label("Back Side")
                    back = textfield {
                        bind(model.back)
                        action {
                            editor.moveRight()
                            front.requestFocus()
                        }
                    }
                }
            }
        }
    }
}