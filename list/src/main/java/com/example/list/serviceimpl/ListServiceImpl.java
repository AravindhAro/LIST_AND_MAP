package com.example.list.serviceimpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.list.dto.AddressDto;
import com.example.list.dto.ArrayListDto;
import com.example.list.dto.CommonDto;
import com.example.list.dto.LinkedListDto;
import com.example.list.dto.MapDto;
import com.example.list.dto.MappingDto;
import com.example.list.entity.AddressEntity;
import com.example.list.entity.ArrayListEntity;
import com.example.list.entity.LinkedEntity;
import com.example.list.entity.MappingEntity;
import com.example.list.repo.AddressRepo;
import com.example.list.repo.ArrayRepo;
import com.example.list.repo.LinkedRepo;
import com.example.list.repo.MappingRepo;
import com.example.list.service.ListService;

@Service
public class ListServiceImpl implements ListService {

	@Autowired
	private LinkedRepo linkedrepo;
	@Autowired
	private ArrayRepo arrayrepo;

	@Autowired
	private MappingRepo mappingrepo;
	
	@Autowired
	private AddressRepo addressrepo;

	private static final Logger logger = LoggerFactory.getLogger(ListServiceImpl.class);

	@Override
	public ResponseEntity<?> saved(List<LinkedListDto> linked) {
		try {
			logger.info("saved method called with linked: " + linked);
			List<LinkedEntity> link = new LinkedList<>();
			for (LinkedListDto dto : linked) {

				LinkedEntity entity = new LinkedEntity();

				entity.setId(dto.getId());

				if (dto.getName() != null && !dto.getName().isEmpty()) {
					entity.setName(dto.getName());
				} else {
					return ResponseEntity.ok("please enter the name ");
				}
				if (dto.getValue() != null && !dto.getValue().isEmpty()) {
					entity.setValue(dto.getValue());
				} else {
					return ResponseEntity.ok("please enter the value ");
				}
				link.add(entity);
			}

			List<LinkedEntity> savedEntities = linkedrepo.saveAll(link);

			if (!savedEntities.isEmpty()) {
				return ResponseEntity.ok("Entities saved successfully");
			} else {
				return ResponseEntity.badRequest().body("Failed to save entities");
			}
		} catch (Exception e) {
			logger.error("An error occurred: " + e.getMessage(), e);

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
		}
	}

	@Override
	public CommonDto<?> fetching(String name) {

		CommonDto<?> response = new CommonDto<>();
		try {
			List<LinkedEntity> entities = linkedrepo.findByName(name);

			if (!entities.isEmpty()) {

				response.setResponsedto(entities);
				response.setMessage(name + " details successfully fetched");

				return response;
			} else {
				response.setMessage("name was not found");

				return response;
			}
		} catch (Exception e) {
			logger.error("An error occurred: " + e.getMessage(), e);
			response.setMessage(e.getMessage());
			return response;
		}
	}

	@Override
	public CommonDto<?> save(List<ArrayListDto> array) {
		CommonDto<?> response = new CommonDto<>();
		List<ArrayListDto> arrayddto = new ArrayList<>();
		arrayddto.addAll(array);

		try {
			for (ArrayListDto arraydto : array) {
				ArrayListEntity employee = new ArrayListEntity();
				employee.setId(arraydto.getId());
				employee.setName(arraydto.getName());

				List<AddressEntity> entity = new ArrayList<>();
				List<AddressDto> ddto = arraydto.getAddressdto();

				for (AddressDto addressDTO : ddto) {
					AddressEntity address = new AddressEntity();
					address.setId(addressDTO.getId());
					address.setAddress(addressDTO.getAddress());
					address.setState(addressDTO.getState());
					address.setCity(addressDTO.getCity());
					address.setPincode(addressDTO.getPincode());
					address.setDistrict(addressDTO.getDistrict());
					address.setArray(employee);
					entity.add(address);
				}

				employee.setAddresses(entity);
				arrayrepo.save(employee);
			}

			response.setMessage("saved successfully");
			response.setResponsedto(arrayddto);
			return response;
		} catch (Exception e) {
			logger.error("An error occurred: " + e.getMessage(), e);
			response.setMessage("An error occurred: " + e.getMessage());
			response.setResponsedto(null);
			return response;
		}
	}

