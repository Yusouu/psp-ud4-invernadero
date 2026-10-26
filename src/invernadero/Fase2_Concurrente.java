package invernadero;

public class Fase2_Concurrente {
    public static void main(String[] args) {
        System.out.println("--- INICIO FASE 2: CONCURRENTE ---");
        long inicio = System.currentTimeMillis();

        Temperatura temp = new Temperatura();
        Humedad hum = new Humedad();
        Luz luz = new Luz();
        ProgramadorRiego riego = new ProgramadorRiego();


        Thread hiloTemp = new Thread(temp, "sensor-Temperatura");
        Thread hiloHum = new Thread(hum, "sensor-Humedad");
        Thread hiloLuz = new Thread(luz, "sensor-Luz");


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

        long fin = System.currentTimeMillis();

        System.out.println("\n--- INFORMES FINALES (CONCURRENTE) ---");
        System.out.println(temp.informe());
        System.out.println(hum.informe());
        System.out.println(luz.informe());
        System.out.println(riego.informe());
        System.out.println("Tiempo total concurrente: " + (fin - inicio) + " ms");
    }
}