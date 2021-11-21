package service
import  kotlin.test.*
import entity.*
import org.junit.jupiter.api.Test
import view.Refreshable

/**
 * Klasse, die Tests für [SpielService] und [SpielerAktionService] bereitstellt.
 * * [TestRefreshable] wird verwendet, um das korrekte Aktualisierungsverhalten zu überprüfen.
 */
class ServiceTest {
    /**
     * Es wird das Spiel mit 3 Spieler gestartet.
     *
     * @param refreshable aktualisierbare Elemente, die dem SchwimmenService direkt nach seiner Instanziierung
     * hinzugefügt werden sollen.
     * @return SchwimmenService, der das gestartete Spiel als [SchwimmenService.schwimmSpiel] enthält.
     */

    private fun spielErstellung(vararg refreshable: Refreshable): SchwimmenService {
        val mc = SchwimmenService()
        refreshable.forEach { mc.addRefreshable(it) }
        val spieler : ArrayDeque<Spieler> = ArrayDeque(4)
        spieler.add(Spieler("Sadoun"))
        spieler.add(Spieler("Max"))
        spieler.add(Spieler("Alsinou"))
        mc.spielService.spielStarten(spieler)
        return  mc
    }
    /**
     * Teste, ob das Spiel schon mit drei Spieler gestartet wird.
     * */
    @Test
    fun spielStartenTest()
    {
        val testRefreshable = TestRefreshable()
        val mc = spielErstellung(testRefreshable)
        assertNotNull(mc.schwimmSpiel)
        assertEquals(mc.schwimmSpiel!!.spieler.size,3)
        assertNotEquals(mc.schwimmSpiel!!.spieler.size,4)
        assertNotEquals(mc.schwimmSpiel!!.spieler.size,2)
    }
    /**
     * Teste, ob die aktuellerSpielerIndex erhört wird, wenn die Methode
     * [SpielService.naechsterSpieler] aufgerufen wird.
     */
    @Test
    fun naechsterSpielerTest()
    {
        val testRefreshable = TestRefreshable()
        val mc = spielErstellung(testRefreshable)
        assertEquals(mc.schwimmSpiel!!.aktuellerSpielerIndex,0)
        mc.spielService.naechsterSpieler()
        assertEquals(mc.schwimmSpiel!!.aktuellerSpielerIndex,1)
        mc.spielService.naechsterSpieler()
        assertEquals(mc.schwimmSpiel!!.aktuellerSpielerIndex,2)
        mc.spielService.naechsterSpieler()
        assertEquals(mc.schwimmSpiel!!.aktuellerSpielerIndex,0)

    }
    /**
     * Teste, ob das Spiel beenden muss, wenn eine geklopft hat oder
     * die Stapel kleiner 3 ist und den PassIndex gleich die Anzahl der Spieler.
     */
    @Test
    fun beendeSpielTest()
    {
        val testRefreshable = TestRefreshable()
        val mc = spielErstellung(testRefreshable)
        assertEquals(mc.spielService.beendeSpiel(),false)
        mc.schwimmSpiel!!.klopfIndexe = mc.schwimmSpiel!!.spieler.size
        assertEquals(mc.spielService.beendeSpiel(),true)
        mc.schwimmSpiel!!.klopfIndexe = 0
        assertEquals(mc.spielService.beendeSpiel(),false)
        mc.schwimmSpiel!!.passIndex = mc.schwimmSpiel!!.spieler.size
        assertEquals(mc.spielService.beendeSpiel(),false)
        for (i in 0 until mc.schwimmSpiel!!.karten.size-3)
        {
            mc.schwimmSpiel!!.karten.removeFirst()
        }
        assertEquals(mc.spielService.beendeSpiel(),false)
        mc.schwimmSpiel!!.karten.removeFirst()
        assertEquals(mc.spielService.beendeSpiel(),true)

    }
    /**
     * Teste, ob den PassIndex auf 0 gesetzt wird und die mitteKarten getauscht wird,
     * wenn [SpielService.mitteErneuren] aufgerufen wird.
     */
    @Test
    fun mitteErneurenTest()
    {
        val testRefreshable = TestRefreshable()
        val mc = spielErstellung(testRefreshable)
        val mitteNew : ArrayDeque<SchwimmKarte> = ArrayDeque(3)
        mc.spielerAktionService.pass()
        val altPassIndex = mc.schwimmSpiel!!.passIndex

        for (i in 0 until 3)
        {
            mitteNew.addFirst(mc.schwimmSpiel!!.karten[i])   // stapel [0,1,2]
        }
        val kartenAlt = mc.schwimmSpiel!!.karten.size

        mc.spielService.mitteErneuren()
        assertEquals(mc.schwimmSpiel!!.mitte,mitteNew)
        assertEquals(kartenAlt-3,mc.schwimmSpiel!!.karten.size)
        assertNotSame(altPassIndex,mc.schwimmSpiel!!.passIndex)
        assertEquals(mc.schwimmSpiel!!.passIndex,0)
    }
    /**
     * Teste, ob alle Karte der Spieler mit der Karten von Mitte getauscht wird.
     */
    @Test
    fun alleKarteTauschenTest()
    {
        val testRefreshable = TestRefreshable()
        val mc = spielErstellung(testRefreshable)
        val karteErsteSpieler = mc.schwimmSpiel!!.spieler[0].hand
        val karteMitte = mc.schwimmSpiel!!.mitte
        assertEquals(mc.schwimmSpiel!!.mitte,karteMitte)
        assertEquals(mc.schwimmSpiel!!.spieler[0].hand,karteErsteSpieler)
        mc.spielerAktionService.alleKarteTauschen()
        assertEquals(mc.schwimmSpiel!!.spieler[0].hand,karteMitte)
        assertEquals(mc.schwimmSpiel!!.mitte,karteErsteSpieler)

    }
    /**
     * Teste, ob die ausgewählten Karten getaucht werden.
     */
    @Test
    fun einKarteTauschen()
    {
        val testRefreshable = TestRefreshable()
        val mc = spielErstellung(testRefreshable)
        val karteSpiler = mc.schwimmSpiel!!.spieler[0].hand[0]
        val karteMitte = mc.schwimmSpiel!!.mitte[0]
        assertEquals(mc.schwimmSpiel!!.spieler[0].hand[0],karteSpiler)
        assertEquals(mc.schwimmSpiel!!.mitte[0],karteMitte)
        mc.spielerAktionService.einKarteTauschen(karteMitte,karteSpiler)
        assertEquals(mc.schwimmSpiel!!.spieler[0].hand[0],karteMitte)
        assertEquals(mc.schwimmSpiel!!.mitte[0],karteSpiler)
    }
    /**
     * Teste, ob den PassIndex erhört wird, wenn ein Spieler gepasst hat.
     * und den PassIndex auf 0 ist, wenn alle Spieler gepasst haben und eine
     * andere Aktion ausgewählt haben.
     */
    @Test
    fun passenTest()
    {
        val testRefreshable = TestRefreshable()
        val mc = spielErstellung(testRefreshable)
        assertEquals(mc.schwimmSpiel!!.passIndex,0)
        mc.spielerAktionService.pass()
        assertEquals(mc.schwimmSpiel!!.passIndex,1)
        mc.spielerAktionService.pass()
        assertEquals(mc.schwimmSpiel!!.passIndex,2)
        mc.spielerAktionService.pass()
        assertEquals(mc.schwimmSpiel!!.passIndex,0)
        assertNotEquals(mc.schwimmSpiel!!.passIndex,1)
        mc.spielerAktionService.pass()
        assertEquals(mc.schwimmSpiel!!.passIndex,1)
        mc.spielerAktionService.alleKarteTauschen()
        assertEquals(mc.schwimmSpiel!!.passIndex,0)
        mc.spielerAktionService.klopfen()
        assertEquals(mc.schwimmSpiel!!.passIndex,0)
        mc.spielerAktionService.einKarteTauschen(mc.schwimmSpiel!!.mitte[0],mc.schwimmSpiel!!.spieler[0].hand[0])
        assertEquals(mc.schwimmSpiel!!.passIndex,0)
    }
    /**
     * Teste, ob wenn ein Spieler geklopft hat, dann sind alle
     * anderen Spieler noch genau einmal an der Reihe.
     * Danach ist das Spiel beendet.
     */
    @Test
    fun klopfenTest()
    {
        val testRefreshable = TestRefreshable()
        val mc = spielErstellung(testRefreshable)
        assertEquals(mc.schwimmSpiel!!.klopfIndexe,0)
        mc.spielerAktionService.klopfen()
        assertEquals(mc.schwimmSpiel!!.klopfIndexe,1)
        mc.spielerAktionService.pass()
        assertEquals(mc.schwimmSpiel!!.klopfIndexe,2)
        mc.spielerAktionService.alleKarteTauschen()
        assertEquals(mc.schwimmSpiel!!.klopfIndexe,3)
        assertEquals(mc.spielService.beendeSpiel(),true)
    }
}