package com.spharosacademy.project.SSGBack.address.repository;

import com.spharosacademy.project.SSGBack.address.dto.AddressListDto;
import com.spharosacademy.project.SSGBack.address.entity.AddressList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressListRepo extends JpaRepository<AddressList, Integer> {

}
