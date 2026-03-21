package service;

import dao.ReservaDAO;
import dao.ReservaDAOImpl;
import model.Vehiculo;

public class ReservaService {

    private ReservaDAO reservaDAO;
    private VehiculoService vehiculoService;
    private PersonaService personaService;

    private static int contador = 1;

    public ReservaService(PersonaService personaService, VehiculoService vehiculoService) {
        this.reservaDAO     = new ReservaDAOImpl();
        this.personaService = personaService;
        this.vehiculoService = vehiculoService;
    }


    private boolean validarCapacidad(String placaVehiculo) {
        return vehiculoService.verificarDisponibilidad(placaVehiculo);
    }
}