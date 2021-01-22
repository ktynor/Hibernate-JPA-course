package pl.nullpointerexception.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.nullpointerexception.hibernate.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class App20CartesianProduct {

    private static Logger logger = LogManager.getLogger(App19MultiJoin.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Product> query = em.createQuery(
                "select p from Product p" +
                        " left join fetch p.attributes",
//                removed one left join and hibernate will take data LAZY way if needed
                Product.class);
        List<Product> resultList = query.getResultList();
        logger.info("Size: " + resultList.size());

        for (Product product : resultList) {
            logger.info(product);
            logger.info(product.getAttributes());
            logger.info(product.getReviews());
        }


        em.getTransaction().commit();
        em.close();
    }
}
