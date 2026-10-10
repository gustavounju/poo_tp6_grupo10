package ar.edu.unju.fi.poo.punto01.model;

import java.util.Date;

public class Cupon {
    private String codigo;
    private Date fechaVencimiento;
    private Double porcentajeDescuento;

    public Cupon() {
    }

    public Cupon(String codigo, Date fechaVencimiento, Double porcentajeDescuento) {
        this.codigo = codigo;
        this.fechaVencimiento = fechaVencimiento;
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(Double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    /**
     * Valida si el cupón se encuentra vencido con respecto a una fecha dada.
     */
    public boolean estaVencido(Date fechaComparacion) {
        if (fechaVencimiento == null || fechaComparacion == null) {
            return true;
        }
        return fechaComparacion.after(fechaVencimiento);
    }

    @Override
    public String toString() {
        return "Cupon [codigo=" + codigo + ", fechaVencimiento=" + fechaVencimiento
                + ", porcentajeDescuento=" + porcentajeDescuento + "%]";
    }
}