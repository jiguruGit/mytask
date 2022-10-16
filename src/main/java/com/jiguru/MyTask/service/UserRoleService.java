package com.jiguru.MyTask.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dipl.udsp.masters.entities.MasterRole;
import com.jiguru.MyTask.Repository.UserTypeRepository;
import com.jiguru.MyTask.entity.User_type;
import com.jiguru.MyTask.util.ResponseBean;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserRoleService {

	@Autowired
	private UserTypeRepository userTypeRepository;
	
	public ResponseEntity<ResponseBean> getById(Integer id){
		ResponseBean responseBean = new ResponseBean();
		try {
			Optional<User_type> userTypeRecords = userTypeRepository.findById(id);
			responseBean.setStatus(HttpStatus.OK);
			if (userTypeRecords.isPresent()) {
				responseBean.setMessage("Records Fetched Successfully");
				responseBean.setData(userTypeRecords.get());
			} else {
				responseBean.setMessage("No record found");
				responseBean.setData(null);
			}
		} catch (Exception e) {
			log.error("Exception occured : {}", e);
			responseBean.setStatus(HttpStatus.BAD_REQUEST);
			responseBean.setData(null);
			responseBean.setMessage("Execption Occured ");
		}
		return new ResponseEntity<>(responseBean, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseBean> save(User_type userTypePayload) {
		ResponseBean responseBean = new ResponseBean();
		try {
			if (userTypePayload.getId() != null && userTypePayload.getId() > 0) {
				Optional<User_type> existingmasterRoleRecord = userTypeRepository.getById(userTypePayload.getId());
				if (existingmasterRoleRecord.isPresent()) {
					userTypePayload.setCreatedOn(existingmasterRoleRecord.get().getCreatedOn());
					userTypePayload.setCreatedBy(existingmasterRoleRecord.get().getCreatedBy());
					userTypePayload = userTypeRepository.save(userTypePayload);
					responseBean.setStatus(HttpStatus.OK);
					responseBean.setMessage("Successfully updated the record");
					responseBean.setData(userTypePayload);
				} else {
					responseBean.setMessage("Record does not exist");
				}
			} else {
				userTypePayload = userTypeRepository.save(userTypePayload);
				responseBean.setStatus(HttpStatus.OK);
				responseBean.setMessage("Successfully added record");
				responseBean.setData(userTypePayload);
			}
		} catch (Exception e) {
			log.error("Exception occured : {}", e);
			responseBean.setStatus(HttpStatus.EXPECTATION_FAILED);
			responseBean.setMessage("Failed to add record");
		}
		return new ResponseEntity<>(responseBean, responseBean.getStatus());
	}
}
