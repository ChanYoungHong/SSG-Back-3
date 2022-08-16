package com.spharosacademy.project.SSGBack.user.repo;

import com.spharosacademy.project.SSGBack.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUserId(String userId);

    @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m from User m where m.fromSocial = :social and m.userEmail =:useremail")
    Optional<User> findByUserEmail(@Param("useremail") String email, @Param("social") boolean social);
}
