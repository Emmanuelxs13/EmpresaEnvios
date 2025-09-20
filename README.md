# EmpresaEnvios

Esta aplicación te permite gestionar envíos por tres medios: Terrestre, Aéreo y Marítimo. Puedes agregar, eliminar y ver todos los envíos en una tabla.

## Autores

Trabajo realizado por **Juan Esteban Correa Cano** y **Emmanuel Berrio**.

## Estructura del proyecto

- **model**: Clases de negocio (Envio, Terrestre, Aereo, Maritimo)
- **controller**: Lógica para manejar los envíos (Logistica)
- **view**: Ventana principal con Swing (OperadorLogisticoUI)
- **App.java**: Main

## Cómo funciona

- Cada envío tiene cliente, código, peso y distancia.
- El costo se calcula automáticamente según el tipo:
  - Terrestre: $1500 por Km + $2000 por Kg
  - Aéreo: $5000 por Km + $4000 por Kg
  - Marítimo: $800 por Km + $1000 por Kg
- Puedes agregar un envío llenando el formulario y presionando "Guardar".
- Para eliminar, selecciona un envío en la tabla y presiona "Eliminar".
- La tabla se actualiza sola cada vez que agregas o eliminas.

## Cómo compilar y ejecutar

1. Compila todos los archivos:

   ```
   javac -d bin src/model/*.java src/controller/*.java src/view/*.java src/App.java
   ```

2. Ejecuta la aplicación:
   ```
   java -cp bin App
   ```

## Clases principales

- **Envio**: Base para todos los envíos.
- **Terrestre, Aereo, Maritimo**: Calculan el costo según el tipo.
- **Logistica**: Maneja la lista de envíos.
- **OperadorLogisticoUI**: Ventana para interactuar con el sistema.

---

Desarrollado para facilitar el aprendizaje y la evaluación.
