package com.globant.pasaportesanitario.locationtracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import c.c.a.d.b;
import c.c.a.d.e;
import c.c.a.d.f;
import c.c.a.d.g;
import com.globant.pasaportesanitario.data.local.BaseDeDatos;
import com.globant.pasaportesanitario.data.remoto.Api;
import com.globant.pasaportesanitario.data.remoto.CovidRetrofit;
import com.globant.pasaportesanitario.data.remoto.interceptores.EncabezadosInterceptor;
import com.globant.pasaportesanitario.flujos.pantallaprincipal.PantallaPrincipalRepository;
import e.a.h;
import e.a.k.a;
import java.util.concurrent.Callable;

public class ReceiverArranqueDeDispositivo extends BroadcastReceiver {

    /* renamed from: a reason: collision with root package name */
    public final a f4579a = new a();

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null && intent.getAction().equalsIgnoreCase("android.intent.action.BOOT_COMPLETED")) {
            b bVar = new b(context, new c.c.a.d.v.a(context));
            this.f4579a.c(h.a((Callable<? extends T>) new g<Object>(this, new PantallaPrincipalRepository(new Api(new CovidRetrofit(new EncabezadosInterceptor(c.c.a.e.d.a.a(context), new c.c.a.d.h(this)))), BaseDeDatos.obtenerbaseDeDatos(context)))).b(e.a.p.a.f5172b).a(e.a.j.a.a.a()).a(new e(this, bVar), new f(this)));
        }
    }
}
