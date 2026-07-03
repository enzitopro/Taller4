# Taller 4: Gestor de Colección Pokémon TCG 🃏

**I Semestre - 2026 | ITI - ICCI - ICI**

Este proyecto es un software con Interfaz Gráfica (GUI) en Java diseñado para que Sutrostian y POOsandon puedan administrar su creciente colección de cartas de Pokémon TCG. Permite realizar operaciones CRUD, visualizar imágenes dinámicamente y calcular puntuaciones de combate basándose en el meta actual.

## 🏛️ Arquitectura y Diseño

El sistema fue construido bajo una estricta separación de responsabilidades (Bajo Acoplamiento y Alta Cohesión), dividiendo el código en cuatro paquetes principales:
- `GUI`: Interfaces gráficas desarrolladas en Java Swing.
- `sistema`: Lógica de persistencia de datos y control de la colección.
- `dominio`: Clases abstractas y entidades concretas del problema.
- `logica`: Implementación de los motores de cálculo y creación de objetos.

### Patrones de Diseño Implementados
1. **Singleton (`SistemaImplementado`):** Garantiza una única instancia del gestor en toda la ejecución, evitando inconsistencias al leer/escribir en el archivo `.txt`.
2. **Factory (`CartaFactory`):** Centraliza la creación de los distintos tipos de cartas ocultando la lógica de parseo de strings y conversiones numéricas.
3. **Visitor (`VisitorPoder` y `CalculadorPoder`):** Aísla las diferentes fórmulas matemáticas requeridas para calcular el poder de combate según la clase de carta, sin contaminar el dominio.
4. **Strategy (`EstrategiaOrdenamiento`):** Permite intercambiar dinámicamente el algoritmo de ordenamiento de la colección (por Rareza, Nombre o Poder) desde la interfaz visual.

## ⚙️ Funcionalidades
- **Pestaña de Administración:** Creación, modificación y eliminación de cartas. Persistencia automática en el archivo `Sobres.txt` respetando el formato de origen.
- **Pestaña de Colección:** Visualización completa del inventario con opciones de ordenamiento.
- **Renderizado Dinámico:** Al seleccionar una carta, el sistema carga automáticamente su imagen `{nombreCarta}.png`. Si la carta no posee registro visual, se utiliza una imagen por defecto (`default_Pokemon.png`, `default_Item.png`, etc.).

## 🚀 Ejecución del Proyecto

### Prerrequisitos
- Java Development Kit (JDK).
- IDE compatible (Eclipse, VSCode, IntelliJ).

### Estructura del Directorio Raíz
Para el correcto funcionamiento, la raíz del proyecto debe contener:
1. El archivo `Sobres.txt`.
2. La carpeta `img/` con las imágenes `.png` correspondientes y los respaldos por defecto.
3. Los PDFs del Modelo de Dominio y Diagrama de Clases.

### Inicialización
1. Clonar el repositorio.
2. Ejecutar la clase `App.java` ubicada en el paquete `GUI`.

---
**Autores:** enzitopro & justamago