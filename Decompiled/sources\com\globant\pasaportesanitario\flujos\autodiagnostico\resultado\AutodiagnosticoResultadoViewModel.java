package com.globant.pasaportesanitario.flujos.autodiagnostico.resultado;

import android.util.Log;
import androidx.lifecycle.LiveData;
import b.o.q;
import com.globant.pasaportesanitario.data.repositorios.RepositorioAutoevaluacion;
import com.globant.pasaportesanitario.flujos.BaseViewModel;
import com.globant.pasaportesanitario.flujos.pantallaprincipal.PantallaPrincipalRepository;
import e.a.h;
import e.a.k.a;
import e.a.m.b;
import java.util.concurrent.Callable;

public class AutodiagnosticoResultadoViewModel extends BaseViewModel {
    public RepositorioAutoevaluacion autoevaluacionRepository;
    public a disposables = new a();
    public q<c.c.a.e.e.a<Boolean>> eventoLoadingDialog = new q<>();
    public q<String> nombreUsuario = new q<>();

    public AutodiagnosticoResultadoViewModel(RepositorioAutoevaluacion repositorioAutoevaluacion, c.c.a.d.a aVar, PantallaPrincipalRepository pantallaPrincipalRepository) {
        super(aVar, pantallaPrincipalRepository);
        this.autoevaluacionRepository = repositorioAutoevaluacion;
    }

    public void cargarNombreUsuario() {
        this.disposables.c(h.a((Callable<? extends T>) new Callable<String>() {
            public String call() throws Exception {
                return AutodiagnosticoResultadoViewModel.this.autoevaluacionRepository.obtenerNombreUsuario();
            }
        }).b(e.a.p.a.f5172b).a(e.a.j.a.a.a()).a(new b<String>() {
            public void accept(String str) throws Exception {
                AutodiagnosticoResultadoViewModel.this.nombreUsuario.b(str);
            }
        }, new b<Throwable>() {
            public void accept(Throwable th) throws Exception {
                AutodiagnosticoResultadoViewModel.this.eventoLoadingDialog.b(new c.c.a.e.e.a(Boolean.valueOf(false)));
                StringBuilder sb = new StringBuilder();
                sb.append("Error obteniendo el nombre del usuario: ");
                sb.append(th.getLocalizedMessage());
                Log.e("Autodiagnostico", sb.toString());
            }
        }));
    }

    public LiveData<c.c.a.e.e.a<Boolean>> obtenerEventoDeDialogo() {
        return this.eventoLoadingDialog;
    }

    public void onCleared() {
        super.onCleared();
        this.disposables.a();
    }
}
