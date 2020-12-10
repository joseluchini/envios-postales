package ar.com.grupoesfera.capacitacion.enviospostales;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class AsignadorDeVehiculosTest {

    private Envio envio;
    private AsignadorDeVehiculos asignador = new AsignadorDeVehiculos();
    private Vehiculo vehiculo;

    @Test
    public void debeAsignarBicicletaCuandoElEnvioEsDe4Kg() throws EsMuyPesadoException {
        dadoUnEnvioDe(Double.valueOf(4));

        cuandoSeAsignaUnVehiculo();

        entoncesVerificoQueELVehiculoEs(Vehiculo.BICICLETA);
    }

    @Test
    public void debeAsignarDroneCuandoElEnvioEsDe1PaqueteYPesaMenosDeMedioKilo() throws EsMuyPesadoException {
        dadoUnEnvioDe(Double.valueOf(0.4));

        dadoQueElEnvioTieneLaCantidadDePaquete(1);

        cuandoSeAsignaUnVehiculo();

        entoncesVerificoQueELVehiculoEs(Vehiculo.DRONE);
    }

    private void dadoQueElEnvioTieneLaCantidadDePaquete(int i) {
        doReturn(1).when(envio).getCantidadDePaquetes();
    }

    @Test
    public void debeAsignarAutoCuandoElEnvioEsDe48Kg() throws EsMuyPesadoException {
        dadoUnEnvioDe(Double.valueOf(48));

        cuandoSeAsignaUnVehiculo();

        entoncesVerificoQueELVehiculoEs(Vehiculo.AUTO);
    }

    @Test
    public void debeAsignarCamionetaCuandoElEnvioEsDe130Kg() throws EsMuyPesadoException {
        dadoUnEnvioDe(Double.valueOf(130));

        cuandoSeAsignaUnVehiculo();

        entoncesVerificoQueELVehiculoEs(Vehiculo.CAMIONETA);
    }

    @Test(expected = EsMuyPesadoException.class)
    public void ocurreUnErrorCuandoElEnvioEsDe160Kg() throws EsMuyPesadoException {
        dadoUnEnvioDe(Double.valueOf(160));

        cuandoSeAsignaUnVehiculo();
    }

    private void dadoUnEnvioDe(Double peso) {
        envio = mock(Envio.class);
        doReturn(peso).when(envio).calcularPeso();
    }

    private void cuandoSeAsignaUnVehiculo() throws EsMuyPesadoException {
        vehiculo = asignador.asignarVehiculo(envio);
    }

    private void entoncesVerificoQueELVehiculoEs(Vehiculo vehiculoEsperado) {
        assertThat(vehiculo).isEqualTo(vehiculoEsperado);
    }
}
