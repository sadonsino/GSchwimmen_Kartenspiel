package view
import entity.*
import service.SchwimmenService
import tools.aqua.bgw.components.gamecomponentviews.CardView
import tools.aqua.bgw.components.uicomponents.Button
import tools.aqua.bgw.components.uicomponents.Label
import tools.aqua.bgw.core.BoardGameScene
import tools.aqua.bgw.visual.ColorVisual
import tools.aqua.bgw.visual.ImageVisual
import java.awt.Color

/**
 * Die Szene die den kompletten Tisch zeigt, mit den MitteKarten, dem Nachziehstapel, die Karten des aktuellen Spilers
 * einen PassCounter, eine Anzeige ob geklopft wurde.
 * @property schwimmenService [SchwimmenService]
 */

class SpielTischSzene (private val schwimmenService: SchwimmenService): BoardGameScene(1920, 1080), Refreshable{


    private  val gameStackBack: CardView = CardView(
        height = 200,
        width = 130,
        posX = 200,
        posY = 200,
        front = ImageVisual(CardImageLoader().backImage)
    )

    private val spielerKarte1: CardView = CardView(
        height = 200,
        width = 130,
        posX = 700,
        posY = 550,
        front = ImageVisual(CardImageLoader().frontImageFor(CardSuit.CLUBS, CardValue.EIGHT))
                )
    private val spielerKarte2: CardView = CardView(
        height = 200,
        width = 130,
        posX = 860,
        posY = 550,
        front = ImageVisual(CardImageLoader().frontImageFor(CardSuit.CLUBS, CardValue.EIGHT))
    )

    private val spielerKarte3: CardView = CardView(
        height = 200,
        width = 130,
        posX = 1020,
        posY = 550,
        front = ImageVisual(CardImageLoader().frontImageFor(CardSuit.CLUBS, CardValue.EIGHT))
    )

    private val mitteKarte1: CardView = CardView(
        height = 200,
        width = 130,
        posX = 700,
        posY = 200,
        front = ImageVisual(CardImageLoader().frontImageFor(CardSuit.CLUBS, CardValue.EIGHT))
    )
    private val mitteKarte2: CardView = CardView(
        height = 200,
        width = 130,
        posX = 860,
        posY = 200,
        front = ImageVisual(CardImageLoader().frontImageFor(CardSuit.CLUBS, CardValue.EIGHT))
    )

    private val mitteKarte3: CardView = CardView(
        height = 200,
        width = 130,
        posX = 1020,
        posY = 200,
        front = ImageVisual(CardImageLoader().frontImageFor(CardSuit.CLUBS, CardValue.EIGHT))
    )
    private val klopfText : Label = Label(
        height = 50,
        width = 200,
        posX = 1500,
        posY = 100,
        text = "Niemand hat geklopft",
        visual = ColorVisual( Color.white)
    )

    private val passenText : Label = Label(
        height = 50,
        width = 200,
        posX = 1500,
        posY = 200,
        text = "Niemand hat gepasst",
        visual = ColorVisual( Color.white)

    )

    private val alleKartenTauschenButton = Button(
        width = 150, height =60,
        posX = 1185, posY = 850,
        text = "AlleKartenTauschen"
    ).apply {
        visual = ColorVisual(Color.white.darker())
        onMouseClicked = {
            schwimmenService.spielerAktionService.alleKarteTauschen()
        }
    }
    private val klopfenButton = Button(
        width = 150 , height = 60,
        posX = 1000 , posY = 850,
        text = "Klopfen"
    ).apply {
        visual = ColorVisual(Color.white.darker())
        onMouseClicked = {
            schwimmenService.spielerAktionService.klopfen()
        }
    }
    private val passenButton = Button(
        width = 150 , height = 60,
        posX = 800 , posY = 850,
        text = "Passen"
    ).apply {
        visual = ColorVisual(Color.white.darker())
        onMouseClicked = {
            schwimmenService.spielerAktionService.pass()

        }
    }
    private val einKarteTauschenButton = Button(
        width = 150 , height = 60,
        posX = 600 , posY = 850,
        text = "EineKarteTauschen",
        visual = ColorVisual(Color.white.darker())
    ).apply {
        onMouseClicked = {
            passenButton.isVisible = false
            alleKartenTauschenButton.isVisible = false
            alleKartenTauschenButton.isVisible = false
            klopfenButton.isVisible = false
            einKarteTauschen()
             }


    }


