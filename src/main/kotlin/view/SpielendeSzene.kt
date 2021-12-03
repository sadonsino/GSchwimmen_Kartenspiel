package view
import entity.CardImageLoader
import service.SchwimmenService
import tools.aqua.bgw.components.gamecomponentviews.CardView
import tools.aqua.bgw.components.uicomponents.Button
import tools.aqua.bgw.components.uicomponents.Label
import tools.aqua.bgw.core.*
import tools.aqua.bgw.visual.ColorVisual
import tools.aqua.bgw.visual.ImageVisual
import tools.aqua.bgw.util.Font
import java.awt.Color

/**
 * Die Szene, die nach der Spielende zeigt
 */

class SpielendeSzene (private val schwimmenService: SchwimmenService):BoardGameScene(1000, 1000) ,Refreshable
{

    val quitbutton: Button = Button(
        height = 60, width = 100,
        posX = 575, posY = 850,text = "Quit",
        visual = ColorVisual.RED)


      val neuStartButton: Button = Button(
        height = 60, width = 100,
        posX = 425, posY = 850,
         text="Neu Starten",
         visual = ColorVisual.GREEN)


    private val s1punkt: Label = Label(
        height = 100,
        width = 300,
        posX = 600,
        posY = 120,
        font = Font(size = 16),
        visual = ColorVisual(Color.white)
    )
    private val s2punkt: Label = Label(
        height = 100,
        width = 300,
        posX = 600,
        posY = 270,
        font = Font(size = 16),
        visual = ColorVisual(Color.white)
    )
    private val s3punkt: Label = Label(
        height = 100,
        width = 300,
        posX = 600,
        posY = 420,
        font = Font(size = 16),
    visual = ColorVisual(Color.white)
    )
    private val s4punkt: Label = Label(
        height = 100,
        width = 300,
        posX = 600,
        posY = 570,
        font = Font(size = 16),
        visual = ColorVisual(Color.white)
    )

  private  val s1Kart1: CardView = CardView(
        height = 150,
        width = 100,
        posX = 100,
        posY = 100,
        front = ImageVisual(CardImageLoader().backImage)
    )
   private val s1Kart2: CardView = CardView(
        height = 150,
        width = 100,
        posX = 200,
        posY = 100,
        front = ImageVisual(CardImageLoader().backImage)
    )
   private val s1Kart3  : CardView = CardView(
        height = 150,
        width = 100,
        posX = 300,
        posY = 100,
        front = ImageVisual(CardImageLoader().backImage)
    )
    private  val s2Kart1: CardView = CardView(
        height = 150,
        width = 100,
        posX = 100,
        posY = 250,
        front = ImageVisual(CardImageLoader().backImage)
    )
    private val s2Kart2: CardView = CardView(
        height = 150,
        width = 100,
        posX = 200,
        posY = 250,
        front = ImageVisual(CardImageLoader().backImage)
    )
    private val s2Kart3  : CardView = CardView(
        height = 150,
        width = 100,
        posX = 300,
        posY = 250,
        front = ImageVisual(CardImageLoader().backImage)
    )
    private  val s3Kart1: CardView = CardView(
        height = 150,
        width = 100,
        posX = 100,
        posY = 400,
        front = ImageVisual(CardImageLoader().backImage)
    )
    private val s3Kart2: CardView = CardView(
        height = 150,
        width = 100,
        posX = 200,
        posY = 400,
        front = ImageVisual(CardImageLoader().backImage)
    )
    private val s3Kart3  : CardView = CardView(
        height = 150,
        width = 100,
        posX = 300,
        posY = 400,
        front = ImageVisual(CardImageLoader().backImage)
    )
    private  val s4Kart1: CardView = CardView(
        height = 150,
        width = 100,
        posX = 100,
        posY = 550,
        front = ImageVisual(CardImageLoader().backImage)
    )
    private val s4Kart2: CardView = CardView(
        height = 150,
        width = 100,
        posX = 200,
        posY = 550,
        front = ImageVisual(CardImageLoader().backImage)
    )
    private val s4Kart3  : CardView = CardView(
        height = 150,
        width = 100,
        posX = 300,
        posY = 550,
        front = ImageVisual(CardImageLoader().backImage)
    )
    private val ergebniss :  Label = Label(
        height = 30,
        width = 20,
        posX = 200,
        posY = 200,

    )

