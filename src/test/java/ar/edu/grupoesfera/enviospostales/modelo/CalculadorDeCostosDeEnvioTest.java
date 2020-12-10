package ar.edu.grupoesfera.enviospostales.modelo;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

public class CalculadorDeCostosDeEnvioTest {

    private CalculadorDeCostosDeEnvio calculador = new CalculadorDeCostosDeEnvio();
    private int cantidadPaquetes;
    private BigDecimal costo;

    @Test(expected=RuntimeException.class)
    public void cantidadDePaquetesNegativoDeberiaDarError(){
        dadoQueLaCantidadDePaquetesEs(-2);
        cuandoSeEjecutaCalcularCosto();
    }

    @Test
    public void elCostoDeNoEnviarPaquetesDeberiaSerCero(){
        dadoQueLaCantidadDePaquetesEs(0);
        cuandoSeEjecutaCalcularCosto();
        entoncesElCostoEs("0.0");
    }

    @Test
    public void elCostoDeMenosDe5PaquetesDeberiaSer50(){
        dadoQueLaCantidadDePaquetesEs(4);
        cuandoSeEjecutaCalcularCosto();
        entoncesElCostoEs("50.0");
    }

    @Test
    public void elCostoDe5PaquetesDeberiaSer80(){
        dadoQueLaCantidadDePaquetesEs(5);
        cuandoSeEjecutaCalcularCosto();
        entoncesElCostoEs("80.0");
    }

    @Test
    public void elCostoDeMenosDe10PaquetesDeberiaSer80(){
        dadoQueLaCantidadDePaquetesEs(9);
        cuandoSeEjecutaCalcularCosto();
        entoncesElCostoEs("80.0");
    }

    @Test
    public void elCostoDe10PaquetesDeberiaSer80Mas15PorPaqueteAdicional(){
        dadoQueLaCantidadDePaquetesEs(10);
        cuandoSeEjecutaCalcularCosto();
        entoncesElCostoEs("95.0");
    }

    @Test
    public void elCostoDeMas10PaquetesDeberiaSer80Mas15PorPaqueteAdicional(){
        dadoQueLaCantidadDePaquetesEs(16);
        cuandoSeEjecutaCalcularCosto();
        entoncesElCostoEs("185.0");
    }

    private void dadoQueLaCantidadDePaquetesEs(int cantidad) {
        cantidadPaquetes = cantidad;
    }


    private void cuandoSeEjecutaCalcularCosto() {
        this.costo = calculador.calcularCosto(cantidadPaquetes);
    }


    private void entoncesElCostoEs(String costo) {
        assertThat(this.costo).isEqualTo(new BigDecimal(costo));
    }
}
