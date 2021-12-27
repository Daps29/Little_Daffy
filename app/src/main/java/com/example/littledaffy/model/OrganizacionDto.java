package com.example.littledaffy.model;

public class OrganizacionDto {


    /* COMIENZO BASE DE DATOS */

    private int estado_organizacion, contacto;
    private String id_organizacion, descripcion, direccion, direccion_literal;
    private String foto, foto_portada;
    private String horaen, horafin;
    private String nombre;

    public static final String TABLE_NAME = "organizaciones";
    public static final String COLUMN_ID_ORGANIZACION = "id_organizacion";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_HORAEN = "horaen";
    public static final String COLUMN_HORAFIN = "horafin";
    public static final String COLUMN_FOTO = "foto";
    public static final String COLUMN_FOTO_PORTADA = "foto_portada";
    public static final String COLUMN_DESCRIPCION = "descripcion";
    public static final String COLUMN_DIRECCION = "direccion";
    public static final String COLUMN_DIRECCION_LITERAL = "direccion_literal";
    public static final String COLUMN_CONTACTO = "contacto";

    /* DATABASE CALLS */

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID_ORGANIZACION + "INTEGER PRIMARY KEY,"
                    + COLUMN_NOMBRE + "TEXT,"
                    + COLUMN_CONTACTO + "INTEGER,"
                    + COLUMN_HORAEN + "TEXT,"
                    + COLUMN_HORAFIN + "TEXT,"
                    + COLUMN_FOTO + "TEXT,"
                    + COLUMN_FOTO_PORTADA + "TEXT,"
                    + COLUMN_DESCRIPCION + "TEXT,"
                    + COLUMN_DIRECCION_LITERAL + "TEXT,"
                    + ")";


    public String getId_organizacion() {
        return id_organizacion;
    }

    public void setId_organizacion(String id_organizacion) {
        this.id_organizacion = id_organizacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccion_literal() {
        return direccion_literal;
    }

    public void setDireccion_literal(String direccion_literal) {
        this.direccion_literal = direccion_literal;
    }

    public int getContacto() {
        return contacto;
    }

    public void setContacto(int contacto) {
        this.contacto = contacto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHoraen() {
        return horaen;
    }

    public void setHoraen(String horaen) {
        this.horaen = horaen;
    }

    public String getHorafin() {
        return horafin;
    }

    public void setHorafin(String horafin) {
        this.horafin = horafin;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto_lista) {
        this.foto = foto_lista;
    }

    public int getEstado_organizacion() {
        return estado_organizacion;
    }

    public void setEstado_organizacion(int estado_organizacion) {
        this.estado_organizacion = estado_organizacion;
    }


    //CONSTRUCTOR
    public OrganizacionDto(String id_organizacion,
                    String descripcion,
                    String direccion,
                    String direccion_literal,
                    int contacto,
                    String foto,
                    String foto_portada,
                    int estado_organizacion,
                    String horaen,
                    String horafin,
                    String nombre){
        this.id_organizacion = id_organizacion;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.direccion_literal = direccion_literal;
        this.contacto = contacto;
        this.foto = foto;
        this.foto_portada = foto_portada;
        this.estado_organizacion = estado_organizacion;
        this.horaen = horaen;
        this.horafin = horafin;
        this.nombre = nombre;
    }

    public OrganizacionDto(){}


}
