package org.matiasnuniez.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import org.matiasnuniez.ui.CartUI;
import org.matiasnuniez.ui.PlaceOrderModalUI;

public class RealizarPedido implements Task {

    private final String nombre;
    private final String pais;
    private final String ciudad;
    private final String tarjeta;
    private final String mes;
    private final String anio;

    public RealizarPedido(String nombre, String pais, String ciudad,
                          String tarjeta, String mes, String anio) {
        this.nombre = nombre;
        this.pais = pais;
        this.ciudad = ciudad;
        this.tarjeta = tarjeta;
        this.mes = mes;
        this.anio = anio;
    }

    public static RealizarPedido con(String nombre, String pais, String ciudad,
                                     String tarjeta, String mes, String anio) {
        return Tasks.instrumented(RealizarPedido.class, nombre, pais, ciudad, tarjeta, mes, anio);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(CartUI.BOTON_PLACE_ORDER),

                WaitUntil.the(PlaceOrderModalUI.CAMPO_NOMBRE,
                        WebElementStateMatchers.isVisible()),

                Enter.theValue(nombre).into(PlaceOrderModalUI.CAMPO_NOMBRE),
                Enter.theValue(pais).into(PlaceOrderModalUI.CAMPO_PAIS),
                Enter.theValue(ciudad).into(PlaceOrderModalUI.CAMPO_CIUDAD),
                Enter.theValue(tarjeta).into(PlaceOrderModalUI.CAMPO_TARJETA),
                Enter.theValue(mes).into(PlaceOrderModalUI.CAMPO_MES),
                Enter.theValue(anio).into(PlaceOrderModalUI.CAMPO_ANIO),

                Click.on(PlaceOrderModalUI.BOTON_CONFIRMAR_COMPRA)
        );
    }
}
