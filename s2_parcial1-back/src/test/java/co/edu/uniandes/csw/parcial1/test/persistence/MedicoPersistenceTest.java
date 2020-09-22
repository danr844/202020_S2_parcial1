/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.parcial1.test.persistence;

import co.edu.uniandes.csw.parcial1.entities.MedicoEntity;
import co.edu.uniandes.csw.parcial1.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.parcial1.persistence.MedicoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Daniel Angel
 */
@RunWith(Arquillian.class)
public class MedicoPersistenceTest {
      @Deployment
      public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
            .addPackage(MedicoEntity.class.getPackage())
            .addPackage(MedicoPersistence.class.getPackage())
            .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
            .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    @Inject 
    MedicoPersistence medicoPersistence;
      
    @Inject
    UserTransaction utx;
      
    @PersistenceContext
    private EntityManager em;
    
    private List<MedicoEntity> data = new ArrayList<>();
       
       /**
     * Configuración inicial de la prueba.
     */
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
        em.createQuery("delete from MedicoEntity").executeUpdate();


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
     * Prueba para crear un reserva.
     */
    @Test
    public  void createMedicoTest() {
        PodamFactory factory = new PodamFactoryImpl();
        MedicoEntity newEntity = factory.manufacturePojo(MedicoEntity.class);
        newEntity.setApellido("hola");
        newEntity.setNombre("hola");
        newEntity.setEspecialidad("hola");
        newEntity.setMedicoID(Long.MIN_VALUE);
        newEntity.setRegistroMedico("hola");
        MedicoEntity result = medicoPersistence.create(newEntity);

        Assert.assertNotNull(result);

        MedicoEntity entity = em.find(MedicoEntity.class, result.getMedicoID());
        entity.setApellido("hola");
        entity.setNombre("hola");
        entity.setEspecialidad("hola");
        entity.setMedicoID(Long.MIN_VALUE);
        entity.setRegistroMedico("hola");
        Assert.assertEquals(newEntity.getMedicoID(), entity.getMedicoID());
        Assert.assertEquals(newEntity.getEspecialidad(), entity.getEspecialidad());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    
    }
    
}