    private var ausgewaelteKarteHand = -1
    private var ausgewaelteKarteMitte = -1


    private  val mitteKarte1tauschen = Button(
        height = 30,
        width = 30,
        posX = 750,
        posY = 155,
        text = "1",
        visual = ColorVisual(Color.white)
    ).apply {
        onMouseClicked = {
            ausgewaelteKarteMitte = 0
            println(ausgewaelteKarteHand)
            kartentauschen()
        }
    }


    private  val mitteKarte2tauschen = Button(
        height = 30,
        width = 30,
        posX = 910,
        posY = 155,
        text = "2",
        visual = ColorVisual(Color.white)

    ).apply {
        onMouseClicked = {
            ausgewaelteKarteMitte = 1
            println(ausgewaelteKarteHand)
            kartentauschen()
        }
    }


    private   val mitteKarte3tauschen = Button(
        height = 30,
        width = 30,
        posX = 1070,
        posY = 155,
        text = "3",
        visual = ColorVisual(Color.white)
    ).apply {
        onMouseClicked = {
            ausgewaelteKarteMitte = 2
            println(ausgewaelteKarteHand)
            kartentauschen()
        }
    }


    private  val handKarte1tauschen = Button(
        height = 30,
        width = 30,
        posX = 750,
        posY = 775,
        text = "1",
        visual = ColorVisual(Color.white)
    ).apply {
        onMouseClicked = {
            ausgewaelteKarteHand = 0
            mitteKarteAuswaelen()
        }
    }


    private  val handKarte2tauschen = Button(
        height = 30,
        width = 30,
        posX = 910,
        posY = 775,
        text = "2",
        visual = ColorVisual(Color.white)
    ).apply {
        onMouseClicked = {
            ausgewaelteKarteHand = 1
            println(ausgewaelteKarteHand)
            mitteKarteAuswaelen()
        }
    }


    private   val handKarte3tauschen = Button(
        height = 30,
        width = 30,
        posX = 1070,
        posY = 775,
        text = "3",
        visual = ColorVisual(Color.white)
    ).apply {
        onMouseClicked = {
            ausgewaelteKarteHand = 2
            println(ausgewaelteKarteHand)
            mitteKarteAuswaelen()
        }
    }

    private fun einKarteTauschen()
    {

        einKarteTauschenButton.isVisible = false
        handKarte1tauschen.isVisible = true
        handKarte2tauschen.isVisible = true
        handKarte3tauschen.isVisible = true

    }

    private fun mitteKarteAuswaelen ()
    {
        handKarte1tauschen.isVisible = false
        handKarte2tauschen.isVisible = false
        handKarte3tauschen.isVisible = false

        mitteKarte1tauschen.isVisible = true
        mitteKarte2tauschen.isVisible = true
        mitteKarte3tauschen.isVisible = true
    }
    private fun kartentauschen ()
    {
        mitteKarte1tauschen.isVisible = false
        mitteKarte2tauschen.isVisible = false
        mitteKarte3tauschen.isVisible = false
        klopfenButton.isVisible = true
        passenButton.isVisible = true
        alleKartenTauschenButton.isVisible = true
        alleKartenTauschenButton.isVisible = true
        val schimmenSpiel = schwimmenService.schwimmSpiel
        checkNotNull(schimmenSpiel)
        val aktullerSpieler = schimmenSpiel.aktuellerSpielerIndex
        schwimmenService.spielerAktionService.einKarteTauschen(schimmenSpiel.mitte[ausgewaelteKarteMitte],schimmenSpiel.spieler[aktullerSpieler].hand[ausgewaelteKarteHand])




    }


