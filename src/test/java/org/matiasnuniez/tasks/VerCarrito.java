package org.matiasnuniez.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import org.matiasnuniez.ui.CartUI;
import org.matiasnuniez.ui.HomePageUI;

public class VerCarrito implements Task {

    public static VerCarrito enLaPagina() {
        return Tasks.instrumented(VerCarrito.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(HomePageUI.ENLACE_CARRITO),

                WaitUntil.the(CartUI.BOTON_PLACE_ORDER,
                        WebElementStateMatchers.isVisible())
        );
    }
}
