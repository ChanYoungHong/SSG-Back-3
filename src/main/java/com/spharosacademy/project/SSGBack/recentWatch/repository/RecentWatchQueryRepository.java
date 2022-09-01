package com.spharosacademy.project.SSGBack.recentWatch.repository;

import com.spharosacademy.project.SSGBack.recentWatch.dto.ResponseRecentQueryDto;
import com.spharosacademy.project.SSGBack.recentWatch.entity.RecentWatchQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecentWatchQueryRepository extends JpaRepository<RecentWatchQuery, Long> {

    @Query(value = "select r.query as query from RecentWatchQuery r where r.user.id =:userId")
    List<ResponseRecentQueryDto> getByUserId(Long userId);

    @Query(value = "select r.id from RecentWatchQuery r where r.user.id =:userId and r.query =:query")
    Long existsByUserAndQuery(Long userId, String query);
}
