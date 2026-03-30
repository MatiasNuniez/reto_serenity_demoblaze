# Conclusiones y Hallazgos — Demoblaze E2E

## Sitio bajo prueba

**URL:** https://www.demoblaze.com/
**Tipo:** Tienda online de productos electrónicos (demo de e-commerce)
**Flujo automatizado:** Selección de productos → Carrito → Formulario de compra → Confirmación

---

## Hallazgos sobre la aplicación

### 1. Navegación al detalle de producto

- El home carga los productos de forma **asíncrona** mediante llamadas a una API REST interna. Si se intenta hacer click en un producto inmediatamente después de abrir la URL, el elemento puede no estar disponible aún.
- **Impacto en la automatización:** Fue necesario agregar `WaitUntil.the(enlaceProducto, isVisible())` antes de cada click para evitar `NoSuchElementException`.

### 2. Alert nativo de JavaScript al agregar al carrito

- Al hacer click en "Add to cart", Demoblaze lanza un `alert()` nativo del navegador (no un modal HTML). Selenium no puede interactuar con este tipo de alerta usando las acciones normales de Screenplay.
- **Impacto en la automatización:** Fue necesario crear una `Interaction` específica (`AceptarAlertaNavegador`) que accede directamente al WebDriver y usa `driver.switchTo().alert().accept()` con un `WebDriverWait`.
- El alert aparece con un pequeño delay después del click, por lo que el `WebDriverWait` con `ExpectedConditions.alertIsPresent()` es imprescindible.

### 3. Persistencia del carrito

- El carrito en Demoblaze **persiste en `localStorage`** del navegador. Esto significa que si se ejecutan varias pruebas sin reiniciar el browser, los productos de un escenario anterior pueden aparecer en el carrito del siguiente.
- **Impacto en la automatización:** El `serenity.conf` tiene configurado `restart.browser.for.each = feature`, lo que garantiza un browser limpio entre ejecuciones de features.

### 4. Modal "Place Order" (formulario de compra)

- El modal es un componente **Bootstrap** que tiene una animación de apertura. Si se intenta interactuar con los campos antes de que la animación termine, los `Enter.theValue()` pueden fallar silenciosamente.
- **Impacto en la automatización:** Se agregó `WaitUntil.the(CAMPO_NOMBRE, isVisible())` como condición de entrada al formulario, garantizando que el modal esté completamente visible antes de llenar datos.
- Los campos del formulario tienen `id` estables (`#name`, `#country`, `#city`, `#card`, `#month`, `#year`), lo que los hace altamente confiables como selectores.

### 5. Confirmación de compra — SweetAlert

- Tras confirmar la compra, Demoblaze muestra un modal de tipo **SweetAlert**. Este modal no es un `alert()` nativo ni un modal Bootstrap, sino un `<div>` con clase `sweet-alert`.
- **Impacto en la automatización:** El localizador XPath `//div[contains(@class,'sweet-alert')]//h2` fue necesario para leer el mensaje de confirmación, ya que SweetAlert no usa IDs estables.
- El mensaje devuelto es `"Thank you for your purchase!"` seguido de detalles del pedido (ID, cantidad, card). La `Question` solo lee el `<h2>`, que contiene únicamente el mensaje de confirmación sin los detalles variables.

### 6. Ausencia de validaciones en el formulario

- El formulario de "Place Order" **no valida** los campos: acepta tarjetas inexistentes, nombres vacíos y fechas inválidas. La compra siempre se confirma.
- **Conclusión de calidad:** Este es un hallazgo funcional relevante si esto fuera un sistema real. En el marco del ejercicio de automatización, simplifica la prueba ya que no hay flujos de error que manejar.

### 7. Comportamiento del carrito al navegar

- Después de agregar un producto, volver al home y agregar otro implica que el `ENLACE_CARRITO` en el header sigue siendo accesible sin necesidad de recargar la página completa.
- **Impacto en la automatización:** `VerCarrito` simplemente hace click en `#cartur` y espera que `BOTON_PLACE_ORDER` sea visible, lo que funciona de forma confiable.

---

## Conclusiones del ejercicio

### Sobre el flujo E2E automatizado

- La principal complejidad técnica del sitio no está en su lógica de negocio, sino en sus **componentes asíncronos**: carga dinámica del catálogo, alert nativo al agregar productos y animaciones de modales.

### Sobre la estrategia de esperas

- **No se usó ningún `Thread.sleep()`**. Todas las esperas son condicionales (`WaitUntil.the(..., isVisible())`), lo que hace las pruebas más rápidas y resilientes a variaciones de rendimiento del entorno.