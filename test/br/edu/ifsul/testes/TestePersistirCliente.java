package br.edu.ifsul.testes;

import br.edu.ifsul.modelo.Cliente;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestePersistirCliente {
    
  EntityManagerFactory emf;
    EntityManager em;
    
    public TestePersistirCliente() {
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
            Cliente c = new Cliente();
            c.setNome("Guilherme");
            c.setEndereco("Rua Dona Paula");
            c.setTelefone("(54)3314-5656");
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            exception = true;  
        }
        Assert.assertEquals(false, exception);
    }
    
}
