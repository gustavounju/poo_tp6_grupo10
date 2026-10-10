package ar.edu.unju.fi.poo.punto01.model;

import java.util.Date;

public class PorHora extends RegistroIngresoSalida {
    private Cupon cupon;
    private Double valorHora;

    public PorHora() {
        super();
    }

    public PorHora(Integer id, Vehiculo vehiculo, String estado, Cupon cupon, Double valorHora) {
        super(id, vehiculo, estado);
        this.cupon = cupon;
        this.valorHora = valorHora;
    }

    public Cupon getCupon() {
        return cupon;
    }

    public void setCupon(Cupon cupon) {
        this.cupon = cupon;
    }

    public Double getValorHora() {
        return valorHora;
    }

    public void setValorHora(Double valorHora) {
        this.valorHora = valorHora;
    }

    @Override
    public Double obtenerImporte() {
        Date salida = new Date();
        long diferenciaMilisegundos = salida.getTime() - getHora().getTime();

        if (diferenciaMilisegundos < 0) {
            diferenciaMilisegundos = 0;
        }

        // Convertir milisegundos a horas
        double horasExactas = diferenciaMilisegundos / (1000.0 * 60.0 * 60.0);

        // Regla: no se fracciona por menos de una hora y mínimo 1 hora siempre
        long horasACobrar = (long) Math.ceil(horasExactas);
        if (horasACobrar < 1) {
            horasACobrar = 1;
        }

        double total = horasACobrar * (valorHora != null ? valorHora : 1000.0);

        // Si tiene cupón y no está vencido, se aplica el descuento
        if (cupon != null && !cupon.estaVencido(salida)) {
            double descuento = total * (cupon.getPorcentajeDescuento() / 100.0);
            total -= descuento;
        }

        return total;
    }

    @Override
    public String toString() {
        return "PorHora [" + super.toString() + ", cupon=" + cupon + ", valorHora=" + valorHora + "]";
    }
}