package view
import service.AbstractRefreshingService

/**
 * Refreshable ist ein Interface, das einen Mechanismus für die Serviceschicht-Klassen bereitstellt,
 * um (normalerweise den Ansichtsklassen) mitzuteilen, dass bestimmte Änderungen
 * an der Entity-Schicht vorgenommen wurden,
 * sodass die User Interface entsprechend aktualisiert werden kann.
 * @see AbstractRefreshingService
 */

interface Refreshable {
    /**
     * Aktualisierungen, die nach dem Start eines neuen Spiels erforderlich sind
     */
    fun refreshNachSpielStarten(){}

    /**
     * Aktualisierungen, die nach dem Spieler eine Aktion ausgewählt hat
     */
    fun refreshNachSpielende(){}

    /**
     * Aktualisierungen, die nach der Ende des Spieles
     */
    fun refreshNachNeuemSpieler(){}

    /**
     * Aktualisierungen, die nach der Karten von Mitte geändert wurde oder einem Spieler gepasst oder geklopft hat
     */
    fun refreshNachZugMittegeandert(){}


}