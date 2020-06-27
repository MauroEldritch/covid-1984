package com.globant.pasaportesanitario.data.local;

import a.a.a.b.a;
import android.database.Cursor;
import android.os.CancellationSignal;
import b.t.b;
import b.t.g;
import b.t.i;
import b.t.k;
import b.v.a.f;
import b.v.a.g.e;
import com.globant.pasaportesanitario.data.local.modelo.DatosCoepBD;
import com.globant.pasaportesanitario.data.local.modelo.DomicilioBD;
import com.globant.pasaportesanitario.data.local.modelo.EstadoActualBD;
import com.globant.pasaportesanitario.data.local.modelo.GeoBD;
import com.globant.pasaportesanitario.data.local.modelo.PermisoCirculacionBD;
import com.globant.pasaportesanitario.data.local.modelo.UsuarioBD;

public final class Usuario_Impl implements Usuario {
    public final g __db;
    public final b<UsuarioBD> __insertionAdapterOfUsuarioBD;
    public final k __preparedStmtOfActualizaEstado;
    public final k __preparedStmtOfDeleteAll;

    public Usuario_Impl(g gVar) {
        this.__db = gVar;
        this.__insertionAdapterOfUsuarioBD = new b<UsuarioBD>(gVar) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `tabla_usuarios` (`usuarioId`,`sexo`,`dni`,`fechaNacimiento`,`nombres`,`apellidos`,`telefono`,`provincia`,`localidad`,`calle`,`numero`,`piso`,`puerta`,`codigoPostal`,`otros`,`latitud`,`longitud`,`altura`,`nombreEstado`,`fechaHoraVencimiento`,`coep`,`informacionDeContacto`,`permisoQr`,`fechaVencimientoPermiso`,`statusServicio`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            public void bind(f fVar, UsuarioBD usuarioBD) {
                Long l = usuarioBD.usuarioId;
                if (l == null) {
                    ((e) fVar).f2134b.bindNull(1);
                } else {
                    ((e) fVar).f2134b.bindLong(1, l.longValue());
                }
                String str = usuarioBD.sexo;
                if (str == null) {
                    ((e) fVar).f2134b.bindNull(2);
                } else {
                    ((e) fVar).f2134b.bindString(2, str);
                }
                Long l2 = usuarioBD.dni;
                if (l2 == null) {
                    ((e) fVar).f2134b.bindNull(3);
                } else {
                    ((e) fVar).f2134b.bindLong(3, l2.longValue());
                }
                String str2 = usuarioBD.fechaNacimiento;
                if (str2 == null) {
                    ((e) fVar).f2134b.bindNull(4);
                } else {
                    ((e) fVar).f2134b.bindString(4, str2);
                }
                String str3 = usuarioBD.nombres;
                if (str3 == null) {
                    ((e) fVar).f2134b.bindNull(5);
                } else {
                    ((e) fVar).f2134b.bindString(5, str3);
                }
                String str4 = usuarioBD.apellidos;
                if (str4 == null) {
                    ((e) fVar).f2134b.bindNull(6);
                } else {
                    ((e) fVar).f2134b.bindString(6, str4);
                }
                String str5 = usuarioBD.telefono;
                if (str5 == null) {
                    ((e) fVar).f2134b.bindNull(7);
                } else {
                    ((e) fVar).f2134b.bindString(7, str5);
                }
                DomicilioBD domicilioBD = usuarioBD.domicilio;
                if (domicilioBD != null) {
                    String str6 = domicilioBD.provincia;
                    if (str6 == null) {
                        ((e) fVar).f2134b.bindNull(8);
                    } else {
                        ((e) fVar).f2134b.bindString(8, str6);
                    }
                    String str7 = domicilioBD.localidad;
                    if (str7 == null) {
                        ((e) fVar).f2134b.bindNull(9);
                    } else {
                        ((e) fVar).f2134b.bindString(9, str7);
                    }
                    String str8 = domicilioBD.calle;
                    if (str8 == null) {
                        ((e) fVar).f2134b.bindNull(10);
                    } else {
                        ((e) fVar).f2134b.bindString(10, str8);
                    }
                    String str9 = domicilioBD.numero;
                    if (str9 == null) {
                        ((e) fVar).f2134b.bindNull(11);
                    } else {
                        ((e) fVar).f2134b.bindString(11, str9);
                    }
                    String str10 = domicilioBD.piso;
                    if (str10 == null) {
                        ((e) fVar).f2134b.bindNull(12);
                    } else {
                        ((e) fVar).f2134b.bindString(12, str10);
                    }
                    String str11 = domicilioBD.puerta;
                    if (str11 == null) {
                        ((e) fVar).f2134b.bindNull(13);
                    } else {
                        ((e) fVar).f2134b.bindString(13, str11);
                    }
                    String str12 = domicilioBD.codigoPostal;
                    if (str12 == null) {
                        ((e) fVar).f2134b.bindNull(14);
                    } else {
                        ((e) fVar).f2134b.bindString(14, str12);
                    }
                    String str13 = domicilioBD.otros;
                    if (str13 == null) {
                        ((e) fVar).f2134b.bindNull(15);
                    } else {
                        ((e) fVar).f2134b.bindString(15, str13);
                    }
                } else {
                    ((e) fVar).f2134b.bindNull(8);
                    e eVar = (e) fVar;
                    eVar.f2134b.bindNull(9);
                    eVar.f2134b.bindNull(10);
                    eVar.f2134b.bindNull(11);
                    eVar.f2134b.bindNull(12);
                    eVar.f2134b.bindNull(13);
                    eVar.f2134b.bindNull(14);
                    eVar.f2134b.bindNull(15);
                }
                GeoBD geoBD = usuarioBD.geolocalizacion;
                if (geoBD != null) {
                    String str14 = geoBD.latitud;
                    if (str14 == null) {
                        ((e) fVar).f2134b.bindNull(16);
                    } else {
                        ((e) fVar).f2134b.bindString(16, str14);
                    }
                    String str15 = geoBD.longitud;
                    if (str15 == null) {
                        ((e) fVar).f2134b.bindNull(17);
                    } else {
                        ((e) fVar).f2134b.bindString(17, str15);
                    }
                    String str16 = geoBD.altura;
                    if (str16 == null) {
                        ((e) fVar).f2134b.bindNull(18);
                    } else {
                        ((e) fVar).f2134b.bindString(18, str16);
                    }
                } else {
                    ((e) fVar).f2134b.bindNull(16);
                    e eVar2 = (e) fVar;
                    eVar2.f2134b.bindNull(17);
                    eVar2.f2134b.bindNull(18);
                }
                EstadoActualBD estadoActualBD = usuarioBD.estadoActual;
                if (estadoActualBD != null) {
                    String str17 = estadoActualBD.nombreEstado;
                    if (str17 == null) {
                        ((e) fVar).f2134b.bindNull(19);
                    } else {
                        ((e) fVar).f2134b.bindString(19, str17);
                    }
                    String str18 = estadoActualBD.fechaHoraVencimiento;
                    if (str18 == null) {
                        ((e) fVar).f2134b.bindNull(20);
                    } else {
                        ((e) fVar).f2134b.bindString(20, str18);
                    }
                    DatosCoepBD datosCoepBD = estadoActualBD.datosCoepBD;
                    if (datosCoepBD != null) {
                        String str19 = datosCoepBD.coep;
                        if (str19 == null) {
                            ((e) fVar).f2134b.bindNull(21);
                        } else {
                            ((e) fVar).f2134b.bindString(21, str19);
                        }
                        String str20 = datosCoepBD.informacionDeContacto;
                        if (str20 == null) {
                            ((e) fVar).f2134b.bindNull(22);
                        } else {
                            ((e) fVar).f2134b.bindString(22, str20);
                        }
                    } else {
                        ((e) fVar).f2134b.bindNull(21);
                        ((e) fVar).f2134b.bindNull(22);
                    }
                    PermisoCirculacionBD permisoCirculacionBD = estadoActualBD.permisoCirculacionBD;
                    if (permisoCirculacionBD != null) {
                        String str21 = permisoCirculacionBD.permisoQr;
                        if (str21 == null) {
                            ((e) fVar).f2134b.bindNull(23);
                        } else {
                            ((e) fVar).f2134b.bindString(23, str21);
                        }
                        String str22 = permisoCirculacionBD.fechaVencimientoPermiso;
                        if (str22 == null) {
                            ((e) fVar).f2134b.bindNull(24);
                        } else {
                            ((e) fVar).f2134b.bindString(24, str22);
                        }
                        ((e) fVar).f2134b.bindLong(25, (long) permisoCirculacionBD.statusServicio);
                        return;
                    }
                    ((e) fVar).f2134b.bindNull(23);
                    e eVar3 = (e) fVar;
                    eVar3.f2134b.bindNull(24);
                    eVar3.f2134b.bindNull(25);
                    return;
                }
                ((e) fVar).f2134b.bindNull(19);
                e eVar4 = (e) fVar;
                eVar4.f2134b.bindNull(20);
                eVar4.f2134b.bindNull(21);
                eVar4.f2134b.bindNull(22);
                eVar4.f2134b.bindNull(23);
                eVar4.f2134b.bindNull(24);
                eVar4.f2134b.bindNull(25);
            }
        };
        this.__preparedStmtOfDeleteAll = new k(gVar) {
            public String createQuery() {
                return "DELETE FROM tabla_usuarios";
            }
        };
        this.__preparedStmtOfActualizaEstado = new k(gVar) {
            public String createQuery() {
                return "UPDATE tabla_usuarios SET nombreEstado = ?, fechaHoraVencimiento = ?,  permisoQr = ?, fechaVencimientoPermiso = ?, statusServicio = ?, coep = ?, informacionDeContacto = ?";
            }
        };
    }

