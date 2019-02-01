# almundoCallCenter
almundoCallCenter

> ## **Resumen**
> Existe un call center donde hay 3 tipos de empleados: operador, supervisor
y director. El proceso de la atención de una llamada telefónica en primera
instancia debe ser atendida por un operador, si no hay ninguno libre debe
ser atendida por un supervisor, y de no haber tampoco supervisores libres
debe ser atendida por un director.

>> ## **Solución**
> - Proyecto Spring boot Maven.
> - Archivo application.properties
> - Número de llamadas simludas propiedad : almundo.incomingCall
> - Se ha definido un clase Dispatcher Encargada por medio de dispatcherCall
gestionar las llamadas el sitema está configurado para atender 10 llamadas en paralelo.
> - La clase Dispatcher implementa la interfaz Runnable, lo cual nos va a permitir gestionar las 
llamadas de manera concurrente.
>Se ha definido una clase que gestionará la liberación de las llamadas en soporte (Llamada que está siendo atendida),
y controlará que las llamadas sean terminas en un intervalo de tiempo de entre 5 y 10 segundos.
> - Se definieron varias colas para que el sistema gestione los empleados disponibles y ocupados (Operador, Supervisor y Director)
como tambien colas para las llamadas en espera.
> - Se definieron  Observer(Observadores) para notificar los cambios que suceden en el sistema ejemplo: llamada en espera, empleados libres y/o ocupados, etc.

>> ## **Extras**
> - En el caso de no haber ningún empleado libre el sistema gestionar toda llamda por medio de una cola de espera y
por medio de proceso ManagerCallsService monitoriará cada 0.5 segundos
las llamadas que están siendo atentidas y en el momento que alguna llamada esté dentro del rango de los 5 a 10 segundos, está será terminada
y dejará disponible al empleado de la llamada terminada.
> - Por medio de la cola de llamadas de espera, la lista de llamadas que están siendo atendidas y el proceso que gestiona la liberación de llamadas se tendrá la seguridad de que
cuando se llega al limite de llamadas concurrentes todas las llamadas serán atendidas.

