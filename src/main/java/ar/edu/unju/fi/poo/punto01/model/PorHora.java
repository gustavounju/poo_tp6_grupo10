package ar.edu.unju.fi.poo.punto01.model;

import java.util.Date;

public class PorHora extends RegistroIngresoSalida {
    private Cupon cupon;
    private double valorHora = 1000.0; // Valor por defecto especificado en el TP

    public PorHora() {
        super();
    }

    public PorHora(Integer id, Date fecha, Date hora, Vehiculo vehiculo, Cupon cupon) {
        super(id, fecha, hora, vehiculo);
        this.cupon = cupon;
        this.valorHora = 1000.0;
    }

    public PorHora(Integer id, Date fecha, Date hora, Vehiculo vehiculo, Cupon cupon, double valorHora) {
        super(id, fecha, hora, vehiculo);
        this.cupon = cupon;
        this.valorHora = valorHora;
    }

    @Override
    public Double obtenerImporte() {
        Date salida = getHoraSalida() != null ? getHoraSalida() : new Date();
        long diferenciaMilisegundos = salida.getTime() - getHora().getTime();

        if (diferenciaMilisegundos < 0) {
            diferenciaMilisegundos = 0;
        }

        // Convertir milisegundos a horas
        double horasExactas = diferenciaMilisegundos / (1000.0 * 60.0 * 60.0);

        // Regla: mínimo 1 hora siempre y redondeo hacia arriba si fracciona
        long horasACobrar = (long) Math.ceil(horasExactas);
        if (horasACobrar < 1) {
            horasACobrar = 1;
        }

        double total = horasACobrar * valorHora;

        // Descuento por cupón solo si existe y NO está vencido al momento de la salida
        if (cupon != null && !cupon.estaVencido(salida)) {
            double descuento = total * (cupon.getPorcentajeDescuento() / 100.0);
            total -= descuento;
        }

        return total;
    }

    public Cupon getCupon() {
        return cupon;
    }

    public void setCupon(Cupon cupon) {
        this.cupon = cupon;
    }

    public double getValorHora() {
        return valorHora;
    }

    public void setValorHora(double valorHora) {
        this.valorHora = valorHora;
    }

    @Override
    public String toString() {
        return "PorHora [" + super.toString() + ", valorHora=" + valorHora + ", cupon=" + cupon + "]";
    }
}