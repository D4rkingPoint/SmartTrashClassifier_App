# SmartTrashClassifier
## Descripción
SmartTrashClassifier es una aplicación para Android que utiliza la cámara de tu dispositivo y el poder del machine learning para ayudarte a reciclar de manera más eficiente.

Apunta la cámara hacia un objeto y la aplicación, usando un modelo de detección de objetos YOLOv11 optimizado para móviles, lo identificará en tiempo real. Basado en la clasificación del objeto (ej. "glass", "carboard", "plastic"), la app te mostrará instantáneamente en qué contenedor de basura debes depositarlo, junto con una imagen del tacho correcto para que no queden dudas.

Esta herramienta busca facilitar y educar sobre la correcta separación de residuos, promoviendo prácticas de reciclaje más efectivas en el día a día.


## Base del proyecto
Este proyecto fue desarrollado tomando como base el excelente trabajo del siguiente repositorio, sobre el cual se realizaron modificaciones y mejoras para adaptarlo a este caso de uso específico:
- [Repositorio del proyecto](https://github.com/gy6543721/LiteRT/tree/main/LiteRT-Android)

Agradecimientos a Surendra Maran por proporcionar una base sólida y bien documentada.

## Cambio de Modelo de Detección

Para utilizar tu propio modelo de TensorFlow Lite (.tflite) en la aplicación, solo necesitas seguir dos sencillos pasos:

- Copiar el modelo: Arrastra y suelta tu archivo .tflite dentro de la carpeta app/src/main/assets/ en la estructura del proyecto de Android Studio.

- Actualizar la constante: Abre el archivo Constants.kt que se encuentra en SmartTrashClassifier_App\app\src\main\assets Dentro, modifica el valor de la constante MODEL_PATH para que coincida con el nombre exacto de tu nuevo archivo de modelo.

```
const val MODEL_PATH = "yolo11n_float32.tflite"
```

## Creación del Modelo TFLite

Para convertir tu propio modelo entrenado de YOLOv11 (best.pt) al formato .tflite que necesita la aplicación, puedes usar el notebook incluido en el repositorio del proyecto:

- [Repositorio del proyecto](https://github.com/D4rkingPoint/SmartTrashClassifier)

Dentro de este repositorio encontrarás el archivo convertir_tflite.ipynb, el cual puedes abrir y ejecutar directamente en Google Colab. Este cuaderno te guiará en el proceso de subir tu archivo best.pt y generar el modelo .tflite compatible con la app.