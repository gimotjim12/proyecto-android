package mx.edu.unpa.miandroid.model

data class Usuario(
    var id: Int? = null,
    var nombre: String,
    var apellido_paterno: String,
    var apellido_materno: String,
    var email: String,
    var telefono: String?= null,
    var password: String,
    var activo: Boolean= false,
    var fecha_registro: String? = null
)
