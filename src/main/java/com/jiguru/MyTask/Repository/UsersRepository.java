package com.jiguru.MyTask.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jiguru.MyTask.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

	@Query(value = "select * from users where id = ?1 and is_deleted = false and is_enabled = true", nativeQuery = true)
	public Users getById(Long id);

	@Query(value = "select * from users where is_deleted = false and is_enabled = true", nativeQuery = true)
	public List<Users> findAll();

	@Modifying
	@Transactional
	@Query(value = "delete from users where id = ?1", nativeQuery = true)
	public void deleteById(Long id);

	@Modifying
	@Transactional
	@Query(value = "update users set is_deleted = true where id = ?1", nativeQuery = true)
	public void deleteByIdSoft(Long id);

	@Query(value = "select * from users where user_name = ?1 and is_deleted = false and is_enabled = true", nativeQuery = true)
	public Users findByUserName(String userId);

	@Query(value = "select * from users where user_name = ?1 and is_deleted = false and is_enabled = true", nativeQuery = true)
	public Users findByUser(String userId);
	
	@Query(value = "select * from users where user_name = ?1 and is_deleted = false  and is_enabled = true", nativeQuery = true)
	public Users findByLoginName(String user_name);

	@Query(value = "select * from users where user_name like ?1 and is_deleted = false and is_enabled = true", nativeQuery = true)
	public Users findUserDetailsByName(String userName);

	@Query(value = "select id from users where pranth_id in(?1) and is_deleted = false and is_enabled = true", nativeQuery = true)
	public List<Long> getByPranthId(List<Long> pranthIds);

	
}