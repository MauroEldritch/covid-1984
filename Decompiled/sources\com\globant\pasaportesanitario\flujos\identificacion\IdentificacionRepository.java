package com.globant.pasaportesanitario.flujos.identificacion;

import android.content.SharedPreferences.Editor;
import android.util.Log;
import c.c.a.e.d.a;
import c.c.a.e.d.b;
import c.c.a.e.d.b.C0083b;
import com.globant.pasaportesanitario.data.ConvertirClasesRemotasEnLocales;
import com.globant.pasaportesanitario.data.local.BaseDeDatos;
import com.globant.pasaportesanitario.data.local.modelo.UsuarioBD;
import com.globant.pasaportesanitario.data.remoto.Api;
import com.globant.pasaportesanitario.data.remoto.modelo.DomicilioRemoto;
import com.globant.pasaportesanitario.data.remoto.modelo.GeoRemoto;
import com.globant.pasaportesanitario.data.remoto.modelo.Token;
import com.globant.pasaportesanitario.data.remoto.modelo.UsuarioRemoto;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class IdentificacionRepository {
    public static IdentificacionRepository identificacionRepository;
    public Api api;
    public BaseDeDatos baseDeDatos;
    public a encabezadoSeguridadUtileria;

    public IdentificacionRepository(Api api2, BaseDeDatos baseDeDatos2, a aVar) {
        this.api = api2;
        this.baseDeDatos = baseDeDatos2;
        this.encabezadoSeguridadUtileria = aVar;
    }

    public Boolean actualizarUsuario(String str, String str2, String str3, DomicilioRemoto domicilioRemoto, GeoRemoto geoRemoto) {
        UsuarioRemoto actualizarUsuario = this.api.actualizarUsuario(str, str2, str3, domicilioRemoto, geoRemoto);
        this.baseDeDatos.usuario().deleteAll();
        if (this.baseDeDatos.usuario().guardar(ConvertirClasesRemotasEnLocales.convertirUsuario(actualizarUsuario)) != null) {
            return Boolean.valueOf(true);
        }
        return Boolean.valueOf(false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x005d A[SYNTHETIC, Splitter:B:23:0x005d] */
    public Boolean autorizarUsuario(String str, String str2, String str3) {
        KeyStore keyStore;
        Token autorizarUsuario = this.api.autorizarUsuario(str, str2, str3);
        if (autorizarUsuario == null || autorizarUsuario.getToken().isEmpty()) {
            return Boolean.valueOf(false);
        }
        a aVar = this.encabezadoSeguridadUtileria;
        String token = autorizarUsuario.getToken();
        Editor edit = aVar.f3119a.edit();
        b bVar = aVar.f3120b;
        byte[] bytes = token.getBytes();
        C0083b bVar2 = (C0083b) bVar.f3121a;
        String str4 = null;
        if (bVar2 != null) {
            try {
                keyStore = KeyStore.getInstance("AndroidKeyStore");
                try {
                    keyStore.load(null);
                    if (keyStore.getCertificate(bVar2.f3122a) != null) {
                        PublicKey publicKey = keyStore.getCertificate(bVar2.f3122a).getPublicKey();
                        if (publicKey == null) {
                            Log.d("b", "Error: Public key was not found in Keystore");
                        } else {
                            str4 = C0083b.a(publicKey, bytes);
                            edit.putString("header", str4);
                            edit.apply();
                            return Boolean.valueOf(true);
                        }
                    }
                    str4 = "";
                } catch (IOException | InvalidKeyException | KeyStoreException | NoSuchAlgorithmException | NoSuchProviderException | CertificateException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException unused) {
                    if (keyStore != null) {
                        try {
                            keyStore.deleteEntry(bVar2.f3122a);
                        } catch (Exception unused2) {
                        }
                    }
                    edit.putString("header", str4);
                    edit.apply();
                    return Boolean.valueOf(true);
                }
            } catch (IOException | InvalidKeyException | KeyStoreException | NoSuchAlgorithmException | NoSuchProviderException | CertificateException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException unused3) {
                keyStore = null;
                if (keyStore != null) {
                }
                edit.putString("header", str4);
                edit.apply();
                return Boolean.valueOf(true);
            }
            edit.putString("header", str4);
            edit.apply();
            return Boolean.valueOf(true);
        }
        throw null;
    }

    public boolean esNecesarioActualizar() {
        Boolean esNecesarioActualizar = this.api.esNecesarioActualizar();
        if (esNecesarioActualizar != null) {
            return esNecesarioActualizar.booleanValue();
        }
        return false;
    }

    public UsuarioBD obtenerUsuario() {
        return this.baseDeDatos.usuario().obtenerUsuario();
    }

    public UsuarioBD registrarUsuario(String str, String str2) {
        UsuarioRemoto obtenerUsuario = this.api.obtenerUsuario(str, str2);
        this.baseDeDatos.usuario().deleteAll();
        UsuarioBD convertirUsuario = ConvertirClasesRemotasEnLocales.convertirUsuario(obtenerUsuario);
        if (this.baseDeDatos.usuario().guardar(convertirUsuario) != null) {
            return convertirUsuario;
        }
        return null;
    }
}
