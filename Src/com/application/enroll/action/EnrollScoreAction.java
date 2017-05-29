
package com.application.enroll.action;


import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts2.ServletActionContext;

import com.application.enroll.service.EnrollScoreService;
import com.application.enroll.service.EnrollService;
import com.application.entity.po.EnrollMajorItemEntity;
import com.application.entity.po.EnrollMajorScoreEntity;
import com.application.entity.po.EnrollStudentEntity;
import com.framework.FrameConstant;
import com.framework.base.BaseAction;
import com.framework.components.pager.PaginationSupport;
import com.system.entity.po.SysUser;
/**
 * 
 * @author wangjunw@cn.ibm.com
 *
 */
public class EnrollScoreAction extends BaseAction
{	
	private static final long serialVersionUID = 1L;
	private final static String ENROLL_LIST="enrollList";
	private final static String ENROLL_EDIT_LIST="enrollEditList";
	private final static String ENROLL_APPROVE_LIST="enrollApproveList";
	private final static String ENROLL_EXPORT_LIST="enrollExportList";
	
	private final static String ENROLL_ADD="enrollAdd";
	private final static String ENROLL_EDIT="enrollEdit";
	private final static String ENROLL_APPROVE="enrollApprove";
	private final static String ENROLL_QUERY="enrollQuery";
	private final static String ENROLL_EXPORT="enrollExport";
	
	
	private final static Integer ENROLL_APPROVE_WAIT=1;
	private final static Integer ENROLL_APPROVE_PASS=2;
	private final static Integer ENROLL_APPROVE_REFUSE=3;
	
	EnrollService enrollService = null;
	EnrollScoreService enrollScoreService = null;
	EnrollStudentEntity enrollStudent = new EnrollStudentEntity();
	List<EnrollMajorScoreEntity> majorScoreList = new ArrayList<EnrollMajorScoreEntity>();
		
	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception 
	{
		this.getEnrollStudent().setEnrollStatus(ENROLL_APPROVE_PASS);//only for approve pass
		PaginationSupport pageResult = enrollScoreService.loadEnrollList(this.getEnrollStudent(), super.getPageSize().intValue(), super.getStartIndex().intValue());
		this.getRequest().put(PAGER_RESULT_KEY, pageResult);
		this.setEnrollStudent(enrollStudent);
		return ENROLL_LIST;
	}
	
	public String executeEdit() throws Exception 
	{
		this.getEnrollStudent().setEnrollStatus(ENROLL_APPROVE_PASS);//only for approve pass
		this.getEnrollStudent().setScoreStatus(ENROLL_APPROVE_REFUSE);//only for score approve refuse
		PaginationSupport pageResult = enrollScoreService.loadEnrolScoreEdit(this.getEnrollStudent(), super.getPageSize().intValue(), super.getStartIndex().intValue());
		this.getRequest().put(PAGER_RESULT_KEY, pageResult);
		this.setEnrollStudent(enrollStudent);
		return ENROLL_EDIT_LIST;
	}
	
	public String executeApprove() throws Exception 
	{
		this.getEnrollStudent().setEnrollStatus(ENROLL_APPROVE_PASS);//only for approve pass
		this.getEnrollStudent().setScoreStatus(ENROLL_APPROVE_WAIT);//only for score approve wait
		PaginationSupport pageResult = enrollScoreService.loadEnrolScoreEdit(this.getEnrollStudent(), super.getPageSize().intValue(), super.getStartIndex().intValue());
		this.getRequest().put(PAGER_RESULT_KEY, pageResult);
		this.setEnrollStudent(enrollStudent);
		return ENROLL_APPROVE_LIST;
	}
	
	public String executeExport() throws Exception 
	{
		this.getEnrollStudent().setEnrollStatus(ENROLL_APPROVE_PASS);//only for approve pass
		this.getEnrollStudent().setScoreStatus(ENROLL_APPROVE_PASS);//only for score approve pass
		PaginationSupport pageResult = enrollScoreService.loadEnrolScoreEdit(this.getEnrollStudent(), super.getPageSize().intValue(), super.getStartIndex().intValue());
		this.getRequest().put(PAGER_RESULT_KEY, pageResult);
		this.setEnrollStudent(enrollStudent);
		this.getReq().setAttribute("majorHash", enrollService.getMajorHash());
		return ENROLL_EXPORT_LIST;
	}
	
