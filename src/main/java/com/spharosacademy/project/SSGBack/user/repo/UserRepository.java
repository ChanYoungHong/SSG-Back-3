package com.spharosacademy.project.SSGBack.user.repo;

import com.spharosacademy.project.SSGBack.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUserId(String userId);
    Optional<User> findByUserId(String userId);

//    @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
//    @Query("select m from User m where m.fromSocial = :social and m.userId =:userId")

//    Optional<User> findByUserId(@Param("userId") String userId, @Param("social") boolean social);
//    @Query("select m from User m where m.userEmail =:userEmail")

//    Optional<User> findByUserEmail(@Param("userEmail") String email);


    @Query("select m from User m where m.userPwd =:userPwd")
    String findByUserPwd(@Param("userPwd") String userPwd);

}
