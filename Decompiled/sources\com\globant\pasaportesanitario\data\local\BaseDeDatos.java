package com.globant.pasaportesanitario.data.local;

import android.content.Context;
import b.t.g;
import b.t.g.a;

public abstract class BaseDeDatos extends g {
    public static volatile BaseDeDatos INSTANCIA;

    public static BaseDeDatos obtenerbaseDeDatos(Context context) {
        Class<BaseDeDatos> cls = BaseDeDatos.class;
        if (INSTANCIA == null) {
            synchronized (cls) {
                if (INSTANCIA == null) {
                    Context applicationContext = context.getApplicationContext();
                    String str = "pasaporte_sanitario";
                    if (str.trim().length() != 0) {
                        a aVar = new a(applicationContext, cls, str);
                        aVar.f2052h = false;
                        aVar.i = true;
                        INSTANCIA = (BaseDeDatos) aVar.a();
                    } else {
                        throw new IllegalArgumentException("Cannot build a database with null or empty name. If you are trying to create an in memory database, use Room.inMemoryDatabaseBuilder");
                    }
                }
            }
        }
        return INSTANCIA;
    }

    public abstract Usuario usuario();
}
