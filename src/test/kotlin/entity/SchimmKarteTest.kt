package entity

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class SchimmKarteTest {
    /**
     * Karten, mit denen des Testes überführen konnte
     */
    private val achtPick : SchwimmKarte = SchwimmKarte(CardSuit.SPADES,CardValue.EIGHT)
    private val aceHerz : SchwimmKarte = SchwimmKarte(CardSuit.HEARTS,CardValue.ACE)
    private val neuenKaro : SchwimmKarte= SchwimmKarte(CardSuit.DIAMONDS, CardValue.NINE)
    private val zehnKreuz : SchwimmKarte= SchwimmKarte(CardSuit.CLUBS,CardValue.TEN)
    private val andereZehnKreuz : SchwimmKarte= SchwimmKarte(CardSuit.CLUBS,CardValue.TEN)

    // Die Farbe der Karten

    private val pick = "♠"
    private val herz = "♥"
    private val karo = "♦"
    private val kreuz = "♣"
   @Test
           /**
            * teste, ob toString für einige TestKarten die richtigen Strings erzeugt
            */
   fun testToString()
   {
       assertEquals(pick+"8",achtPick.toString())
       assertEquals(herz+"A",aceHerz.toString())
       assertEquals(karo+"9",neuenKaro.toString())
       assertEquals(kreuz+"10",zehnKreuz.toString())
   }
    @Test
            /**
             * Teste, ob die Methode, die richtige Wert ausgibt
             */
    fun karteWertTest()
    {
        assertEquals(achtPick.karteWert(),8)
        assertEquals(aceHerz.karteWert(),11)
        assertEquals(neuenKaro.karteWert(),9)
        assertEquals(zehnKreuz.karteWert(),10)
    }
    @Test
            /**
             * Teste, ob die Karten mit gleichen Wert und Farbe gleich sind
             * oder mit verschiedene Wert und Farbe nicht gleich sind
             */
    fun karteEquals()
    {
        assertNotEquals(zehnKreuz,neuenKaro)
        assertEquals(zehnKreuz, andereZehnKreuz)
    }
  }
