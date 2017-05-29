
package com.application.personnel.action;


import com.application.department.basic.service.DepartmentInfoService;
import com.application.entity.po.DepartmentEntity;
import com.application.entity.po.EmployeeEntity;
import com.framework.FrameConstant;
import com.framework.base.BaseAction;
import com.framework.components.pager.PaginationSupport;
import com.system.entity.po.SysUser;
import com.system.user.UserService;
/**
 * 
 * @author wangjunw@cn.ibm.com
 *
 */
public class TrainingInfoAction extends BaseAction
{	
	private static final long serialVersionUID = 1L;
	private final static String DEPARTMENT_LIST="departmentList";
	private final static String DEPARTMENT_ADD="departmentAdd";
	private final static String DEPARTMENT_EDIT="departmentEdit";	
	DepartmentInfoService departmentInfoService = null;
	DepartmentEntity department=new DepartmentEntity();
	
	
		
	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception 
	{
		PaginationSupport pageResult = departmentInfoService.loadDepartmentInfoList(this.getDepartment(), super.getPageSize().intValue(), super.getStartIndex().intValue());
		this.getRequest().put(PAGER_RESULT_KEY, pageResult);
		this.setDepartment(department);
		return DEPARTMENT_LIST;
	}
	
	public String toEditPage() throws Exception
	{
		String Ids=this.getReq().getParameter("Ids");
		if(super.isNullOrEmpty(Ids)){
			return DEPARTMENT_ADD;
		}
		DepartmentEntity departmentBean=new DepartmentEntity();
		departmentBean.setDiId(Long.valueOf(Ids));
		this.setDepartment(departmentInfoService.getDepartmentEntity(departmentBean));
		return DEPARTMENT_EDIT;
	}
	
	public String saveDepartment() throws Exception 
	{
		if(this.getDepartment()!=null){
			departmentInfoService.saveDepartment(this.getDepartment());
			this.setDepartment(new DepartmentEntity());
			this.Msg = "部门信息保存成功！";
		}
		return execute();
	}
	
	
	
	public String deleteDepartment() throws Exception 
	{
		String Ids=this.getReq().getParameter("Ids");
		departmentInfoService.deleteDepartment(Ids);
		this.setDepartment(this.getDepartment());
		return execute();
	}

	public String goBack() throws Exception 
	{
		this.setDepartment(new DepartmentEntity());
		return execute();
	}
/*****************set/get method****************************************/

	public DepartmentEntity getDepartment() {
		return department;
	}

	public DepartmentInfoService getDepartmentInfoService() {
	return departmentInfoService;
}

public void setDepartmentInfoService(DepartmentInfoService departmentInfoService) {
	this.departmentInfoService = departmentInfoService;
}

	public void setDepartment(DepartmentEntity department) {
		this.department = department;
	}
	
}
