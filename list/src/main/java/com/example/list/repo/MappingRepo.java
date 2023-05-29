package com.example.list.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.list.entity.MappingEntity;
@Repository
public interface MappingRepo extends JpaRepository<MappingEntity, Long>{

}
