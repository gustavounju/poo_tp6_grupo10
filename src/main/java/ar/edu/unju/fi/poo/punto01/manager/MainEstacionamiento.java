package ar.edu.unju.fi.poo.punto01.manager;

import java.util.Calendar;
import ar.edu.unju.fi.poo.punto01.model.Cliente;
import ar.edu.unju.fi.poo.punto01.model.Cupon;
import ar.edu.unju.fi.poo.punto01.model.Mensual;
import ar.edu.unju.fi.poo.punto01.model.PorHora;
import ar.edu.unju.fi.poo.punto01.model.RegistroIngresoSalida;
import ar.edu.unju.fi.poo.punto01.model.Vehiculo;

public class MainEstacionamiento {

    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GESTION DE PLAYA DE ESTACIONAMIENTO ===\n");

        Vehiculo vehiculo1 = new Vehiculo("AAA", "FORD", "BLANCO");
        Vehiculo vehiculo2 = new Vehiculo("BBB", "FORD", "VERDE");
        Vehiculo vehiculo3 = new Vehiculo("CCC", "TOYOTA", "NEGRO");

        Cliente cliente1 = new Cliente(100, "100", "Carlos", "388463521");

        // Cupón vigente con 10% de descuento (vence mañana)
        Calendar calManana = Calendar.getInstance();
        calManana.add(Calendar.DAY_OF_YEAR, 1);
        Cupon cupon1 = new Cupon("CC11", calManana.getTime(), 10.0);

        Manager manager = new Manager();

        // 1. Caso de prueba: Vehículo mensual
        System.out.println("--- Caso 1: Ingreso y Salida de Mensual ---");
        RegistroIngresoSalida registro1 = new Mensual(100, vehiculo1, "INGRESADO", cliente1);
        manager.registrarIngreso(registro1);

        RegistroIngresoSalida registroBuscado1 = manager.obtenerRegistro(100);
        Double importeMensual = manager.registrarSalida(registroBuscado1);
        System.out.println("El importe es: " + importeMensual);
        System.out.println("Estado final: " + registroBuscado1.getEstado() + "\n");

        // 2. Caso de prueba: Vehículo por hora con cupón
        System.out.println("--- Caso 2: Ingreso y Salida Por Hora con Cupon ---");
        RegistroIngresoSalida registro2 = new PorHora(101, vehiculo2, "INGRESADO", cupon1, 1000.0);
        manager.registrarIngreso(registro2);

        RegistroIngresoSalida registroBuscado2 = manager.obtenerRegistro(101);
        Double importePorHora = manager.registrarSalida(registroBuscado2);
        System.out.println("El importe es: " + importePorHora);
        System.out.println("Estado final: " + registroBuscado2.getEstado() + "\n");

        // 3. Caso de prueba: Intentar ingresar un vehículo ya ingresado
        System.out.println("--- Caso 3: Validacion de Patente Duplicada ---");
        RegistroIngresoSalida registro3 = new PorHora(102, vehiculo3, "INGRESADO", null, 1000.0);
        manager.registrarIngreso(registro3);

        // Se intenta ingresar nuevamente el mismo vehículo estando adentro
        RegistroIngresoSalida registro3Repetido = new PorHora(103, vehiculo3, "INGRESADO", null, 1000.0);
        manager.registrarIngreso(registro3Repetido);
        System.out.println();

        // 4. Caso de prueba: Vehículo por hora intentando ingresar fuera de horario (22:00 hs)
        System.out.println("--- Caso 4: Ingreso Por Hora despues de las 21:00 hs ---");
        Calendar calTarde = Calendar.getInstance();
        calTarde.set(Calendar.HOUR_OF_DAY, 22);
        calTarde.set(Calendar.MINUTE, 15);

        Vehiculo vehiculo4 = new Vehiculo("DDD", "FIAT", "ROJO");
        RegistroIngresoSalida registroTarde = new PorHora(104, vehiculo4, "INGRESADO", null, 1000.0);
        registroTarde.setHora(calTarde.getTime());
        manager.registrarIngreso(registroTarde);
    }
}