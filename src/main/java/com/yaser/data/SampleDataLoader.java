package com.yaser.data;

import com.github.javafaker.Faker;
import com.yaser.entities.People;
import com.yaser.repository.PeopleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class SampleDataLoader implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(SampleDataLoader.class);
    private final PeopleRepository peopleRepository;
    private final Faker faker;

    public SampleDataLoader(PeopleRepository peopleRepository, Faker faker) {
        this.peopleRepository = peopleRepository;
        this.faker = faker;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Loading Sample Data");
        List<People> collect = IntStream.rangeClosed(1, 1000)
                .mapToObj(i ->
                        new People(faker.name().firstName(),
                                faker.name().lastName(),
                                faker.address().fullAddress(),
                                faker.number().numberBetween(15, 150))
                ).collect(Collectors.toList());
        peopleRepository.saveAll(collect);
    }
}
