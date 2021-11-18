package view
import service.AbstractRefreshingService


interface Refreshable {
    fun refreshNachSpielStarten(){}
    fun refreshNachSpielende(){}
    fun refreshNachNeuemSpieler(){}
    fun refreshNachKartenTauschenMitte(){}
    fun refreshNachKartenTauschenHand(){}
    fun refreshNachKlopfen(){}
    fun refreshNachPassen(){}


}