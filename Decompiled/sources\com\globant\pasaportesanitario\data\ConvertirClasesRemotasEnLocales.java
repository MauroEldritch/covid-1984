package com.globant.pasaportesanitario.data;

import com.globant.pasaportesanitario.data.local.modelo.DatosCoepBD;
import com.globant.pasaportesanitario.data.local.modelo.DomicilioBD;
import com.globant.pasaportesanitario.data.local.modelo.EstadoActualBD;
import com.globant.pasaportesanitario.data.local.modelo.GeoBD;
import com.globant.pasaportesanitario.data.local.modelo.PermisoCirculacionBD;
import com.globant.pasaportesanitario.data.local.modelo.UsuarioBD;
import com.globant.pasaportesanitario.data.remoto.modelo.DatosCoepRemoto;
import com.globant.pasaportesanitario.data.remoto.modelo.DomicilioRemoto;
import com.globant.pasaportesanitario.data.remoto.modelo.EstadoActualRemoto;
import com.globant.pasaportesanitario.data.remoto.modelo.GeoRemoto;
import com.globant.pasaportesanitario.data.remoto.modelo.PermisoCirculacionRemoto;
import com.globant.pasaportesanitario.data.remoto.modelo.UltimoEstadoDeUsuarioRemoto;
import com.globant.pasaportesanitario.data.remoto.modelo.UsuarioRemoto;

public class ConvertirClasesRemotasEnLocales {
    public static PermisoCirculacionBD convertirUltimoEstado(UltimoEstadoDeUsuarioRemoto ultimoEstadoDeUsuarioRemoto) {
        PermisoCirculacionRemoto permisoCirculacionRemoto = ultimoEstadoDeUsuarioRemoto.estadoActual.permisoCirculacionRemoto;
        if (permisoCirculacionRemoto != null) {
            return new PermisoCirculacionBD(permisoCirculacionRemoto.permisoQr, permisoCirculacionRemoto.fechaVencimientoPermiso, permisoCirculacionRemoto.statusServicio);
        }
        return null;
    }

    public static UsuarioBD convertirUsuario(UsuarioRemoto usuarioRemoto) {
        DomicilioRemoto domicilioRemoto = usuarioRemoto.domicilio;
        String str = "";
        String str2 = domicilioRemoto != null ? domicilioRemoto.provincia : str;
        DomicilioRemoto domicilioRemoto2 = usuarioRemoto.domicilio;
        String str3 = domicilioRemoto2 != null ? domicilioRemoto2.localidad : str;
        DomicilioRemoto domicilioRemoto3 = usuarioRemoto.domicilio;
        String str4 = domicilioRemoto3 != null ? domicilioRemoto3.calle : str;
        DomicilioRemoto domicilioRemoto4 = usuarioRemoto.domicilio;
        String str5 = domicilioRemoto4 != null ? domicilioRemoto4.numero : str;
        DomicilioRemoto domicilioRemoto5 = usuarioRemoto.domicilio;
        String str6 = domicilioRemoto5 != null ? domicilioRemoto5.piso : str;
        DomicilioRemoto domicilioRemoto6 = usuarioRemoto.domicilio;
        String str7 = domicilioRemoto6 != null ? domicilioRemoto6.puerta : str;
        DomicilioRemoto domicilioRemoto7 = usuarioRemoto.domicilio;
        String str8 = domicilioRemoto7 != null ? domicilioRemoto7.codigoPostal : str;
        DomicilioRemoto domicilioRemoto8 = usuarioRemoto.domicilio;
        DomicilioBD domicilioBD = new DomicilioBD(str2, str3, str4, str5, str6, str7, str8, domicilioRemoto8 != null ? domicilioRemoto8.otros : str);
        GeoRemoto geoRemoto = usuarioRemoto.geolocalizacion;
        String str9 = geoRemoto != null ? geoRemoto.latitud : str;
        GeoRemoto geoRemoto2 = usuarioRemoto.geolocalizacion;
        String str10 = geoRemoto2 != null ? geoRemoto2.longitud : str;
        GeoRemoto geoRemoto3 = usuarioRemoto.geolocalizacion;
        GeoBD geoBD = new GeoBD(str9, str10, geoRemoto3 != null ? geoRemoto3.altura : str);
        PermisoCirculacionRemoto permisoCirculacionRemoto = usuarioRemoto.estadoActualRemoto.permisoCirculacionRemoto;
        String str11 = permisoCirculacionRemoto != null ? permisoCirculacionRemoto.permisoQr : str;
        PermisoCirculacionRemoto permisoCirculacionRemoto2 = usuarioRemoto.estadoActualRemoto.permisoCirculacionRemoto;
        String str12 = permisoCirculacionRemoto2 != null ? permisoCirculacionRemoto2.fechaVencimientoPermiso : str;
        PermisoCirculacionRemoto permisoCirculacionRemoto3 = usuarioRemoto.estadoActualRemoto.permisoCirculacionRemoto;
        PermisoCirculacionBD permisoCirculacionBD = new PermisoCirculacionBD(str11, str12, permisoCirculacionRemoto3 != null ? permisoCirculacionRemoto3.statusServicio : 0);
        DatosCoepRemoto datosCoepRemoto = usuarioRemoto.estadoActualRemoto.datosCoep;
        String str13 = datosCoepRemoto != null ? datosCoepRemoto.coep : str;
        DatosCoepRemoto datosCoepRemoto2 = usuarioRemoto.estadoActualRemoto.datosCoep;
        DatosCoepBD datosCoepBD = new DatosCoepBD(str13, datosCoepRemoto2 != null ? datosCoepRemoto2.informacionDeContacto : str);
        EstadoActualRemoto estadoActualRemoto = usuarioRemoto.estadoActualRemoto;
        String str14 = estadoActualRemoto != null ? estadoActualRemoto.nombreEstado : str;
        EstadoActualRemoto estadoActualRemoto2 = usuarioRemoto.estadoActualRemoto;
        if (estadoActualRemoto2 != null) {
            str = estadoActualRemoto2.fechaHoraVencimiento;
        }
        UsuarioBD usuarioBD = new UsuarioBD(usuarioRemoto.sexo, usuarioRemoto.dni, usuarioRemoto.fechaNacimiento, usuarioRemoto.nombres, usuarioRemoto.apellidos, usuarioRemoto.telefono, domicilioBD, geoBD, new EstadoActualBD(str14, str, datosCoepBD, permisoCirculacionBD));
        return usuarioBD;
    }
}
