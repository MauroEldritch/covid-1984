package com.globant.pasaportesanitario.flujos.identificacion;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.location.Geocoder;
import android.location.Location;
import android.os.Handler;
import android.text.TextUtils;
import androidx.lifecycle.LiveData;
import b.o.q;
import c.c.a.e.e.a;
import c.d.a.a.c.m.k.l0;
import c.d.a.a.c.m.k.z;
import c.d.a.a.h.d;
import c.d.a.a.h.e;
import c.d.a.a.h.j;
import c.d.b.l;
import com.globant.pasaportesanitario.data.DniEntidad;
import com.globant.pasaportesanitario.data.Localidad;
import com.globant.pasaportesanitario.data.Localidades;
import com.globant.pasaportesanitario.data.NombreEstado;
import com.globant.pasaportesanitario.data.Provincia;
import com.globant.pasaportesanitario.data.Provincias;
import com.globant.pasaportesanitario.data.local.modelo.DomicilioBD;
import com.globant.pasaportesanitario.data.local.modelo.UsuarioBD;
import com.globant.pasaportesanitario.data.remoto.modelo.DomicilioRemoto;
import com.globant.pasaportesanitario.data.remoto.modelo.GeoRemoto;
import com.globant.pasaportesanitario.data.repositorios.RepositorioLogout;
import com.globant.pasaportesanitario.flujos.BaseViewModel;
import com.globant.pasaportesanitario.flujos.pantallaprincipal.PantallaPrincipalRepository;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import com.newrelic.agent.android.instrumentation.Instrumented;
import e.a.g;
import e.a.h;
import e.a.m.b;
import e.a.n.d.c;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;

@Instrumented
public class IdentificacionViewModel extends BaseViewModel {
    public static String CADENA_VACIA_PRESENTACION = " ";
    public q<a<Boolean>> actualizarUsuarioRespuesta = new q<>();
    public q<DniEntidad> dniEntidadMutableLiveData = new q<>();
    public q<DomicilioRemoto> domicilioRemoto = new q<>();
    public c.d.a.a.f.a fusedLocationProviderClient;
    public GeoRemoto geoRemoto;
    public Geocoder geocoder;
    public IdentificacionRepository identificacionRepository;
    public q<a<Boolean>> limpiarPantallaLogin = new q<>();
    public Localidad localidad = null;
    public Localidades localidades;
    public q<List<Localidad>> localidadesSpinnerLiveData = new q<>();
    public IdentificacionNavegador navegador;
    public String nroTelefono = "";
    public Provincia provinciaSeleccionado = null;
    public q<Provincias> provinciasMutableLiveData = new q<>();
    public q<a<NavegacionFragments>> registrarUsuarioRespuesta = new q<>();
    public RepositorioLogout repositorioLogout;
    public Resources resources;
    public q<UsuarioBD> usuarioliveData = new q<>();

    public IdentificacionViewModel(IdentificacionRepository identificacionRepository2, Geocoder geocoder2, c.d.a.a.f.a aVar, Resources resources2, RepositorioLogout repositorioLogout2, IdentificacionNavegador identificacionNavegador, c.c.a.d.a aVar2, PantallaPrincipalRepository pantallaPrincipalRepository) {
        super(aVar2, pantallaPrincipalRepository);
        this.identificacionRepository = identificacionRepository2;
        this.geocoder = geocoder2;
        this.fusedLocationProviderClient = aVar;
        this.resources = resources2;
        this.repositorioLogout = repositorioLogout2;
        this.navegador = identificacionNavegador;
        this.localidades = crearObjetoLocalidadesDesdeString();
    }

    @SuppressLint({"CheckResult"})
    public void actualizarUsuario() {
        h.a((Callable<? extends T>) new Callable<Boolean>() {
            public Boolean call() throws Exception {
                IdentificacionRepository access$100 = IdentificacionViewModel.this.identificacionRepository;
                String l = ((UsuarioBD) IdentificacionViewModel.this.usuarioliveData.a()).dni.toString();
                String str = ((UsuarioBD) IdentificacionViewModel.this.usuarioliveData.a()).sexo;
                IdentificacionViewModel identificacionViewModel = IdentificacionViewModel.this;
                return access$100.actualizarUsuario(l, str, identificacionViewModel.nroTelefono, (DomicilioRemoto) identificacionViewModel.domicilioRemoto.a(), IdentificacionViewModel.this.geoRemoto);
            }
        }).b(e.a.p.a.f5172b).a(e.a.j.a.a.a()).a(new b<Boolean>() {
            public void accept(Boolean bool) throws Exception {
                IdentificacionViewModel.this.actualizarUsuarioRespuesta.b(new a(Boolean.valueOf(true)));
            }
        }, new b<Throwable>() {
            public void accept(Throwable th) throws Exception {
                IdentificacionViewModel.this.actualizarUsuarioRespuesta.b(new a(Boolean.valueOf(false)));
            }
        });
    }

