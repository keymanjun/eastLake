
package com.application.enroll.action;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.application.data2pdf.EnrollExport2PDFUtil;
import com.application.enroll.service.EnrollService;
import com.application.entity.po.EnrollDicEntity;
import com.application.entity.po.EnrollMajorEntity;
import com.application.entity.po.EnrollMajorItemEntity;
import com.application.entity.po.EnrollStudentEntity;
import com.framework.FrameConstant;
import com.framework.base.BaseAction;
import com.framework.components.pager.PaginationSupport;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.system.entity.po.SysUser;
/**
 * 
 * @author wangjunw@cn.ibm.com
 *
 */
public class EnrollAction extends BaseAction
{	
	private static final long serialVersionUID = 1L;
	private final static String ENROLL_LIST="enrollList";
	private final static String ENROLL_ADD="enrollAdd";
	private final static String ENROLL_EDIT="enrollEdit";
	private final static String ENROLL_EXPORT="enrollExport";
	
	
	private final static Integer ENROLL_APPROVE_WAIT=1;
	private final static Integer ENROLL_APPROVE_PASS=2;
	private final static Integer ENROLL_APPROVE_REFUSE=3;
	
	private final static String picsPath = "studentPics";
	EnrollService enrollService = null;
	EnrollStudentEntity enrollStudent = new EnrollStudentEntity();
	
	private FileInputStream inputStream; 
	
	private File uploadImage = null;
		
	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception 
	{
		PaginationSupport pageResult = enrollService.loadEnrollStudent(this.getEnrollStudent(), super.getPageSize().intValue(), super.getStartIndex().intValue());
		this.getRequest().put(PAGER_RESULT_KEY, pageResult);
		this.setEnrollStudent(enrollStudent);
		return ENROLL_LIST;
	}
	
	public String toAddPage() throws Exception
	{
		PaginationSupport pageResult = enrollService.loadEnrollStudent(this.getEnrollStudent(), super.getPageSize().intValue(), super.getStartIndex().intValue());
		if(pageResult.getItems().size()>0){
			this.Msg = "您已经添加了报名申请，点击确认自动跳转到\'我的报名信息\'";
			return execute();
		}else{
			this.getReq().setAttribute("majorHash", enrollService.getMajorHash());
			return ENROLL_ADD;
		}
	}
	
	public String toEditPage() throws Exception
	{
		String Ids=this.getReq().getParameter("Ids");
		if(super.isNullOrEmpty(Ids)){
			return ENROLL_ADD;
		}
		this.getReq().setAttribute("majorHash", enrollService.getMajorHash());
		EnrollStudentEntity enrollBean=new EnrollStudentEntity();
		enrollBean.setId(Long.valueOf(Ids));
		this.setEnrollStudent(enrollService.getEnrollEntity(enrollBean));
		return ENROLL_EDIT;
	}
	
	
	private Boolean checkEnrollRecord(Long userId){
		Boolean isEnrollRecordExist = false;
		EnrollStudentEntity enroll = new EnrollStudentEntity();
		enroll.setUserId(userId);
		if(enrollService.getEnrollEntity(enroll)!=null){
			isEnrollRecordExist = true;
		}
		return isEnrollRecordExist;
	}
	public String saveEnroll() throws Exception 
	{
		if(this.getEnrollStudent()!=null){
			//upload the image to the server, and save the url into database
			if(this.getUploadImage()!=null){
				if(this.checkImageSize(this.getUploadImage())){
					String imagePath = this.saveImage(this.getUploadImage(), this.getEnrollStudent().getIdentify());
					if(imagePath!=null&&!"".equals(imagePath)){
						this.getEnrollStudent().setPicPath(imagePath);
					}
				}else{
					this.setEnrollStudent(this.getEnrollStudent());
					this.getReq().setAttribute("majorHash", enrollService.getMajorHash());
					this.Msg = "上传的图片尺寸请不要大于100KB";
					return ENROLL_ADD;
				}
			}
			
			List<EnrollStudentEntity> list = enrollService.loadEnrollListByIdentify(this.getEnrollStudent());
			if(list!=null&&list.size()>0){
				this.getReq().setAttribute("majorHash", enrollService.getMajorHash());
				this.Msg = "身份证号已经注册，请确认!";
				this.setEnrollStudent(this.getEnrollStudent());
				return ENROLL_ADD;
			}
			this.getEnrollStudent().setEnrollStatus(ENROLL_APPROVE_WAIT);
			SysUser user = (SysUser)ServletActionContext.getRequest().getSession().getAttribute(FrameConstant.SESSION_ACEGI_USER_KEY);
			this.getEnrollStudent().setUserId(user.getUserid());
			//Only record the fist commit date
			if(super.isNullOrEmpty(this.getEnrollStudent().getCommitDate())){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				this.getEnrollStudent().setCommitDate(sdf.format(new Date()));
			}
			if(!checkEnrollRecord(this.getEnrollStudent().getUserId())){
				enrollService.saveEnroll(this.getEnrollStudent());
				this.setEnrollStudent(new EnrollStudentEntity());
				this.Msg = "学生报名信息保存成功！请耐心等待老师的审批";
			}else{
				this.Msg = "学生报名信息已经存在，请勿重复提交！";
				this.setEnrollStudent(this.getEnrollStudent());
				this.getReq().setAttribute("majorHash", enrollService.getMajorHash());
				return ENROLL_ADD;
			}
			
		}
		return execute();
	}
	
