package com.globant.pasaportesanitario.flujos.pantallaprincipal.ui.pantallaprincipal;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import ar.gob.coronavirus.R;
import b.b.k.h;
import b.o.a0;
import b.o.l;
import b.o.r;
import b.o.y;
import b.o.z.c;
import b.o.z.e;
import com.globant.pasaportesanitario.data.NombreEstado;
import com.globant.pasaportesanitario.data.local.modelo.TiempoRestante;
import com.globant.pasaportesanitario.data.local.modelo.UsuarioBD;
import com.globant.pasaportesanitario.flujos.BaseActivity;
import com.globant.pasaportesanitario.flujos.autodiagnostico.AutodiagnosticoActivity;
import com.globant.pasaportesanitario.flujos.autodiagnostico.resultado.ResultadoActivity;
import com.globant.pasaportesanitario.flujos.autodiagnostico.resultado.ResultadoActivity.OpcionesNavegacion;
import com.globant.pasaportesanitario.flujos.identificacion.IdentificacionActivity;
import com.globant.pasaportesanitario.flujos.pantallaprincipal.PantallaPrincipalViewModel;
import com.globant.pasaportesanitario.flujos.pantallaprincipal.PantallaPrincipalViewModelFactory;
import com.globant.pasaportesanitario.utils.dialogs.PantallaCompletaDialog;
import com.globant.pasaportesanitario.utils.dialogs.PantallaCompletaDialog.a;
import com.globant.pasaportesanitario.utils.token.TokenUtils;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;

@Instrumented
public class NoInfectadoFragment extends Fragment implements TraceFieldInterface {
    public Trace _nr_trace;
    public View botonNuevoDiagnostico;
    public TextView cantidadHoras;
    public h confirmacionDialogo = null;
    public View containerMasInfromacion;
    public PantallaPrincipalViewModel mViewModel;
    public TextView unidadDeTiempo;

    /* access modifiers changed from: private */
    public void crearDialogo() {
        final PantallaCompletaDialog a2 = PantallaCompletaDialog.a(getString(R.string.hubo_error), getString(R.string.no_hay_internet), getString(R.string.cerrar).toUpperCase(), R.drawable.ic_error);
        a2.f4589b = new a() {
            public void onClick(View view) {
                a2.dismiss();
            }
        };
        a2.show(getParentFragmentManager(), "TAG");
    }

    private void escucharCambiosDelUsuario() {
        this.mViewModel.obtenerUltimoEstadoLiveData().a(requireActivity(), new r<UsuarioBD>() {
            public void onChanged(UsuarioBD usuarioBD) {
                NoInfectadoFragment.this.mViewModel.despacharEventoNavegacion();
                if (usuarioBD.estadoActual.nombreEstado.equals(NombreEstado.NO_CONTAGIOSO)) {
                    NoInfectadoFragment.this.unidadDeTiempo.setText("");
                    NoInfectadoFragment.this.cantidadHoras.setText(" ");
                    NoInfectadoFragment.this.containerMasInfromacion.setVisibility(8);
                    NoInfectadoFragment.this.botonNuevoDiagnostico.setVisibility(8);
                    return;
                }
                NoInfectadoFragment.this.setTiempo(usuarioBD);
            }
        });
    }

    /* access modifiers changed from: private */
    public void setTiempo(UsuarioBD usuarioBD) {
        TiempoRestante d2 = a.a.a.b.a.d(usuarioBD.estadoActual.fechaHoraVencimiento);
        if (getActivity() != null) {
            TextView textView = this.cantidadHoras;
            if (textView != null && this.unidadDeTiempo != null) {
                textView.setText(d2.getTiempoRestante());
                this.unidadDeTiempo.setText(d2.getUnidadTiempo());
            }
        }
    }

    private void setupEmojis() {
        TextView textView = (TextView) getView().findViewById(R.id.emoji_uno);
        TextView textView2 = (TextView) getView().findViewById(R.id.emoji_dos);
        TextView textView3 = (TextView) getView().findViewById(R.id.emoji_tres);
        c.c.a.e.g.a a2 = TokenUtils.a();
        textView.setText(a2.a());
        textView2.setText(a2.b());
        textView3.setText(a2.c());
    }

