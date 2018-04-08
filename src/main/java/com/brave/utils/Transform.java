package com.brave.utils;

import java.util.LinkedList;
import java.util.List;
import com.brave.entity.UsersKyxm;
import com.brave.service.KyxmDaoService;
import com.brave.show.ShowKyxms;

public class Transform {
	public static List<ShowKyxms> transfromUsersKyxmList(List<UsersKyxm> store,KyxmDaoService kyxmDaoService){
		List<ShowKyxms> ret = new LinkedList<ShowKyxms>();
		for(UsersKyxm tempUsersKyxm : store){
			ShowKyxms tempShowKyxms = new ShowKyxms();
			tempShowKyxms.setU_id(tempUsersKyxm.getU_id());
			tempShowKyxms.setK_name(kyxmDaoService.getKyxmFromId(tempUsersKyxm.getK_id()).getK_name());
			tempShowKyxms.setK_score(kyxmDaoService.getKyxmFromId(tempUsersKyxm.getK_id()).getK_score());
			tempShowKyxms.setK_status(tempUsersKyxm.getK_status());
			tempShowKyxms.setK_applyTime(tempUsersKyxm.getK_applyTime());
			tempShowKyxms.setK_setTime(tempUsersKyxm.getK_setTime());
			tempShowKyxms.setK_endTime(tempUsersKyxm.getK_endTime());
			tempShowKyxms.setK_scoreApplied(tempUsersKyxm.isK_scoreApplied());
			tempShowKyxms.setK_completed(tempUsersKyxm.isK_completed());
			tempShowKyxms.setK_id(tempUsersKyxm.getK_id());
			ret.add(tempShowKyxms);
		}
		return ret;
	}
}
