package ar.edu.grupoesfera.enviospostales.modelo;

import ar.edu.grupoesfera.enviospostales.modelo.excepciones.ExcepcionAsignadorDeTransporte;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class AsignadorDeTransporteTest {

    private AsignadorDeTransporte asignador = new AsignadorDeTransporte();
    private List<Paquete> paquetes = new LinkedList<>();
    private Paquete paquete = mock(Paquete.class);
    private Vehiculo vehiculoAsignado;

    @Before
    public void setUp(){
        dadoQueLaListaDePaquetesContieneCincoPaquetes();
    }

    @Test(expected = ExcepcionAsignadorDeTransporte.class)
    public void siLaListaDePaquetesEstaVaciaDeberiaDarError(){
        dadoQueLaListaDePaquetesEstaVacia();
        cuandoSeAsignaElTransporteParaElListadoDePaquetesActual();
    }

    @Test
    public void paraEnviosDeMenosDe5KilosSeDeberiaAsignarBicicleta(){
        dadoQueCadaPaquetePesa(0.8);
        cuandoSeAsignaElTransporteParaElListadoDePaquetesActual();
        entoncesElVehiculoAsignadoEsDeTipo(Bicicleta.class);
    }

    @Test
    public void paraEnviosDe5KilosSeDeberiaAsignarBicicleta(){
        dadoQueCadaPaquetePesa(1.0);
        cuandoSeAsignaElTransporteParaElListadoDePaquetesActual();
        entoncesElVehiculoAsignadoEsDeTipo(Bicicleta.class);
    }

    @Test
    public void paraEnviosDeMenosDe50KilosSeDeberiaAsignarAuto(){
        dadoQueCadaPaquetePesa(9.0);
        cuandoSeAsignaElTransporteParaElListadoDePaquetesActual();
        entoncesElVehiculoAsignadoEsDeTipo(Auto.class);
    }

    @Test
    public void paraEnviosDe50KilosSeDeberiaAsignarAuto(){
        dadoQueCadaPaquetePesa(10.0);
        cuandoSeAsignaElTransporteParaElListadoDePaquetesActual();
        entoncesElVehiculoAsignadoEsDeTipo(Auto.class);
    }

    @Test
    public void paraEnviosDeMenosDe150KilosSeDeberiaAsignarCamioneta(){
        dadoQueCadaPaquetePesa(29.0);
        cuandoSeAsignaElTransporteParaElListadoDePaquetesActual();
        entoncesElVehiculoAsignadoEsDeTipo(Camioneta.class);
    }

    @Test
    public void paraEnviosDe150KilosSeDeberiaAsignarCamioneta(){
        dadoQueCadaPaquetePesa(30.0);
        cuandoSeAsignaElTransporteParaElListadoDePaquetesActual();
        entoncesElVehiculoAsignadoEsDeTipo(Camioneta.class);
    }

    @Test(expected = ExcepcionAsignadorDeTransporte.class)
    public void paraEnviosDeMasDe150KilosNoSeDeberiaAsignarVehiculo(){
        dadoQueCadaPaquetePesa(35.0);
        cuandoSeAsignaElTransporteParaElListadoDePaquetesActual();
    }

    private void dadoQueCadaPaquetePesa(double peso) {
        doReturn(peso).when(paquete).getPeso();
    }

    private void cuandoSeAsignaElTransporteParaElListadoDePaquetesActual() {
        vehiculoAsignado = asignador.asignarTransporte(paquetes);

    }

    private void entoncesElVehiculoAsignadoEsDeTipo(Class vehiculo) {
        assertThat(vehiculoAsignado).isInstanceOf(vehiculo);
    }

    private void dadoQueLaListaDePaquetesContieneCincoPaquetes() {
        paquetes.add(paquete);
        paquetes.add(paquete);
        paquetes.add(paquete);
        paquetes.add(paquete);
        paquetes.add(paquete);
    }

    private void dadoQueLaListaDePaquetesEstaVacia() {
        paquetes = Mockito.mock(List.class);
        doReturn(true).when(paquetes).isEmpty();
    }

}
