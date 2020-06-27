package com.globant.pasaportesanitario.flujos.inicio;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import ar.gob.coronavirus.R;
import b.m.d.o;
import b.o.a0;
import b.o.y;
import b.o.z.c;
import b.o.z.e;
import c.a.a.a.a;
import com.globant.pasaportesanitario.flujos.BaseActivity;
import com.globant.pasaportesanitario.flujos.identificacion.IdentificacionActivity;
import com.google.android.material.appbar.AppBarLayout;
import com.newrelic.agent.android.NewRelic;

public class InicioActivity extends BaseActivity {
    public InicioViewModel inicioViewModel;

    public static void iniciar(Context context) {
        context.startActivity(new Intent(context, InicioActivity.class));
    }

    public void navegarALogin() {
        IdentificacionActivity.iniciar(this);
        finish();
    }

    public void onCreate(Bundle bundle) {
        y yVar;
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_inicio);
        NewRelic.withApplicationToken("AAb25cb32f75a3c8a4e952df367fb52e52a6526c0a-NRMA").start(getApplication());
        InicioViewModelFactory inicioViewModelFactory = new InicioViewModelFactory(this, getApplication());
        a0 viewModelStore = getViewModelStore();
        Class<InicioViewModel> cls = InicioViewModel.class;
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            String b2 = a.b("androidx.lifecycle.ViewModelProvider.DefaultKey:", canonicalName);
            y yVar2 = (y) viewModelStore.f1739a.get(b2);
            if (!cls.isInstance(yVar2)) {
                if (inicioViewModelFactory instanceof c) {
                    yVar = ((c) inicioViewModelFactory).a(b2, cls);
                } else {
                    yVar = inicioViewModelFactory.create(cls);
                }
                yVar2 = yVar;
                y yVar3 = (y) viewModelStore.f1739a.put(b2, yVar2);
                if (yVar3 != null) {
                    yVar3.onCleared();
                }
            } else if (inicioViewModelFactory instanceof e) {
                ((e) inicioViewModelFactory).a(yVar2);
            }
            this.inicioViewModel = (InicioViewModel) yVar2;
            if (bundle == null) {
                ((AppBarLayout) findViewById(R.id.app_bar_inicio)).setVisibility(8);
                InicioSplashFragment inicioSplashFragment = new InicioSplashFragment();
                o supportFragmentManager = getSupportFragmentManager();
                if (supportFragmentManager != null) {
                    b.m.d.a aVar = new b.m.d.a(supportFragmentManager);
                    aVar.a(R.id.fragment_container, inicioSplashFragment, null, 1);
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
