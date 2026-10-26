package invernadero;

public class Fase4_Vigilante {
    public static void main(String[] args) {
        Temperatura temp = new Temperatura();
        Humedad hum = new Humedad();
        Luz luz = new Luz();
        ProgramadorRiego riego = new ProgramadorRiego();

        Thread hiloTemp = new Thread(temp, "sensor-Temperatura");
        Thread hiloHum = new Thread(hum, "sensor-Humedad");
        Thread hiloLuz = new Thread(luz, "sensor-Luz");

        // Creamos el hilo vigilante
        Thread vigilante = new Thread(() -> {
            while (true) {
                System.out.println("\n[VIGILANTE] Estados actuales de los hilos:");
                System.out.println(" - " + hiloTemp.getName() + ": " + hiloTemp.getState());
                System.out.println(" - " + hiloHum.getName() + ": " + hiloHum.getState());
                System.out.println(" - " + hiloLuz.getName() + ": " + hiloLuz.getState());
                System.out.println(" - " + riego.getName() + ": " + riego.getState());
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }, "hilo-vigilante");


        vigilante.setDaemon(true);

        System.out.println("--- ESTADO ANTES DE ARRANCAR (NEW) ---");
        System.out.println(hiloTemp.getName() + ": " + hiloTemp.getState());


        vigilante.start();
        hiloTemp.start();
        hiloHum.start();
        hiloLuz.start();
        riego.start();


        try {
            hiloTemp.join();
            hiloHum.join();
            hiloLuz.join();
            riego.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n--- PROGRAMA FINALIZADO CORRECTAMENTE ---");
    }
}