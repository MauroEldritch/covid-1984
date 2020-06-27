package com.globant.pasaportesanitario.flujos.autodiagnostico.resultado;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import ar.gob.coronavirus.R;
import b.o.a0;
import b.o.r;
import b.o.y;
import b.o.z.c;
import b.o.z.e;
import c.c.a.e.e.a;
import c.c.a.e.f.b;
import com.globant.pasaportesanitario.data.NombreEstado;
import com.globant.pasaportesanitario.data.local.modelo.DatosCoepBD;
import com.globant.pasaportesanitario.data.local.modelo.UsuarioBD;
import com.globant.pasaportesanitario.flujos.BaseActivity;
import com.globant.pasaportesanitario.flujos.pantallaprincipal.PantallaPrincipalViewModel;
import com.globant.pasaportesanitario.flujos.pantallaprincipal.PantallaPrincipalViewModelFactory;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;

@Instrumented
public class NoPuedeCircularFragment extends Fragment implements TraceFieldInterface {
    public Trace _nr_trace;
    public CardView cardView;
    public Dialog loaderDialog;
    public PantallaPrincipalViewModel viewModel;

    private void iniciarCardView() {
        this.cardView = (CardView) getView().findViewById(R.id.cv_evaluacion_no_puede_circular);
    }

    public static NoPuedeCircularFragment newInstance() {
        return new NoPuedeCircularFragment();
    }

    private void setViewsValues() {
        this.viewModel.obtenerEventoDeDialogo().a(getViewLifecycleOwner(), new r<a<Boolean>>() {
            public void onChanged(a<Boolean> aVar) {
                if (aVar.a() != null && !((Boolean) aVar.f3123a).booleanValue()) {
                    NoPuedeCircularFragment.this.loaderDialog.dismiss();
                }
            }
        });
        this.viewModel.obtenerUltimoEstadoLiveData().a(getViewLifecycleOwner(), new r<UsuarioBD>() {
            public void onChanged(UsuarioBD usuarioBD) {
                String string = NoPuedeCircularFragment.this.getString(R.string.msg_sintomas_compatibles_y_reportados, usuarioBD.nombres);
                if (usuarioBD.estadoActual.nombreEstado.equals(NombreEstado.DERIVADO_A_SALUD_LOCAL)) {
                    ((TextView) NoPuedeCircularFragment.this.getView().findViewById(R.id.txtNoPuedeCircularDiagnostico)).setText(a.a.a.b.a.a(string, usuarioBD.nombres.length() + 4, string.length(), a.a.a.b.a.a((Context) NoPuedeCircularFragment.this.requireActivity(), (int) R.font.roboto_bold)));
                    TextView textView = (TextView) NoPuedeCircularFragment.this.getView().findViewById(R.id.txtNumeroLlamar);
                    NoPuedeCircularFragment noPuedeCircularFragment = NoPuedeCircularFragment.this;
                    DatosCoepBD datosCoepBD = usuarioBD.estadoActual.datosCoepBD;
                    textView.setText(noPuedeCircularFragment.getString(R.string.provinvia_y_telefono, datosCoepBD.coep, datosCoepBD.informacionDeContacto));
                } else if (usuarioBD.estadoActual.nombreEstado.equals(NombreEstado.INFECTADO)) {
                    NoPuedeCircularFragment.this.getString(R.string.msg_migrante, usuarioBD.nombres);
                    ((TextView) NoPuedeCircularFragment.this.getView().findViewById(R.id.txtPuedeCircularTitle)).setText(NoPuedeCircularFragment.this.getString(R.string.covid_positivo));
                    Spanned a2 = a.a.a.b.a.a(NoPuedeCircularFragment.this.requireContext(), NoPuedeCircularFragment.this.getString(R.string.texto_resultado_positivo_dos), (int) R.font.roboto_bold);
                    ((TextView) NoPuedeCircularFragment.this.getView().findViewById(R.id.txtNoPuedeCircularDiagnostico)).setText(b.a(NoPuedeCircularFragment.this.getActivity().getString(R.string.texto_resultado_positivo_uno), usuarioBD.nombres, a2));
                    ((TextView) NoPuedeCircularFragment.this.getView().findViewById(R.id.txtPuedeCircularDiagnostico2)).setVisibility(8);
                    ((TextView) NoPuedeCircularFragment.this.getView().findViewById(R.id.txtIntruccionesLlamar)).setVisibility(8);
                    ((TextView) NoPuedeCircularFragment.this.getView().findViewById(R.id.txtNumeroLlamar)).setText(NoPuedeCircularFragment.this.getString(R.string.msg_numero_llamar_migrate));
                }
                NoPuedeCircularFragment.this.loaderDialog.dismiss();
                NoPuedeCircularFragment.this.cardView.setVisibility(0);
            }
        });
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        String str = "NoPuedeCircularFragment#onCreateView";
        try {
            TraceMachine.enterMethod(this._nr_trace, str, null);
        } catch (NoSuchFieldError unused) {
            TraceMachine.enterMethod(null, str, null);
        }
        View inflate = layoutInflater.inflate(R.layout.fragment_no_puede_circular, viewGroup, false);
        TraceMachine.exitMethod();
        return inflate;
    }

    public void onStart() {
        super.onStart();
    }

    public void onStop() {
        super.onStop();
    }

    public void onViewCreated(View view, Bundle bundle) {
        y yVar;
        super.onViewCreated(view, bundle);
        iniciarCardView();
        PantallaPrincipalViewModelFactory pantallaPrincipalViewModelFactory = new PantallaPrincipalViewModelFactory((BaseActivity) requireActivity());
        a0 viewModelStore = requireActivity().getViewModelStore();
        Class<PantallaPrincipalViewModel> cls = PantallaPrincipalViewModel.class;
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            String b2 = c.a.a.a.a.b("androidx.lifecycle.ViewModelProvider.DefaultKey:", canonicalName);
            y yVar2 = (y) viewModelStore.f1739a.get(b2);
            if (!cls.isInstance(yVar2)) {
                if (pantallaPrincipalViewModelFactory instanceof c) {
                    yVar = ((c) pantallaPrincipalViewModelFactory).a(b2, cls);
                } else {
                    yVar = pantallaPrincipalViewModelFactory.create(cls);
                }
                yVar2 = yVar;
                y yVar3 = (y) viewModelStore.f1739a.put(b2, yVar2);
                if (yVar3 != null) {
                    yVar3.onCleared();
                }
            } else if (pantallaPrincipalViewModelFactory instanceof e) {
                ((e) pantallaPrincipalViewModelFactory).a(yVar2);
            }
            PantallaPrincipalViewModel pantallaPrincipalViewModel = (PantallaPrincipalViewModel) yVar2;
            this.viewModel = pantallaPrincipalViewModel;
            pantallaPrincipalViewModel.obtenerUsuarioDeLaBD();
            b.m.d.c activity = getActivity();
            getActivity().getLayoutInflater();
            Dialog a2 = a.a.a.b.a.a((Context) activity);
            this.loaderDialog = a2;
            a2.show();
            setViewsValues();
            return;
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }
}
