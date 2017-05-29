
package com.system.user;


import com.framework.FrameConstant;
import com.framework.base.BaseAction;
import com.framework.components.pager.PaginationSupport;
import com.system.entity.po.SysUser;
/**
 * 
 * @author dafei
 *
 */
public class UserAction extends BaseAction
{	
	private static final long serialVersionUID = 1L;
	private final static String USER_LIST="userList";
	private final static String USER_ADD="userAdd";
	private final static String USER_ADD_REGISTER="userAddRegister";
	private final static String USER_ADD_REGISTER_SUCCESS="userAddRegisterSuccess";
	
	private final static String USER_EDIT="userEdit";	
	public final static Long REGISTER_USER=(long) 0;	//通过页面注册的用户
	public final static String REGISTER_USER_ROLE= "3";	//通过页面注册的用户,在数据库里面对应的role 的ID
	public final static Long GENERAL_USER=(long) 1;	//通过系统内部添加的用户
	UserService userService=null;
	SysUser user=new SysUser();
	
	
		
	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception 
	{
		PaginationSupport pageResult = userService.loadUserList(this.getUser(), super.getPageSize().intValue(), super.getStartIndex().intValue());
		this.getRequest().put(PAGER_RESULT_KEY, pageResult);
		this.setUser(user);
		return USER_LIST;
	}
	
	public String toEditPage() throws Exception
	{
		String Ids=this.getReq().getParameter("Ids");
		if(super.isNullOrEmpty(Ids)){
			return USER_ADD;
		}
		SysUser userBean=new SysUser();
		userBean.setUserid(Long.valueOf(Ids));
		this.setUser(userService.getUserEntity(userBean));
		return USER_EDIT;
	}
	
	public String editUser() throws Exception
	{
		String account=(String)this.getHttpSession().getAttribute(FrameConstant.SESSION_ACEGI_ACCOUNT_KEY);
		if(super.isNullOrEmpty(account)){
			return "logonOut";
		}
		SysUser userBean=new SysUser();
		userBean.setUseraccount(account);
		this.setUser(userService.getUserEntity(userBean));
		return USER_EDIT;
	}
	
	public String saveUser() throws Exception 
	{
		String sForward=USER_ADD;
		if(this.getUser().getUserid()!=null){
			sForward=USER_EDIT;
		}
		
		//判断帐号是否有重复
		if(sForward.equals(USER_ADD))
		{
			if(userService.getUserEntity(this.getUser())!=null)
			{
				super.setMsg("error.sys.user.namedouble");
				this.setUser(this.getUser());
				return sForward;
			}
		}
		this.getUser().setUsertype(REGISTER_USER);
		userService.saveUser(this.getUser());
		this.setUser(this.getUser());
		return sForward;
	}
	
	public String saveRegisterUser() throws Exception 
	{
		String sForward=USER_ADD_REGISTER;
		//判断帐号是否有重复
		if(userService.getUserEntity(this.getUser())!=null)
		{
			super.setMsg("您申请的账号已经存在！");
			this.setUser(this.getUser());
			return sForward;
		}
		this.getUser().setUsertype(REGISTER_USER);
		userService.saveUser(this.getUser());
		this.setUser(new SysUser());
		this.setMsg("注册成功,请登录!");
		return USER_ADD_REGISTER_SUCCESS;
	}
	
	
	
	public String deleteUser() throws Exception 
	{
		String Ids=this.getReq().getParameter("Ids");
		userService.deleteUser(Ids);
		this.setUser(this.getUser());
		return execute();
	}

	public String goBack() throws Exception 
	{
		this.setUser(new SysUser());
		return execute();
	}
	
	public SysUser getUser() {
		return this.user;
	}
	public void setUser(SysUser user) {
		this.user = user;
	}
	public UserService getUserService() {
		return this.userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
}
