package com.globant.pasaportesanitario.data.local.modelo;

public class EstadoActualBD {
    public DatosCoepBD datosCoepBD;
    public String fechaHoraVencimiento;
    public String nombreEstado;
    public PermisoCirculacionBD permisoCirculacionBD;

    public EstadoActualBD(String str, String str2, DatosCoepBD datosCoepBD2, PermisoCirculacionBD permisoCirculacionBD2) {
        this.nombreEstado = str;
        this.fechaHoraVencimiento = str2;
        this.datosCoepBD = datosCoepBD2;
        this.permisoCirculacionBD = permisoCirculacionBD2;
    }
}
