package org.example



class GestorPedidos {

    data class TipoCliente(val nombre: String, val descuento: Double)

    // Contenedor de tipos cliente definidos como constantes
    object TiposCliente {
        val Regular = TipoCliente("regular", 0.05)
        val Vip = TipoCliente("vip", 0.10)
        val Premium = TipoCliente("premium", 0.15)
    }

    // Inicializa el catálogo de productos del restaurante
    fun inicializarCatalogo(): List<Producto> {
        return listOf(
            ProductoComida(1, "Hamburguesa Clásica", 8990, false),
            ProductoComida(2, "Salmón Grillado", 15990, true),
            ProductoBebida(3, "Coca Cola", 1990, "mediano"),
            ProductoBebida(4, "Jugo Natural", 2990, "grande")
        )
    }

    // Convierte la entrada de texto en un tipo de cliente válido o null si no existe
    fun obtenerTipoCliente(texto: String): TipoCliente? {
        return when (texto.lowercase()) {
            "regular" -> TiposCliente.Regular
            "vip" -> TiposCliente.Vip
            "premium" -> TiposCliente.Premium
            else -> null
        }
    }

    // Devuelve el porcentaje de descuento basado en el tipo de cliente
    fun obtenerDescuento(tipo: TipoCliente): Double {
        return tipo.descuento
    }

    // Calcula el total a pagar dados subtotal y tipo de cliente (incluye descuento e IVA 19%)
    fun calcularTotal(subtotal: Int, tipo: TipoCliente): Int {
        val montoDescuento = subtotal * obtenerDescuento(tipo)
        val baseImponible = subtotal - montoDescuento
        val iva = baseImponible * 0.19
        val total = baseImponible + iva
        return total.toInt()
    }
}
