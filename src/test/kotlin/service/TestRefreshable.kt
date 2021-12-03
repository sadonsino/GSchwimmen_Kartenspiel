package service
import view.Refreshable

/**
 * Es wird nichts aktualisiert aber merkt, ob eine Refresh-Methode
 * aufgerufen wurde (seit dem letzten Zurücksetzen)
 */

class TestRefreshable : Refreshable{
    var refreshNachSpielStarten : Boolean = false
    private set
    var refreshNachSpielende : Boolean = false
        private set
    var refreshNachNeuemSpieler : Boolean = false
        private set
    var refreshNachZugMittegeandert : Boolean = false
        private set

    /**
     * Hier wird alle Eigenschaft auf false gesetzt
     */
    fun reset() {
        refreshNachSpielende = false
        refreshNachNeuemSpieler = false
        refreshNachZugMittegeandert = false

    }
    override fun refreshNachSpielStarten() {
        refreshNachSpielStarten = true
    }
    override fun refreshNachSpielende() {
        refreshNachSpielende = true
    }
    override fun refreshNachNeuemSpieler() {
        refreshNachNeuemSpieler = true
    }
    override fun refreshNachZugMittegeandert() {
        refreshNachZugMittegeandert = true
    }


}