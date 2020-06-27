package com.globant.pasaportesanitario.data.remoto.modelo;

import c.d.b.d0.b;

public class UsuarioRemoto {
    public String apellidos;
    public Long dni;
    public DomicilioRemoto domicilio;
    @b("estado-actual")
    public EstadoActualRemoto estadoActualRemoto = new EstadoActualRemoto();
    @b("fecha-nacimiento")
    public String fechaNacimiento;
    @b("geo-inicial")
    public GeoRemoto geolocalizacion;
    public String nombres;
    public String sexo;
    public String telefono;
}
