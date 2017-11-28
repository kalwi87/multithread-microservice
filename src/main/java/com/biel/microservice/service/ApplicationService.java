package com.biel.microservice.service;


import com.biel.microservice.model.AnalyzedUrl;
import com.biel.microservice.model.User;
import com.biel.microservice.repository.AnalyzedUrlRepo;
import com.biel.microservice.repository.UserRepository;
import com.biel.microservice.service.urlanalyzer.UrlExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ApplicationService {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationService.class);

    private UserRepository userRepository;

    private AnalyzedUrlRepo analyzedUrlRepo;

    private UrlExtractor urlExtractor;

    @Autowired
    public ApplicationService(UserRepository userRepository, AnalyzedUrlRepo analyzedUrlRepo, UrlExtractor urlExtractor) {
        this.userRepository = userRepository;
        this.analyzedUrlRepo = analyzedUrlRepo;
        this.urlExtractor = urlExtractor;
    }

    public User authenticationUser(String username, String password){
        User user = userRepository.findByUsername(username);
        if(password.equals(user.getPassword())){
            return user;
        }else return null;
    }

    @Async
    public void taskRunner(String uuid,String url){
        logger.info("running task for user " + uuid + " and url " + url);
        logger.info("Thread " + Thread.currentThread().getName());
        List<String> internalLinks = urlExtractor.getInternalLinks(url);
        AnalyzedUrl analyzedUrl = new AnalyzedUrl();
        analyzedUrl.setUserId(uuid);
        analyzedUrl.setUrl(url);
        analyzedUrl.setInternalLinks(internalLinks);
        logger.info("Thread " + Thread.currentThread().getName() + " saving to database");
        analyzedUrlRepo.save(analyzedUrl);
    }

    public List<AnalyzedUrl> findAll(String uuid){
        return analyzedUrlRepo.findByUuid(uuid);
    }

    public List<String> findInternalLinks(Long id) {
        return analyzedUrlRepo.findInternalLinks(id);
    }
}
