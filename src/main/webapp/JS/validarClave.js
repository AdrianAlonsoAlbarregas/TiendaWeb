/*
 * Esta funcion sirve para asegurarse de que el usuario introduce correctamente la clave al registrarse
 * Se reciben los dos campos en los que se introduce la contraseña y si no coinciden, se para el registro
 */
function validarClave() {
    var clave = document.getElementById("clave").value;
    var confClave = document.getElementById("confClave").value;
    if (clave != confClave) {
        alert("Las contraseñas no coinciden");
        return false;
    } else {
        return true;
    }

}
;


