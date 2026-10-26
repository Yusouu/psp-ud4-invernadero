public class ProgramadorRiego extends Thread {
    private int ciclos = 5;
    private double litrosTotales = 0;
    private int ciclosRealizados = 0;

    public ProgramadorRiego() {
        super("riego");
    }

    @Override
    public void run() {
        for (int i = 1; i <= ciclos; i++) {
            ciclosRealizados++;
            boolean abre = (i % 2 != 0);
            String estadoValvula = abre ? "ABIERTA (Riega 2.5L)" : "CERRADA";
            if (abre) {
                litrosTotales += 2.5;
            }

            String horaActual = java.time.LocalTime.now().toString();
            System.out.println("[" + horaActual + "] [" + getName() + "] Ciclo " + i + ": Válvula " + estadoValvula);

            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("El riego fue interrumpido.");
            }
        }
    }

    public String informe() {
        return String.format("Riego -> Ciclos realizados: %d, Litros totales gastados: %.1f L", ciclosRealizados, litrosTotales);
    }
}