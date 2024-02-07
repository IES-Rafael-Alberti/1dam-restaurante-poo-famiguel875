class Plato(nombre: String, precio: Double, tiempoPreparacion: Int, private val ingredientes: MutableList<String>) {

    /** Usamos setters para asignar características a las variables de la clase plato */

    var nombre: String = nombre
        set(value) {
            require(value.isNotEmpty()) { "El nombre del plato no puede ser vacío" }
            field = value
        }

    var precio: Double = precio
        set(value) {
            require(value > 0) { "El precio del plato debe ser mayor que 0" }
            field = value
        }

    var tiempoPreparacion: Int = tiempoPreparacion
        set(value) {
            require(value > 1) { "El tiempo de preparación debe ser mayor que 1" }
            field = value
        }

    init {
        require(this.ingredientes.all { it.isNotEmpty() }) { "Los ingredientes no pueden ser vacíos" }
    }

    /** Creamos una función para que el ingrediente no pueda estar vacío */
    fun agregarIngrediente(ingrediente: String) {
        require(ingrediente.isNotEmpty()) { "El ingrediente no puede ser vacío" }
        this.ingredientes.add(ingrediente)
    }

    override fun toString(): String {
        return "$nombre ($tiempoPreparacion min.) -> $precio€ (${ingredientes.joinToString()})"
    }
}