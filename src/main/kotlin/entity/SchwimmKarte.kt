package entity

data class SchwimmKarte(val farbe : CardSuit, val wert : CardValue) {

    fun karteWert() : Int
    {
        return try
        {
            wert.toString().toInt()
        }
        catch (e: NumberFormatException)
        {
            if (wert.toString() == "A") 11
            else 10
        }
    }
    override fun toString() : String = "${farbe.toString()}${wert.toString()}"

}