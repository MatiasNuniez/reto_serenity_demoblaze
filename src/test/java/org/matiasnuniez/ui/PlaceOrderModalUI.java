package org.matiasnuniez.ui;

import net.serenitybdd.screenplay.targets.Target;

public class PlaceOrderModalUI {

    public static final Target CAMPO_NOMBRE = Target.the("campo Name")
            .locatedBy("#name");

    public static final Target CAMPO_PAIS = Target.the("campo Country")
            .locatedBy("#country");

    public static final Target CAMPO_CIUDAD = Target.the("campo City")
            .locatedBy("#city");

    public static final Target CAMPO_TARJETA = Target.the("campo Credit Card")
            .locatedBy("#card");

    public static final Target CAMPO_MES = Target.the("campo Month")
            .locatedBy("#month");

    public static final Target CAMPO_ANIO = Target.the("campo Year")
            .locatedBy("#year");

    public static final Target BOTON_CONFIRMAR_COMPRA = Target.the("botón Purchase")
            .locatedBy("//button[normalize-space(text())='Purchase']");
}
