package Controlador;

public abstract class Tarjeta {

    private String numero;
    private double disponible;
    private int codigoDeSeguridad;
    private int fechaDeVencimiento;

    public Tarjeta(String numero, double disponible, int codigoDeSeguridad, int fechaDeVencimiento) {
        this.numero = numero;
        this.disponible = disponible;
        this.codigoDeSeguridad = codigoDeSeguridad;
        this.fechaDeVencimiento = fechaDeVencimiento;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getDisponible() {
        return disponible;
    }

    public void setDisponible(double disponible) {
        this.disponible = disponible;
    }

    public int getCodigoDeSeguridad() {
        return codigoDeSeguridad;
    }

    public void setCodigoDeSeguridad(int codigoDeSeguridad) {
        this.codigoDeSeguridad = codigoDeSeguridad;
    }

    public int getFechaDeVencimiento() {
        return fechaDeVencimiento;
    }

    public void setFechaDeVencimiento(int fechaDeVencimiento) {
        this.fechaDeVencimiento = fechaDeVencimiento;
    }
}
