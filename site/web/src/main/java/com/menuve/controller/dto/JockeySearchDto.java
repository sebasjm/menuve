package com.menuve.controller.dto;

/**
 *
 * @author sebasjm
 */
public class JockeySearchDto {
    
    private String nombre;
    private Integer categoria;
    private Integer pais;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public Integer getPais() {
        return pais;
    }

    public void setPais(Integer pais) {
        this.pais = pais;
    }
    
}
