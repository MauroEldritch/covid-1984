package com.globant.pasaportesanitario.locationtracker;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.text.TextUtils;
import ar.gob.coronavirus.R;
import b.h.e.j;
import c.c.a.d.c;
import c.c.a.d.d;
import c.c.a.d.q;
import c.c.a.d.r;
import c.c.a.d.s;
import c.c.a.d.t;
import c.c.a.d.u;
import c.d.a.a.c.m.k.a0;
import c.d.a.a.c.m.k.g;
import c.d.a.a.c.m.k.k0;
import c.d.a.a.c.m.k.z;
import c.d.a.a.c.n.v;
import c.d.a.a.f.b;
import c.d.a.a.f.m;
import c.d.a.a.f.n;
import com.globant.pasaportesanitario.data.local.BaseDeDatos;
import com.globant.pasaportesanitario.data.local.modelo.UsuarioBD;
import com.globant.pasaportesanitario.data.remoto.Api;
import com.globant.pasaportesanitario.data.remoto.CovidRetrofit;
import com.globant.pasaportesanitario.data.remoto.interceptores.EncabezadosInterceptor;
import com.globant.pasaportesanitario.flujos.pantallaprincipal.PantallaPrincipalRepository;
import com.google.android.gms.location.LocationRequest;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import e.a.e;
import e.a.k.a;
import e.a.n.e.b.f;
import e.a.n.e.b.h;
import java.util.concurrent.TimeUnit;

public class ServicioDeRastreo extends Service {
    public static ServicioDeRastreo j;

    /* renamed from: b reason: collision with root package name */
    public final a f4580b = new a();

    /* renamed from: c reason: collision with root package name */
    public final a f4581c = new a();

    /* renamed from: d reason: collision with root package name */
    public c.d.a.a.f.a f4582d;

    /* renamed from: e reason: collision with root package name */
    public c f4583e;

    /* renamed from: f reason: collision with root package name */
    public q f4584f;

    /* renamed from: g reason: collision with root package name */
    public PantallaPrincipalRepository f4585g;

    /* renamed from: h reason: collision with root package name */
    public c.c.a.d.a f4586h;
    public c.c.a.e.d.a i;

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0029  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0106  */
    public static /* synthetic */ void a(ServicioDeRastreo servicioDeRastreo) {
        boolean z;
        if (servicioDeRastreo != null) {
            boolean z2 = false;
            try {
                UsuarioBD usuario = servicioDeRastreo.f4585g.getUsuario();
                boolean z3 = !TextUtils.isEmpty(servicioDeRastreo.i.a());
                boolean b2 = servicioDeRastreo.f4586h.b(usuario);
                if (z3 && b2) {
                    z = true;
                    if (!z) {
                        d d2 = a.a.a.b.a.d((Context) servicioDeRastreo);
                        if (d2 == d.TODO_EL_TIEMPO || d2 == d.SOLO_CON_LA_APLICACION_VISIBLE) {
                            u uVar = new u(servicioDeRastreo);
                            LocationRequest locationRequest = new LocationRequest();
                            locationRequest.f4629b = 100;
                            LocationRequest.a(0);
                            locationRequest.f4630c = 0;
                            if (!locationRequest.f4632e) {
                                locationRequest.f4631d = (long) (((double) 0) / 6.0d);
                            }
                            LocationRequest.a(0);
                            locationRequest.f4632e = true;
                            locationRequest.f4631d = 0;
                            locationRequest.f4634g = 1;
                            c.d.a.a.f.a aVar = servicioDeRastreo.f4582d;
                            Looper mainLooper = Looper.getMainLooper();
                            if (aVar != null) {
                                c.d.a.a.e.a.q qVar = new c.d.a.a.e.a.q(locationRequest, c.d.a.a.e.a.q.i, null, false, false, false, null);
                                if (mainLooper == null) {
                                    if (Looper.myLooper() != null) {
                                        z2 = true;
                                    }
                                    v.a(z2, (Object) "Can't create handler inside thread that has not called Looper.prepare()");
                                    mainLooper = Looper.myLooper();
                                }
                                String simpleName = b.class.getSimpleName();
                                v.a(uVar, (Object) "Listener must not be null");
                                v.a(mainLooper, (Object) "Looper must not be null");
                                v.a(simpleName, (Object) "Listener type must not be null");
                                g gVar = new g(mainLooper, uVar, simpleName);
                                m mVar = new m(gVar, qVar, gVar);
                                n nVar = new n(aVar, gVar.f3221c);
                                v.a(mVar);
                                v.a(nVar);
                                String str = "Listener has already been released.";
                                v.a(mVar.f3228a.f3221c, (Object) str);
                                v.a(nVar.f3232a, (Object) str);
                                if (mVar.f3228a.f3221c.equals(nVar.f3232a)) {
                                    c.d.a.a.c.m.k.c cVar = aVar.f3168g;
                                    if (cVar != null) {
                                        k0 k0Var = new k0(new a0(mVar, nVar), new c.d.a.a.h.d());
                                        Handler handler = cVar.m;
                                        handler.sendMessage(handler.obtainMessage(8, new z(k0Var, cVar.f3196h.get(), aVar)));
                                        return;
                                    }
                                    throw null;
                                }
                                throw new IllegalArgumentException("Listener registration and unregistration methods must be constructed with the same ListenerHolder.");
                            }
                            throw null;
                        }
                        return;
                    }
                    servicioDeRastreo.stopForeground(true);
                    servicioDeRastreo.stopSelf();
                    return;
                }
            } catch (Exception unused) {
            }
            z = false;
            if (!z) {
            }
        } else {
            throw null;
        }
    }

