package com.globant.pasaportesanitario.flujos.autodiagnostico;

public enum TipoSintoma {
    S_TDG(r2),
    S_DRE(r3),
    S_PDO(r4),
    S_PDG(r5);
    
    public final String value;

    /* access modifiers changed from: public */
    TipoSintoma(String str) {
        this.value = str;
    }
}
