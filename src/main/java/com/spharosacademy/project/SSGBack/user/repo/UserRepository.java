package com.spharosacademy.project.SSGBack.user.repo;

import com.spharosacademy.project.SSGBack.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
