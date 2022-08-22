package com.spharosacademy.project.SSGBack.user.repository;

import com.spharosacademy.project.SSGBack.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
