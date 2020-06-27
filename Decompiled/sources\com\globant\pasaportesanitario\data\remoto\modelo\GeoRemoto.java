package com.globant.pasaportesanitario.data.remoto.modelo;

public class GeoRemoto {
    public String altura;
    public String latitud;
    public String longitud;

    public GeoRemoto(String str, String str2, String str3) {
        this.latitud = str;
        this.longitud = str2;
        this.altura = str3;
    }

    public GeoRemoto(Double d2, Double d3, Double d4) {
        this.latitud = Double.toString(d2.doubleValue());
        this.longitud = Double.toString(d3.doubleValue());
        this.altura = Double.toString(d4.doubleValue());
    }
}
