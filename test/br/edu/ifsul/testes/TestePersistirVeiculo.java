package br.edu.ifsul.testes;

import br.edu.ifsul.modelo.Cliente;
import br.edu.ifsul.modelo.Mecanico1;
import br.edu.ifsul.modelo.Ordem_Servico;
import br.edu.ifsul.modelo.Veiculo;
import java.util.Calendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestePersistirVeiculo {

    EntityManagerFactory emf;
    EntityManager em;

    public TestePersistirVeiculo() {
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
            Veiculo v = new Veiculo();
            v.setDescricao("Ford Fox 2017");
            v.setPlaca("PGB123");
            v.setCor("Prata");
            v.setCliente(em.find(Cliente.class, 1));
            v.setMecanico1(em.find(Mecanico1.class, 2));
            Ordem_Servico o = new Ordem_Servico();
            o.setInicio(Calendar.getInstance());
            o.setConclusao(Calendar.getInstance());
            o.setTipo("revisao");
            v.adicionarOrdem(o);
            em.getTransaction().begin();
            em.persist(v);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            exception = true;
        }

        Assert.assertEquals(false, exception);
    }
}
