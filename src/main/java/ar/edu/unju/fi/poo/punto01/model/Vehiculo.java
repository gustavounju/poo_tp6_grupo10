package ar.edu.unju.fi.poo.punto01.model;

import java.util.Objects;

public class Vehiculo {
    private String patente;
    private String marca;
    private String color;

    public Vehiculo() {
    }

    public Vehiculo(String patente, String marca, String color) {
        this.patente = patente;
        this.marca = marca;
        this.color = color;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehiculo vehiculo = (Vehiculo) o;
        return Objects.equals(patente, vehiculo.patente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patente);
    }

    @Override
    public String toString() {
        return "Vehiculo [patente=" + patente + ", marca=" + marca + ", color=" + color + "]";
    }
}