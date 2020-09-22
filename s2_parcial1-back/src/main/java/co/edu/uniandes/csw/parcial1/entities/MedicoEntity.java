/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.parcial1.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author DAniel Angel 
 */
@Entity
public class MedicoEntity extends BaseEntity  implements Serializable{

    
    private String nombre;
    private String apellido; 
    private String registroMedico;
    private String especialidad;
    private Long medicoID;
    
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
    
}
