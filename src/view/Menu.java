package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.Vehiculo;
import model.Conductor;
import model.Pasajero;
import model.Ticket;
import service.VehiculoService;
import service.PersonaService;
import service.TicketService;

public class Menu {
    
    private ArrayList<Vehiculo>vehiculos=new ArrayList<>();
    private ArrayList<Conductor>conductores=new ArrayList<>();
    private ArrayList<Pasajero>pasajeros=new ArrayList<>();
    private ArrayList<Ticket>tickets=new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    private VehiculoService vehiculoService = new VehiculoService();
    private PersonaService  personaService  = new PersonaService();
    private TicketService   ticketService   = new TicketService();

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
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            op = sc.nextInt();

            switch (op){
                case 1: registrarVehiculo(); break;
                case 2: listarVehiculos(); break;
                case 3: registrarConductor(); break;
                case 4: registrarPasajero(); break;
                case 5: venderTicket(); break;
                case 6: listarTickets(); break;
                case 7: verEstadisticas(); break;
                case 0: System.out.println("Adios"); break;
                default: System.out.println("Opcion invalida");
            }

        } while (op != 0);
    }

    private void registrarVehiculo() {
        sc.nextLine();
        System.out.println("Registrar Vehiculo");
        System.out.print("Placa: ");
        String placa = sc.nextLine();
        System.out.print("ruta: ");
        String ruta = sc.nextLine();
        System.out.print("Modelo: ");
        String modelo = sc.nextLine();
        System.out.println("Tipo: 1.Buseta  2.MicroBus  3.Bus");
        int tipo = sc.nextInt();
        System.out.println(vehiculoService.registrar(vehiculos, placa, ruta, modelo));

    }
    private void listarVehiculos() {
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
        System.out.print("Numero de licencia: ");
        String licencia = sc.nextLine();
        System.out.println("Categoria: 1.B1  2.B2  3.C1  4.C2");
        int categoria = sc.nextInt();
        System.out.println(personaService.registrarConductor(conductores, cedula, nombre, licencia, categoria));
    }
    
    private void registrarPasajero() {
        sc.nextLine();
        System.out.print("Cedula: ");
        String cedula = sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Tipo: 1.Regular  2.Estudiante  3.Adulto Mayor");
        int tipo = sc.nextInt();
        System.out.println(personaService.registrarPasajero(pasajeros, cedula, nombre, tipo));
    }

}
