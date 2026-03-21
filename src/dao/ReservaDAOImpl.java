/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */


/**
 *
 * @author Csamu
 */
package dao;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Reserva;
import model.EstadoReserva;
import model.Pasajero;
import model.Vehiculo;

public class ReservaDAOImpl implements ReservaDAO {

    private static final String ARCHIVO = "reservas.txt";

    @Override
    public void guardar(Reserva reserva) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            bw.write(reservaToLinea(reserva));
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar reserva: " + e.getMessage());
        }
    }

    @Override
    public List<Reserva> listarTodas() {
        List<Reserva> lista = new ArrayList<>();
        File f = new File(ARCHIVO);
        if (!f.exists()) return lista;
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Reserva r = lineaToReserva(linea);
                if (r != null) lista.add(r);
            }
        } catch (IOException e) {
            System.out.println("Error al leer reservas: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Reserva buscarPorCodigo(String codigo) {
        for (Reserva r : listarTodas()) {
            if (r.getCodigo().equalsIgnoreCase(codigo)) return r;
        }
        return null;
    }

    @Override
    public void actualizar(Reserva reserva) {
        List<Reserva> lista = listarTodas();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO, false))) {
            for (Reserva r : lista) {
                if (r.getCodigo().equalsIgnoreCase(reserva.getCodigo())) {
                    bw.write(reservaToLinea(reserva));
                } else {
                    bw.write(reservaToLinea(r));
                }
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al actualizar reserva: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(String codigo) {
        List<Reserva> lista = listarTodas();
        lista.removeIf(r -> r.getCodigo().equalsIgnoreCase(codigo));
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO, false))) {
            for (Reserva r : lista) {
                bw.write(reservaToLinea(r));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al eliminar reserva: " + e.getMessage());
        }
    }

    // ── Métodos auxiliares ──────────────────────────────
    private String reservaToLinea(Reserva r) {
        return r.getCodigo() + ";" +
               r.getPasajero().getCedula() + ";" +
               r.getVehiculo().getPlaca() + ";" +
               r.getFechaCreacion() + ";" +
               r.getFechaViaje() + ";" +
               r.getEstado();
    }

    private Reserva lineaToReserva(String linea) {
        String[] campos = linea.split(";");
        if (campos.length < 6) return null;
        Reserva r = new Reserva();
        r.setCodigo(campos[0]);
        r.setFechaCreacion(LocalDate.parse(campos[3]));
        r.setFechaViaje(LocalDate.parse(campos[4]));
        r.setEstado(campos[5]);
        return r;
    }
}
