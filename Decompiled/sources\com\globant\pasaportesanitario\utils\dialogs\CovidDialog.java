package com.globant.pasaportesanitario.utils.dialogs;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import ar.gob.coronavirus.R;
import c.b.a.i;
import c.b.a.j;
import c.c.a.e.c.d;
import c.c.a.e.c.e;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.TraceMachine;

@Instrumented
public class CovidDialog extends DialogFragment implements TraceFieldInterface {

    /* renamed from: b reason: collision with root package name */
    public b f4587b;

    /* renamed from: c reason: collision with root package name */
    public a f4588c;

    public interface a extends OnClickListener {
        void onClick(View view);
    }

    public interface b extends OnClickListener {
        void onClick(View view);
    }

    public final void a(String str, Button button) {
        if (str != null && !str.isEmpty()) {
            button.setVisibility(0);
            button.setText(str);
            return;
        }
        button.setVisibility(8);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:1|2|5|3|4) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:1:0x0003 */
    /* JADX WARNING: Removed duplicated region for block: B:1:0x0003 A[LOOP:0: B:1:0x0003->B:2:?, LOOP_START, SYNTHETIC, Splitter:B:1:0x0003] */
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        while (true) {
            TraceMachine.enterMethod(null, "CovidDialog#onCreateView", null);
            View inflate = layoutInflater.inflate(R.layout.dialog_covid, viewGroup);
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
        String string = getArguments().getString("LLAVE_TITULO");
        String string2 = getArguments().getString("LLAVE_MENSAJE");
        String string3 = getArguments().getString("LLAME_MENSAJE_DEL_BOTON_POSITIVO");
        String string4 = getArguments().getString("LLAVE_MESNAJE_DEL_BOTON_NEGATIVO");
        boolean z = getArguments().getBoolean("ES_DIALOGO_DE_CARGA");
        boolean z2 = getArguments().getBoolean("ES_CANCELABLE");
        int i = getArguments().getInt("LLAVE_RECURSO_IMAGEN");
        String string5 = getArguments().getString("LLAVE_URL_IMAGEN");
        TextView textView = (TextView) view.findViewById(R.id.txtTitle);
        TextView textView2 = (TextView) view.findViewById(R.id.txtMessage);
        Button button = (Button) view.findViewById(R.id.btnPositive);
        Button button2 = (Button) view.findViewById(R.id.btnNegative);
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBarDialog);
        ImageView imageView = (ImageView) view.findViewById(R.id.imgDialog);
        a(string, textView);
        a(string2, textView2);
        a(string3, button);
        a(string4, button2);
        if (z) {
            progressBar.setVisibility(0);
        } else {
            progressBar.setVisibility(8);
        }
        setCancelable(z2);
        getDialog().setCanceledOnTouchOutside(z2);
        button.setOnClickListener(new d(this));
        button2.setOnClickListener(new e(this));
        if (i != 0) {
            imageView.setVisibility(0);
            j a2 = c.b.a.b.a((Fragment) this);
            Integer valueOf = Integer.valueOf(i);
            if (a2 != null) {
                i iVar = new i(a2.f2344b, a2, Drawable.class, a2.f2345c);
                iVar.G = valueOf;
                iVar.J = true;
                iVar.a((c.b.a.r.a<?>) (c.b.a.r.e) new c.b.a.r.e().a(c.b.a.s.a.a(iVar.B))).a(imageView);
                return;
            }
            throw null;
        } else if (!string5.isEmpty()) {
            imageView.setVisibility(0);
            j a3 = c.b.a.b.a((Fragment) this);
            if (a3 != null) {
                i iVar2 = new i(a3.f2344b, a3, Drawable.class, a3.f2345c);
                iVar2.G = string5;
                iVar2.J = true;
                iVar2.a(imageView);
                return;
            }
            throw null;
        } else {
            imageView.setVisibility(8);
        }
    }

    public final void a(String str, TextView textView) {
        if (str != null && !str.isEmpty()) {
            textView.setVisibility(0);
            textView.setText(str);
            return;
        }
        textView.setVisibility(8);
    }
}
