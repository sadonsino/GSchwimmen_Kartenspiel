package service
import  kotlin.test.*
import entity.*
import org.junit.jupiter.api.Test
import view.Refreshable

class ServiceTest {
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
    @Test
    fun spielStartenTest()
    {
        val testRefreshable = TestRefreshable()
        val mc = spielErstellung(testRefreshable)
        assertNotNull(mc.schwimmSpiel)
    }

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
            mitteNew.addFirst(mc.schwimmSpiel!!.karten.get(i))
        }
        val kartenAlt = mc.schwimmSpiel!!.karten.size

        mc.spielService.mitteErneuren()
        assertEquals(mc.schwimmSpiel!!.mitte,mitteNew)
        assertNotSame(kartenAlt,mc.schwimmSpiel!!.karten.size)
        assertNotSame(altPassIndex,mc.schwimmSpiel!!.passIndex)
        assertEquals(mc.schwimmSpiel!!.passIndex,0)
    }
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
    }
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
        mc.spielerAktionService.pass()
        assertEquals(mc.schwimmSpiel!!.klopfIndexe,3)
        assertEquals(mc.spielService.beendeSpiel(),true)

    }




}