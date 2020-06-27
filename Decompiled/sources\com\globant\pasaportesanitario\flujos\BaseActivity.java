package com.globant.pasaportesanitario.flujos;

import a.a.a.b.a;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import android.util.Log;
import ar.gob.coronavirus.R;
import b.b.k.i;
import b.o.r;
import c.c.a.d.d;
import c.c.a.d.m;
import c.c.a.e.b;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;

@Instrumented
public class BaseActivity extends i implements b, TraceFieldInterface {
    public Trace _nr_trace;
    public AlertDialog dialogoDePermisoDeUbicacion;
    public AlertDialog dialogoParaCambiarManualmentePermisosDeUbicacion;
    public m receiverConectividad;
    public BaseViewModel viewModel;

    /* renamed from: com.globant.pasaportesanitario.flujos.BaseActivity$6 reason: invalid class name */
    public static /* synthetic */ class AnonymousClass6 {
        public static final /* synthetic */ int[] $SwitchMap$com$globant$pasaportesanitario$locationtracker$PermisoDeUbicacion;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x001d */
        static {
            int[] iArr = new int[d.values().length];
            $SwitchMap$com$globant$pasaportesanitario$locationtracker$PermisoDeUbicacion = iArr;
            try {
                d dVar = d.SIN_PERMISO;
                iArr[0] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                int[] iArr2 = $SwitchMap$com$globant$pasaportesanitario$locationtracker$PermisoDeUbicacion;
                d dVar2 = d.TODO_EL_TIEMPO;
                iArr2[1] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr3 = $SwitchMap$com$globant$pasaportesanitario$locationtracker$PermisoDeUbicacion;
            d dVar3 = d.SOLO_CON_LA_APLICACION_VISIBLE;
            iArr3[2] = 3;
            try {
                int[] iArr4 = $SwitchMap$com$globant$pasaportesanitario$locationtracker$PermisoDeUbicacion;
                d dVar4 = d.NUNCA;
                iArr4[3] = 4;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private void capturarUbicacionDeUsuarioParaUsarUnaSolaVez(int i, String[] strArr, int[] iArr) {
        int i2 = VERSION.SDK_INT;
        if (i2 >= 29) {
            d a2 = a.a(strArr, iArr);
            if (a2 == d.TODO_EL_TIEMPO) {
                this.viewModel.setResultadoDialogoCustomPermisoDeUbicacion(true);
            } else if (a2 == d.SOLO_CON_LA_APLICACION_VISIBLE) {
                this.viewModel.setResultadoDialogoCustomPermisoDeUbicacion(true);
            }
        } else if (i2 >= 23) {
            boolean z = false;
            if (iArr.length > 0 && iArr[0] == 0) {
                z = true;
            }
            if (z) {
                this.viewModel.setResultadoDialogoCustomPermisoDeUbicacion(true);
                return;
            }
            String str = "android.permission.ACCESS_FINE_LOCATION";
            if (b.h.f.a.a((Context) this, str) != 0) {
                requestPermissions(new String[]{str}, i);
            }
        }
    }

    private void crearDialogoParaCambiarManualmentePermisosDeUbicacion() {
        AlertDialog alertDialog = this.dialogoParaCambiarManualmentePermisosDeUbicacion;
        if (alertDialog == null || !alertDialog.isShowing()) {
            Builder builder = new Builder(this);
            builder.setView(getLayoutInflater().inflate(R.layout.dialogo_pedir_habilitar_manualmente_ubicacion, null));
            builder.setPositiveButton(getString(R.string.aceptar), new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.setNegativeButton(getString(R.string.cancelar), new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            this.dialogoParaCambiarManualmentePermisosDeUbicacion = builder.show();
        }
    }

    private void crearDialogoParaSolicitarPermisosDeUbicacion(final Integer num) {
        Builder builder = new Builder(this);
        builder.setView(getLayoutInflater().inflate(R.layout.dialogo_pedir_ubicacion, null));
        builder.setPositiveButton(getString(R.string.permitir), new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                BaseActivity.this.validarPermisoDeUbicacionActual(num);
            }
        });
        builder.setNegativeButton(getString(R.string.cancelar), new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                BaseActivity.this.viewModel.setResultadoDialogoCustomPermisoDeUbicacion(false);
                dialogInterface.dismiss();
            }
        });
        this.dialogoDePermisoDeUbicacion = builder.show();
    }

    private void iniciarRastreoDeUsuario(int i, String[] strArr, int[] iArr) {
        int i2 = VERSION.SDK_INT;
        if (i2 >= 29) {
            d a2 = a.a(strArr, iArr);
            if (a2 == d.TODO_EL_TIEMPO || a2 == d.SOLO_CON_LA_APLICACION_VISIBLE) {
                this.viewModel.lanzarServicoDeRastreo();
            }
        } else if (i2 >= 23) {
            boolean z = false;
            if (iArr.length > 0 && iArr[0] == 0) {
                z = true;
            }
            if (z) {
                this.viewModel.lanzarServicoDeRastreo();
            }
        }
    }

    private void observarPermisosDeUbicacionParaLanzarServicioDeRastreo() {
        this.viewModel.obtenerLanzarDialogoPermisosLocalizacionLiveData().a(this, new r<Integer>() {
            public void onChanged(Integer num) {
                int intValue = num.intValue();
                if (intValue == 113) {
                    boolean mostrarDialogoDeUbicacion = BaseActivity.this.mostrarDialogoDeUbicacion(num);
                    BaseActivity.this.viewModel.setResultadoDialogoCustomPermisoDeUbicacion(mostrarDialogoDeUbicacion);
                    if (mostrarDialogoDeUbicacion) {
                        BaseActivity.this.viewModel.lanzarServicoDeRastreo();
                    }
                } else if (intValue == 213) {
                    BaseActivity.this.viewModel.setResultadoDialogoCustomPermisoDeUbicacion(BaseActivity.this.mostrarDialogoDeUbicacion(num));
                }
            }
        });
        this.receiverConectividad = new m(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(this.receiverConectividad, intentFilter);
    }

    /* access modifiers changed from: private */
    public void validarPermisoDeUbicacionActual(Integer num) {
        d a2 = a.a((i) this, num.intValue());
        int i = VERSION.SDK_INT;
        String str = "android.permission.ACCESS_FINE_LOCATION";
        if (i >= 29) {
            int ordinal = a2.ordinal();
            if (ordinal == 0) {
                b.h.e.a.a(this, new String[]{str, "android.permission.ACCESS_BACKGROUND_LOCATION"}, num.intValue());
            } else if (ordinal == 1 || ordinal == 2) {
                this.viewModel.lanzarServicoDeRastreo();
            }
        } else if (i >= 23 && a2.ordinal() == 0) {
            int intValue = num.intValue();
            if (b.h.f.a.a((Context) this, str) != 0) {
                requestPermissions(new String[]{str}, intValue);
            }
        }
    }

    public void mostrarDialogoDeActualizarForzado() {
        Intent intent = new Intent(this, ActualizarForzadoActivity.class);
        intent.addFlags(268468224);
        startActivity(intent);
    }

    public boolean mostrarDialogoDeUbicacion(Integer num) {
        int ordinal = a.a((i) this, num.intValue()).ordinal();
        if (ordinal == 0) {
            AlertDialog alertDialog = this.dialogoDePermisoDeUbicacion;
            if (alertDialog == null || !alertDialog.isShowing()) {
                crearDialogoParaSolicitarPermisosDeUbicacion(num);
            }
        } else if (ordinal == 1 || ordinal == 2) {
            return true;
        } else {
            if (ordinal == 3) {
                crearDialogoParaCambiarManualmentePermisosDeUbicacion();
            }
        }
        return false;
    }

    public void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(this.receiverConectividad);
        } catch (Exception unused) {
            Log.e(getClass().getName(), "Receiver de conectividad no registrado");
        }
        AlertDialog alertDialog = this.dialogoDePermisoDeUbicacion;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.dialogoDePermisoDeUbicacion.dismiss();
        }
        AlertDialog alertDialog2 = this.dialogoParaCambiarManualmentePermisosDeUbicacion;
        if (alertDialog2 != null && alertDialog2.isShowing()) {
            this.dialogoParaCambiarManualmentePermisosDeUbicacion.dismiss();
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 113) {
            iniciarRastreoDeUsuario(i, strArr, iArr);
        } else if (i == 213) {
            capturarUbicacionDeUsuarioParaUsarUnaSolaVez(i, strArr, iArr);
        }
    }

    public void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    public void onStop() {
        super.onStop();
        ApplicationStateMonitor.getInstance().activityStopped();
    }

    public void setBaseViewModel(BaseViewModel baseViewModel) {
        this.viewModel = baseViewModel;
        observarPermisosDeUbicacionParaLanzarServicioDeRastreo();
    }
}