	@Override
	public CommonDto<?> get(String name) {

		CommonDto<?> response = new CommonDto<>();

		try {
			ArrayListEntity entity = arrayrepo.findByName(name);
			if (entity != null) {
				List<ArrayListDto> arraydto = new ArrayList<>();

				ArrayListDto dto = new ArrayListDto();
				dto.setId(entity.getId());
				dto.setName(entity.getName());

				List<AddressDto> adto = new ArrayList<>();
				List<AddressEntity> aentity = entity.getAddresses();
				for (AddressEntity en : aentity) {
					AddressDto ddto = new AddressDto();
					ddto.setId(en.getId());
					ddto.setAddress(en.getAddress());
					ddto.setCity(en.getCity());
					ddto.setDistrict(en.getDistrict());
					ddto.setState(en.getState());
					ddto.setPincode(en.getPincode());
					adto.add(ddto);
				}
				dto.setAddressdto(adto);
				arraydto.add(dto);
				response.setMessage(name + " details get successfully");
				response.setResponsedto(arraydto);

			} else {
				response.setMessage("No object found with the given name: " + name);
				response.setResponsedto(null);
			}
		} catch (Exception e) {
			logger.error("An error occurred: " + e.getMessage(), e);
			response.setMessage("An error occurred: " + e.getMessage());
			response.setResponsedto(null);
		}

		return response;
	}
	@Override
	public CommonDto<?> gettwo(String name, String state) {
	    CommonDto<?> response = new CommonDto<>();
	    try {
	        if (state == null || state.isEmpty()) {
	            throw new IllegalArgumentException("State parameter is required");
	        } else if(name ==null || name.isEmpty()) {
	            throw new IllegalArgumentException("name parameter is required");

	        }
	        

	        List<AddressEntity> aentity = addressrepo.findByStateAndArrayName(state, name);
	        logger.info(state);
	        List<ArrayListDto> arrayDtoList = new ArrayList<>();

	        if (!aentity.isEmpty() && !name.isEmpty()) {
	            for (AddressEntity en : aentity) {
	                AddressDto ddto = new AddressDto();
	                ddto.setId(en.getId());
	                ddto.setAddress(en.getAddress());
	                ddto.setCity(en.getCity());
	                ddto.setDistrict(en.getDistrict());
	                ddto.setState(en.getState());
	                ddto.setPincode(en.getPincode());

	                ArrayListDto arrayDto = new ArrayListDto();
	                arrayDto.setId(en.getArray().getId());
	                arrayDto.setName(en.getArray().getName());
	                arrayDto.setAddressdto(Collections.singletonList(ddto));

	                arrayDtoList.add(arrayDto);
	            }

	            response.setMessage(name + " details get successfully");
	            response.setResponsedto(arrayDtoList);
	        } else {
//	            response.setMessage("No records found for the state: " + state);
	            response.setMessage("No records found for the name: " + name + " and state: " + state);
	            response.setResponsedto(null);
	        } 
	    } catch (IllegalArgumentException e) {
	        response.setMessage("Invalid : " + e.getMessage());
	        response.setResponsedto(null);
	        logger.error("An error occurred: " + e.getMessage(), e);
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.setMessage("An error occurred: " + e);
	        response.setResponsedto(null);
	        logger.error("An error occurred: " + e.getMessage(), e);
	    }

	    return response;
	}
//	@Override
//	public CommonDto<?> gettwo(String name, String state) {
//	    CommonDto<?> response = new CommonDto<>();
//	    try {
//	        if (name == null || name.isEmpty()) {
//	            throw new IllegalArgumentException("Name parameter is required");
//	        }
//	        
//	        if (state == null || state.isEmpty()) {
//	            throw new IllegalArgumentException("State parameter is required");
//	        }
//
//	        List<ArrayListEntity> entityList = arrayrepo.findByNameAndAddressesState(name, state);
//	        List<ArrayListDto> arrayDtoList = new ArrayList<>();
//
//	        if (!entityList.isEmpty()) {
//	            for (ArrayListEntity entity : entityList) {
//	                ArrayListDto arrayDto = new ArrayListDto();
//	                arrayDto.setId(entity.getId());
//	                arrayDto.setName(entity.getName());
//
//	                List<AddressDto> adtoList = new ArrayList<>();
//	                List<AddressEntity> addressEntityList = entity.getAddresses();
//	                for (AddressEntity addressEntity : addressEntityList) {
//	                    AddressDto ddto = new AddressDto();
//	                    ddto.setId(addressEntity.getId());
//	                    ddto.setAddress(addressEntity.getAddress());
//	                    ddto.setCity(addressEntity.getCity());
//	                    ddto.setDistrict(addressEntity.getDistrict());
//	                    ddto.setState(addressEntity.getState());
//	                    ddto.setPincode(addressEntity.getPincode());
//	                    adtoList.add(ddto);
//	                }
//
//	                arrayDto.setAddressdto(adtoList);
//	                arrayDtoList.add(arrayDto);
//	            }
//
//	            response.setMessage(name + " details get successfully");
//	            response.setResponsedto(arrayDtoList);
//	        } else {
//	            response.setMessage("No records found for the name: " + name + " and state: " + state);
//	            response.setResponsedto(null);
//	        }
//	    } catch (IllegalArgumentException e) {
//	        response.setMessage("Invalid input parameters: " + e.getMessage());
//	        response.setResponsedto(null);
//	        logger.error("An error occurred: " + e.getMessage(), e);
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	        response.setMessage("An error occurred: " + e);
//	        response.setResponsedto(null);
//	        logger.error("An error occurred: " + e.getMessage(), e);
//	    }
//
//	    return response;
//	}



