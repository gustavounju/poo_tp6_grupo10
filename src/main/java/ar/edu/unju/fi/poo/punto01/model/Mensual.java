package ar.edu.unju.fi.poo.punto01.model;

public class Mensual extends RegistroIngresoSalida {
    private Cliente cliente;

    public Mensual() {
        super();
    }

    public Mensual(Integer id, Vehiculo vehiculo, String estado, Cliente cliente) {
        super(id, vehiculo, estado);
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public Double obtenerImporte() {
        return (double) 0;
    }

    @Override
    public String toString() {
        return "Mensual [" + super.toString() + ", cliente=" + cliente + "]";
    }
}