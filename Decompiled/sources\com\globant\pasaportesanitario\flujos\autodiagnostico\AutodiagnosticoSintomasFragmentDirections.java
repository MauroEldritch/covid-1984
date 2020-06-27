package com.globant.pasaportesanitario.flujos.autodiagnostico;

import android.os.Bundle;
import ar.gob.coronavirus.R;
import b.r.j;
import c.a.a.a.a;
import java.util.HashMap;

public class AutodiagnosticoSintomasFragmentDirections {

    public static class ActionAutodiagnosticoSintomasFragmentToAutodiagnosticoAntecedentesFragment implements j {
        public final HashMap arguments;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || ActionAutodiagnosticoSintomasFragmentToAutodiagnosticoAntecedentesFragment.class != obj.getClass()) {
                return false;
            }
            ActionAutodiagnosticoSintomasFragmentToAutodiagnosticoAntecedentesFragment actionAutodiagnosticoSintomasFragmentToAutodiagnosticoAntecedentesFragment = (ActionAutodiagnosticoSintomasFragmentToAutodiagnosticoAntecedentesFragment) obj;
            String str = "pasoActual";
            return this.arguments.containsKey(str) == actionAutodiagnosticoSintomasFragmentToAutodiagnosticoAntecedentesFragment.arguments.containsKey(str) && getPasoActual() == actionAutodiagnosticoSintomasFragmentToAutodiagnosticoAntecedentesFragment.getPasoActual() && getActionId() == actionAutodiagnosticoSintomasFragmentToAutodiagnosticoAntecedentesFragment.getActionId();
        }

        public int getActionId() {
            return R.id.action_autodiagnosticoSintomasFragment_to_autodiagnosticoAntecedentesFragment;
        }

        public Bundle getArguments() {
            Bundle bundle = new Bundle();
            String str = "pasoActual";
            if (this.arguments.containsKey(str)) {
                bundle.putInt(str, ((Integer) this.arguments.get(str)).intValue());
            } else {
                bundle.putInt(str, 3);
            }
            return bundle;
        }

        public int getPasoActual() {
            return ((Integer) this.arguments.get("pasoActual")).intValue();
        }

        public int hashCode() {
            return getActionId() + ((getPasoActual() + 31) * 31);
        }

        public ActionAutodiagnosticoSintomasFragmentToAutodiagnosticoAntecedentesFragment setPasoActual(int i) {
            this.arguments.put("pasoActual", Integer.valueOf(i));
            return this;
        }

        public String toString() {
            StringBuilder a2 = a.a("ActionAutodiagnosticoSintomasFragmentToAutodiagnosticoAntecedentesFragment(actionId=");
            a2.append(getActionId());
            a2.append("){pasoActual=");
            a2.append(getPasoActual());
            a2.append("}");
            return a2.toString();
        }

        public ActionAutodiagnosticoSintomasFragmentToAutodiagnosticoAntecedentesFragment() {
            this.arguments = new HashMap();
        }
    }

    public static ActionAutodiagnosticoSintomasFragmentToAutodiagnosticoAntecedentesFragment actionAutodiagnosticoSintomasFragmentToAutodiagnosticoAntecedentesFragment() {
        return new ActionAutodiagnosticoSintomasFragmentToAutodiagnosticoAntecedentesFragment();
    }
}
