package com.globant.pasaportesanitario.data;

import c.d.b.d0.b;

public class Localidad {
    @b("departamento_id")
    public String departamentoId;
    @b("departamento_nombre")
    public String departamentoNombre;
    @b("id")
    public String id;
    @b("localidad_censal_id")
    public String localidadCensalId;
    @b("localidad_censal_nombre")
    public String localidadCensalNombre;
    @b("nombre")
    public String nombre;
    @b("provincia_id")
    public String provinciaId;
    @b("provincia_nombre")
    public String provinciaNombre;

    public String getDepartamentoId() {
        return this.departamentoId;
    }

    public String getDepartamentoNombre() {
        return this.departamentoNombre;
    }

    public String getId() {
        return this.id;
    }

    public String getLocalidadCensalId() {
        return this.localidadCensalId;
    }

    public String getLocalidadCensalNombre() {
        return this.localidadCensalNombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getProvinciaId() {
        return this.provinciaId;
    }

    public String getProvinciaNombre() {
        return this.provinciaNombre;
    }

    public void setDepartamentoId(String str) {
        this.departamentoId = str;
    }

    public void setDepartamentoNombre(String str) {
        this.departamentoNombre = str;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setLocalidadCensalId(String str) {
        this.localidadCensalId = str;
    }

    public void setLocalidadCensalNombre(String str) {
        this.localidadCensalNombre = str;
    }

    public void setNombre(String str) {
        this.nombre = str;
    }

    public void setProvinciaId(String str) {
        this.provinciaId = str;
    }

    public void setProvinciaNombre(String str) {
        this.provinciaNombre = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.nombre);
        sb.append(" - ");
        sb.append(this.departamentoNombre);
        return sb.toString();
    }
}
