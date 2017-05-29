package com.application.enroll.service;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import com.application.entity.po.EnrollMajorEntity;
import com.application.entity.po.EnrollMajorItemEntity;
import com.application.entity.po.EnrollMajorScoreEntity;
import com.application.entity.po.EnrollStudentEntity;
import com.framework.FrameConstant;
import com.framework.base.BaseService;
import com.framework.components.pager.PaginationSupport;
import com.framework.utils.StringUtils;
import com.system.entity.po.SysUser;

/**
 * 
 * @author wangjunw@cn.ibm.com
 *
 */
public class EnrollScoreService extends BaseService
{
	public PaginationSupport loadEnrollList(EnrollStudentEntity entity, int pageSize,int pageNo) throws Exception
	{
		StringBuffer queryString = new StringBuffer();
		queryString.append(" from EnrollStudentEntity d  where 1=1");
		if(entity!=null){
			if(!super.isNullOrEmpty(entity.getNo())){
				queryString.append(" and d.no like '%"+StringUtils.replace(entity.getNo(), "'", "''")+"%'");
			}
			if(!super.isNullOrEmpty(entity.getName())){
				queryString.append(" and d.name like '%"+StringUtils.replace(entity.getName(), "'", "''")+"%'");
			}
			if(entity.getEnrollStatus()!=null&&entity.getEnrollStatus() > 0){
				queryString.append(" and d.enrollStatus="+entity.getEnrollStatus());
			}
			if((!super.isNullOrEmpty(entity.getTargetMajorCode()))&&(!entity.getTargetMajorCode().equals("-1"))){
				queryString.append(" and d.targetMajorCode='"+StringUtils.replace(entity.getTargetMajorCode(), "'", "''")+"'");
			}
			if((!super.isNullOrEmpty(entity.getIdentify()))){
				queryString.append(" and d.identify like '%"+StringUtils.replace(entity.getIdentify(), "'", "''")+"%'");
			}
			if((!super.isNullOrEmpty(entity.getPhone()))){
				queryString.append(" and d.phone like '%"+StringUtils.replace(entity.getPhone(), "'", "''")+"%'");
			}
			queryString.append(" and d.id not in (select distinct enrollId from EnrollMajorScoreEntity)");
		}
		if(!super.isNullOrEmpty(entity.getOrderBy())&&!super.isNullOrEmpty(entity.getDescOrasc())){
			queryString.append(" order by ");
			queryString.append(entity.getOrderBy());
			queryString.append(" ");
			queryString.append(entity.getDescOrasc());
		}
		return super.getHbm3Dao().findPageByQuery(queryString.toString(), pageSize, pageNo, null);
	}
	
	public PaginationSupport loadEnrolScoreEdit(EnrollStudentEntity entity, int pageSize,int pageNo) throws Exception
	{
		StringBuffer queryString = new StringBuffer();
		queryString.append(" from EnrollStudentEntity d  where 1=1");
		if(entity!=null){
			if(!super.isNullOrEmpty(entity.getNo())){
				queryString.append(" and d.no like '%"+StringUtils.replace(entity.getNo(), "'", "''")+"%'");
			}
			if(!super.isNullOrEmpty(entity.getName())){
				queryString.append(" and d.name like '%"+StringUtils.replace(entity.getName(), "'", "''")+"%'");
			}
			if(entity.getEnrollStatus()!=null&&entity.getEnrollStatus() > 0){
				queryString.append(" and d.enrollStatus="+entity.getEnrollStatus());
			}
			if(entity.getScoreStatus()!=null&&entity.getScoreStatus() > 0){
				queryString.append(" and d.scoreStatus="+entity.getScoreStatus());
			}
			if((!super.isNullOrEmpty(entity.getTargetMajorCode()))&&(!entity.getTargetMajorCode().equals("-1"))){
				queryString.append(" and d.targetMajorCode='"+StringUtils.replace(entity.getTargetMajorCode(), "'", "''")+"'");
			}
			if((!super.isNullOrEmpty(entity.getIdentify()))){
				queryString.append(" and d.identify like '%"+StringUtils.replace(entity.getIdentify(), "'", "''")+"%'");
			}
			if((!super.isNullOrEmpty(entity.getPhone()))){
				queryString.append(" and d.phone like '%"+StringUtils.replace(entity.getPhone(), "'", "''")+"%'");
			}
			queryString.append(" and d.id in (select distinct enrollId from EnrollMajorScoreEntity)");
		}
		if(!super.isNullOrEmpty(entity.getOrderBy())&&!super.isNullOrEmpty(entity.getDescOrasc())){
			queryString.append(" order by ");
			queryString.append(entity.getOrderBy());
			queryString.append(" ");
			queryString.append(entity.getDescOrasc());
		}
		return super.getHbm3Dao().findPageByQuery(queryString.toString(), pageSize, pageNo, null);
	}
	
