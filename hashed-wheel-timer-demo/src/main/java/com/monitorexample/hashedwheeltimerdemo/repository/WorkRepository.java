package com.monitorexample.hashedwheeltimerdemo.repository;

import com.monitorexample.hashedwheeltimerdemo.entity.Work;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface WorkRepository extends ReactiveCrudRepository<Work, Long> {
    @Query("UPDATE public.work SET status=:status WHERE workid = :workId")
    Mono<Void> updateStatusByWorkId(Long workId, String status);
}
