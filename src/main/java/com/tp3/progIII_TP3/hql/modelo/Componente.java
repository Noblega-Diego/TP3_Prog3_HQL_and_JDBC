package com.tp3.progIII_TP3.hql.modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author diego
 */
@Entity
@Table(name = "Componentes")
public class Componente implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "nroSerie")
    private String nroSerie;
    @ManyToOne
    @JoinColumn(name="computadoraId")
    private Computadora computadora;
    
    public Componente(){}
    public Componente(long id, String nombre, String nroSerie, Computadora computadora) {
        this.id = id;
        this.nombre = nombre;
        this.nroSerie = nroSerie;
        this.computadora = computadora;
    }

    public Componente(String nombre, String nroSerie, Computadora computadora) {
        this.nombre = nombre;
        this.nroSerie = nroSerie;
        this.computadora = computadora;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNroSerie() {
        return nroSerie;
    }

    public void setNroSerie(String nroSerie) {
        this.nroSerie = nroSerie;
    }

    public Computadora getComputadora() {
        return computadora;
    }

    public void setComputadora(Computadora computadora) {
        this.computadora = computadora;
    }
    
    
}
