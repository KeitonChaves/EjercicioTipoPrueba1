package org.example


import kotlinx.coroutines.*
import kotlin.random.Random

fun main() = runBlocking {
    val gestor = GestorPedidos()
    try {
        val catalogo = gestor.inicializarCatalogo()

        println("----- BIENVENIDO A FOODEXPRESS -----")
        println("Seleccione productos del siguiente catálogo (números separados por coma):")
        println("CATÁLOGO DISPONIBLE:")
        catalogo.forEach { producto ->
            println("${producto.index}. ${producto.nombre} - \$${producto.precio}")
        }

        print("Ingrese número(s) de productos: ")
        val seleccionProducto = readLine()

        val indicesSeleccionados = seleccionProducto
            ?.split(",")
            ?.mapNotNull { it.trim().toIntOrNull() }
            ?: listOf()

        val productosSeleccionados = catalogo.filter { it.index in indicesSeleccionados }

        if (productosSeleccionados.isEmpty()) {
            println("No seleccionó productos válidos.")
            return@runBlocking
        }

        print("Ingrese tipo de cliente (regular, vip, premium): ")
        val tipoClienteInput = readLine()?.lowercase() ?: ""
        val tipoCliente = gestor.obtenerTipoCliente(tipoClienteInput)

        if (tipoCliente == null) {
            println("Tipo de cliente inválido.")
            return@runBlocking
        }

        val subtotal = productosSeleccionados.sumOf { it.precio }
        val total = gestor.calcularTotal(subtotal, tipoCliente)

        println("\n=== RESUMEN DEL PEDIDO ===")
        productosSeleccionados.forEach {
            println("- ${it.nombre}: \$${it.precio}")
        }
        println("Subtotal: \$${subtotal}")
        println("Descuento ${tipoCliente.nombre.capitalize()} (${(tipoCliente.descuento * 100).toInt()}%): -\$${(subtotal * tipoCliente.descuento).toInt()}")
        println("IVA (19%): \$${((subtotal - (subtotal * tipoCliente.descuento)) * 0.19).toInt()}")
        println("TOTAL: \$${total}")

        println("\nProcesando pedido...")
        simularProcesamientoPedidoConErrores()

    } catch (ex: Exception) {
        println("Error inesperado: ${ex.message}. Por favor, intente nuevamente.")
    }
}

// Simulación asíncrona con estados y posible error
suspend fun simularProcesamientoPedidoConErrores() {
    var estadoActual: EstadoPedido = EstadoPedido.Pendiente
    println("Estado: Pendiente")
    delay(1000)

    estadoActual = EstadoPedido.EnPreparacion
    println("Estado: En Preparación")
    delay(3000)

    val errorSimulado = Random.nextInt(100) < 20
    if (errorSimulado) {
        estadoActual = EstadoPedido.Error
        println("Estado: Error durante la preparación.")
    } else {
        estadoActual = EstadoPedido.Listo
        println("Estado: Listo para entrega")
    }
}
