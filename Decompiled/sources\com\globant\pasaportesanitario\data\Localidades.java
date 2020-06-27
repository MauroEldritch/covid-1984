package com.globant.pasaportesanitario.data;

import c.a.a.a.a;
import c.d.b.d0.b;
import java.util.List;

public class Localidades {
    @b("cantidad")
    public Integer cantidad;
    @b("inicio")
    public Integer inicio;
    @b("localidades")
    public List<Localidad> localidades = null;
    @b("total")
    public Integer total;

    public Integer getCantidad() {
        return this.cantidad;
    }

    public Integer getInicio() {
        return this.inicio;
    }

    public List<Localidad> getLocalidades() {
        return this.localidades;
    }

    public Integer getTotal() {
        return this.total;
    }

    public void setCantidad(Integer num) {
        this.cantidad = num;
    }

    public void setInicio(Integer num) {
        this.inicio = num;
    }

    public void setLocalidades(List<Localidad> list) {
        this.localidades = list;
    }

    public void setTotal(Integer num) {
        this.total = num;
    }

    public String toString() {
        StringBuilder a2 = a.a("Localidades{cantidad=");
        a2.append(this.cantidad);
        a2.append(", inicio=");
        a2.append(this.inicio);
        a2.append(", localidades=");
        a2.append(this.localidades);
        a2.append(", total=");
        a2.append(this.total);
        a2.append('}');
        return a2.toString();
    }
}
