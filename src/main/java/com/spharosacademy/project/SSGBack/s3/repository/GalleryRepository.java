package com.spharosacademy.project.SSGBack.s3.repository;

import com.spharosacademy.project.SSGBack.s3.Entity.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryRepository extends JpaRepository<Gallery,Long> {
}
