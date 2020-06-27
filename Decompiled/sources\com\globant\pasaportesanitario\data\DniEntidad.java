package com.globant.pasaportesanitario.data;

import c.a.a.a.a;

public class DniEntidad {
    public String apellido;
    public String ejemplar;
    public String fechaDeEmision;
    public String fechaDeNacimiento;
    public String id;
    public String nombre;
    public String sexo;
    public String tramite;

    public DniEntidad(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        this.id = str;
        this.nombre = str2;
        this.apellido = str3;
        this.tramite = str4;
        this.sexo = str5;
        this.ejemplar = str6;
        this.fechaDeNacimiento = str7;
        this.fechaDeEmision = str8;
    }

    public DniEntidad construirDni(String str) {
        if (str != null) {
            String str2 = "@";
            String[] split = str.split(str2);
            if (!str.startsWith(str2) && split.length >= 7) {
                String str3 = "";
                String str4 = split[0] != null ? split[0] : str3;
                String str5 = split[1] != null ? split[1] : str3;
                String str6 = split[2] != null ? split[2] : str3;
                String str7 = split[3] != null ? split[3] : str3;
                String replaceAll = (split[4] != null ? split[4] : str3).replaceAll("[a-zA-Z]", str3);
                String str8 = split[5] != null ? split[5] : str3;
                String str9 = split[6] != null ? split[6] : str3;
                if (split[7] != null) {
                    str3 = split[7];
                }
                DniEntidad dniEntidad = new DniEntidad(replaceAll, str6, str5, str4, str7, str8, str9, str3);
                return dniEntidad;
            }
        }
        return new DniEntidad();
    }

    public String getApellido() {
        return this.apellido;
    }

    public String getEjemplar() {
        return this.ejemplar;
    }

    public String getFechaDeEmision() {
        return this.fechaDeEmision;
    }

    public String getFechaDeNacimiento() {
        return this.fechaDeNacimiento;
    }

    public String getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getSexo() {
        return this.sexo;
    }

    public String getTramite() {
        return this.tramite;
    }

    public void setApellido(String str) {
        this.apellido = str;
    }

    public void setEjemplar(String str) {
        this.ejemplar = str;
    }

    public void setFechaDeEmision(String str) {
        this.fechaDeEmision = str;
    }

    public void setFechaDeNacimiento(String str) {
        this.fechaDeNacimiento = str;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setNombre(String str) {
        this.nombre = str;
    }

    public void setSexo(String str) {
        this.sexo = str;
    }

    public void setTramite(String str) {
        this.tramite = str;
    }

    public boolean tieneDatosBasicosCompletos() {
        String str = this.id;
        if (str != null && !str.isEmpty()) {
            String str2 = this.tramite;
            if (str2 != null && !str2.isEmpty()) {
                String str3 = this.sexo;
                if (str3 != null && !str3.isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder a2 = a.a("DniEntidad{id='");
        a.a(a2, this.id, '\'', ", nombre='");
        a.a(a2, this.nombre, '\'', ", apellido='");
        a.a(a2, this.apellido, '\'', ", tramite='");
        a.a(a2, this.tramite, '\'', ", sexo='");
        a.a(a2, this.sexo, '\'', ", ejemplar='");
        a.a(a2, this.ejemplar, '\'', ", fechaDeNacimiento='");
        a.a(a2, this.fechaDeNacimiento, '\'', ", fechaDeEmision='");
        a2.append(this.fechaDeEmision);
        a2.append('\'');
        a2.append('}');
        return a2.toString();
    }

    public DniEntidad() {
        String str = "";
        this.id = str;
        this.nombre = str;
        this.apellido = str;
        this.tramite = str;
        this.sexo = str;
        this.ejemplar = str;
        this.fechaDeNacimiento = str;
        this.fechaDeEmision = str;
    }
}
