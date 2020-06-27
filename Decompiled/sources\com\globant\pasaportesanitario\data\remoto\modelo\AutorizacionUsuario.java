package com.globant.pasaportesanitario.data.remoto.modelo;

public class AutorizacionUsuario {
    public String dni;
    public String sexo;
    public String tramite;

    public AutorizacionUsuario(String str, String str2, String str3) {
        this.dni = str;
        this.sexo = str2;
        this.tramite = str3;
    }
}
