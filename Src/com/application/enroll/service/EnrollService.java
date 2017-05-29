package com.application.enroll.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.application.entity.po.EnrollDicEntity;
import com.application.entity.po.EnrollGenerateExamNoEntity;
import com.application.entity.po.EnrollMajorEntity;
import com.application.entity.po.EnrollMajorItemEntity;
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
public class EnrollService extends BaseService
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
			if((!super.isNullOrEmpty(entity.getEmail()))){
				queryString.append(" and d.email like '%"+StringUtils.replace(entity.getEmail(), "'", "''")+"%'");
			}
			
		}
		if(!super.isNullOrEmpty(entity.getOrderBy())&&!super.isNullOrEmpty(entity.getDescOrasc())){
			queryString.append(" order by ");
			queryString.append(entity.getOrderBy());
			queryString.append(" ");
			queryString.append(entity.getDescOrasc());
		}
		return super.getHbm3Dao().findPageByQuery(queryString.toString(), pageSize, pageNo, null);
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

	public void saveEnroll(EnrollStudentEntity enroll) throws Exception
	{
		super.getHbm3Dao().save(enroll);
	}
	public void updateEnrollApprove(EnrollStudentEntity enroll) throws Exception
	{	
		String hql = "update EnrollStudentEntity d set d.enrollStatus="+enroll.getEnrollStatus()+", d.approveComment ='"+enroll.getApproveComment()+"' where d.id="+enroll.getId();
		//审批通过才生成考试号
		if(enroll.getEnrollStatus().equals(2)){
			String[] examNoRoomNo = this.generateExamNo(enroll.getTargetMajorCode()).split("&");
			hql = "update EnrollStudentEntity d set d.enrollStatus="+enroll.getEnrollStatus()+", d.approveComment ='"+enroll.getApproveComment()+"', d.examCode='"+examNoRoomNo[0]+"', d.examRoom='"+examNoRoomNo[1]+"' where d.id="+enroll.getId();
		}
		super.getHbm3Dao().batchUpdateByHql(hql);
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
//			if(enroll.getIdentify()!=null&&!"".equals(enroll.getIdentify())){
//				queryString.append(" and d.identify ='"+StringUtils.replace(enroll.getIdentify(), "'", "''")+"'");
//			}
			
		}
		List<EnrollStudentEntity> list= super.getHbm3Dao().find(queryString.toString());
		if(list==null || list.size()<1) return null;
		return list.get(0);
	}
	/**
	 * 准考证号生成规则：20140100101,2014表示年份，01表示报考的专业（01表示环境设计，02软件工程，03电气工程及其自动化，04金融学），001表示考场号，01表示座位号，一个考场最多坐30人
	 * @param majorCode
	 * @return
	 */
	private String generateExamNo(String majorCode){
		String returnString = null;
		String examRoom = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy");//设置日期格式
		String year = df.format(new Date());
		String updateString = "";
		if(majorCode!=null&&!"".equals(majorCode)){
			EnrollMajorEntity majorEntity = this.getEnrollMajorByCode(majorCode);
			if(majorEntity!=null){
				StringBuffer queryString = new StringBuffer(" from EnrollGenerateExamNoEntity d where d.majorNo = '"+StringUtils.replace(majorEntity.getNo().trim(), "'", "''")+"'");
				List<EnrollGenerateExamNoEntity> list= super.getHbm3Dao().find(queryString.toString());
				if(list!=null&&list.size()>0){
					EnrollGenerateExamNoEntity entity = list.get(0);
					int seatint = entity.getSeatNo();
					if(seatint < 30){
						returnString = year+majorEntity.getNo().trim()+String.format("%03d",entity.getRoomNo())+String.format("%02d",(seatint+1));
						examRoom = majorEntity.getName()+"专业的第"+entity.getRoomNo()+"考场";
						updateString = "update EnrollGenerateExamNoEntity set seatNo="+(seatint+1)+" where majorNo='"+majorEntity.getNo().trim()+"'";
					}else{
						returnString = year+majorEntity.getNo().trim()+String.format("%03d",(entity.getRoomNo()+1))+String.format("%02d",(1));
						examRoom = majorEntity.getName()+"专业的第"+(entity.getRoomNo()+1)+"考场";
						updateString = "update EnrollGenerateExamNoEntity set seatNo= 1, roomNo= "+(entity.getRoomNo()+1)+" where majorNo='"+majorEntity.getNo().trim()+"'";
					}
					super.getHbm3Dao().batchUpdateByHql(updateString);
				}else{
					EnrollGenerateExamNoEntity entity = new EnrollGenerateExamNoEntity();
					entity.setMajorNo(majorEntity.getNo());
					entity.setRoomNo(1);
					entity.setSeatNo(1);
					returnString = year+majorEntity.getNo()+"00101";
					super.getHbm3Dao().save(entity);
					examRoom = majorEntity.getName()+"专业的第1考场";
				}
			}
		}
		return returnString+"&"+examRoom;
		
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
	
	public List<EnrollDicEntity> getEnrollDicByType(String dicType){
		String sql = "from EnrollDicEntity where dicType = '"+StringUtils.replace(dicType, "'", "''")+"'";
		return super.getHbm3Dao().find(sql);
	}
}
