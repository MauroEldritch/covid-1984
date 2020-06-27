package com.globant.pasaportesanitario.flujos.pantallaprincipal;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import ar.gob.coronavirus.R;
import b.m.d.a;
import b.m.d.o;
import b.o.a0;
import b.o.r;
import b.o.y;
import b.o.z.c;
import b.o.z.e;
import c.c.a.e.f.b;
import com.globant.pasaportesanitario.data.NombreEstado;
import com.globant.pasaportesanitario.data.local.modelo.UsuarioBD;
import com.globant.pasaportesanitario.flujos.BaseActivity;
import com.globant.pasaportesanitario.flujos.autodiagnostico.AutodiagnosticoActivity;
import com.globant.pasaportesanitario.flujos.autodiagnostico.resultado.ResultadoActivity;
import com.globant.pasaportesanitario.flujos.autodiagnostico.resultado.ResultadoActivity.OpcionesNavegacion;
import com.globant.pasaportesanitario.flujos.identificacion.IdentificacionActivity;
import com.globant.pasaportesanitario.flujos.pantallaprincipal.PantallaPrincipalViewModel.NavegacionDestinosPantallaPrincipal;
import com.globant.pasaportesanitario.flujos.pantallaprincipal.ui.pantallaprincipal.CircularFragment;
import com.globant.pasaportesanitario.flujos.pantallaprincipal.ui.pantallaprincipal.CovidPositivoFragment;
import com.globant.pasaportesanitario.flujos.pantallaprincipal.ui.pantallaprincipal.DerivadoASaludFragment;
import com.globant.pasaportesanitario.flujos.pantallaprincipal.ui.pantallaprincipal.NoInfectadoFragment;
import com.globant.pasaportesanitario.utils.dialogs.PantallaCompletaDialog;

public class PantallaPrincipalActivity extends BaseActivity {
    public static final String LLAVE_MOSTRAR_RESULTADO = "LLAVE_MOSTRAR_RESULTADO";
    public Dialog loadingDialog;
    public PantallaPrincipalViewModel mViewModel;
    public boolean mostrarResultado;
    public TextView nombreDeUsuario;
    public TextView numeroDni;

