package service
import entity.*

class SchwimmenService{
    var schwimmSpiel : Schwimmen? = null
    val spielService = SpielService(this)
    val spielerAktionService = SpielerAktionService(this)
    fun spielStarten (spieler: ArrayDeque<Spieler>)
    {
        val schwimmSpiel1 = Schwimmen(spieler)
        schwimmSpiel1.addAufOben(defaultRandomCardList())

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
    private fun defaultRandomCardList() = List(32)
    { index ->
        SchwimmKarte(
            CardSuit.values()[index / 8],
            CardValue.values()[(index % 8) + 5]
        )
    }.shuffled()

}