package com.globant.pasaportesanitario.data;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class NombreEstado {
    public static final String DEBE_AUTODIAGNOSTICARSE = "DEBE_AUTODIAGNOSTICARSE";
    public static final String DERIVADO_A_SALUD_LOCAL = "DERIVADO_A_SALUD_LOCAL";
    public static final String INFECTADO = "INFECTADO";
    public static final String NO_CONTAGIOSO = "NO_CONTAGIOSO";
    public static final String NO_INFECTADO = "NO_INFECTADO";
    public final String nombreEstado;

    @Retention(RetentionPolicy.SOURCE)
    public @interface NombreEstadoDef {
    }

    public NombreEstado(String str) {
        this.nombreEstado = str;
    }
}
