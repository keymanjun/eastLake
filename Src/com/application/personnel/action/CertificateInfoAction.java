
package com.application.personnel.action;


import java.util.ArrayList;
import java.util.List;

import com.application.employee.basic.service.EmployeeInfoService;
import com.application.entity.po.CertificateEntity;
import com.application.entity.po.EmployeeEntity;
import com.application.personnel.service.CertificateInfoService;
import com.framework.base.BaseAction;
import com.framework.components.pager.PaginationSupport;
/**
 * 
 * @author wangjunw@cn.ibm.com
 *
 */
public class CertificateInfoAction extends BaseAction
{	
	
	/**
	 * @param serialVersionUID long
	 */
	private static final long serialVersionUID = 5125316454985727731L;
	
	private final static String CERTIFICATE_LIST="certificateList";
	private final static String CERTIFICATE_ADD="certificateAdd";
	private final static String CERTIFICATE_EDIT="certificateEdit";	
	private EmployeeInfoService employeeInfoService = null;
	private CertificateInfoService certificateInfoService = null;
	private EmployeeEntity employeeInfo = new EmployeeEntity();
	private List<CertificateEntity> certificateList = new ArrayList<CertificateEntity>();
	private CertificateEntity certificate = null;
	
	
	
		
	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception 
	{
		PaginationSupport pageResult = employeeInfoService.loadEmployeeInfoList(this.getEmployeeInfo(), super.getPageSize().intValue(), super.getStartIndex().intValue());
		this.getRequest().put(PAGER_RESULT_KEY, pageResult);
		this.setEmployeeInfo(this.getEmployeeInfo());
		return CERTIFICATE_LIST;
	}
	
	public String toEditPage() throws Exception
	{
		String Ids=this.getReq().getParameter("Ids");
		if(super.isNullOrEmpty(Ids)){
			return this.goBack();
		}
		EmployeeEntity employeeReturn = new EmployeeEntity();
		employeeReturn.setEiId(Long.parseLong(Ids));
		employeeReturn = employeeInfoService.getEmployeeEntity(employeeReturn);
		this.setEmployeeInfo(employeeReturn);
		CertificateEntity certificateBean=new CertificateEntity();
		certificateBean.setEiId(Long.valueOf(Ids));
		this.setCertificateList(certificateInfoService.getCertificateList(certificateBean));
		return CERTIFICATE_EDIT;
	}
	
	public String saveCertificate() throws Exception 
	{
		if(this.getCertificate()!=null){
			certificateInfoService.saveCertificate(this.getCertificate());
			this.setCertificate(new CertificateEntity());
			this.Msg = "信息保存成功！";
		}
		return execute();
	}
	
	
	
	public String deleteCertificate() throws Exception 
	{
//		String Ids=this.getReq().getParameter("Ids");
//		certificateInfoService.deleteCertificate(Ids);
//		this.setCertificate(this.getCertificate());
		return execute();
	}

	public String goBack() throws Exception 
	{
//		this.setCertificate(new CertificateEntity());
		return execute();
	}
/*****************set/get method****************************************/

	public EmployeeInfoService getEmployeeInfoService() {
		return employeeInfoService;
	}

	public void setEmployeeInfoService(EmployeeInfoService employeeInfoService) {
		this.employeeInfoService = employeeInfoService;
	}

	public CertificateInfoService getCertificateInfoService() {
		return certificateInfoService;
	}

	public void setCertificateInfoService(
			CertificateInfoService certificateInfoService) {
		this.certificateInfoService = certificateInfoService;
	}

	public EmployeeEntity getEmployeeInfo() {
		return employeeInfo;
	}

	public void setEmployeeInfo(EmployeeEntity employeeInfo) {
		this.employeeInfo = employeeInfo;
	}

	public List<CertificateEntity> getCertificateList() {
		return certificateList;
	}

	public void setCertificateList(List<CertificateEntity> certificateList) {
		this.certificateList = certificateList;
	}

	public CertificateEntity getCertificate() {
		return certificate;
	}

	public void setCertificate(CertificateEntity certificate) {
		this.certificate = certificate;
	}

}
