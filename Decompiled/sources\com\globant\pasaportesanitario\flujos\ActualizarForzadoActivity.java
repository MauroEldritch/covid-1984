package com.globant.pasaportesanitario.flujos;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import ar.gob.coronavirus.R;
import b.b.k.i;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;

@Instrumented
public class ActualizarForzadoActivity extends i implements TraceFieldInterface {
    public Trace _nr_trace;

    public void onCreate(Bundle bundle) {
        String str = "ActualizarForzadoActivity#onCreate";
        TraceMachine.startTracing("ActualizarForzadoActivity");
        try {
            TraceMachine.enterMethod(this._nr_trace, str, null);
        } catch (NoSuchFieldError unused) {
            TraceMachine.enterMethod(null, str, null);
        }
        super.onCreate(bundle);
        setContentView((int) R.layout.dialogo_pantalla_completa);
        ((TextView) findViewById(R.id.txt_titulo_pantalla_completa_dialog)).setText(R.string.dialog_version_title);
        ((TextView) findViewById(R.id.txt_mensaje_pantalla_completa_dialog)).setText(R.string.dialog_version_message);
        ((ImageView) findViewById(R.id.pantalla_completa_logo)).setImageDrawable(getResources().getDrawable(R.drawable.ic_error));
        TextView textView = (TextView) findViewById(R.id.btn_action_pantalla_completa_dialog);
        textView.setText(R.string.actualizar);
        textView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                String str = "android.intent.action.VIEW";
                String packageName = ActualizarForzadoActivity.this.getPackageName();
                try {
                    ActualizarForzadoActivity actualizarForzadoActivity = ActualizarForzadoActivity.this;
                    StringBuilder sb = new StringBuilder();
                    sb.append("market://details?id=");
                    sb.append(packageName);
                    actualizarForzadoActivity.startActivity(new Intent(str, Uri.parse(sb.toString())));
                } catch (ActivityNotFoundException unused) {
                    ActualizarForzadoActivity actualizarForzadoActivity2 = ActualizarForzadoActivity.this;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("https://play.google.com/store/apps/details?id=");
                    sb2.append(packageName);
                    actualizarForzadoActivity2.startActivity(new Intent(str, Uri.parse(sb2.toString())));
                }
                ActualizarForzadoActivity.this.finishAffinity();
            }
        });
        TraceMachine.exitMethod();
    }

    public void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    public void onStop() {
        super.onStop();
        ApplicationStateMonitor.getInstance().activityStopped();
    }
}
