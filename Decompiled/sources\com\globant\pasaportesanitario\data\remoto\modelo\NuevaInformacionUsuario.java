package com.globant.pasaportesanitario.data.remoto.modelo;

import c.d.b.d0.b;

public class NuevaInformacionUsuario {
    public DomicilioRemoto domicilio;
    @b("geo-inicial")
    public GeoRemoto geo;
    public String telefono;

    public NuevaInformacionUsuario(String str, DomicilioRemoto domicilioRemoto, GeoRemoto geoRemoto) {
        this.telefono = str;
        this.domicilio = domicilioRemoto;
        this.geo = geoRemoto;
    }
}
