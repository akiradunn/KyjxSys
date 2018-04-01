package com.brave.dao;

import java.util.List;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository(value = "authoritiesDao")
public interface AuthoritiesDao {
	@Select("select r_role from role where r_id=#{r_id}")
	public List<String> getAuthorities(int r_id);
}
