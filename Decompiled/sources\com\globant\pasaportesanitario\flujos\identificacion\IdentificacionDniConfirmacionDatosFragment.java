package com.globant.pasaportesanitario.flujos.identificacion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import ar.gob.coronavirus.R;
import b.o.r;
import b.o.z;
import com.globant.pasaportesanitario.data.local.modelo.UsuarioBD;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

@Instrumented
public class IdentificacionDniConfirmacionDatosFragment extends Fragment implements TraceFieldInterface {
    public Trace _nr_trace;
    public IdentificacionViewModel identificacionViewModel;
    public TextView tvDni;
    public TextView tvFechaNacimiento;
    public TextView tvNombreCompleto;
    public TextView tvSexo;

    private void iniciarViews() {
        this.tvNombreCompleto = (TextView) getView().findViewById(R.id.tv_nombre_completo_confirmacion_identificacion_fragment);
        this.tvDni = (TextView) getView().findViewById(R.id.tv_dni_response_identificacion_fragment);
        this.tvFechaNacimiento = (TextView) getView().findViewById(R.id.tv_fecha_nacimiento_response_identificacion_fragment);
        this.tvSexo = (TextView) getView().findViewById(R.id.tv_sexo_response_identificacion_fragment);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        iniciarViews();
        IdentificacionViewModel identificacionViewModel2 = (IdentificacionViewModel) new z(getActivity()).a(IdentificacionViewModel.class);
        this.identificacionViewModel = identificacionViewModel2;
        identificacionViewModel2.obtenerUsuario();
        this.identificacionViewModel.obtenerUsuarioliveData().a(getViewLifecycleOwner(), new r<UsuarioBD>() {
            public void onChanged(UsuarioBD usuarioBD) {
                String str;
                try {
                    str = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(usuarioBD.fechaNacimiento));
                } catch (ParseException unused) {
                    str = "";
                }
                IdentificacionDniConfirmacionDatosFragment.this.tvNombreCompleto.setText(String.format("%s, %s", new Object[]{usuarioBD.nombres, usuarioBD.apellidos}));
                String str2 = "%s";
                IdentificacionDniConfirmacionDatosFragment.this.tvDni.setText(String.format(str2, new Object[]{usuarioBD.dni}));
                IdentificacionDniConfirmacionDatosFragment.this.tvFechaNacimiento.setText(String.format(str2, new Object[]{str}));
                if (usuarioBD.sexo.equals("F")) {
                    IdentificacionDniConfirmacionDatosFragment.this.tvSexo.setText(String.format(str2, new Object[]{IdentificacionDniConfirmacionDatosFragment.this.getString(R.string.femenino)}));
                    return;
                }
                IdentificacionDniConfirmacionDatosFragment.this.tvSexo.setText(String.format(str2, new Object[]{IdentificacionDniConfirmacionDatosFragment.this.getString(R.string.masculio)}));
            }
        });
        ((TextView) getActivity().findViewById(R.id.btn_siguiente_confirmacion_datos_identificacion_fragment)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ((IdentificacionActivity) IdentificacionDniConfirmacionDatosFragment.this.getActivity()).navegarAIdentificacionTelefonoFragment();
            }
        });
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        String str = "IdentificacionDniConfirmacionDatosFragment#onCreateView";
        try {
            TraceMachine.enterMethod(this._nr_trace, str, null);
        } catch (NoSuchFieldError unused) {
            TraceMachine.enterMethod(null, str, null);
        }
        View inflate = layoutInflater.inflate(R.layout.identificacion_pregunta_confirmacion_datos_fragment, viewGroup, false);
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
