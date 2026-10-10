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

        // Clientes
        Cliente clienteMensual = new Cliente(1, "30123456", "Belgrano 123", "3888123456");

        // Cupones: uno vigente (mañana) y uno vencido (ayer)
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

        // b. Registro de vehículo por hora con cupón (no vencido y vencido)
        System.out.println("--- Caso b1: Ingreso por hora CON cupón NO VENCIDO (10% desc.) ---");
        RegistroIngresoSalida regHoraCuponValido = new PorHora(102, v2, "INGRESADO", cuponValido, 1000.0);
        manager.registrarIngreso(regHoraCuponValido);
        System.out.println();

        System.out.println("--- Caso b2: Ingreso por hora CON cupón VENCIDO ---");
        RegistroIngresoSalida regHoraCuponVencido = new PorHora(103, v3, "INGRESADO", cuponVencido, 1000.0);
        manager.registrarIngreso(regHoraCuponVencido);
        System.out.println();

        // c. Registro de ingreso fuera de horario (luego de las 21:00 hs)
        System.out.println("--- Caso c: Ingreso por hora FUERA DE HORARIO (21:30 hs) ---");
        Calendar calFueraHora = Calendar.getInstance();
        calFueraHora.set(Calendar.HOUR_OF_DAY, 21);
        calFueraHora.set(Calendar.MINUTE, 30);

        RegistroIngresoSalida regFueraHora = new PorHora(104, v4, "INGRESADO", null, 1000.0);
        regFueraHora.setHora(calFueraHora.getTime());
        manager.registrarIngreso(regFueraHora);
        System.out.println();

        // d. Registro de ingreso de vehículo de cliente mensual
        System.out.println("--- Caso d: Ingreso de vehículo MENSUAL ---");
        RegistroIngresoSalida regMensual = new Mensual(105, v5, "INGRESADO", clienteMensual);
        manager.registrarIngreso(regMensual);
        System.out.println();

        // e. Buscar registro por ID y mostrar importe actual (sin dar de baja aún)
        System.out.println("--- Caso e: Buscar por ID y mostrar importe actual ---");
        RegistroIngresoSalida busqueda1 = manager.obtenerRegistro(102);
        if (busqueda1 != null) {
            System.out.println("Registro encontrado ID " + busqueda1.getId() 
                    + " | Vehículo: " + busqueda1.getVehiculo().getPatente() 
                    + " | Importe actual calculado: $" + busqueda1.obtenerImporte()
                    + " | Estado: " + busqueda1.getEstado());
        }
        System.out.println();

        // f. Buscar registro por ID y registrar salida de vehículo por hora
        System.out.println("--- Caso f: Buscar por ID y registrar salida de vehículo POR HORA ---");
        RegistroIngresoSalida busquedaSalidaHora = manager.obtenerRegistro(101);
        if (busquedaSalidaHora != null) {
            double totalCobrado = manager.registrarSalida(busquedaSalidaHora);
            System.out.println("Salida efectuada para patente: " + busquedaSalidaHora.getVehiculo().getPatente());
            System.out.println("Importe cobrado: $" + totalCobrado);
            System.out.println("Nuevo estado: " + busquedaSalidaHora.getEstado());
        }
        System.out.println();

        // g. Buscar registro por ID y registrar salida de vehículo mensual
        System.out.println("--- Caso g: Buscar por ID y registrar salida de vehículo MENSUAL ---");
        RegistroIngresoSalida busquedaSalidaMensual = manager.obtenerRegistro(105);
        if (busquedaSalidaMensual != null) {
            double totalCobradoMensual = manager.registrarSalida(busquedaSalidaMensual);
            System.out.println("Salida efectuada para patente: " + busquedaSalidaMensual.getVehiculo().getPatente());
            System.out.println("Importe cobrado: $" + totalCobradoMensual);
            System.out.println("Nuevo estado: " + busquedaSalidaMensual.getEstado());
        }
    }
}