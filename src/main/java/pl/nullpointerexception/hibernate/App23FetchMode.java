package pl.nullpointerexception.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.nullpointerexception.hibernate.entity.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App23FetchMode {

    private static Logger logger = LogManager.getLogger(App23FetchMode.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Order order = em.find(Order.class, 1L);
        logger.info(order);
        logger.info(order.getOrderRows());
// one query for order and one LAZY for OrderRows

        em.getTransaction().commit();
        em.close();
    }
}
