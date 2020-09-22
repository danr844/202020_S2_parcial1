/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.parcial1.test.logic;

import co.edu.uniandes.csw.parcial1.ejb.MedicoLogic;
import co.edu.uniandes.csw.parcial1.entities.MedicoEntity;
import co.edu.uniandes.csw.parcial1.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.parcial1.persistence.MedicoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author DAniel Angel 
 */
@RunWith(Arquillian.class)

public class MedicoLogicTest {
     
    @PersistenceContext
    private EntityManager em;

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private MedicoLogic medicoLogic;
    

    @Inject
    private UserTransaction utx;

    private List<MedicoEntity> data = new ArrayList<>();
    @Deployment
    public static JavaArchive CreateDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MedicoEntity.class.getPackage())
                .addPackage(MedicoPersistence.class.getPackage())
                .addPackage(MedicoLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from ataqueEntity").executeUpdate();
        em.createQuery("delete from PokemonEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            MedicoEntity entity = factory.manufacturePojo(MedicoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
       
       
    }

    /**
     *
     * @throws BusinessLogicException
     */
    @Test
    public void createPokemon() throws BusinessLogicException {
        MedicoEntity newEntity = factory.manufacturePojo(MedicoEntity.class);
        newEntity.setMedicoID(Long.MIN_VALUE);
        newEntity.setNombre("hola");
        newEntity.setApellido("hola2");
        newEntity.setEspecialidad("holasda");


        MedicoEntity result = medicoLogic.createMedico(newEntity);
        Assert.assertNotNull(result);
        
        MedicoEntity entity = em.find(MedicoEntity.class, result.getMedicoID());
        Assert.assertEquals(entity.getMedicoID(), result.getMedicoID());
        Assert.assertEquals(entity.getNombre(), result.getNombre());
    }
    
        @Test(expected = BusinessLogicException.class)
     public void createMedicoFake() throws BusinessLogicException {
        MedicoEntity newEntity = factory.manufacturePojo(MedicoEntity.class);
        newEntity.setMedicoID(Long.MAX_VALUE);
        newEntity.setNombre("hola");
        newEntity.setApellido("hola2");
        newEntity.setEspecialidad("holasda");
        MedicoEntity newEntity2 = factory.manufacturePojo(MedicoEntity.class);
        newEntity2.setMedicoID(Long.MAX_VALUE);
        newEntity2.setNombre("hola");
        newEntity2.setApellido("hola2");
        newEntity2.setEspecialidad("holasda");

        MedicoEntity result = medicoLogic.createMedico(newEntity);
        MedicoEntity result2 = medicoLogic.createMedico(newEntity2);

        Assert.assertNotNull(result);
        
        MedicoEntity entity = em.find(MedicoEntity.class, result.getMedicoID());
        Assert.assertEquals(entity.getMedicoID(), result.getMedicoID());
        Assert.assertEquals(entity.getNombre(), result.getNombre());
    }
    
}
