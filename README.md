# almundoCallCenter
almundoCallCenter

> ## **Resumen**
> Existe un call center donde hay 3 tipos de empleados: operador, supervisor
y director. El proceso de la atención de una llamada telefónica en primera
instancia debe ser atendida por un operador, si no hay ninguno libre debe
ser atendida por un supervisor, y de no haber tampoco supervisores libres
debe ser atendida por un director.

>> ## **Solución**
> - Proyecto maven.
> - Se ha definido un clase Dispatcher Encargada por medio de dispatcherCall
gestionar las llamadas el sitema está configurado para atender 10 llamadas en paralelo.
> - La clase Dispatcher implementa la interfaz Runnable, lo cual nos va a permitir gestionar las 
llamadas de manera concurrente.
>Se ha definido una clase que gestionará las llamadas en soporte (Llamada que está siendo atendida),
y controlará que las llamadas sean terminas en un intervalo de tiempo de entre 5 y 10 segundos.
> - Se definieron varias colas para que el sistema gestione los empleados disponibles y ocupados (Operador, Supervisor y Director)
como tambien colas para las llamadas en espera.
> - Se definieron  Observer(Observadores) para notificar los cambios que suceden en el sistema ejemplo: llamada en espera, empleados libres y/o ocupados, etc.



