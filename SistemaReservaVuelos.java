import java.util.Scanner;

public class SistemaReservaVuelos {

    // Variables globales
    static final int NUM_ASIENTOS = 20; // Número total de asientos por ruta
    static final double PRECIO_PASAJE = 100.0; // Precio base del pasaje
    static final int TIEMPO_RESERVA = 48; // Horas límite para cambios/cancelaciones
    static final int PESO_EQUIPAJE_MAX = 20; // Peso máximo permitido (en kg)
    static final int CANT_EQUIPAJE_MAX = 2; // Cantidad máxima de maletas

    static int asientosRuta1 = NUM_ASIENTOS; // Contador de asientos disponibles para Ruta 1
    static int asientosRuta2 = NUM_ASIENTOS; // Contador de asientos disponibles para Ruta 2

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Sistema de Reserva de Vuelos ---");
            System.out.println("1. Reservar vuelo");
            System.out.println("2. Cancelar reserva");
            System.out.println("3. Mostrar asientos disponibles");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    reservarVuelo(sc);
                    break;
                case 2:
                    cancelarReserva(sc);
                    break;
                case 3:
                    mostrarAsientosDisponibles();
                    break;
                case 4:
                    System.out.println("Gracias por usar el sistema.");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 4);

        sc.close();
    }

    // Función para mostrar asientos disponibles
    public static void mostrarAsientosDisponibles() {
        System.out.println("\n--- Asientos Disponibles ---");
        System.out.println("Ruta 1 (Quito-Guayaquil): " + asientosRuta1);
        System.out.println("Ruta 2 (MANTA-GALAPAGOS): " + asientosRuta2);
    }

    // Función para reservar vuelo
    public static void reservarVuelo(Scanner sc) {
        sc.nextLine(); // Limpiar buffer
        System.out.println("\n--- Selección de Ruta ---");
        System.out.println("1. Quito-Guayaquil");
        System.out.println("2. MANTA-GALAPAGOS");
        System.out.print("Seleccione la ruta (1 o 2): ");
        int rutaSeleccionada = sc.nextInt();

        if (rutaSeleccionada == 1 && asientosRuta1 > 0) {
            procesarReserva(sc, "Quito-Guayaquil", 1);
        } else if (rutaSeleccionada == 2 && asientosRuta2 > 0) {
            procesarReserva(sc, "MANTA-GALAPAGOS", 2);
        } else {
            System.out.println("\nLo sentimos, no hay asientos disponibles en la ruta seleccionada.");
        }
    }

    // Función para procesar la reserva
    public static void procesarReserva(Scanner sc, String ruta, int numeroRuta) {
        sc.nextLine(); // Limpiar buffer
        System.out.println("\n--- Reserva de Vuelo ---");
        System.out.print("Ingrese su nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Ingrese cantidad de equipaje (máximo " + CANT_EQUIPAJE_MAX + "): ");
        int cantidadEquipaje = sc.nextInt();
        System.out.print("Ingrese peso total del equipaje (máximo " + PESO_EQUIPAJE_MAX + " kg): ");
        double pesoEquipaje = sc.nextDouble();

        if (cantidadEquipaje <= CANT_EQUIPAJE_MAX && pesoEquipaje <= PESO_EQUIPAJE_MAX) {
            if (numeroRuta == 1) {
                asientosRuta1--;
            } else if (numeroRuta == 2) {
                asientosRuta2--;
            }
            System.out.println("\nReserva realizada con éxito.");
            System.out.println("Detalles:");
            System.out.println("Nombre: " + nombre);
            System.out.println("Ruta: " + ruta);
            System.out.println("Asiento asignado: " + (NUM_ASIENTOS - (numeroRuta == 1 ? asientosRuta1 : asientosRuta2)));
        } else {
            System.out.println("\nError: Equipaje excede las restricciones permitidas.");
        }
    }

    // Función para cancelar reserva
    public static void cancelarReserva(Scanner sc) {
        sc.nextLine(); // Limpiar buffer
        System.out.println("\n--- Cancelación de Reserva ---");
        System.out.println("1. Quito-Guayaquil");
        System.out.println("2. MANTA-GALAPAGOS");
        System.out.print("Seleccione la ruta (1 o 2): ");
        int rutaSeleccionada = sc.nextInt();

        System.out.print("Ingrese las horas restantes para el vuelo: ");
        int horasRestantes = sc.nextInt();

        if (horasRestantes >= TIEMPO_RESERVA) {
            if (rutaSeleccionada == 1) {
                asientosRuta1++;
            } else if (rutaSeleccionada == 2) {
                asientosRuta2++;
            }
            System.out.println("Reserva cancelada con éxito. No se aplican multas.");
        } else {
            double multa = PRECIO_PASAJE * 0.20;
            System.out.println("Cancelación realizada fuera del tiempo límite.");
            System.out.println("Se aplicará una multa del 20%: $" + multa);
            if (rutaSeleccionada == 1) {
                asientosRuta1++;
            } else if (rutaSeleccionada == 2) {
                asientosRuta2++;
            }
        }
    }
}
