package com.globant.pasaportesanitario.data.remoto.interceptores;

import c.c.a.e.b;
import c.c.a.e.d.a;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.instrumentation.okhttp3.OkHttp3Instrumentation;
import com.newrelic.agent.android.util.Constants.Network;
import com.newrelic.agent.android.util.Constants.Network.ContentType;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Request.Builder;
import okhttp3.Response;

@Instrumented
public class EncabezadosInterceptor implements Interceptor {
    public static final String BASIC = "Basic ";
    public static final int CODIGO_DE_ACTUALIZAR_FORZADO = 426;
    public b actualizarForzado;
    public a encabezadoSeguridadUtileria;

    public EncabezadosInterceptor(a aVar, b bVar) {
        this.encabezadoSeguridadUtileria = aVar;
        this.actualizarForzado = bVar;
    }

    private String obtenerToken() {
        StringBuilder a2 = c.a.a.a.a.a(BASIC);
        a2.append(this.encabezadoSeguridadUtileria.a());
        return a2.toString();
    }

    public Response intercept(Chain chain) throws IOException {
        Builder addHeader = chain.request().newBuilder().addHeader("X-App-Platform", "android").addHeader("X-App-Version", "115").addHeader(Network.CONTENT_TYPE_HEADER, ContentType.JSON).addHeader("Authorization", obtenerToken());
        Response proceed = chain.proceed(!(addHeader instanceof Builder) ? addHeader.build() : OkHttp3Instrumentation.build(addHeader));
        if (proceed.code() == 426) {
            this.actualizarForzado.mostrarDialogoDeActualizarForzado();
        }
        return proceed;
    }
}
