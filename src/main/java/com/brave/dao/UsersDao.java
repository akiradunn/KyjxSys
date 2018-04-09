package com.brave.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.brave.entity.Users;

@Repository(value = "usersDao")
public interface UsersDao {
	@Select("select * from users where u_name = #{u_name}")
	public Users getUsersFromName(String u_name);

	@Select("select * from users")
	public List<Users> getAllUsers();
	
	@Select("select * from users where u_id = #{u_id}")
	public Users getUsersFromId(int u_id);

	@Delete("delete from users where u_name =#{u_name}")
	public void deleteUsers(String u_name);

	@Insert("insert into users(u_id,u_name,u_identity,u_wholePoints) values(#{u_id},#{u_name},#{u_identity},#{u_wholePoints})")
	public void insertUsers(int u_id, String u_name, String u_identity, int u_wholePoints);
	
	@Update("update users set u_name=#{u_name},u_sex=#{u_sex} where u_id=#{u_id}")
	public void updateUsers(@Param("u_id")int u_id, @Param("u_name")String u_name, @Param("u_sex")String u_sex);

	@Update("update users set u_wholePoints = #{u_wholePoints} where u_id=#{u_id}")
	public void updateUsersPoints(@Param("u_id")int u_id,@Param("u_wholePoints")int u_wholePoints);
}
