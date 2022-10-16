package com.jiguru.MyTask.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.jiguru.MyTask.entity.User_type;


public interface UserTypeRepository extends JpaRepository<User_type, Integer> {

	@Query(value = "select * from master_role where id = ?1 and is_deleted = false", nativeQuery = true)
	Optional<User_type> findById(Integer id);

	@Query(value = "select * from master_role where is_deleted = false ", nativeQuery = true)
	public List<User_type> findAll();

	@Modifying
	@Transactional
	@Query(value = "update master_role set is_deleted=true where id = ?1 ", nativeQuery = true)
	public void deleteByIdSoft(Integer id);
	
	@Query(value = "select * from master_role where role=?1 and  is_deleted = false ", nativeQuery = true)
	public Optional<User_type> findByRoleName(String role);
}