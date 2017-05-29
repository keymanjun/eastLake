
package com.application.enroll.action;


import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.application.enroll.service.EnrollService;
import com.application.entity.po.EnrollStudentEntity;
import com.framework.base.BaseAction;
import com.framework.components.pager.PaginationSupport;
/**
 * 
 * @author wangjunw@cn.ibm.com
 *
 */
public class EnrollApproveAction extends BaseAction
{	
	private static final long serialVersionUID = 1L;
	private final static String ENROLL_LIST="enrollList";
	private final static String ENROLL_ADD="enrollAdd";
	private final static String ENROLL_EDIT="enrollEdit";
	private final static String ENROLL_TO_EXPORT="enrollToExport";
	private final static String ENROLL_TO_LABEL_EXPORT="enrollToLabelExport";
	private final static String ENROLL_EXPORT="enrollExport";
	private final static String ENROLL_EXPORT_LABEL="enrollExportLabel";
	private final static String ENROLL_LIST_QUERY="enrollListQuery";
	
	// The approval status is : 1 待审批； 2审批通过； 3 审批拒绝
	
	EnrollService enrollService = null;
	EnrollStudentEntity enrollStudent = new EnrollStudentEntity();
	
	
		
	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception 
	{
		this.getEnrollStudent().setEnrollStatus(1);
		PaginationSupport pageResult = enrollService.loadEnrollList(this.getEnrollStudent(), super.getPageSize().intValue(), super.getStartIndex().intValue());
		this.getRequest().put(PAGER_RESULT_KEY, pageResult);
		this.setEnrollStudent(enrollStudent);
		return ENROLL_LIST;
	}
	
	public String toEditPage() throws Exception
	{
		String Ids=this.getReq().getParameter("Ids");
		if(super.isNullOrEmpty(Ids)){
			return ENROLL_ADD;
		}
		EnrollStudentEntity enrollBean=new EnrollStudentEntity();
		enrollBean.setId(Long.valueOf(Ids));
		this.setEnrollStudent(enrollService.getEnrollEntity(enrollBean));
		return ENROLL_EDIT;
	}
	
	public String updateEnrollApprove() throws Exception 
	{
		String flag = this.getReq().getParameter("flag");
		if(flag!=null&&!"".equals(flag)){
			this.getEnrollStudent().setEnrollStatus(Integer.parseInt(flag));
			if(this.getEnrollStudent()!=null){
				enrollService.updateEnrollApprove(this.getEnrollStudent());
				this.setEnrollStudent(new EnrollStudentEntity());
				this.Msg = "学生报名信息审批成功！";
			}
		}
		return execute();
	}
	
	public String updateEnrollBulkApprove() throws Exception 
	{
		String svalue = this.getReq().getParameter("Ids");
		String approveStatus = this.getReq().getParameter("approveStatus");
		if(svalue!=null&&!"".equals(svalue)&&approveStatus!=null&&!"".equals(approveStatus)){
			String[] valueArray = svalue.split(",");
			if(valueArray.length>0){
				for(int i=0;i<=valueArray.length-1;i++){
					EnrollStudentEntity enrollStudent = new EnrollStudentEntity();
					enrollStudent.setEnrollStatus(Integer.parseInt(approveStatus));
					enrollStudent.setId(Long.parseLong(valueArray[i]));
					//enrollStudent.setApproveComment("");
					if(approveStatus.equals("2")){//如果是审批通过，则读取专业代码
						enrollStudent = enrollService.getEnrollEntity(enrollStudent);
						enrollStudent.setEnrollStatus(Integer.parseInt(approveStatus));
					}
					enrollService.updateEnrollApprove(enrollStudent);
					this.setEnrollStudent(new EnrollStudentEntity());
				}
				this.Msg = "学生报名信息审批成功！";
			}
		}
		return execute();
	}
	public String deleteEnroll() throws Exception 
	{
		String Ids=this.getReq().getParameter("Ids");
		enrollService.deleteEnroll(Ids);
		this.setEnrollStudent(this.getEnrollStudent());
		return execute();
	}

	public String goBack() throws Exception 
	{
		this.setEnrollStudent(new EnrollStudentEntity());
		return execute();
	}
	public String toExportEnroll() throws Exception{
		//this.getEnrollStudent().setEnrollStatus(2);
		PaginationSupport pageResult = enrollService.loadEnrollList(this.getEnrollStudent(), super.getPageSize().intValue(), super.getStartIndex().intValue());
		this.getRequest().put(PAGER_RESULT_KEY, pageResult);
		this.setEnrollStudent(enrollStudent);
		this.getReq().setAttribute("majorHash", enrollService.getMajorHash());
		return ENROLL_TO_EXPORT;
	}
	
