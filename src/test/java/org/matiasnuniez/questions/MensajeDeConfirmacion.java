package org.matiasnuniez.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import org.matiasnuniez.ui.ConfirmationModalUI;

public class MensajeDeConfirmacion implements Question<String> {

    @Override
    public String getSubject() {
        return "el mensaje de confirmación de compra";
    }

    public static MensajeDeConfirmacion mostrado() {
        return new MensajeDeConfirmacion();
    }

    @Override
    public String answeredBy(Actor actor) {
        actor.attemptsTo(
                WaitUntil.the(ConfirmationModalUI.TITULO_CONFIRMACION,
                        WebElementStateMatchers.isVisible())
        );
        return Text.of(ConfirmationModalUI.TITULO_CONFIRMACION)
                .answeredBy(actor);
    }
}
