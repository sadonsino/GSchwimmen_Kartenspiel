package entity

class Spieler( val spielerName : String) {

    var gepasst : Boolean = false
    var geklopft : Boolean = false
    var Punkte : Double = 0.0
    var hand : ArrayDeque<SchwimmKarte> = ArrayDeque<SchwimmKarte>(3)

    override fun toString() : String{
        return "$spielerName hat diesen Katre: ${hand.toString()}"
    }


}