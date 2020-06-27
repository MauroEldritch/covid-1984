package com.globant.pasaportesanitario.data.remoto;

import com.globant.pasaportesanitario.data.remoto.modelo.AutorizacionUsuario;
import com.globant.pasaportesanitario.data.remoto.modelo.DomicilioRemoto;
import com.globant.pasaportesanitario.data.remoto.modelo.GeoRemoto;
import com.globant.pasaportesanitario.data.remoto.modelo.NuevaInformacionUsuario;
import com.globant.pasaportesanitario.data.remoto.modelo.RastreoUbicacion;
import com.globant.pasaportesanitario.data.remoto.modelo.Token;
import com.globant.pasaportesanitario.data.remoto.modelo.UltimoEstadoDeUsuarioRemoto;
import com.globant.pasaportesanitario.data.remoto.modelo.UsuarioRemoto;
import com.globant.pasaportesanitario.data.remoto.modelo_autodiagnostico.AutoevaluacionRemoto;
import java.io.IOException;

public class Api {
    public CovidRetrofit covidRetrofit;

    public Api(CovidRetrofit covidRetrofit2) {
        this.covidRetrofit = covidRetrofit2;
    }

    public UsuarioRemoto actualizarUsuario(String str, String str2, String str3, DomicilioRemoto domicilioRemoto, GeoRemoto geoRemoto) {
        try {
            return (UsuarioRemoto) this.covidRetrofit.obtenerCovidApiService().actualizarUsuario(str, str2, new NuevaInformacionUsuario(str3, domicilioRemoto, geoRemoto)).execute().body();
        } catch (IOException unused) {
            return null;
        }
    }

    public Token autorizarUsuario(String str, String str2, String str3) {
        try {
            return (Token) this.covidRetrofit.obtenerCovidApiService().autorizacion(new AutorizacionUsuario(str, str2, str3)).execute().body();
        } catch (IOException unused) {
            return null;
        }
    }

    public UsuarioRemoto confirmarAutodiagnostico(String str, String str2, AutoevaluacionRemoto autoevaluacionRemoto) {
        try {
            return (UsuarioRemoto) this.covidRetrofit.obtenerAutoEvaluacoinService().confirmarAutoevaluacion(str, str2, autoevaluacionRemoto).execute().body();
        } catch (IOException unused) {
            return null;
        }
    }

    public Object enviarUbicacion(String str, String str2, String str3, String str4, String str5) throws IOException {
        return this.covidRetrofit.obtenerRastreoUbicacionService().enviarUbicacion(str, str2, new RastreoUbicacion(str3, str4, str5)).execute().body();
    }

    public Boolean esNecesarioActualizar() {
        try {
            return Boolean.valueOf(this.covidRetrofit.obtenerCovidApiService().obtenerVersion().execute().code() == 426);
        } catch (IOException unused) {
            return null;
        }
    }

    public UltimoEstadoDeUsuarioRemoto obtenerUltimoEstadoDeUsuario(String str, String str2) {
        try {
            return (UltimoEstadoDeUsuarioRemoto) this.covidRetrofit.obtenerCovidApiService().obtenerUltimoEstadoDeUsuario(str, str2).execute().body();
        } catch (IOException unused) {
            return null;
        }
    }

    public UsuarioRemoto obtenerUsuario(String str, String str2) {
        try {
            return (UsuarioRemoto) this.covidRetrofit.obtenerCovidApiService().obtenerUsuario(str, str2).execute().body();
        } catch (IOException unused) {
            return null;
        }
    }
}
