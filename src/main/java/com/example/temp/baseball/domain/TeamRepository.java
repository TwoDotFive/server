package com.example.temp.baseball.domain;

import com.example.temp.common.exception.CustomException;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends Repository<Team, Long> {
    Optional<Team> findById(long id);

    List<Team> findBySeason(Season season);

    default Team findByIdOrElseThrow(long id) {
        return findById(id)
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, "Team not found"));
    }

}
