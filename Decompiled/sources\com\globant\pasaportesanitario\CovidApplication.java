package com.globant.pasaportesanitario;

import android.app.Application;
import android.content.Intent;
import com.globant.pasaportesanitario.flujos.ErrorGenericoActivity;
import java.lang.Thread.UncaughtExceptionHandler;

public class CovidApplication extends Application {

    public class a implements UncaughtExceptionHandler {
        public a() {
        }

        public void uncaughtException(Thread thread, Throwable th) {
            CovidApplication covidApplication = CovidApplication.this;
            if (covidApplication != null) {
                Intent intent = new Intent(covidApplication.getApplicationContext(), ErrorGenericoActivity.class);
                intent.setFlags(268435456);
                covidApplication.startActivity(intent);
                System.exit(1);
                return;
            }
            throw null;
        }
    }

    public void onCreate() {
        super.onCreate();
        Thread.setDefaultUncaughtExceptionHandler(new a());
    }
}
