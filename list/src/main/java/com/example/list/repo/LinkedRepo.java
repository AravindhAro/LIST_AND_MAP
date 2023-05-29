package com.example.list.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.list.entity.LinkedEntity;

@Repository
public interface LinkedRepo extends JpaRepository<LinkedEntity, Long> {

	List<LinkedEntity> findByName(String name);

}
