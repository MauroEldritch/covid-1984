package com.globant.pasaportesanitario.flujos.identificacion;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import ar.gob.coronavirus.R;
import b.m.d.o;
import b.o.a0;
import b.o.r;
import b.o.y;
import b.o.z.c;
import b.o.z.e;
import c.c.a.e.e.a;
import com.globant.pasaportesanitario.flujos.BaseActivity;
import com.globant.pasaportesanitario.flujos.autodiagnostico.AutodiagnosticoActivity;
import com.globant.pasaportesanitario.flujos.pantallaprincipal.PantallaPrincipalActivity;
import com.globant.pasaportesanitario.utils.dialogs.PantallaCompletaDialog;
import com.google.android.material.appbar.AppBarLayout;

public class IdentificacionActivity extends BaseActivity implements IdentificacionNavegador {
    public IdentificacionViewModel identificacionViewModel;

    public static void iniciar(Context context) {
        context.startActivity(new Intent(context, IdentificacionActivity.class));
    }

    public static void iniciarEliminandoPilaNavegacion(Context context) {
        context.startActivity(new Intent(context, IdentificacionActivity.class).addFlags(268468224));
    }

    private void iniciarObservers() {
        this.identificacionViewModel.obtenerActualizarUsuarioLiveData().a(this, new r<a<Boolean>>() {
            public void onChanged(a<Boolean> aVar) {
                if (aVar.a() != null) {
                    String str = "TAG";
                    if (((Boolean) aVar.f3123a).booleanValue()) {
                        final PantallaCompletaDialog a2 = PantallaCompletaDialog.a(IdentificacionActivity.this.getString(R.string.gracias_por_tu_ayuda), IdentificacionActivity.this.getString(R.string.ahora_podemos_continuar), "Continuar", R.drawable.confirmacion_icon_96px);
                        a2.f4589b = new PantallaCompletaDialog.a() {
                            public void onClick(View view) {
                                IdentificacionActivity.this.identificacionViewModel.navegarSiguientePantallaDependiendoDelEstado();
                                a2.dismiss();
                            }
                        };
                        a2.show(IdentificacionActivity.this.getSupportFragmentManager(), str);
                        return;
                    }
                    final PantallaCompletaDialog a3 = PantallaCompletaDialog.a(IdentificacionActivity.this.getString(R.string.hubo_error), IdentificacionActivity.this.getString(R.string.hubo_error_desc), "CERRAR", R.drawable.ic_error);
                    a3.f4589b = new PantallaCompletaDialog.a() {
                        public void onClick(View view) {
                            a3.dismiss();
                        }
                    };
                    a3.show(IdentificacionActivity.this.getSupportFragmentManager(), str);
                }
            }
        });
    }

    public void navegarAAutoDiagnosticoActivity() {
        AutodiagnosticoActivity.iniciar(this, false);
        finish();
    }

    public void navegarAIdentificacionConfirmacionDatosFragment() {
        IdentificacionDniConfirmacionDatosFragment identificacionDniConfirmacionDatosFragment = new IdentificacionDniConfirmacionDatosFragment();
        o supportFragmentManager = getSupportFragmentManager();
        if (supportFragmentManager != null) {
            b.m.d.a aVar = new b.m.d.a(supportFragmentManager);
            aVar.a(R.id.fragment_container, identificacionDniConfirmacionDatosFragment);
            aVar.a((String) null);
            aVar.a();
            return;
        }
        throw null;
    }

    public void navegarAIdentificacionDireccionCompletaFragment() {
        IdentificacionDireccionCompletaFragment identificacionDireccionCompletaFragment = new IdentificacionDireccionCompletaFragment();
        o supportFragmentManager = getSupportFragmentManager();
        if (supportFragmentManager != null) {
            b.m.d.a aVar = new b.m.d.a(supportFragmentManager);
            aVar.a(R.id.fragment_container, identificacionDireccionCompletaFragment);
            aVar.a((String) null);
            aVar.a();
            return;
        }
        throw null;
    }

    public void navegarAIdentificacionDireccionFragment() {
        IdentificacionDireccionFragment identificacionDireccionFragment = new IdentificacionDireccionFragment();
        o supportFragmentManager = getSupportFragmentManager();
        if (supportFragmentManager != null) {
            b.m.d.a aVar = new b.m.d.a(supportFragmentManager);
            aVar.a(R.id.fragment_container, identificacionDireccionFragment);
            aVar.a((String) null);
            aVar.a();
            return;
        }
        throw null;
    }

    public void navegarAIdentificacionTelefonoFragment() {
        IdentificacionTelefonoFragment identificacionTelefonoFragment = new IdentificacionTelefonoFragment();
        o supportFragmentManager = getSupportFragmentManager();
        if (supportFragmentManager != null) {
            b.m.d.a aVar = new b.m.d.a(supportFragmentManager);
            aVar.a(R.id.fragment_container, identificacionTelefonoFragment);
            aVar.a((String) null);
            aVar.a();
            return;
        }
        throw null;
    }

    public void navegarAPantallaPrincipal(boolean z) {
        PantallaPrincipalActivity.iniciar(this, z);
        finish();
    }

    public void onBackPressed() {
        if (getSupportFragmentManager().b((int) R.id.fragment_container) instanceof IdentificacionDniConfirmacionDatosFragment) {
            this.identificacionViewModel.logout();
            super.onBackPressed();
            return;
        }
        super.onBackPressed();
    }

    public void onCreate(Bundle bundle) {
        y yVar;
        super.onCreate(bundle);
        setContentView((int) R.layout.identificacion_activity);
        IdentificacionViewModelFactory identificacionViewModelFactory = new IdentificacionViewModelFactory(this, this);
        a0 viewModelStore = getViewModelStore();
        Class<IdentificacionViewModel> cls = IdentificacionViewModel.class;
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            String b2 = c.a.a.a.a.b("androidx.lifecycle.ViewModelProvider.DefaultKey:", canonicalName);
            y yVar2 = (y) viewModelStore.f1739a.get(b2);
            if (!cls.isInstance(yVar2)) {
                if (identificacionViewModelFactory instanceof c) {
                    yVar = ((c) identificacionViewModelFactory).a(b2, cls);
                } else {
                    yVar = identificacionViewModelFactory.create(cls);
                }
                yVar2 = yVar;
                y yVar3 = (y) viewModelStore.f1739a.put(b2, yVar2);
                if (yVar3 != null) {
                    yVar3.onCleared();
                }
            } else if (identificacionViewModelFactory instanceof e) {
                ((e) identificacionViewModelFactory).a(yVar2);
            }
            IdentificacionViewModel identificacionViewModel2 = (IdentificacionViewModel) yVar2;
            this.identificacionViewModel = identificacionViewModel2;
            setBaseViewModel(identificacionViewModel2);
            iniciarObservers();
            if (bundle == null) {
                ((AppBarLayout) findViewById(R.id.app_bar_inicio_identificacion)).setVisibility(0);
                IdentificacionDniManualFragment identificacionDniManualFragment = new IdentificacionDniManualFragment();
                o supportFragmentManager = getSupportFragmentManager();
                if (supportFragmentManager != null) {
                    b.m.d.a aVar = new b.m.d.a(supportFragmentManager);
                    aVar.a(R.id.fragment_container, identificacionDniManualFragment, null, 1);
                    aVar.a();
                    return;
                }
                throw null;
            }
            return;
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }
}
