package entity

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

/**
 * SpielerTest ist die Klasse, die Funktionalität der Klasse SchimmKarte Überprüft
 */

class SchimmKarteTest {
    /**
     * Karten, mit denen des Testes überführen konnte
     */
    private val achtPick : SchwimmKarte = SchwimmKarte(CardSuit.SPADES,CardValue.EIGHT)
    private val aceHerz : SchwimmKarte = SchwimmKarte(CardSuit.HEARTS,CardValue.ACE)
    private val neuenKaro : SchwimmKarte= SchwimmKarte(CardSuit.DIAMONDS, CardValue.NINE)
    private val zehnKreuz : SchwimmKarte= SchwimmKarte(CardSuit.CLUBS,CardValue.TEN)
    private val andereZehnKreuz : SchwimmKarte= SchwimmKarte(CardSuit.CLUBS,CardValue.TEN)

    /**
     *  Die Farbe der Karten
     */

    private val pick = "♠"
    private val herz = "♥"
    private val karo = "♦"
    private val kreuz = "♣"

    /**
     * teste, ob toString für einige TestKarten die richtigen Strings erzeugt
     */
   @Test
   fun testToString()
   {
       assertEquals(pick+"8",achtPick.toString())
       assertEquals(herz+"A",aceHerz.toString())
       assertEquals(karo+"9",neuenKaro.toString())
       assertEquals(kreuz+"10",zehnKreuz.toString())
   }
    /**
     * Teste, ob die Methode, die richtige Wert ausgibt
     */
    @Test
    fun karteWertTest()
    {
        assertEquals(achtPick.karteWert(),8)
        assertEquals(aceHerz.karteWert(),11)
        assertEquals(neuenKaro.karteWert(),9)
        assertEquals(zehnKreuz.karteWert(),10)
    }
    /**
     * Teste, ob die Karten mit gleichen Wert und Farbe gleich sind
     * oder mit verschiedene Wert und Farbe nicht gleich sind
     */
    @Test
    fun karteEquals()
    {
        assertNotEquals(zehnKreuz,neuenKaro)
        assertEquals(zehnKreuz, andereZehnKreuz)
    }
  }