	@Override
	public CommonDto<?> map(Map<String, MapDto> mapdto) {
		CommonDto<?> response = new CommonDto<>();
		logger.info("saved method called with mapdto: " + mapdto);

		try {
			String state = "KErLA";
			List<Map<String, MapDto>> list = new ArrayList<>();
			for (Map.Entry<String, MapDto> entry : mapdto.entrySet()) {

				Map<String, MapDto> mm = new HashMap<String, MapDto>();
				String name = entry.getKey();
				MapDto dto = entry.getValue();
				if (dto.getState().equalsIgnoreCase(state)) {

					logger.info("User Name : " + name + "   : name : '" + dto.getName() + "'  : Pin Code : '"
							+ dto.getPincode() + "'   : State : '" + dto.getState() + "'");

					mm.put(name, dto);
					list.add(mm);
				}

			}
			response.setMessage("success");
			response.setResponsedto(list);

		} catch (Exception e) {
			response.setMessage("error");
			response.getResponsedto();
			logger.error("An error occurred: " + e.getMessage(), e);
		}

		return response;
	}

	@Override
	public CommonDto<?> getmap() {
		CommonDto<?> response = new CommonDto<>();

		try {
			List<MappingEntity> entity = mappingrepo.findAll();
			logger.info("saved method called with mapping entity: " + entity);

			List<Map<Long, MappingDto>> responseData = new ArrayList<>();
			for (MappingEntity en : entity) {
				MappingDto mdto = new MappingDto();
				mdto.setName(en.getName());
				mdto.setCode(en.getCode());
				Map<Long, MappingDto> mapDto = new HashMap<>();
				mapDto.put(en.getId(), mdto);
				responseData.add(mapDto);
			}
			response.setMessage("success");
			response.setResponsedto(responseData);
		} catch (Exception e) {
			logger.error("An error occurred while retrieving the map.", e);
		}

		return response;
	}

}
