package service
import entity.*

class SpielerAktionService(private val schwimmenService: SchwimmenService) : AbstractRefreshingService(){
    /**
     * Die Karten in der Mitte und die Karten, die den Spieler im Hand hat, werden getauscht
     */
    fun alleKarteTauschen()
    {
        val schwimmSpiel = schwimmenService.schwimmSpiel
        checkNotNull(schwimmSpiel)
        if (schwimmSpiel.klopfIndexe>0)
        {
            schwimmSpiel.klopfIndexe++
        }
        val aktullerSpieler = schwimmSpiel.spieler[schwimmSpiel.aktuellerSpielerIndex]
        val temp = aktullerSpieler.hand
        aktullerSpieler.hand = schwimmSpiel.mitte
        schwimmSpiel.mitte = temp
    }

    /**
     * Eine ausgewählte Karte von Mitte und eine Karte von Hand der Spieler werden getauscht
     * @param mittKarte die ausgewählte Karte von Mitte
     * @param handKarte die ausgewählte Karte von Hand der Spieler
     */
    fun einKarteTauschen(mittKarte: SchwimmKarte ,handKarte: SchwimmKarte)
    {
        val schwimmSpiel = schwimmenService.schwimmSpiel
        checkNotNull(schwimmSpiel)
        val aktullerSpieler = schwimmSpiel.spieler[schwimmSpiel.aktuellerSpielerIndex]
        if (schwimmSpiel.klopfIndexe>0)
        {
            schwimmSpiel.klopfIndexe++
        }
        for (k in schwimmSpiel.mitte)
        {
            if (k==mittKarte)
            {
                for (h in aktullerSpieler.hand)
                {
                    if (h==handKarte)
                    {
                        val temp = k
                        schwimmSpiel.mitte.remove(k)
                        schwimmSpiel.mitte.addFirst(h)
                        aktullerSpieler.hand.remove(h)
                        aktullerSpieler.hand.add(temp)
                    }
                }
            }
        }

    }

    /**
     *  Wird den PassIndex erhört und überprüft,
     *  ob schon jemanden geklopft hat, dann wird den KlopfIndex auch erhört
     */
    fun pass()
    {
        val schwimmSpiel = schwimmenService.schwimmSpiel
        checkNotNull(schwimmSpiel)
        if (schwimmSpiel.klopfIndexe>0)
        {
            schwimmSpiel.klopfIndexe++
        }
        schwimmSpiel.passIndex++
        schwimmenService.spielService.naechsterSpieler()

    }

    /**
     * Wird den KlopfIndex erhört
     */
    fun klopfen()
    {
        val schwimmSpiel = schwimmenService.schwimmSpiel
        checkNotNull(schwimmSpiel)
        schwimmSpiel.klopfIndexe++
        schwimmenService.spielService.naechsterSpieler()
    }
}