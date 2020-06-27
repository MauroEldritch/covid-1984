package com.globant.pasaportesanitario.flujos.identificacion;

import android.app.Activity;
import android.content.res.Resources;
import android.location.Geocoder;
import android.util.Log;
import b.o.y;
import b.o.z.b;
import c.c.a.e.d.a;
import com.globant.pasaportesanitario.data.local.BaseDeDatos;
import com.globant.pasaportesanitario.data.remoto.Api;
import com.globant.pasaportesanitario.data.remoto.CovidRetrofit;
import com.globant.pasaportesanitario.data.remoto.interceptores.EncabezadosInterceptor;
import com.globant.pasaportesanitario.data.repositorios.RepositorioLogout;
import com.globant.pasaportesanitario.flujos.BaseActivity;
import com.globant.pasaportesanitario.flujos.pantallaprincipal.PantallaPrincipalRepository;
import java.util.Locale;

public class IdentificacionViewModelFactory implements b {
    public BaseActivity context;
    public IdentificacionNavegador identificacionNavegador;

    public IdentificacionViewModelFactory(BaseActivity baseActivity, IdentificacionNavegador identificacionNavegador2) {
        this.context = baseActivity;
        this.identificacionNavegador = identificacionNavegador2;
    }

    public <T extends y> T create(Class<T> cls) {
        Class<T> cls2 = cls;
        a a2 = a.a(this.context);
        Api api = new Api(new CovidRetrofit(new EncabezadosInterceptor(a2, this.context)));
        Geocoder geocoder = new Geocoder(this.context, Locale.getDefault());
        c.d.a.a.f.a aVar = new c.d.a.a.f.a((Activity) this.context);
        Resources resources = this.context.getResources();
        BaseDeDatos obtenerbaseDeDatos = BaseDeDatos.obtenerbaseDeDatos(this.context);
        IdentificacionRepository identificacionRepository = new IdentificacionRepository(api, obtenerbaseDeDatos, a2);
        c.c.a.d.b bVar = new c.c.a.d.b(this.context, new c.c.a.d.v.a(this.context));
        PantallaPrincipalRepository pantallaPrincipalRepository = new PantallaPrincipalRepository(api, obtenerbaseDeDatos);
        RepositorioLogout repositorioLogout = new RepositorioLogout(this.context, a2, obtenerbaseDeDatos);
        try {
            return (y) cls2.getConstructor(new Class[]{IdentificacionRepository.class, Geocoder.class, c.d.a.a.f.a.class, Resources.class, RepositorioLogout.class, IdentificacionNavegador.class, c.c.a.d.a.class, PantallaPrincipalRepository.class}).newInstance(new Object[]{identificacionRepository, geocoder, aVar, resources, repositorioLogout, this.identificacionNavegador, bVar, pantallaPrincipalRepository});
        } catch (Exception unused) {
            Log.e("viewModel", "error");
            StringBuilder sb = new StringBuilder();
            sb.append("unexpected model class ");
            sb.append(cls2);
            throw new IllegalArgumentException(sb.toString());
        }
    }
}
