package pl.nullpointerexception.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.nullpointerexception.hibernate.entity.Category;
import pl.nullpointerexception.hibernate.entity.Customer;
import pl.nullpointerexception.hibernate.entity.Order;
import pl.nullpointerexception.hibernate.entity.Product;

import javax.persistence.*;
import java.util.List;

public class App21MultiMultiJoin {

    private static Logger logger = LogManager.getLogger(App21MultiMultiJoin.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
/*
SELECT cu.id, cu.lastname customer, ca.name category, SUM(orw.price) total FROM customer cu
inner join `order` o on cu.id = o.customer_id
inner join order_row orw on o.id = orw.order_id
inner join product p on orw.product_id = p.id
inner join category ca on p.category_id = ca.id
group by ca.id, cu.id
having total > 50
ORDER BY total DESC
;
 */
        Query query = em.createQuery(
                "select distinct c.id, c.lastname as customer, ca.name as category, SUM(orw.price) as total " +
                        "from Customer c" +
                        " inner join c.orders o" +
                        " inner join o.orderRows orw" +
                        " inner join orw.product p" +
                        " inner join p.category ca" +
                        " group by ca, c" +
                        " having SUM(orw.price) > 50" + //hibernate didn't understand alias 'total'
                        " order by total desc"
        );

        List<Object[]> resultList = query.getResultList();
        for (Object[] row : resultList) {
            logger.info(row[0] + ", \t" + row[1] + ", \t" + row[2] + ", \t" + row[3]);
        }

        em.getTransaction().commit();
        em.close();
    }
}
