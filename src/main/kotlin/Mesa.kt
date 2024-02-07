class Mesa(val numero: Int, var capacidad: Int) {
    private var estado: EstadoMesa = EstadoMesa.LIBRE
    val pedidos = mutableListOf<Pedido>()

    init {
        require(capacidad in 1..6) { "La capacidad de la mesa debe estar entre 1 y 6" }
    }

    /**
     * Cambia el estado de la mesa a "ocupada", pero solo si la mesa está en estado "libre".
     */
    fun ocuparMesa() {
        if (estado == EstadoMesa.LIBRE || estado == EstadoMesa.RESERVADA) {
            estado = EstadoMesa.OCUPADA
        }
    }

    /**
     * Cambia el estado de la mesa a "ocupada", pero solo si la mesa está en estado "reservada".
     */
    fun ocuparReserva() {
        if (estado == EstadoMesa.RESERVADA) {
            estado = EstadoMesa.OCUPADA
        }
    }

    /**
     * Cambia el estado de la mesa a "libre".
     */
    fun liberarMesa() {
        estado = EstadoMesa.LIBRE
    }

    /**
     * Agrega un pedido a los pedidos de la mesa si la mesa está "ocupada".
     *
     * @param pedido Pedido a agregar a la mesa.
     */
    fun agregarPedido(pedido: Pedido) {
        if (estado == EstadoMesa.OCUPADA) {
            pedidos.add(pedido)
        }
    }
}