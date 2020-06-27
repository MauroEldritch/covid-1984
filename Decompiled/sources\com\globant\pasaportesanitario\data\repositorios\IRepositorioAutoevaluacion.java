package com.globant.pasaportesanitario.data.repositorios;

import com.globant.pasaportesanitario.data.local.modelo.UsuarioBD;

public interface IRepositorioAutoevaluacion {
    String obtenerEstadoUsuario();

    UsuarioBD obtenerUsuario();
}
