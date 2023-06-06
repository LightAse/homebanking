package Controlador;

public class UsuarioRegular extends Usuario{

    public UsuarioRegular(long id,String dni, String nombre, String telefono, String domicilio, String fechaDeNacimiento, Cuenta[] cuentasDeUsuario, String usuario, String password, String email) {
        super(id,dni, nombre, telefono, domicilio, fechaDeNacimiento, usuario, password, email);
    }

    public UsuarioRegular() {
    }
}
