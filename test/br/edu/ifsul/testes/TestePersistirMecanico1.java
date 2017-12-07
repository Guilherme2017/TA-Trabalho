package br.edu.ifsul.testes;

import br.edu.ifsul.modelo.Mecanico1;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestePersistirMecanico1 {
    
  EntityManagerFactory emf;
    EntityManager em;
    
    public TestePersistirMecanico1() {
    }

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("TA-TrabalhoPU");
        em = emf.createEntityManager();
    }
    @After
    public void tearDown() {
        em.close();
        emf.close();
    }
    @Test
    public void teste() {
        boolean exception = false;
        try {
            Mecanico1 m = new Mecanico1();
            m.setNome("Guilherme");
            m.setEndereco("Rua Dona Paula");
            m.setTelefone("(54)3314-5656");
            em.getTransaction().begin();
            em.persist(m);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            exception = true;  
        }
        Assert.assertEquals(false, exception);
    }
    
}
