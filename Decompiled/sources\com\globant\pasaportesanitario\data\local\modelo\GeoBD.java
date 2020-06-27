package com.globant.pasaportesanitario.data.local.modelo;

public class GeoBD {
    public String altura;
    public String latitud;
    public String longitud;

    public GeoBD(String str, String str2, String str3) {
        this.latitud = str;
        this.longitud = str2;
        this.altura = str3;
    }
}
