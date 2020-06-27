package com.globant.pasaportesanitario.flujos.autodiagnostico.resultado;

import a.a.a.b.a;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import ar.gob.coronavirus.R;
import b.o.a0;
import b.o.r;
import b.o.y;
import b.o.z.c;
import b.o.z.e;
import com.globant.pasaportesanitario.flujos.BaseActivity;
import com.globant.pasaportesanitario.flujos.autodiagnostico.AutoevaluacionViewModelFactory;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;

@Instrumented
public class PuedeCircularFragment extends Fragment implements TraceFieldInterface {
    public Trace _nr_trace;
    public Dialog loaderDialog;
    public AutodiagnosticoResultadoViewModel viewModel;

    public static PuedeCircularFragment newInstance() {
        return new PuedeCircularFragment();
    }

    private void setViewsValues() {
        this.viewModel.nombreUsuario.a(getViewLifecycleOwner(), new r<String>() {
            public void onChanged(String str) {
                String string = PuedeCircularFragment.this.getString(R.string.msg_no_tiene_sintomas, str);
                ((TextView) PuedeCircularFragment.this.getView().findViewById(R.id.txtPuedeCircularDiagnostico)).setText(a.a(string, str.length() + 2, string.length(), a.a((Context) PuedeCircularFragment.this.requireActivity(), (int) R.font.roboto_bold)));
                PuedeCircularFragment.this.loaderDialog.dismiss();
            }
        });
        this.viewModel.obtenerEventoDeDialogo().a(getViewLifecycleOwner(), new r<c.c.a.e.e.a<Boolean>>() {
            public void onChanged(c.c.a.e.e.a<Boolean> aVar) {
                if (aVar.a() != null && !((Boolean) aVar.f3123a).booleanValue()) {
                    PuedeCircularFragment.this.loaderDialog.dismiss();
                }
            }
        });
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        String str = "PuedeCircularFragment#onCreateView";
        try {
            TraceMachine.enterMethod(this._nr_trace, str, null);
        } catch (NoSuchFieldError unused) {
            TraceMachine.enterMethod(null, str, null);
        }
        View inflate = layoutInflater.inflate(R.layout.fragment_puede_circular, viewGroup, false);
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
        AutoevaluacionViewModelFactory autoevaluacionViewModelFactory = new AutoevaluacionViewModelFactory((BaseActivity) requireActivity());
        a0 viewModelStore = requireActivity().getViewModelStore();
        Class<AutodiagnosticoResultadoViewModel> cls = AutodiagnosticoResultadoViewModel.class;
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            String b2 = c.a.a.a.a.b("androidx.lifecycle.ViewModelProvider.DefaultKey:", canonicalName);
            y yVar2 = (y) viewModelStore.f1739a.get(b2);
            if (!cls.isInstance(yVar2)) {
                if (autoevaluacionViewModelFactory instanceof c) {
                    yVar = ((c) autoevaluacionViewModelFactory).a(b2, cls);
                } else {
                    yVar = autoevaluacionViewModelFactory.create(cls);
                }
                yVar2 = yVar;
                y yVar3 = (y) viewModelStore.f1739a.put(b2, yVar2);
                if (yVar3 != null) {
                    yVar3.onCleared();
                }
            } else if (autoevaluacionViewModelFactory instanceof e) {
                ((e) autoevaluacionViewModelFactory).a(yVar2);
            }
            this.viewModel = (AutodiagnosticoResultadoViewModel) yVar2;
            b.m.d.c activity = getActivity();
            getActivity().getLayoutInflater();
            Dialog a2 = a.a((Context) activity);
            this.loaderDialog = a2;
            a2.show();
            setViewsValues();
            return;
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }
}
