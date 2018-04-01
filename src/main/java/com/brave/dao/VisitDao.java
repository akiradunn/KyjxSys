package com.brave.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.brave.entity.Visit;

@Repository(value = "visitDao")
public interface VisitDao {
	@Select("select * from visit where username=#{username}")
	public Visit getVisit(String username);
	@Select("select u_id from visit where username=#{username}")
	public int getVisitUID(String username);
}
