package view


import service.SchwimmenService
import tools.aqua.bgw.core.BoardGameApplication

/**
 * Umsetzung der BGW [BoardGameApplication] für das SchwimmenSpiel
 */

class SchwimmenAnwendung : BoardGameApplication("Schwimmen"), Refreshable {

    private val schwimmenService = SchwimmenService()

    private val bereitAbfrageSzene = BereitAbfrageSzene(schwimmenService)
        .apply {
            bereitButton.onMouseClicked={
               hideMenuScene()
            }
        }
    private val spielTischSzene = SpielTischSzene(schwimmenService)
    private val spielendeSzene = SpielendeSzene(schwimmenService).apply {
        neuStartButton.onMouseClicked = {
            showMenuScene(neueSpielMenuSzene)
            showGameScene(spielTischSzene)
        }
        quitbutton.onMouseClicked = {
            exit()
        }
    }
    private val neueSpielMenuSzene = NeueSpielMenuSzene(schwimmenService).apply {
        quitbutton.onMouseClicked = {
            exit()
        }
    }

    init {
        schwimmenService.addRefreshables(
            this,
            bereitAbfrageSzene,
            spielendeSzene,
            spielTischSzene,
            neueSpielMenuSzene

        )

        this.showGameScene(spielTischSzene)
        this.showMenuScene(neueSpielMenuSzene)


    }

    override fun refreshNachSpielStarten() {
            hideMenuScene()
            showMenuScene(bereitAbfrageSzene)
    }

    override fun refreshNachNeuemSpieler() {
        showMenuScene(bereitAbfrageSzene)
    }

        override fun refreshNachSpielende() {
            showGameScene(spielendeSzene)
        }


    }
