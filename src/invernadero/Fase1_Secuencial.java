package invernadero;

public class Fase1_Secuencial {
    public static void main(String[] args) {
        System.out.println("--- INICIO FASE 1: SECUENCIAL ---");
        long inicio = System.currentTimeMillis();

        Temperatura temp = new Temperatura();
        Humedad hum = new Humedad();
        Luz luz = new Luz();
        ProgramadorRiego riego = new ProgramadorRiego();

        // Llamada explícita a run() uno tras otro (versión secuencial / lenta)
        temp.run();
        hum.run();
        luz.run();
        riego.run();

        long fin = System.currentTimeMillis();

        System.out.println("\n--- INFORMES FINALES (SECUENCIAL) ---");
        System.out.println(temp.informe());
        System.out.println(hum.informe());
        System.out.println(luz.informe());
        System.out.println(riego.informe());
        System.out.println("Tiempo total secuencial: (Suma aproximada de tiempos) " + (fin - inicio) + " ms");
    }
}