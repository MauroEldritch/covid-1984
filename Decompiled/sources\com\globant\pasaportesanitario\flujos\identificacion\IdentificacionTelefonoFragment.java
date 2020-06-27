package com.globant.pasaportesanitario.flujos.identificacion;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import ar.gob.coronavirus.R;
import b.o.z;
import c.a.a.a.a;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;

@Instrumented
public class IdentificacionTelefonoFragment extends Fragment implements TraceFieldInterface {
    public static final String PREFIJO_TELEFONO = "+54";
    public static final String REGEX_TELEFONO = "[0-9]{6,13}";
    public Trace _nr_trace;
    public TextView botonSiguiente;
    public IdentificacionViewModel identificacionViewModel;
    public TextInputEditText telefonoTie;
    public TextInputLayout telefonoTil;

    private void iniciarListeners() {
        this.botonSiguiente.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                String obj = IdentificacionTelefonoFragment.this.telefonoTie.getText().toString();
                if (IdentificacionTelefonoFragment.this.validarDatos(obj)) {
                    IdentificacionTelefonoFragment.this.identificacionViewModel.nroTelefono = a.b(IdentificacionTelefonoFragment.PREFIJO_TELEFONO, obj);
                    ((IdentificacionActivity) IdentificacionTelefonoFragment.this.getActivity()).navegarAIdentificacionDireccionCompletaFragment();
                }
            }
        });
    }

    private void iniciarViews() {
        this.telefonoTil = (TextInputLayout) getView().findViewById(R.id.til_telefono_identificacion_fragment);
        this.telefonoTie = (TextInputEditText) getView().findViewById(R.id.tie_telefono_identificacion_fragment);
        this.botonSiguiente = (TextView) getView().findViewById(R.id.btn_siguiente_telefono_fragment);
        this.telefonoTie.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
                IdentificacionTelefonoFragment.this.validarDatos(IdentificacionTelefonoFragment.this.telefonoTie.getText().toString());
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean validarDatos(String str) {
        if (str.matches(REGEX_TELEFONO)) {
            this.telefonoTil.setError(null);
            this.botonSiguiente.setEnabled(true);
            return true;
        }
        this.telefonoTil.setError(getString(R.string.error_numero_telefono));
        this.botonSiguiente.setEnabled(false);
        return false;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        iniciarViews();
        this.identificacionViewModel = (IdentificacionViewModel) new z(getActivity()).a(IdentificacionViewModel.class);
        iniciarListeners();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        String str = "IdentificacionTelefonoFragment#onCreateView";
        try {
            TraceMachine.enterMethod(this._nr_trace, str, null);
        } catch (NoSuchFieldError unused) {
            TraceMachine.enterMethod(null, str, null);
        }
        View inflate = layoutInflater.inflate(R.layout.identificacion_pregunta_telefono_fragment, viewGroup, false);
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
