
package com.system.rights;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.framework.FrameConstant;
import com.framework.base.BaseAction;
import com.framework.components.pager.PaginationSupport;
//import com.iss.business.grotto.entity.po.Grotto;
import com.system.entity.po.SysMenu;
import com.system.entity.po.SysRole;
import com.system.entity.po.SysUser;
import com.system.entity.vo.Menu;
import com.system.entity.vo.MenuElements;
import com.system.role.RoleService;
/**
 * 
 * @author dafei
 *
 */
public class RightsAction extends BaseAction
{	
	private static final long serialVersionUID = 1L;
	
	private static final String RIGHTS_NO="noRights";	
	private static final String RIGHTS_LOGON_OUT="logonOut";
	
	private final static String RIGHTS_MAIN="toMain";
	private final static String RIGHTS_MENULIST="toMenuList";
	
	private final static String RIGHTS_LIST="rightsList";
	private final static String RIGHTS_ADDUSER="rightsUserList";
	private final static String RIGHTS_ADDMENU="rightsMenuList";
	private final static String RIGHTS_ADDMENU_LEFT="rightsTreeLeft";
	private final static String RIGHTS_ADDMENU_RIGHT="rightsTreeRight";
	
	RightsService rightsService=null;	
	RoleService roleService=null;
	SysRole role=null;
	public String execute() throws Exception 
	{
		PaginationSupport pageResult = roleService.loadRoleList(this.getRole(), super.getPageSize().intValue(), super.getStartIndex().intValue());
		this.getRequest().put(PAGER_RESULT_KEY, pageResult);
		this.setRole(this.getRole());
		return RIGHTS_LIST;
	}
	
	/**
	 * 转到分配用户角色页面
	 * @return
	 * @throws Exception
	 */
	public String toEditUserRolePage() throws Exception 
	{
		if(this.getRole().getRoleid()==null){
			String roleId=this.getReq().getParameter("roleId");
			this.getRole().setRoleid(Long.valueOf(roleId));
		}
		this.setRole(roleService.getRoleEntity(this.getRole()));		
		List list1=rightsService.loadWFPUserForRole(this.getRole().getRoleid());
		List list2=rightsService.loadYFPUserForRole(this.getRole().getRoleid());
		this.getRequest().put("item1", list1);
		this.getRequest().put("item2", list2);
		this.setRole(this.getRole());
		return RIGHTS_ADDUSER;
	}
	
	/**
	 * 转到分配角色菜单页面
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String toEditRoleRightsPage() throws Exception 
	{
		String sysmark=FrameConstant.SYSTEM_MARK_DEFAULT;
		if(!super.isNullOrEmpty(super.getReq().getParameter("sysmark"))){
			sysmark=super.getReq().getParameter("sysmark");
		}
		if(this.getRole().getRoleid()==null){
			String roleId=this.getReq().getParameter("roleId");
			this.getRole().setRoleid(Long.valueOf(roleId));
		}
		
		//当前系统是否还包含其它子系统
		List syscodelist=(List)FrameConstant.ParsMap.get(FrameConstant.SYSTEM_MARK);
		if(syscodelist!=null && syscodelist.size()>0){
			this.getRequest().put("sysmarks", syscodelist);
		}
		this.setRole(roleService.getRoleEntity(this.getRole()));
		this.getRequest().put(FrameConstant.SYSTEM_MARK, sysmark);
		return RIGHTS_ADDMENU;
	}
	
	/**
	 * 获得角色的权限与未分配权限
	 * @return
	 * @throws Exception
	 */
	public String getRoleRights() throws Exception 
	{
		String flag=super.getReq().getParameter("flag");
		String roleid=super.getReq().getParameter("roleid");
		String sysmark=FrameConstant.SYSTEM_MARK_DEFAULT;
		if(!super.isNullOrEmpty(super.getReq().getParameter("sysmark"))){
			sysmark=super.getReq().getParameter("sysmark");
		}
		this.getRequest().put("sysmark", sysmark);
		if("left".equals(flag))
		{
			List list1=rightsService.loadLeftMenuForRole(sysmark,Long.valueOf(roleid));
			this.getRequest().put("item1", markRightsTreeString(list1));
			return RIGHTS_ADDMENU_LEFT;
		}else{
			List list2=rightsService.loadRightMenuForRole(sysmark,Long.valueOf(roleid));
			this.getRequest().put("item2", markRightsTreeString(list2));			
		}
		return RIGHTS_ADDMENU_RIGHT;
	}
	
	/**
	 * 新增、移除用户角色角色操作方法
	 * @return
	 * @throws Exception
	 */
	public String editUserForRights() throws Exception 
	{
		String userIds=this.getReq().getParameter("Ids");
		String flag=this.getReq().getParameter("flag");
		boolean isAdd="1".equals(flag);
		rightsService.updateUserRoleForRights(this.getRole().getRoleid(),userIds,isAdd);		
		return toEditUserRolePage();
	}
		
