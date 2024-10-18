# WikiBfs
*Wikiracing haha goes brrrr*  
Un proyecto personal para aprender sobre algoritmos de búsqueda, hilos, optimizaciones en Kotlin y git
Versión de Gradle: 8.8
Versión de Kotlin: 2.0.0
Versión de Java JRE: 22

# Descripción
Esta aplicación permite buscar el camino mínimo entre dos entradas de Wikipedia (actualmente solo disponible en castellano) y si están conectadas entre sí.   
La búsqueda se realiza mediante el algoritmo de Búsqueda en Anchura (BFS) (sin repetición).
Además he implementado diversas variaciones del algoritmo de busqueda, como el usar cache, o hilos, o por el contrario, no usar nada de esto.
Los paquetes son: 
- `nocanohi` -> BFS por defecto, sin hilos ni cache
- `nohi` -> BFS usando solamente cache.
- `wikiracing` -> Algoritmo junto a mejoras de cache e hilos (corutinas)

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
## Rendimiento
(Con paquete de corutinas y cache)
- Busquedas de nivel 1 (misma pagina)-> Segundos
- Busquedas de nivel 2 (a una pagina de distancia) -> Minutos (~6-10)
- Busquedas de nivel 3 (a dos paginas de distancia) -> Horas (~1-2)
- Busquedas de nivel 4 ("") -> Horas a infinito  
# Resultados
Tras los resultados del anterior apartado, ahora mismo, el codigo mas optimizado del programa, permite realizar busquedas de nivel 3 en un tiempo razonable.  
Más allá de este nivel, el tiempo crece exponencialmente, al crecer el numero de paginas que se debe visitar.
Es por tanto, un claro objetivo el **minimizar el tiempo de busqueda lo mas posible**, optimizando el programa.
# Lista ToDo
- [ ] Optimizar al máximo el programa
- [ ] Optimizaciones con multithreading
- [ ] Aceptar el resto de idiomas de la wikipedia
- [ ] Publicar en archivo docker
