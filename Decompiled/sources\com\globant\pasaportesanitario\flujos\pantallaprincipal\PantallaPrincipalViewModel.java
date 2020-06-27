package com.globant.pasaportesanitario.flujos.pantallaprincipal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import androidx.lifecycle.LiveData;
import b.o.q;
import com.globant.pasaportesanitario.data.NombreEstado;
import com.globant.pasaportesanitario.data.local.modelo.PermisoCirculacionBD;
import com.globant.pasaportesanitario.data.local.modelo.UsuarioBD;
import com.globant.pasaportesanitario.data.repositorios.RepositorioLogout;
import com.globant.pasaportesanitario.flujos.BaseViewModel;
import e.a.h;
import e.a.k.a;
import e.a.m.b;
import e.a.n.d.e;
import java.util.concurrent.Callable;

public class PantallaPrincipalViewModel extends BaseViewModel {
    public final a disposables = new a();
    public q<c.c.a.e.e.a<Integer>> eventoErrorBackend = new q<>();
    public q<c.c.a.e.e.a<Boolean>> eventoLoadingDialog = new q<>();
    public q<c.c.a.e.e.a<NavegacionDestinosPantallaPrincipal>> eventosDeNavegacion = new q<>();
    public q<c.c.a.e.e.a<Intent>> levantarWebPermisoCirculacion = new q<>();
    public PantallaPrincipalRepository pantallaPrincipalRepository;
    public boolean permitirNavegar;
    public RepositorioLogout repositorioLogout;
    public UsuarioBD usuarioActual;
    public q<UsuarioBD> usuarioLiveData = new q<>();

    public enum NavegacionDestinosPantallaPrincipal {
        CIRCULAR,
        INFECTADO,
        NO_INFECTADO,
        DERIVADO_A_SALUD_LOCAL,
        DEBE_AUTODIAGNOSTICARSE,
        DESLOGUEAR
    }

    public PantallaPrincipalViewModel(PantallaPrincipalRepository pantallaPrincipalRepository2, RepositorioLogout repositorioLogout2, c.c.a.d.a aVar) {
        super(aVar, pantallaPrincipalRepository2);
        this.pantallaPrincipalRepository = pantallaPrincipalRepository2;
        this.repositorioLogout = repositorioLogout2;
    }

    public void actualizarDatosUsuario() {
        this.disposables.c(h.a((Callable<? extends T>) new Callable<UsuarioBD>() {
            public UsuarioBD call() throws Exception {
                return PantallaPrincipalViewModel.this.pantallaPrincipalRepository.getUsuario();
            }
        }).b(e.a.p.a.f5172b).a(e.a.j.a.a.a()).a(new b<UsuarioBD>() {
            public void accept(UsuarioBD usuarioBD) throws Exception {
                PantallaPrincipalViewModel.this.usuarioActual = usuarioBD;
                PantallaPrincipalViewModel.this.usuarioLiveData.b(usuarioBD);
                PantallaPrincipalViewModel.this.evaluarUltimoEstadoDelUsuario(usuarioBD);
            }
        }, new b<Throwable>() {
            public void accept(Throwable th) throws Exception {
                Log.w("UserUpdate", "Error actualizando los datos de usuario");
            }
        }));
    }

    public void despacharEventoErrorBackend(Integer num) {
        this.eventoErrorBackend.b(new c.c.a.e.e.a(num));
    }

    public void despacharEventoNavegacion() {
        if (this.permitirNavegar) {
            UsuarioBD usuarioBD = this.usuarioActual;
            if (usuarioBD == null) {
                obtenerNavegador(NavegacionDestinosPantallaPrincipal.DESLOGUEAR);
                return;
            }
            String str = usuarioBD.estadoActual.nombreEstado;
            this.eventoLoadingDialog.b(new c.c.a.e.e.a(Boolean.valueOf(true)));
            char c2 = 65535;
            switch (str.hashCode()) {
                case -794967207:
                    if (str.equals(NombreEstado.NO_INFECTADO)) {
                        c2 = 3;
                        break;
                    }
                    break;
                case -559334313:
                    if (str.equals(NombreEstado.INFECTADO)) {
                        c2 = 0;
                        break;
                    }
                    break;
                case -541344566:
                    if (str.equals(NombreEstado.DERIVADO_A_SALUD_LOCAL)) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 1656715636:
                    if (str.equals(NombreEstado.DEBE_AUTODIAGNOSTICARSE)) {
                        c2 = 4;
                        break;
                    }
                    break;
                case 1913666072:
                    if (str.equals(NombreEstado.NO_CONTAGIOSO)) {
                        c2 = 2;
                        break;
                    }
                    break;
            }
            if (c2 == 0) {
                obtenerNavegador(NavegacionDestinosPantallaPrincipal.INFECTADO);
            } else if (c2 == 1) {
                obtenerNavegador(NavegacionDestinosPantallaPrincipal.DERIVADO_A_SALUD_LOCAL);
            } else if (c2 == 2 || c2 == 3) {
                PermisoCirculacionBD permisoCirculacionBD = this.usuarioActual.estadoActual.permisoCirculacionBD;
                if (permisoCirculacionBD == null || TextUtils.isEmpty(permisoCirculacionBD.permisoQr)) {
                    obtenerNavegador(NavegacionDestinosPantallaPrincipal.NO_INFECTADO);
                } else {
                    obtenerNavegador(NavegacionDestinosPantallaPrincipal.CIRCULAR);
                }
            } else if (c2 == 4) {
                obtenerNavegador(NavegacionDestinosPantallaPrincipal.DEBE_AUTODIAGNOSTICARSE);
            }
            this.eventoLoadingDialog.b(new c.c.a.e.e.a(Boolean.valueOf(false)));
        }
    }

