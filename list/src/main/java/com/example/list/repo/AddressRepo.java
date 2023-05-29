package com.example.list.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.list.entity.AddressEntity;

@Repository
public interface AddressRepo extends JpaRepository<AddressEntity, Long>{

	List<AddressEntity> findByStateAndArrayName(String state, String name);


}
