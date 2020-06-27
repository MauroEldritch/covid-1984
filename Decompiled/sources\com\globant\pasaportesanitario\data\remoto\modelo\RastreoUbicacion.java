package com.globant.pasaportesanitario.data.remoto.modelo;

public class RastreoUbicacion {
    public final String altitud;
    public final String latitud;
    public final String longitud;

    public RastreoUbicacion(String str, String str2, String str3) {
        this.latitud = str;
        this.longitud = str2;
        this.altitud = str3;
    }

    public String getAltitud() {
        return this.altitud;
    }

    public String getLatitud() {
        return this.latitud;
    }

    public String getLongitud() {
        return this.longitud;
    }
}
