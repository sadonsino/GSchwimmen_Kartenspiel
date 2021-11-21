package view
//import service.AbstractRefreshingService

/**
 * Refreshable ist ein Interface, das einen Mechanismus für die Serviceschicht-Klassen bereitstellt,
 * um (normalerweise den Ansichtsklassen) mitzuteilen, dass bestimmte Änderungen
 * an der Entity-Schicht vorgenommen wurden,
 * sodass die User Interface entsprechend aktualisiert werden kann.
 */

interface Refreshable {
    fun refreshNachSpielStarten(){}
    fun refreshNachSpielende(){}
    fun refreshNachNeuemSpieler(){}
    fun refreshNachKartenTauschenMitte(){}
    fun refreshNachKartenTauschenHand(){}
    fun refreshNachKlopfen(){}
    fun refreshNachPassen(){}


}