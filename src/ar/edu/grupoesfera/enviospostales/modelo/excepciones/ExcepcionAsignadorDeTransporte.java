package ar.edu.grupoesfera.enviospostales.modelo.excepciones;

public class ExcepcionAsignadorDeTransporte extends RuntimeException{

    private Double peso;

    public ExcepcionAsignadorDeTransporte(String mensaje, Double peso){
        super(mensaje + ", el peso actual es: " + ((peso!=null)?peso:"null"));
    }

    public Double getPeso() {
        return peso;
    }
}
