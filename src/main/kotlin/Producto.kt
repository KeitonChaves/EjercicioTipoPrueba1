package org.example

// Clase base Producto, preparada para herencia y uso directo
open class Producto(
    val index: Int,
    val nombre: String,
    val precio: Int
) {
    override fun toString(): String {
        return "Producto(index=$index, nombre='$nombre', precio=$precio)"
    }
}
