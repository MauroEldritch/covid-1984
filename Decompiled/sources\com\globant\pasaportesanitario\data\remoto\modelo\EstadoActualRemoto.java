package com.globant.pasaportesanitario.data.remoto.modelo;

import c.d.b.d0.b;

public class EstadoActualRemoto {
    @b("datos-coep")
    public DatosCoepRemoto datosCoep = new DatosCoepRemoto();
    @b("fecha-hora-vencimiento")
    public String fechaHoraVencimiento;
    @b("nombre-estado")
    public String nombreEstado;
    @b("permiso-circulacion")
    public PermisoCirculacionRemoto permisoCirculacionRemoto = new PermisoCirculacionRemoto();

    public EstadoActualRemoto() {
        String str = "";
        this.nombreEstado = str;
        this.fechaHoraVencimiento = str;
    }
}
