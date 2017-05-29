package com.application.personnel.service;

import java.util.List;

import com.application.entity.po.CertificateEntity;
import com.application.entity.po.DepartmentEntity;
import com.framework.base.BaseService;
import com.framework.components.pager.PaginationSupport;
import com.framework.utils.StringUtils;

/**
 * 
 * @author wangjunw@cn.ibm.com
 *
 */
public class CertificateInfoService extends BaseService
{		
	public PaginationSupport loadCertificateInfoList(CertificateEntity entity, int pageSize,int pageNo) throws Exception
	{
		StringBuffer queryString = new StringBuffer();
		queryString.append(" from CertificateEntity c  where 1=1");
		if(entity!=null){
//			if(!super.isNullOrEmpty(entity.getName())){
//				queryString.append(" and d.name like '%"+StringUtils.replace(entity.getName(), "'", "''")+"%'");
//			}
		}
		if(!super.isNullOrEmpty(entity.getOrderBy())&&!super.isNullOrEmpty(entity.getDescOrasc())){
			queryString.append(" order by ");
			queryString.append(entity.getOrderBy());
			queryString.append(" ");
			queryString.append(entity.getDescOrasc());
		}
		return super.getHbm3Dao().findPageByQuery(queryString.toString(), pageSize, pageNo, null);
	}
	

	public void saveCertificate(CertificateEntity certificate) throws Exception
	{
		super.getHbm3Dao().save(certificate);
	}
	
	public void deleteCertificate(String ids) throws Exception
	{
//		String hql=" delete from CertificateEntity c where c.ciId in("+ids+")";
		String hql=" update CertificateEntity c  set c.ciStatus = 0 where c.ciId in("+ids+")";
		super.getHbm3Dao().batchUpdateByHql(hql);		
	}


	public CertificateEntity getCertificateEntity(CertificateEntity certificate) 
	{		
		StringBuffer queryString = new StringBuffer(" from CertificateEntity c where 1=1 ");
		if(certificate!=null){
			if(certificate.getCiId()> -1){
				queryString.append(" and c.ciId = "+certificate.getCiId());
			}
//			if(!super.isNullOrEmpty(certificate.getName())){
//				queryString.append(" and u.name like '%"+StringUtils.replace(department.getName(), "'", "''")+"%'");
//			}	
		}
		List<CertificateEntity> list= super.getHbm3Dao().find(queryString.toString());
		if(list==null || list.size()<1) return null;
		return list.get(0);
	}
	public List<CertificateEntity> getCertificateList(CertificateEntity certificate) 
	{		
		StringBuffer queryString = new StringBuffer(" from CertificateEntity c where 1=1 ");
		if(certificate!=null){
			if(certificate.getEiId()!= -1){
				queryString.append(" and c.eiId = "+certificate.getEiId());
			}	
		}
		List<CertificateEntity> list= super.getHbm3Dao().find(queryString.toString());
		if(list==null || list.size()<1) return null;
		return list;
	}

}
