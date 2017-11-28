package com.biel.microservice.repository;

import com.biel.microservice.model.AnalyzedUrl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class AnalyzedUrlRepo{

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public AnalyzedUrl save(AnalyzedUrl analyzedUrl) {
        if (analyzedUrl.getId() == null) {
            em.persist(analyzedUrl);
            return analyzedUrl;
        } else {
            return em.merge(analyzedUrl);
        }
    }
    @Transactional
    public List<AnalyzedUrl> findByUuid(String uuid){
       Query query = em.createNativeQuery("SELECT * FROM ANALYZEDURL WHERE USER_ID = ?1");
       query.setParameter(1,uuid);
       return query.getResultList();
    }


    public List<String> findInternalLinks(Long id) {
        Query query = em.createNativeQuery("SELECT INTERNAL_LINKS FROM ANALYZED_URL_INTERNAL_LINKS WHERE ANALYZED_URL_ID = ?1");
        query.setParameter(1,id);
        return query.getResultList();
    }
}
