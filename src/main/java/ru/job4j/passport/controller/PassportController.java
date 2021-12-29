package ru.job4j.passport.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.passport.model.Passport;
import ru.job4j.passport.service.PassportService;

import java.util.List;

/**
 * Класс PassportController
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@RestController
@RequestMapping("api/passport")
public class PassportController {

    private final PassportService passportService;

    public PassportController(PassportService passportService) {
        this.passportService = passportService;
    }

    @GetMapping("/find")
    public ResponseEntity<List<Passport>> findPassports(
            @RequestParam(required = false) Integer seria) {
        if (seria == null) {
            return new ResponseEntity<>(
                    passportService.findAll(),
                    HttpStatus.OK
            );
        }
        List<Passport> passports = passportService.findBySeries(seria);
        if (passports.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                passports,
                HttpStatus.OK
        );
    }

    @GetMapping("/unavailable")
    public ResponseEntity<List<Passport>> findUnavailable() {
        List<Passport> passports = passportService.findUnavailablePassports();
        return getListResponseEntity(passports);
    }

    @GetMapping("/find-replaceable")
    public ResponseEntity<List<Passport>> findReplaceable() {
        List<Passport> passports = passportService.findReplaceablePassports();
        return getListResponseEntity(passports);
    }

    private ResponseEntity<List<Passport>> getListResponseEntity(List<Passport> passports) {
        return new ResponseEntity<>(
                passports,
                passports.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK
        );
    }

    @PostMapping("/save")
    public ResponseEntity<Passport> savePassport(@RequestBody Passport passport) {
        try {
            Passport savedPassport = passportService.save(passport);
            return new ResponseEntity<>(
                    savedPassport,
                    HttpStatus.CREATED
            );
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Паспорт с такой серией и номером уже существует!");
        }
    }

    @Transactional
    @PutMapping("/update")
    public ResponseEntity<Passport> updatePassport(@RequestParam Integer id,
                                   @RequestBody Passport passport) {
        Passport foundedPassport = passportService.findById(id);
        if (foundedPassport == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Паспорт с id = %d не найден!", id));
        }
        foundedPassport.setSeries(passport.getSeries());
        foundedPassport.setNumber(passport.getNumber());
        foundedPassport.setName(passport.getName());
        foundedPassport.setSurName(passport.getSurName());
        foundedPassport.setBirthDate(passport.getBirthDate());
        foundedPassport.setDateOfIssue(passport.getDateOfIssue());
        foundedPassport.setDateOfReplacement(passport.getDateOfReplacement());
        return new ResponseEntity<>(
                foundedPassport,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deletePassport(@RequestParam Integer id) {
        try {
            passportService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Паспорта с id = %d не существует!", id));
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
