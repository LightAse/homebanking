package Controlador;

public abstract class Cuenta{

    private long cbu;
    private String alias;

    private double saldo;

    private long userOwner;
    private String moneda;

    public Cuenta(long  cbu, String alias, double saldo, long user, String moneda) {
        this.cbu = cbu;
        this.alias = alias;
        this.saldo = saldo;
        this.userOwner = user;
        this.moneda = moneda;
    }



    public Cuenta() {
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public long getUserOwner() {
        return userOwner;
    }

    public void setUserOwner(long userOwner) {
        this.userOwner = userOwner;
    }

    public long getCbu() {
        return cbu;
    }

    public void setCbu(long cbu) {
        this.cbu = cbu;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }





}
