package com.globant.pasaportesanitario.flujos.autodiagnostico;

import android.os.Bundle;
import c.a.a.a.a;
import java.util.HashMap;

public class AutodiagnosticoAntecedentesFragmentArgs {
    public final HashMap arguments;

    public static class Builder {
        public final HashMap arguments;

        public Builder(AutodiagnosticoAntecedentesFragmentArgs autodiagnosticoAntecedentesFragmentArgs) {
            HashMap hashMap = new HashMap();
            this.arguments = hashMap;
            hashMap.putAll(autodiagnosticoAntecedentesFragmentArgs.arguments);
        }

        public AutodiagnosticoAntecedentesFragmentArgs build() {
            return new AutodiagnosticoAntecedentesFragmentArgs(this.arguments);
        }

        public int getPasoActual() {
            return ((Integer) this.arguments.get("pasoActual")).intValue();
        }

        public Builder setPasoActual(int i) {
            this.arguments.put("pasoActual", Integer.valueOf(i));
            return this;
        }

        public Builder() {
            this.arguments = new HashMap();
        }
    }

    public static AutodiagnosticoAntecedentesFragmentArgs fromBundle(Bundle bundle) {
        AutodiagnosticoAntecedentesFragmentArgs autodiagnosticoAntecedentesFragmentArgs = new AutodiagnosticoAntecedentesFragmentArgs();
        bundle.setClassLoader(AutodiagnosticoAntecedentesFragmentArgs.class.getClassLoader());
        String str = "pasoActual";
        if (bundle.containsKey(str)) {
            autodiagnosticoAntecedentesFragmentArgs.arguments.put(str, Integer.valueOf(bundle.getInt(str)));
        } else {
            autodiagnosticoAntecedentesFragmentArgs.arguments.put(str, Integer.valueOf(3));
        }
        return autodiagnosticoAntecedentesFragmentArgs;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AutodiagnosticoAntecedentesFragmentArgs.class != obj.getClass()) {
            return false;
        }
        AutodiagnosticoAntecedentesFragmentArgs autodiagnosticoAntecedentesFragmentArgs = (AutodiagnosticoAntecedentesFragmentArgs) obj;
        String str = "pasoActual";
        return this.arguments.containsKey(str) == autodiagnosticoAntecedentesFragmentArgs.arguments.containsKey(str) && getPasoActual() == autodiagnosticoAntecedentesFragmentArgs.getPasoActual();
    }

    public int getPasoActual() {
        return ((Integer) this.arguments.get("pasoActual")).intValue();
    }

    public int hashCode() {
        return getPasoActual() + 31;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        String str = "pasoActual";
        if (this.arguments.containsKey(str)) {
            bundle.putInt(str, ((Integer) this.arguments.get(str)).intValue());
        } else {
            bundle.putInt(str, 3);
        }
        return bundle;
    }

    public String toString() {
        StringBuilder a2 = a.a("AutodiagnosticoAntecedentesFragmentArgs{pasoActual=");
        a2.append(getPasoActual());
        a2.append("}");
        return a2.toString();
    }

    public AutodiagnosticoAntecedentesFragmentArgs() {
        this.arguments = new HashMap();
    }

    public AutodiagnosticoAntecedentesFragmentArgs(HashMap hashMap) {
        HashMap hashMap2 = new HashMap();
        this.arguments = hashMap2;
        hashMap2.putAll(hashMap);
    }
}
