package com.example.littledaffy.model;

import android.graphics.drawable.Drawable;

public class CategoriasDto {

    private String nombre_categoria;
    private Drawable foto_categoria;
    private int id_categoria;

    /* GETTERS */
    public String getNombre_categoria() {
        return nombre_categoria;
    }

    public Drawable getFoto_categoria() {
        return foto_categoria;
    }


    public int getId_categoria() {
        return id_categoria;
    }

    /* SETTERS */
    public void setNombre_categoria(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
    }


    public void setFoto_categoria(Drawable foto_categoria) {
        this.foto_categoria = foto_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public CategoriasDto(int id_categoria, String nombre_categoria, Drawable foto_categoria){
        this.id_categoria = id_categoria;
        this.nombre_categoria = nombre_categoria;
        this.foto_categoria = foto_categoria;
    }
}
