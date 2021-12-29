package ru.job4j.passport.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Класс Passport
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "passports", uniqueConstraints = {@UniqueConstraint(
                columnNames = {"series", "number"})})
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int series;
    private long number;
    private String name;
    private String surName;
    private Timestamp birthDate;
    private Timestamp dateOfIssue;
    private Timestamp dateOfReplacement;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Passport passport = (Passport) o;
        return id == passport.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
