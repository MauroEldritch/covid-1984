package com.globant.pasaportesanitario.flujos.autodiagnostico;

import android.os.Bundle;
import ar.gob.coronavirus.R;
import b.r.j;
import c.a.a.a.a;
import java.util.HashMap;

public class AutodiagnosticoTemperaturaFragmentDirections {

    public static class ActionAutodiagnosticoTemperaturaFragmentToAutodiagnosticoSintomasFragment implements j {
        public final HashMap arguments;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || ActionAutodiagnosticoTemperaturaFragmentToAutodiagnosticoSintomasFragment.class != obj.getClass()) {
                return false;
            }
            ActionAutodiagnosticoTemperaturaFragmentToAutodiagnosticoSintomasFragment actionAutodiagnosticoTemperaturaFragmentToAutodiagnosticoSintomasFragment = (ActionAutodiagnosticoTemperaturaFragmentToAutodiagnosticoSintomasFragment) obj;
            String str = "pasoActual";
            return this.arguments.containsKey(str) == actionAutodiagnosticoTemperaturaFragmentToAutodiagnosticoSintomasFragment.arguments.containsKey(str) && getPasoActual() == actionAutodiagnosticoTemperaturaFragmentToAutodiagnosticoSintomasFragment.getPasoActual() && getActionId() == actionAutodiagnosticoTemperaturaFragmentToAutodiagnosticoSintomasFragment.getActionId();
        }

        public int getActionId() {
            return R.id.action_autodiagnosticoTemperaturaFragment_to_autodiagnosticoSintomasFragment;
        }

        public Bundle getArguments() {
            Bundle bundle = new Bundle();
            String str = "pasoActual";
            if (this.arguments.containsKey(str)) {
                bundle.putInt(str, ((Integer) this.arguments.get(str)).intValue());
            } else {
                bundle.putInt(str, 2);
            }
            return bundle;
        }

        public int getPasoActual() {
            return ((Integer) this.arguments.get("pasoActual")).intValue();
        }

        public int hashCode() {
            return getActionId() + ((getPasoActual() + 31) * 31);
        }

        public ActionAutodiagnosticoTemperaturaFragmentToAutodiagnosticoSintomasFragment setPasoActual(int i) {
            this.arguments.put("pasoActual", Integer.valueOf(i));
            return this;
        }

        public String toString() {
            StringBuilder a2 = a.a("ActionAutodiagnosticoTemperaturaFragmentToAutodiagnosticoSintomasFragment(actionId=");
            a2.append(getActionId());
            a2.append("){pasoActual=");
            a2.append(getPasoActual());
            a2.append("}");
            return a2.toString();
        }

        public ActionAutodiagnosticoTemperaturaFragmentToAutodiagnosticoSintomasFragment() {
            this.arguments = new HashMap();
        }
    }

    public static ActionAutodiagnosticoTemperaturaFragmentToAutodiagnosticoSintomasFragment actionAutodiagnosticoTemperaturaFragmentToAutodiagnosticoSintomasFragment() {
        return new ActionAutodiagnosticoTemperaturaFragmentToAutodiagnosticoSintomasFragment();
    }
}
