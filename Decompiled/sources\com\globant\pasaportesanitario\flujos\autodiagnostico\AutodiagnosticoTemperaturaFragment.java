package com.globant.pasaportesanitario.flujos.autodiagnostico;

import a.a.a.b.a;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import androidx.appcompat.app.AlertController;
import androidx.fragment.app.Fragment;
import ar.gob.coronavirus.R;
import b.b.k.h;
import b.o.a0;
import b.o.y;
import b.o.z;
import b.o.z.e;
import b.r.j;
import c.c.a.b.c;
import c.c.a.b.i;
import c.d.a.b.v.b;
import com.globant.pasaportesanitario.flujos.BaseActivity;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.Locale;

@Instrumented
public class AutodiagnosticoTemperaturaFragment extends Fragment implements TraceFieldInterface {
    public final double LIMITE_INFERIOR = 34.0d;
    public final double LIMITE_SUPERIOR = 42.0d;
    public Trace _nr_trace;
    public i binding = null;
    public h infoDialogo = null;
    public AutodiagnosticoViewModel viewModel;

    /* access modifiers changed from: private */
    public void actualizarTemperatura() {
        this.binding.z.setText(String.format(Locale.FRANCE, "%.1f", new Object[]{Double.valueOf(this.viewModel.temperatura)}));
    }