    public void construirDomicilioRemoto() {
    }

    public void crearDomicilioRemoto(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        DomicilioRemoto domicilioRemoto2;
        Localidad localidad2 = this.localidad;
        if (localidad2 != null) {
            domicilioRemoto2 = new DomicilioRemoto(str, localidad2.getNombre() != null ? this.localidad.getNombre() : CADENA_VACIA_PRESENTACION, str2, str3, str4, str5, str6, str7, this.localidad.getDepartamentoNombre() != null ? this.localidad.getDepartamentoNombre() : CADENA_VACIA_PRESENTACION);
        } else {
            String str8 = CADENA_VACIA_PRESENTACION;
            domicilioRemoto2 = new DomicilioRemoto(str, str8, str2, str3, str4, str5, str6, str7, str8);
        }
        this.domicilioRemoto.b(domicilioRemoto2);
    }

    public Localidades crearObjetoLocalidadesDesdeString() {
        return (Localidades) GsonInstrumentation.fromJson(new l().a(), obtenerLocalidadesDeAssets(), Localidades.class);
    }

    public void crearObjetoProvinciaDesdeString() {
        Provincias provincias = (Provincias) GsonInstrumentation.fromJson(new l().a(), obtenerProvinciasDeAssets(), Provincias.class);
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(provincias.getProvincias());
        Collections.sort(arrayList, new Comparator<Provincia>() {
            public int compare(Provincia provincia, Provincia provincia2) {
                return provincia.getNombre().compareToIgnoreCase(provincia2.getNombre());
            }
        });
        provincias.setProvincias(arrayList);
        this.provinciasMutableLiveData.b(provincias);
    }

    public void filtrarLocalidades(String str) {
        ArrayList arrayList = new ArrayList();
        for (Localidad localidad2 : this.localidades.getLocalidades()) {
            if (localidad2.getProvinciaId().equals(str)) {
                arrayList.add(localidad2);
            }
        }
        Collections.sort(arrayList, new Comparator<Localidad>() {
            public int compare(Localidad localidad, Localidad localidad2) {
                return localidad.getNombre().compareToIgnoreCase(localidad2.getNombre());
            }
        });
        this.localidadesSpinnerLiveData.b(arrayList);
    }

    public LiveData<DniEntidad> getDniEntidadLiveData() {
        return this.dniEntidadMutableLiveData;
    }

    public void inicializaGeoRemoto() {
        String str = "0";
        this.geoRemoto = new GeoRemoto(str, str, str);
    }

    public void localidadSeleccionada(Localidad localidad2) {
        this.localidad = localidad2;
    }

    public void logout() {
        e.a.a a2 = e.a.a.a((e.a.m.a) new e.a.m.a() {
            public void run() throws Exception {
                IdentificacionViewModel.this.repositorioLogout.Logout();
            }
        }).a(e.a.p.a.f5172b);
        g a3 = e.a.j.a.a.a();
        e.a.n.b.b.a(a3, "scheduler is null");
        e.a.n.e.a.b bVar = new e.a.n.e.a.b(a2, a3);
        AnonymousClass13 r0 = new e.a.m.a() {
            public void run() throws Exception {
                IdentificacionViewModel.this.limpiarPantallaLogin.b(new a(Boolean.valueOf(true)));
            }
        };
        AnonymousClass14 r1 = new b<Throwable>() {
            public void accept(Throwable th) throws Exception {
            }
        };
        e.a.n.b.b.a(r1, "onError is null");
        e.a.n.b.b.a(r0, "onComplete is null");
        bVar.a((e.a.b) new c(r1, r0));
    }

    public void navegarSiguientePantallaDependiendoDelEstado() {
        if (((UsuarioBD) this.usuarioliveData.a()).estadoActual.nombreEstado == NombreEstado.DEBE_AUTODIAGNOSTICARSE) {
            this.navegador.navegarAAutoDiagnosticoActivity();
        } else {
            this.navegador.navegarAPantallaPrincipal(false);
        }
    }

    public LiveData<a<Boolean>> obtenerActualizarUsuarioLiveData() {
        return this.actualizarUsuarioRespuesta;
    }

    public LiveData<a<Boolean>> obtenerLimpiarLogin() {
        return this.limpiarPantallaLogin;
    }

    public String obtenerLocalidadesDeAssets() {
        return a.a.a.b.a.a(this.resources, "localidades.json");
    }

