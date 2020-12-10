package ar.edu.grupoesfera.enviospostales.modelo;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DespachadorTest {

    private DespachadorDeEnvios despachadorDeEnvios;
    private CalculadorDeCostosDeEnvio calculadorDeCostosDeEnvio;
    private AsignadorDeTransporte asignadorDeTransporte;
    private final String direccionDeEntrega = "Peru 11";
    private Envio envio;

    @Before
    public void setUp(){
        asignadorDeTransporte = mock(AsignadorDeTransporte.class);
        calculadorDeCostosDeEnvio = mock(CalculadorDeCostosDeEnvio.class);
        despachadorDeEnvios = new DespachadorDeEnvios(calculadorDeCostosDeEnvio, asignadorDeTransporte);
    }

    @Test
    public void siNoEsPosibleAsignarVehiculoNoSeDeberiaGenerarUnEnvio(){
        dadoQueNoSePuedenAsignarVehiculos();
        cuandoSeEjecutaElDespachoDePaquetes();
        entoncesElEnvioEsNulo();
    }

    @Test
    public void antesDelDecimoEnvioNoDeberiaHaberRecargo(){
        dadoQueElCalculadorDeCostosDeEnvioDevuelveUnCostoDe("50.00");
        dadoQueSeDespachanUnaCantidadDeEnviosIgualA(9);
        cuandoSeEjecutaElDespachoDePaquetes();
        entoncesElEnvioEsDistintoDeNull();
        entoncesElCostoDelEnvioEs("50.00");
    }

    @Test
    public void despuesDelDecimoEnvioDeberiaHaberRecargo(){
        dadoQueElCalculadorDeCostosDeEnvioDevuelveUnCostoDe("50.0");
        dadoQueSeDespachanUnaCantidadDeEnviosIgualA(10);
        cuandoSeEjecutaElDespachoDePaquetes();
        entoncesElEnvioEsDistintoDeNull();
        entoncesElCostoDelEnvioEs("55.00");
    }

    private void entoncesElEnvioEsNulo() {
        assertThat(this.envio).isNull();
    }

    private void entoncesElEnvioEsDistintoDeNull() {
        assertThat(this.envio).isNotNull();
    }

    private void cuandoSeEjecutaElDespachoDePaquetes() {
        this.envio = despachadorDeEnvios.despachar(mock(List.class), direccionDeEntrega);
    }

    private void dadoQueNoSePuedenAsignarVehiculos() {
        doThrow(new RuntimeException()).when(asignadorDeTransporte).asignarTransporte(any(List.class));
    }


    private void entoncesElCostoDelEnvioEs(String precio) {
        assertThat(envio.getCosto()).isEqualTo(new BigDecimal(precio));
    }

    private void dadoQueElCalculadorDeCostosDeEnvioDevuelveUnCostoDe(String costo) {
        when(calculadorDeCostosDeEnvio.calcularCosto(anyInt())).thenReturn(new BigDecimal(costo));
    }

    private void dadoQueSeDespachanNueveEnvios() {
        for(int i=0; i<9; i++){
            despachadorDeEnvios.despachar(mock(List.class), direccionDeEntrega);
        }
    }

    private void dadoQueSeDespachanUnaCantidadDeEnviosIgualA(int cantidadDespachos) {
        for(int i=1; i<=cantidadDespachos; i++){
            despachadorDeEnvios.despachar(mock(List.class), direccionDeEntrega);
        }
    }
}
