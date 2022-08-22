package com.spharosacademy.project.SSGBack.addresslist.repository;

import com.spharosacademy.project.SSGBack.addresslist.entity.AddressList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressListRepository extends JpaRepository<AddressList, Integer> {

    List<AddressList> findAllByUserId(Long userId);


}
