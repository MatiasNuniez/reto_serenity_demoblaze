# Demoblaze E2E — Serenity BDD + Screenplay + Cucumber

Automatización E2E del flujo de compra en [Demoblaze](https://www.demoblaze.com/) utilizando **Serenity BDD 5.3.3**, **Screenplay Pattern**, **Cucumber 7.34** y **JUnit 5**.

---

## Requisitos previos

| Herramienta    | Versión mínima | Verificar con             |
|----------------|----------------|---------------------------|
| Java (JDK)     | 17             | `java -version`           |
| Google Chrome   | última estable | `chrome --version`        |
| Gradle         | 9.0 (incluido) | `./gradlew --version`     |

> No es necesario instalar Gradle. El proyecto incluye el **Gradle Wrapper** (`gradlew` / `gradlew.bat`).

---

## Ejecución paso a paso

### 1. Clonar el repositorio

```bash
git clone <url-del-repositorio>
cd api_screenplay
```

### 2. Verificar Java 17+

```bash
java -version
```

### 3. Ejecutar las pruebas

**Windows:**
```powershell
.\gradlew.bat clean test
```

**Linux / macOS:**
```bash
./gradlew clean test
```

Esto ejecuta:
1. `clean` — Limpia artefactos anteriores
2. `test` — Compila y ejecuta los escenarios Cucumber
3. `aggregate` + `reports` — Se ejecutan automáticamente vía `finalizedBy`, generando reportes Serenity

### 4. Ejecutar solo el tag @compra

```powershell
.\gradlew.bat clean test -Dcucumber.filter.tags="@compra"
```

### 5. Ejecutar en modo headless

Editar `src/test/resources/serenity.conf` y agregar `"--headless"` al array `args`:

```hocon
"goog:chromeOptions" {
  args = ["--headless", "remote-allow-origins=*", ...]
}
```

### 6. Ver los reportes

| Reporte | Ubicación |
|---|---|
| **Serenity HTML completo** | `target/site/serenity/index.html` |
| **Resumen Single Page** | `target/site/serenity/serenity-summary.html` |
| **JUnit XML** | `build/test-results/test/` |
| **HTML de Gradle** | `build/reports/tests/test/index.html` |

**Windows:**
```powershell
start target\site\serenity\index.html
```

**Linux / macOS:**
```bash
open target/site/serenity/index.html
```

---