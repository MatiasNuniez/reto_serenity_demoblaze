package org.matiasnuniez.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import org.matiasnuniez.interactions.AceptarAlertaNavegador;
import org.matiasnuniez.ui.HomePageUI;
import org.matiasnuniez.ui.ProductDetailUI;

public class AgregarProductoAlCarrito implements Task {

    private final String nombreProducto;

    public AgregarProductoAlCarrito(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public static AgregarProductoAlCarrito llamado(String nombreProducto) {
        return Tasks.instrumented(AgregarProductoAlCarrito.class, nombreProducto);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(HomePageUI.enlaceProducto(nombreProducto),
                        WebElementStateMatchers.isVisible()),

                Click.on(HomePageUI.enlaceProducto(nombreProducto)),

                WaitUntil.the(ProductDetailUI.BOTON_AGREGAR_AL_CARRITO,
                        WebElementStateMatchers.isVisible()),

                Click.on(ProductDetailUI.BOTON_AGREGAR_AL_CARRITO),

                AceptarAlertaNavegador.siEstaPresente()
        );
    }
}
