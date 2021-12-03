package service

import entity.*

/**
 * Klasse in der die Spielfunktionen gewährleistet werden.
 * @property schwimmenService[SchwimmenService] aktueller schwimmenService wird übergeben
 */

class SpielService(private val schwimmenService : SchwimmenService) : AbstractRefreshingService()
{
    /**
     * Start ein neues Spiel
     * @param spielerArrayString ist ein Feld von Typ String
     */

    fun spielStarten (spielerArrayString: ArrayDeque<String>)
    {
        val spielerArray : ArrayDeque<Spieler> =  ArrayDeque<Spieler>(4)
        for (i in 0 until spielerArrayString.size)
        {
            spielerArray.add(Spieler(spielerArrayString[i]))
        }

       val karten : ArrayDeque<SchwimmKarte> = ArrayDeque(32)
        val schwimmSpiel1 = Schwimmen(spielerArray,karten)
        schwimmSpiel1.kartenHinzufuegen(defaultRandomCardList())

        for (p in 0 until  schwimmSpiel1.spieler.size)
        {
            for (h in 0 until 3)
            {
                schwimmSpiel1.spieler[p].hand.add(schwimmSpiel1.karten[0])
                schwimmSpiel1.karten.removeFirst()
            }
        }
        for (m in 0 until 3)
        {
            schwimmSpiel1.mitte.add(schwimmSpiel1.karten[0])
            schwimmSpiel1.karten.removeFirst()

        }
        schwimmenService.schwimmSpiel = schwimmSpiel1
        onAllRefreshables { refreshNachSpielStarten() }
    }

    /**
     * Erstellt eine gemischte 32-Karten-Liste aller vier Farben und Karten
     * von 7 bis Ass.
     * @return eine Liste, die 32 Objekt von SchwimmKarte enthält.
     */
    private fun defaultRandomCardList() = List(32)
    { index ->
        SchwimmKarte(
            CardSuit.values()[index / 8],
            CardValue.values()[(index % 8) + 5]  //
        )
    }.shuffled()

    /**
     * Es wird den aktuellerSpielerIndex erhört und zwei Fälle betrachtet:
     * 1. Wenn den passIndex ist gleich spieler.size, dann muss die Funktion [mitteErneuren()] aufgerufen
     * 2. Wenn aktuellerSpielerIndex ist gleich spieler.size-1, dann muss aktuellerSpielerIndex
     * auf 0 gesetzt wird.
     */
    fun naechsterSpieler()
    {
        val schwimmSpiel = schwimmenService.schwimmSpiel
        checkNotNull(schwimmSpiel)
            if (!beendeSpiel())
            {
                if (schwimmSpiel.aktuellerSpielerIndex == schwimmSpiel.spieler.size - 1)
                {
                schwimmSpiel.aktuellerSpielerIndex = 0
                }
                else schwimmSpiel.aktuellerSpielerIndex++
                onAllRefreshables { refreshNachNeuemSpieler() }
            }
        else onAllRefreshables { refreshNachSpielende() }


    }
    /**
     * Hier wird zurückgegeben, ob das Spiel beendet bezüglich der Logik des Spiels ist oder nicht.
     * @return true, wenn die Ende des Spiels ist oder false, wenn nicht.
     */
    fun beendeSpiel(): Boolean
    {
        val schwimmSpiel = schwimmenService.schwimmSpiel
        checkNotNull(schwimmSpiel)
        return if (schwimmSpiel.karten.size<3&&schwimmSpiel.passIndex==schwimmSpiel.spieler.size) {
            true
        } else schwimmSpiel.klopfIndexe==schwimmSpiel.spieler.size
    }

    /**
     * Hier wird die Karten von Mitte und entfernt und drei neue Karten von stapel hinzufügt.
     */
    fun mitteErneuren()
    {
        val schwimmSpiel = schwimmenService.schwimmSpiel
        checkNotNull(schwimmSpiel)
        if (schwimmSpiel.karten.size<3)
        {
            onAllRefreshables { refreshNachSpielende() }
        }
        else
        {
            for (kart in 0 until  3)
            {
                schwimmSpiel.mitte.removeFirst()
            }

            for (kart in 0 until  3)
            {
                schwimmSpiel.mitte.addFirst(schwimmSpiel.karten.removeFirst())
            }

            schwimmSpiel.passIndex=0
            onAllRefreshables { refreshNachZugMittegeandert() }

        }

    }

}
