package com.spharosacademy.project.SSGBack.user.repo;

import com.spharosacademy.project.SSGBack.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserId(String userId);

    Optional<User> findByUserId(String userId);

    Optional<User> findByUserEmail(String userEmail);

}
