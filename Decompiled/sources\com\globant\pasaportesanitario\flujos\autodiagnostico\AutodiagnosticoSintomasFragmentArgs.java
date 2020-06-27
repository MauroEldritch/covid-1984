package com.globant.pasaportesanitario.flujos.autodiagnostico;

import android.os.Bundle;
import c.a.a.a.a;
import java.util.HashMap;

public class AutodiagnosticoSintomasFragmentArgs {
    public final HashMap arguments;

    public static class Builder {
        public final HashMap arguments;

        public Builder(AutodiagnosticoSintomasFragmentArgs autodiagnosticoSintomasFragmentArgs) {
            HashMap hashMap = new HashMap();
            this.arguments = hashMap;
            hashMap.putAll(autodiagnosticoSintomasFragmentArgs.arguments);
        }

        public AutodiagnosticoSintomasFragmentArgs build() {
            return new AutodiagnosticoSintomasFragmentArgs(this.arguments);
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

    public static AutodiagnosticoSintomasFragmentArgs fromBundle(Bundle bundle) {
        AutodiagnosticoSintomasFragmentArgs autodiagnosticoSintomasFragmentArgs = new AutodiagnosticoSintomasFragmentArgs();
        bundle.setClassLoader(AutodiagnosticoSintomasFragmentArgs.class.getClassLoader());
        String str = "pasoActual";
        if (bundle.containsKey(str)) {
            autodiagnosticoSintomasFragmentArgs.arguments.put(str, Integer.valueOf(bundle.getInt(str)));
        } else {
            autodiagnosticoSintomasFragmentArgs.arguments.put(str, Integer.valueOf(2));
        }
        return autodiagnosticoSintomasFragmentArgs;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AutodiagnosticoSintomasFragmentArgs.class != obj.getClass()) {
            return false;
        }
        AutodiagnosticoSintomasFragmentArgs autodiagnosticoSintomasFragmentArgs = (AutodiagnosticoSintomasFragmentArgs) obj;
        String str = "pasoActual";
        return this.arguments.containsKey(str) == autodiagnosticoSintomasFragmentArgs.arguments.containsKey(str) && getPasoActual() == autodiagnosticoSintomasFragmentArgs.getPasoActual();
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
            bundle.putInt(str, 2);
        }
        return bundle;
    }

    public String toString() {
        StringBuilder a2 = a.a("AutodiagnosticoSintomasFragmentArgs{pasoActual=");
        a2.append(getPasoActual());
        a2.append("}");
        return a2.toString();
    }

    public AutodiagnosticoSintomasFragmentArgs() {
        this.arguments = new HashMap();
    }

    public AutodiagnosticoSintomasFragmentArgs(HashMap hashMap) {
        HashMap hashMap2 = new HashMap();
        this.arguments = hashMap2;
        hashMap2.putAll(hashMap);
    }
}
