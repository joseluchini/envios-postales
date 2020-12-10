package ar.com.grupoesfera.capacitacion.enviospostales;


public class DespachadorDeEnvios {

    private RepositorioDeEnvios repositorioDeEnvios;

    public Double calcularCosto(Envio envio) {

        Double porcentajeDeAumento = Double.valueOf(1);
        Integer cantidadDeEnviosDelDia = repositorioDeEnvios.getCantidadDeEnviosDelDia();
        if(cantidadDeEnviosDelDia > 10) {
            porcentajeDeAumento = Double.valueOf(1.1);
        }

        int cantidadDePaquetes = envio.getCantidadDePaquetes();
        if(cantidadDePaquetes < 5){
            return Double.valueOf(50) * porcentajeDeAumento;
        }
        else if(cantidadDePaquetes < 10){
            return Double.valueOf(80) * porcentajeDeAumento;
        }
        return Double.valueOf(cantidadDePaquetes * 15) * porcentajeDeAumento;
    }

    public void setRepositorioDeEnvios(RepositorioDeEnvios repositorioDeEnvios) {
        this.repositorioDeEnvios = repositorioDeEnvios;
    }
}
