package org.example

class ProductoComida(
    index: Int,
    nombre: String,
    precio: Int,
    val premium: Boolean
) : Producto(index, nombre, precio) {
    // Aquí puedes agregar métodos o sobrescribir toString si quieres
}
