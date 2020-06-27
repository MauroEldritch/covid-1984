package com.globant.pasaportesanitario.data.remoto.modelo_autodiagnostico;

import java.util.ArrayList;
import java.util.List;

public class AutoevaluacionRemoto {
    public List<AntecedentesRemoto> antecedentes;
    public List<SintomasRemoto> sintomas;
    public double temperatura;

    public AutoevaluacionRemoto(double d2, ArrayList<SintomasRemoto> arrayList, ArrayList<AntecedentesRemoto> arrayList2) {
        this.temperatura = d2;
        this.antecedentes = arrayList2;
        this.sintomas = arrayList;
    }

    public List<AntecedentesRemoto> getAntecedentes() {
        return this.antecedentes;
    }

    public List<SintomasRemoto> getSintomas() {
        return this.sintomas;
    }

    public double getTemperatura() {
        return this.temperatura;
    }

    public void setAntecedentes(List<AntecedentesRemoto> list) {
        this.antecedentes = list;
    }

    public void setSintomas(List<SintomasRemoto> list) {
        this.sintomas = list;
    }

    public void setTemperatura(double d2) {
        this.temperatura = d2;
    }
}
