package Controlador;

import java.util.ArrayList;
import java.util.List;

public abstract class Usuario {

    private long id;
    private String dni;
    private String nombre;
    private String telefono;
    private String Domicilio;
    private String fechaDeNacimiento;
    private List<Cuenta> cuentasDeUsuario;
    private String usuario;
    private String password;
    private String email;

    public Usuario(long id, String dni, String nombre, String telefono, String domicilio, String fechaDeNacimiento, String usuario, String password, String email) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
        Domicilio = domicilio;
        this.fechaDeNacimiento = fechaDeNacimiento;
        cuentasDeUsuario = new ArrayList<>();
        this.usuario = usuario;
        this.password = password;
        this.email = email;
    }

    public Usuario() {
    }

    public long getId() {
        return id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDomicilio() {
        return Domicilio;
    }

    public void setDomicilio(String domicilio) {
        Domicilio = domicilio;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Cuenta getCuentasDeUsuario(int pos) {

        return cuentasDeUsuario.get(pos);

    }

    public void setCuentasDeUsuario(Cuenta cuentitaNew) {
        cuentasDeUsuario.add(cuentitaNew);
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double consultarSaldo(Cuenta cuentaActual){

        return cuentaActual.getSaldo();

    }

    public void setId(long id) {
        this.id = id;
    }
}
