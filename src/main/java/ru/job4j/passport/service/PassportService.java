package ru.job4j.passport.service;

import org.springframework.stereotype.Service;
import ru.job4j.passport.model.Passport;
import ru.job4j.passport.repository.PassportRepository;

import java.util.List;

/**
 * Класс PassportService
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Service
public class PassportService {

    private final PassportRepository passportRepository;

    public PassportService(PassportRepository passportRepository) {
        this.passportRepository = passportRepository;
    }

    public Passport save(Passport passport) {
        return passportRepository.save(passport);
    }

    public List<Passport> findAll() {
        return (List<Passport>) passportRepository.findAll();
    }

    public List<Passport> findBySeries(int series) {
        return passportRepository.findBySeries(series).orElse(List.of());
    }

    public Passport findById(int id) {
        return passportRepository.findById(id).orElse(null);
    }

    public void deleteById(int id) {
        passportRepository.deleteById(id);
    }

    public List<Passport> findUnavailablePassports() {
        return passportRepository.findUnavailablePassports().orElse(List.of());
    }

    public List<Passport> findReplaceablePassports() {
        return passportRepository.findReplaceablePassports().orElse(List.of());
    }
}