    public void onActivityCreated(Bundle bundle) {
        y yVar;
        super.onActivityCreated(bundle);
        if (getView() != null) {
            this.cantidadHoras = (TextView) getView().findViewById(R.id.cantidad_horas);
            this.unidadDeTiempo = (TextView) getView().findViewById(R.id.unidad_de_tiempo);
            this.containerMasInfromacion = getView().findViewById(R.id.container_mas_informacion);
            View findViewById = getView().findViewById(R.id.boton_nuevo_diagnostico);
            this.botonNuevoDiagnostico = findViewById;
            findViewById.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    AutodiagnosticoActivity.iniciarActividadParaResultado(NoInfectadoFragment.this.getActivity(), true);
                }
            });
        }
        PantallaPrincipalViewModelFactory pantallaPrincipalViewModelFactory = new PantallaPrincipalViewModelFactory((BaseActivity) getActivity());
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
            this.mViewModel = pantallaPrincipalViewModel;
            pantallaPrincipalViewModel.obtenerLevantarWebLiveData().a(requireActivity(), new r<c.c.a.e.e.a<Intent>>() {
                public void onChanged(c.c.a.e.e.a<Intent> aVar) {
                    if (aVar.a() != null) {
                        NoInfectadoFragment.this.startActivity((Intent) aVar.f3123a);
                    }
                }
            });
            return;
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        String str = "NoInfectadoFragment#onCreateView";
        try {
            TraceMachine.enterMethod(this._nr_trace, str, null);
        } catch (NoSuchFieldError unused) {
            TraceMachine.enterMethod(null, str, null);
        }
        View inflate = layoutInflater.inflate(R.layout.fragment_no_infectado, viewGroup, false);
        inflate.findViewById(R.id.boton_actualizar_certificado).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                a.a.a.b.a.a(view.getContext(), (int) R.string.pregunta_actualizar_certificado, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        NoInfectadoFragment.this.mViewModel.obtenerUltimoEstadoDeBackend();
                    }
                });
            }
        });
        TextView textView = (TextView) inflate.findViewById(R.id.boton_mas_informacion);
        textView.setPaintFlags(textView.getPaintFlags() | 8);
        textView.setOnClickListener(new OnClickListener() {
            public void onClick(final View view) {
                view.setClickable(false);
                ResultadoActivity.iniciar(NoInfectadoFragment.this.getActivity(), OpcionesNavegacion.RESULTADO_VERDE);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        view.setClickable(true);
                    }
                }, 500);
            }
        });
        TextView textView2 = (TextView) inflate.findViewById(R.id.desvincular_dni);
        textView2.setPaintFlags(textView2.getPaintFlags() | 8);
        textView2.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                a.a.a.b.a.a(view.getContext(), (int) R.string.pregunta_desvincular_dni, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        NoInfectadoFragment.this.mViewModel.logout();
                        IdentificacionActivity.iniciarEliminandoPilaNavegacion(NoInfectadoFragment.this.requireContext());
                    }
                });
            }
        });
        ((TextView) inflate.findViewById(R.id.habilitar_circulacion)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (a.a.a.b.a.c(NoInfectadoFragment.this.getContext())) {
                    NoInfectadoFragment.this.mViewModel.habilitarCirculacion();
                } else {
                    NoInfectadoFragment.this.crearDialogo();
                }
            }
        });
        TraceMachine.exitMethod();
        return inflate;
    }

    public void onResume() {
        super.onResume();
        setupEmojis();
        this.mViewModel.actualizarDatosUsuario();
    }

    public void onStart() {
        super.onStart();
        escucharCambiosDelUsuario();
    }

    public void onStop() {
        super.onStop();
        this.mViewModel.obtenerUltimoEstadoLiveData().a((l) requireActivity());
    }
}
