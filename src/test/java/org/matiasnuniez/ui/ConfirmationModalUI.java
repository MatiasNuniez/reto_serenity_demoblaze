package org.matiasnuniez.ui;

import net.serenitybdd.screenplay.targets.Target;


public class ConfirmationModalUI {

    public static final Target TITULO_CONFIRMACION = Target.the("título de confirmación de compra")
            .locatedBy("//div[contains(@class,'sweet-alert')]//h2");

    public static final Target BOTON_OK = Target.the("botón OK de confirmación")
            .locatedBy("//div[contains(@class,'sweet-alert')]//button[contains(@class,'confirm')]");
}