	private Boolean checkImageSize(File image) throws Exception{
		Boolean sizeOK = false;
		FileInputStream fis = new FileInputStream(image);
		double size = fis.available();
		if(size < 102400){
			sizeOK=true;
		}
		return sizeOK;
	}
	
	private String saveImage(File uploadImage,String identified) throws IOException{
		String returnImagePath = null;
		if(uploadImage!=null){
			String targetDirectory = this.getRealPath("/")+picsPath;
			String targetImageName = identified+".jpg";
			returnImagePath = picsPath+"/"+targetImageName;
			File targetFile = new File(targetDirectory,targetImageName);
			FileUtils.copyFile(uploadImage, targetFile);
		}
		return returnImagePath;
	}
	
	
	public String deleteEnroll() throws Exception 
	{
		String Ids=this.getReq().getParameter("Ids");
		enrollService.deleteEnroll(Ids);
		this.setEnrollStudent(this.getEnrollStudent());
		return execute();
	}

	/**
	 * Query the enroll information
	 */
	public String exportEnroll() throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		Date beforeDate = null;
		Date afterDate = null;
		List<EnrollDicEntity> dics = enrollService.getEnrollDicByType("1");
		if(dics!=null&&dics.size()>0){
			if(dics.get(0).getName().equals("printEnrollStartDate")){
				beforeDate = sdf.parse(dics.get(0).getValue());
				afterDate = sdf.parse(dics.get(1).getValue());
			}else{
				beforeDate = sdf.parse(dics.get(1).getValue());
				afterDate = sdf.parse(dics.get(0).getValue());
			}
		}
		Date currentDate =new Date();
		if(currentDate.after(beforeDate)&& currentDate.before(afterDate)){
			try{
				EnrollStudentEntity enroll = new EnrollStudentEntity();
				SysUser user = (SysUser)ServletActionContext.getRequest().getSession().getAttribute(FrameConstant.SESSION_ACEGI_USER_KEY);
				enroll.setUserId(user.getUserid());
				enroll = enrollService.getEnrollEntity(enroll);
				EnrollMajorEntity major = enrollService.getEnrollMajorByCode(enroll.getTargetMajorCode());
				List<EnrollMajorItemEntity> majorItemList = enrollService.getEnrollMajorItemByOriginalNo(enroll.getTargetMajorCode());
				if(enroll!=null&&!"".equals(enroll)&&major!=null&&!"".equals(major)){
					this.getResp().setContentType("application/pdf;charset=UTF-8");
					this.getResp().addHeader("Content-Disposition", "attachment;filename="+enroll.getIdentify()+".pdf");
					OutputStream os = this.getResp().getOutputStream();
					PdfWriter pdfWriter = new PdfWriter(os);
					
					this.setInputStream(new FileInputStream(new File(this.getRealPath("/")+"/enrollExport2PDF/"+enroll.getIdentify()+".pdf")));
					EnrollExport2PDFUtil enrollExport2PDFUtil = new EnrollExport2PDFUtil();
					enrollExport2PDFUtil.createEnrollPDf(pdfWriter,enroll,major,majorItemList);
					pdfWriter.close();
					
					
//					WorkbookSettings workbookSettings = new WorkbookSettings();
//					workbookSettings.setEncoding("UTF-8");
//					WritableWorkbook workbook = Workbook.createWorkbook(os);
//					WritableSheet sheet = workbook.createSheet("准考证", 0);
//					
//					WritableFont wf_title = new WritableFont(WritableFont.ARIAL, 20,  
//		                    WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,  
//		                    jxl.format.Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色 
//					 WritableCellFormat wcf_title = new WritableCellFormat(wf_title);
//					 wcf_title.setBorder(Border.ALL, BorderLineStyle.THIN);
//					 wcf_title.setAlignment(jxl.format.Alignment.CENTRE);
//					 wcf_title.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
//					 
//					 WritableFont wf_content = new WritableFont(WritableFont.ARIAL, 12,  
//		                    WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,  
//		                    jxl.format.Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色 
//					 WritableCellFormat wcf_content = new WritableCellFormat(wf_content);
//					 wcf_content.setBorder(Border.ALL, BorderLineStyle.THIN);
//					 wcf_content.setAlignment(jxl.format.Alignment.CENTRE);
//					 wcf_content.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
//	
//					 WritableFont wf_note_content = new WritableFont(WritableFont.ARIAL, 12,  
//		                    WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,  
//		                    jxl.format.Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色 
//					 WritableCellFormat wcf_note_content = new WritableCellFormat(wf_note_content);
//					 wcf_note_content.setBorder(Border.ALL, BorderLineStyle.THIN);
//					 wcf_note_content.setAlignment(jxl.format.Alignment.LEFT);
//					 wcf_note_content.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
//					 wcf_note_content.setWrap(true);//自动换行
//					 
//					 
//					 WritableCellFormat wcf_note_content_attention = new WritableCellFormat(wf_note_content);
//					 wcf_note_content_attention.setBorder(Border.LEFT, BorderLineStyle.THIN);
//					 wcf_note_content_attention.setBorder(Border.RIGHT, BorderLineStyle.THIN);
//					 wcf_note_content_attention.setAlignment(jxl.format.Alignment.LEFT);
//					 wcf_note_content_attention.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
//					 wcf_note_content_attention.setWrap(true);//自动换行
//					 
//					 WritableCellFormat wcf_note_content_attention_bottom = new WritableCellFormat(wf_note_content);
//					 wcf_note_content_attention_bottom.setBorder(Border.LEFT, BorderLineStyle.THIN);
//					 wcf_note_content_attention_bottom.setBorder(Border.RIGHT, BorderLineStyle.THIN);
//					 wcf_note_content_attention_bottom.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
//					 wcf_note_content_attention_bottom.setAlignment(jxl.format.Alignment.LEFT);
//					 wcf_note_content_attention_bottom.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
//					 wcf_note_content_attention_bottom.setWrap(true);//自动换行
//					 
//					 
//					 
//					 sheet.setColumnView(0, 12); // 设置列的宽度  
//					 sheet.setColumnView(1, 20); // 设置列的宽度  
//					 sheet.setColumnView(2, 18); // 设置列的宽度  
//					 sheet.setColumnView(3, 18); // 设置列的宽度  
//					 sheet.setColumnView(4, 10); // 设置列的宽度  
//					 sheet.setColumnView(5, 10); // 设置列的宽度  
//					 
//					 sheet.setRowView(0, 700);
//					 sheet.setRowView(1, 900);
//					 sheet.setRowView(2, 900);
//					 sheet.setRowView(3, 900);
//					 sheet.setRowView(4, 900);
//					 sheet.setRowView(5, 900);
//					 sheet.setRowView(6, 900);
//					 sheet.setRowView(7, 900);
//					 sheet.setRowView(8, 900);
//					 sheet.setRowView(9, 900);
//					 sheet.setRowView(10, 900);
//					 sheet.setRowView(11, 500);
//					 sheet.setRowView(12, 500);
//					 sheet.setRowView(13, 900);
//					 sheet.setRowView(14, 500);
//					 sheet.setRowView(15, 500);
//					 
//					 
//					 Label title=new Label(0,0,"武汉东湖学院"+Calendar.getInstance().get(Calendar.YEAR)+"年\"专升本\"考试",wcf_content);
//					 Label titleSub=new Label(0,1,"准      考      证",wcf_title);
//					 
//					 Label examNoTitle=new Label(0,2,"准考证号",wcf_content);
//					 Label examNo=new Label(1,2,enroll.getExamCode(),wcf_content);
//					 Label nameTitle=new Label(2,2,"姓名",wcf_content);
//					 Label name=new Label(3,2,enroll.getName(),wcf_content);
//					 Label sexTitle=new Label(4,2,"性别",wcf_content);
//					 Label sex=new Label(5,2,enroll.getSexName(),wcf_content);
//					 
//					 Label targetMajorTitle=new Label(0,3,"报考专业",wcf_content);
//					 Label targetMajor=new Label(1,3,enroll.getTargetMajor(),wcf_content);
//					 Label examDateTitle=new Label(2,3,"考试日期",wcf_content);
//					 Label examDate=new Label(3,3,major.getExamDate(),wcf_content);
//					 
//					 byte[] imageByte = this.readImageintoByte(enroll.getPicPath());
//					 WritableImage wrimage=new WritableImage(4,3,2,3,imageByte);
//					 
//					 Label examCourseTitle=new Label(0,4,"考试科目",wcf_content);
//					 Label examCourse1=new Label(1,4,majorItemList.get(0).getName(),wcf_content);
//					 Label examCourse2=new Label(2,4,majorItemList.get(1).getName(),wcf_content);
//					 Label examCourse3=new Label(3,4,majorItemList.get(2).getName(),wcf_content);
//					 
//					 Label examTimeTitle=new Label(0,5,"考试时间",wcf_content);
//					 Label examTime1=new Label(1,5,majorItemList.get(0).getExamTime(),wcf_content);
//					 Label examTime2=new Label(2,5,majorItemList.get(1).getExamTime(),wcf_content);
//					 Label examTime3=new Label(3,5,majorItemList.get(2).getExamTime(),wcf_content);
//					 
//					 Label examAddressTitle=new Label(0,6,"考试地点",wcf_content);
//					 Label examAddress=new Label(1,6,enroll.getExamRoom(),wcf_content);
//					 
//					 
//					 Label noteTitle=new Label(0,7,"考生注意事项",wcf_title);
//					 Label noteContent1=new Label(0,8,"一 、考生凭准考证、身份证入室对号入座，准考证放在课桌左上角，以便查对，考试响铃开始答题。",wcf_note_content_attention);
//					 Label noteContent2=new Label(0,9,"二、考生除自带必要的文具用品（钢笔、签字笔、圆珠笔、橡皮擦）外，其他物品一律不得带入考场，艺术类考生自带绘画工具。",wcf_note_content_attention);
//					 Label noteContent3=new Label(0,10,"三、各科考前15分钟打预备铃。开考15分钟后，不得进入考场；考试开始30分钟内，不得交卷出考场；交卷后不得在考场附近逗留、谈论。",wcf_note_content_attention);
//					 Label noteContent4=new Label(0,11,"四、答题应写在指定答题区域内，不得写在密封线以外或草稿纸上，否则一律无效。",wcf_note_content_attention);
//					 Label noteContent5=new Label(0,12,"五、考生除遇试卷分发错误或缺页、缺题和字迹模糊等问题提问外，其他不准提问。",wcf_note_content_attention);
//					 Label noteContent6=new Label(0,13,"六、考生必须严格遵守考试规则，不准吸烟、左顾右盼，凡是在考试中夹带、传接答案、交换答案、找人代考、抄袭他人答案、带走他人答案、携带规定以外物品入考场等，考卷作零分记。",wcf_note_content_attention);
//					 Label noteContent7=new Label(0,14,"七、考试结束响铃后，考生立即停止答卷。注意不得将草稿纸带走。",wcf_note_content_attention);
//					 Label noteContent8=new Label(0,15,"八、本证应妥善保存，如有遗失，不补发。",wcf_note_content_attention_bottom);
//					 
//					 
//					sheet.addCell(title);
//					sheet.addCell(titleSub);
//					sheet.addCell(examNoTitle);
//					sheet.addCell(examNo);
//					sheet.addCell(nameTitle);
//					sheet.addCell(name);
//					sheet.addCell(sexTitle);
//					sheet.addCell(sex);
//					sheet.addCell(targetMajorTitle);
//					sheet.addCell(targetMajor);
//					sheet.addCell(examDateTitle);
//					sheet.addCell(examDate);
//					sheet.addImage(wrimage);
//					sheet.addCell(examCourseTitle);
//					sheet.addCell(examCourse1);
//					sheet.addCell(examCourse2);
//					sheet.addCell(examCourse3);
//					sheet.addCell(examTimeTitle);
//					sheet.addCell(examTime1);
//					sheet.addCell(examTime2);
//					sheet.addCell(examTime3);
//					sheet.addCell(examAddressTitle);
//					sheet.addCell(examAddress);
//					
//					sheet.addCell(noteTitle);
//					sheet.addCell(noteContent1);
//					sheet.addCell(noteContent2);
//					sheet.addCell(noteContent3);
//					sheet.addCell(noteContent4);
//					sheet.addCell(noteContent5);
//					sheet.addCell(noteContent6);
//					sheet.addCell(noteContent7);
//					sheet.addCell(noteContent8);
//					
//					sheet.mergeCells(0, 0, 5, 0);
//					sheet.mergeCells(0, 1, 5, 1);
//					sheet.mergeCells(4, 3, 5, 5);//照片
//					sheet.mergeCells(1, 6, 5, 6);
//					
//					sheet.mergeCells(0, 7, 5, 7);
//					sheet.mergeCells(0, 8, 5, 8);
//					sheet.mergeCells(0, 9, 5, 9);
//					sheet.mergeCells(0, 10, 5, 10);
//					sheet.mergeCells(0, 11, 5, 11);
//					sheet.mergeCells(0, 12, 5, 12);
//					sheet.mergeCells(0, 13, 5, 13);
//					sheet.mergeCells(0, 14, 5, 14);
//					sheet.mergeCells(0, 15, 5, 15);
//					
//					workbook.write();
//					workbook.close();
					os.close();
		
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return SUCCESS;
		}else{
			this.Msg = "请在"+sdf2.format(beforeDate)+"至"+sdf2.format(afterDate)+"登陆系统打印准考证。";
			return execute();
		}
		
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

	public File getUploadImage() {
		return uploadImage;
	}

	public void setUploadImage(File uploadImage) {
		this.uploadImage = uploadImage;
	}

	public void setInputStream(FileInputStream inputStream) {
		this.inputStream = inputStream;
	}
	public InputStream getInputStream() { 

		return inputStream; 
	}  

}