    private void crearDialogo() {
        b bVar = new b(requireContext(), R.style.AlertDialogTheme);
        View view = c.a(getLayoutInflater()).f265f;
        AlertController.b bVar2 = bVar.f521a;
        bVar2.t = view;
        bVar2.s = 0;
        bVar2.u = false;
        AnonymousClass8 r1 = new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        };
        AlertController.b bVar3 = bVar.f521a;
        bVar3.i = bVar3.f80a.getText(R.string.cerrar);
        bVar.f521a.j = r1;
        this.infoDialogo = bVar.a();
    }

    /* access modifiers changed from: private */
    public void deshabilitarBotones(Double d2) {
        if (d2.doubleValue() <= 34.0d) {
            this.binding.t.setEnabled(false);
            this.binding.t.setImageDrawable(getResources().getDrawable(R.drawable.ic_quitar_gris));
            this.binding.u.setEnabled(true);
            this.binding.u.setImageDrawable(getResources().getDrawable(R.drawable.ic_add));
        }
        if (d2.doubleValue() >= 42.0d) {
            this.binding.t.setEnabled(true);
            this.binding.t.setImageDrawable(getResources().getDrawable(R.drawable.ic_quitar));
            this.binding.u.setEnabled(false);
            this.binding.u.setImageDrawable(getResources().getDrawable(R.drawable.ic_add_gris));
        }
        if (d2.doubleValue() > 34.0d && d2.doubleValue() < 42.0d) {
            this.binding.u.setImageDrawable(getResources().getDrawable(R.drawable.ic_add));
            this.binding.t.setEnabled(true);
            this.binding.t.setImageDrawable(getResources().getDrawable(R.drawable.ic_quitar));
            this.binding.u.setEnabled(true);
        }
    }

    private void iniciarInterfaz() {
        this.binding.v.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (AutodiagnosticoTemperaturaFragment.this.viewModel.temperatura < 34.0d || AutodiagnosticoTemperaturaFragment.this.viewModel.temperatura > 42.0d) {
                    AutodiagnosticoTemperaturaFragment.this.binding.B.setError(AutodiagnosticoTemperaturaFragment.this.getString(R.string.temperatura_error_limite, Double.valueOf(34.0d), Double.valueOf(42.0d)));
                    return;
                }
                AutodiagnosticoTemperaturaFragment.this.viewModel.setTemperatura(AutodiagnosticoTemperaturaFragment.this.viewModel.temperatura);
                a.a(view).a((j) AutodiagnosticoTemperaturaFragmentDirections.actionAutodiagnosticoTemperaturaFragmentToAutodiagnosticoSintomasFragment());
            }
        });
        this.binding.A.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AutodiagnosticoTemperaturaFragment.this.infoDialogo.show();
            }
        });
        this.binding.t.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AutodiagnosticoTemperaturaFragment autodiagnosticoTemperaturaFragment = AutodiagnosticoTemperaturaFragment.this;
                autodiagnosticoTemperaturaFragment.deshabilitarBotones(Double.valueOf(autodiagnosticoTemperaturaFragment.viewModel.temperatura));
                if (AutodiagnosticoTemperaturaFragment.this.viewModel.temperatura > 34.0d) {
                    AutodiagnosticoViewModel access$000 = AutodiagnosticoTemperaturaFragment.this.viewModel;
                    access$000.temperatura -= 0.1d;
                    AutodiagnosticoTemperaturaFragment.this.actualizarTemperatura();
                }
            }
        });
        this.binding.t.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View view) {
                if (AutodiagnosticoTemperaturaFragment.this.viewModel.temperatura <= 35.0d) {
                    return false;
                }
                AutodiagnosticoViewModel access$000 = AutodiagnosticoTemperaturaFragment.this.viewModel;
                access$000.temperatura -= 1.0d;
                AutodiagnosticoTemperaturaFragment.this.actualizarTemperatura();
                return true;
            }
        });
        this.binding.u.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AutodiagnosticoTemperaturaFragment autodiagnosticoTemperaturaFragment = AutodiagnosticoTemperaturaFragment.this;
                autodiagnosticoTemperaturaFragment.deshabilitarBotones(Double.valueOf(autodiagnosticoTemperaturaFragment.viewModel.temperatura));
                if (AutodiagnosticoTemperaturaFragment.this.viewModel.temperatura < 42.0d) {
                    AutodiagnosticoViewModel access$000 = AutodiagnosticoTemperaturaFragment.this.viewModel;
                    access$000.temperatura += 0.1d;
                    AutodiagnosticoTemperaturaFragment.this.actualizarTemperatura();
                }
            }
        });
        this.binding.u.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View view) {
                if (AutodiagnosticoTemperaturaFragment.this.viewModel.temperatura >= 41.0d) {
                    return false;
                }
                AutodiagnosticoViewModel access$000 = AutodiagnosticoTemperaturaFragment.this.viewModel;
                access$000.temperatura += 1.0d;
                AutodiagnosticoTemperaturaFragment.this.actualizarTemperatura();
                return true;
            }
        });
        this.binding.z.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
                String obj = AutodiagnosticoTemperaturaFragment.this.binding.z.getText().toString();
                String replaceAll = obj.replaceAll(",", "");
                if (obj.length() - replaceAll.length() > 1) {
                    AutodiagnosticoTemperaturaFragment.this.actualizarTemperatura();
                    return;
                }
                String replace = obj.replace(',', '.');
                if (replace.isEmpty() || replaceAll.isEmpty()) {
                    AutodiagnosticoTemperaturaFragment.this.binding.B.setError(AutodiagnosticoTemperaturaFragment.this.getString(R.string.temperatura_vacia_error));
                } else if (Double.parseDouble(replace) < 34.0d || Double.parseDouble(replace) > 42.0d) {
                    AutodiagnosticoTemperaturaFragment.this.deshabilitarBotones(Double.valueOf(Double.parseDouble(replace)));
                    AutodiagnosticoTemperaturaFragment.this.binding.B.setError(AutodiagnosticoTemperaturaFragment.this.getString(R.string.temperatura_error_limite, Double.valueOf(34.0d), Double.valueOf(42.0d)));
                    AutodiagnosticoTemperaturaFragment.this.viewModel.temperatura = Double.parseDouble(replace);
                } else {
                    AutodiagnosticoTemperaturaFragment.this.deshabilitarBotones(Double.valueOf(Double.parseDouble(replace)));
                    AutodiagnosticoTemperaturaFragment.this.binding.B.setError(null);
                    AutodiagnosticoTemperaturaFragment.this.viewModel.temperatura = Double.parseDouble(replace);
                }
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        y yVar;
        String str = "AutodiagnosticoTemperaturaFragment#onCreateView";
        try {
            TraceMachine.enterMethod(this._nr_trace, str, null);
        } catch (NoSuchFieldError unused) {
            TraceMachine.enterMethod(null, str, null);
        }
        this.binding = i.a(layoutInflater, viewGroup, false);
        AutoevaluacionViewModelFactory autoevaluacionViewModelFactory = new AutoevaluacionViewModelFactory((BaseActivity) requireActivity());
        a0 viewModelStore = requireActivity().getViewModelStore();
        Class<AutodiagnosticoViewModel> cls = AutodiagnosticoViewModel.class;
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            String b2 = c.a.a.a.a.b("androidx.lifecycle.ViewModelProvider.DefaultKey:", canonicalName);
            y yVar2 = (y) viewModelStore.f1739a.get(b2);
            if (!cls.isInstance(yVar2)) {
                if (autoevaluacionViewModelFactory instanceof z.c) {
                    yVar = ((z.c) autoevaluacionViewModelFactory).a(b2, cls);
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
            if (((c.c.a.b.j) this.binding) != null) {
                crearDialogo();
                this.binding.a(getViewLifecycleOwner());
                iniciarInterfaz();
                View view = this.binding.f265f;
                TraceMachine.exitMethod();
                return view;
            }
            throw null;
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
            this.viewModel.pasoActual.a(Integer.valueOf(AutodiagnosticoTemperaturaFragmentArgs.fromBundle(getArguments()).getPasoActual()));
            actualizarTemperatura();
        }
    }
}
