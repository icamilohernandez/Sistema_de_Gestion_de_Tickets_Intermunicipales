package view;

import java.util.ArrayList;
import java.util.Scanner;
import model.Vehiculo;
import model.Conductor;
import model.Pasajero;
import model.Ticket;

public class Menu {
    
    private ArrayList<Vehiculo>vehiculos=new ArrayList<>();
    private ArrayList<Conductor>conductores=new ArrayList<>();
    private ArrayList<Pasajero>pasajeros=new ArrayList<>();
    private ArrayList<Ticket>tickets=new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public void mostrar() {
        int op;
        do {
            System.out.println("TransCesar S.A.S");
            System.out.println("1. Registrar Vehiculo");
            System.out.println("2. Listar vehiculos");
            System.out.println("3. Registrar Conductor");
            System.out.println("4. Registrar conductor");
            System.out.println("5. Registrar pasajero");
            System.out.println("6. Vender ticket");
            System.out.println("7. Listar tickets");
            System.out.println("8. Estadisticas");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            op = sc.nextInt();

            switch (op){
                case 1: break; // registrarVehiculo
                case 2: break; // listarVehiculos
                case 3: break; // registrarConductor
                case 4: break; // registrarPasajero
                case 5: break; // venderTicket
                case 6: break; // listarTickets
                case 7: break; // verEstadisticas
                case 0: System.out.println("Adios"); break;
                default: System.out.println("Opcion invalida");
            }

        } while (op != 0);
    }
}
