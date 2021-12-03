package view

import service.SchwimmenService
import tools.aqua.bgw.components.uicomponents.Label
import tools.aqua.bgw.core.*
import tools.aqua.bgw.util.Font
import tools.aqua.bgw.visual.ColorVisual
import java.awt.Color
import tools.aqua.bgw.components.uicomponents.Button

/**
 * Die Szene, die naechster Spieler abfragt, ob er schin bereit ist, weil er drin ist
 * @property schwimmenService [SchwimmenService]
 */

class BereitAbfrageSzene (private val schwimmenService: SchwimmenService) : MenuScene(1000, 1000) ,Refreshable {
    private val aktullerSpieler = Label(
        width = 550 , height = 500,
        posX = 200, posY = 200,
        font = Font(size = 30),
        text = ""

    )
    val bereitButton = Button(
        width = 170, height = 50,
        posX = 400, posY = 550,
        text = "Bereit"
    ).apply {
        visual = ColorVisual(0, 255, 0)
    }


    override fun refreshNachSpielStarten() {
        val schwimmenSpiel = checkNotNull(schwimmenService.schwimmSpiel)
        aktullerSpieler.text = "Spieler "+ schwimmenSpiel.getName() +" du bist dran"

    }

    override fun refreshNachNeuemSpieler() {
        val schwimmenSpiel = checkNotNull(schwimmenService.schwimmSpiel)
        aktullerSpieler.text = "Spieler "+ schwimmenSpiel.getName() +" du bist dran"
    }

    init {
        opacity = .5
        background = ColorVisual(Color.white)
        addComponents(
            aktullerSpieler,
            bereitButton
        )
    }



}
