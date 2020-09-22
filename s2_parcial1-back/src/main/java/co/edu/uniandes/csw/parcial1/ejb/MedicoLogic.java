/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.parcial1.ejb;

import co.edu.uniandes.csw.parcial1.entities.MedicoEntity;
import co.edu.uniandes.csw.parcial1.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.parcial1.persistence.MedicoPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Daniel Angel
 */
@Stateless
public class MedicoLogic {
     private static final Logger LOGGER = Logger.getLogger(MedicoLogic.class.getName());

    @Inject
    private MedicoPersistence persistence;
    
    public MedicoEntity createMedico(MedicoEntity medico) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del medico.");
        if (persistence.find(medico.getMedicoID())!=null) {
            if(persistence.find(medico.getMedicoID()).getNombre().equals(medico.getNombre()))
            throw new BusinessLogicException("Ya existe un medico con el nombre especificado.");
        }

        if (medico.getNombre().equals("")||medico.getNombre() == null) {
            throw new BusinessLogicException("Un medicoNo puede tener nombre vacio");
        }

         if (medico.getApellido().equals("")||medico.getApellido() == null) {
            throw new BusinessLogicException("Un medicoNo puede tener nombre vacio");
        }
         if (medico.getEspecialidad().length()<4) {
            throw new BusinessLogicException("Un medicoNo puede tener especialidad de menos de 4 caracteres");
        }
      

        medico = persistence.create(medico);
        LOGGER.log(Level.INFO, "Termina proceso de creación del medico.");
        return medico;
    }
    
}