    /* renamed from: com.globant.pasaportesanitario.flujos.pantallaprincipal.PantallaPrincipalActivity$5 reason: invalid class name */
    public static /* synthetic */ class AnonymousClass5 {
        public static final /* synthetic */ int[] $SwitchMap$com$globant$pasaportesanitario$flujos$pantallaprincipal$PantallaPrincipalViewModel$NavegacionDestinosPantallaPrincipal;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x002b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x000f */
        static {
            int[] iArr = new int[NavegacionDestinosPantallaPrincipal.values().length];
            $SwitchMap$com$globant$pasaportesanitario$flujos$pantallaprincipal$PantallaPrincipalViewModel$NavegacionDestinosPantallaPrincipal = iArr;
            NavegacionDestinosPantallaPrincipal navegacionDestinosPantallaPrincipal = NavegacionDestinosPantallaPrincipal.CIRCULAR;
            iArr[0] = 1;
            try {
                int[] iArr2 = $SwitchMap$com$globant$pasaportesanitario$flujos$pantallaprincipal$PantallaPrincipalViewModel$NavegacionDestinosPantallaPrincipal;
                NavegacionDestinosPantallaPrincipal navegacionDestinosPantallaPrincipal2 = NavegacionDestinosPantallaPrincipal.NO_INFECTADO;
                iArr2[2] = 2;
            } catch (NoSuchFieldError unused) {
            }
            try {
                int[] iArr3 = $SwitchMap$com$globant$pasaportesanitario$flujos$pantallaprincipal$PantallaPrincipalViewModel$NavegacionDestinosPantallaPrincipal;
                NavegacionDestinosPantallaPrincipal navegacionDestinosPantallaPrincipal3 = NavegacionDestinosPantallaPrincipal.INFECTADO;
                iArr3[1] = 3;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                int[] iArr4 = $SwitchMap$com$globant$pasaportesanitario$flujos$pantallaprincipal$PantallaPrincipalViewModel$NavegacionDestinosPantallaPrincipal;
                NavegacionDestinosPantallaPrincipal navegacionDestinosPantallaPrincipal4 = NavegacionDestinosPantallaPrincipal.DERIVADO_A_SALUD_LOCAL;
                iArr4[3] = 4;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr5 = $SwitchMap$com$globant$pasaportesanitario$flujos$pantallaprincipal$PantallaPrincipalViewModel$NavegacionDestinosPantallaPrincipal;
            NavegacionDestinosPantallaPrincipal navegacionDestinosPantallaPrincipal5 = NavegacionDestinosPantallaPrincipal.DEBE_AUTODIAGNOSTICARSE;
            iArr5[4] = 5;
            try {
                int[] iArr6 = $SwitchMap$com$globant$pasaportesanitario$flujos$pantallaprincipal$PantallaPrincipalViewModel$NavegacionDestinosPantallaPrincipal;
                NavegacionDestinosPantallaPrincipal navegacionDestinosPantallaPrincipal6 = NavegacionDestinosPantallaPrincipal.DESLOGUEAR;
                iArr6[5] = 6;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static void iniciar(Context context, boolean z) {
        Intent intent = new Intent(context, PantallaPrincipalActivity.class);
        intent.putExtra(LLAVE_MOSTRAR_RESULTADO, z);
        context.startActivity(intent);
    }

    /* access modifiers changed from: private */
    public boolean puedeUsuarioCircular(String str) {
        return str.equals(NombreEstado.NO_CONTAGIOSO) || str.equals(NombreEstado.NO_INFECTADO);
    }

    private void setupViews() {
        this.nombreDeUsuario = (TextView) findViewById(R.id.nombre_de_usuario);
        this.numeroDni = (TextView) findViewById(R.id.numero_dni);
    }

    public void circular() {
        if (!(getSupportFragmentManager().b((int) R.id.container) instanceof CircularFragment)) {
            o supportFragmentManager = getSupportFragmentManager();
            if (supportFragmentManager != null) {
                a aVar = new a(supportFragmentManager);
                aVar.a(R.id.container, new CircularFragment());
                aVar.c();
                return;
            }
            throw null;
        }
    }

    public void covidPositivo() {
        if (!(getSupportFragmentManager().b((int) R.id.container) instanceof CovidPositivoFragment)) {
            o supportFragmentManager = getSupportFragmentManager();
            if (supportFragmentManager != null) {
                a aVar = new a(supportFragmentManager);
                aVar.a(R.id.container, new CovidPositivoFragment());
                aVar.c();
                return;
            }
            throw null;
        }
    }

    public void derivadoSalud() {
        if (!(getSupportFragmentManager().b((int) R.id.container) instanceof DerivadoASaludFragment)) {
            o supportFragmentManager = getSupportFragmentManager();
            if (supportFragmentManager != null) {
                a aVar = new a(supportFragmentManager);
                aVar.a(R.id.container, new DerivadoASaludFragment());
                aVar.c();
                return;
            }
            throw null;
        }
    }

    public void noInfectado() {
        if (!(getSupportFragmentManager().b((int) R.id.container) instanceof NoInfectadoFragment)) {
            o supportFragmentManager = getSupportFragmentManager();
            if (supportFragmentManager != null) {
                a aVar = new a(supportFragmentManager);
                aVar.a(R.id.container, new NoInfectadoFragment());
                aVar.c();
                return;
            }
            throw null;
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (25 == i && i2 == -1) {
            this.mostrarResultado = true;
            this.mViewModel.limpiarUsuarioActual();
            this.mViewModel.obtenerUsuarioDeLaBD();
        }
    }

    public void onCreate(Bundle bundle) {
        y yVar;
        super.onCreate(bundle);
        setContentView((int) R.layout.pantalla_principal_activity);
        PantallaPrincipalViewModelFactory pantallaPrincipalViewModelFactory = new PantallaPrincipalViewModelFactory(this);
        a0 viewModelStore = getViewModelStore();
        Class<PantallaPrincipalViewModel> cls = PantallaPrincipalViewModel.class;
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            String b2 = c.a.a.a.a.b("androidx.lifecycle.ViewModelProvider.DefaultKey:", canonicalName);
            y yVar2 = (y) viewModelStore.f1739a.get(b2);
            if (!cls.isInstance(yVar2)) {
                if (pantallaPrincipalViewModelFactory instanceof c) {
                    yVar = ((c) pantallaPrincipalViewModelFactory).a(b2, cls);
                } else {
                    yVar = pantallaPrincipalViewModelFactory.create(cls);
                }
                yVar2 = yVar;
                y yVar3 = (y) viewModelStore.f1739a.put(b2, yVar2);
                if (yVar3 != null) {
                    yVar3.onCleared();
                }
            } else if (pantallaPrincipalViewModelFactory instanceof e) {
                ((e) pantallaPrincipalViewModelFactory).a(yVar2);
            }
            PantallaPrincipalViewModel pantallaPrincipalViewModel = (PantallaPrincipalViewModel) yVar2;
            this.mViewModel = pantallaPrincipalViewModel;
            setBaseViewModel(pantallaPrincipalViewModel);
            this.mViewModel.permitirNavegar = false;
            setupViews();
            getLayoutInflater();
            this.loadingDialog = a.a.a.b.a.a((Context) this);
            this.mostrarResultado = getIntent().getBooleanExtra(LLAVE_MOSTRAR_RESULTADO, false);
            this.mViewModel.obtenerUsuarioDeLaBD();
            this.mViewModel.obtenerUltimoEstadoLiveData().a(this, new r<UsuarioBD>() {
                public void onChanged(UsuarioBD usuarioBD) {
                    String str = "%s %s";
                    PantallaPrincipalActivity.this.nombreDeUsuario.setText(String.format(str, new Object[]{usuarioBD.nombres, usuarioBD.apellidos}));
                    PantallaPrincipalActivity.this.numeroDni.setText(b.a(str, a.a.a.b.a.a((Context) PantallaPrincipalActivity.this, "DNI:", (int) R.font.roboto_bold), usuarioBD.dni));
                    PantallaPrincipalActivity pantallaPrincipalActivity = PantallaPrincipalActivity.this;
                    if (pantallaPrincipalActivity.mostrarResultado) {
                        if (pantallaPrincipalActivity.puedeUsuarioCircular(usuarioBD.estadoActual.nombreEstado)) {
                            ResultadoActivity.iniciar(PantallaPrincipalActivity.this, OpcionesNavegacion.RESULTADO_VERDE);
                        } else {
                            ResultadoActivity.iniciar(PantallaPrincipalActivity.this, OpcionesNavegacion.RESULTADO_ROSA);
                        }
                        PantallaPrincipalActivity.this.mostrarResultado = false;
                    }
                }
            });
            this.mViewModel.obtenerUltimoEstadoDeBackend();
            this.mViewModel.obtenerEventosDeNavegacionLiveData().a(this, new r<c.c.a.e.e.a<NavegacionDestinosPantallaPrincipal>>() {
                public void onChanged(c.c.a.e.e.a<NavegacionDestinosPantallaPrincipal> aVar) {
                    NavegacionDestinosPantallaPrincipal navegacionDestinosPantallaPrincipal = (NavegacionDestinosPantallaPrincipal) aVar.a();
                    if (navegacionDestinosPantallaPrincipal != null) {
                        int ordinal = navegacionDestinosPantallaPrincipal.ordinal();
                        if (ordinal == 0) {
                            PantallaPrincipalActivity.this.circular();
                        } else if (ordinal == 1) {
                            PantallaPrincipalActivity.this.covidPositivo();
                        } else if (ordinal == 2) {
                            PantallaPrincipalActivity.this.noInfectado();
                        } else if (ordinal == 3) {
                            PantallaPrincipalActivity.this.derivadoSalud();
                        } else if (ordinal == 4) {
                            AutodiagnosticoActivity.iniciarActividadParaResultado(PantallaPrincipalActivity.this, true);
                        } else if (ordinal == 5) {
                            PantallaPrincipalActivity.this.mViewModel.logout();
                            IdentificacionActivity.iniciarEliminandoPilaNavegacion(PantallaPrincipalActivity.this);
                        }
                    }
                }
            });
            this.mViewModel.obtenerErrorBackend().a(this, new r<c.c.a.e.e.a<Integer>>() {
                public void onChanged(c.c.a.e.e.a<Integer> aVar) {
                    if (aVar.a() != null) {
                        PantallaPrincipalActivity.this.loadingDialog.dismiss();
                        PantallaCompletaDialog.a(PantallaPrincipalActivity.this.getString(R.string.hubo_error), PantallaPrincipalActivity.this.getString(R.string.hubo_error_desc), "Cerrar", R.drawable.ic_error).show(PantallaPrincipalActivity.this.getSupportFragmentManager(), AutodiagnosticoActivity.ERROR_DIALOG_TAG);
                    }
                }
            });
            this.mViewModel.obtenerEventoDeDialogo().a(this, new r<c.c.a.e.e.a<Boolean>>() {
                public void onChanged(c.c.a.e.e.a<Boolean> aVar) {
                    if (aVar.a() == null) {
                        return;
                    }
                    if (((Boolean) aVar.f3123a).booleanValue()) {
                        PantallaPrincipalActivity.this.loadingDialog.show();
                    } else {
                        PantallaPrincipalActivity.this.loadingDialog.dismiss();
                    }
                }
            });
            return;
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }
}
