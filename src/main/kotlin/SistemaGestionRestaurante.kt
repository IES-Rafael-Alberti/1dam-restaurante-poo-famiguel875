/**
 * Esta clase gestiona las operaciones del restaurante, como realizar y cerrar pedidos,
 * y buscar información sobre los platos más pedidos.
 *
 * @property mesas Lista de todas las mesas en el restaurante.
 */
class SistemaGestionRestaurante(private val mesas: List<Mesa>) {

    /**
     * Asocia un nuevo pedido a una mesa.
     *
     * @param numeroMesa Número de la mesa donde se realiza el pedido.
     * @param pedido Pedido a asociar a la mesa.
     */
    fun realizarPedido(numeroMesa: Int, pedido: Pedido) {
        val mesa = mesas.find { it.numero == numeroMesa }
        mesa?.agregarPedido(pedido)
    }

    /**
     * Cambia el estado del último pedido de una mesa a "servido" o cambia el estado
     * de un pedido específico a "servido".
     *
     * @param numeroMesa Número de la mesa donde se encuentra el pedido.
     * @param numeroPedido Número del pedido a cerrar. Si es null, se cierra el último pedido.
     */
    fun cerrarPedido(numeroMesa: Int, numeroPedido: Int? = null) {
        val mesa = mesas.find { it.numero == numeroMesa }
        mesa?.pedidos?.lastOrNull { it.numero == numeroPedido }?.estado = "servido"
    }

    /**
     * Si todos los pedidos de una mesa están "servidos", libera la mesa.
     *
     * @param numeroMesa Número de la mesa a cerrar.
     */
    fun cerrarMesa(numeroMesa: Int) {
        val mesa = mesas.find { it.numero == numeroMesa }
        if (mesa?.pedidos?.all { it.estado == "servido" } == true) {
            mesa.liberarMesa()
        }
    }

    /**
     * Retorna una lista con el nombre de los platos pedidos.
     * Si no hay ningún plato pedido, retorna null.
     *
     * @return Lista de nombres de platos pedidos, o null si no hay ninguno.
     */
    fun buscarPlatos(): List<String>? {
        val platos = mesas.flatMap { it.pedidos }.flatMap { it.platos }.map { it.nombre }
        return platos.ifEmpty { null }
    }

    /**
     * Cuenta el número de veces que se ha pedido un plato específico.
     * Si el plato no ha sido pedido, retorna null.
     *
     * @param nombre Nombre del plato a contar.
     * @return Número de veces que se ha pedido el plato, o null si no ha sido pedido.
     */
    fun contarPlato(nombre: String): Int? {
        val count = mesas.flatMap { it.pedidos }
            .flatMap { it.platos }
            .count { it.nombre == nombre }
        return if (count > 0) count else null
    }

    /**
     * Busca y retorna el o los platos más pedidos.
     * Si no hay ningún plato pedido, retorna null.
     *
     * @return Lista con el nombre de los platos más pedidos, o null si no hay ninguno.
     */
    fun buscarPlatoMasPedido(): List<String>? {
        val platoCounts = mesas.flatMap { it.pedidos }
            .flatMap { it.platos }
            .groupingBy { it.nombre }
            .eachCount()

        val maxCount = platoCounts.maxByOrNull { it.value }?.value
        return maxCount?.let { max -> platoCounts.filter { it.value == max }.keys.toList() }
    }
}