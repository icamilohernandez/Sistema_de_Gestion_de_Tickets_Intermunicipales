package view;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Scanner;

import model.Conductor;
import model.Pasajero;
import model.PasajeroAdultoMayor;
import model.PasajeroEstudiante;
import model.PasajeroRegular;
import model.Ticket;
import model.Vehiculo;
import service.PersonaService;
import service.TicketService;
import service.VehiculoService;

public class Menu {

    private Scanner sc = new Scanner(System.in);

    private VehiculoService vehiculoService = new VehiculoService();
    private PersonaService  personaService  = new PersonaService();
    private TicketService   ticketService   = new TicketService(personaService, vehiculoService);

    public void mostrar() {
        int op;
        do {
            System.out.println("TransCesar S.A.S");
            System.out.println("1. Registrar Vehiculo");
            System.out.println("2. Listar vehiculos");
            System.out.println("3. Registrar Conductor");
            System.out.println("4. Registrar pasajero");
            System.out.println("5. Vender ticket");
            System.out.println("6. Listar tickets");
            System.out.println("7. Estadisticas");
            System.out.println("8. Reportes");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            op = sc.nextInt();

            switch (op) {
                case 1: registrarVehiculo();  break;
                case 2: listarVehiculos();    break;
                case 3: registrarConductor(); break;
                case 4: registrarPasajero();  break;
                case 5: venderTicket();       break;
                case 6: listaTickets();       break;
                case 7: verEstadisticas();    break;
                case 8: menuReportes();       break;
                case 0: System.out.println("Adios, gracias por usar nuestro sistema"); break;
                default: System.out.println("Opcion no valida");
            }
        } while (op != 0);
    }

    private void registrarVehiculo() {
        sc.nextLine();
        System.out.println("Registrar Vehiculo");
        System.out.print("Placa: ");
        String placa = sc.nextLine();
        System.out.println("Rutas disponibles: R001=Bogota-Medellin | R002=Bogota-Cali | R003=Medellin-Cartagena");
        System.out.print("Codigo de ruta: ");
        String ruta = sc.nextLine();
        System.out.print("Modelo: ");
        String modelo = sc.nextLine();
        vehiculoService.registrar(placa, ruta, modelo);
    }

