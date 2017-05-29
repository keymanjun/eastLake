
package com.application.enroll.action;


import java.util.List;

import com.application.enroll.service.EnrollService;
import com.application.entity.po.EnrollStudentEntity;
import com.framework.base.BaseAction;
import com.googlecode.jsonplugin.annotations.JSON;
/**
 * 
 * @author wangjunw@cn.ibm.com
 *
 */
public class EnrollCheckAction extends BaseAction
{	
	private static final long serialVersionUID = 1L;
	EnrollService enrollService = null;
	private String resultReturn = null;
	
	public String checkIdentify() throws Exception{
		List<EnrollStudentEntity> list = null;
		String identify = this.getReq().getParameter("identify");
		String id = this.getReq().getParameter("id");
		if(!super.isNullOrEmpty(identify)){
			EnrollStudentEntity entity = new EnrollStudentEntity();
			entity.setIdentify(identify);
			if(!super.isNullOrEmpty(id)){
				entity.setId(Long.parseLong(id));
			}
			list = enrollService.loadEnrollListByIdentify(entity);
			if(list!=null&&list.size()>0){
				this.resultReturn = "true";
			}else{
				this.resultReturn = "false";
			}
		}
		return SUCCESS;
	}
	
	public String popEnrollDetail() throws Exception{
		String id = this.getReq().getParameter("id");
		if(!super.isNullOrEmpty(id)){
			EnrollStudentEntity entity = new EnrollStudentEntity();
			entity.setId(Long.parseLong(id));
			entity = enrollService.getEnrollEntity(entity);
			if(entity!=null){
				this.resultReturn = entity.toJsonString();
			}
		}
	return SUCCESS;
	}
/*****************set/get method****************************************/
	@JSON(serialize=false)
	public EnrollService getEnrollService() {
		return enrollService;
	}

	public void setEnrollService(EnrollService enrollService) {
		this.enrollService = enrollService;
	}
	@JSON(name="JSONRETURN")
	public String getResultReturn() {
		return resultReturn;
	}

	public void setResultReturn(String resultReturn) {
		this.resultReturn = resultReturn;
	}

}
