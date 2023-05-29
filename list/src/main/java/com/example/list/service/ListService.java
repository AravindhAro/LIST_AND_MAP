package com.example.list.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.example.list.dto.ArrayListDto;
import com.example.list.dto.CommonDto;
import com.example.list.dto.LinkedListDto;
import com.example.list.dto.MapDto;

public interface ListService {

	ResponseEntity<?> saved(List<LinkedListDto> linked);

	CommonDto<?> fetching(String name);

	CommonDto<?> save(List<ArrayListDto> array);

	CommonDto<?> get(String name);

	CommonDto<?> gettwo(String name, String state);

	CommonDto<?> map(Map<String, MapDto> mapdto);

	CommonDto<?> getmap();

}
