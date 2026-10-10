package ar.edu.unju.fi.poo.punto01.model;

import java.util.Date;

public class Mensual extends RegistroIngresoSalida {
    private Cliente cliente;

    public Mensual() {
        super();
    }

    public Mensual(Integer id, Date fecha, Date hora, Vehiculo vehiculo, Cliente cliente) {
        super(id, fecha, hora, vehiculo);
        this.cliente = cliente;
    }

    @Override
    public Double obtenerImporte() {
        // En los vehículos mensuales el importe a pagar al salir es siempre cero
        return 0.0;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Mensual [" + super.toString() + ", cliente=" + cliente + "]";
    }
}