    public LiveData<List<Localidad>> obtenerLocalidadesParaSpinner() {
        return this.localidadesSpinnerLiveData;
    }

    public String obtenerProvinciasDeAssets() {
        return a.a.a.b.a.a(this.resources, "provincias.json");
    }

    public LiveData<Provincias> obtenerProvinciasLiveData() {
        return this.provinciasMutableLiveData;
    }

    public LiveData<a<NavegacionFragments>> obtenerRegistrarUsuarioLiveData() {
        return this.registrarUsuarioRespuesta;
    }

    public void obtenerUbicacionLatLong() {
        c.d.a.a.f.a aVar = this.fusedLocationProviderClient;
        if (aVar != null) {
            c.d.a.a.f.l lVar = new c.d.a.a.f.l();
            d dVar = new d();
            c.d.a.a.c.m.k.c cVar = aVar.f3168g;
            c.d.a.a.c.m.k.a aVar2 = aVar.f3167f;
            if (cVar != null) {
                l0 l0Var = new l0(0, lVar, dVar, aVar2);
                Handler handler = cVar.m;
                handler.sendMessage(handler.obtainMessage(4, new z(l0Var, cVar.f3196h.get(), aVar)));
                c.d.a.a.h.l<TResult> lVar2 = dVar.f3461a;
                AnonymousClass10 r2 = new c.d.a.a.h.a<Location>() {
                    public void onComplete(c.d.a.a.h.c<Location> cVar) {
                        Location location = (Location) cVar.a();
                        if (location != null) {
                            IdentificacionViewModel.this.geoRemoto = new GeoRemoto(Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude()), Double.valueOf(location.getAltitude()));
                        }
                    }
                };
                if (lVar2 != null) {
                    lVar2.f3478b.a((j<TResult>) new c.d.a.a.h.h<TResult>(e.f3462a, r2));
                    lVar2.c();
                    return;
                }
                throw null;
            }
            throw null;
        }
        throw null;
    }

    @SuppressLint({"CheckResult"})
    public void obtenerUsuario() {
        h.a((Callable<? extends T>) new Callable<UsuarioBD>() {
            public UsuarioBD call() throws Exception {
                return IdentificacionViewModel.this.identificacionRepository.obtenerUsuario();
            }
        }).b(e.a.p.a.f5172b).a(e.a.j.a.a.a()).a(new b<UsuarioBD>() {
            public void accept(UsuarioBD usuarioBD) throws Exception {
                IdentificacionViewModel.this.usuarioliveData.b(usuarioBD);
            }
        }, new b<Throwable>() {
            public void accept(Throwable th) throws Exception {
            }
        });
    }

    public LiveData<UsuarioBD> obtenerUsuarioliveData() {
        return this.usuarioliveData;
    }

    public void procesarCodigoQr(String str) {
        this.dniEntidadMutableLiveData.b(new DniEntidad().construirDni(str));
    }

    @SuppressLint({"CheckResult"})
    public void registrarUsuario(final String str, final String str2, final String str3) {
        h.a((Callable<? extends T>) new Callable<UsuarioBD>() {
            public UsuarioBD call() throws Exception {
                if (IdentificacionViewModel.this.identificacionRepository.autorizarUsuario(str, str3, str2.replaceFirst("^0+(?!$)", "")).booleanValue()) {
                    return IdentificacionViewModel.this.identificacionRepository.registrarUsuario(str, str3);
                }
                return null;
            }
        }).b(e.a.p.a.f5172b).a(e.a.j.a.a.a()).a(new b<UsuarioBD>() {
            public void accept(UsuarioBD usuarioBD) throws Exception {
                DomicilioBD domicilioBD = usuarioBD.domicilio;
                if (domicilioBD == null || TextUtils.isEmpty(domicilioBD.provincia)) {
                    IdentificacionViewModel.this.registrarUsuarioRespuesta.b(new a(NavegacionFragments.IDENTIFICACION));
                } else if (usuarioBD.estadoActual.nombreEstado.equals(NombreEstado.DEBE_AUTODIAGNOSTICARSE)) {
                    IdentificacionViewModel.this.registrarUsuarioRespuesta.b(new a(NavegacionFragments.AUTODIAGNOSTICO));
                } else {
                    IdentificacionViewModel.this.registrarUsuarioRespuesta.b(new a(NavegacionFragments.PRINCIPAL));
                }
            }
        }, new b<Throwable>() {
            public void accept(Throwable th) throws Exception {
                IdentificacionViewModel.this.registrarUsuarioRespuesta.b(new a(NavegacionFragments.ERROR));
            }
        });
    }
}
