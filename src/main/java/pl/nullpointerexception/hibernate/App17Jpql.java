package pl.nullpointerexception.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.nullpointerexception.hibernate.entity.Product;
import pl.nullpointerexception.hibernate.entity.Review;
import pl.nullpointerexception.hibernate.entity.ReviewDto;

import javax.persistence.*;
import java.util.List;

public class App17Jpql {

    private static Logger logger = LogManager.getLogger(App17Jpql.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery(
                "select AVG(p.price) from Product p"
        );

        Double singleResult = (Double) query.getSingleResult();
        logger.info(singleResult);

        em.getTransaction().commit();
        em.close();
    }
}
