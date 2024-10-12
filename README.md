# WikiBfs
*Wikiracing haha goes brrrr*  
Un proyecto personal para aprender sobre algoritmos de búsqueda, hilos, optimizaciones en Kotlin y git
Versión de Gradle: 8.8
Versión de Kotlin: 2.0.0
Versión de Java JRE: 22

# Descripción
Esta aplicación permite buscar el camino mínimo entre dos entradas de Wikipedia (actualmente solo disponible en castellano) y si están conectadas entre sí.   
La búsqueda se realiza mediante el algoritmo de Búsqueda en Anchura (BFS) (sin repetición).

# Requisitos del Sistema
Para ejecutar esta aplicación, asegúrate de cumplir con los siguientes requisitos de sistema:  
- Java: Debes tener instalado el Java JRE 22.
- CPU:  Se recomienda una CPU potente para obtener los resultados en menor tiempo. No obstante, también puedes utilizar una CPU menos potente, aunque los tiempos de procesamiento serán mayores.

# Uso
1. Descargue el código en formato comprimido y descomprímalo en algún lugar de su ordenador. 
2. Navegue al directorio que se acaba de descomprimir y abra una terminal
3. Ejecute el siguiente comando: 
``` bash
java -jar app/build/libs/app.jar
```
4. En el menú de la aplicación se le requerirá introducir el enlace al articulo inicial de Wikipedia, (desde el que quiere iniciar la búsqueda) y posteriormente, el articulo objetivo.  
Asegúrese que los enlaces tienen formato `https://es.wikipedia.org/wiki/[Nombre_del_articulo]` (los anexos también están permitidos)

# Lista ToDo
- [ ] Optimizar al máximo el programa
- [ ] Optimizaciones con multithreading
- [ ] Aceptar el resto de idiomas de la wikipedia
- [ ] Publicar en archivo docker
