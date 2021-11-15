package service

import entity.*

class SpielService(private val schwimmenService : SchwimmenService) : AbstractRefreshingService()
{
    fun summePunkte (spieler: Spieler) : Double
    {
       val schwimmSpiel = schwimmenService.schwimmSpiel
        checkNotNull(schwimmSpiel)
        var punkte : Double
        val hand  = spieler.hand
        if (hand[0].wert==hand[1].wert && hand[1].wert ==hand[2].wert)
        {
                punkte = 30.5
                return punkte
        }
        if (hand[0].farbe==hand[1].farbe && hand[1].farbe ==hand[2].farbe)
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
    fun naechsterSpieler()
    {
        val schwimmSpiel = schwimmenService.schwimmSpiel
        checkNotNull(schwimmSpiel)
        if (!beendeSpiel())
        {
            if (schwimmSpiel.passIndex == 4)
            {
                mitteErneuren()
            }
             if(schwimmSpiel.aktuellerSpielerIndex==3)
            {

                schwimmSpiel.aktuellerSpielerIndex = 0 ;

            }
            else schwimmSpiel.aktuellerSpielerIndex++
        }

    }
    fun beendeSpiel(): Boolean
    {
        val schimmSpiel = schwimmenService.schwimmSpiel
        checkNotNull(schimmSpiel)
       if (stapelLeer()&&schimmSpiel.passIndex==4)
       {
           return true
       }
       else if (schimmSpiel.klopfIndexe==4  )
       {
            return true
       }
        return false
    }
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
    fun resetPassIndex()
    {
        val schwimmSpiel = schwimmenService.schwimmSpiel
        checkNotNull(schwimmSpiel)
        schwimmSpiel.passIndex = 0

    }
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
