package com.globant.pasaportesanitario.data.local;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import b.t.e;
import b.t.g;
import b.t.h;
import b.t.n.c.C0048c;
import b.t.n.c.d;
import b.v.a.b;
import b.v.a.c;
import b.v.a.g.a;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.connectivity.CatPayload;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

@Instrumented
public final class BaseDeDatos_Impl extends BaseDeDatos {
    public volatile Usuario _usuario;

    public void clearAllTables() {
        boolean g2;
        String str = "VACUUM";
        String str2 = "PRAGMA wal_checkpoint(FULL)";
        super.assertNotMainThread();
        b a2 = super.getOpenHelper().a();
        try {
            super.beginTransaction();
            String str3 = "DELETE FROM `tabla_usuarios`";
            if (!(a2 instanceof SQLiteDatabase)) {
                ((a) a2).f2125b.execSQL(str3);
            } else {
                SQLiteInstrumentation.execSQL((SQLiteDatabase) a2, str3);
            }
            super.setTransactionSuccessful();
            if (g2) {
                return;
            }
        } finally {
            super.endTransaction();
            a aVar = (a) a2;
            aVar.b(str2).close();
            if (!aVar.g()) {
                if (!(aVar instanceof SQLiteDatabase)) {
                    aVar.f2125b.execSQL(str);
                } else {
                    SQLiteInstrumentation.execSQL((SQLiteDatabase) aVar, str);
                }
            }
        }
    }

    public e createInvalidationTracker() {
        return new e(this, new HashMap(0), new HashMap(0), "tabla_usuarios");
    }

    public c createOpenHelper(b.t.a aVar) {
        h hVar = new h(aVar, new h.a(2) {
            public void createAllTables(b bVar) {
                boolean z = bVar instanceof SQLiteDatabase;
                String str = "CREATE TABLE IF NOT EXISTS `tabla_usuarios` (`usuarioId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `sexo` TEXT, `dni` INTEGER, `fechaNacimiento` TEXT, `nombres` TEXT, `apellidos` TEXT, `telefono` TEXT, `provincia` TEXT, `localidad` TEXT, `calle` TEXT, `numero` TEXT, `piso` TEXT, `puerta` TEXT, `codigoPostal` TEXT, `otros` TEXT, `latitud` TEXT, `longitud` TEXT, `altura` TEXT, `nombreEstado` TEXT, `fechaHoraVencimiento` TEXT, `coep` TEXT, `informacionDeContacto` TEXT, `permisoQr` TEXT, `fechaVencimientoPermiso` TEXT, `statusServicio` INTEGER)";
                if (!z) {
                    ((a) bVar).f2125b.execSQL(str);
                } else {
                    SQLiteInstrumentation.execSQL((SQLiteDatabase) bVar, str);
                }
                String str2 = "CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)";
                if (!z) {
                    ((a) bVar).f2125b.execSQL(str2);
                } else {
                    SQLiteInstrumentation.execSQL((SQLiteDatabase) bVar, str2);
                }
                String str3 = "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '87451073e6469877f248c5d8bc004403')";
                if (!z) {
                    ((a) bVar).f2125b.execSQL(str3);
                } else {
                    SQLiteInstrumentation.execSQL((SQLiteDatabase) bVar, str3);
                }
            }

            public void dropAllTables(b bVar) {
                String str = "DROP TABLE IF EXISTS `tabla_usuarios`";
                if (!(bVar instanceof SQLiteDatabase)) {
                    ((a) bVar).f2125b.execSQL(str);
                } else {
                    SQLiteInstrumentation.execSQL((SQLiteDatabase) bVar, str);
                }
                if (BaseDeDatos_Impl.this.mCallbacks != null) {
                    int size = BaseDeDatos_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((g.b) BaseDeDatos_Impl.this.mCallbacks.get(i)).b();
                    }
                }
            }

            public void onCreate(b bVar) {
                if (BaseDeDatos_Impl.this.mCallbacks != null) {
                    int size = BaseDeDatos_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((g.b) BaseDeDatos_Impl.this.mCallbacks.get(i)).a();
                    }
                }
            }

            public void onOpen(b bVar) {
                BaseDeDatos_Impl.this.mDatabase = bVar;
                BaseDeDatos_Impl.this.internalInitInvalidationTracker(bVar);
                if (BaseDeDatos_Impl.this.mCallbacks != null) {
                    int size = BaseDeDatos_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((g.b) BaseDeDatos_Impl.this.mCallbacks.get(i)).c();
                    }
                }
            }

