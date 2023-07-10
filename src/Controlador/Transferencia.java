package Controlador;

public class Transferencia {

    private long id;
    private String destinatario;
    private String remitente;
    private Double cantidad;
    private String motivo;
    private String fecha;

    public Transferencia(long id,String destinatario, String remitente, Double cantidad, String motivo,String fecha) {
        this.id = id;
        this.destinatario = destinatario;
        this.remitente = remitente;
        this.cantidad = cantidad;
        this.motivo = motivo;
        this.fecha = fecha;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }


    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
