package com.globant.pasaportesanitario.data.local;

import com.globant.pasaportesanitario.data.local.modelo.UsuarioBD;

public interface Usuario {
    int actualizaEstado(String str, String str2, String str3, String str4, int i, String str5, String str6);

    void deleteAll();

    Long guardar(UsuarioBD usuarioBD);

    UsuarioBD obtenerUsuario();
}
