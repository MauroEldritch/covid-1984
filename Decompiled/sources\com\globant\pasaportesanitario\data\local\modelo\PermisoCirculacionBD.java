package com.globant.pasaportesanitario.data.local.modelo;

public class PermisoCirculacionBD {
    public String fechaVencimientoPermiso;
    public String permisoQr;
    public int statusServicio;

    public PermisoCirculacionBD(String str, String str2, int i) {
        this.permisoQr = str;
        this.fechaVencimientoPermiso = str2;
        this.statusServicio = i;
    }
}
