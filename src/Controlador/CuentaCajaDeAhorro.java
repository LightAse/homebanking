package Controlador;

public class CuentaCajaDeAhorro extends Cuenta{

    private double interes;
    private String moneda;

    public CuentaCajaDeAhorro(String cbu, String alias, double saldo, double interes, String moneda) {
        super(cbu, alias, saldo);
        this.interes = interes;
        this.moneda = moneda;
    }

    public double getInteres() {
        return interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }



}
