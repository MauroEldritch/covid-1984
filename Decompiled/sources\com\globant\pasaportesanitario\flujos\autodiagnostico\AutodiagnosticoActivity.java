package com.globant.pasaportesanitario.flujos.autodiagnostico;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.navigation.NavController;
import ar.gob.coronavirus.R;
import b.o.a0;
import b.o.l;
import b.o.r;
import b.o.y;
import b.o.z.c;
import b.o.z.e;
import b.r.i;
import b.r.k;
import b.r.w.b;
import c.c.a.e.e.a;
import com.globant.pasaportesanitario.flujos.BaseActivity;
import com.globant.pasaportesanitario.flujos.autodiagnostico.AutodiagnosticoViewModel.EstadoAlPresionarBack;
import com.globant.pasaportesanitario.flujos.autodiagnostico.AutodiagnosticoViewModel.EstadoPantalla;
import com.globant.pasaportesanitario.flujos.identificacion.IdentificacionActivity;
import com.globant.pasaportesanitario.flujos.pantallaprincipal.PantallaPrincipalActivity;
import com.globant.pasaportesanitario.utils.dialogs.PantallaCompletaDialog;
import com.google.android.material.appbar.MaterialToolbar;
import java.util.HashSet;

public class AutodiagnosticoActivity extends BaseActivity {
    public static final String ERROR_DIALOG_TAG = "ERROR_DIALOG_TAG";
    public static final String LLAVE_ORIGEN_PRINCIPAL = "LLAVE_ORIGEN_PRINCIPAL";
    public Dialog loaderDialog;
    public boolean vieneDesdePrincipal = false;
    public AutodiagnosticoViewModel viewModel = null;

    /* renamed from: com.globant.pasaportesanitario.flujos.autodiagnostico.AutodiagnosticoActivity$3 reason: invalid class name */
    public static /* synthetic */ class AnonymousClass3 {
        public static final /* synthetic */ int[] $SwitchMap$com$globant$pasaportesanitario$flujos$autodiagnostico$AutodiagnosticoViewModel$EstadoPantalla;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x000f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0016 */
        static {
            int[] iArr = new int[EstadoPantalla.values().length];
            $SwitchMap$com$globant$pasaportesanitario$flujos$autodiagnostico$AutodiagnosticoViewModel$EstadoPantalla = iArr;
            EstadoPantalla estadoPantalla = EstadoPantalla.EnviandoAServicio;
            iArr[2] = 1;
            int[] iArr2 = $SwitchMap$com$globant$pasaportesanitario$flujos$autodiagnostico$AutodiagnosticoViewModel$EstadoPantalla;
            EstadoPantalla estadoPantalla2 = EstadoPantalla.PantallaPrincipal;
            iArr2[0] = 2;
            int[] iArr3 = $SwitchMap$com$globant$pasaportesanitario$flujos$autodiagnostico$AutodiagnosticoViewModel$EstadoPantalla;
            EstadoPantalla estadoPantalla3 = EstadoPantalla.ErrorServicio;
            iArr3[1] = 3;
        }
    }

    private void escucharCambioDePantalla() {
        this.viewModel.estadoDePantalla.a(this, new r<EstadoPantalla>() {
            public void onChanged(EstadoPantalla estadoPantalla) {
                int ordinal = estadoPantalla.ordinal();
                if (ordinal == 0) {
                    AutodiagnosticoActivity.this.navegarPantallaPuedeCircular();
                    AutodiagnosticoActivity.this.loaderDialog.dismiss();
                } else if (ordinal == 1) {
                    AutodiagnosticoActivity.this.loaderDialog.dismiss();
                    AutodiagnosticoActivity.this.showErrorDialog();
                } else if (ordinal == 2) {
                    AutodiagnosticoActivity.this.loaderDialog.show();
                }
            }
        });
    }

    public static void iniciar(Context context, boolean z) {
        Intent intent = new Intent(context, AutodiagnosticoActivity.class);
        intent.putExtra(LLAVE_ORIGEN_PRINCIPAL, z);
        context.startActivity(intent);
    }

    public static void iniciarActividadParaResultado(Activity activity, boolean z) {
        Intent intent = new Intent(activity, AutodiagnosticoActivity.class);
        intent.putExtra(LLAVE_ORIGEN_PRINCIPAL, z);
        activity.startActivityForResult(intent, 25);
    }

