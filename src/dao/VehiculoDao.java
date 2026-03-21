package dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import model.Buseta;
import model.Bus;
import model.MicroBus;
import model.Ruta;
import model.Vehiculo;

public class VehiculoDao {

    private static final String ARCHIVO_BUSETA   = "buseta.txt";
    private static final String ARCHIVO_MICROBUS = "microbus.txt";
    private static final String ARCHIVO_BUS      = "bus.txt";

    // Rutas predefinidas
    private static final List<Ruta> rutasDisponibles = new ArrayList<>();
    static {
        rutasDisponibles.add(new Ruta("R001", "Bogota",   "Medellin",  420, 480));
        rutasDisponibles.add(new Ruta("R002", "Bogota",   "Cali",      510, 540));
        rutasDisponibles.add(new Ruta("R003", "Medellin", "Cartagena", 650, 720));
    }

    private Ruta buscarRutaPorCodigo(String codigo) {
        for (Ruta r : rutasDisponibles) {
            if (r.getCodigoRuta().equals(codigo)) return r;
        }
        return new Ruta(codigo, "Origen", "Destino", 0, 0);
    }

    public void guardar(Vehiculo vehiculo) {
        String archivo = obtenerArchivo(vehiculo);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
            bw.write(vehiculoToLinea(vehiculo));
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar vehiculo: " + e.getMessage());
        }
    }

    public List<Vehiculo> listarTodos() {
        List<Vehiculo> lista = new ArrayList<>();
        lista.addAll(cargarDesdeArchivo(ARCHIVO_BUSETA,   "Buseta"));
        lista.addAll(cargarDesdeArchivo(ARCHIVO_MICROBUS, "MicroBus"));
        lista.addAll(cargarDesdeArchivo(ARCHIVO_BUS,      "Bus"));
        return lista;
    }

    public Vehiculo buscarPorPlaca(String placa) {
        for (Vehiculo v : listarTodos()) {
            if (v.getPlaca().equalsIgnoreCase(placa)) return v;
        }
        return null;
    }

    public void actualizar(Vehiculo vehiculo) {
        String archivo = obtenerArchivo(vehiculo);
        List<Vehiculo> lista = cargarDesdeArchivo(archivo, vehiculo.getClass().getSimpleName());
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, false))) {
            for (Vehiculo v : lista) {
                bw.write(vehiculoToLinea(v.getPlaca().equalsIgnoreCase(vehiculo.getPlaca()) ? vehiculo : v));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al actualizar vehiculo: " + e.getMessage());
        }
    }

    public void eliminar(String placa) {
        String[] archivos = {ARCHIVO_BUSETA, ARCHIVO_MICROBUS, ARCHIVO_BUS};
        String[] tipos    = {"Buseta", "MicroBus", "Bus"};
        for (int i = 0; i < archivos.length; i++) {
            List<Vehiculo> lista = cargarDesdeArchivo(archivos[i], tipos[i]);
            if (lista.removeIf(v -> v.getPlaca().equalsIgnoreCase(placa))) {
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivos[i], false))) {
                    for (Vehiculo v : lista) { bw.write(vehiculoToLinea(v)); bw.newLine(); }
                } catch (IOException e) {
                    System.out.println("Error al eliminar vehiculo: " + e.getMessage());
                }
                break;
            }
        }
    }

    private String vehiculoToLinea(Vehiculo v) {
        String codigoRuta = v.getRuta() != null ? v.getRuta().getCodigoRuta() : "SIN_RUTA";
        return v.getClass().getSimpleName() + ";" + v.getPlaca() + ";" + codigoRuta + ";" +
               v.getCapacidadMax() + ";" + v.getPasajerosActuales() + ";" + v.isDisponible();
    }

    private List<Vehiculo> cargarDesdeArchivo(String archivo, String tipo) {
        List<Vehiculo> lista = new ArrayList<>();
        File f = new File(archivo);
        if (!f.exists()) return lista;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(";");
                if (campos.length < 6) continue;
                String placa  = campos[1];
                Ruta   ruta   = buscarRutaPorCodigo(campos[2]);
                int    pas    = Integer.parseInt(campos[4]);
                boolean disp  = Boolean.parseBoolean(campos[5]);
                Vehiculo v = null;
                switch (campos[0]) {
                    case "Buseta"  -> v = new Buseta(placa, ruta);
                    case "MicroBus"-> v = new MicroBus(placa, ruta);
                    case "Bus"     -> v = new Bus(placa, ruta);
                }
                if (v != null) { v.setPasajerosActuales(pas); v.setDisponible(disp); lista.add(v); }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar archivo " + archivo + ": " + e.getMessage());
        }
        return lista;
    }

    private String obtenerArchivo(Vehiculo vehiculo) {
        return switch (vehiculo.getClass().getSimpleName()) {
            case "Buseta"   -> ARCHIVO_BUSETA;
            case "MicroBus" -> ARCHIVO_MICROBUS;
            default         -> ARCHIVO_BUS;
        };
    }
}