package com.globant.pasaportesanitario.data.local.modelo;

public class UsuarioBD {
    public String apellidos;
    public Long dni;
    public DomicilioBD domicilio;
    public EstadoActualBD estadoActual;
    public String fechaNacimiento;
    public GeoBD geolocalizacion;
    public String nombres;
    public String sexo;
    public String telefono;
    public Long usuarioId;

    public UsuarioBD(String str, Long l, String str2, String str3, String str4, String str5, DomicilioBD domicilioBD, GeoBD geoBD, EstadoActualBD estadoActualBD) {
        this.sexo = str;
        this.dni = l;
        this.fechaNacimiento = str2;
        this.nombres = str3;
        this.apellidos = str4;
        this.telefono = str5;
        this.domicilio = domicilioBD;
        this.geolocalizacion = geoBD;
        this.estadoActual = estadoActualBD;
    }
}
