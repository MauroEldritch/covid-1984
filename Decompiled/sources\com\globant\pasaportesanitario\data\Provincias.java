package com.globant.pasaportesanitario.data;

import c.d.b.d0.b;
import java.util.List;

public class Provincias {
    @b("cantidad")
    public Integer cantidad;
    @b("inicio")
    public Integer inicio;
    @b("provincias")
    public List<Provincia> provincias = null;
    @b("total")
    public Integer total;

    public Integer getCantidad() {
        return this.cantidad;
    }

    public Integer getInicio() {
        return this.inicio;
    }

    public List<Provincia> getProvincias() {
        return this.provincias;
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

    public void setProvincias(List<Provincia> list) {
        this.provincias = list;
    }

    public void setTotal(Integer num) {
        this.total = num;
    }
}