	/**
	 * 新增、移除角色菜单操作方法
	 * @return
	 * @throws Exception
	 */
	public String editMenuForRights() throws Exception 
	{		
		String flag=this.getReq().getParameter("flag");
		String menuIds=this.getReq().getParameter("Ids");
		String sysmark=FrameConstant.SYSTEM_MARK_DEFAULT;
		if(!super.isNullOrEmpty(super.getReq().getParameter("sysmark"))){
			sysmark=super.getReq().getParameter("sysmark");
		}
		try{
			if("addMenus".equals(flag))
			{
				rightsService.addRightsForRole(sysmark,this.getRole().getRoleid(),menuIds);	
			}
			else{
				rightsService.delRightsForRole(sysmark,this.getRole().getRoleid(),menuIds);	
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return toEditRoleRightsPage();
	}
		
	public String checkLogin() throws Exception
	{
//		SysUser user=(SysUser)this.getHttpSession().getAttribute(FrameConstant.SESSION_ACEGI_USER_KEY);
		SysUser user = null;
		String juser=this.getReq().getParameter("j_username");
		String jpwd=this.getReq().getParameter("j_password");
		//1、验证用户信息
		if(juser!=null&&!"".equals(juser)&&jpwd!=null&&!"".equals(jpwd)){
			user=rightsService.checkLogin(juser, jpwd);
		}
		if(user==null){
			return RIGHTS_LOGON_OUT;
		}
		this.getHttpSession().setAttribute(FrameConstant.SESSION_ACEGI_ACCOUNT_KEY, user.getUseraccount());
		this.getHttpSession().setAttribute(FrameConstant.SESSION_ACEGI_USER_KEY, user);
		return RIGHTS_MAIN;
	}
	
	
	public String savePassword() throws Exception
	{
		String account=(String)this.getHttpSession().getAttribute(FrameConstant.SESSION_ACEGI_ACCOUNT_KEY);
		if(super.isNullOrEmpty(account)) return RIGHTS_LOGON_OUT;
		String jpwd=this.getReq().getParameter("newpwd");		
		SysUser user=rightsService.checkName(account);
		user.setUserpwd(jpwd);
		rightsService.savePassword(user);
		return loginOut();
	}
	
	/**
	 * 获得登录帐号所用的权限查询方法
	 * @return
	 * @throws Exception
	 */
	public String getMenuListForRights()  throws Exception
	{		
		String sysmark=FrameConstant.SYSTEM_MARK_DEFAULT;
		String account=(String)this.getHttpSession().getAttribute(FrameConstant.SESSION_ACEGI_ACCOUNT_KEY);
		SysUser user=rightsService.checkName(account);
		if(user==null){
			return RIGHTS_LOGON_OUT;
		}
		List<SysMenu> menuList=rightsService.loadMenuRightsByUser(sysmark,user);
		if(menuList==null || menuList.size()<1){
			return toNoRights();
		}
		getReq().setAttribute("menus", toJson(sysmark,menuList));
		return RIGHTS_MENULIST;
	}
	
	/**
	 * 用于将帐号对应的权限集合转换为json字符串,供页面菜单脚本引用
	 * @param menuList
	 * @return
	 */
	private String toJson(String sysmark,List<SysMenu> menuList) 
	{
		//**********json add**********************		
		JSONObject obj = new JSONObject();
		List<Menu> list= new ArrayList<Menu>();
		Menu menu = null;
		
		Map<String, Menu> menuMap = new HashMap<String, Menu>();
		List<MenuElements> elementList = null;
		int i = 0;

		for(SysMenu menuel : menuList) {
			if(menuel.getParentmenucode().equals(sysmark)) {
				menu = new Menu();
				menu.setMenuid(Integer.toString(++i));
				menu.setMenuname(menuel.getMenuname());
				menu.setMenus(new ArrayList<MenuElements>());
				list.add(menu);
				menuMap.put(menuel.getMenucode(), menu);
			}
		}
		MenuElements elements =null;
		for(SysMenu menuel : menuList) {
			if(!menuel.getParentmenucode().equals(sysmark)) {
					menu = menuMap.get(menuel.getParentmenucode());
					if(menu!=null) {
					elementList = menuMap.get(menuel.getParentmenucode()).getMenus();
				    elements = new MenuElements();
				    elements.setMenuname(menuel.getMenuname());
				    elements.setUrl(menuel.getMenuurl());
					elementList.add(elements);
				}
			}
		}
		obj.put("menus", list);
		//---end
		System.out.println(obj.toString());
		return obj.toString();
	}
	
	/**
	 * 用于将角色对应的权限集合转换为key:value值对字符串，供权限checkbox树引用
	 * @param menuList
	 * @return
	 * @throws Exception
	 */
	private String markRightsTreeString(List<SysMenu> menuList) throws Exception
	{
		String sTree="";
		if(menuList==null || menuList.size()<1) return "";
		for(int i=0;i<menuList.size();i++)
		{
			SysMenu menu=menuList.get(i);
			sTree+=menu.getMenucode()+","+menu.getParentmenucode()+","+menu.getMenuname()+";";
		}
		return sTree;
	}
	
	
	public String loginOut() throws Exception
	{		
		super.getHttpSession().removeAttribute(FrameConstant.SESSION_ACEGI_ACCOUNT_KEY);
		super.getHttpSession().removeAttribute(FrameConstant.SESSION_ACEGI_USER_KEY);
		return RIGHTS_LOGON_OUT;
	}
	
	public String toNoRights() throws Exception
	{		
		return RIGHTS_NO;
	}
	
	public String goBack() throws Exception 
	{
		this.setRole(new SysRole());
		return execute();
	}
	
	public SysRole getRole() {
		return this.role;
	}

	public void setRole(SysRole role) {
		this.role = role;
	}

	public RoleService getRoleService() {
		return this.roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public RightsService getRightsService() {
		return this.rightsService;
	}

	public void setRightsService(RightsService rightsService) {
		this.rightsService = rightsService;
	}
	
	
	
}
