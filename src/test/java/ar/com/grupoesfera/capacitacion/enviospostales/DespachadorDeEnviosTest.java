package ar.com.grupoesfera.capacitacion.enviospostales;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class DespachadorDeEnviosTest {

    private Envio envio;
    private DespachadorDeEnvios despachador = new DespachadorDeEnvios();
    private Double costo;
    private RepositorioDeEnvios repositorioDeEnvios = mock(RepositorioDeEnvios.class);

    @Before
    public void init() {
        despachador.setRepositorioDeEnvios(repositorioDeEnvios);
    }

    @Test
    public void conMenosDe5PaquetesElCostoDebeSer50() {
        dadoQueTengo0Envios();
        dadoQueTengoUnEnvioConUnaCantidadDePaquetes(3);

        cuandoCalculoElCosto();

        entoncesVerificoQueElCostoEs(Double.valueOf(50));
    }

    @Test
    public void con7PaquetesElCostoDebeSer80() {
        dadoQueTengo0Envios();
        dadoQueTengoUnEnvioConUnaCantidadDePaquetes(7);

        cuandoCalculoElCosto();

        entoncesVerificoQueElCostoEs(Double.valueOf(80));
    }

    @Test
    public void con13PaquetesElCostoDebeSer195() {
        dadoQueTengo0Envios();
        dadoQueTengoUnEnvioConUnaCantidadDePaquetes(13);

        cuandoCalculoElCosto();

        entoncesVerificoQueElCostoEs(Double.valueOf(195));
    }

    @Test
    public void con11EnviosElCostoTieneUn10Porciento() {
        dadoQueTengo11Envios();
        dadoQueTengoUnEnvioConUnaCantidadDePaquetes(7);

        cuandoCalculoElCosto();

        entoncesVerificoQueElCostoEs(Double.valueOf(88));
    }

    private void dadoQueTengo11Envios() {
        doReturn(11).when(repositorioDeEnvios).getCantidadDeEnviosDelDia();
    }

    private void dadoQueTengo0Envios() {
        doReturn(0).when(repositorioDeEnvios).getCantidadDeEnviosDelDia();
    }

    private void dadoQueTengoUnEnvioConUnaCantidadDePaquetes(Integer cantidadDePaquetes) {
        envio = mock(Envio.class);
        doReturn(cantidadDePaquetes).when(envio).getCantidadDePaquetes();
    }

    private void cuandoCalculoElCosto() {
        costo = despachador.calcularCosto(envio);
    }

    private void entoncesVerificoQueElCostoEs(Double valor) {
        assertThat(costo).isEqualTo(valor);
    }
}
