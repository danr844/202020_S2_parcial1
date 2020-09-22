/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.parcial1.persistence;

import co.edu.uniandes.csw.parcial1.entities.MedicoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Daniel Angel
 */
@Stateless
public class MedicoPersistence {
    private static final Logger LOGGER = Logger.getLogger(MedicoPersistence.class.getName());
    @PersistenceContext(unitName = "parcial1PU")
    protected EntityManager em;
    
    public MedicoEntity create(MedicoEntity medicoEntity){
        em.persist(medicoEntity);
        return medicoEntity;
    }
     public List<MedicoEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los Medicos");
       
        TypedQuery query = em.createQuery("select u from MedicoEntity u", MedicoEntity.class);
        
        return query.getResultList();
    }
     /**
     * Busca si hay alguna reserva con el id que se envía de argumento
     *
     * @param medicoID: id correspondiente a la reserva buscada.
     * @return una reserva.
     */
    public MedicoEntity find(Long medicoID) {
        LOGGER.log(Level.INFO, "Consultando el Medico con id={0}", medicoID);
        
        return em.find(MedicoEntity.class, medicoID);
    }
     /**
     * Actualiza un Pokemon.
     *
     * @param pokemonEntity: la reserva que viene con los nuevos cambios.
     * @return una reserva con los cambios aplicados.
     */
    public MedicoEntity update(MedicoEntity pokemonEntity) {
        LOGGER.log(Level.INFO, "Actualizando el author con id={0}", pokemonEntity.getMedicoID());
        return em.merge(pokemonEntity);
    }
     /**
     * Borra una reserva de la base de datos recibiendo como argumento el id de
     * la reserva
     *
     * @param pokemonId: id correspondiente a la reserva a borrar.
     */
    public void delete(Long pokemonId) {

        LOGGER.log(Level.INFO, "Borrando el Pokemon con id={0}", pokemonId);
      
        MedicoEntity pokemonEntity = em.find(MedicoEntity.class, pokemonId);
       
        em.remove(pokemonEntity);
    }
    
}
