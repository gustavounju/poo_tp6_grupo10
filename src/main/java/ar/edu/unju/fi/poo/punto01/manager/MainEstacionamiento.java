package ar.edu.unju.fi.poo.punto01.manager;

import java.util.Calendar;
import java.util.Date;

import ar.edu.unju.fi.poo.punto01.manager.Manager;
import ar.edu.unju.fi.poo.punto01.model.Cliente;
import ar.edu.unju.fi.poo.punto01.model.Cupon;
import ar.edu.unju.fi.poo.punto01.model.Mensual;
import ar.edu.unju.fi.poo.punto01.model.PorHora;
import ar.edu.unju.fi.poo.punto01.model.RegistroIngresoSalida;
import ar.edu.unju.fi.poo.punto01.model.Vehiculo;

public class MainEstacionamiento {

    public static void main(String[] args) {
        System.out.println("==========================================================");
        System.out.println("   TRABAJO PRACTICO Nº 6 - PUNTO 1: PLAYA DE ESTACIONAMIENTO");
        System.out.println("==========================================================\n");

        Manager manager = new Manager();

        // Vehículos de prueba
        Vehiculo v1 = new Vehiculo("AA111AA", "Toyota", "Gris");
        Vehiculo v2 = new Vehiculo("BB222BB", "Ford", "Azul");
        Vehiculo v3 = new Vehiculo("CC333CC", "Chevrolet", "Blanco");
        Vehiculo v4 = new Vehiculo("DD444DD", "Volkswagen", "Rojo");
        Vehiculo v5 = new Vehiculo("EE555EE", "Fiat", "Negro");
        Vehiculo v6 = new Vehiculo("FF666FF", "Peugeot", "Gris");

        // Cliente mensual
        Cliente clienteMensual = new Cliente(1, "30123456", "Belgrano 123", "3888123456");

        // Cupones: uno vigente (+2 días) y uno vencido (-2 días)
        Calendar calVigente = Calendar.getInstance();
        calVigente.add(Calendar.DAY_OF_YEAR, 2);
        Cupon cuponValido = new Cupon("DESC10", calVigente.getTime(), 10.0);

        Calendar calVencido = Calendar.getInstance();
        calVencido.add(Calendar.DAY_OF_YEAR, -2);
        Cupon cuponVencido = new Cupon("DESC50", calVencido.getTime(), 50.0);

        // a. Registro de vehículo por hora sin cupón
        System.out.println("--- Caso a: Ingreso por hora SIN cupón ---");
        RegistroIngresoSalida regHoraSinCupon = new PorHora(101, v1, "INGRESADO", null, 1000.0);
        manager.registrarIngreso(regHoraSinCupon);
        System.out.println();

        // b1. Registro por hora con cupón NO VENCIDO
        System.out.println("--- Caso b1: Ingreso por hora CON cupón NO VENCIDO (10% desc.) ---");
        RegistroIngresoSalida regHoraCuponValido = new PorHora(102, v2, "INGRESADO", cuponValido, 1000.0);
        manager.registrarIngreso(regHoraCuponValido);
        System.out.println();

        // b2. Registro por hora con cupón VENCIDO
        System.out.println("--- Caso b2: Ingreso por hora CON cupón VENCIDO ---");
        RegistroIngresoSalida regHoraCuponVencido = new PorHora(103, v3, "INGRESADO", cuponVencido, 1000.0);
        manager.registrarIngreso(regHoraCuponVencido);
        System.out.println();

        // c. Registro por hora FUERA DE HORARIO (21:30 hs) -> Debe ser rechazado
        System.out.println("--- Caso c: Ingreso por hora FUERA DE HORARIO (21:30 hs) ---");
        Calendar calFueraHora = Calendar.getInstance();
        calFueraHora.set(Calendar.HOUR_OF_DAY, 21);
        calFueraHora.set(Calendar.MINUTE, 30);
        RegistroIngresoSalida regFueraHora = new PorHora(104, v4, "INGRESADO", null, 1000.0);
        regFueraHora.setHora(calFueraHora.getTime());
        manager.registrarIngreso(regFueraHora);
        System.out.println();

        // d. Registro de vehículo mensual
        System.out.println("--- Caso d: Ingreso de vehículo MENSUAL ---");
        RegistroIngresoSalida regMensual = new Mensual(105, v5, "INGRESADO", clienteMensual);
        manager.registrarIngreso(regMensual);
        System.out.println();

        // Prueba extra del profe: Mensual ingresando después de hora (ej. 23:00 hs) -> Debe permitirse
        System.out.println("--- Prueba Extra Profe: Ingreso de MENSUAL a las 23:00 hs ---");
        Calendar calNoche = Calendar.getInstance();
        calNoche.set(Calendar.HOUR_OF_DAY, 23);
        calNoche.set(Calendar.MINUTE, 0);
        Vehiculo vMensualNoche = new Vehiculo("GG777GG", "Renault", "Azul");
        RegistroIngresoSalida regMensualNoche = new Mensual(106, vMensualNoche, "INGRESADO", clienteMensual);
        regMensualNoche.setHora(calNoche.getTime());
        manager.registrarIngreso(regMensualNoche);
        System.out.println();

        // Prueba extra del profe: Intentar ingresar un auto con patente ya ingresada
        System.out.println("--- Prueba Extra Profe: Intento de ingreso con PATENTE DUPLICADA adentro ---");
        RegistroIngresoSalida regDuplicado = new PorHora(107, v1, "INGRESADO", null, 1000.0);
        manager.registrarIngreso(regDuplicado);
        System.out.println();

        // e. Buscar por ID y mostrar importe actual
        System.out.println("--- Caso e: Buscar por ID y mostrar importe actual (ID 102 con cupón) ---");
        RegistroIngresoSalida busqueda1 = manager.obtenerRegistro(102);
        if (busqueda1 != null) {
            System.out.println("Registro encontrado ID: " + busqueda1.getId() 
                    + " | Patente: " + busqueda1.getVehiculo().getPatente() 
                    + " | Importe calculado: $" + busqueda1.obtenerImporte()
                    + " | Estado: " + busqueda1.getEstado());
        }
        System.out.println();

        // f. Registrar salida de vehículo por hora
        System.out.println("--- Caso f: Registrar salida de vehículo POR HORA (ID 101) ---");
        RegistroIngresoSalida salidaHora = manager.obtenerRegistro(101);
        if (salidaHora != null) {
            double totalCobrado = manager.registrarSalida(salidaHora);
            System.out.println("Patente: " + salidaHora.getVehiculo().getPatente() 
                    + " | Cobrado: $" + totalCobrado 
                    + " | Estado final: " + salidaHora.getEstado());
        }
        System.out.println();

        // g. Registrar salida de vehículo mensual (debe dar 0.0)
        System.out.println("--- Caso g: Registrar salida de vehículo MENSUAL (ID 105) ---");
        RegistroIngresoSalida salidaMensual = manager.obtenerRegistro(105);
        if (salidaMensual != null) {
            double totalCobradoMensual = manager.registrarSalida(salidaMensual);
            System.out.println("Patente: " + salidaMensual.getVehiculo().getPatente() 
                    + " | Cobrado: $" + totalCobradoMensual 
                    + " | Estado final: " + salidaMensual.getEstado());
        }
        System.out.println();

        // Prueba extra del profe: Auto por hora que entra y sale a los 30 minutos (cobra 1 hora mínima)
        System.out.println("--- Prueba Extra Profe: Salida a la media hora (Cobra 1 hora mínima) ---");
        Calendar calEntradaMediaHora = Calendar.getInstance();
        calEntradaMediaHora.add(Calendar.MINUTE, -30); // Entró hace 30 min
        RegistroIngresoSalida regMediaHora = new PorHora(108, v6, "INGRESADO", null, 1000.0);
        regMediaHora.setHora(calEntradaMediaHora.getTime());
        manager.registrarIngreso(regMediaHora);

        double cobroMinimo = manager.registrarSalida(regMediaHora);
        System.out.println("Permanencia: 30 minutos | Importe cobrado: $" + cobroMinimo 
                + " (Regla: no fracciona por menos de 1h y cobra $1000)");
    }
}