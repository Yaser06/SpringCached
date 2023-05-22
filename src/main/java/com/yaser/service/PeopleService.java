package com.yaser.service;

import com.yaser.entities.People;
import com.yaser.repository.PeopleRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;


@AllArgsConstructor
@Service
public class PeopleService {

    private final Logger logger = LoggerFactory.getLogger(PeopleService.class);
    private final PeopleRepository peopleRepository;
    final CacheManager cacheManager;

    @Cacheable("people")
    public List<People> getList() {
        logger.info("All List ..");
        return peopleRepository.findAll();
    }

    @Cacheable("specific-age")
    public List<People> getListSpecificAge(int number) {
        logger.info("Specific List ... ");
        return peopleRepository.findAllByAge(number);
    }

    @CacheEvict({"specific-age", "people"})
    public People save(People people) {
        logger.info("Save to object..", people);
        logger.info("Cache clear..");
        return peopleRepository.save(people);
    }

    @CacheEvict(cacheNames = {"specific-age", "people"}, allEntries = true)
    @PostConstruct
    @Scheduled(fixedRateString = "100000")
    public void clearCache() {
        logger.info("Caches are cleared");
    }
}
