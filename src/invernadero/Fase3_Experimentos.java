package invernadero;

public class Fase3_Experimentos {
    public static void main(String[] args) {

        System.out.println("=== EXPERIMENTO A ===");
        Temperatura tempA = new Temperatura();
        Thread hiloA = new Thread(tempA, "sensor-A");
        hiloA.start();


        System.out.println("Informe inmediato (sin esperar): " + tempA.informe());

        try {
            hiloA.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Informe tras join (terminado): " + tempA.informe());


        System.out.println("\n=== EXPERIMENTO B ===");
        ProgramadorRiego riegoB = new ProgramadorRiego();
        long iniB = System.currentTimeMillis();

        riegoB.run();

        long finB = System.currentTimeMillis();
        System.out.println("Tiempo tardado con run(): " + (finB - iniB) + " ms");
        System.out.println("Estado del objeto Thread al terminar (.getState()): " + riegoB.getState());

        System.out.println("\n=== EXPERIMENTO C ===");
        ProgramadorRiego riegoC = new ProgramadorRiego();
        riegoC.start();

        try {
            riegoC.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("Intentando relanzar el mismo objeto Thread con start()...");
            riegoC.start();
        } catch (IllegalThreadStateException e) {
            System.out.println("¡Excepción capturada con éxito!: " + e);
            System.out.println("Significado: Un hilo que ha llegado al estado TERMINATED no se puede reiniciar.");
        }
    }
}