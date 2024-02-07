class Pedido {
    companion object {
        private var contadorPedidos = 0

        /**
         * Obtiene el número de pedido incrementando el contador interno.
         */
        fun obtenerNumeroPedido(): Int {
            contadorPedidos++
            return contadorPedidos
        }
    }
    val numero: Int = obtenerNumeroPedido()
    val platos = mutableListOf<Plato>()
    var estado: String = "pendiente"
    /**
     * Añade un nuevo plato al pedido.
     *
     * @param plato Plato a agregar al pedido.
     */
    fun agregarPlato(plato: Plato) {
        platos.add(plato)
    }

    /**
     * Elimina un plato del pedido basándose en el nombre.
     *
     * @param nombrePlato Nombre del plato a eliminar del pedido.
     */
    fun eliminarPlato(nombrePlato: String) {
        platos.removeAll { it.nombre == nombrePlato }
    }

    /**
     * Calcula el precio total del pedido sumando los precios de cada plato.
     *
     * @return Precio total del pedido.
     */
    fun calcularPrecio(): Double {
        return platos.sumOf { it.precio }
    }

    /**
     * Calcula el tiempo total de preparación sumando el tiempo de preparación de cada plato.
     *
     * @return Tiempo total de preparación del pedido en minutos.
     */
    fun calcularTiempo(): Int {
        return platos.sumOf { it.tiempoPreparacion }
    }

    /**
     * Retorna la información del pedido incluyendo el número de mesa,
     * los platos del pedido y su estado.
     *
     * @return Información detallada del pedido.
     */
    override fun toString(): String {
        val platosInfo = platos.joinToString("\n") { it.toString() }
        return "Pedido $numero: $platosInfo Estado: $estado"
    }
}
