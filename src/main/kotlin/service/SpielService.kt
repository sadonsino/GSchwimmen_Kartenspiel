package service

import entity.*

class SpielService(private val schwimmenService : SchwimmenService) : AbstractRefreshingService()
{
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
        val punkte : Double
        val hand  = spieler.hand
        if (hand[0].wert==hand[1].wert && hand[1].wert ==hand[2].wert)
        {
                punkte = 30.5
                return punkte
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
        punkte = maxOf(herzPunkte,karoPunkte,karoPunkte,pickPunkte)
        return punkte
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
        if (!beendeSpiel() && schwimmSpiel.passIndex == schwimmSpiel.spieler.size )     // 4 Spieler = 0.1 0  0 _pass-> 1 1 -- pass 0 0
        {
                mitteErneuren()
        }
        if (!beendeSpiel())
        {
            if (schwimmSpiel.aktuellerSpielerIndex == schwimmSpiel.spieler.size - 1)
            {
                schwimmSpiel.aktuellerSpielerIndex = 0
            }
            else schwimmSpiel.aktuellerSpielerIndex++
        }
    }

    /**
     * Hier wird zurückgegeben, ob das Spiel beendet bezüglich der Logik des Spiels ist oder nicht
     * @return true, wenn die Ende des Spiels ist oder false, wenn nicht
     */
    fun beendeSpiel(): Boolean
    {
        val schwimmSpiel = schwimmenService.schwimmSpiel
        checkNotNull(schwimmSpiel)
       if (stapelLeer()&&schwimmSpiel.passIndex==schwimmSpiel.spieler.size)
       {
           return true
       }
       else if (schwimmSpiel.klopfIndexe==schwimmSpiel.spieler.size  )
       {
            return true
       }
        return false
    }

    /**
     * Hier wird die Karten von Mitte und entfernt und drei neue Karten von stapel hinzufügt
     */
    fun mitteErneuren()
    {
        val schwimmSpiel = schwimmenService.schwimmSpiel
        checkNotNull(schwimmSpiel)
        if (stapelLeer())
        {
            beendeSpiel()
        }
        else
        {
            for (kart in schwimmSpiel.mitte)
            {
                schwimmSpiel.mitte.removeFirst()
            }

            for (kart in schwimmSpiel.mitte)
            {
                schwimmSpiel.mitte.addFirst(schwimmSpiel.karten.removeFirst())
            }
            resetPassIndex()

        }

    }

    /**
     * Hier wird den PassIndex auf 0 gesetzt
     */
    fun resetPassIndex()
    {
        val schwimmSpiel = schwimmenService.schwimmSpiel
        checkNotNull(schwimmSpiel)
        schwimmSpiel.passIndex = 0

    }

    /**
     * Hier wird überprüft, ob die Stapel schon leer ist oder nicht
     * @return true, wenn den Stapel weniger als 3 Karten hat, false, wenn nicht
     */
     fun stapelLeer() : Boolean
    {
        val schwimmSpiel = schwimmenService.schwimmSpiel
        checkNotNull(schwimmSpiel)
        if (schwimmSpiel.karten.size<3)
        {
            return true
        }
        return false
    }

    /**
     * Wird ein Array von Gewinner zurückgegeben, weil es den Fall sein Kann,
     * dass mehr als einen Spieler die selbe Punkte hat
     * @return Array von Type Spieler, die schon das maximal Punkte haben
     */
    fun gewinnerListe() : ArrayDeque<Spieler>
    {
        val schwimmSpiel = schwimmenService.schwimmSpiel
        checkNotNull(schwimmSpiel){"No game currently running."}

        var max = summePunkte(schwimmSpiel.spieler.first())
        var indexOfMax=0
        val gewinnerArray : ArrayDeque<Spieler> = ArrayDeque()

        for(i in 1 until schwimmSpiel.spieler.size){
            if(summePunkte(schwimmSpiel.spieler[i])>max){
                max=summePunkte(schwimmSpiel.spieler[i])
                indexOfMax=i
            }
        }
        gewinnerArray.add(schwimmSpiel.spieler[indexOfMax])
        for(i in 0 until schwimmSpiel.spieler.size){
            if(summePunkte(schwimmSpiel.spieler[i])==max && i!=indexOfMax){
                gewinnerArray.add(schwimmSpiel.spieler[i])
            }
        }
        return gewinnerArray
    }

    }