    public int actualizaEstado(String str, String str2, String str3, String str4, int i, String str5, String str6) {
        this.__db.assertNotSuspendingTransaction();
        f acquire = this.__preparedStmtOfActualizaEstado.acquire();
        if (str == null) {
            ((e) acquire).f2134b.bindNull(1);
        } else {
            ((e) acquire).f2134b.bindString(1, str);
        }
        if (str2 == null) {
            ((e) acquire).f2134b.bindNull(2);
        } else {
            ((e) acquire).f2134b.bindString(2, str2);
        }
        if (str3 == null) {
            ((e) acquire).f2134b.bindNull(3);
        } else {
            ((e) acquire).f2134b.bindString(3, str3);
        }
        if (str4 == null) {
            ((e) acquire).f2134b.bindNull(4);
        } else {
            ((e) acquire).f2134b.bindString(4, str4);
        }
        ((e) acquire).f2134b.bindLong(5, (long) i);
        if (str5 == null) {
            ((e) acquire).f2134b.bindNull(6);
        } else {
            ((e) acquire).f2134b.bindString(6, str5);
        }
        if (str6 == null) {
            ((e) acquire).f2134b.bindNull(7);
        } else {
            ((e) acquire).f2134b.bindString(7, str6);
        }
        this.__db.beginTransaction();
        try {
            int g2 = ((b.v.a.g.f) acquire).g();
            this.__db.setTransactionSuccessful();
            return g2;
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfActualizaEstado.release(acquire);
        }
    }

