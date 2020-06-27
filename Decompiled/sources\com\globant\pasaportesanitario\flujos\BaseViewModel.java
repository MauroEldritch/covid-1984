package com.globant.pasaportesanitario.flujos;

import android.util.Log;
import b.o.q;
import b.o.y;
import com.globant.pasaportesanitario.data.local.modelo.UsuarioBD;
import com.globant.pasaportesanitario.flujos.pantallaprincipal.PantallaPrincipalRepository;
import e.a.h;
import e.a.k.a;
import e.a.m.b;
import java.util.concurrent.Callable;

public class BaseViewModel extends y {
    public final a disposables = new a();
    public final c.c.a.d.a lanzadorDeServicioDeRastreo;
    public q<Integer> lanzarDialogoPermisosLocalizacionLiveData = new q<>();
    public final PantallaPrincipalRepository pantallaPrincipalRepository;
    public q<Boolean> resultadoDialogoDePermisoDeUbicacionLivaData = new q<>();

    public BaseViewModel(c.c.a.d.a aVar, PantallaPrincipalRepository pantallaPrincipalRepository2) {
        this.lanzadorDeServicioDeRastreo = aVar;
        this.pantallaPrincipalRepository = pantallaPrincipalRepository2;
    }

    private Callable<UsuarioBD> getUsuarioCallable() {
        return new Callable<UsuarioBD>() {
            public UsuarioBD call() throws Exception {
                return BaseViewModel.this.pantallaPrincipalRepository.getUsuario();
            }
        };
    }

    public void evaluarUltimoEstadoDelUsuario(UsuarioBD usuarioBD) {
        if (this.lanzadorDeServicioDeRastreo.b(usuarioBD)) {
            this.lanzarDialogoPermisosLocalizacionLiveData.b(Integer.valueOf(113));
        }
    }

    public void lanzarDialogoPermisosLocalizacion(int i) {
        this.lanzarDialogoPermisosLocalizacionLiveData.b(Integer.valueOf(i));
    }

    public void lanzarServicioDeRastreo(UsuarioBD usuarioBD) {
        this.lanzadorDeServicioDeRastreo.a(usuarioBD);
    }

    public void lanzarServicoDeRastreo() {
        this.disposables.c(h.a(getUsuarioCallable()).b(e.a.p.a.f5172b).a(e.a.j.a.a.a()).a(new b<UsuarioBD>() {
            public void accept(UsuarioBD usuarioBD) {
                BaseViewModel.this.lanzarServicioDeRastreo(usuarioBD);
            }
        }, new b<Throwable>() {
            public void accept(Throwable th) {
                Log.d(AnonymousClass2.class.getName(), "Error al obtener el usuario");
            }
        }));
    }

    public q<Integer> obtenerLanzarDialogoPermisosLocalizacionLiveData() {
        return this.lanzarDialogoPermisosLocalizacionLiveData;
    }

    public q<Boolean> obtenerResultadoDialogoDePermisoDeUbicacionLivaData() {
        return this.resultadoDialogoDePermisoDeUbicacionLivaData;
    }

    public void revisarEstadoDelUsuario(UsuarioBD usuarioBD) {
        evaluarUltimoEstadoDelUsuario(usuarioBD);
    }

    public void setResultadoDialogoCustomPermisoDeUbicacion(boolean z) {
        this.resultadoDialogoDePermisoDeUbicacionLivaData.b(Boolean.valueOf(z));
    }
}
