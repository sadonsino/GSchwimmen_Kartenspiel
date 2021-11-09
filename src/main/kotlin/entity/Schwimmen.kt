package entity

/**
 * Klasse, die einen Spielzustand von "Schwimmen" darstellt.
 * @param spieler enthält alle Spieler
 * @param mitte enthält die Karte, die in der Mitte von Spielfeld steht
 * @param stapel enthält die Karte, die im Stapel ist
 * @param aktuellerSpieler ist den Index, der des aktuellen Spielers
 * @param klopfIndexe ist den Index, der schon gezeigt, ob es geklopft wurde
 * @param passIndex ist den Index, der schon zählt, wie viel mal gepasst wurde
 */

class Schwimmen(val spieler: ArrayDeque<Spieler>,  var mitte : ArrayDeque<SchwimmKarte>, var stapel : ArrayDeque<SchwimmKarte>,
                var aktuellerSpieler:Int, var klopfIndexe :Int,var passIndex:Int)
{

}