	/**
	 * Query the enroll information
	 */
	public String exportEnroll() throws Exception{
		try{
		this.getResp().setContentType("application/vnd.ms-excel;charset=UTF-8");
		this.getResp().addHeader("Content-Disposition", "attachment;filename=StudentEnrollInfo.xls");
		OutputStream os = this.getResp().getOutputStream();
		//Only filter the approved passed.
		//this.getEnrollStudent().setEnrollStatus(2);
		List<EnrollStudentEntity> enrollList = enrollService.loadEnrollList(this.getEnrollStudent());
		if(enrollList.size() >0 ){
			WorkbookSettings workbookSettings = new WorkbookSettings();
			workbookSettings.setEncoding("UTF-8");
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			WritableSheet sheet = workbook.createSheet("报名信息", 0);
			//title
			String titleList[] ={"学生姓名","学号","出生日期","性别","民族","政治面貌","联系电话","家庭住址","所在院校","所在院校代码","所在专业","高考报名号","报考院校","报考院校代码","报考专业","报考专业代码","在校期间受过何种奖励","有何特长","联系地址","邮编","审批状态"};
			if(titleList.length > 0){
				int h = 0;
				for (int i = 0; i<titleList.length; i++){
					sheet.addCell(formatCell(h,0,titleList[i],true));
					h++;
				}
			}
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
					sheet.addCell(formatCell(k,i+1,enrollList.get(i).getEnrollStatusName(),false));
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
	
	//学生考试标签导出
	public String toExportLabel() throws Exception{
		// only for approval passed
		this.getEnrollStudent().setEnrollStatus(2);
		PaginationSupport pageResult = enrollService.loadEnrollList(this.getEnrollStudent(), super.getPageSize().intValue(), super.getStartIndex().intValue());
		this.getRequest().put(PAGER_RESULT_KEY, pageResult);
		this.setEnrollStudent(enrollStudent);
		this.getReq().setAttribute("majorHash", enrollService.getMajorHash());
		return ENROLL_TO_LABEL_EXPORT;
	}
	public String exportLabel() throws Exception{
		if(this.getEnrollStudent().getTargetMajorCode()!=null){
			try{
				this.getResp().setContentType("application/vnd.ms-excel;charset=UTF-8");
				this.getResp().addHeader("Content-Disposition", "attachment;filename=enrollLabelInfo.xls");
				OutputStream os = this.getResp().getOutputStream();
				List<EnrollStudentEntity> enrollList = enrollService.loadEnrollListByTargetMajorCode(this.getEnrollStudent());
				if(enrollList.size() >0 ){
					WorkbookSettings workbookSettings = new WorkbookSettings();
					workbookSettings.setEncoding("UTF-8");
					WritableWorkbook workbook = Workbook.createWorkbook(os);
					WritableSheet sheet = workbook.createSheet("学生考试标签", 0);
					
					sheet.setColumnView(0, 12); // 设置列的宽度  
					sheet.setColumnView(1, 23); // 设置列的宽度  
					sheet.setColumnView(2, 12); // 设置列的宽度  
					sheet.setColumnView(3, 21); // 设置列的宽度  
					 
					WritableFont wf_title = new WritableFont(WritableFont.ARIAL, 13,  
		                    WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,  
		                    jxl.format.Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色 
					 WritableCellFormat wcf_title = new WritableCellFormat(wf_title);
					 wcf_title.setBorder(Border.ALL, BorderLineStyle.THIN);
					 wcf_title.setAlignment(jxl.format.Alignment.CENTRE);
					 wcf_title.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
					 
					 WritableFont wf_content = new WritableFont(WritableFont.ARIAL, 10,  
			                    WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,  
			                    jxl.format.Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色 
						 WritableCellFormat wcf_content = new WritableCellFormat(wf_content);
						 wcf_content.setBorder(Border.ALL, BorderLineStyle.THIN);
						 wcf_content.setAlignment(jxl.format.Alignment.CENTRE);
						 wcf_content.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
					 int rowNo = 0;
					for(EnrollStudentEntity entity:enrollList){
						sheet.setRowView(rowNo, 910);
						sheet.setRowView(rowNo+1, 910);
						Label nameTitle=new Label(0,rowNo,"姓名",wcf_title);
						sheet.addCell(nameTitle);
						Label name=new Label(1,rowNo,entity.getName(),wcf_content);
						sheet.addCell(name);
						Label majorTitle=new Label(2,rowNo,"报考专业",wcf_title);
						sheet.addCell(majorTitle);
						Label major=new Label(3,rowNo,entity.getTargetMajor(),wcf_content);
						sheet.addCell(major);
						
						Label identifyTitle=new Label(0,rowNo+1,"身份证号",wcf_title);
						sheet.addCell(identifyTitle);
						Label identify=new Label(1,rowNo+1,entity.getIdentify(),wcf_content);
						sheet.addCell(identify);
						Label examNoTitle=new Label(2,rowNo+1,"考试号",wcf_title);
						sheet.addCell(examNoTitle);
						Label examNo=new Label(3,rowNo+1,entity.getExamCode(),wcf_content);
						sheet.addCell(examNo);
						
						 byte[] imageByte = this.readImageintoByte(entity.getPicPath());
						 WritableImage wrimage=new WritableImage(4,rowNo,2,2,imageByte);
						 sheet.addImage(wrimage);
						 
						 sheet.mergeCells(4, rowNo, 5, rowNo+1);//照片
						 rowNo = rowNo+2;
					}
					workbook.write();
					workbook.close();
					os.close();

				}
				}catch(Exception e){
					e.printStackTrace();
				}
		}
		return ENROLL_EXPORT_LABEL;
	}
	
	public String loadEnrollList() throws Exception 
	{
		PaginationSupport pageResult = enrollService.loadEnrollList(this.getEnrollStudent(), super.getPageSize().intValue(), super.getStartIndex().intValue());
		this.getRequest().put(PAGER_RESULT_KEY, pageResult);
		this.setEnrollStudent(enrollStudent);
		
		HashMap<Integer,String> enrollStatush = new HashMap<Integer,String>();
		enrollStatush.put(1,"待审批");
		enrollStatush.put(2,"审批通过");
		enrollStatush.put(3,"审批拒绝");
		this.getReq().setAttribute("enrollStatush",enrollStatush);
		return ENROLL_LIST_QUERY;
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

}
