package com.globant.pasaportesanitario.flujos.inicio;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import b.o.r;
import b.o.z;
import c.c.a.b.k;
import c.c.a.e.e.a;
import com.globant.pasaportesanitario.flujos.autodiagnostico.AutodiagnosticoActivity;
import com.globant.pasaportesanitario.flujos.pantallaprincipal.PantallaPrincipalActivity;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;

@Instrumented
public class InicioSplashFragment extends Fragment implements TraceFieldInterface {
    public Trace _nr_trace;
    public k binding;
    public InicioViewModel inicioViewModel;

    /* renamed from: com.globant.pasaportesanitario.flujos.inicio.InicioSplashFragment$3 reason: invalid class name */
    public static /* synthetic */ class AnonymousClass3 {
        public static final /* synthetic */ int[] $SwitchMap$com$globant$pasaportesanitario$flujos$inicio$NavegacionFragments;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|(2:1|2)|3|5|6|7|8|10) */
        /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0016 */
        static {
            int[] iArr = new int[NavegacionFragments.values().length];
            $SwitchMap$com$globant$pasaportesanitario$flujos$inicio$NavegacionFragments = iArr;
            try {
                NavegacionFragments navegacionFragments = NavegacionFragments.LOGIN;
                iArr[1] = 1;
            } catch (NoSuchFieldError unused) {
            }
            int[] iArr2 = $SwitchMap$com$globant$pasaportesanitario$flujos$inicio$NavegacionFragments;
            NavegacionFragments navegacionFragments2 = NavegacionFragments.PRINCIPAL;
            iArr2[3] = 2;
            int[] iArr3 = $SwitchMap$com$globant$pasaportesanitario$flujos$inicio$NavegacionFragments;
            NavegacionFragments navegacionFragments3 = NavegacionFragments.DIAGNOSTICO;
            iArr3[2] = 3;
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        InicioViewModel inicioViewModel2 = (InicioViewModel) new z(requireActivity()).a(InicioViewModel.class);
        this.inicioViewModel = inicioViewModel2;
        inicioViewModel2.obtenerEventosDeNavegacionLiveData().a(getViewLifecycleOwner(), new r<a<NavegacionFragments>>() {
            public void onChanged(a<NavegacionFragments> aVar) {
                if (aVar.a() != null) {
                    int ordinal = ((NavegacionFragments) aVar.f3123a).ordinal();
                    if (ordinal == 1) {
                        ((InicioActivity) InicioSplashFragment.this.requireActivity()).navegarALogin();
                    } else if (ordinal == 2) {
                        AutodiagnosticoActivity.iniciar(InicioSplashFragment.this.getContext(), false);
                        InicioSplashFragment.this.getActivity().finish();
                    } else if (ordinal == 3) {
                        PantallaPrincipalActivity.iniciar(InicioSplashFragment.this.getContext(), false);
                        InicioSplashFragment.this.getActivity().finish();
                    }
                }
            }
        });
        new Handler().postDelayed(new Runnable() {
            public void run() {
                InicioSplashFragment.this.inicioViewModel.navegarSiguientePantalla();
            }
        }, 2000);
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(" ");
        sb.append("3.0.7");
        this.binding.t.setText(sb.toString());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        String str = "InicioSplashFragment#onCreateView";
        try {
            TraceMachine.enterMethod(this._nr_trace, str, null);
        } catch (NoSuchFieldError unused) {
            TraceMachine.enterMethod(null, str, null);
        }
        k a2 = k.a(layoutInflater, viewGroup, false);
        this.binding = a2;
        View view = a2.f265f;
        TraceMachine.exitMethod();
        return view;
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
}
