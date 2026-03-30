package org.matiasnuniez.ui;

import net.serenitybdd.screenplay.targets.Target;

public class CartUI {

    public static final Target FILAS_CARRITO = Target.the("filas del carrito")
            .locatedBy("//tbody/tr");

    public static final Target BOTON_PLACE_ORDER = Target.the("botón Place Order")
            .locatedBy("//button[normalize-space(text())='Place Order']");
}
