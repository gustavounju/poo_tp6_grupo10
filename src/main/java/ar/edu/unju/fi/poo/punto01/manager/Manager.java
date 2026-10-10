package ar.edu.unju.fi.poo.punto01.manager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ar.edu.unju.fi.poo.punto01.model.PorHora;
import ar.edu.unju.fi.poo.punto01.model.RegistroIngresoSalida;

public class Manager {
    private List<RegistroIngresoSalida> registros = new ArrayList<>();

    public boolean validarPatente(String patente) {
        for (RegistroIngresoSalida r : registros) {
            if (r.getVehiculo() != null 
                    && r.getVehiculo().getPatente().equalsIgnoreCase(patente) 
                    && "INGRESADO".equalsIgnoreCase(r.getEstado())) {
                return false; // Ya se encuentra dentro de la playa
            }
        }
        return true; // Patente habilitada para ingresar
    }

    public void registrarIngreso(RegistroIngresoSalida registro) {
        if (registro == null || registro.getVehiculo() == null) {
            System.out.println("Error: Registro o vehículo inválido.");
            return;
        }

        // a. Validar que el vehículo no se encuentre ya adentro
        if (!validarPatente(registro.getVehiculo().getPatente())) {
            System.out.println("Ingreso rechazado: El vehículo patente " 
                    + registro.getVehiculo().getPatente() + " ya está INGRESADO.");
            return;
        }

        // b. Si es ingreso por hora, no permitir después de las 21:00 hs
        if (registro instanceof PorHora) {
            Date horaIngreso = registro.getHora() != null ? registro.getHora() : new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(horaIngreso);
            int horaDelDia = cal.get(Calendar.HOUR_OF_DAY);

            if (horaDelDia >= 21) {
                System.out.println("Ingreso rechazado: No se permiten ingresos por hora luego de las 21:00 hs.");
                return;
            }
        }

        registro.setEstado("INGRESADO");
        registros.add(registro);
        System.out.println("Vehículo patente " + registro.getVehiculo().getPatente() + " ingresado con éxito (ID: " + registro.getId() + ").");
    }

    public RegistroIngresoSalida obtenerRegistro(Integer id) {
        for (RegistroIngresoSalida registroIngresoSalida : registros) {
            if (registroIngresoSalida.getId().equals(id)) {
                return registroIngresoSalida;
            }
        }
        return null;
    }

    public double registrarSalida(RegistroIngresoSalida registro) {
        if (registro == null) {
            System.out.println("Registro no encontrado.");
            return 0.0;
        }
        double importe = registro.obtenerImporte();
        registro.setEstado("AFUERA");
        return importe;
    }

    public List<RegistroIngresoSalida> getRegistros() {
        return registros;
    }
}