package com.globant.pasaportesanitario.flujos;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import ar.gob.coronavirus.R;
import b.b.k.i;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;

@Instrumented
public class ErrorGenericoActivity extends i implements TraceFieldInterface {
    public Trace _nr_trace;

    public void onCreate(Bundle bundle) {
        String str = "ErrorGenericoActivity#onCreate";
        TraceMachine.startTracing("ErrorGenericoActivity");
        try {
            TraceMachine.enterMethod(this._nr_trace, str, null);
        } catch (NoSuchFieldError unused) {
            TraceMachine.enterMethod(null, str, null);
        }
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_error_generico);
        ((TextView) findViewById(R.id.btn_action_pantalla_completa_dialog)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ErrorGenericoActivity.this.finishAffinity();
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
