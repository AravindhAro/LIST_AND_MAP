package com.example.list.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.list.dto.ArrayListDto;
import com.example.list.dto.CommonDto;
import com.example.list.dto.LinkedListDto;
import com.example.list.dto.MapDto;
import com.example.list.service.ListService;

@RestController
public class ListController {

	@Autowired
	private ListService service;

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody ArrayListDto array) {
		List<LinkedListDto> linked = array.getRequest();
		ResponseEntity<?> response = service.saved(linked);
		return response;
	}

	@GetMapping("/fetch")
	public ResponseEntity<CommonDto<?>> fetch(@RequestParam("name") String name) {
		CommonDto<?> response = service.fetching(name);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/saving")
	public ResponseEntity<CommonDto<?>> saving(@RequestBody LinkedListDto add) {
		List<ArrayListDto> array = add.getEmployee();
		CommonDto<?> response = service.save(array);
		return new ResponseEntity<CommonDto<?>>(response, HttpStatus.OK);
	}

	@GetMapping("/get")
	public ResponseEntity<CommonDto<?>> get(@RequestParam("name") String name) {

		CommonDto<?> response = service.get(name);
		return ResponseEntity.ok(response);

	}

	@GetMapping("/gettwo")
	public ResponseEntity<CommonDto<?>> gettwo(@RequestParam("name") String name, @RequestParam("state") String state) {

		CommonDto<?> response = service.gettwo(name, state);
		return ResponseEntity.ok(response);

	}

	@PostMapping("/mapping")
	public ResponseEntity<CommonDto<?>> mapping(@RequestBody Map<String, MapDto> mapdto) {
		CommonDto<?> response = service.map(mapdto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/getmapping")
	public ResponseEntity<CommonDto<?>> getmapping() {
		CommonDto<?> response = service.getmap();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
