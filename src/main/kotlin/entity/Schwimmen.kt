package entity

class Schwimmen(val spieler: ArrayDeque<Spieler>) {
    var mitte : ArrayDeque<SchwimmKarte> = ArrayDeque<SchwimmKarte>(3)
    var stapel : ArrayDeque<SchwimmKarte> = ArrayDeque<SchwimmKarte>(32)
    var aktuellerSpieler: Int = 0
    var klopfIndexe : Int = 0
    var passIndex : Int = 0
}