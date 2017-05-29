package com.application.employee.basic.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.application.entity.po.DepartmentEntity;
import com.application.entity.po.EmployeeEntity;
import com.framework.base.BaseService;
import com.framework.components.pager.PaginationSupport;
import com.framework.utils.StringUtils;

public class EmployeeInfoService extends BaseService {

	public PaginationSupport loadEmployeeInfoList(EmployeeEntity entity, int iPageNo,
			int iPageSize) throws Exception {
		PaginationSupport pageResult = null;
		StringBuffer queryString = new StringBuffer();
		queryString.append(" from EmployeeEntity e where e.status = 1");
		if (entity != null) {
//			if (!super.isNullOrEmpty(entity.getCode())) {
//				queryString.append(" and e.code like '%"
//						+ StringUtils.replace(entity.getCode(), "'", "''")
//						+ "%'");
//			}
			if (!super.isNullOrEmpty(entity.getUsername())) {
				queryString.append(" and e.username like '%"
						+ StringUtils.replace(entity.getUsername(), "'", "''")
						+ "%'");
			}
			if (entity.getSex()!=null && entity.getSex() != -1) {
				queryString.append(" and e.sex=" + entity.getSex());
			}
//			if (!super.isNullOrEmpty(entity.getIdentifie())) {
//				queryString.append(" and e.identifie like '%"
//						+ StringUtils.replace(entity.getIdentifie(), "'", "''")
//						+ "%'");
//			}
//			if (!super.isNullOrEmpty(entity.getEmail())) {
//				queryString.append(" and e.email like '%"
//						+ StringUtils.replace(entity.getEmail(), "'", "''")
//						+ "%'");
//			}
//			if (!super.isNullOrEmpty(entity.getPhone())) {
//				queryString.append(" and e.phone like '%"
//						+ StringUtils.replace(entity.getPhone(), "'", "''")
//						+ "%'");
//			}
//			if (!super.isNullOrEmpty(entity.getAddress())) {
//				queryString.append(" and e.address like '%"
//						+ StringUtils.replace(entity.getAddress(), "'", "''")
//						+ "%'");
//			}
			if (!super.isNullOrEmpty(entity.getSchool())) {
				queryString.append(" and e.school like '%"
						+ StringUtils.replace(entity.getSchool(), "'", "''")
						+ "%'");
			}
//			if (!super.isNullOrEmpty(entity.getMajor())) {
//				queryString.append(" and e.major like '%"
//						+ StringUtils.replace(entity.getMajor(), "'", "''")
//						+ "%'");
//			}
			if (!super.isNullOrEmpty(entity.getBirthday())) {
				queryString.append(" and e.birthday like '%"
						+ StringUtils.replace(entity.getBirthday(), "'", "''")
						+ "%'");
			}
			if (!super.isNullOrEmpty(entity.getNation())) {
				queryString.append(" and e.nation like '%"
						+ StringUtils.replace(entity.getNation(), "'", "''")
						+ "%'");
			}
			if (!super.isNullOrEmpty(entity.getNatives())) {
				queryString.append(" and e.natives like '%"
						+ StringUtils.replace(entity.getNatives(), "'", "''")
						+ "%'");
			}
			if (entity.getPolitics()!=null && entity.getPolitics()!= -1) {
				queryString.append(" and e.politics = "
						+ entity.getPolitics());
			}
			if(!super.isNullOrEmpty(entity.getOnboardTime())&&!super.isNullOrEmpty(entity.getOnboardTimeEnd())){  
				queryString.append(" and e.onboardTime between '"+entity.getOnboardTime()+"' and '"+entity.getOnboardTimeEnd()+"'");
			}
			if(!super.isNullOrEmpty(entity.getOrderBy())&&!super.isNullOrEmpty(entity.getDescOrasc())){
				queryString.append(" order by ");
				queryString.append(entity.getOrderBy());
				queryString.append(" ");
				queryString.append(entity.getDescOrasc());
			}
		}
		pageResult = super.getHbm3Dao().findPageByQuery(queryString.toString(), iPageNo, iPageSize, null);
		return pageResult;
	}
	public List<EmployeeEntity> loadEmployeeExport(EmployeeEntity entity){
		List<EmployeeEntity> employeeList = null;
		try{
		StringBuffer queryString = new StringBuffer();
		queryString.append(" select new EmployeeEntity(e.code,e.username,e.sex,e.birthday,e.natives,e.nation,e.politics,e.firstEducation,e.firstDegree,e.firstSchool,e.firstMajor,e.education,e.degree,e.school,e.major,e.position,e.jobTitle,e.registeredLocal,e.onboardTime,e.employeeComposition,d.name as departmentName) from EmployeeEntity e,DepartmentEntity d where e.diId = d.diId and e.status = 1");
		if (entity != null) {
//			if (!super.isNullOrEmpty(entity.getCode())) {
//				queryString.append(" and e.code like '%"
//						+ StringUtils.replace(entity.getCode(), "'", "''")
//						+ "%'");
//			}
			if (!super.isNullOrEmpty(entity.getUsername())) {
				queryString.append(" and e.username like '%"
						+ StringUtils.replace(entity.getUsername(), "'", "''")
						+ "%'");
			}
			if (entity.getSex()!=null && entity.getSex() != -1) {
				queryString.append(" and e.sex=" + entity.getSex());
			}
//			if (!super.isNullOrEmpty(entity.getIdentifie())) {
//				queryString.append(" and e.identifie like '%"
//						+ StringUtils.replace(entity.getIdentifie(), "'", "''")
//						+ "%'");
//			}
//			if (!super.isNullOrEmpty(entity.getEmail())) {
//				queryString.append(" and e.email like '%"
//						+ StringUtils.replace(entity.getEmail(), "'", "''")
//						+ "%'");
//			}
//			if (!super.isNullOrEmpty(entity.getPhone())) {
//				queryString.append(" and e.phone like '%"
//						+ StringUtils.replace(entity.getPhone(), "'", "''")
//						+ "%'");
//			}
//			if (!super.isNullOrEmpty(entity.getAddress())) {
//				queryString.append(" and e.address like '%"
//						+ StringUtils.replace(entity.getAddress(), "'", "''")
//						+ "%'");
//			}
			if (!super.isNullOrEmpty(entity.getSchool())) {
				queryString.append(" and e.school like '%"
						+ StringUtils.replace(entity.getSchool(), "'", "''")
						+ "%'");
			}
//			if (!super.isNullOrEmpty(entity.getMajor())) {
//				queryString.append(" and e.major like '%"
//						+ StringUtils.replace(entity.getMajor(), "'", "''")
//						+ "%'");
//			}
			if (!super.isNullOrEmpty(entity.getBirthday())) {
				queryString.append(" and e.birthday like '%"
						+ StringUtils.replace(entity.getBirthday(), "'", "''")
						+ "%'");
			}
			if (!super.isNullOrEmpty(entity.getNation())) {
				queryString.append(" and e.nation like '%"
						+ StringUtils.replace(entity.getNation(), "'", "''")
						+ "%'");
			}
			if (!super.isNullOrEmpty(entity.getNatives())) {
				queryString.append(" and e.natives like '%"
						+ StringUtils.replace(entity.getNatives(), "'", "''")
						+ "%'");
			}
			if (entity.getPolitics()!=null && entity.getPolitics()!= -1) {
				queryString.append(" and e.politics = "
						+ entity.getPolitics());
			}
			if(!super.isNullOrEmpty(entity.getOnboardTime())&&!super.isNullOrEmpty(entity.getOnboardTimeEnd())){  
				queryString.append(" and e.onboardTime between '"+entity.getOnboardTime()+"' and '"+entity.getOnboardTimeEnd()+"'");
			}
		}
		queryString.append(" order by e.eiId ");
		employeeList =  super.getHbm3Dao().find(queryString.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		return employeeList;
	}
	
	public void saveEmployee(EmployeeEntity entity) {
		super.getHbm3Dao().save(entity);
	}
	
	
	public int deleteMember(String memberIds) {
		//This delete is logic delete.
		String hql= " update EmployeeEntity e set e.status=0 where e.eiId in(" + memberIds + ")";
		return super.getHbm3Dao().batchUpdateByHql(hql);		
	}
	
	
	public EmployeeEntity getEmployeeEntity(EmployeeEntity employee) {		
		StringBuffer queryString = new StringBuffer();
		queryString.append(" from EmployeeEntity e where e.status = 1 ");
		if(employee != null){
			if(employee.getEiId() > -1){
				queryString.append(" and eiId = ");
				queryString.append(employee.getEiId());
			}
		}
		List<EmployeeEntity> list= super.getHbm3Dao().find(queryString.toString());
		if(list==null || list.size()<1) return null;
		return list.get(0);
	}
	
	public Boolean loadEmployeeInfo(List<EmployeeEntity> list){
		Boolean isSucess = false;
		try{
			if(list.size() > 0){
				Session session = super.getHbm3Dao().getSessionFactory().openSession();
				Transaction tx = session.beginTransaction();
				for(int i = 0; i <list.size(); i++){
					if(list.get(i).getCode()!=null&&!"".equals(list.get(i).getCode())){
						session.save(list.get(i));
					}
					if(i%20 == 0){
						session.flush();
						session.clear();
					}
				}
				tx.commit();
				session.close();
				isSucess= true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return isSucess;
	}
	public List<DepartmentEntity> loadDepartmentList(){
		String queryString = "from DepartmentEntity";
		return super.getHbm3Dao().find(queryString);
	}
//	
//	/**
//	 * 
//	* <p>方法名称: getMemberEntity|描述: </p>
//	* @param member
//	* @return
//	 */
//	public Member getMemberEntity(Member member) {		
//		StringBuffer queryString = new StringBuffer();
//		queryString.append(" from Member m where 1=1 ");
//		if(member != null){
//			if(!super.isNullOrEmpty(member.getAccount())){
//				queryString.append(" and m.account='" + StringUtils.replace(member.getAccount(), "'", "''") + "'");
//			}
//			if(!super.isNullOrEmpty(member.getTruename())){
//				queryString.append(" and m.truename='" + StringUtils.replace(member.getTruename(), "'", "''") + "'");
//			}	
//			if(member.getMemberid() != null){
//				queryString.append(" and m.memberid=" + member.getMemberid());
//			}
//		}
//		List<Member> list= super.getHbm3Dao().find(queryString.toString());
//		if(list==null || list.size()<1) return null;
//		return list.get(0);
//	}
//	
//	/**
//	 * 
//	* <p>方法名称: getMemberLvByTotalJf|描述: 获得会员等级</p>
//	* @param totaljf
//	* @return
//	 */
//	public MemberLv getMemberLvByTotalJf(double totaljf) {
//		StringBuffer queryString = new StringBuffer();
//		queryString.append(" from MemberLv m where 1=1 and m.jfmin<=? and m.jsmax<=? order by m.lvid");
//		List<MemberLv> list= super.getHbm3Dao().find(queryString.toString(), totaljf, totaljf);
//		if(list == null && list.size() <= 0) return null;
//		return list.get(list.size());
//	}
}
