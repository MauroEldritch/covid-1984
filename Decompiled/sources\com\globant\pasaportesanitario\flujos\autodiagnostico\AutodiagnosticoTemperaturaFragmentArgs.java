package com.globant.pasaportesanitario.flujos.autodiagnostico;

import android.os.Bundle;
import c.a.a.a.a;
import java.util.HashMap;

public class AutodiagnosticoTemperaturaFragmentArgs {
    public final HashMap arguments;

    public static class Builder {
        public final HashMap arguments;

        public Builder(AutodiagnosticoTemperaturaFragmentArgs autodiagnosticoTemperaturaFragmentArgs) {
            HashMap hashMap = new HashMap();
            this.arguments = hashMap;
            hashMap.putAll(autodiagnosticoTemperaturaFragmentArgs.arguments);
        }

        public AutodiagnosticoTemperaturaFragmentArgs build() {
            return new AutodiagnosticoTemperaturaFragmentArgs(this.arguments);
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

    public static AutodiagnosticoTemperaturaFragmentArgs fromBundle(Bundle bundle) {
        AutodiagnosticoTemperaturaFragmentArgs autodiagnosticoTemperaturaFragmentArgs = new AutodiagnosticoTemperaturaFragmentArgs();
        bundle.setClassLoader(AutodiagnosticoTemperaturaFragmentArgs.class.getClassLoader());
        String str = "pasoActual";
        if (bundle.containsKey(str)) {
            autodiagnosticoTemperaturaFragmentArgs.arguments.put(str, Integer.valueOf(bundle.getInt(str)));
        } else {
            autodiagnosticoTemperaturaFragmentArgs.arguments.put(str, Integer.valueOf(1));
        }
        return autodiagnosticoTemperaturaFragmentArgs;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AutodiagnosticoTemperaturaFragmentArgs.class != obj.getClass()) {
            return false;
        }
        AutodiagnosticoTemperaturaFragmentArgs autodiagnosticoTemperaturaFragmentArgs = (AutodiagnosticoTemperaturaFragmentArgs) obj;
        String str = "pasoActual";
        return this.arguments.containsKey(str) == autodiagnosticoTemperaturaFragmentArgs.arguments.containsKey(str) && getPasoActual() == autodiagnosticoTemperaturaFragmentArgs.getPasoActual();
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
            bundle.putInt(str, 1);
        }
        return bundle;
    }

    public String toString() {
        StringBuilder a2 = a.a("AutodiagnosticoTemperaturaFragmentArgs{pasoActual=");
        a2.append(getPasoActual());
        a2.append("}");
        return a2.toString();
    }

    public AutodiagnosticoTemperaturaFragmentArgs() {
        this.arguments = new HashMap();
    }

    public AutodiagnosticoTemperaturaFragmentArgs(HashMap hashMap) {
        HashMap hashMap2 = new HashMap();
        this.arguments = hashMap2;
        hashMap2.putAll(hashMap);
    }
}