            public void onPostMigrate(b bVar) {
            }

            /* JADX INFO: finally extract failed */
            public void onPreMigrate(b bVar) {
                ArrayList arrayList = new ArrayList();
                a aVar = (a) bVar;
                Cursor b2 = aVar.b("SELECT name FROM sqlite_master WHERE type = 'trigger'");
                while (b2.moveToNext()) {
                    try {
                        arrayList.add(b2.getString(0));
                    } catch (Throwable th) {
                        b2.close();
                        throw th;
                    }
                }
                b2.close();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    String str = (String) it.next();
                    if (str.startsWith("room_fts_content_sync_")) {
                        aVar.f2125b.execSQL(c.a.a.a.a.b("DROP TRIGGER IF EXISTS ", str));
                    }
                }
            }

            /* JADX INFO: finally extract failed */
            /* JADX WARNING: Removed duplicated region for block: B:59:0x0312  */
            /* JADX WARNING: Removed duplicated region for block: B:61:0x0332  */
            public h.b onValidateSchema(b bVar) {
                HashSet hashSet;
                b.t.n.c cVar;
                List list;
                int i;
                int i2;
                int i3;
                HashMap hashMap = new HashMap(25);
                b.t.n.c.a aVar = new b.t.n.c.a("usuarioId", "INTEGER", true, 1, null, 1);
                hashMap.put("usuarioId", aVar);
                b.t.n.c.a aVar2 = new b.t.n.c.a("sexo", "TEXT", false, 0, null, 1);
                hashMap.put("sexo", aVar2);
                b.t.n.c.a aVar3 = new b.t.n.c.a("dni", "INTEGER", false, 0, null, 1);
                hashMap.put("dni", aVar3);
                b.t.n.c.a aVar4 = new b.t.n.c.a("fechaNacimiento", "TEXT", false, 0, null, 1);
                hashMap.put("fechaNacimiento", aVar4);
                b.t.n.c.a aVar5 = new b.t.n.c.a("nombres", "TEXT", false, 0, null, 1);
                hashMap.put("nombres", aVar5);
                b.t.n.c.a aVar6 = new b.t.n.c.a("apellidos", "TEXT", false, 0, null, 1);
                hashMap.put("apellidos", aVar6);
                b.t.n.c.a aVar7 = new b.t.n.c.a("telefono", "TEXT", false, 0, null, 1);
                hashMap.put("telefono", aVar7);
                b.t.n.c.a aVar8 = new b.t.n.c.a("provincia", "TEXT", false, 0, null, 1);
                hashMap.put("provincia", aVar8);
                b.t.n.c.a aVar9 = new b.t.n.c.a("localidad", "TEXT", false, 0, null, 1);
                hashMap.put("localidad", aVar9);
                b.t.n.c.a aVar10 = new b.t.n.c.a("calle", "TEXT", false, 0, null, 1);
                hashMap.put("calle", aVar10);
                b.t.n.c.a aVar11 = new b.t.n.c.a("numero", "TEXT", false, 0, null, 1);
                hashMap.put("numero", aVar11);
                b.t.n.c.a aVar12 = new b.t.n.c.a("piso", "TEXT", false, 0, null, 1);
                hashMap.put("piso", aVar12);
                b.t.n.c.a aVar13 = new b.t.n.c.a("puerta", "TEXT", false, 0, null, 1);
                hashMap.put("puerta", aVar13);
                b.t.n.c.a aVar14 = new b.t.n.c.a("codigoPostal", "TEXT", false, 0, null, 1);
                hashMap.put("codigoPostal", aVar14);
                b.t.n.c.a aVar15 = new b.t.n.c.a("otros", "TEXT", false, 0, null, 1);
                hashMap.put("otros", aVar15);
                b.t.n.c.a aVar16 = new b.t.n.c.a("latitud", "TEXT", false, 0, null, 1);
                hashMap.put("latitud", aVar16);
                b.t.n.c.a aVar17 = new b.t.n.c.a("longitud", "TEXT", false, 0, null, 1);
                hashMap.put("longitud", aVar17);
                b.t.n.c.a aVar18 = new b.t.n.c.a("altura", "TEXT", false, 0, null, 1);
                hashMap.put("altura", aVar18);
                b.t.n.c.a aVar19 = new b.t.n.c.a("nombreEstado", "TEXT", false, 0, null, 1);
                hashMap.put("nombreEstado", aVar19);
                b.t.n.c.a aVar20 = new b.t.n.c.a("fechaHoraVencimiento", "TEXT", false, 0, null, 1);
                hashMap.put("fechaHoraVencimiento", aVar20);
                b.t.n.c.a aVar21 = new b.t.n.c.a("coep", "TEXT", false, 0, null, 1);
                hashMap.put("coep", aVar21);
                b.t.n.c.a aVar22 = new b.t.n.c.a("informacionDeContacto", "TEXT", false, 0, null, 1);
                hashMap.put("informacionDeContacto", aVar22);
                b.t.n.c.a aVar23 = new b.t.n.c.a("permisoQr", "TEXT", false, 0, null, 1);
                hashMap.put("permisoQr", aVar23);
                b.t.n.c.a aVar24 = new b.t.n.c.a("fechaVencimientoPermiso", "TEXT", false, 0, null, 1);
                hashMap.put("fechaVencimientoPermiso", aVar24);
                b.t.n.c.a aVar25 = new b.t.n.c.a("statusServicio", "INTEGER", false, 0, null, 1);
                hashMap.put("statusServicio", aVar25);
                String str = "tabla_usuarios";
                b.t.n.c cVar2 = new b.t.n.c(str, hashMap, new HashSet(0), new HashSet(0));
                a aVar26 = (a) bVar;
                Cursor b2 = aVar26.b("PRAGMA table_info(`tabla_usuarios`)");
                HashMap hashMap2 = new HashMap();
                try {
                    String str2 = "name";
                    if (b2.getColumnCount() > 0) {
                        int columnIndex = b2.getColumnIndex(str2);
                        int columnIndex2 = b2.getColumnIndex(AnalyticAttribute.TYPE_ATTRIBUTE);
                        int columnIndex3 = b2.getColumnIndex("notnull");
                        int columnIndex4 = b2.getColumnIndex("pk");
                        int columnIndex5 = b2.getColumnIndex("dflt_value");
                        while (b2.moveToNext()) {
                            String string = b2.getString(columnIndex);
                            b.t.n.c.a aVar27 = r14;
                            b.t.n.c.a aVar28 = new b.t.n.c.a(string, b2.getString(columnIndex2), b2.getInt(columnIndex3) != 0, b2.getInt(columnIndex4), b2.getString(columnIndex5), 2);
                            hashMap2.put(string, aVar27);
                        }
                    }
                    b2.close();
                    HashSet hashSet2 = new HashSet();
                    Cursor b3 = aVar26.b("PRAGMA foreign_key_list(`tabla_usuarios`)");
                    try {
                        int columnIndex6 = b3.getColumnIndex(CatPayload.PAYLOAD_ID_KEY);
                        int columnIndex7 = b3.getColumnIndex("seq");
                        int columnIndex8 = b3.getColumnIndex("table");
                        int columnIndex9 = b3.getColumnIndex("on_delete");
                        int columnIndex10 = b3.getColumnIndex("on_update");
                        List a2 = b.t.n.c.a(b3);
                        int count = b3.getCount();
                        int i4 = 0;
                        while (i4 < count) {
                            b3.moveToPosition(i4);
                            if (b3.getInt(columnIndex7) != 0) {
                                i3 = columnIndex6;
                                i2 = columnIndex7;
                                list = a2;
                                i = count;
                            } else {
                                int i5 = b3.getInt(columnIndex6);
                                i3 = columnIndex6;
                                ArrayList arrayList = new ArrayList();
                                i2 = columnIndex7;
                                ArrayList arrayList2 = new ArrayList();
                                Iterator it = ((ArrayList) a2).iterator();
                                while (it.hasNext()) {
                                    List list2 = a2;
                                    C0048c cVar3 = (C0048c) it.next();
                                    int i6 = count;
                                    if (cVar3.f2104b == i5) {
                                        arrayList.add(cVar3.f2106d);
                                        arrayList2.add(cVar3.f2107e);
                                    }
                                    count = i6;
                                    a2 = list2;
                                }
                                list = a2;
                                i = count;
                                b.t.n.c.b bVar2 = new b.t.n.c.b(b3.getString(columnIndex8), b3.getString(columnIndex9), b3.getString(columnIndex10), arrayList, arrayList2);
                                hashSet2.add(bVar2);
                            }
                            i4++;
                            columnIndex6 = i3;
                            columnIndex7 = i2;
                            count = i;
                            a2 = list;
                        }
                        b3.close();
                        Cursor b4 = aVar26.b("PRAGMA index_list(`tabla_usuarios`)");
                        try {
                            int columnIndex11 = b4.getColumnIndex(str2);
                            int columnIndex12 = b4.getColumnIndex("origin");
                            int columnIndex13 = b4.getColumnIndex("unique");
                            if (columnIndex11 != -1 && columnIndex12 != -1) {
                                if (columnIndex13 != -1) {
                                    hashSet = new HashSet();
                                    while (b4.moveToNext()) {
                                        if ("c".equals(b4.getString(columnIndex12))) {
                                            d a3 = b.t.n.c.a(aVar26, b4.getString(columnIndex11), b4.getInt(columnIndex13) == 1);
                                            if (a3 != null) {
                                                hashSet.add(a3);
                                            }
                                        }
                                    }
                                    b4.close();
                                    cVar = new b.t.n.c(str, hashMap2, hashSet2, hashSet);
                                    if (!cVar2.equals(cVar)) {
                                        return new h.b(true, null);
                                    }
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("tabla_usuarios(com.globant.pasaportesanitario.data.local.modelo.UsuarioBD).\n Expected:\n");
                                    sb.append(cVar2);
                                    sb.append("\n Found:\n");
                                    sb.append(cVar);
                                    return new h.b(false, sb.toString());
                                }
                            }
                            b4.close();
                            hashSet = null;
                            cVar = new b.t.n.c(str, hashMap2, hashSet2, hashSet);
                            if (!cVar2.equals(cVar)) {
                            }
                        } catch (Throwable th) {
                            b4.close();
                            throw th;
                        }
                    } catch (Throwable th2) {
                        b3.close();
                        throw th2;
                    }
                } catch (Throwable th3) {
                    b2.close();
                    throw th3;
                }
            }
        }, "87451073e6469877f248c5d8bc004403", "e912f04411f2e31b2ae894d21e0dc548");
        Context context = aVar.f2001b;
        String str = aVar.f2002c;
        if (context != null) {
            return aVar.f2000a.a(new c.b(context, str, hVar));
        }
        throw new IllegalArgumentException("Must set a non-null context to create the configuration.");
    }

    public Usuario usuario() {
        Usuario usuario;
        if (this._usuario != null) {
            return this._usuario;
        }
        synchronized (this) {
            if (this._usuario == null) {
                this._usuario = new Usuario_Impl(this);
            }
            usuario = this._usuario;
        }
        return usuario;
    }
}
