package com.globant.pasaportesanitario.flujos.autodiagnostico;

import android.annotation.SuppressLint;
import android.util.Log;
import b.o.q;
import com.globant.pasaportesanitario.data.NombreEstado;
import com.globant.pasaportesanitario.data.remoto.modelo.UsuarioRemoto;
import com.globant.pasaportesanitario.data.remoto.modelo_autodiagnostico.AntecedentesRemoto;
import com.globant.pasaportesanitario.data.remoto.modelo_autodiagnostico.AutoevaluacionRemoto;
import com.globant.pasaportesanitario.data.remoto.modelo_autodiagnostico.SintomasRemoto;
import com.globant.pasaportesanitario.data.repositorios.RepositorioAutoevaluacion;
import com.globant.pasaportesanitario.data.repositorios.RepositorioLogout;
import com.globant.pasaportesanitario.flujos.BaseViewModel;
import com.globant.pasaportesanitario.flujos.pantallaprincipal.PantallaPrincipalRepository;
import e.a.h;
import e.a.k.a;
import e.a.m.b;
import e.a.n.d.c;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class AutodiagnosticoViewModel extends BaseViewModel {
    public final Map<String, AntecedentesRemoto> antecedentes = new HashMap();
    public final AutoevaluacionRemoto autoevaluacionRemoto;
    public a disposables = new a();
    public final q<c.c.a.e.e.a<EstadoAlPresionarBack>> estadoAlPresionarBack = new q<>();
    public final q<EstadoPantalla> estadoDePantalla = new q<>();
    public q<Integer> pasoActual = new q<>();
    public final RepositorioAutoevaluacion repositorioAutoevaluacion;
    public final RepositorioLogout repositorioLogout;
    public final Map<String, SintomasRemoto> sintomas = new HashMap();
    public double temperatura = 37.0d;

    public enum EstadoAlPresionarBack {
        Diagnosticado,
        DebeDiagnosticarse
    }

    public enum EstadoPantalla {
        PantallaPrincipal,
        ErrorServicio,
        EnviandoAServicio
    }

    public AutodiagnosticoViewModel(RepositorioAutoevaluacion repositorioAutoevaluacion2, RepositorioLogout repositorioLogout2, PantallaPrincipalRepository pantallaPrincipalRepository, c.c.a.d.a aVar) {
        super(aVar, pantallaPrincipalRepository);
        this.repositorioAutoevaluacion = repositorioAutoevaluacion2;
        this.repositorioLogout = repositorioLogout2;
        this.autoevaluacionRemoto = new AutoevaluacionRemoto(0.0d, new ArrayList(), new ArrayList());
    }

    public void agregarAntecedente(AntecedentesRemoto antecedentesRemoto) {
        this.antecedentes.put(antecedentesRemoto.getDescripcion(), antecedentesRemoto);
        this.autoevaluacionRemoto.getAntecedentes().clear();
        this.autoevaluacionRemoto.getAntecedentes().addAll(this.antecedentes.values());
    }

    public void agregarSintoma(SintomasRemoto sintomasRemoto) {
        this.sintomas.put(sintomasRemoto.getId(), sintomasRemoto);
        this.autoevaluacionRemoto.getSintomas().clear();
        this.autoevaluacionRemoto.getSintomas().addAll(this.sintomas.values());
    }

    public void enviarResultadosAutoevaluacion() {
        this.estadoDePantalla.b(EstadoPantalla.EnviandoAServicio);
        this.disposables.c(h.a((Callable<? extends T>) new Callable<UsuarioRemoto>() {
            public UsuarioRemoto call() {
                return AutodiagnosticoViewModel.this.repositorioAutoevaluacion.confirmarAutoevaluacion(AutodiagnosticoViewModel.this.autoevaluacionRemoto);
            }
        }).b(e.a.p.a.f5172b).a(e.a.j.a.a.a()).a(new b<UsuarioRemoto>() {
            public void accept(UsuarioRemoto usuarioRemoto) {
                AutodiagnosticoViewModel.this.estadoDePantalla.b(EstadoPantalla.PantallaPrincipal);
            }
        }, new b<Throwable>() {
            public void accept(Throwable th) {
                AutodiagnosticoViewModel.this.estadoDePantalla.b(EstadoPantalla.ErrorServicio);
            }
        }));
    }

    @SuppressLint({"CheckResult"})
    public void logout() {
        e.a.a a2 = e.a.a.a((e.a.m.a) new e.a.m.a() {
            public void run() throws Exception {
                AutodiagnosticoViewModel.this.repositorioLogout.Logout();
            }
        }).a(e.a.p.a.f5172b);
        AnonymousClass7 r1 = new e.a.m.a() {
            public void run() throws Exception {
                Log.d(AutodiagnosticoViewModel.class.getCanonicalName(), "usuario deslogueado");
            }
        };
        AnonymousClass8 r2 = new b<Throwable>() {
            public void accept(Throwable th) throws Exception {
                Log.d(AutodiagnosticoViewModel.class.getCanonicalName(), "error al desloguear usuario");
            }
        };
        e.a.n.b.b.a(r2, "onError is null");
        e.a.n.b.b.a(r1, "onComplete is null");
        a2.a((e.a.b) new c(r2, r1));
    }

    public void manejarBotonBack() {
        this.disposables.c(h.a((Callable<? extends T>) new Callable<String>() {
            public String call() throws Exception {
                return AutodiagnosticoViewModel.this.repositorioAutoevaluacion.obtenerEstadoUsuario();
            }
        }).b(e.a.p.a.f5172b).a(e.a.j.a.a.a()).a(new b<String>() {
            public void accept(String str) {
                if (str.equals(NombreEstado.DEBE_AUTODIAGNOSTICARSE)) {
                    AutodiagnosticoViewModel.this.estadoAlPresionarBack.b(new c.c.a.e.e.a(EstadoAlPresionarBack.DebeDiagnosticarse));
                } else {
                    AutodiagnosticoViewModel.this.estadoAlPresionarBack.b(new c.c.a.e.e.a(EstadoAlPresionarBack.Diagnosticado));
                }
            }
        }, new b<Throwable>() {
            public void accept(Throwable th) throws Exception {
            }
        }));
    }

    public void modificarAntecedente(String str, boolean z) {
        AntecedentesRemoto antecedentesRemoto = (AntecedentesRemoto) this.antecedentes.get(str);
        if (antecedentesRemoto != null) {
            antecedentesRemoto.setValor(z);
            this.antecedentes.put(str, antecedentesRemoto);
            this.autoevaluacionRemoto.getAntecedentes().clear();
            this.autoevaluacionRemoto.getAntecedentes().addAll(this.antecedentes.values());
        }
    }

    public boolean noTieneAntecedentes() {
        return this.antecedentes.isEmpty();
    }

    public boolean noTieneSintomas() {
        return this.sintomas.isEmpty();
    }

    public AntecedentesRemoto obtenerAntecedente(String str) {
        return (AntecedentesRemoto) this.antecedentes.get(str);
    }

    public SintomasRemoto obtenerSintoma(TipoSintoma tipoSintoma) {
        return (SintomasRemoto) this.sintomas.get(tipoSintoma.value);
    }

    public void onCleared() {
        this.disposables.a();
        super.onCleared();
    }

    public void setTemperatura(double d2) {
        this.autoevaluacionRemoto.setTemperatura(d2);
    }
}
