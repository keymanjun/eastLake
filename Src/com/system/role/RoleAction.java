
package com.system.role;

import com.framework.FrameConstant;
import com.framework.base.BaseAction;
import com.framework.components.pager.PaginationSupport;
import com.system.entity.po.SysRole;
/**
 * ��ɫ����
 * @author dafei
 *
 */
public class RoleAction extends BaseAction
{	
	private static final long serialVersionUID = 1L;
	private final static String ROLE_LIST="roleList";
	private final static String ROLE_ADD="roleAdd";
	private final static String ROLE_EDIT="roleEdit";
	
	RoleService roleService=null;
	SysRole role=null;
	
	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception 
	{
		PaginationSupport pageResult = roleService.loadRoleList(this.getRole(), super.getPageSize().intValue(), super.getStartIndex().intValue());
		this.getRequest().put(PAGER_RESULT_KEY, pageResult);
		this.setRole(this.getRole());
		return ROLE_LIST;
	}
	
	public String toEditPage() throws Exception
	{
		String Ids=this.getReq().getParameter("Ids");
		if(super.isNullOrEmpty(Ids)){
			return ROLE_ADD;
		}
		SysRole roleBean=new SysRole();
		roleBean.setRoleid(Long.valueOf(Ids));
		this.setRole(roleService.getRoleEntity(roleBean));
		return ROLE_EDIT;
	}
	
	public String saveRole() throws Exception 
	{
		String sForward=ROLE_ADD;		
		if(this.getRole().getRoleid()!=null){
			sForward=ROLE_EDIT;
		}
		if(sForward.equals(ROLE_ADD))
		{
			if(roleService.getRoleEntity(this.getRole())!=null){
				super.setMsg("error.sys.role.namedouble");
				this.setRole(this.getRole());
				return sForward;
			}
		}		
		roleService.saveRole(this.getRole(),(String)getHttpSession().getAttribute(FrameConstant.SESSION_ACEGI_ACCOUNT_KEY));
		this.setRole(this.getRole());
		return sForward;
	}
	
	public String deleteRole() throws Exception 
	{
		String Ids=this.getReq().getParameter("Ids");
		roleService.deleteRole(Ids);
		this.setRole(this.getRole());
		return execute();
	}

	public String goBack() throws Exception 
	{
		this.setRole(new SysRole());
		return execute();
	}

	public RoleService getRoleService() {
		return this.roleService;
	}

	public SysRole getRole() {
		return this.role;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public void setRole(SysRole role) {
		this.role = role;
	}
	
}
