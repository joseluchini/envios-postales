package ar.com.grupoesfera.capacitacion.enviospostales;

import java.util.List;

public class Envio {

    private List<Paquete> paquetes;

    public Envio(List<Paquete> paquetes) {
        this.paquetes = paquetes;
    }

    public List<Paquete> getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(List<Paquete> paquetes) {
        this.paquetes = paquetes;
    }

    public int getCantidadDePaquetes() {
        return this.paquetes.size();
    }

    public Double calcularPeso() {
        return null;
    }
}
