package ar.edu.grupoesfera.enviospostales.modelo;

import ar.edu.grupoesfera.enviospostales.modelo.excepciones.ExcepcionAsignadorDeTransporte;

import java.util.List;

public class AsignadorDeTransporte {

    // Ojo con la ubicuidad del concepto, si Vehiculo y Transporte son
    // el mismo concepto para el modelo entonces conviene elegir
    // un solo nombre y usar ese en todos los lugares (clases, métodos, paquetes, etc)
    public Vehiculo asignarTransporte(List<Paquete> paquetes){
        Vehiculo vehiculo = null;

        if(paquetes.isEmpty()){
            throw new ExcepcionAsignadorDeTransporte("No se puede asignar vehiculo", null);
        }
        Double pesoTotal = pesoTotal(paquetes);
        if(pesoTotal <= 5){
            vehiculo =  new Bicicleta();
        } else if(pesoTotal <= 50){
            vehiculo = new Auto();
        } else if(pesoTotal <= 150){
            vehiculo = new Camioneta();
        } else {
            throw new ExcepcionAsignadorDeTransporte("No se puede asignar vehiculo", pesoTotal);
        }
        return vehiculo;
    }

    private Double pesoTotal(List<Paquete> paquetes) {
        return paquetes.stream().mapToDouble(i -> i.getPeso()).sum();
    }
}