    public void deleteAll() {
        this.__db.assertNotSuspendingTransaction();
        b.v.a.g.f acquire = this.__preparedStmtOfDeleteAll.acquire();
        this.__db.beginTransaction();
        acquire = (b.v.a.g.f) acquire;
        try {
            acquire.g();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteAll.release(acquire);
        }
    }

    public Long guardar(UsuarioBD usuarioBD) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            long insertAndReturnId = this.__insertionAdapterOfUsuarioBD.insertAndReturnId(usuarioBD);
            this.__db.setTransactionSuccessful();
            Long valueOf = Long.valueOf(insertAndReturnId);
            return valueOf;
        } finally {
            this.__db.endTransaction();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0155 A[Catch:{ all -> 0x023f }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x016b A[Catch:{ all -> 0x023f }] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x018a A[Catch:{ all -> 0x023f }] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x01bc A[Catch:{ all -> 0x023f }] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x01d4 A[Catch:{ all -> 0x023f }] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x01fc A[Catch:{ all -> 0x023f }] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x01fd A[Catch:{ all -> 0x023f }] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0226 A[Catch:{ all -> 0x023f }] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x022a A[Catch:{ all -> 0x023f }] */
    public UsuarioBD obtenerUsuario() {
        i iVar;
        UsuarioBD usuarioBD;
        Long l;
        DomicilioBD domicilioBD;
        int i;
        GeoBD geoBD;
        int i2;
        EstadoActualBD estadoActualBD;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        DatosCoepBD datosCoepBD;
        PermisoCirculacionBD permisoCirculacionBD;
        int i9;
        int i10;
        i a2 = i.a("SELECT * from tabla_usuarios", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = this.__db.query((b.v.a.e) a2, (CancellationSignal) null);
        try {
            int a3 = a.a(query, "usuarioId");
            int a4 = a.a(query, "sexo");
            int a5 = a.a(query, "dni");
            int a6 = a.a(query, "fechaNacimiento");
            int a7 = a.a(query, "nombres");
            int a8 = a.a(query, "apellidos");
            int a9 = a.a(query, "telefono");
            int a10 = a.a(query, "provincia");
            int a11 = a.a(query, "localidad");
            int a12 = a.a(query, "calle");
            int a13 = a.a(query, "numero");
            int a14 = a.a(query, "piso");
            int a15 = a.a(query, "puerta");
            int a16 = a.a(query, "codigoPostal");
            iVar = a2;
            try {
                int a17 = a.a(query, "otros");
                int i11 = a3;
                int a18 = a.a(query, "latitud");
                int a19 = a.a(query, "longitud");
                int a20 = a.a(query, "altura");
                int a21 = a.a(query, "nombreEstado");
                int a22 = a.a(query, "fechaHoraVencimiento");
                int a23 = a.a(query, "coep");
                int a24 = a.a(query, "informacionDeContacto");
                int a25 = a.a(query, "permisoQr");
                int a26 = a.a(query, "fechaVencimientoPermiso");
                int a27 = a.a(query, "statusServicio");
                if (query.moveToFirst()) {
                    String string = query.getString(a4);
                    if (query.isNull(a5)) {
                        l = null;
                    } else {
                        l = Long.valueOf(query.getLong(a5));
                    }
                    String string2 = query.getString(a6);
                    String string3 = query.getString(a7);
                    String string4 = query.getString(a8);
                    String string5 = query.getString(a9);
                    if (query.isNull(a10) && query.isNull(a11) && query.isNull(a12) && query.isNull(a13) && query.isNull(a14) && query.isNull(a15) && query.isNull(a16)) {
                        if (query.isNull(a17)) {
                            i = a18;
                            domicilioBD = null;
                            if (!query.isNull(i)) {
                                i10 = a19;
                                if (query.isNull(i10)) {
                                    i9 = a20;
                                    if (!query.isNull(i9)) {
                                        geoBD = new GeoBD(query.getString(i), query.getString(i10), query.getString(i9));
                                        i2 = a21;
                                        if (!query.isNull(i2)) {
                                            i8 = a22;
                                            if (query.isNull(i8)) {
                                                i7 = a23;
                                                if (query.isNull(i7)) {
                                                    i6 = a24;
                                                    if (query.isNull(i6)) {
                                                        i5 = a25;
                                                        if (query.isNull(i5)) {
                                                            i4 = a26;
                                                            if (query.isNull(i4)) {
                                                                if (query.isNull(a27)) {
                                                                    estadoActualBD = null;
                                                                    UsuarioBD usuarioBD2 = new UsuarioBD(string, l, string2, string3, string4, string5, domicilioBD, geoBD, estadoActualBD);
                                                                    i3 = i11;
                                                                    if (query.isNull(i3)) {
                                                                        usuarioBD2.usuarioId = null;
                                                                    } else {
                                                                        usuarioBD2.usuarioId = Long.valueOf(query.getLong(i3));
                                                                    }
                                                                    usuarioBD = usuarioBD2;
                                                                }
                                                            }
                                                            String string6 = query.getString(i2);
                                                            String string7 = query.getString(i8);
                                                            if (query.isNull(i7)) {
                                                                if (query.isNull(i6)) {
                                                                    datosCoepBD = null;
                                                                    if (query.isNull(i5) && query.isNull(i4)) {
                                                                        if (query.isNull(a27)) {
                                                                            permisoCirculacionBD = null;
                                                                            estadoActualBD = new EstadoActualBD(string6, string7, datosCoepBD, permisoCirculacionBD);
                                                                            UsuarioBD usuarioBD22 = new UsuarioBD(string, l, string2, string3, string4, string5, domicilioBD, geoBD, estadoActualBD);
                                                                            i3 = i11;
                                                                            if (query.isNull(i3)) {
                                                                            }
                                                                            usuarioBD = usuarioBD22;
                                                                        }
                                                                    }
                                                                    permisoCirculacionBD = new PermisoCirculacionBD(query.getString(i5), query.getString(i4), query.getInt(a27));
                                                                    estadoActualBD = new EstadoActualBD(string6, string7, datosCoepBD, permisoCirculacionBD);
                                                                    UsuarioBD usuarioBD222 = new UsuarioBD(string, l, string2, string3, string4, string5, domicilioBD, geoBD, estadoActualBD);
                                                                    i3 = i11;
                                                                    if (query.isNull(i3)) {
                                                                    }
                                                                    usuarioBD = usuarioBD222;
                                                                }
                                                            }
                                                            datosCoepBD = new DatosCoepBD(query.getString(i7), query.getString(i6));
                                                            if (query.isNull(a27)) {
                                                            }
                                                        }
                                                        i4 = a26;
                                                        String string62 = query.getString(i2);
                                                        String string72 = query.getString(i8);
                                                        if (query.isNull(i7)) {
                                                        }
                                                        datosCoepBD = new DatosCoepBD(query.getString(i7), query.getString(i6));
                                                        if (query.isNull(a27)) {
                                                        }
                                                    }
                                                    i5 = a25;
                                                    i4 = a26;
                                                    String string622 = query.getString(i2);
                                                    String string722 = query.getString(i8);
                                                    if (query.isNull(i7)) {
                                                    }
                                                    datosCoepBD = new DatosCoepBD(query.getString(i7), query.getString(i6));
                                                    if (query.isNull(a27)) {
                                                    }
                                                }
                                                i6 = a24;
                                                i5 = a25;
                                                i4 = a26;
                                                String string6222 = query.getString(i2);
                                                String string7222 = query.getString(i8);
                                                if (query.isNull(i7)) {
                                                }
                                                datosCoepBD = new DatosCoepBD(query.getString(i7), query.getString(i6));
                                                if (query.isNull(a27)) {
                                                }
                                            }
                                        } else {
                                            i8 = a22;
                                        }
                                        i7 = a23;
                                        i6 = a24;
                                        i5 = a25;
                                        i4 = a26;
                                        String string62222 = query.getString(i2);
                                        String string72222 = query.getString(i8);
                                        if (query.isNull(i7)) {
                                        }
                                        datosCoepBD = new DatosCoepBD(query.getString(i7), query.getString(i6));
                                        if (query.isNull(a27)) {
                                        }
                                    } else {
                                        i2 = a21;
                                        geoBD = null;
                                        if (!query.isNull(i2)) {
                                        }
                                        i7 = a23;
                                        i6 = a24;
                                        i5 = a25;
                                        i4 = a26;
                                        String string622222 = query.getString(i2);
                                        String string722222 = query.getString(i8);
                                        if (query.isNull(i7)) {
                                        }
                                        datosCoepBD = new DatosCoepBD(query.getString(i7), query.getString(i6));
                                        if (query.isNull(a27)) {
                                        }
                                    }
                                }
                            } else {
                                i10 = a19;
                            }
                            i9 = a20;
                            geoBD = new GeoBD(query.getString(i), query.getString(i10), query.getString(i9));
                            i2 = a21;
                            if (!query.isNull(i2)) {
                            }
                            i7 = a23;
                            i6 = a24;
                            i5 = a25;
                            i4 = a26;
                            String string6222222 = query.getString(i2);
                            String string7222222 = query.getString(i8);
                            if (query.isNull(i7)) {
                            }
                            datosCoepBD = new DatosCoepBD(query.getString(i7), query.getString(i6));
                            if (query.isNull(a27)) {
                            }
                        }
                    }
                    DomicilioBD domicilioBD2 = new DomicilioBD(query.getString(a10), query.getString(a11), query.getString(a12), query.getString(a13), query.getString(a14), query.getString(a15), query.getString(a16), query.getString(a17));
                    domicilioBD = domicilioBD2;
                    i = a18;
                    if (!query.isNull(i)) {
                    }
                    i9 = a20;
                    geoBD = new GeoBD(query.getString(i), query.getString(i10), query.getString(i9));
                    i2 = a21;
                    if (!query.isNull(i2)) {
                    }
                    i7 = a23;
                    i6 = a24;
                    i5 = a25;
                    i4 = a26;
                    String string62222222 = query.getString(i2);
                    String string72222222 = query.getString(i8);
                    if (query.isNull(i7)) {
                    }
                    datosCoepBD = new DatosCoepBD(query.getString(i7), query.getString(i6));
                    if (query.isNull(a27)) {
                    }
                } else {
                    usuarioBD = null;
                }
                query.close();
                iVar.g();
                return usuarioBD;
            } catch (Throwable th) {
                th = th;
                query.close();
                iVar.g();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            iVar = a2;
            query.close();
            iVar.g();
            throw th;
        }
    }
}
