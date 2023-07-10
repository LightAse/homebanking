package Controlador;

public class TarjetaDebito {

    private long id;
    private String numero;
    private long cbuCuentaOwner;
    private long codigoDeSeguridad;
    private String fechaDeVencimiento;
    private double saldo;
    private double disponible;


    public TarjetaDebito(long id, String numero, long codigoDeSeguridad, String fechaDeVencimiento, long cbuCuentaOwner, Double disponible) {
        this.id = id;
        this.numero = numero;
        this.codigoDeSeguridad = codigoDeSeguridad;
        this.fechaDeVencimiento = fechaDeVencimiento;
        this.saldo = 0;
        this.cbuCuentaOwner = cbuCuentaOwner;
        this.disponible = disponible;

    }

    public TarjetaDebito() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getDisponible() {
        return disponible;
    }

    public void setDisponible(double disponible) {
        this.disponible = disponible;
    }

    public long getCbuCuentaOwner() {
        return cbuCuentaOwner;
    }

    public void setCbuCuentaOwner(long cbuCuentaOwner) {
        this.cbuCuentaOwner = cbuCuentaOwner;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public long getCodigoDeSeguridad() {
        return codigoDeSeguridad;
    }

    public void setCodigoDeSeguridad(long codigoDeSeguridad) {
        this.codigoDeSeguridad = codigoDeSeguridad;
    }

    public String getFechaDeVencimiento() {
        return fechaDeVencimiento;
    }

    public void setFechaDeVencimiento(String fechaDeVencimiento) {
        this.fechaDeVencimiento = fechaDeVencimiento;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void addSaldo(double agregar) {
        if( ( getSaldo() <= getDisponible() ) && ( getSaldo()+agregar <= getDisponible() ) ){

            setSaldo(getSaldo() + agregar);

        }

    }


}
