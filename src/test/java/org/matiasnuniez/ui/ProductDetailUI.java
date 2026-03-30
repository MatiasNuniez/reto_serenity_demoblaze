package org.matiasnuniez.ui;

import net.serenitybdd.screenplay.targets.Target;


public class ProductDetailUI {

    public static final Target NOMBRE_PRODUCTO = Target.the("nombre del producto")
            .locatedBy("h2.name");

    public static final Target BOTON_AGREGAR_AL_CARRITO = Target.the("botón Add to cart")
            .locatedBy("//a[contains(@onclick,'addToCart') and contains(@class,'btn-success')]");
}
