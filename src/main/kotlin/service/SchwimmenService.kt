package service
import entity.*

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

    /**
     * Start ein neues Spiel
     * @param spielerArray ist ein Feld von Typ Spieler
     */

    fun spielStarten (spielerArray: ArrayDeque<Spieler>)
    {
        val schwimmSpiel1 = Schwimmen(spielerArray)
        schwimmSpiel1.kartenHinzufügen(defaultRandomCardList())

        for (p in 0 until  schwimmSpiel1.spieler.size)
        {
            for (h in 0 until schwimmSpiel1.spieler[p].hand.size)
            {
                schwimmSpiel1.spieler[p].hand.add(schwimmSpiel1.karten[0])
                schwimmSpiel1.karten.removeFirst()
            }
        }
        for (m in 0 until schwimmSpiel1.mitte.size)
        {
            schwimmSpiel1.mitte.add(schwimmSpiel1.karten[0])
            schwimmSpiel1.karten.removeFirst()

        }
        schwimmSpiel = schwimmSpiel1

    }

    /**
     * Erstellt eine gemischte 32-Karten-Liste aller vier Farben und Karten
     * von 7 bis Ass
     * @return eine Liste, die 32 Objekt von SchwimmKarte enthält
     */
    private fun defaultRandomCardList() = List(32)
    { index ->
        SchwimmKarte(
            CardSuit.values()[index / 8],
            CardValue.values()[(index % 8) + 5]
        )
    }.shuffled()

}