package entity
/**
 * Die Klasse Spieler repräsentiert der Spieler in dem Spiel "Schwimmen"
 * @param spielerName repräsentiert die Spielername
 */
class Spieler( val spielerName : String ) {
    /**
     *  hand: ist ein Array aus drei Karten, mit denen der Spieler spielen kann.
     */
    var hand : ArrayDeque<SchwimmKarte> = ArrayDeque(3)
    /* gibt die SpielerName und die drei Karten, die das er hat, zurück */
    override fun toString() : String
    {
        return "$spielerName hat diesen Karten: $hand"
    }
    /**
     * Hier wird die Werte der Karten berechnet und
     * die maximal Punkte von der Karten bezüglich der Logik des Spiels zurückgegeben
     * @return maximale Punkte, was er erreichen kann
     */
    fun summePunkte () : Double
    {
        val punkte: Double
        if (hand[0].wert==hand[1].wert && hand[1].wert ==hand[2].wert)
        {
            punkte = 30.5
            return punkte
        }
        else {
            var herzPunkte = 0.0
            var kreuzPunkte = 0.0
            var karoPunkte = 0.0
            var pickPunkte = 0.0

            for (karte in hand) {
                when (karte.farbe) {
                    CardSuit.SPADES -> pickPunkte += karte.getPunkte()
                    CardSuit.CLUBS -> kreuzPunkte += karte.getPunkte()
                    CardSuit.HEARTS -> herzPunkte += karte.getPunkte()
                    CardSuit.DIAMONDS -> karoPunkte += karte.getPunkte()
                }
            }
            return maxOf(herzPunkte,kreuzPunkte,karoPunkte,pickPunkte)
        }


    }
}