	public List<EnrollMajorItemEntity> loadMajorItemListByMajorCode(String code) throws Exception{
		StringBuffer queryString = new StringBuffer(" from EnrollMajorItemEntity d where 1=1 ");
		queryString.append(" and d.orginalNo = '"+StringUtils.replace(code, "'", "''")+"'");
		return super.getHbm3Dao().find(queryString.toString());
	}
	
	public List<EnrollMajorScoreEntity> loadMajorScoreListByEnrollId(String id) throws Exception{
		StringBuffer queryString = new StringBuffer(" from EnrollMajorScoreEntity d where 1=1 ");
		queryString.append(" and d.enrollId = "+StringUtils.replace(id, "'", "''")+"");
		return super.getHbm3Dao().find(queryString.toString());
	}
	
	public void updateEnrollScoreStatus(Integer status,EnrollStudentEntity entity) throws Exception{
		if(!super.isNullOrEmpty(entity.getApproveScoreComment())){
			String hql = "update EnrollStudentEntity set scoreStatus = ?, approveScoreComment = ? where id = ?";
			super.getHbm3Dao().updateByHql(hql, status,entity.getApproveScoreComment(), entity.getId());
		}else{
			String hql = "update EnrollStudentEntity set scoreStatus = ? where id = ?";
			super.getHbm3Dao().updateByHql(hql, status,entity.getId());
		}
	}
	
	
	
	public synchronized void saveEnrollScore (EnrollMajorScoreEntity enroll) throws Exception
	{
		super.getHbm3Dao().save(enroll);
	}
	
	public PaginationSupport loadEnrollStudent(EnrollStudentEntity entity, int pageSize,int pageNo) throws Exception
	{
		SysUser user = (SysUser)ServletActionContext.getRequest().getSession().getAttribute(FrameConstant.SESSION_ACEGI_USER_KEY);
		StringBuffer queryString = new StringBuffer();
		queryString.append(" from EnrollStudentEntity d  where userId="+user.getUserid());
		
		if(!super.isNullOrEmpty(entity.getOrderBy())&&!super.isNullOrEmpty(entity.getDescOrasc())){
			queryString.append(" order by ");
			queryString.append(entity.getOrderBy());
			queryString.append(" ");
			queryString.append(entity.getDescOrasc());
		}
		return super.getHbm3Dao().findPageByQuery(queryString.toString(), pageSize, pageNo, null);
	}
	
	public List<EnrollStudentEntity> loadEnrollList(EnrollStudentEntity entity){
		StringBuffer queryString = new StringBuffer();
		queryString.append(" from EnrollStudentEntity d  where 1=1");
		if(entity!=null){
			if(!super.isNullOrEmpty(entity.getNo())){
				queryString.append(" and d.no like '%"+StringUtils.replace(entity.getNo(), "'", "''")+"%'");
			}
			if(!super.isNullOrEmpty(entity.getName())){
				queryString.append(" and d.name like '%"+StringUtils.replace(entity.getName(), "'", "''")+"%'");
			}
			if(entity.getEnrollStatus()!=null&&entity.getEnrollStatus() > 0){
				queryString.append(" and d.enrollStatus="+entity.getEnrollStatus());
			}
		}
		return super.getHbm3Dao().find(queryString.toString());
	}

	
	public List<EnrollStudentEntity> loadEnrollListExport(EnrollStudentEntity entity){
		List<EnrollStudentEntity> returnEntity = null;
		StringBuffer queryString = new StringBuffer();
		queryString.append(" from EnrollStudentEntity d  where 1=1");
		if(entity!=null){
			if(!super.isNullOrEmpty(entity.getNo())){
				queryString.append(" and d.no like '%"+StringUtils.replace(entity.getNo(), "'", "''")+"%'");
			}
			if(!super.isNullOrEmpty(entity.getName())){
				queryString.append(" and d.name like '%"+StringUtils.replace(entity.getName(), "'", "''")+"%'");
			}
			if(entity.getEnrollStatus()!=null&&entity.getEnrollStatus() > 0){
				queryString.append(" and d.enrollStatus="+entity.getEnrollStatus());
			}
			if(entity.getScoreStatus()!=null&&entity.getScoreStatus() > 0){
				queryString.append(" and d.scoreStatus="+entity.getScoreStatus());
			}
			if((!super.isNullOrEmpty(entity.getTargetMajorCode()))&&(!entity.getTargetMajorCode().equals("-1"))){
				queryString.append(" and d.targetMajorCode='"+StringUtils.replace(entity.getTargetMajorCode(), "'", "''")+"'");
			}
		}
		returnEntity =  super.getHbm3Dao().find(queryString.toString());
		if(returnEntity!=null&&returnEntity.size()>0){
			for(EnrollStudentEntity enrollEntity:returnEntity){
				if(enrollEntity.getMajorScore().iterator().hasNext()){
					//EnrollMajorScoreEntity scoreEntity = enrollEntity.getMajorScore().iterator().next();
					enrollEntity.setMajorScore(enrollEntity.getMajorScore());
				}
			}
		}
		return returnEntity;
	}
	public void saveEnroll(EnrollStudentEntity enroll) throws Exception
	{
		super.getHbm3Dao().save(enroll);
	}
	
