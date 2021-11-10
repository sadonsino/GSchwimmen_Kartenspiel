package entity
/**
 * Die Klasse Spieler repräsentiert der Spieler in dem Spiel "Schwimmen"
 * @param spielerName repräsentiert die Spielername
 */
class Spieler( val spielerName : String ) {
    /**
     *  gepasst: repräsentiert den Passstatus des Spielers dar.
     *  geklopft: repräsentiert den Klopfstatus des Spielers.
     *  punkte: repräsentiert die Punkte des Spielers.
     *  hand: ist ein Array aus drei Karten, mit denen der Spieler spielen kann.
     */
    var gepasst : Boolean = false
    var geklopft : Boolean = false
    var punkte : Double = 0.0
    var hand : ArrayDeque<SchwimmKarte> = ArrayDeque<SchwimmKarte>(3)
    // gibt die SpielerName und die drei Karten, die das er hat, zurück
    override fun toString() : String
    {
        return "$spielerName hat diesen Karten: ${hand.toString()}"
    }
}