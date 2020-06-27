package com.globant.pasaportesanitario.utils.dialogs;

import android.os.Bundle;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import ar.gob.coronavirus.R;
import c.c.a.e.c.f;
import com.google.android.material.textview.MaterialTextView;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.TraceMachine;

@Instrumented
public class PantallaCompletaDialog extends DialogFragment implements TraceFieldInterface {

    /* renamed from: b reason: collision with root package name */
    public a f4589b;

    /* renamed from: c reason: collision with root package name */
    public Boolean f4590c = Boolean.valueOf(true);

    public interface a extends OnClickListener {
        void onClick(View view);
    }

    public static PantallaCompletaDialog a(String str, String str2, String str3, int i) {
        Bundle bundle = new Bundle();
        bundle.putString("TITULO_LLAVE", str);
        bundle.putString("MENSAJE_LLAVE", str2);
        bundle.putString("MENSAJE_BOTON_LLAVE", str3);
        bundle.putInt("ICONO_RECURSO_LLAVE", i);
        PantallaCompletaDialog pantallaCompletaDialog = new PantallaCompletaDialog();
        pantallaCompletaDialog.setArguments(bundle);
        return pantallaCompletaDialog;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:1|2|5|3|4) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:1:0x0008 */
    /* JADX WARNING: Removed duplicated region for block: B:1:0x0008 A[LOOP:0: B:1:0x0008->B:2:?, LOOP_START, SYNTHETIC, Splitter:B:1:0x0008] */
    public void onCreate(Bundle bundle) {
        String str = "PantallaCompletaDialog#onCreate";
        TraceMachine.startTracing("PantallaCompletaDialog");
        while (true) {
            TraceMachine.enterMethod(null, str, null);
            super.onCreate(bundle);
            setStyle(0, 16973834);
            TraceMachine.exitMethod();
            return;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:1|2|5|3|4) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:1:0x0003 */
    /* JADX WARNING: Removed duplicated region for block: B:1:0x0003 A[LOOP:0: B:1:0x0003->B:2:?, LOOP_START, SYNTHETIC, Splitter:B:1:0x0003] */
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        while (true) {
            TraceMachine.enterMethod(null, "PantallaCompletaDialog#onCreateView", null);
            View inflate = layoutInflater.inflate(R.layout.dialogo_pantalla_completa, viewGroup);
            TraceMachine.exitMethod();
            return inflate;
        }
    }

    public void onStart() {
        super.onStart();
    }

    public void onStop() {
        super.onStop();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        String string = getArguments().getString("TITULO_LLAVE");
        String string2 = getArguments().getString("MENSAJE_LLAVE");
        String string3 = getArguments().getString("MENSAJE_BOTON_LLAVE");
        int i = getArguments().getInt("ICONO_RECURSO_LLAVE");
        TextView textView = (TextView) getView().findViewById(R.id.txt_titulo_pantalla_completa_dialog);
        TextView textView2 = (TextView) getView().findViewById(R.id.txt_mensaje_pantalla_completa_dialog);
        ImageView imageView = (ImageView) getView().findViewById(R.id.pantalla_completa_logo);
        MaterialTextView materialTextView = (MaterialTextView) getView().findViewById(R.id.btn_action_pantalla_completa_dialog);
        Spanned a2 = a.a.a.b.a.a(getContext(), string, (int) R.font.encode_bold);
        Spanned a3 = a.a.a.b.a.a(getContext(), string2, (int) R.font.roboto_medium);
        textView.setText(a2);
        textView2.setText(a3);
        imageView.setImageResource(i);
        materialTextView.setText(string3);
        materialTextView.setOnClickListener(new f(this));
        setCancelable(false);
        getDialog().setCanceledOnTouchOutside(false);
    }
}