    override fun refreshNachSpielende() {
        val cardImageLoader = CardImageLoader()
       val schwimmenSpiel = schwimmenService.schwimmSpiel
        s1Kart1.frontVisual = ImageVisual(cardImageLoader.frontImageFor(
            schwimmenSpiel!!.spieler[0].hand[0].farbe,schwimmenSpiel.spieler[0].hand[0].wert))
        s1Kart2.frontVisual = ImageVisual(cardImageLoader.frontImageFor(
            schwimmenSpiel.spieler[0].hand[1].farbe,schwimmenSpiel.spieler[0].hand[1].wert))
        s1Kart3.frontVisual = ImageVisual(cardImageLoader.frontImageFor(
            schwimmenSpiel.spieler[0].hand[2].farbe,schwimmenSpiel.spieler[0].hand[2].wert))
        s1punkt.text = "Spieler 1: "+schwimmenSpiel.spieler[0].spielerName
        s1punkt.text += " Punkte: "+schwimmenSpiel.spieler[0].summePunkte().toString()
        s1Kart1.showFront()
        s1Kart2.showFront()
        s1Kart3.showFront()
        s2Kart1.frontVisual = ImageVisual(cardImageLoader.frontImageFor(
            schwimmenSpiel.spieler[1].hand[0].farbe,schwimmenSpiel.spieler[1].hand[0].wert))
        s2Kart2.frontVisual = ImageVisual(cardImageLoader.frontImageFor(
            schwimmenSpiel.spieler[1].hand[1].farbe,schwimmenSpiel.spieler[1].hand[1].wert))
        s2Kart3.frontVisual = ImageVisual(cardImageLoader.frontImageFor(
            schwimmenSpiel.spieler[1].hand[2].farbe,schwimmenSpiel.spieler[1].hand[2].wert))
        s2punkt.text = "Spieler 2: "+schwimmenSpiel.spieler[1].spielerName
        s2punkt.text +=" Punkte: "+schwimmenSpiel.spieler[1].summePunkte().toString()
        s2Kart1.showFront()
        s2Kart2.showFront()
        s2Kart3.showFront()
        if (schwimmenSpiel.spieler.size>2)
        {
            s3punkt.isVisible = true
            s3Kart1.isVisible = true
            s3Kart2.isVisible = true
            s3Kart3.isVisible = true
            s3Kart1.frontVisual = ImageVisual(cardImageLoader.frontImageFor(
                schwimmenSpiel.spieler[2].hand[0].farbe,schwimmenSpiel.spieler[2].hand[0].wert))
            s3Kart2.frontVisual = ImageVisual(cardImageLoader.frontImageFor(
                schwimmenSpiel.spieler[2].hand[1].farbe,schwimmenSpiel.spieler[2].hand[1].wert))
            s3Kart3.frontVisual = ImageVisual(cardImageLoader.frontImageFor(
                schwimmenSpiel.spieler[2].hand[2].farbe,schwimmenSpiel.spieler[2].hand[2].wert))
            s3punkt.text = "Spieler 3: "+schwimmenSpiel.spieler[2].spielerName
            s3punkt.text +=" Punkte: "+schwimmenSpiel.spieler[2].summePunkte().toString()
            s3Kart1.showFront()
            s3Kart2.showFront()
            s3Kart3.showFront()

        }
        if (schwimmenSpiel.spieler.size>3)
        {
            s4punkt.isVisible = true
            s4Kart1.isVisible = true
            s4Kart2.isVisible = true
            s4Kart3.isVisible = true
            s4Kart1.frontVisual = ImageVisual(cardImageLoader.frontImageFor(
                schwimmenSpiel.spieler[3].hand[0].farbe,schwimmenSpiel.spieler[3].hand[0].wert))
            s4Kart2.frontVisual = ImageVisual(cardImageLoader.frontImageFor(
                schwimmenSpiel.spieler[3].hand[1].farbe,schwimmenSpiel.spieler[3].hand[1].wert))
            s4Kart3.frontVisual = ImageVisual(cardImageLoader.frontImageFor(
                schwimmenSpiel.spieler[3].hand[2].farbe,schwimmenSpiel.spieler[3].hand[2].wert))
            s4punkt.text = "Spieler 4: "+schwimmenSpiel.spieler[3].spielerName
            s4punkt.text +=" Punkte: "+schwimmenSpiel.spieler[3].summePunkte().toString()
            s4Kart1.showFront()
            s4Kart2.showFront()
            s4Kart3.showFront()

        }


    }


    init {
        background = ColorVisual(Color.green)
        s3punkt.isVisible = false
        s4punkt.isVisible = false
        s3Kart1.isVisible = false
        s3Kart2.isVisible = false
        s3Kart3.isVisible = false
        s4Kart1.isVisible = false
        s4Kart2.isVisible = false
        s4Kart3.isVisible = false
        opacity = .5
        addComponents(
            quitbutton,
            neuStartButton,
            ergebniss,
            s1Kart1,
            s1Kart2,
            s1Kart3,
            s2Kart1,
            s2Kart2,
            s2Kart3,
            s3Kart1,
            s3Kart2,
            s3Kart3,
            s4Kart1,
            s4Kart2,
            s4Kart3,
            s1punkt,
            s2punkt,
            s3punkt,
            s4punkt


        )
    }
}