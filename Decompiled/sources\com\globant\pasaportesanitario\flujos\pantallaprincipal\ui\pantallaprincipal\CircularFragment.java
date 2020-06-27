package com.globant.pasaportesanitario.flujos.pantallaprincipal.ui.pantallaprincipal;

import a.a.a.b.a;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import ar.gob.coronavirus.R;
import b.o.a0;
import b.o.l;
import b.o.r;
import b.o.y;
import b.o.z.c;
import b.o.z.e;
import com.globant.pasaportesanitario.data.NombreEstado;
import com.globant.pasaportesanitario.data.local.modelo.EstadoActualBD;
import com.globant.pasaportesanitario.data.local.modelo.TiempoRestante;
import com.globant.pasaportesanitario.data.local.modelo.UsuarioBD;
import com.globant.pasaportesanitario.flujos.BaseActivity;
import com.globant.pasaportesanitario.flujos.autodiagnostico.AutodiagnosticoActivity;
import com.globant.pasaportesanitario.flujos.autodiagnostico.resultado.ResultadoActivity;
import com.globant.pasaportesanitario.flujos.autodiagnostico.resultado.ResultadoActivity.OpcionesNavegacion;
import com.globant.pasaportesanitario.flujos.identificacion.IdentificacionActivity;
import com.globant.pasaportesanitario.flujos.pantallaprincipal.PantallaPrincipalViewModel;
import com.globant.pasaportesanitario.flujos.pantallaprincipal.PantallaPrincipalViewModelFactory;
import com.globant.pasaportesanitario.utils.token.TokenUtils;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.text.SimpleDateFormat;

@Instrumented
public class CircularFragment extends Fragment implements TraceFieldInterface {
    public Trace _nr_trace;
    public View botonNuevoDiagnostico;
    public TextView cantidadHoras;
    public View containerMasInfromacion;
    public ImageView imagenQr;
    public PantallaPrincipalViewModel mViewModel;
    public TextView unidadDeTiempo;

    private void escucharCambiosDelUsuario() {
        this.mViewModel.obtenerUltimoEstadoLiveData().a(requireActivity(), new r<UsuarioBD>() {
            public void onChanged(UsuarioBD usuarioBD) {
                CircularFragment.this.mViewModel.despacharEventoNavegacion();
                if (CircularFragment.this.getActivity() != null && !TextUtils.isEmpty(usuarioBD.estadoActual.permisoCirculacionBD.permisoQr)) {
                    byte[] decode = Base64.decode(usuarioBD.estadoActual.permisoCirculacionBD.permisoQr, 0);
                    CircularFragment.this.imagenQr.setImageBitmap(BitmapFactoryInstrumentation.decodeByteArray(decode, 0, decode.length));
                    CircularFragment.this.setTiempo(usuarioBD);
                    if (usuarioBD.estadoActual.nombreEstado.equals(NombreEstado.NO_CONTAGIOSO)) {
                        CircularFragment.this.unidadDeTiempo.setText("");
                        CircularFragment.this.cantidadHoras.setText(" ");
                        CircularFragment.this.containerMasInfromacion.setVisibility(8);
                        CircularFragment.this.botonNuevoDiagnostico.setVisibility(8);
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void setTiempo(UsuarioBD usuarioBD) {
        TiempoRestante tiempoRestante;
        a.d(usuarioBD.estadoActual.permisoCirculacionBD.fechaVencimientoPermiso);
        if (getActivity() != null && this.cantidadHoras != null && this.unidadDeTiempo != null) {
            EstadoActualBD estadoActualBD = usuarioBD.estadoActual;
            String str = estadoActualBD.permisoCirculacionBD.fechaVencimientoPermiso;
            String str2 = estadoActualBD.fechaHoraVencimiento;
            new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            if (a.a(str).compareTo(a.a(str2)) > 0) {
                tiempoRestante = a.d(str2);
            } else {
                tiempoRestante = a.d(str);
            }
            this.cantidadHoras.setText(tiempoRestante.getTiempoRestante());
            this.unidadDeTiempo.setText(tiempoRestante.getUnidadTiempo());
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
            this.botonNuevoDiagnostico = getView().findViewById(R.id.boton_nuevo_diagnostico);
            this.containerMasInfromacion = getView().findViewById(R.id.container_mas_informacion);
            this.botonNuevoDiagnostico.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    AutodiagnosticoActivity.iniciarActividadParaResultado(CircularFragment.this.getActivity(), true);
                }
            });
        }
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
            this.mViewModel = (PantallaPrincipalViewModel) yVar2;
            return;
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        String str = "CircularFragment#onCreateView";
        try {
            TraceMachine.enterMethod(this._nr_trace, str, null);
        } catch (NoSuchFieldError unused) {
            TraceMachine.enterMethod(null, str, null);
        }
        View inflate = layoutInflater.inflate(R.layout.fragment_circular, viewGroup, false);
        this.imagenQr = (ImageView) inflate.findViewById(R.id.imagenQr);
        inflate.findViewById(R.id.boton_actualizar_certificado).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                a.a(view.getContext(), (int) R.string.pregunta_actualizar_certificado, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CircularFragment.this.mViewModel.obtenerUltimoEstadoDeBackend();
                    }
                });
            }
        });
        TextView textView = (TextView) inflate.findViewById(R.id.boton_mas_informacion);
        textView.setPaintFlags(textView.getPaintFlags() | 8);
        textView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ResultadoActivity.iniciar(CircularFragment.this.getActivity(), OpcionesNavegacion.RESULTADO_VERDE);
            }
        });
        TextView textView2 = (TextView) inflate.findViewById(R.id.desvincular_dni);
        textView2.setPaintFlags(textView2.getPaintFlags() | 8);
        textView2.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                a.a(view.getContext(), (int) R.string.pregunta_desvincular_dni, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CircularFragment.this.mViewModel.logout();
                        IdentificacionActivity.iniciarEliminandoPilaNavegacion(CircularFragment.this.requireContext());
                    }
                });
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