    override fun refreshNachSpielStarten() {
        val schwimmenSpiel = schwimmenService.schwimmSpiel
        checkNotNull(schwimmenSpiel)
        val cardImageLoader = CardImageLoader()
        passenText.text = "Niemand hat gepasst"
        klopfText. text = "Niemand hat geklopft"
        klopfenButton.isDisabled = false
        val mitte = schwimmenSpiel.mitte
        val hand = schwimmenSpiel.spieler[0].hand
        spielerKarte1.frontVisual = ImageVisual(cardImageLoader.frontImageFor(hand[0].farbe,hand[0].wert))
        spielerKarte2.frontVisual = ImageVisual(cardImageLoader.frontImageFor(hand[1].farbe,hand[1].wert))
        spielerKarte3.frontVisual = ImageVisual(cardImageLoader.frontImageFor(hand[2].farbe,hand[2].wert))
        mitteKarte1.frontVisual = ImageVisual(cardImageLoader.frontImageFor(mitte[0].farbe,mitte[0].wert))
        mitteKarte2.frontVisual = ImageVisual(cardImageLoader.frontImageFor(mitte[1].farbe,mitte[1].wert))
        mitteKarte3.frontVisual = ImageVisual(cardImageLoader.frontImageFor(mitte[2].farbe,mitte[2].wert))
        mitteKarte1.showFront()
        mitteKarte2.showFront()
        mitteKarte3.showFront()
        spielerKarte1.showFront()
        spielerKarte2.showFront()
        spielerKarte3.showFront()

    }

    override fun refreshNachZugMittegeandert()
    {
        einKarteTauschenButton.isVisible = true
       val schwimmenSpiel = schwimmenService.schwimmSpiel
        checkNotNull(schwimmenSpiel)
        val cardImageLoader = CardImageLoader()
        val mitte = schwimmenSpiel.mitte
        mitteKarte1.frontVisual = ImageVisual(cardImageLoader.frontImageFor(mitte[0].farbe,mitte[0].wert))
        mitteKarte2.frontVisual = ImageVisual(cardImageLoader.frontImageFor(mitte[1].farbe,mitte[1].wert))
        mitteKarte3.frontVisual = ImageVisual(cardImageLoader.frontImageFor(mitte[2].farbe,mitte[2].wert))
        mitteKarte1.showFront()
        mitteKarte2.showFront()
        mitteKarte3.showFront()
    }

    override fun refreshNachNeuemSpieler() {
        val schwimmenSpiel = schwimmenService.schwimmSpiel
        checkNotNull(schwimmenSpiel)
        val cardImageLoader = CardImageLoader()
        val aktuellerSpieler = schwimmenSpiel.aktuellerSpielerIndex
        val hand = schwimmenSpiel.spieler[aktuellerSpieler].hand
        spielerKarte1.frontVisual = ImageVisual(cardImageLoader.frontImageFor(hand[0].farbe,hand[0].wert))
        spielerKarte2.frontVisual = ImageVisual(cardImageLoader.frontImageFor(hand[1].farbe,hand[1].wert))
        spielerKarte3.frontVisual = ImageVisual(cardImageLoader.frontImageFor(hand[2].farbe,hand[2].wert))
        spielerKarte1.showFront()
        spielerKarte2.showFront()
        spielerKarte3.showFront()
        if (schwimmenSpiel.klopfIndexe==1)
        {
            klopfText.text = schwimmenSpiel.spieler[aktuellerSpieler].spielerName+" hat geklopft"
            klopfenButton.isDisabled=true
        }
        when (schwimmenSpiel.passIndex) {
            0 -> {
                passenText.text = "Niemand hat gepasst"
            }
            1 -> {
                passenText.text = schwimmenSpiel.spieler[aktuellerSpieler].spielerName+"  hat gepasst\n"
            }
            else -> passenText.text += schwimmenSpiel.spieler[aktuellerSpieler].spielerName+"  hat gepasst\n"
        }

    }


    init {
        handKarte1tauschen.isVisible = false
        handKarte2tauschen.isVisible = false
        handKarte3tauschen.isVisible = false
        mitteKarte1tauschen.isVisible = false
        mitteKarte2tauschen.isVisible = false
        mitteKarte3tauschen.isVisible = false
    background = ColorVisual(0, 139, 0)
    addComponents(
        spielerKarte1,
        spielerKarte2,
        spielerKarte3,
        mitteKarte1,
        mitteKarte2,
        mitteKarte3,
        gameStackBack,
        alleKartenTauschenButton,
        klopfenButton,
        passenButton,
        klopfText,
        passenText,
        einKarteTauschenButton,
        handKarte1tauschen,
        handKarte2tauschen,
        handKarte3tauschen,
        mitteKarte1tauschen,
        mitteKarte2tauschen,
        mitteKarte3tauschen
    )
}
}