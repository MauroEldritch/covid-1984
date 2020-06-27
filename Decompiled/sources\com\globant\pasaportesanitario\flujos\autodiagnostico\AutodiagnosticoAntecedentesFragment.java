package com.globant.pasaportesanitario.flujos.autodiagnostico;

import a.a.a.b.a;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import androidx.appcompat.app.AlertController;
import androidx.fragment.app.Fragment;
import ar.gob.coronavirus.R;
import b.b.k.h;
import b.o.a0;
import b.o.y;
import b.o.z;
import b.o.z.c;
import c.c.a.b.e;
import c.d.a.b.v.b;
import com.globant.pasaportesanitario.data.remoto.modelo_autodiagnostico.AntecedentesRemoto;
import com.globant.pasaportesanitario.flujos.BaseActivity;
import com.globant.pasaportesanitario.utils.dialogs.PantallaCompletaDialog;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;

@Instrumented
public class AutodiagnosticoAntecedentesFragment extends Fragment implements ClickHandler, TraceFieldInterface {
    public Trace _nr_trace;
    public e binding = null;
    public h confirmacionDialogo = null;
    public h internetDialogo = null;
    public AutodiagnosticoViewModel viewModel;

    /* access modifiers changed from: private */
    public void crearDialogo() {
        b bVar = new b(requireContext(), R.style.AlertDialogTheme);
        AlertController.b bVar2 = bVar.f521a;
        bVar2.f85f = bVar2.f80a.getText(R.string.autodiagnostico_confirmacion);
        AlertController.b bVar3 = bVar.f521a;
        bVar3.f87h = bVar3.f80a.getText(R.string.autodiagnostico_confirmacion_message);
        AnonymousClass3 r1 = new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (a.c(AutodiagnosticoAntecedentesFragment.this.getContext())) {
                    AutodiagnosticoAntecedentesFragment.this.viewModel.enviarResultadosAutoevaluacion();
                } else {
                    AutodiagnosticoAntecedentesFragment.this.crearDialogoInternet();
                }
            }
        };
        AlertController.b bVar4 = bVar.f521a;
        bVar4.i = bVar4.f80a.getText(R.string.enviar);
        bVar.f521a.j = r1;
        AnonymousClass2 r12 = new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        };
        AlertController.b bVar5 = bVar.f521a;
        bVar5.k = bVar5.f80a.getText(R.string.cancelar);
        bVar.f521a.l = r12;
        this.confirmacionDialogo = bVar.a();
    }

    /* access modifiers changed from: private */
    public void crearDialogoInternet() {
        final PantallaCompletaDialog a2 = PantallaCompletaDialog.a(getString(R.string.hubo_error), getString(R.string.no_hay_internet), getString(R.string.cerrar).toUpperCase(), R.drawable.ic_error);
        a2.f4589b = new PantallaCompletaDialog.a() {
            public void onClick(View view) {
                a2.dismiss();
            }
        };
        a2.show(getParentFragmentManager(), "TAG");
    }

    private void iniciarAntecedentes() {
        if (this.viewModel.noTieneAntecedentes()) {
            this.viewModel.agregarAntecedente(obtenerNuevoAntecedente(TipoAntecedente.A_EMB, R.string.antecedentes_embarazo, false));
            this.viewModel.agregarAntecedente(obtenerNuevoAntecedente(TipoAntecedente.A_CAN, R.string.antecedentes_cancer, false));
            this.viewModel.agregarAntecedente(obtenerNuevoAntecedente(TipoAntecedente.A_DIA, R.string.antecedentes_diabetes, false));
            this.viewModel.agregarAntecedente(obtenerNuevoAntecedente(TipoAntecedente.A_HEP, R.string.antecedentes_enfermedad_hepatica, false));
            this.viewModel.agregarAntecedente(obtenerNuevoAntecedente(TipoAntecedente.A_REN, R.string.antecedentes_enfermedad_renal, false));
            this.viewModel.agregarAntecedente(obtenerNuevoAntecedente(TipoAntecedente.A_RES, R.string.antecedentes_enfermedad_respiratoria, false));
            this.viewModel.agregarAntecedente(obtenerNuevoAntecedente(TipoAntecedente.A_CAR, R.string.antecedentes_enfermedad_cardiaca, false));
        }
    }

    private void iniciarInterfaz() {
        this.binding.t.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (AutodiagnosticoAntecedentesFragment.this.confirmacionDialogo == null) {
                    AutodiagnosticoAntecedentesFragment.this.crearDialogo();
                }
                AutodiagnosticoAntecedentesFragment.this.confirmacionDialogo.show();
            }
        });
    }

    private void iniciarValoresDeVistas() {
        MaterialCheckBox materialCheckBox = this.binding.w;
        materialCheckBox.setChecked(obtenerAntecedenteExistente(materialCheckBox).booleanValue());
        MaterialCheckBox materialCheckBox2 = this.binding.u;
        materialCheckBox2.setChecked(obtenerAntecedenteExistente(materialCheckBox2).booleanValue());
        MaterialCheckBox materialCheckBox3 = this.binding.v;
        materialCheckBox3.setChecked(obtenerAntecedenteExistente(materialCheckBox3).booleanValue());
        MaterialCheckBox materialCheckBox4 = this.binding.y;
        materialCheckBox4.setChecked(obtenerAntecedenteExistente(materialCheckBox4).booleanValue());
        MaterialCheckBox materialCheckBox5 = this.binding.z;
        materialCheckBox5.setChecked(obtenerAntecedenteExistente(materialCheckBox5).booleanValue());
        MaterialCheckBox materialCheckBox6 = this.binding.A;
        materialCheckBox6.setChecked(obtenerAntecedenteExistente(materialCheckBox6).booleanValue());
        MaterialCheckBox materialCheckBox7 = this.binding.x;
        materialCheckBox7.setChecked(obtenerAntecedenteExistente(materialCheckBox7).booleanValue());
    }

    private Boolean obtenerAntecedenteExistente(CheckBox checkBox) {
        return Boolean.valueOf(this.viewModel.obtenerAntecedente(checkBox.getText().toString()).isValor());
    }

    private AntecedentesRemoto obtenerNuevoAntecedente(TipoAntecedente tipoAntecedente, int i, boolean z) {
        return new AntecedentesRemoto(tipoAntecedente.value, getString(i), z);
    }

    public void onClick(View view) {
        CheckBox checkBox = (CheckBox) view;
        boolean isChecked = checkBox.isChecked();
        this.viewModel.modificarAntecedente(checkBox.getText().toString(), isChecked);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        y yVar;
        String str = "AutodiagnosticoAntecedentesFragment#onCreateView";
        try {
            TraceMachine.enterMethod(this._nr_trace, str, null);
        } catch (NoSuchFieldError unused) {
            TraceMachine.enterMethod(null, str, null);
        }
        this.binding = e.a(layoutInflater, viewGroup, false);
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
            } else if (autoevaluacionViewModelFactory instanceof z.e) {
                ((z.e) autoevaluacionViewModelFactory).a(yVar2);
            }
            this.viewModel = (AutodiagnosticoViewModel) yVar2;
            this.binding.a(this);
            this.binding.a(getViewLifecycleOwner());
            iniciarInterfaz();
            View view = this.binding.f265f;
            TraceMachine.exitMethod();
            return view;
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.confirmacionDialogo = null;
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
            this.viewModel.pasoActual.a(Integer.valueOf(AutodiagnosticoAntecedentesFragmentArgs.fromBundle(getArguments()).getPasoActual()));
            crearDialogo();
        }
        iniciarAntecedentes();
        iniciarValoresDeVistas();
    }
}
