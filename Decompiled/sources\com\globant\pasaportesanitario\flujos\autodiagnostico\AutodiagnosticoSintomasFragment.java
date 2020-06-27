package com.globant.pasaportesanitario.flujos.autodiagnostico;

import a.a.a.b.a;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import androidx.fragment.app.Fragment;
import ar.gob.coronavirus.R;
import b.o.a0;
import b.o.y;
import b.o.z.c;
import b.o.z.e;
import b.r.j;
import c.c.a.b.g;
import c.d.a.b.e0.o;
import com.globant.pasaportesanitario.data.remoto.modelo_autodiagnostico.SintomasRemoto;
import com.globant.pasaportesanitario.flujos.BaseActivity;
import com.google.android.material.snackbar.Snackbar;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;

@Instrumented
public class AutodiagnosticoSintomasFragment extends Fragment implements TraceFieldInterface {
    public Trace _nr_trace;
    public g binding = null;
    public AutodiagnosticoViewModel viewModel;

    private void iniciarInterfaz() {
        this.binding.t.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (AutodiagnosticoSintomasFragment.this.binding.x.getCheckedRadioButtonId() == -1 || AutodiagnosticoSintomasFragment.this.binding.u.getCheckedRadioButtonId() == -1 || AutodiagnosticoSintomasFragment.this.binding.w.getCheckedRadioButtonId() == -1 || AutodiagnosticoSintomasFragment.this.binding.v.getCheckedRadioButtonId() == -1) {
                    AutodiagnosticoSintomasFragment autodiagnosticoSintomasFragment = AutodiagnosticoSintomasFragment.this;
                    autodiagnosticoSintomasFragment.validarRadioGroup(autodiagnosticoSintomasFragment.binding.w);
                    AutodiagnosticoSintomasFragment autodiagnosticoSintomasFragment2 = AutodiagnosticoSintomasFragment.this;
                    autodiagnosticoSintomasFragment2.validarRadioGroup(autodiagnosticoSintomasFragment2.binding.v);
                    AutodiagnosticoSintomasFragment autodiagnosticoSintomasFragment3 = AutodiagnosticoSintomasFragment.this;
                    autodiagnosticoSintomasFragment3.validarRadioGroup(autodiagnosticoSintomasFragment3.binding.x);
                    AutodiagnosticoSintomasFragment autodiagnosticoSintomasFragment4 = AutodiagnosticoSintomasFragment.this;
                    autodiagnosticoSintomasFragment4.validarRadioGroup(autodiagnosticoSintomasFragment4.binding.u);
                    Snackbar a2 = Snackbar.a(AutodiagnosticoSintomasFragment.this.requireView(), R.string.seleccionar_sintoma, -1);
                    o b2 = o.b();
                    int i = a2.f4776e;
                    int i2 = -2;
                    if (i != -2) {
                        if (VERSION.SDK_INT >= 29) {
                            i = a2.q.getRecommendedTimeoutMillis(i, 3);
                        }
                        i2 = i;
                    }
                    b2.a(i2, a2.n);
                    return;
                }
                a.a(view).a((j) AutodiagnosticoSintomasFragmentDirections.actionAutodiagnosticoSintomasFragmentToAutodiagnosticoAntecedentesFragment());
            }
        });
        this.binding.x.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                AutodiagnosticoSintomasFragment autodiagnosticoSintomasFragment = AutodiagnosticoSintomasFragment.this;
                autodiagnosticoSintomasFragment.validarRadioGroup(autodiagnosticoSintomasFragment.binding.x);
                switch (i) {
                    case R.id.radio_tos_no /*2131296614*/:
                        AutodiagnosticoSintomasFragment.this.viewModel.agregarSintoma(AutodiagnosticoSintomasFragment.this.obtenerNuevoSintoma(TipoSintoma.S_TDG, R.string.sintoma_uno_label, false));
                        return;
                    case R.id.radio_tos_si /*2131296615*/:
                        AutodiagnosticoSintomasFragment.this.viewModel.agregarSintoma(AutodiagnosticoSintomasFragment.this.obtenerNuevoSintoma(TipoSintoma.S_TDG, R.string.sintoma_uno_label, true));
                        return;
                    default:
                        return;
                }
            }
        });
        this.binding.u.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                AutodiagnosticoSintomasFragment autodiagnosticoSintomasFragment = AutodiagnosticoSintomasFragment.this;
                autodiagnosticoSintomasFragment.validarRadioGroup(autodiagnosticoSintomasFragment.binding.u);
                switch (i) {
                    case R.id.radio_respiratoria_no /*2131296612*/:
                        AutodiagnosticoSintomasFragment.this.viewModel.agregarSintoma(AutodiagnosticoSintomasFragment.this.obtenerNuevoSintoma(TipoSintoma.S_DRE, R.string.sintoma_dos_label, false));
                        return;
                    case R.id.radio_respiratoria_si /*2131296613*/:
                        AutodiagnosticoSintomasFragment.this.viewModel.agregarSintoma(AutodiagnosticoSintomasFragment.this.obtenerNuevoSintoma(TipoSintoma.S_DRE, R.string.sintoma_dos_label, true));
                        return;
                    default:
                        return;
                }
            }
        });
        this.binding.w.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                AutodiagnosticoSintomasFragment autodiagnosticoSintomasFragment = AutodiagnosticoSintomasFragment.this;
                autodiagnosticoSintomasFragment.validarRadioGroup(autodiagnosticoSintomasFragment.binding.w);
                switch (i) {
                    case R.id.radio_perdida_olfato_no /*2131296610*/:
                        AutodiagnosticoSintomasFragment.this.viewModel.agregarSintoma(AutodiagnosticoSintomasFragment.this.obtenerNuevoSintoma(TipoSintoma.S_PDO, R.string.sintoma_perdida_olfato, false));
                        return;
                    case R.id.radio_perdida_olfato_si /*2131296611*/:
                        AutodiagnosticoSintomasFragment.this.viewModel.agregarSintoma(AutodiagnosticoSintomasFragment.this.obtenerNuevoSintoma(TipoSintoma.S_PDO, R.string.sintoma_perdida_olfato, true));
                        return;
                    default:
                        return;
                }
            }
        });
        this.binding.v.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                AutodiagnosticoSintomasFragment autodiagnosticoSintomasFragment = AutodiagnosticoSintomasFragment.this;
                autodiagnosticoSintomasFragment.validarRadioGroup(autodiagnosticoSintomasFragment.binding.v);
                switch (i) {
                    case R.id.radio_perdida_gusto_no /*2131296608*/:
                        AutodiagnosticoSintomasFragment.this.viewModel.agregarSintoma(AutodiagnosticoSintomasFragment.this.obtenerNuevoSintoma(TipoSintoma.S_PDG, R.string.sintoma_perdida_olfato, false));
                        return;
                    case R.id.radio_perdida_gusto_si /*2131296609*/:
                        AutodiagnosticoSintomasFragment.this.viewModel.agregarSintoma(AutodiagnosticoSintomasFragment.this.obtenerNuevoSintoma(TipoSintoma.S_PDG, R.string.sintoma_perdida_olfato, true));
                        return;
                    default:
                        return;
                }
            }
        });
    }

    private void iniciarValoresDeVistas() {
        if (this.viewModel.obtenerSintoma(TipoSintoma.S_PDO) != null) {
            if (this.viewModel.obtenerSintoma(TipoSintoma.S_PDO).isValor()) {
                this.binding.w.check(R.id.radio_perdida_olfato_si);
            } else {
                this.binding.w.check(R.id.radio_perdida_olfato_no);
            }
        }
        if (this.viewModel.obtenerSintoma(TipoSintoma.S_PDG) != null) {
            if (this.viewModel.obtenerSintoma(TipoSintoma.S_PDG).isValor()) {
                this.binding.v.check(R.id.radio_perdida_gusto_si);
            } else {
                this.binding.v.check(R.id.radio_perdida_gusto_no);
            }
        }
        SintomasRemoto obtenerSintoma = this.viewModel.obtenerSintoma(TipoSintoma.S_TDG);
        if (obtenerSintoma != null) {
            if (obtenerSintoma.isValor()) {
                this.binding.x.check(R.id.radio_tos_si);
            } else {
                this.binding.x.check(R.id.radio_tos_no);
            }
        }
        if (this.viewModel.obtenerSintoma(TipoSintoma.S_DRE) == null) {
            return;
        }
        if (this.viewModel.obtenerSintoma(TipoSintoma.S_DRE).isValor()) {
            this.binding.u.check(R.id.radio_respiratoria_si);
        } else {
            this.binding.u.check(R.id.radio_respiratoria_no);
        }
    }

    /* access modifiers changed from: private */
    public SintomasRemoto obtenerNuevoSintoma(TipoSintoma tipoSintoma, int i, boolean z) {
        return new SintomasRemoto(tipoSintoma.value, getString(i), z);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        y yVar;
        String str = "AutodiagnosticoSintomasFragment#onCreateView";
        try {
            TraceMachine.enterMethod(this._nr_trace, str, null);
        } catch (NoSuchFieldError unused) {
            TraceMachine.enterMethod(null, str, null);
        }
        this.binding = g.a(layoutInflater, viewGroup, false);
        AutoevaluacionViewModelFactory autoevaluacionViewModelFactory = new AutoevaluacionViewModelFactory((BaseActivity) requireActivity());
        a0 viewModelStore = requireActivity().getViewModelStore();
        Class<AutodiagnosticoViewModel> cls = AutodiagnosticoViewModel.class;
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
            this.viewModel = (AutodiagnosticoViewModel) yVar2;
            this.binding.a(getViewLifecycleOwner());
            iniciarInterfaz();
            iniciarValoresDeVistas();
            View view = this.binding.f265f;
            TraceMachine.exitMethod();
            return view;
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    public void onStart() {
        super.onStart();
    }

    public void onStop() {
        super.onStop();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (getArguments() != null) {
            this.viewModel.pasoActual.a(Integer.valueOf(AutodiagnosticoSintomasFragmentArgs.fromBundle(getArguments()).getPasoActual()));
        }
    }

    public void validarRadioGroup(RadioGroup radioGroup) {
        int childCount = radioGroup.getChildCount() - 1;
        if (radioGroup.getCheckedRadioButtonId() == -1) {
            ((RadioButton) radioGroup.getChildAt(childCount)).setError("Error");
        } else {
            ((RadioButton) radioGroup.getChildAt(childCount)).setError(null);
        }
    }
}