	public String toAddPage() throws Exception
	{
		String Ids=this.getReq().getParameter("Ids");
		if(!super.isNullOrEmpty(Ids)){
			EnrollStudentEntity enrollBean=new EnrollStudentEntity();
			enrollBean.setId(Long.valueOf(Ids));
			enrollBean = enrollService.getEnrollEntity(enrollBean);
			if(enrollBean.getScoreStatus()==null||"".equals(enrollBean.getScoreStatus())){
				this.setEnrollStudent(enrollBean);
				List<EnrollMajorItemEntity> list = enrollScoreService.loadMajorItemListByMajorCode(enrollBean.getTargetMajorCode());
				if(list!=null&&list.size()>0){
					List<EnrollMajorScoreEntity> majorScoreList = new ArrayList<EnrollMajorScoreEntity>();
					for(EnrollMajorItemEntity entity:list){
						EnrollMajorScoreEntity scoreEntity = new EnrollMajorScoreEntity();
						scoreEntity.setMajorItemName(entity.getName());
						majorScoreList.add(scoreEntity);
					}
					this.setMajorScoreList(majorScoreList);	
				}
			}else{
				this.setMsg("该学生成绩已经录入，点击确认返回录入列表");
				return execute();
			}
		}
		return ENROLL_ADD;
	}
	
	public String toEditPage() throws Exception
	{
		String Ids=this.getReq().getParameter("Ids");
		if(!super.isNullOrEmpty(Ids)){
			EnrollStudentEntity enrollBean=new EnrollStudentEntity();
			enrollBean.setId(Long.valueOf(Ids));
			enrollBean = enrollService.getEnrollEntity(enrollBean);
			this.setEnrollStudent(enrollBean);
			List<EnrollMajorScoreEntity> list = enrollScoreService.loadMajorScoreListByEnrollId(Ids);
			this.setMajorScoreList(list);	
		}
		return ENROLL_EDIT;
	}
	
	public String toApprovePage() throws Exception
	{
		String Ids=this.getReq().getParameter("Ids");
		if(!super.isNullOrEmpty(Ids)){
			EnrollStudentEntity enrollBean=new EnrollStudentEntity();
			enrollBean.setId(Long.valueOf(Ids));
			enrollBean = enrollService.getEnrollEntity(enrollBean);
			this.setEnrollStudent(enrollBean);
			List<EnrollMajorScoreEntity> list = enrollScoreService.loadMajorScoreListByEnrollId(Ids);
			this.setMajorScoreList(list);
			//分数
			if(list!=null&&list.size()>0){
				BigDecimal totalScore = null;
				for(EnrollMajorScoreEntity entity:list){
					if(totalScore==null){
						totalScore = new BigDecimal(entity.getMajorItemScore());
					}else{
						totalScore = totalScore.add(new BigDecimal(entity.getMajorItemScore()));
					}
				}
				this.getReq().setAttribute("totalScore", totalScore);
			}
		}
		return ENROLL_APPROVE;
	}
	
	public String saveEnrollScore() throws Exception 
	{
		if(this.getMajorScoreList()!=null&&this.getMajorScoreList().size()>0){
			for(EnrollMajorScoreEntity entity:this.getMajorScoreList()){
				entity.setEnrollId(this.getEnrollStudent().getId());
				enrollScoreService.saveEnrollScore(entity);
			}
			enrollScoreService.updateEnrollScoreStatus(ENROLL_APPROVE_WAIT, this.getEnrollStudent());
			this.setMsg("学生成绩录入成功，确定返回录入列表");
		}
		return execute();
	}
	
	public String saveEnrollScoreEdit() throws Exception 
	{
		if(this.getMajorScoreList()!=null&&this.getMajorScoreList().size()>0){
			for(EnrollMajorScoreEntity entity:this.getMajorScoreList()){
				entity.setEnrollId(this.getEnrollStudent().getId());
				enrollScoreService.saveEnrollScore(entity);
			}
			enrollScoreService.updateEnrollScoreStatus(ENROLL_APPROVE_WAIT, this.getEnrollStudent());
			this.setMsg("学生成绩修改成功，确定返回修改列表");
		}
		return executeEdit();
	}
	public String saveEnrollScoreApprove() throws Exception 
	{
		String flag = this.getReq().getParameter("flag");
		if(!super.isNullOrEmpty(flag)){
			enrollScoreService.updateEnrollScoreStatus(Integer.parseInt(flag), this.getEnrollStudent());
		}
		this.setMsg("学生成绩审批成功，确定返回审批列表");
		return executeApprove();
	}
	
