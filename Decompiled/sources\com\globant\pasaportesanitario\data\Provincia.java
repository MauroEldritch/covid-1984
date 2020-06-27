package com.globant.pasaportesanitario.data;

import c.d.b.d0.b;

public class Provincia {
    @b("id")
    public String id;
    @b("nombre")
    public String nombre;

    public String getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setNombre(String str) {
        this.nombre = str;
    }

    public String toString() {
        return this.nombre;
    }
}
