# sockets
Trabajo de Redes 2 FIUNI 2015

Implementar una aplicación usando el lenguaje de programación Java, basada en la 

arquitectura Cliente-Servidor y que cumpla con los siguientes requisitos funcionales:

1. Utilizar las clases Socket y ServerSocket del paquete java.net.*, para la creación de los 

programas cliente y servidor. De este modo pondremos en práctica algunos de los conceptos 

relacionados a un protocolo orientado a la conexión. (En particular TCP)

2. El programa completo consta de tres componentes principales: El servidor Web, El Servidor 

Enlace (Proxy), El Cliente. La interacción entre las módulos se muestra en el diagrama de 

más abajo.




El servidor Web: Consiste en un Apache o cualquier otro WebServer, ejecutandose

en la máquina Local o Remota y corriendo una aplicación Web simple. Para acceder 

a cualquiera de la páginas de la aplicación web se debe estar autenticado (logueado), 

esto significa, que si el usuario intenta acceder a la página usuarios.json y no se ha 

autenticado aún, la aplicación debe redirigirte a login.html para poder ingresar tus 

credenciales. 

Esta aplicación pueden implementarlas en cualquier lenguaje que deseen, por 

ejmplo: php, asp.net, python, java, RoR, etc, y no necesita conectarse a una base de 

datos. Pueden haber una lista predefinida de usuarios que se pueden autenticar. 

La lista de páginas que deben estar presentes en la app web son: login.html, 

index.html, usuarios.json, libros.json, autores.json. 

▪ Login.html: pagina para autenticación de usuarios

▪ index.html: pagina simple que lo único que muestra son tres enlaces, uno por 

cada pagina *.json.
*.json: paginas que retornan un JSON representando la lista de usuarios, libros o

autores, dependiendo de que lo que se pida.

◦ El Servidor Enlace (proxy): Es el programa que usa un ServerSocket, es el 

encargado de recibir y procesar los pedidos (REQUEST) del programa Cliente. Una

vez procesado el REQUEST, retorna un RESPONSE al cliente que ha hecho el 

pedido anteriormente. Un REQUEST y un RESPONSE tienen un formato 

predefinido que será detallado más adelante. 

Una parte importante del trabajo de este servidor es que al recibir un pedido de algún

cliente, debe interpretarlo y como éste no posee la información que el cliente solicita,

realiza el pedido a la aplicación Web corriendo en el ServidorWeb. Una vez recibida 

la respuesta del ServidorWeb, la procesa, la formatea en el forma esperada por el 

cliente y se la envía como un RESPONSE. 

Al ejecutarse este ServidorEnlace se le debe proveer la información del 

usuario/password que usará para autenticarse cuando intente realizar pedidos a la app

corriendo en el WebServer. 

Este Servidor debe ser Multi-hilo porque pueden haber varios clientes realizando 

pedidos al mismo tiempo.

Un ejemplo de como ejecutar este programa se muestra a continuación:

java ProxyServer <Puerto> <Usuario> <Password>

◦ El Cliente: Es el programa que realiza un REQUEST al ProxyServer, una vez que 

recibe el RESPONSE del ProxyServer, lo imprime por pantalla (consola).

Como ejecutar este programa:

java Client <Server IP> <Server Port>

3. Cada una de la partes deben poder ejecutarse en una máquina distinta (por conveniencia, 

durante la implementación se pueden ejecutar en una sóla computadora).

4. Diagramas de los paquetes REQUEST y RESPONSE

1. REQUEST:

1. FORMAT: Indica en que formato se envia el pedido

2. DATA: Información extra que se puede enviar

3. RESULT: Formato en el que se desea el resultado a este pedido

4. RESOURCE: Indica que cosa se esta pidiendo. En este caso tenemos tres opciones: 

USUARIOS, LIBROS, AUTORES

2. RESPONSE:

1. FORMAT: Indica en que formato se envia la respuesta

2. DATA: Datos enviados