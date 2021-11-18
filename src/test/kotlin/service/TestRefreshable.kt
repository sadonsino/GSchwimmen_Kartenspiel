package service
import view.Refreshable

class TestRefreshable : Refreshable{
    var refreshNachSpielStarten : Boolean = false
    private set
    var refreshNachSpielende : Boolean = false
        private set
    var refreshNachNeuemSpieler : Boolean = false
        private set
    var refreshNachKartenTauschenMitte : Boolean = false
        private set
    var refreshNachKartenTauschenHand : Boolean = false
        private set
    var refreshNachKlopfen : Boolean = false
        private set
    var refreshNachPassen : Boolean = false
        private set
    fun reset() {
        refreshNachSpielende = false
        refreshNachNeuemSpieler = false
        refreshNachKartenTauschenMitte = false
        refreshNachKartenTauschenHand = false
        refreshNachKlopfen = false
        refreshNachPassen = false

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
    override fun refreshNachKartenTauschenMitte() {
        refreshNachKartenTauschenMitte = true
    }
    override fun refreshNachKartenTauschenHand() {
        refreshNachKartenTauschenHand = true
    }
    override fun refreshNachKlopfen() {
        refreshNachKlopfen = true
    }
    override fun refreshNachPassen() {
        refreshNachPassen = true
    }


}