package com.brave.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
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
	//管理员获取visit列表
	@Select("select * from visit")
	public List<Visit> getVisitList();
	
	//管理员获取指定visit
	@Select("select * from visit where u_id=#{u_id} and username=#{username}")
	public Visit getSpecifiedVisit(@Param("u_id")int u_id,@Param("username")String username);
	
	//管理员删除指定visit
	@Delete("delete from visit where u_id=#{u_id} and username=#{username}")
	public void deleteSpecifiedVisit(@Param("u_id")int u_id,@Param("username")String username);
	
	//管理员插入指定visit
	@Insert("insert into Visit values(#{u_id},2,#{username},#{password})")
	public void insertVisit(@Param("u_id")int u_id,@Param("username")String username,@Param("password")String password);
}
