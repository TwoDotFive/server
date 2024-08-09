package com.example.temp.fanpool.domain;

import com.example.temp.common.exception.CustomException;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;

import java.util.Optional;

public interface FanpoolRepository extends Repository<Fanpool, Long> {
    Fanpool save(Fanpool fanpool);

    Optional<Fanpool> findById(Long id);

    default Fanpool findByIdOrElseThrow(long id) {
        return findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "Fanpool not found")
        );
    }
}
