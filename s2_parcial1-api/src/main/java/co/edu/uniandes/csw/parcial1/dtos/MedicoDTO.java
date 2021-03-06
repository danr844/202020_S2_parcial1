/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.parcial1.dtos;

import co.edu.uniandes.csw.parcial1.entities.MedicoEntity;
import java.io.Serializable;

/**
 *
 * @author josejbocanegra
 */
public class MedicoDTO implements Serializable {

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the registroMedico
     */
    public String getRegistroMedico() {
        return registroMedico;
    }

    /**
     * @param registroMedico the registroMedico to set
     */
    public void setRegistroMedico(String registroMedico) {
        this.registroMedico = registroMedico;
    }

    /**
     * @return the especialidad
     */
    public String getEspecialidad() {
        return especialidad;
    }

    /**
     * @param especialidad the especialidad to set
     */
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * @return the medicoID
     */
    public Long getMedicoID() {
        return medicoID;
    }

    /**
     * @param medicoID the medicoID to set
     */
    public void setMedicoID(Long medicoID) {
        this.medicoID = medicoID;
    }
     private String nombre;
    private String apellido; 
    private String registroMedico;
    private String especialidad;
    private Long medicoID;
    
        public MedicoDTO(){
    
    }
    public MedicoDTO(MedicoEntity blogEntity) {
      if (blogEntity != null) {
            this.medicoID = blogEntity.getMedicoID();
            this.nombre = blogEntity.getNombre();
            this.apellido = blogEntity.getApellido();
            this.registroMedico = blogEntity.getRegistroMedico();
            this.especialidad = blogEntity.getEspecialidad();
            
      }      
        
    }
    public MedicoEntity toEntity() {
    MedicoEntity blogEntity = new MedicoEntity();
    blogEntity.setApellido(this.getApellido());
        blogEntity.setNombre(this.getNombre());
        blogEntity.setEspecialidad(this.getEspecialidad());
        blogEntity.setMedicoID(this.getMedicoID());
                blogEntity.setRegistroMedico(this.getRegistroMedico());




        return blogEntity;
    }
}
