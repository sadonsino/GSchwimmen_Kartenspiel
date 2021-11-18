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
        schwimmSpiel.passIndex = 0
        if (schwimmSpiel.klopfIndexe>0)
        {
            schwimmSpiel.klopfIndexe++
        }
        val aktullerSpieler = schwimmSpiel.spieler[schwimmSpiel.aktuellerSpielerIndex]
        val temp = aktullerSpieler.hand
        aktullerSpieler.hand = schwimmSpiel.mitte
        schwimmSpiel.mitte = temp
        schwimmenService.spielService.naechsterSpieler()

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
        schwimmSpiel.passIndex = 0
        val aktullerSpieler = schwimmSpiel.spieler[schwimmSpiel.aktuellerSpielerIndex]
        if (schwimmSpiel.klopfIndexe>0)
        {
            schwimmSpiel.klopfIndexe++
        }
        var handPosition = -1
        var mittePosition = -1
        for (k in 0 until  schwimmSpiel.mitte.size)
        {
            if (schwimmSpiel.mitte[k]==mittKarte)
            {
                mittePosition = k
            }
            if (aktullerSpieler.hand[k]==handKarte)
            {
                handPosition = k
            }

        }
        schwimmSpiel.mitte.set(mittePosition,handKarte)
        aktullerSpieler.hand.set(handPosition,mittKarte)
        schwimmenService.spielService.naechsterSpieler()

    }

    /**
     *  Wird den PassIndex erhört und überprüft,
     *  ob schon jemanden geklopft hat, dann wird den KlopfIndex auch erhört
     */
    fun pass()
    {
        val schwimmSpiel = schwimmenService.schwimmSpiel
        checkNotNull(schwimmSpiel)
        schwimmSpiel.passIndex++
        if (schwimmSpiel.klopfIndexe>0)
        {
            schwimmSpiel.klopfIndexe++
        }
        if (!schwimmenService.spielService.beendeSpiel()&&schwimmSpiel.passIndex == schwimmSpiel.spieler.size )
        {
            schwimmenService.spielService.mitteErneuren()
        }
        schwimmenService.spielService.naechsterSpieler()

    }

    /**
     * Wird den KlopfIndex erhört
     */
    fun klopfen()
    {
        val schwimmSpiel = schwimmenService.schwimmSpiel
        checkNotNull(schwimmSpiel)
        schwimmSpiel.passIndex = 0
        schwimmSpiel.klopfIndexe++
        schwimmenService.spielService.naechsterSpieler()
    }
}