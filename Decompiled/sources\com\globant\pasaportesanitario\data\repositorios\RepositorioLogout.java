package com.globant.pasaportesanitario.data.repositorios;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import c.c.a.e.d.a;
import com.globant.pasaportesanitario.data.local.BaseDeDatos;

public class RepositorioLogout {
    public BaseDeDatos baseDeDatos;
    public Context context;
    public a encabezadoSeguridadUtileria;

    public RepositorioLogout(Context context2, a aVar, BaseDeDatos baseDeDatos2) {
        this.context = context2;
        this.encabezadoSeguridadUtileria = aVar;
        this.baseDeDatos = baseDeDatos2;
    }

    public void Logout() {
        Editor edit = a.a(this.context).f3119a.edit();
        edit.remove("header");
        edit.apply();
        BaseDeDatos.obtenerbaseDeDatos(this.context).usuario().deleteAll();
    }
}
