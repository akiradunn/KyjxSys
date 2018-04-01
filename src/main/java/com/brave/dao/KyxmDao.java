package com.brave.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
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
	public void insertKyxm(int k_id, String k_name, String k_category, int k_score);

	@Delete("delete from users where k_name =#{k_name}")
	public void deleteKyxm(String k_name);

	@Select("select * from kyxm where k_id = #{k_id}")
	public Kyxm getKyxmFromId(int k_id);
}
