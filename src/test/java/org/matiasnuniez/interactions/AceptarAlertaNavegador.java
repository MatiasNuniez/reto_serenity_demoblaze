package org.matiasnuniez.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AceptarAlertaNavegador implements Interaction {

    private static final Duration TIMEOUT_ALERTA = Duration.ofSeconds(10);

    public static AceptarAlertaNavegador siEstaPresente() {
        return Tasks.instrumented(AceptarAlertaNavegador.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        var driver = BrowseTheWeb.as(actor).getDriver();
        new WebDriverWait(driver, TIMEOUT_ALERTA)
                .until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }
}
