package entity
/**
 * Die Klasse Spieler repräsentiert der Spieler in dem Spiel "Schwimmen"
 * @param spielerName repräsentiert die Spielername
 */
class Spieler( val spielerName : String ) {
    /**
     *  hand: ist ein Array aus drei Karten, mit denen der Spieler spielen kann.
     */
    var hand : ArrayDeque<SchwimmKarte> = ArrayDeque<SchwimmKarte>(3)
    /* gibt die SpielerName und die drei Karten, die das er hat, zurück */
    override fun toString() : String
    {
        return "$spielerName hat diesen Karten: ${hand.toString()}"
    }
}