package app.noah.repository.pouch;

import app.noah.domain.Pouch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PouchRepository_Old
{
    private final EntityManager em;

    public Pouch findOne(Long id)
    {
        return em.find(Pouch.class,id);
    }

    public List<Pouch> findAll()
    {
        return em.createQuery("select p from Pouch p",Pouch.class).getResultList();
    }


    public void save(Pouch p)
    {
        em.persist(p);
    }


    public List<Pouch> findAllUsingFetchJoin()
    {
        return em.createQuery("select p from Pouch p "
                                        + " join fetch  p.pouchCategory pc "
                                        + " join fetch p.brand b"
                                        + " join fetch p.adminAccount a"
                                      , Pouch.class
        ).getResultList();
    }

    public List<Pouch> findAllUsingFetchJoin(int offset, int limit)
    {
        return em.createQuery("select p from Pouch p "
                        + " join fetch  p.pouchCategory pc "
                        + " join fetch p.brand b"
                        + " join fetch p.adminAccount a"
                ,Pouch.class)
                .setFirstResult(offset).setMaxResults(limit).getResultList();
    }
}
