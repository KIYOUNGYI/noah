package app.noah.repository;

import app.noah.domain.Pouch;
import app.noah.domain.PouchCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PouchRepository
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

    public List<PouchCategory> findAll2()
    {
        return em.createQuery("select p from PouchCategory p", PouchCategory.class).getResultList();
    }

    public void save(Pouch p)
    {
        em.persist(p);
    }
}
