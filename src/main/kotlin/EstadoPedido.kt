package org.example

sealed class EstadoPedido {
    object Pendiente : EstadoPedido()
    object EnPreparacion : EstadoPedido()
    object Listo : EstadoPedido()
    object Error : EstadoPedido()
}