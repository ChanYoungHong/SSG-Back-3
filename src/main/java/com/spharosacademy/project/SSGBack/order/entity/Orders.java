package com.spharosacademy.project.SSGBack.order.entity;

import com.spharosacademy.project.SSGBack.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Transactional
@Builder
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    User user;
}
