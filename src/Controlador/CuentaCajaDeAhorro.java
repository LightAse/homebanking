package Controlador;

public class CuentaCajaDeAhorro extends CuentaCorriente {

    private double interes;

    public CuentaCajaDeAhorro(long cbu, String alias, double saldo, long user, double interes, String moneda) {
        super(cbu, alias, saldo, user, moneda);
        this.interes = interes;
    }

    public CuentaCajaDeAhorro() {
    }

    public double getInteres() {
        return interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }



}
