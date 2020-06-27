package com.globant.pasaportesanitario.data.remoto.modelo;

import c.d.b.d0.b;

public class PermisoCirculacionRemoto {
    @b("fecha-vencimiento-permiso")
    public String fechaVencimientoPermiso;
    @b("permiso-qr")
    public String permisoQr;
    @b("status-servicio")
    public int statusServicio = -1;

    public PermisoCirculacionRemoto() {
        String str = "";
        this.permisoQr = str;
        this.fechaVencimientoPermiso = str;
    }
}