    @SuppressLint({"CheckResult"})
    public void habilitarCirculacion() {
        this.eventoLoadingDialog.b(new c.c.a.e.e.a(Boolean.valueOf(true)));
        h.a((Callable<? extends T>) new Callable<UsuarioBD>() {
            public UsuarioBD call() throws Exception {
                return PantallaPrincipalViewModel.this.pantallaPrincipalRepository.getUsuario();
            }
        }).b(e.a.p.a.f5172b).a(e.a.j.a.a.a()).a(new b<UsuarioBD>() {
            public void accept(UsuarioBD usuarioBD) throws Exception {
                PantallaPrincipalViewModel.this.usuarioActual = usuarioBD;
                PantallaPrincipalViewModel.this.eventoLoadingDialog.b(new c.c.a.e.e.a(Boolean.valueOf(false)));
                if (usuarioBD.estadoActual.permisoCirculacionBD.permisoQr.isEmpty()) {
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setData(Uri.parse("https://www.argentina.gob.ar/solicitar-certificado-unico-habilitante-para-circulacion-emergencia-covid-19"));
                    PantallaPrincipalViewModel.this.levantarWebPermisoCirculacion.b(new c.c.a.e.e.a(intent));
                    return;
                }
                PantallaPrincipalViewModel.this.despacharEventoNavegacion();
            }
        }, new b<Throwable>() {
            public void accept(Throwable th) throws Exception {
                PantallaPrincipalViewModel.this.eventoLoadingDialog.b(new c.c.a.e.e.a(Boolean.valueOf(false)));
            }
        });
    }

    public void limpiarUsuarioActual() {
        this.permitirNavegar = false;
        this.usuarioActual = null;
    }

    public void logout() {
        e.a.a.a((e.a.m.a) new e.a.m.a() {
            public void run() throws Exception {
                PantallaPrincipalViewModel.this.repositorioLogout.Logout();
            }
        }).a(e.a.p.a.f5172b).a((e.a.b) new e());
    }

    public LiveData<c.c.a.e.e.a<Integer>> obtenerErrorBackend() {
        return this.eventoErrorBackend;
    }

    public LiveData<c.c.a.e.e.a<Boolean>> obtenerEventoDeDialogo() {
        return this.eventoLoadingDialog;
    }

    public LiveData<c.c.a.e.e.a<NavegacionDestinosPantallaPrincipal>> obtenerEventosDeNavegacionLiveData() {
        return this.eventosDeNavegacion;
    }

    public LiveData<c.c.a.e.e.a<Intent>> obtenerLevantarWebLiveData() {
        return this.levantarWebPermisoCirculacion;
    }

    @SuppressLint({"CheckResult"})
    public void obtenerNavegador(NavegacionDestinosPantallaPrincipal navegacionDestinosPantallaPrincipal) {
        this.eventosDeNavegacion.b(new c.c.a.e.e.a(navegacionDestinosPantallaPrincipal));
    }

    @SuppressLint({"CheckResult"})
    public void obtenerUltimoEstadoDeBackend() {
        this.eventoLoadingDialog.b(new c.c.a.e.e.a(Boolean.valueOf(true)));
        h.a((Callable<? extends T>) new Callable<UsuarioBD>() {
            public UsuarioBD call() throws Exception {
                return PantallaPrincipalViewModel.this.pantallaPrincipalRepository.getUsuario();
            }
        }).b(e.a.p.a.f5172b).a(e.a.j.a.a.a()).a(new b<UsuarioBD>() {
            public void accept(UsuarioBD usuarioBD) throws Exception {
                PantallaPrincipalViewModel.this.usuarioActual = usuarioBD;
                PantallaPrincipalViewModel pantallaPrincipalViewModel = PantallaPrincipalViewModel.this;
                pantallaPrincipalViewModel.permitirNavegar = true;
                pantallaPrincipalViewModel.usuarioLiveData.b(usuarioBD);
                PantallaPrincipalViewModel.this.eventoLoadingDialog.b(new c.c.a.e.e.a(Boolean.valueOf(false)));
                PantallaPrincipalViewModel.this.revisarEstadoDelUsuario(usuarioBD);
            }
        }, new b<Throwable>() {
            public void accept(Throwable th) throws Exception {
                PantallaPrincipalViewModel.this.despacharEventoErrorBackend(Integer.valueOf(0));
            }
        });
    }

    public LiveData<UsuarioBD> obtenerUltimoEstadoLiveData() {
        return this.usuarioLiveData;
    }

    @SuppressLint({"CheckResult"})
    public void obtenerUsuarioDeLaBD() {
        h.a((Callable<? extends T>) new Callable<UsuarioBD>() {
            public UsuarioBD call() throws Exception {
                return PantallaPrincipalViewModel.this.pantallaPrincipalRepository.getUsuario();
            }
        }).b(e.a.p.a.f5172b).a(e.a.j.a.a.a()).a(new b<UsuarioBD>() {
            public void accept(UsuarioBD usuarioBD) throws Exception {
                PantallaPrincipalViewModel.this.usuarioActual = usuarioBD;
                PantallaPrincipalViewModel pantallaPrincipalViewModel = PantallaPrincipalViewModel.this;
                pantallaPrincipalViewModel.permitirNavegar = true;
                pantallaPrincipalViewModel.usuarioLiveData.b(usuarioBD);
                PantallaPrincipalViewModel.this.despacharEventoNavegacion();
            }
        }, new b<Throwable>() {
            public void accept(Throwable th) throws Exception {
                Log.d("PantallaPrincipal", "Error al obtener el ultimo estado");
            }
        });
    }

    public void onCleared() {
        super.onCleared();
        this.disposables.a();
    }
}
