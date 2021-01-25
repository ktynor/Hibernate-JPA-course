package pl.nullpointerexception.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.nullpointerexception.hibernate.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App22FetchTypeInDirectFetchingAndQueryFetching {

    private static Logger logger = LogManager.getLogger(App22FetchTypeInDirectFetchingAndQueryFetching.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Product product = em.createQuery(// word 'fetch' eliminates additional query for category
                "select p from Product p" +
                        " left join fetch p.category c" +
                        " where p.id=:id and c.id=:catId",
                Product.class)
                .setParameter("id", 1L)
                .setParameter("catId", 1L)
                .getSingleResult();
//        Product product = em.find(Product.class, 1L);
        logger.info(product);
        logger.info(product.getCategory()); // @OneToOne(fetch = FetchType.EAGER)
        logger.info(product.getReviews()); // fetch = FetchType.EAGER
        em.getTransaction().commit();
        em.close();
    }
}
