package com.globant.pasaportesanitario.flujos.autodiagnostico;

import b.o.y;
import b.o.z.b;
import c.c.a.d.a;
import com.globant.pasaportesanitario.data.local.BaseDeDatos;
import com.globant.pasaportesanitario.data.remoto.Api;
import com.globant.pasaportesanitario.data.remoto.CovidRetrofit;
import com.globant.pasaportesanitario.data.remoto.interceptores.EncabezadosInterceptor;
import com.globant.pasaportesanitario.data.repositorios.RepositorioAutoevaluacion;
import com.globant.pasaportesanitario.data.repositorios.RepositorioLogout;
import com.globant.pasaportesanitario.flujos.BaseActivity;
import com.globant.pasaportesanitario.flujos.autodiagnostico.resultado.AutodiagnosticoResultadoViewModel;
import com.globant.pasaportesanitario.flujos.pantallaprincipal.PantallaPrincipalRepository;

public class AutoevaluacionViewModelFactory implements b {
    public BaseActivity context;

    public AutoevaluacionViewModelFactory(BaseActivity baseActivity) {
        this.context = baseActivity;
    }

    public <T extends y> T create(Class<T> cls) {
        Class<a> cls2 = a.class;
        Class<PantallaPrincipalRepository> cls3 = PantallaPrincipalRepository.class;
        Class<RepositorioAutoevaluacion> cls4 = RepositorioAutoevaluacion.class;
        c.c.a.e.d.a a2 = c.c.a.e.d.a.a(this.context);
        Api api = new Api(new CovidRetrofit(new EncabezadosInterceptor(a2, this.context)));
        BaseDeDatos obtenerbaseDeDatos = BaseDeDatos.obtenerbaseDeDatos(this.context);
        RepositorioAutoevaluacion repositorioAutoevaluacion = new RepositorioAutoevaluacion(api, obtenerbaseDeDatos);
        RepositorioLogout repositorioLogout = new RepositorioLogout(this.context, a2, obtenerbaseDeDatos);
        PantallaPrincipalRepository pantallaPrincipalRepository = new PantallaPrincipalRepository(api, obtenerbaseDeDatos);
        c.c.a.d.b bVar = new c.c.a.d.b(this.context, new c.c.a.d.v.a(this.context));
        try {
            if (AutodiagnosticoViewModel.class.equals(cls)) {
                return (y) cls.getConstructor(new Class[]{cls4, RepositorioLogout.class, cls3, cls2}).newInstance(new Object[]{repositorioAutoevaluacion, repositorioLogout, pantallaPrincipalRepository, bVar});
            }
            if (AutodiagnosticoResultadoViewModel.class.equals(cls)) {
                return (y) cls.getConstructor(new Class[]{cls4, cls2, cls3}).newInstance(new Object[]{repositorioAutoevaluacion, bVar, pantallaPrincipalRepository});
            }
            StringBuilder sb = new StringBuilder();
            sb.append("unexpected model class ");
            sb.append(cls);
            throw new IllegalArgumentException(sb.toString());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
