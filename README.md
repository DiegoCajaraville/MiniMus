# MiniMus
Adaptación del proyecto de la asignatura Programación II del Grado en Ingeniería de Tecnologías de Telecomunicación en la Universidad de Vigo.

## Ejecución

Se trata de un proyecto Maven y su ejecución se llevará a cabo con los comandos:
```sh
mvn clean package exec:java -Dexec.mainClass="com.teleco.minimus.App" -Dexec.args="{}"
```
El parámetro `-Dexec.args="{}"` indica el paso por parámetros del proyecto Java y se debe sustituir `{}` por los parámetros deseados para ejecutar un modo concreto del programa.