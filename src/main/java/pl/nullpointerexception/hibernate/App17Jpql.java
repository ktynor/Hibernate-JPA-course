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

        TypedQuery<Product> query = em.createQuery(
                "select p from Product p " +
                        "where p.id=:id",//id value flexible to define in query.setParameter("id", value);
                Product.class//return
        );
        query.setParameter("id", 300L);

        try {
            Product product = query.getSingleResult();//single result instead the list
            logger.info(product);
        } catch (NoResultException e) {
            logger.error("Brak wynikow", e);
        }

//        List<Product> resultList = query.getResultList();
//        for (Product product : resultList) {
//            logger.info(product);//print output
//        }

        em.getTransaction().commit();
        em.close();
    }
}
