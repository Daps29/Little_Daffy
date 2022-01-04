package com.example.littledaffy.model;

public class MascotaDto {

    /* COMIENZO BASE DE DATOS */

    private int edad, estado, estadoperdida, verificacion;
    private String categorias, fecha, raza, sexo;
    private String foto1, foto2, foto3;
    private String ubicacion, vacuna, user;
    private String nombre, descripcion, id_mascota;

    //CONSTRUCTOR
    public MascotaDto(String id_mascota, int edad, int estado, int estadoperdida, int verificacion, String categorias, String fecha, String raza, String sexo, String foto1, String foto2, String foto3, String ubicacion, String vacuna, String user, String nombre, String descripcion) {
        this.edad = edad;
        this.estado = estado;
        this.estadoperdida = estadoperdida;
        this.verificacion = verificacion;
        this.categorias = categorias;
        this.fecha = fecha;
        this.raza = raza;
        this.sexo = sexo;
        this.foto1 = foto1;
        this.foto2 = foto2;
        this.foto3 = foto3;
        this.ubicacion = ubicacion;
        this.vacuna = vacuna;
        this.user = user;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.id_mascota = id_mascota;
    }

    public String getId_mascota() {
        return id_mascota;
    }

    public void setId_mascota(String id_mascota) {
        this.id_mascota = id_mascota;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getEstadoperdida() {
        return estadoperdida;
    }

    public void setEstadoperdida(int estadoperdida) {
        this.estadoperdida = estadoperdida;
    }

    public int getVerificacion() {
        return verificacion;
    }

    public void setVerificacion(int verificacion) {
        this.verificacion = verificacion;
    }

    public String getCategorias() {
        return categorias;
    }

    public void setCategorias(String categorias) {
        this.categorias = categorias;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFoto1() {
        return foto1;
    }

    public void setFoto1(String foto1) {
        this.foto1 = foto1;
    }

    public String getFoto2() {
        return foto2;
    }

    public void setFoto2(String foto2) {
        this.foto2 = foto2;
    }

    public String getFoto3() {
        return foto3;
    }

    public void setFoto3(String foto3) {
        this.foto3 = foto3;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getVacuna() {
        return vacuna;
    }

    public void setVacuna(String vacuna) {
        this.vacuna = vacuna;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public MascotaDto(){}
}
