package pl.nullpointerexception.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.nullpointerexception.hibernate.entity.Order;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class App25EntityGraph {

    private static Logger logger = LogManager.getLogger(App25EntityGraph.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        EntityGraph<?> entityGraph = em.getEntityGraph("order-rows");

        Map<String, Object> map = new HashMap<>();
        map.put("javax.persistence.fetchgraph", entityGraph);

        Order order = em.find(Order.class, 1L/*, map*/);
        logger.info(order);
        logger.info(order.getOrderRows());

        em.getTransaction().commit();
        em.close();
    }
}
