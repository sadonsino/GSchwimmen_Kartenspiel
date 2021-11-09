package entity

class Schwimmen(val spieler: ArrayDeque<Spieler>, val karte: SchwimmKarte) {


    var mitte : ArrayDeque<SchwimmKarte> = ArrayDeque<SchwimmKarte>(3)
    var stapel : ArrayDeque<SchwimmKarte> = ArrayDeque<SchwimmKarte>(26)
    var aktuellerSpieler: Int = 0
    var klopfIndexe : Int = 0
    var passIndex : Int = 0
}