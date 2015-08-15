Instrucciones para ejecutar el servidor:

Desde maven: En la linea de comandos ejecutar mvn tomcat:run
El endpoint quedará en http://localhost:8080/crawler/crawl

Para ejecutar en otro tomcat, tan solo habrá que hacer "mvn clean package" y copiar el war generado al tomcat deseado.

Consideraciones:
- He utilizado la base de datos MongoDB para probar, ya que la he usado pocas veces y me parecía interesante usarlo ahora
- He intentado generalizar las Task ya que todas siguen el mismo patrón
- Todo está acoplado a la clase CrawlQueue, idealmente esto sería un sistema de mensajería (ActiveMQ o Rabbit)
- He intentado que todo sea lo más optimo posible, sin embargo tenemos un cuello de botella enorme al obtener las páginas, probablemente haya alguna forma de no tener que descargar todo el html pero no he visto forma 
- He hecho que la api devuelva lo más rápido posible el 200 OK, sin embargo, eso no garantiza que todo haya ido bien ya que todo el trabajo queda en segundo plano. 
- He utilizado JavaConfig para cargar el ApplicationContext en vez del estándar XML ya que me parece más sencillo
- El uso de "com.fasterxml.jackson.core" sirve para poder usar correctamente la anotacion @RequestBody (En versiones anteriores de spring estaba integrada, por eso he supuesto que no sería un problema utilizarla). Si fuera imprescindible no utilizarla habría cambiado por HttpServletRequest las peticiones.
- He intentado serparar al máximo las tareas:
	- Para añadir o modificar las reglas de "isMarfeelizable" sólo hay que modifiar el fichero RulesConfig y añadir las necesarias
	- Para modificar la librería de Jsoup sólo hay que modificar DocumentRetrieverTask
	- Para cambiar la persistencia únicamente hay que cambiar DatabaseConfig y CrawlMongoStorage
	- Para cambiar el comportamiento hay que modificar los Processors y sus respectivas tasks
	
- No he dedicado mucho tiempo a validar la entrada, si las URL son incorrectas no habrá error y se escribirá un error en la base de datos como el siguiente:
ProcessingCrawlResult{uri=google.es, marfeelizable=false, e=java.lang.IllegalArgumentException: Malformed URL: google.es}
