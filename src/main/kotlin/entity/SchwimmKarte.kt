package entity
/**
 * SchimmKarte ist eine data Class und wird durch die Farbe und Wert charakterisiert
 * @param farbe beschreibt die Farbe der Karte
 * @param wert beschreibt die Wert der Karte
 */
data class SchwimmKarte(val farbe : CardSuit, val wert : CardValue) {
    /**
     *gibt den Card int-Wert jeder Karte zurück -> K,Q,J(10 Punkte) ,A (11 Punkte),  7,8,9,10 (7,8,9,10 Punkte)
     */
    fun getPunkte() : Double
    {
        return try
        {
            wert.toString().toDouble()
        }
        catch (e: NumberFormatException)
        {
            if (wert.toString() == "A") 11.0
            else 10.0
        }
    }
    override fun toString() : String = "${farbe.toString()}${wert.toString()}"
}