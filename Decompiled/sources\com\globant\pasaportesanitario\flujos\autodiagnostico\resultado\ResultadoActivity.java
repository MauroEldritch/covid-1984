package com.globant.pasaportesanitario.flujos.autodiagnostico.resultado;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import ar.gob.coronavirus.R;
import b.m.d.a;
import b.m.d.o;
import b.o.a0;
import b.o.y;
import b.o.z.c;
import b.o.z.e;
import com.globant.pasaportesanitario.flujos.BaseActivity;
import com.globant.pasaportesanitario.flujos.autodiagnostico.AutoevaluacionViewModelFactory;

public class ResultadoActivity extends BaseActivity {
    public static final String LLAVE_OPCION_NAVEGACION = "LLAVE_OPCION_NAVEGACION";
    public TextView btnAceptarResultado;
    public ImageView imgCerrar;
    public AutodiagnosticoResultadoViewModel viewModel;

    /* renamed from: com.globant.pasaportesanitario.flujos.autodiagnostico.resultado.ResultadoActivity$3 reason: invalid class name */
    public static /* synthetic */ class AnonymousClass3 {
        public static final /* synthetic */ int[] $SwitchMap$com$globant$pasaportesanitario$flujos$autodiagnostico$resultado$ResultadoActivity$OpcionesNavegacion;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x000f */
        static {
            int[] iArr = new int[OpcionesNavegacion.values().length];
            $SwitchMap$com$globant$pasaportesanitario$flujos$autodiagnostico$resultado$ResultadoActivity$OpcionesNavegacion = iArr;
            OpcionesNavegacion opcionesNavegacion = OpcionesNavegacion.RESULTADO_VERDE;
            iArr[0] = 1;
            try {
                int[] iArr2 = $SwitchMap$com$globant$pasaportesanitario$flujos$autodiagnostico$resultado$ResultadoActivity$OpcionesNavegacion;
                OpcionesNavegacion opcionesNavegacion2 = OpcionesNavegacion.RESULTADO_ROSA;
                iArr2[1] = 2;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public enum OpcionesNavegacion {
        RESULTADO_VERDE,
        RESULTADO_ROSA
    }

    private void agregarFragment(Fragment fragment) {
        o supportFragmentManager = getSupportFragmentManager();
        if (supportFragmentManager != null) {
            a aVar = new a(supportFragmentManager);
            aVar.a(R.id.contenedor_fragment_resultado, fragment);
            aVar.a();
            return;
        }
        throw null;
    }

    public static void iniciar(Context context, OpcionesNavegacion opcionesNavegacion) {
        Intent intent = new Intent(context, ResultadoActivity.class);
        intent.putExtra(LLAVE_OPCION_NAVEGACION, opcionesNavegacion);
        context.startActivity(intent);
    }

    private Fragment obtenerFragment(OpcionesNavegacion opcionesNavegacion) {
        int ordinal = opcionesNavegacion.ordinal();
        if (ordinal == 0) {
            return PuedeCircularFragment.newInstance();
        }
        if (ordinal != 1) {
            return null;
        }
        return NoPuedeCircularFragment.newInstance();
    }

    public void onCreate(Bundle bundle) {
        y yVar;
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_autodiagnostico_resultado);
        AutoevaluacionViewModelFactory autoevaluacionViewModelFactory = new AutoevaluacionViewModelFactory(this);
        a0 viewModelStore = getViewModelStore();
        Class<AutodiagnosticoResultadoViewModel> cls = AutodiagnosticoResultadoViewModel.class;
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
            this.viewModel = (AutodiagnosticoResultadoViewModel) yVar2;
            this.btnAceptarResultado = (TextView) findViewById(R.id.btnAceptarResultado);
            ImageView imageView = (ImageView) findViewById(R.id.imgCerrar);
            this.imgCerrar = imageView;
            imageView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ResultadoActivity.this.finish();
                }
            });
            this.btnAceptarResultado.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ResultadoActivity.this.finish();
                }
            });
            return;
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    public void onStart() {
        super.onStart();
        agregarFragment(obtenerFragment((OpcionesNavegacion) getIntent().getSerializableExtra(LLAVE_OPCION_NAVEGACION)));
        this.viewModel.cargarNombreUsuario();
    }
}
