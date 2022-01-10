package ru.job4j.passport.controller;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.job4j.passport.model.Passport;
import ru.job4j.passport.service.PassportService;

import java.util.List;

/**
 * Класс KafkaPassportController
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Component
@EnableScheduling
public class KafkaPassportController {

    private final KafkaTemplate<Integer, List<Passport>> template;
    private final PassportService passportService;

    public KafkaPassportController(KafkaTemplate<Integer, List<Passport>> template,
                                   PassportService passportService) {
        this.template = template;
        this.passportService = passportService;
    }

    /* @Scheduled(fixedRate = 5000) */
    public void sendUnavailablePassports() {
        List<Passport> unavailablePassports = passportService.findUnavailablePassports();
        if (!unavailablePassports.isEmpty()) {
            template.send("unavailable", unavailablePassports);
        }
    }
}
