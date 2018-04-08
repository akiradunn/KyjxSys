package com.brave.dao;
//数据库Dao层尽量不要写具体函数,要写通用函数,多抽象，要不然要经常改
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
	public UsersKyxm getUsersKyxmFromUidKid(@Param("u_id")int u_id,@Param("k_id")int k_id);
	
	//用户申报课题
	@Insert("insert into UsersKyxm(u_id,k_id,k_status,k_applyTime,k_setTime,k_endTime,k_scoreApplied,k_completed) "
			+ "values(#{u_id},#{k_id},#{k_status},#{k_applyTime},#{k_setTime},#{k_endTime},#{k_scoreApplied},#{k_completed})")
	public void insertKyxm(@Param("u_id")int u_id, @Param("k_id")int k_id, @Param("k_status")String k_status, @Param("k_applyTime")String k_applyTime, @Param("k_setTime")String k_setTime, @Param("k_endTime")String k_endTime, @Param("k_scoreApplied")boolean k_scoreApplied, @Param("k_completed")boolean k_completed);
	
	//用户申请结项课题
	@Update("update UsersKyxm set k_status=#{k_status} where u_id = #{u_id} and k_id = #{k_id}")
	public void applySetMyKyxm(@Param("u_id")int u_id,@Param("k_id")int k_id,@Param("k_status")String k_status);
	
	//管理员查看待审列表
	@Select("select * from UsersKyxm where k_status=#{k_status}")
	public List<UsersKyxm> getApplyingKyxmList(@Param("k_status")String k_status);
	
	//管理员对课题审批进行相应操作
	@Update("update UsersKyxm set k_status=#{k_status},k_setTime=#{k_setTime},k_endTime =#{k_endTime},k_scoreApplied=#{k_scoreApplied},k_completed=#{k_completed} where u_id=#{u_id} and k_id=#{k_id}")
	public void operateApplyingKyxm(@Param("u_id")int u_id,@Param("k_id")int k_id,@Param("k_status")String k_status,@Param("k_setTime")String k_setTime,@Param("k_endTime")String k_endTime,@Param("k_scoreApplied")boolean k_scoreApplied, @Param("k_completed")boolean k_completed);

}
