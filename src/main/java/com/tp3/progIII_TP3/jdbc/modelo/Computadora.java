
package com.tp3.progIII_TP3.jdbc.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class Computadora {
    private long id;
    private String codigo;
    private String marca;
    private String modelo;
    private List<Componente> componentes;

    public Computadora(long id, String codigo, String marca, String modelo) {
        this.id = id;
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.componentes = new ArrayList<Componente>();
    }
    
    public Computadora(String codigo, String marca, String modelo) {
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.componentes = new ArrayList<Componente>();
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public List<Componente> getComponentes() {
        return componentes;
    }

    public void setComponentes(List<Componente> componentes) {
        this.componentes = componentes;
    }
    
    public void addComponente(Componente componente){
        this.componentes.add(componente);
    }
    
    
}
