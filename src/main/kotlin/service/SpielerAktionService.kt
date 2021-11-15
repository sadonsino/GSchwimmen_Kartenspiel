package service
import entity.*

class SpielerAktionService(private val schwimmenService: SchwimmenService) : AbstractRefreshingService(){
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
    fun klopfen()
    {
        val schwimmSpiel = schwimmenService.schwimmSpiel
        checkNotNull(schwimmSpiel)
        schwimmSpiel.klopfIndexe++
        schwimmenService.spielService.naechsterSpieler()
    }
}