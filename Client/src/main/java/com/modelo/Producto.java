package com.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "producto")
public class Producto implements Serializable {


    @Id
    private int idproducto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idproveedor")
    @JsonIgnore
    private Proveedor idproveedor;

    private String nombre;

    private int valor;

    private String serial;



    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public Proveedor getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(Proveedor idproveedor) {
        this.idproveedor = idproveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }
}
