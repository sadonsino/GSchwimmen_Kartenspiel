package service
import view.Refreshable

/**
 * Abstrakte Serviceklasse, die mehrere Refreshables verarbeitet,
 * die über die [onAllRefreshables]-Methode über zu aktualisierende Änderungen benachrichtigt werden.
 */

abstract class AbstractRefreshingService {
    private val refreshables = mutableListOf<Refreshable>()

    /**
     * Fügt der Liste ein [Refreshable] hinzu, das aufgerufen wird,
     * wenn [onAllRefreshables] verwendet wird.
     */

    fun addRefreshable(newRefreshable : Refreshable)
    {
        refreshables += newRefreshable
      }

    /**
     * Führt die übergebene Methode auf allen [Refreshable]s aus,
     * die bei der Service-Klassen registriert sind, die diesen [AbstractRefreshingService] erweitert.
     */

      fun onAllRefreshables(method: Refreshable.() -> Unit) =
        refreshables.forEach { it.method() }

}