    /* access modifiers changed from: private */
    public void navegarPantallaPuedeCircular() {
        if (!this.vieneDesdePrincipal) {
            PantallaPrincipalActivity.iniciar(this, true);
        }
        setResult(-1);
        finish();
    }

    private void reaccionarAlBotonBack() {
        this.viewModel.estadoAlPresionarBack.a(this, new r<a<EstadoAlPresionarBack>>() {
            public void onChanged(a<EstadoAlPresionarBack> aVar) {
                if (aVar.a() != null) {
                    T t = aVar.f3123a;
                    if (t == EstadoAlPresionarBack.DebeDiagnosticarse) {
                        AutodiagnosticoActivity.this.viewModel.logout();
                        IdentificacionActivity.iniciarEliminandoPilaNavegacion(AutodiagnosticoActivity.this.getApplicationContext());
                    } else if (t == EstadoAlPresionarBack.Diagnosticado) {
                        AutodiagnosticoActivity.super.onBackPressed();
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void showErrorDialog() {
        PantallaCompletaDialog.a(getString(R.string.hubo_error), getString(R.string.hubo_error_desc), "Cerrar", R.drawable.ic_error).show(getSupportFragmentManager(), ERROR_DIALOG_TAG);
    }

    public void onBackPressed() {
        if (a.a.a.b.a.a((Activity) this, (int) R.id.nav_autodiagnostico).b().f1811d.getInt("pasoActual") == 1) {
            this.viewModel.manejarBotonBack();
        } else {
            super.onBackPressed();
        }
    }

    public void onCreate(Bundle bundle) {
        y yVar;
        super.onCreate(bundle);
        c.c.a.b.a a2 = c.c.a.b.a.a(getLayoutInflater());
        setContentView(a2.f265f);
        this.vieneDesdePrincipal = getIntent().getBooleanExtra(LLAVE_ORIGEN_PRINCIPAL, false);
        AutoevaluacionViewModelFactory autoevaluacionViewModelFactory = new AutoevaluacionViewModelFactory(this);
        a0 viewModelStore = getViewModelStore();
        Class<AutodiagnosticoViewModel> cls = AutodiagnosticoViewModel.class;
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            String b2 = c.a.a.a.a.b("androidx.lifecycle.ViewModelProvider.DefaultKey:", canonicalName);
            y yVar2 = (y) viewModelStore.f1739a.get(b2);
            if (!cls.isInstance(yVar2)) {
                if (autoevaluacionViewModelFactory instanceof c) {
                    yVar = ((c) autoevaluacionViewModelFactory).a(b2, cls);
                } else {
                    yVar = autoevaluacionViewModelFactory.create(cls);
                }
                yVar2 = yVar;
                y yVar3 = (y) viewModelStore.f1739a.put(b2, yVar2);
                if (yVar3 != null) {
                    yVar3.onCleared();
                }
            } else if (autoevaluacionViewModelFactory instanceof e) {
                ((e) autoevaluacionViewModelFactory).a(yVar2);
            }
            AutodiagnosticoViewModel autodiagnosticoViewModel = (AutodiagnosticoViewModel) yVar2;
            this.viewModel = autodiagnosticoViewModel;
            a2.a(autodiagnosticoViewModel);
            a2.a((l) this);
            setSupportActionBar(a2.t);
            getSupportActionBar().c(false);
            NavController a3 = a.a.a.b.a.a((Activity) this, (int) R.id.nav_autodiagnostico);
            i iVar = a3.f337d;
            if (iVar != null) {
                HashSet hashSet = new HashSet();
                while (iVar instanceof k) {
                    k kVar = (k) iVar;
                    iVar = kVar.b(kVar.k);
                }
                hashSet.add(Integer.valueOf(iVar.f1832d));
                b bVar = new b(hashSet, null, null, null);
                MaterialToolbar materialToolbar = a2.t;
                b.r.w.e eVar = new b.r.w.e(materialToolbar, bVar);
                if (!a3.f341h.isEmpty()) {
                    b.r.e eVar2 = (b.r.e) a3.f341h.peekLast();
                    eVar.a(a3, eVar2.f1810c, eVar2.f1811d);
                }
                a3.l.add(eVar);
                materialToolbar.setNavigationOnClickListener(new b.r.w.c(a3, bVar));
                escucharCambioDePantalla();
                reaccionarAlBotonBack();
                getLayoutInflater();
                this.loaderDialog = a.a.a.b.a.a((Context) this);
                return;
            }
            throw new IllegalStateException("You must call setGraph() before calling getGraph()");
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }
}
