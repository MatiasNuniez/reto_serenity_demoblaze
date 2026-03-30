package org.matiasnuniez.ui;

import net.serenitybdd.screenplay.targets.Target;

public class HomePageUI {

    public static final Target ENLACE_CARRITO = Target.the("enlace al carrito")
            .locatedBy("#cartur");

    public static final Target ENLACE_HOME = Target.the("enlace Home")
            .locatedBy("a.navbar-brand");

    public static Target enlaceProducto(String nombreProducto) {
        return Target.the("producto '" + nombreProducto + "'")
                .locatedBy("//a[contains(@class,'hrefch') and normalize-space(text())='{0}']")
                .of(nombreProducto);
    }
}
