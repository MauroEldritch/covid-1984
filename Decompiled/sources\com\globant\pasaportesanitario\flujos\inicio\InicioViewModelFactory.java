package com.globant.pasaportesanitario.flujos.inicio;

import android.app.Application;
import android.content.res.Resources;
import android.util.Log;
import b.o.y;
import b.o.z.b;
import c.c.a.e.d.a;
import com.globant.pasaportesanitario.data.local.BaseDeDatos;
import com.globant.pasaportesanitario.data.remoto.Api;
import com.globant.pasaportesanitario.data.remoto.CovidRetrofit;
import com.globant.pasaportesanitario.data.remoto.interceptores.EncabezadosInterceptor;
import com.globant.pasaportesanitario.flujos.BaseActivity;
import com.globant.pasaportesanitario.flujos.identificacion.IdentificacionRepository;
import com.globant.pasaportesanitario.flujos.pantallaprincipal.PantallaPrincipalRepository;

public class InicioViewModelFactory implements b {
    public Application application;
    public BaseActivity context;

    public InicioViewModelFactory(BaseActivity baseActivity, Application application2) {
        this.context = baseActivity;
        this.application = application2;
    }

    public <T extends y> T create(Class<T> cls) {
        a a2 = a.a(this.context);
        Api api = new Api(new CovidRetrofit(new EncabezadosInterceptor(a2, this.context)));
        Resources resources = this.context.getResources();
        BaseDeDatos obtenerbaseDeDatos = BaseDeDatos.obtenerbaseDeDatos(this.context);
        IdentificacionRepository identificacionRepository = new IdentificacionRepository(api, obtenerbaseDeDatos, a2);
        c.c.a.d.b bVar = new c.c.a.d.b(this.context, new c.c.a.d.v.a(this.context));
        PantallaPrincipalRepository pantallaPrincipalRepository = new PantallaPrincipalRepository(api, obtenerbaseDeDatos);
        try {
            return (y) cls.getConstructor(new Class[]{IdentificacionRepository.class, Resources.class, Application.class, c.c.a.d.a.class, PantallaPrincipalRepository.class}).newInstance(new Object[]{identificacionRepository, resources, this.application, bVar, pantallaPrincipalRepository});
        } catch (Exception unused) {
            Log.e("viewModel", "error");
            StringBuilder sb = new StringBuilder();
            sb.append("unexpected model class ");
            sb.append(cls);
            throw new IllegalArgumentException(sb.toString());
        }
    }
}
