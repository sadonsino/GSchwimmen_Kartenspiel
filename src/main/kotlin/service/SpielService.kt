package service

import entity.*

class SpielService(private val schwimmenService : SchwimmenService) : AbstractRefreshingService()
{



    /**
     * Start ein neues Spiel
     * @param spielerArray ist ein Feld von Typ Spieler
     */

    fun spielStarten (spielerArray: ArrayDeque<Spieler>)
    {
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
            CardValue.values()[(index % 8) + 5]  //
        )
    }.shuffled()


    /**
     * Hier wird die Werte der Karten berechnet und
     * die maximal Punkte von der Karten bezüglich der Logik des Spiels zurückgegeben
     * @param spieler ist der Spieler, der seine Karten berechnet wird
     * @return maximale Punkte, was er erreichen kann
     */
    fun summePunkte (spieler: Spieler) : Double
    {
       val schwimmSpiel = schwimmenService.schwimmSpiel
        checkNotNull(schwimmSpiel)
        var punkte  = 0.0
        val hand  = spieler.hand
        if (hand[0].wert==hand[1].wert && hand[1].wert ==hand[2].wert)
        {
                punkte = 30.5
        }
        var herzPunkte = 0.0
        var kreuzPunkte = 0.0
        var karoPunkte = 0.0
        var pickPunkte = 0.0

        for(karte in hand)
        {
            when (karte.farbe)
            {
                CardSuit.SPADES -> pickPunkte += karte.karteWert()
                CardSuit.CLUBS -> kreuzPunkte += karte.karteWert()
                CardSuit.HEARTS -> herzPunkte += karte.karteWert()
                CardSuit.DIAMONDS -> karoPunkte += karte.karteWert()
            }

        }

        return maxOf(herzPunkte,karoPunkte,karoPunkte,pickPunkte,punkte)
    }

    /**
     * Es wird den aktuellerSpielerIndex erhört und zwei Fälle betrachtet:
     * 1. Wenn den passIndex ist gleich spieler.size, dann muss die Funktion [mitteErneuren()] aufgerufen
     * 2. Wenn aktuellerSpielerIndex ist gleich spieler.size-1, dann muss aktuellerSpielerIndex
     * auf 0 gesetzt wird
     */
    fun naechsterSpieler()
    {
        val schwimmSpiel = schwimmenService.schwimmSpiel
        checkNotNull(schwimmSpiel)
            if (!beendeSpiel()&&schwimmSpiel.aktuellerSpielerIndex == schwimmSpiel.spieler.size - 1)
            {
                schwimmSpiel.aktuellerSpielerIndex = 0
            }
            else schwimmSpiel.aktuellerSpielerIndex++

    }

    /**
     * Hier wird zurückgegeben, ob das Spiel beendet bezüglich der Logik des Spiels ist oder nicht
     * @return true, wenn die Ende des Spiels ist oder false, wenn nicht
     */
    fun beendeSpiel(): Boolean
    {
        val schwimmSpiel = schwimmenService.schwimmSpiel
        checkNotNull(schwimmSpiel)
       if (schwimmSpiel.karten.size<3&&schwimmSpiel.passIndex==schwimmSpiel.spieler.size)
       {
           return true
       }
       else return schwimmSpiel.klopfIndexe==schwimmSpiel.spieler.size
    }

    /**
     * Hier wird die Karten von Mitte und entfernt und drei neue Karten von stapel hinzufügt
     */
    fun mitteErneuren()
    {
        val schwimmSpiel = schwimmenService.schwimmSpiel
        checkNotNull(schwimmSpiel)
        if (schwimmSpiel.karten.size<3)
        {
            beendeSpiel()
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

        }

    }


    }
