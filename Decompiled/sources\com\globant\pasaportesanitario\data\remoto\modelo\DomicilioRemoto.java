package com.globant.pasaportesanitario.data.remoto.modelo;

import c.d.b.d0.b;

public class DomicilioRemoto {
    public String calle;
    @b("codigo-postal")
    public String codigoPostal;
    public String departamento;
    public GeoRemoto geoRemoto;
    public String localidad;
    public String numero;
    public String otros;
    public String piso;
    public String provincia;
    public String puerta;

    public DomicilioRemoto(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        this.provincia = str;
        this.localidad = str2;
        this.calle = str3;
        this.numero = str4;
        this.piso = str5;
        this.puerta = str6;
        this.codigoPostal = str7;
        this.otros = str8;
        this.departamento = str9;
    }
}
