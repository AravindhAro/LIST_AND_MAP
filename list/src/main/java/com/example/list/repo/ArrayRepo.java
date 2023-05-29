package com.example.list.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.list.entity.ArrayListEntity;

@Repository
public interface ArrayRepo extends JpaRepository<ArrayListEntity, Long>{

	ArrayListEntity findByName(String name);

	List<ArrayListEntity> findByNameAndAddressesState(String name, String state);


	





}
