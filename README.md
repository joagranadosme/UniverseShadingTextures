# Taller Interaction/Shaders

## Objetivos

* Implementar interactividad por medio de un dispositivo móvil.

* Desarrollar shaders de luz y textura para los objetos de la aplicación.

## Discusión

Desarrollamos una aplicación móvil basada en el sistema solar, con el sol y los cuatro planetas más cercanos; la cual permite 
interactuar con el sistema, implementando interactividad por medio de la pantalla táctil como también shaders de textura 
y luz.

### Interactividad

 Con el fin de proveer interactividad en la aplicación, utilizamos las herramientas que ofrecen los dispositivos móviles
 como acelerometro, giroscopio y la pantalla táctil. De esta manera la aplicacin permite realizar zoom a los elementos 
 del sistema y cambiar la orientación del movimiento del sistema.

### Shaders

  Implementamos dos tipos de shaders.
  
  * Light Shader: Para implementar el shader de light en la aplicación, usamos el sol como punto de luz para iluminar
  a los demas planetas utilizando luz difusa por vertice. 
  
      ![Light](http://visualcomputing.github.io/Shaders/fig/vertlight.png)
 
 * Texture Shader: Para implementar el shader de textura en la aplicación, a cada planeta y al sol le agregamos la textura
 tomada de internet, usamos textura simple pasando los datos a traves de los shaders, tomando como referencia el ejemplo 
 visto en clase.
 
      ![Texture](http://visualcomputing.github.io/Shaders/fig/chowmein.png)
 
### Resultados

Al realizar las implementaciones previstas, obtuvimos los resultados esperados tanto en interactividad como en Shaders y con la
ayuda de la librería del curso frames, para separar los diferentes objetos del sistema por medio de Frames. Las siguientes
pantallas muestran el resultado final de la aplicación:

![App1](https://github.com/joagranadosme/UniverseShadingTextures/blob/master/App1.jpg)

![App2](https://github.com/joagranadosme/UniverseShadingTextures/blob/master/App2.jpg)
    
### Trabajo futuro
 * Implementar light shaders por medio del phong model.
 * Utilizar filtros en la aplicación 
 * Agregar texturas más complejas para los planetas.
 
## Integrantes

Máximo 3.

Complete la tabla:

| Integrante | github nick |
|------------|-------------|
| Jairo Suarez | [JairoASuarez](https://github.com/JairoASuarez) |
| Jonathan Granados | [joagranadosme](https://github.com/joagranadosme) |

### References

1. [VisualComputing - Interaction](http://visualcomputing.github.io/Interaction).
1. [VisualComputing - Shaders](http://visualcomputing.github.io/Shaders).
2. [Processing Shaders Tutorial - Andres Colubri](https://processing.org/tutorials/pshader/).
3. [Processing for android](http://android.processing.org/)
