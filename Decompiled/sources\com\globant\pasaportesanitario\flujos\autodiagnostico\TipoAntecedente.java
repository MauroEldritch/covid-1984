package com.globant.pasaportesanitario.flujos.autodiagnostico;

public enum TipoAntecedente {
    A_EMB(r2),
    A_CAN(r3),
    A_DIA(r4),
    A_HEP(r5),
    A_REN(r6),
    A_RES(r7),
    A_CAR(r8);
    
    public final String value;

    /* access modifiers changed from: public */
    TipoAntecedente(String str) {
        this.value = str;
    }
}
