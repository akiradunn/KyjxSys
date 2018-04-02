package com.brave.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.brave.entity.UsersKyxm;

@Repository(value = "usersKyxmDao")
public interface UsersKyxmDao {
	@Select("select * from UsersKyxm")
	public List<UsersKyxm> getAllUsersKyxm();

	@Select("select * from UsersKyxm where u_id = #{u_id}")
	public List<UsersKyxm> getUsersKyxmFromUid(int u_id);

	@Select("select * from UsersKyxm where k_id = #{k_id}")
	public List<UsersKyxm> getUsersKyxmFromKid(int k_id);
	
	@Select("select * from UsersKyxm where u_id = #{u_id} and k_id = #{k_id}")
	public UsersKyxm getUsersKyxmFromUidKid(int u_id , int k_id);
	
	@Insert("insert into UsersKyxm(u_id,k_id,k_status,k_applyTime,k_setTime,k_endTime,k_scoreApplied,k_completed) "
			+ "values(#{u_id},#{k_id},#{k_status},#{k_applyTime},#{k_setTime},#{k_endTime},#{k_scoreApplied},#{k_completed})")
	public void insertKyxm(@Param("u_id")int u_id, @Param("k_id")int k_id, @Param("k_status")String k_status, @Param("k_applyTime")String k_applyTime, @Param("k_setTime")String k_setTime, @Param("k_endTime")String k_endTime, @Param("k_scoreApplied")boolean k_scoreApplied, @Param("k_completed")boolean k_completed);
}
