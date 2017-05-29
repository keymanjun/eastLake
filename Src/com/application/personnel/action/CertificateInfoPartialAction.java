
package com.application.personnel.action;

import org.apache.struts2.ServletActionContext;

import com.application.entity.po.CertificateEntity;
import com.application.personnel.service.CertificateInfoService;
import com.framework.FrameConstant;
import com.framework.utils.DateUtil;
import com.framework.utils.JsonStringUtils;
import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.system.entity.po.SysUser;
/**
 * 
 * @author wangjunw@cn.ibm.com
 *
 */
@SuppressWarnings("serial")
public class CertificateInfoPartialAction extends ActionSupport
{	
	private CertificateEntity certificate = null;
	private CertificateInfoService certificateInfoService = null;
	private String certificateList = "";

	@Override
	public String execute() throws Exception
    {

        String employId=ServletActionContext.getRequest().getParameter("id");
        if(employId!=null&&!"".equals(employId)){
            CertificateEntity certificateBean=new CertificateEntity();
            certificateBean.setEiId(Long.valueOf(employId));
            this.certificateList = JsonStringUtils.listToJsonString(certificateInfoService.getCertificateList(certificateBean));
        }
        return SUCCESS;
    }
	
    public String selectEntityById() throws Exception
    {

        String id=ServletActionContext.getRequest().getParameter("id");
        if(id!=null&&!"".equals(id)){
            CertificateEntity certificateBean=new CertificateEntity();
            certificateBean.setCiId(Long.valueOf(id));
            certificate = certificateInfoService.getCertificateEntity(certificateBean);
        }
        return SUCCESS;
    }
	
	public String saveCertificate() throws Exception 
	{
		//0 删除  1 有效
		if(this.getCertificate()!=null){
			if(this.getCertificate().getCiStatus()!=1){
				this.getCertificate().setCiStatus(new Long(1));
			}
			SysUser user = (SysUser)ServletActionContext.getRequest().getSession().getAttribute(FrameConstant.SESSION_ACEGI_USER_KEY);
			if(user!=null&&!"".equals(user)){
				this.getCertificate().setCiPerson(user.getUsername());
			}
			this.getCertificate().setCiAddDate(DateUtil.serverCurrentTimeStamp(DateUtil.ORA_DATES_FORMAT));
			certificateInfoService.saveCertificate(this.getCertificate());
		}
		CertificateEntity certificateBean=new CertificateEntity();
		certificateBean.setEiId(this.getCertificate().getEiId());
		this.certificateList = JsonStringUtils.listToJsonString(certificateInfoService.getCertificateList(certificateBean));
		System.out.println(certificateList);
		return SUCCESS;
	}
	public String deleteEntityById() throws Exception
    {

        String ids=ServletActionContext.getRequest().getParameter("id");
        
        if(ids!=null&&!"".equals(ids)){
            certificateInfoService.deleteCertificate(ids);
        }
        return SUCCESS;
    }
/*****************set/get method****************************************/
    @JSON(serialize=false)
    public CertificateInfoService getCertificateInfoService() {
            return certificateInfoService;
    }

    public void setCertificateInfoService(
                    CertificateInfoService certificateInfoService) {
            this.certificateInfoService = certificateInfoService;
    }
    @JSON(name="JSONRETURN")
	public CertificateEntity getCertificate() {
		return certificate;
	}

	public void setCertificate(CertificateEntity certificate) {
		this.certificate = certificate;
	}
	@JSON(name="JSONLIST")
	public String getCertificateList() {
		return certificateList;
	}

	public void setCertificateList(String certificateList) {
		this.certificateList = certificateList;
	}
	
}
