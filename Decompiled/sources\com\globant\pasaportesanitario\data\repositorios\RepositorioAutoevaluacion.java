package com.globant.pasaportesanitario.data.repositorios;

import com.globant.pasaportesanitario.data.ConvertirClasesRemotasEnLocales;
import com.globant.pasaportesanitario.data.local.BaseDeDatos;
import com.globant.pasaportesanitario.data.local.modelo.UsuarioBD;
import com.globant.pasaportesanitario.data.remoto.Api;
import com.globant.pasaportesanitario.data.remoto.modelo.UsuarioRemoto;
import com.globant.pasaportesanitario.data.remoto.modelo_autodiagnostico.AutoevaluacionRemoto;

public class RepositorioAutoevaluacion implements IRepositorioAutoevaluacion {
    public Api api;
    public BaseDeDatos db;

    public RepositorioAutoevaluacion(Api api2, BaseDeDatos baseDeDatos) {
        this.api = api2;
        this.db = baseDeDatos;
    }

    public UsuarioRemoto confirmarAutoevaluacion(AutoevaluacionRemoto autoevaluacionRemoto) {
        UsuarioBD obtenerUsuario = this.db.usuario().obtenerUsuario();
        UsuarioRemoto confirmarAutodiagnostico = this.api.confirmarAutodiagnostico(obtenerUsuario.dni.toString(), obtenerUsuario.sexo, autoevaluacionRemoto);
        UsuarioBD convertirUsuario = ConvertirClasesRemotasEnLocales.convertirUsuario(confirmarAutodiagnostico);
        convertirUsuario.geolocalizacion = obtenerUsuario.geolocalizacion;
        convertirUsuario.domicilio = obtenerUsuario.domicilio;
        convertirUsuario.apellidos = obtenerUsuario.apellidos;
        convertirUsuario.nombres = obtenerUsuario.nombres;
        convertirUsuario.fechaNacimiento = obtenerUsuario.fechaNacimiento;
        convertirUsuario.telefono = obtenerUsuario.telefono;
        this.db.usuario().deleteAll();
        this.db.usuario().guardar(convertirUsuario);
        return confirmarAutodiagnostico;
    }

    public String obtenerEstadoUsuario() {
        return this.db.usuario().obtenerUsuario().estadoActual.nombreEstado;
    }

    public String obtenerNombreUsuario() {
        return this.db.usuario().obtenerUsuario().nombres;
    }

    public UsuarioBD obtenerUsuario() {
        return this.db.usuario().obtenerUsuario();
    }
}