	public void deleteEnroll(String ids) throws Exception
	{
		String hql=" delete from EnrollStudentEntity d where d.id in("+ids+")";
		super.getHbm3Dao().batchUpdateByHql(hql);	
	}


	public EnrollStudentEntity getEnrollEntity(EnrollStudentEntity enroll) 
	{		
		StringBuffer queryString = new StringBuffer(" from EnrollStudentEntity d where 1=1 ");
		if(enroll!=null){
			if(enroll.getId()!=null&&enroll.getId() > 0){
				queryString.append(" and d.id="+enroll.getId());
			}
			if(enroll.getUserId()!=null&&enroll.getUserId()>0){
				queryString.append(" and d.userId ="+enroll.getUserId());
			}
		}
		List<EnrollStudentEntity> list= super.getHbm3Dao().find(queryString.toString());
		if(list==null || list.size()<1) return null;
		return list.get(0);
	}
	public HashMap<String,String> getMajorHash(){
		String sql = "from EnrollMajorEntity";
		HashMap<String,String> hs =null;
		List<EnrollMajorEntity> list = super.getHbm3Dao().find(sql);
		if(list!=null&&list.size()>0){
			hs = new HashMap<String,String>();
			for(EnrollMajorEntity entity:list){
				hs.put(entity.getOrginalNo(), entity.getName());
			}
		}
		return hs;
	}
	
	public EnrollMajorEntity getEnrollMajorByCode(String code){
		String sql = "from EnrollMajorEntity where orginalNo = '"+StringUtils.replace(code, "'", "''")+"'";
		List<EnrollMajorEntity> list = super.getHbm3Dao().find(sql);
		if(list==null || list.size()<1) return null;
		return list.get(0);
	}
	public List<EnrollMajorItemEntity> getEnrollMajorItemByOriginalNo(String code){
		String sql = "from EnrollMajorItemEntity where orginalNo = '"+StringUtils.replace(code, "'", "''")+"'";
		return super.getHbm3Dao().find(sql);
	}
	
	public List<EnrollStudentEntity> loadEnrollListByTargetMajorCode(EnrollStudentEntity entity){
		StringBuffer queryString = new StringBuffer();
		queryString.append(" from EnrollStudentEntity d  where 1=1");
		if(entity!=null){
			if(!super.isNullOrEmpty(entity.getTargetMajorCode())){
				queryString.append(" and d.targetMajorCode = '"+StringUtils.replace(entity.getTargetMajorCode(), "'", "''")+"'");
			}
			if(entity.getEnrollStatus()!=null&&entity.getEnrollStatus() > 0){
				queryString.append(" and d.enrollStatus="+entity.getEnrollStatus());
			}
		}
		return super.getHbm3Dao().find(queryString.toString());
	}
	
	public List<EnrollStudentEntity> loadEnrollListByIdentify(EnrollStudentEntity entity){
		StringBuffer queryString = new StringBuffer();
		queryString.append(" from EnrollStudentEntity d  where 1=1");
		if(entity!=null){
			if(!super.isNullOrEmpty(entity.getIdentify())){
				queryString.append(" and d.identify = '"+StringUtils.replace(entity.getIdentify(), "'", "''")+"'");
			}
			if(entity.getId()!=null && entity.getId() > 0){
				queryString.append(" and d.id != "+entity.getId());
			}
		}
		return super.getHbm3Dao().find(queryString.toString());
	}
}
