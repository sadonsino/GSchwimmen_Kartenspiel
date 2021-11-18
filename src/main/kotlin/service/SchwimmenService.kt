package service
import entity.*
import view.Refreshable

/**
 * HauptKlasse der ServiceSchicht.
 * Bietet Zugang für alle anderen Serviceklassen
 */

class SchwimmenService{

    val spielService = SpielService(this)
    val spielerAktionService = SpielerAktionService(this)

    /**
     * schwimmSpiel ist das aktuelle Spiel. Kann `null` sein, wenn noch kein Spiel gestartet wurde.
     */
    var schwimmSpiel : Schwimmen? = null

    fun addRefreshable(newRefreshable: Refreshable) {
        spielService.addRefreshable(newRefreshable)
        spielerAktionService.addRefreshable(newRefreshable)
    }
    fun addRefreshables(vararg newRefreshables: Refreshable) {
        newRefreshables.forEach { addRefreshable(it) }
    }


}