    private void listarVehiculos() {
        List<Vehiculo> vehiculos = vehiculoService.listarVehiculos();
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehiculos registrados");
            return;
        }
        System.out.println("Lista de Vehiculos:");
        for (Vehiculo v : vehiculos) {
            System.out.println(v);
        }
    }

    private void registrarConductor() {
        sc.nextLine();
        System.out.print("Cedula: ");
        String cedula = sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Sexo (M/F): ");
        String sexo = sc.nextLine();
        System.out.print("Telefono: ");
        String telefono = sc.nextLine();
        System.out.print("Edad: ");
        int edad = sc.nextInt();
        sc.nextLine();
        System.out.print("Numero de licencia: ");
        String licencia = sc.nextLine();
        System.out.println("Categoria: 1.B1  2.B2  3.C1  4.C2");
        int cat = sc.nextInt();
        sc.nextLine();
        String[] categorias = {"B1", "B2", "C1", "C2"};
        String categoria = (cat >= 1 && cat <= 4) ? categorias[cat - 1] : "B1";
        Conductor conductor = new Conductor(cedula, nombre, sexo, licencia, categoria, telefono, edad);
        personaService.registrarPersona(conductor);
        System.out.println("Conductor registrado exitosamente.");
    }

    private void registrarPasajero() {
        sc.nextLine();
        System.out.print("Cedula: ");
        String cedula = sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Sexo (M/F): ");
        String sexo = sc.nextLine();
        System.out.print("Telefono: ");
        String telefono = sc.nextLine();

        System.out.print("Año de nacimiento (YYYY): ");
        int anio = sc.nextInt();
        System.out.print("Mes de nacimiento (1-12): ");
        int mes = sc.nextInt();
        System.out.print("Dia de nacimiento: ");
        int dia = sc.nextInt();
        sc.nextLine();

        LocalDate fechaNacimiento = LocalDate.of(anio, mes, dia);
        int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();

        Pasajero pasajero;

        // El sistema determina automaticamente si es Adulto Mayor
        if (edad >= 60) {
            pasajero = new PasajeroAdultoMayor(nombre, cedula, edad, sexo, telefono, fechaNacimiento);
            System.out.println("Pasajero registrado automaticamente como Adulto Mayor (descuento 30%) - edad: " + edad);
        } else {
            System.out.println("Tipo de pasajero: 1. Regular  2. Estudiante");
            int tipo = sc.nextInt();
            sc.nextLine();
            if (tipo == 2) {
                pasajero = new PasajeroEstudiante(nombre, cedula, edad, sexo, telefono, fechaNacimiento);
                System.out.println("Pasajero registrado como Estudiante (descuento 15%)");
            } else {
                pasajero = new PasajeroRegular(nombre, cedula, edad, sexo, telefono, fechaNacimiento);
                System.out.println("Pasajero registrado como Regular (sin descuento)");
            }
        }

        personaService.registrarPersona(pasajero);
        System.out.println("Pasajero registrado exitosamente.");
    }

    private void venderTicket() {
        sc.nextLine();
        System.out.print("Cedula del pasajero: ");
        String cedula = sc.nextLine();
        System.out.print("Placa del vehiculo: ");
        String placa = sc.nextLine();
        System.out.print("Origen: ");
        String origen = sc.nextLine();
        System.out.print("Destino: ");
        String destino = sc.nextLine();
        System.out.println(ticketService.venderTicket(cedula, placa, origen, destino));
    }

    private void listaTickets() {
        List<Ticket> tickets = ticketService.listarTickets();
        if (tickets.isEmpty()) {
            System.out.println("No hay tickets vendidos");
            return;
        }
        System.out.println("Lista de Tickets:");
        for (Ticket t : tickets) {
            System.out.println(t);
        }
    }

    private void verEstadisticas() {
        ticketService.mostrarEstadisticas();
    }

    private void menuReportes() {
        int opcion;
        do {
            System.out.println("\n===== Reportes =====");
            System.out.println("1. Tickets por fecha");
            System.out.println("2. Tickets por tipo de vehiculo");
            System.out.println("3. Tickets por tipo de pasajero");
            System.out.println("4. Resumen del dia actual");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1: reporteFecha();        break;
                case 2: reporteTipoVehiculo(); break;
                case 3: reporteTipoPasajero(); break;
                case 4: resumenDiaActual();    break;
                case 0: System.out.println("Volviendo al menu principal..."); break;
                default: System.out.println("Opcion invalida");
            }
        } while (opcion != 0);
    }

    private void reporteFecha() {
        sc.nextLine();
        System.out.print("Ingrese la fecha (dd/MM/yyyy): ");
        String fecha = sc.nextLine();
        System.out.println("\n===== Tickets del " + fecha + " =====");
        ticketService.reporteFecha(fecha);
    }

    private void reporteTipoVehiculo() {
        System.out.println("Tipo de vehiculo: 1.Buseta  2.MicroBus  3.Bus");
        int tipo = sc.nextInt();
        System.out.println("\n===== Tickets por tipo de vehiculo =====");
        ticketService.reporteTipoVehiculo(tipo);
    }

    private void reporteTipoPasajero() {
        System.out.println("Tipo de pasajero: 1.Regular  2.Estudiante  3.Adulto Mayor");
        int tipo = sc.nextInt();
        System.out.println("\n===== Tickets por tipo de pasajero =====");
        ticketService.reporteTipoPasajero(tipo);
    }

    private void resumenDiaActual() {
        System.out.println("\n===== Resumen del dia actual =====");
        ticketService.resumenDiaActual();
    }
}