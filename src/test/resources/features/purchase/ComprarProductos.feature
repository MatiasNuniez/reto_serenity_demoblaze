# language: es
# Flujo E2E de compra en Demoblaze usando Serenity BDD Screenplay + Cucumber
Característica: Compra de productos en Demoblaze
  Como usuario de la tienda Demoblaze
  Quiero agregar productos al carrito y completar una compra
  Para adquirir productos de forma exitosa

  @compra @e2e
  Escenario: Compra exitosa de dos productos con formulario Place Order
    Dado que el usuario abre la página de inicio de Demoblaze
    Cuando el usuario agrega los productos del catálogo al carrito
    Y el usuario navega al carrito
    Cuando el usuario realiza un pedido con los datos del comprador
    Entonces el usuario ve el mensaje de compra exitosa
