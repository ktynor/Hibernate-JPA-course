package pl.nullpointerexception.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.nullpointerexception.hibernate.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class App18Join {

    private static Logger logger = LogManager.getLogger(App18Join.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Product> query = em.createQuery("select p from Product p " +
                "inner join fetch p.category c " +// "on p.category_id=c.id" <--will be added by hibernate
                "where c.id=:id", Product.class);
        //fetch in hibernate avoids additional queries like in a previous example.
        // without fetch, select filters entities, but not takes all entities from related tables.
        // There must be done another select to take related entities in a LAZY way.
        query.setParameter("id", 1L);
        List<Product> resultList = query.getResultList();
        for (Product product : resultList) {
            logger.info(product.getName());
            logger.info(product.getCategory().getName());
        }

        em.getTransaction().commit();
        em.close();
    }
}
