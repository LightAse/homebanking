package Controlador;

public class TarjetaDeCredito extends Tarjeta{

    private double saldoActual;

    public TarjetaDeCredito(String numero, double disponible, int codigoDeSeguridad, int fechaDeVencimiento) {
        super(numero, disponible, codigoDeSeguridad, fechaDeVencimiento);
        saldoActual = 0;
    }

    public double getSaldoActual() {
        return saldoActual;
    }

    public boolean addSaldoActual(double agregar) {

        if( ( saldoActual <= getDisponible() ) && ( saldoActual+agregar <= getDisponible() ) ){

            saldoActual += agregar;
            return true;
        }
        return false;

    }
}