    public static /* synthetic */ void b(ServicioDeRastreo servicioDeRastreo) {
        servicioDeRastreo.stopForeground(true);
        servicioDeRastreo.stopSelf();
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        if (j == null) {
            j = this;
        }
        CovidRetrofit covidRetrofit = new CovidRetrofit(new EncabezadosInterceptor(c.c.a.e.d.a.a(this), new r(this)));
        BaseDeDatos obtenerbaseDeDatos = BaseDeDatos.obtenerbaseDeDatos(this);
        Api api = new Api(covidRetrofit);
        PantallaPrincipalRepository pantallaPrincipalRepository = new PantallaPrincipalRepository(api, obtenerbaseDeDatos);
        this.f4585g = pantallaPrincipalRepository;
        this.f4584f = new q(api, pantallaPrincipalRepository);
        this.f4582d = new c.d.a.a.f.a((Context) this);
        this.f4583e = new c(this);
        this.f4586h = new c.c.a.d.b(this, new c.c.a.d.v.a(this));
        this.i = c.c.a.e.d.a.a(this);
    }

    public void onDestroy() {
        this.f4580b.a();
        this.f4581c.a();
        j = null;
    }

    public int onStartCommand(Intent intent, int i2, int i3) {
        String str = "rastreoChannelId";
        if (VERSION.SDK_INT >= 26) {
            c cVar = this.f4583e;
            if (cVar != null) {
                NotificationChannel notificationChannel = new NotificationChannel(str, "ServicioDeRastreoCanal", 3);
                notificationChannel.setLightColor(-16776961);
                notificationChannel.setLockscreenVisibility(0);
                ((NotificationManager) cVar.f3090a.getSystemService("notification")).createNotificationChannel(notificationChannel);
                b.h.e.g gVar = new b.h.e.g(cVar.f3090a, str);
                gVar.a(2, true);
                gVar.O.icon = R.drawable.ic_launcher_foreground;
                gVar.a((CharSequence) cVar.f3090a.getString(R.string.notificacion_rastreo_titulo));
                gVar.l = 1;
                gVar.A = "service";
                Notification a2 = gVar.a();
                j jVar = new j(cVar.f3090a);
                Notification a3 = gVar.a();
                Bundle bundle = a3.extras;
                if (bundle != null && bundle.getBoolean("android.support.useSideChannel")) {
                    jVar.a((j.d) new j.a(jVar.f1278a.getPackageName(), 1, null, a3));
                    jVar.f1279b.cancel(null, 1);
                } else {
                    jVar.f1279b.notify(null, 1, a3);
                }
                startForeground(1, a2);
            } else {
                throw null;
            }
        } else {
            c cVar2 = this.f4583e;
            b.h.e.g gVar2 = new b.h.e.g(cVar2.f3090a, str);
            gVar2.O.when = System.currentTimeMillis();
            Bitmap decodeResource = BitmapFactoryInstrumentation.decodeResource(cVar2.f3090a.getResources(), R.drawable.profile_icon);
            gVar2.O.icon = R.drawable.profile_icon;
            if (decodeResource != null && VERSION.SDK_INT < 27) {
                Resources resources = gVar2.f1262a.getResources();
                int dimensionPixelSize = resources.getDimensionPixelSize(b.h.b.compat_notification_large_icon_max_width);
                int dimensionPixelSize2 = resources.getDimensionPixelSize(b.h.b.compat_notification_large_icon_max_height);
                if (decodeResource.getWidth() > dimensionPixelSize || decodeResource.getHeight() > dimensionPixelSize2) {
                    double min = Math.min(((double) dimensionPixelSize) / ((double) Math.max(1, decodeResource.getWidth())), ((double) dimensionPixelSize2) / ((double) Math.max(1, decodeResource.getHeight())));
                    decodeResource = Bitmap.createScaledBitmap(decodeResource, (int) Math.ceil(((double) decodeResource.getWidth()) * min), (int) Math.ceil(((double) decodeResource.getHeight()) * min), true);
                }
            }
            gVar2.i = decodeResource;
            gVar2.a((CharSequence) cVar2.f3090a.getString(R.string.notificacion_rastreo_titulo));
            gVar2.f1266e = b.h.e.g.b(cVar2.f3090a.getString(R.string.notificacion_rastreo_contenido));
            gVar2.l = -1;
            startForeground(1, gVar2.a());
        }
        this.f4580b.a();
        a aVar = this.f4580b;
        TimeUnit timeUnit = c.c.a.e.a.f3111a;
        e.a.g gVar3 = e.a.p.a.f5171a;
        e.a.n.b.b.a(timeUnit, "unit is null");
        String str2 = "scheduler is null";
        e.a.n.b.b.a(gVar3, str2);
        e.a.n.e.b.d dVar = new e.a.n.e.b.d(Math.max(0, 15), Math.max(0, 15), timeUnit, gVar3);
        Long valueOf = Long.valueOf(0);
        String str3 = "item is null";
        e.a.n.b.b.a(valueOf, str3);
        e.a.n.b.b.a(valueOf, str3);
        e[] eVarArr = {new e.a.n.e.b.e(valueOf), dVar};
        e.a.n.b.b.a(eVarArr, "items is null");
        e.a.n.e.b.b bVar = new e.a.n.e.b.b(new e.a.n.e.b.c(eVarArr), e.a.n.b.a.f4975a, e.a.c.f4938a, e.a.n.h.c.BOUNDARY);
        e.a.g gVar4 = e.a.p.a.f5171a;
        e.a.n.b.b.a(gVar4, str2);
        h hVar = new h(bVar, gVar4);
        e.a.g gVar5 = e.a.p.a.f5172b;
        int i4 = e.a.c.f4938a;
        e.a.n.b.b.a(gVar5, str2);
        if (i4 > 0) {
            f fVar = new f(hVar, gVar5, false, i4);
            s sVar = new s(this);
            t tVar = new t(this);
            e.a.m.a aVar2 = e.a.n.b.a.f4977c;
            e.a.m.b<Object> bVar2 = e.a.n.b.a.f4978d;
            e.a.n.b.b.a(sVar, "onNext is null");
            e.a.n.b.b.a(tVar, "onError is null");
            e.a.n.b.b.a(aVar2, "onComplete is null");
            e.a.n.b.b.a(bVar2, "onSubscribe is null");
            e.a.n.d.f fVar2 = new e.a.n.d.f(sVar, tVar, aVar2, bVar2);
            fVar.a(fVar2);
            aVar.c(fVar2);
            return super.onStartCommand(intent, i2, i3);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("bufferSize");
        sb.append(" > 0 required but it was ");
        sb.append(i4);
        throw new IllegalArgumentException(sb.toString());
    }
}
