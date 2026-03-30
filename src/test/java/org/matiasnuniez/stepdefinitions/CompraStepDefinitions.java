package org.matiasnuniez.stepdefinitions;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.GivenWhenThen;
import org.matiasnuniez.hooks.OpenBrowser;
import org.matiasnuniez.questions.MensajeDeConfirmacion;
import org.matiasnuniez.tasks.AgregarProductoAlCarrito;
import org.matiasnuniez.tasks.RealizarPedido;
import org.matiasnuniez.tasks.VerCarrito;
import org.matiasnuniez.util.TestData;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;

public class CompraStepDefinitions {

    private static final String ACTOR_NOMBRE = "Comprador";

    @Dado("que el usuario abre la página de inicio de Demoblaze")
    public void abrirPaginaDeInicio() {
        theActorCalled(ACTOR_NOMBRE).attemptsTo(
                OpenBrowser.withUrl(TestData.DEMOBLAZE_URL)
        );
    }

    @Cuando("el usuario agrega los productos del catálogo al carrito")
    public void agregarProductosAlCarrito() {
        for (String producto : TestData.PRODUCTOS) {
            theActorInTheSpotlight().attemptsTo(
                    OpenBrowser.withUrl(TestData.DEMOBLAZE_URL),
                    AgregarProductoAlCarrito.llamado(producto)
            );
        }
    }

    @Cuando("el usuario navega al carrito")
    public void navegarAlCarrito() {
        theActorInTheSpotlight().attemptsTo(
                VerCarrito.enLaPagina()
        );
    }

    @Cuando("el usuario realiza un pedido con los datos del comprador")
    public void realizarPedido() {
        theActorInTheSpotlight().attemptsTo(
                RealizarPedido.con(
                        TestData.COMPRADOR_NOMBRE,
                        TestData.COMPRADOR_PAIS,
                        TestData.COMPRADOR_CIUDAD,
                        TestData.COMPRADOR_TARJETA,
                        TestData.COMPRADOR_MES,
                        TestData.COMPRADOR_ANIO
                )
        );
    }

    @Entonces("el usuario ve el mensaje de compra exitosa")
    public void verificarMensajeDeConfirmacion() {
        theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(
                        "el mensaje de confirmación",
                        MensajeDeConfirmacion.mostrado(),
                        equalTo(TestData.MENSAJE_COMPRA_EXITOSA)
                )
        );
    }
}
