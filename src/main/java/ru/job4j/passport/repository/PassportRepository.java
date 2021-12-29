package ru.job4j.passport.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.passport.model.Passport;

import java.util.List;
import java.util.Optional;

/**
 * Класс PassportRepository
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public interface PassportRepository extends CrudRepository<Passport, Integer> {

    Optional<List<Passport>> findBySeries(int series);

    @Query(value = "from Passport where dateOfReplacement < current_timestamp")
    Optional<List<Passport>> findUnavailablePassports();

    @Query(nativeQuery = true, value =
            "SELECT * FROM passports WHERE date_of_replacement BETWEEN "
                    + "current_timestamp AND current_timestamp + INTERVAL '3 month'")
    Optional<List<Passport>> findReplaceablePassports();
}
