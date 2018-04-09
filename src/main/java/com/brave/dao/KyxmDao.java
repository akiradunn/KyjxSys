package com.brave.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.brave.entity.Kyxm;

@Repository(value = "kyxmDao")
public interface KyxmDao {
	@Select("select * from kyxm")
	public List<Kyxm> getAllKyxm();

	@Select("select * from kyxm where k_name = #{k_name}")
	public List<Kyxm> getKyxmFromName(String k_name);

	@Insert("insert into kyxm(k_id,k_name,k_category,k_score) "
			+ "values(#{k_id},#{k_name},#{k_category},#{k_score})")
	public void insertKyxm(@Param("k_id")int k_id, @Param("k_name")String k_name, @Param("k_category")String k_category, @Param("k_score")int k_score);

	@Delete("delete from users where k_name =#{k_name}")
	public void deleteKyxm(String k_name);
	
	@Delete("delete from kyxm where k_id =#{k_id}")
	public void deleteSpecifiedKyxm(@Param("k_id")int k_id);

	@Select("select * from kyxm where k_id = #{k_id}")
	public Kyxm getKyxmFromId(int k_id);
	
	@Update("update kyxm set k_name=#{k_name},k_category=#{k_category},k_score=#{k_score} where k_id=#{k_id}")
	public void updateKyxm(@Param("k_id")int k_id,@Param("k_name")String k_name, @Param("k_category")String k_category, @Param("k_score")int k_score);
}
