# Servicio Web SOAP - Producto

Este proyecto implementa un servicio web SOAP para la gestión de productos. El servicio se publica en el puerto 8000 de la máquina local y proporciona un archivo WSDL para que se puedan consumir los métodos del servicio de manera sencilla.

## Requisitos

- **Java 17**: Asegúrate de tener Java 17 instalado en tu sistema.
- **IDE de Java** (opcional): Puedes usar cualquier entorno de desarrollo como IntelliJ IDEA, Eclipse o Visual Studio Code.

## Instrucciones de uso

1. **Clona este repositorio** (si aún no lo has hecho):

    ```bash
    git clone https://github.com/krloss86/java-web-api-marzo-2021.git
    cd tu-carpeta
    ```

2. **Compila el proyecto**:

    Si estás utilizando Maven, ejecuta el siguiente comando en la raíz del proyecto:

    ```bash
    mvn clean install
    ```

3. **Ejecuta el servicio web**:

    Una vez que el proyecto esté compilado, puedes iniciar el servicio con el siguiente comando:

    ```bash
    java --add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.base/java.lang.reflect=ALL-UNNAMED -jar target/ws-soap-server-1.0.0-SNAPSHOT.jar
    ```

    Esto iniciará el servicio web y verás en la consola los siguientes mensajes:

    ```plaintext
    Publicando Servicio Web de producto...
    Servicio Web de producto iniciado en http://localhost:8000/
    La direccion del wsdl es: http://localhost:8000/?wsdl
    ```

4. **Accede al WSDL**:

    Abre un navegador y visita la siguiente URL para acceder al WSDL del servicio web:

    ```plaintext
    http://localhost:8000/?wsdl
    ```

    Esto te permitirá integrar el servicio en un cliente SOAP o probarlo con herramientas como SoapUI.

## Métodos del servicio web

- **ProductoWsSoapServiceImpl**: Este servicio implementa métodos para la gestión de productos. Puedes acceder a estos métodos utilizando el WSDL proporcionado.

## Notas

- Asegúrate de que el puerto 8000 no esté siendo utilizado por otro servicio en tu máquina.
- Si deseas cambiar el puerto o la URL, ajusta el código en el método `Endpoint.publish` dentro de la clase principal.

## Contribuciones

Si deseas contribuir a este proyecto, siéntete libre de enviar un pull request o abrir un issue para sugerir mejoras.

## Licencia

Este proyecto está licenciado bajo la Licencia MIT. Puedes ver más detalles en el archivo `LICENSE`.
