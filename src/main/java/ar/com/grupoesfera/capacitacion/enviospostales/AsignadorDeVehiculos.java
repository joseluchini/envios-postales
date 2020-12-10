package ar.com.grupoesfera.capacitacion.enviospostales;

public class AsignadorDeVehiculos {
    public Vehiculo asignarVehiculo(Envio envio) throws EsMuyPesadoException {
        Double peso = envio.calcularPeso();
        if(peso <= 0.5 && envio.getCantidadDePaquetes() == 1){
            return Vehiculo.DRONE;
        }
        else if(peso < 5) {
            return Vehiculo.BICICLETA;
        } else if (peso < 50) {
            return Vehiculo.AUTO;
        } else if (peso < 150) {
            return Vehiculo.CAMIONETA;
        }
        throw new EsMuyPesadoException();
    }
}
