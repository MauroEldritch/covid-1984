package com.globant.pasaportesanitario.data.remoto;

import com.globant.pasaportesanitario.data.remoto.interceptores.EncabezadosInterceptor;
import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CovidRetrofit {
    public static AutoevaluacionService autoevaluacionService;
    public static OkHttpClient clienteHttp;
    public static CovidApiService covidApiService;
    public static RastreoUbicacionService rastreoUbicacionService;
    public static Retrofit retrofit;
    public EncabezadosInterceptor encabezadosInterceptor;

    public CovidRetrofit(EncabezadosInterceptor encabezadosInterceptor2) {
        this.encabezadosInterceptor = encabezadosInterceptor2;
    }

    private OkHttpClient obtenerClienteHttp() {
        if (clienteHttp == null) {
            Builder builder = new Builder();
            builder.addInterceptor(this.encabezadosInterceptor);
            String str = "**.api.app.covid.ar";
            builder.certificatePinner(new CertificatePinner.Builder().add(str, "sha256/UXt/pC5LL5LT5C2ajleIfKh8FUrseWflM+tcO+284+o=").add(str, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=").add(str, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=").build());
            clienteHttp = builder.build();
        }
        return clienteHttp;
    }

    private Retrofit obtenerInstanciaRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl("https://api.app.covid.ar/").addConverterFactory(GsonConverterFactory.create()).client(obtenerClienteHttp()).build();
        }
        return retrofit;
    }

    public AutoevaluacionService obtenerAutoEvaluacoinService() {
        if (autoevaluacionService == null) {
            autoevaluacionService = (AutoevaluacionService) obtenerInstanciaRetrofit().create(AutoevaluacionService.class);
        }
        return autoevaluacionService;
    }

    public CovidApiService obtenerCovidApiService() {
        if (covidApiService == null) {
            covidApiService = (CovidApiService) obtenerInstanciaRetrofit().create(CovidApiService.class);
        }
        return covidApiService;
    }

    public RastreoUbicacionService obtenerRastreoUbicacionService() {
        if (rastreoUbicacionService == null) {
            rastreoUbicacionService = (RastreoUbicacionService) obtenerInstanciaRetrofit().create(RastreoUbicacionService.class);
        }
        return rastreoUbicacionService;
    }
}
