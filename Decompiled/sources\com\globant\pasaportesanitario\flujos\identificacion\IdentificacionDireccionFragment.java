package com.globant.pasaportesanitario.flujos.identificacion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import ar.gob.coronavirus.R;
import b.o.z;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;

@Instrumented
public class IdentificacionDireccionFragment extends Fragment implements TraceFieldInterface {
    public Trace _nr_trace;
    public IdentificacionViewModel identificacionViewModel;

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.identificacionViewModel = (IdentificacionViewModel) new z(getActivity()).a(IdentificacionViewModel.class);
        EditText editText = (EditText) getView().findViewById(R.id.et_direccion_identificacion_fragment);
        ((TextView) getView().findViewById(R.id.btn_siguiente_direccion_fragment)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ((IdentificacionActivity) IdentificacionDireccionFragment.this.getActivity()).navegarAIdentificacionDireccionCompletaFragment();
                Toast.makeText(IdentificacionDireccionFragment.this.getContext(), "Guardando Dirección sencilla.", 0).show();
            }
        });
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        String str = "IdentificacionDireccionFragment#onCreateView";
        try {
            TraceMachine.enterMethod(this._nr_trace, str, null);
        } catch (NoSuchFieldError unused) {
            TraceMachine.enterMethod(null, str, null);
        }
        View inflate = layoutInflater.inflate(R.layout.identificacion_pregunta_direccion_fragment, viewGroup, false);
        TraceMachine.exitMethod();
        return inflate;
    }

    public void onStart() {
        super.onStart();
    }

    public void onStop() {
        super.onStop();
    }
}
