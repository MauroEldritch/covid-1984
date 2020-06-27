package com.globant.pasaportesanitario.flujos.pantallaprincipal;

import b.o.y;
import b.o.z.b;
import c.c.a.e.d.a;
import com.globant.pasaportesanitario.data.local.BaseDeDatos;
import com.globant.pasaportesanitario.data.remoto.Api;
import com.globant.pasaportesanitario.data.remoto.CovidRetrofit;
import com.globant.pasaportesanitario.data.remoto.interceptores.EncabezadosInterceptor;
import com.globant.pasaportesanitario.data.repositorios.RepositorioLogout;
import com.globant.pasaportesanitario.flujos.BaseActivity;

public class PantallaPrincipalViewModelFactory implements b {
    public BaseActivity context;

    public PantallaPrincipalViewModelFactory(BaseActivity baseActivity) {
        this.context = baseActivity;
    }

    public <T extends y> T create(Class<T> cls) {
        a a2 = a.a(this.context);
        Api api = new Api(new CovidRetrofit(new EncabezadosInterceptor(a2, this.context)));
        BaseDeDatos obtenerbaseDeDatos = BaseDeDatos.obtenerbaseDeDatos(this.context);
        RepositorioLogout repositorioLogout = new RepositorioLogout(this.context, a2, obtenerbaseDeDatos);
        c.c.a.d.b bVar = new c.c.a.d.b(this.context, new c.c.a.d.v.a(this.context));
        PantallaPrincipalRepository pantallaPrincipalRepository = new PantallaPrincipalRepository(api, obtenerbaseDeDatos);
        try {
            return (y) cls.getConstructor(new Class[]{PantallaPrincipalRepository.class, RepositorioLogout.class, c.c.a.d.a.class}).newInstance(new Object[]{pantallaPrincipalRepository, repositorioLogout, bVar});
        } catch (Exception e2) {
            e2.printStackTrace();
            StringBuilder sb = new StringBuilder();
            sb.append("unexpected model class ");
            sb.append(cls);
            throw new IllegalArgumentException(sb.toString());
        }
    }
}
