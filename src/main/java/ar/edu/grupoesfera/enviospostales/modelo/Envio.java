package ar.edu.grupoesfera.enviospostales.modelo;

import java.math.BigDecimal;
import java.util.List;

public class Envio {

    private List<Paquete> paquetes;
    private BigDecimal costo;
    private String direccionDeEntrega;
    private Vehiculo transporte;

    public Envio(List<Paquete> paquetes, BigDecimal costo, String direccionDeEntrega, Vehiculo transporte){
        this.paquetes = paquetes;
        this.costo = costo;
        this.direccionDeEntrega = direccionDeEntrega;
        this.transporte = transporte;
    }

    public List<Paquete> getPaquetes() {
        return paquetes;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public String getDireccionDeEntrega() {
        return direccionDeEntrega;
    }

    public Vehiculo getTransporte() {
        return transporte;
    }
}
