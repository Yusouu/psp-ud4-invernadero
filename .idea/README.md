# Práctica Invernadero - PSP

Práctica de programación concurrente en Java (JDK 21) para simular un invernadero con varios sensores y un sistema de riego.

## Cómo ejecutarlo
Abrimos el proyecto en IntelliJ IDEA y ejecutamos las clases principales de cada fase:
- `Fase1_Secuencial`
- `Fase2_Concurrente`
- `Fase3_Experimentos`
- `Fase4_Vigilante`

---
## Resultados y Comparativa
* **Fase 1 (Secuencial):** Todo lo ejecuta el hilo `main` uno detrás de otro llamando a `.run()`. Tarda la suma de todo porque no hay paralelismo.
* **Fase 2 (Concurrente):** Cada sensor y el riego van en su propio hilo con `.start()`. Tarda lo que el dispositivo más lento porque corren a la vez. El `join()` sirve para que el `main` espere a que acaben y no imprima el informe vacío.
---

## Experimentos (Fase 3)

* **Experpimento A:** Si imprimes el informe sin hacer `join()`, sale vacío porque el `main` va más rápido que los hilos.
* **Experimento B:** Si usamos `.run()` en vez de `.start()` en el riego, no se crea hilo nuevo corre en el `main` y el estado se queda en `TERMINATED`.
* **Experimento C:** Si intentamos hacer `.start()` a un hilo que ya ha terminado, Java salta con una excepción (`IllegalThreadStateException`) porque los hilos no se pueden reiniciar.

---

## Hilo Vigilante (Fase 4)
Un hilo extra que comprueba cada poco los estados de los hilos (`NEW`,`RUNNABLE`,`TIMED_WAITING`,`TERMINATED`).
Está configurado como (`setDaemon(true)`) para que no bloquee el cierre del programa cuando los demás hilos terminan.

---

## Thread vs Runnable

* **Runnable:** Lo usan los sensores. Te deja heredar de otras clases si lo necesitas y desacopla la tarea del hilo.
* **Thread:** Lo usa el riego.Hereda directamente, pero te limita la herencia múltiple en Java y no se puede reutilizar la instancia para lanzarlo otra vez.