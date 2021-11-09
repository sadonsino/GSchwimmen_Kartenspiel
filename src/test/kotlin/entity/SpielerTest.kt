package entity
import kotlin.test.*

class SpielerTest {
    private val spieler1 : Spieler = Spieler("Sadoun")
    @Test
    fun testToString()
    {
        spieler1.hand.add(SchwimmKarte(CardSuit.HEARTS,CardValue.FIVE))
        spieler1.hand.add(SchwimmKarte(CardSuit.SPADES,CardValue.FIVE))
        assertEquals(spieler1.toString(),"Sadoun hat diesen Karten: "+spieler1.hand.toString(),"Erste Versuch")
    }



}