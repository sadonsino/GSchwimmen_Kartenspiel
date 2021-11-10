package entity
/**
 * Klasse, die einen Spielzustand von "Schwimmen" darstellt.
 * @param spieler enthält alle Spieler
 * @param mitte enthält die Karte, die in der Mitte von Spielfeld steht
 * @param stapel enthält die Karte, die im Stapel ist
 */

class Schwimmen(val spieler: ArrayDeque<Spieler>,  var mitte : ArrayDeque<SchwimmKarte>, var stapel : ArrayDeque<SchwimmKarte>)
{
    /**
     *  aktuellerSpieler ist den Index, der des aktuellen Spielers
     *  klopfIndexe ist den Index, der schon gezeigt, ob es geklopft wurde
     *  passIndex ist den Index, der schon zählt, wie viel mal gepasst wurde
     */
     var aktuellerSpieler:Int = 0
     var klopfIndexe :Int = 0
     var passIndex:Int = 0
}