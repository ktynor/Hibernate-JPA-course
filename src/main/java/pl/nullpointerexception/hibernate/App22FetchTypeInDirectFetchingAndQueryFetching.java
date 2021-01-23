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

        Product product = em.createQuery(
                "select p from Product p where p.id=:id",
                Product.class)
                .setParameter("id", 1L)
                .getSingleResult();
        logger.info(product);
        logger.info(product.getCategory()); //@OneToOne(fetch = FetchType.EAGER)
        //second query to get category, but done earlier, before printing product

        em.getTransaction().commit();
        em.close();
    }
}
