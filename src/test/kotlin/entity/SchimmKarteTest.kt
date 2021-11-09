package entity

import kotlin.test.Test
import kotlin.test.assertEquals

class SchimmKarteTest {
 val achtPick : SchwimmKarte = SchwimmKarte(CardSuit.SPADES,CardValue.EIGHT)
    private val aceHerz : SchwimmKarte = SchwimmKarte(CardSuit.HEARTS,CardValue.ACE)
    private val neuenKaro : SchwimmKarte= SchwimmKarte(CardSuit.DIAMONDS, CardValue.NINE)
    private val zehnKreuz : SchwimmKarte= SchwimmKarte(CardSuit.CLUBS,CardValue.TEN)

    private val pick = "♠"
    private val herz = "♥"
    private val karo = "♦"
    private val kreuz = "♣"
   @Test
   fun testToString()
   {
       assertEquals(pick+"8",achtPick.toString())
       assertEquals(herz+"A",aceHerz.toString())
       assertEquals(karo+"9",neuenKaro.toString())
       assertEquals(kreuz+"10",zehnKreuz.toString())

   }
    @Test
    fun karteWertTest()
    {
        assertEquals(achtPick.karteWert(),8)
        assertEquals(aceHerz.karteWert(),11)
        assertEquals(neuenKaro.karteWert(),9)
        assertEquals(zehnKreuz.karteWert(),10)
    }
}