package entity
import kotlin.test.*

/**
 * SpielerTest ist die Klasse, die Funktionalität der Klasse Spieler Überprüft
 */

class SpielerTest {
    /**
     * Spieler, mit denen des Testes durchführen konnte
      */
    private val spieler1 : Spieler = Spieler("Sadoun")
    private val spieler2 : Spieler = Spieler("")
    private val spieler3 : Spieler = Spieler("")
    @BeforeTest
    fun inHandHinzufuegen()
    {
        spieler1.hand.add(SchwimmKarte(CardSuit.HEARTS,CardValue.SEVEN))
        spieler1.hand.add(SchwimmKarte(CardSuit.SPADES,CardValue.SEVEN))
    }

    /**
     * teste, ob toString für einige TestSpieler die richtigen Strings erzeugt
     */
    @Test
    fun testToString()
    {
        assertEquals(spieler1.toString(),"Sadoun hat diesen Karten: "+spieler1.hand.toString())
    }
    /**
     * Teste, ob zwei Spieler mit gleichen Namen gleich sind oder
     * mit zwei verschiedene Namen nicht gleich sind
     */
    @Test
    fun testEquals()
    {
        assertNotSame(spieler1.spielerName, spieler3.spielerName)
        assertEquals(spieler3.spielerName, spieler2.spielerName)
    }
}