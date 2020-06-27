package com.globant.pasaportesanitario.data.local.modelo;

public class TiempoRestante {
    public String tiempoRestante;
    public String unidadTiempo;

    public TiempoRestante(String str, String str2) {
        this.tiempoRestante = str;
        this.unidadTiempo = str2;
    }

    public String getTiempoRestante() {
        return this.tiempoRestante;
    }

    public String getUnidadTiempo() {
        return this.unidadTiempo;
    }

    public void setTiempoRestante(String str) {
        this.tiempoRestante = str;
    }

    public void setUnidadTiempo(String str) {
        this.unidadTiempo = str;
    }
}