	public String goBack() throws Exception 
	{
		this.setEnrollStudent(new EnrollStudentEntity());
		return execute();
	}
	
	public String goBackEdit() throws Exception 
	{
		this.setEnrollStudent(new EnrollStudentEntity());
		return executeEdit();
	}
	public String goBackApprove() throws Exception 
	{
		this.setEnrollStudent(new EnrollStudentEntity());
		return executeApprove();
	}

	public String exportEnroll() throws Exception{
		try{
			this.getResp().setContentType("application/vnd.ms-excel;charset=UTF-8");
			this.getResp().addHeader("Content-Disposition", "attachment;filename=StudentEnrollInfo.xls");
			OutputStream os = this.getResp().getOutputStream();
			//Only filter the approved passed.
			this.getEnrollStudent().setEnrollStatus(ENROLL_APPROVE_PASS);
			this.getEnrollStudent().setScoreStatus(ENROLL_APPROVE_PASS);
			List<EnrollStudentEntity> enrollList = enrollScoreService.loadEnrollListExport(this.getEnrollStudent());
			if(enrollList.size() >0 ){
				WorkbookSettings workbookSettings = new WorkbookSettings();
				workbookSettings.setEncoding("UTF-8");
				WritableWorkbook workbook = Workbook.createWorkbook(os);
				WritableSheet sheet = workbook.createSheet("报名信息", 0);
				//title
				String titleList[] ={"学生姓名","学号","出生日期","性别","民族","政治面貌","联系电话","家庭住址","所在院校","所在院校代码","所在专业","高考报名号","报考院校","报考院校代码","报考专业","报考专业代码","在校期间受过何种奖励","有何特长","联系地址","邮编"};
					int h = 0;
					for (int i = 0; i<titleList.length; i++){
						sheet.addCell(formatCell(h,0,titleList[i],true));
						h++;
					}
					//score
					Iterator<EnrollMajorScoreEntity> it = enrollList.get(0).getMajorScore().iterator();
					while(it.hasNext()){
						EnrollMajorScoreEntity entity = it.next();
						sheet.addCell(formatCell(h,0,entity.getMajorItemName(),true));
						h++;
					}
					sheet.addCell(formatCell(h,0,"总分",true));
				//data
				if(enrollList!=null && enrollList.size()>0){
					for (int i=0; i< enrollList.size(); i++){
						int k =0;
						sheet.addCell(formatCell(k,i+1,enrollList.get(i).getName()==null?"未知":enrollList.get(i).getName(),false));
						k++;
						sheet.addCell(formatCell(k,i+1,enrollList.get(i).getNo(),false));
						k++;
						sheet.addCell(formatCell(k,i+1,formatBirthdayDate(enrollList.get(i).getBrithDate()),false));
						k++;
						sheet.addCell(formatCell(k,i+1,enrollList.get(i).getSex()==1?"男":"女",false));
						k++;
						sheet.addCell(formatCell(k,i+1,enrollList.get(i).getNation(),false));
						k++;
						sheet.addCell(formatCell(k,i+1,enrollList.get(i).getPoliticalType(),false));
						k++;
						sheet.addCell(formatCell(k,i+1,enrollList.get(i).getPhone(),false));
						k++;
						sheet.addCell(formatCell(k,i+1,enrollList.get(i).getHomeAddress(),false));
						k++;
						sheet.addCell(formatCell(k,i+1,enrollList.get(i).getSchoolName(),false));
						k++;
						sheet.addCell(formatCell(k,i+1,enrollList.get(i).getSchoolCode(),false));
						k++;
						sheet.addCell(formatCell(k,i+1,enrollList.get(i).getMajor(),false));
						k++;
						sheet.addCell(formatCell(k,i+1,enrollList.get(i).getGaokaoCode(),false));
						k++;
						sheet.addCell(formatCell(k,i+1,enrollList.get(i).getTargetSchoolName(),false));
						k++;
						sheet.addCell(formatCell(k,i+1,enrollList.get(i).getTargetSchoolCode(),false));
						k++;
						sheet.addCell(formatCell(k,i+1,enrollList.get(i).getTargetMajor(),false));
						k++;
						sheet.addCell(formatCell(k,i+1,enrollList.get(i).getTargetMajorCode(),false));
						k++;
						sheet.addCell(formatCell(k,i+1,enrollList.get(i).getAwardStuff(),false));
						k++;
						sheet.addCell(formatCell(k,i+1,enrollList.get(i).getSpeciality(),false));
						k++;
						sheet.addCell(formatCell(k,i+1,enrollList.get(i).getContactAddress(),false));
						k++;
						sheet.addCell(formatCell(k,i+1,enrollList.get(i).getPostcode(),false));
						k++;
						Iterator<EnrollMajorScoreEntity> its = enrollList.get(i).getMajorScore().iterator();
						BigDecimal totalScore = null;
						while(its.hasNext()){
							EnrollMajorScoreEntity entity = its.next();
							sheet.addCell(formatCell(k,i+1,entity.getMajorItemScore(),true));
							k++;
							if(totalScore==null){
								totalScore = new BigDecimal(entity.getMajorItemScore());
							}else{
								totalScore = totalScore.add(new BigDecimal(entity.getMajorItemScore()));
							}
							
						}
						sheet.addCell(formatCell(k,i+1,String.valueOf(totalScore.doubleValue()),true));
					}
				}
				workbook.write();
				workbook.close();
				os.close();
	
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return ENROLL_EXPORT;
	}
	private String formatBirthdayDate(String date){
		StringBuffer returnString = null;
		if(!super.isNullOrEmpty(date)){
			returnString = new StringBuffer();
			String year = date.substring(0,4);
			String month = date.substring(5,7);
			String day = date.substring(8,10);
			returnString.append(year);
			returnString.append(month);
			returnString.append(day);
		}
		return returnString.toString();
	}
	private Label formatCell(int column,int row,String value,boolean isTitle) throws Exception{
		WritableCellFormat cellFormat=new WritableCellFormat();
		Label label = new Label(column, row, value); //put a label in cell A3, Label(column,row)
		if(isTitle){
	        cellFormat.setAlignment(jxl.format.Alignment.LEFT);
	        cellFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	        cellFormat.setWrap(false);
	        cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
		}else{
	        cellFormat.setAlignment(jxl.format.Alignment.LEFT);
	        cellFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	        cellFormat.setWrap(true);
		}
		label.setCellFormat(cellFormat);
		return label;
	}
	
	
	public String queryScore() throws Exception
	{
		SysUser user = (SysUser)ServletActionContext.getRequest().getSession().getAttribute(FrameConstant.SESSION_ACEGI_USER_KEY);
		if(user!=null){
			EnrollStudentEntity enrollBean=new EnrollStudentEntity();
			enrollBean.setUserId(user.getUserid());
			enrollBean = enrollService.getEnrollEntity(enrollBean);
			this.setEnrollStudent(enrollBean);
			List<EnrollMajorScoreEntity> list = enrollScoreService.loadMajorScoreListByEnrollId(enrollBean.getId().toString());
			this.setMajorScoreList(list);	
		}
		return ENROLL_QUERY;
	}
/*****************set/get method****************************************/

	public EnrollService getEnrollService() {
		return enrollService;
	}

	public void setEnrollService(EnrollService enrollService) {
		this.enrollService = enrollService;
	}

	public EnrollStudentEntity getEnrollStudent() {
		return enrollStudent;
	}

	public void setEnrollStudent(EnrollStudentEntity enrollStudent) {
		this.enrollStudent = enrollStudent;
	}

	public EnrollScoreService getEnrollScoreService() {
		return enrollScoreService;
	}

	public void setEnrollScoreService(EnrollScoreService enrollScoreService) {
		this.enrollScoreService = enrollScoreService;
	}

	public List<EnrollMajorScoreEntity> getMajorScoreList() {
		return majorScoreList;
	}

	public void setMajorScoreList(List<EnrollMajorScoreEntity> majorScoreList) {
		this.majorScoreList = majorScoreList;
	}
	
}
