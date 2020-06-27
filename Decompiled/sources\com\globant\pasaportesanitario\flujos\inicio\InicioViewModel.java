package com.globant.pasaportesanitario.flujos.inicio;

import android.app.Application;
import android.content.res.Resources;
import android.text.TextUtils;
import androidx.lifecycle.LiveData;
import b.o.q;
import c.c.a.e.e.a;
import com.globant.pasaportesanitario.data.NombreEstado;
import com.globant.pasaportesanitario.data.local.modelo.DomicilioBD;
import com.globant.pasaportesanitario.data.local.modelo.UsuarioBD;
import com.globant.pasaportesanitario.flujos.BaseViewModel;
import com.globant.pasaportesanitario.flujos.identificacion.IdentificacionRepository;
import com.globant.pasaportesanitario.flujos.pantallaprincipal.PantallaPrincipalRepository;
import e.a.h;
import e.a.m.b;
import java.util.concurrent.Callable;

public class InicioViewModel extends BaseViewModel {
    public final Application application;
    public q<a<NavegacionFragments>> eventosDeNavegacion = new q<>();
    public IdentificacionRepository identificacionRepository;
    public Resources resources;

    public InicioViewModel(IdentificacionRepository identificacionRepository2, Resources resources2, Application application2, c.c.a.d.a aVar, PantallaPrincipalRepository pantallaPrincipalRepository) {
        super(aVar, pantallaPrincipalRepository);
        this.identificacionRepository = identificacionRepository2;
        this.resources = resources2;
        this.application = application2;
    }

    public void navegarSiguientePantalla() {
        if (TextUtils.isEmpty(c.c.a.e.d.a.a(this.application).a())) {
            this.eventosDeNavegacion.b(new a(NavegacionFragments.LOGIN));
        } else {
            h.a((Callable<? extends T>) new Callable<UsuarioBD>() {
                public UsuarioBD call() throws Exception {
                    return InicioViewModel.this.identificacionRepository.obtenerUsuario();
                }
            }).b(e.a.p.a.f5172b).a(e.a.j.a.a.a()).a(new b<UsuarioBD>() {
                public void accept(UsuarioBD usuarioBD) throws Exception {
                    DomicilioBD domicilioBD = usuarioBD.domicilio;
                    if (domicilioBD == null || TextUtils.isEmpty(domicilioBD.provincia)) {
                        InicioViewModel.this.eventosDeNavegacion.b(new a(NavegacionFragments.LOGIN));
                    } else if (usuarioBD.estadoActual.nombreEstado.equals(NombreEstado.DEBE_AUTODIAGNOSTICARSE)) {
                        InicioViewModel.this.eventosDeNavegacion.b(new a(NavegacionFragments.DIAGNOSTICO));
                    } else {
                        InicioViewModel.this.eventosDeNavegacion.b(new a(NavegacionFragments.PRINCIPAL));
                    }
                }
            }, new b<Throwable>() {
                public void accept(Throwable th) throws Exception {
                    InicioViewModel.this.eventosDeNavegacion.b(new a(NavegacionFragments.LOGIN));
                }
            });
        }
    }

    public LiveData<a<NavegacionFragments>> obtenerEventosDeNavegacionLiveData() {
        return this.eventosDeNavegacion;
    }
}
