package com.globant.pasaportesanitario.flujos.pantallaprincipal;

import com.globant.pasaportesanitario.data.ConvertirClasesRemotasEnLocales;
import com.globant.pasaportesanitario.data.local.BaseDeDatos;
import com.globant.pasaportesanitario.data.local.modelo.UsuarioBD;
import com.globant.pasaportesanitario.data.remoto.Api;
import com.globant.pasaportesanitario.data.remoto.modelo.UsuarioRemoto;

public class PantallaPrincipalRepository {
    public Api api;
    public BaseDeDatos baseDeDatos;

    public PantallaPrincipalRepository(Api api2, BaseDeDatos baseDeDatos2) {
        this.api = api2;
        this.baseDeDatos = baseDeDatos2;
    }

    public UsuarioBD getUsuario() throws Exception {
        UsuarioBD obtenerUsuario = this.baseDeDatos.usuario().obtenerUsuario();
        UsuarioRemoto obtenerUsuario2 = this.api.obtenerUsuario(String.valueOf(obtenerUsuario.dni), obtenerUsuario.sexo);
        if (obtenerUsuario2 == null) {
            return obtenerUsuario;
        }
        UsuarioBD convertirUsuario = ConvertirClasesRemotasEnLocales.convertirUsuario(obtenerUsuario2);
        this.baseDeDatos.usuario().deleteAll();
        if (this.baseDeDatos.usuario().guardar(convertirUsuario) != null) {
            return convertirUsuario;
        }
        throw new Exception("Error guardando usuario en la Base de datos");
    }
}
