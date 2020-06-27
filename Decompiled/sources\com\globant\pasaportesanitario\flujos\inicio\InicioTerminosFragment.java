package com.globant.pasaportesanitario.flujos.inicio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import ar.gob.coronavirus.R;
import com.google.android.material.textview.MaterialTextView;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;

@Instrumented
public class InicioTerminosFragment extends DialogFragment implements TraceFieldInterface {
    public Trace _nr_trace;
    public ImageView backButton;
    public MaterialTextView buttonEntendido;
    public TextView textoTerminos;

    private void configurarPantallaCompleta() {
        setStyle(0, 16973838);
    }

    public void onCreate(Bundle bundle) {
        String str = "InicioTerminosFragment#onCreate";
        TraceMachine.startTracing("InicioTerminosFragment");
        try {
            TraceMachine.enterMethod(this._nr_trace, str, null);
        } catch (NoSuchFieldError unused) {
            TraceMachine.enterMethod(null, str, null);
        }
        super.onCreate(bundle);
        configurarPantallaCompleta();
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        String str = "InicioTerminosFragment#onCreateView";
        try {
            TraceMachine.enterMethod(this._nr_trace, str, null);
        } catch (NoSuchFieldError unused) {
            TraceMachine.enterMethod(null, str, null);
        }
        View inflate = layoutInflater.inflate(R.layout.inicio_terminos_fragment, viewGroup, false);
        TextView textView = (TextView) inflate.findViewById(R.id.textoTerminos);
        this.textoTerminos = textView;
        textView.setText(getText(R.string.texto_terminos));
        this.buttonEntendido = (MaterialTextView) inflate.findViewById(R.id.btn_aceptar);
        this.backButton = (ImageView) inflate.findViewById(R.id.imgRowBack);
        this.buttonEntendido.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                InicioTerminosFragment.this.dismiss();
            }
        });
        this.backButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                InicioTerminosFragment.this.dismiss();
            }
        });
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
