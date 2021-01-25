package pl.nullpointerexception.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import pl.nullpointerexception.hibernate.entity.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class App23FetchMode {

    private static Logger logger = LogManager.getLogger(App23FetchMode.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

//        Order order = em.find(Order.class, 1L);
        List<Order> orders = em.createQuery("select o from Order o" +
                        " order by o.created desc ",
                Order.class
        )
                .setMaxResults(5)
                .getResultList();
        for (Order order : orders) {
            logger.info(order);
            logger.info(order.getOrderRows());
        }
        // @Fetch(FetchMode.SUBSELECT)
//
// 2 queries but in a second query all two tables are printed, that might create problems with large DB

        em.getTransaction().commit();
        em.close();
    }
}
