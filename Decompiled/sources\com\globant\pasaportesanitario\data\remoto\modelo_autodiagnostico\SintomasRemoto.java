package com.globant.pasaportesanitario.data.remoto.modelo_autodiagnostico;

public class SintomasRemoto {
    public String descripcion;
    public String id;
    public boolean valor;

    public SintomasRemoto(String str, String str2, boolean z) {
        this.id = str;
        this.descripcion = str2;
        this.valor = z;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public String getId() {
        return this.id;
    }

    public boolean isValor() {
        return this.valor;
    }

    public void setDescripcion(String str) {
        this.descripcion = str;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setValor(boolean z) {
        this.valor = z;
    }
}
