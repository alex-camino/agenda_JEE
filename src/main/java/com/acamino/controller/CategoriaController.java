/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acamino.controller;

import com.acamino.ejb.CategoriaFacadeLocal;
import com.acamino.model.Categoria;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

//Inyecciones de dependencias.
@Named
@ViewScoped
public class CategoriaController implements Serializable{
    
    //Al añadir esta anotación evitamos instanciar el objeto.
    @EJB
    private CategoriaFacadeLocal categoriaEJB;
    @Inject
    private Categoria categoria;

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    
    @PostConstruct
    public void init(){
        
        //this.categoria = new Categoria();        
    }
    
    public void registrar(){
        
        try{
            this.categoriaEJB.create(this.categoria);
        }catch(Exception e){
            //mensaje prime grwol
        }
    }
}
