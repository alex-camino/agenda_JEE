package com.acamino.controller;

import com.acamino.ejb.CategoriaFacadeLocal;
import com.acamino.ejb.NotaFacadeLocal;
import com.acamino.model.Categoria;
import com.acamino.model.Nota;
import com.acamino.model.Persona;
import com.acamino.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class NotaController implements Serializable {

    @EJB
    private NotaFacadeLocal EJBnota;

    @EJB
    private CategoriaFacadeLocal EJBcategoria;

    @Inject
    private Nota nota;

    @Inject
    private Categoria categoria;

    private List<Categoria> categorias;

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @PostConstruct
    public void init() {

        categorias = EJBcategoria.findAll();
    }

    public void registrar() {

        try {
            Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            nota.setCategoria(categoria);
            nota.setPersona(us.getCodigo());
            EJBnota.create(nota);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso: ", "Se registró la nota."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso: ", "ERROR!!"));
        }

    }
}
