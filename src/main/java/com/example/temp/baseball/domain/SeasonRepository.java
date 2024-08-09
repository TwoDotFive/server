package com.example.temp.baseball.domain;


import com.example.temp.common.exception.CustomException;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;

import java.util.Optional;

public interface SeasonRepository extends Repository<Season, Long> {
    Optional<Season> findByYear(int year);

    default Season findByYearOrElseThrow(int year) {
        return findByYear(year)
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, "Year not found"));
    }
}
