package ar.edu.grupoesfera.enviospostales.modelo;

import java.math.BigDecimal;

public class CalculadorDeCostosDeEnvio {

    public BigDecimal calcularCosto(Integer cantidadDePaquetes){
        BigDecimal costo = new BigDecimal("0.0");

        if(cantidadDePaquetes == 0){
            costo = new BigDecimal("0.0");
        } else if (cantidadDePaquetes > 0){
            if(cantidadDePaquetes < 5){
                costo = new BigDecimal("50.0");
            } else if (cantidadDePaquetes < 10){
                costo =  new BigDecimal("80.0");
            } else {
                BigDecimal precioBase = new BigDecimal("80.0");
                Integer cantidadBase = 9;
                costo =  precioBase.add(new BigDecimal((cantidadDePaquetes-cantidadBase) * 15)) ;
            }
        } else {
            throw new RuntimeException("La cantidad de paquetes no puede ser negativa");
        }
        return costo;
    }
}