package Controlador;

import java.util.ArrayList;
import java.util.List;

public abstract class Cuenta{

    private String cbu;
    private String alias;
    private List<String> historial;
    private double saldo;
    private List<Tarjeta> tarjetaUsuario;

    public Cuenta(String cbu, String alias, double saldo) {
        this.cbu = cbu;
        this.alias = alias;
        historial = new ArrayList<>();
        this.saldo = saldo;
        tarjetaUsuario = new ArrayList<>();
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getHistorial(int pos) {
        return (String) historial.get(pos);
    }

    public void setHistorial(String historialNew) {
        historial.add(historialNew);
    }

    public double getSaldo() {
        double saldito = 0;
        for (int i = 0; i < tarjetaUsuario.size(); i++) {
            if(tipoTarjeta(tarjetaUsuario.get(i))){
                TarjetaDeCredito tarjetita = (TarjetaDeCredito) tarjetaUsuario.get(i);
                saldito += tarjetita.getSaldoActual();

            }
        }

        return saldo - saldito;
    }

    public Tarjeta getTarjetaUsuario(int pos) {
        return tarjetaUsuario.get(pos);
    }

    public void setTarjetaUsuario(Tarjeta tarjetaUsuarioNew) {
        tarjetaUsuario.add(tarjetaUsuarioNew);
    }

    public boolean tipoTarjeta(Tarjeta tar){
        return tar instanceof TarjetaDeCredito;
    }




}
