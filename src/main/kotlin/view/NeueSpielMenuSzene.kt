package view
import service.SchwimmenService
import tools.aqua.bgw.components.uicomponents.Button
import tools.aqua.bgw.components.uicomponents.Label
import tools.aqua.bgw.core.MenuScene
import tools.aqua.bgw.util.Font
import tools.aqua.bgw.visual.ColorVisual
import tools.aqua.bgw.components.uicomponents.TextField
import java.awt.Color.white

/**
 * Die Szene, die Start Menu zeigt und wird überprüft, ob mindestens zwei Spielernamen angegeben wurde
 *
 */

class NeueSpielMenuSzene(schwimmenService: SchwimmenService) : MenuScene(1000, 1000) ,Refreshable {
    private val headlineLabel = Label(
        width = 600, height = 100, posX = 200, posY = 0,
        text = "InfoText",
        font = Font(size = 22)

    )

    private val p1Label = Label(
        width = 300, height = 300,
        posX = 200, posY = 75,
        text = "Spieler 1:",
        font = Font(size = 20)
    )
    private val p1Input: TextField = TextField(
        width = 200, height = 35,
        posX = 400, posY = 208,
        font = Font(size = 18),
        text = "P1"
    ).apply {
        onKeyTyped = {
            startbutton.isDisabled = this.text.isBlank()
            p2Input.isDisabled = this.text.isBlank()
            p3Input.isDisabled = this.text.isBlank()
            p4Input.isDisabled = this.text.isBlank()
        }
    }
    private val p2Input: TextField = TextField(
        width = 200, height = 35,
        posX = 400, posY = 360,
        font = Font(size = 18),
        text = "P2"
    ).apply {
        onKeyTyped = {
            startbutton.isDisabled = this.text.isBlank()
            p3Input.isDisabled = this.text.isBlank()
            p4Input.isDisabled = this.text.isBlank()
        }
    }
    private val p3Input: TextField = TextField(
        width = 200, height = 35,
        posX = 400, posY = 504,
        font = Font(size = 18),
        text = "P3"
    ).apply {
        onKeyTyped = {
            p4Input.isDisabled = this.text.isBlank()
        }
    }
    private val p4Input: TextField = TextField(
        width = 200, height = 35,
        posX = 400, posY = 655,
        font = Font(size = 18),
        text = "P4"
    )


    private val p2Label = Label(
        width = 300, height = 300,
        posX = 200, posY = 225,
        text = "Spieler 2:",
        font = Font(size = 22)
    )

    private val p3Label = Label(
        width = 300, height = 300,
        posX = 200, posY = 375,
        text = "Spieler 3:",
        font = Font(size = 22)
    )

    private val p4Label = Label(
        width = 300, height = 300,
        posX = 200, posY = 525,
        text = "Spieler 4:",
        font = Font(size = 22)
    )
    val quitbutton: Button = Button(
        height = 60, width = 100,
        posX = 600, posY = 800
    ).apply {
        text = "Beenden"
        visual = ColorVisual.RED
    }

    private val startbutton: Button = Button(
        height = 60, width = 100,
        posX = 350, posY = 800, text = "Starten"
    ).apply {
        visual = ColorVisual.GREEN
        onMouseClicked =
            {
        val spieler: ArrayDeque<String> = ArrayDeque(4)
        spieler.add(p1Input.text.trim())
        spieler.add(p2Input.text.trim())
        if (p3Input.text.isNotBlank()) {
            spieler.add(p3Input.text.trim())
            if (p4Input.text.isNotBlank()) {
                spieler.add(p4Input.text.trim())
            }
        }
        schwimmenService.spielService.spielStarten(spieler)
                println(schwimmenService.schwimmSpiel!!.spieler)
    }
}

    init {
        background = ColorVisual(white)
        addComponents(
            headlineLabel,
            p1Label,
            p1Input,
            p2Input,
            p3Input,
            p4Input,
            p2Label,
            p3Label,
            p4Label,
            startbutton,
            quitbutton

        )
    }

}