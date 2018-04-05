package com.brave.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.brave.entity.Visit;

@Repository(value = "visitDao")
public interface VisitDao {
	@Select("select * from visit where username=#{username}")
	public Visit getVisit(String username);
	@Select("select u_id from visit where username=#{username}")
	public int getVisitUID(String username);
	@Update("update visit set username=#{modifiedusername},password=#{password} where u_id=#{u_id} and username=#{username}")
	public void updateUsers(@Param("u_id")int u_id,@Param("username")String username, @Param("modifiedusername")String modifiedusername,@Param("password")String password);
}
