package entity
/**
 * Klasse, die einen Spielzustand von "Schwimmen" darstellt.
 * @param spieler enthält alle Spieler
 */

class Schwimmen(val spieler: ArrayDeque<Spieler>, val karten:ArrayDeque<SchwimmKarte> )
{
    /**
     *  aktuellerSpieler ist den Index, der des aktuellen Spielers
     *  klopfIndexe ist den Index, der schon gezeigt, ob es geklopft wurde
     *  passIndex ist den Index, der schon zählt, wie viel mal gepasst wurde
     *  mitte enthält die Karte, die in der Mitte von Spielfeld steht
     */
     var mitte : ArrayDeque<SchwimmKarte> = ArrayDeque(3)
     var aktuellerSpielerIndex:Int = 0
     var klopfIndexe :Int = 0
     var passIndex:Int = 0

    /**
     * Die Funktion legt eine vorgegebene Kartenliste oben auf diesen Kartenstapel
     * @param karte ist eine Liste von SchimmKarte
     */
   fun kartenHinzufuegen(karte : List<SchwimmKarte>)
   {
      karte.forEach(this.karten::addFirst)
   }
}