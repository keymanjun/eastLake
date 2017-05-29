package com.application.employee.basic.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.application.employee.basic.service.EmployeeInfoService;
import com.application.entity.po.DepartmentEntity;
import com.application.entity.po.EmployeeEntity;
import com.framework.base.BaseAction;
import com.framework.components.pager.PaginationSupport;

public class EmployeeInfoAction extends BaseAction {

	/** serialVersionUID*/
	private static final long serialVersionUID = -4931011901004752860L;
	
	private final static String EMPLOYEE_LIST = "employeeList";
	private final static String EMPLOYEE_ADD="employeeAdd";
	private final static String EMPLOYEE_EDIT = "employeeEdit";
	private final static String EMPLOYEE_INFO = "EMPLOYEEInfo";
	private final static String EMPLOYEE_LOAD = "employeeLoad";
	private final static String EMPLOYEE_TO_EXPORT = "employeeToExport";
	private final static String EMPLOYEE_EXPORT = "employeeExport";

	private EmployeeInfoService employeeInfoService = null;; 
	private File fileName = null;
	private EmployeeEntity employeeInfo = null;
	/**
	 * Query the employee information
	 */
	public String execute() throws Exception{
		PaginationSupport pageResult = employeeInfoService.loadEmployeeInfoList(this.getEmployeeInfo(), super.getPageSize().intValue(), super.getStartIndex().intValue());
		this.getRequest().put(PAGER_RESULT_KEY, pageResult);
		this.setEmployeeInfo(this.getEmployeeInfo());
		return EMPLOYEE_LIST;
	}
	
	/**
	 * 
	* forward to edit employee information page.
	* @return
	* @throws Exception
	 */
	public String toEditPage() throws Exception{
		String ids = this.getReq().getParameter("Ids");
		List<DepartmentEntity> departmentList = employeeInfoService.loadDepartmentList();
		this.getRequest().put("departmentList", departmentList);
		if(super.isNullOrEmpty(ids)){
			this.setEmployeeInfo(new EmployeeEntity());
			return EMPLOYEE_ADD;
		}
		EmployeeEntity employee = new EmployeeEntity();
		employee.setEiId(Long.parseLong(ids));
		this.setEmployeeInfo(employeeInfoService.getEmployeeEntity(employee));
		return EMPLOYEE_EDIT;
	}
		
	
	/**
	 * 
	* Save employee information
	* @return
	* @throws Exception
	 */
	public String saveEmployee() throws Exception {
		if(this.getEmployeeInfo()!=null){
			employeeInfoService.saveEmployee(this.getEmployeeInfo());
			this.setEmployeeInfo(new EmployeeEntity());
			this.Msg = "员工信息保存成功！";
		}
		return execute();
	}
	/**
	 * 
	* <p>方法名称: deleteEMPLOYEE|描述: 删除会员信息</p>
	* @return
	* @throws Exception
	 */
	public String deleteEmployee() throws Exception {
		String ids = this.getReq().getParameter("Ids");
		if(ids!=null && !"".equals(ids)){
			int count = employeeInfoService.deleteMember(ids);
			if (count > 0){
				super.Msg = "删除操作成功！";
			}
		}
		return execute();
	}
	/**
	 * to ExportEmployee Info
	 */
	public String toExportEmployeeInfo() throws Exception{
		this.setEmployeeInfo(this.getEmployeeInfo());
		return EMPLOYEE_TO_EXPORT;
	}
	/**
	 * Query the employee information
	 */
	public String exportEmployee() throws Exception{
		try{
		this.getResp().setContentType("application/vnd.ms-excel;charset=UTF-8");
		this.getResp().addHeader("Content-Disposition", "attachment;filename=employeeInfo.xls");
		OutputStream os = this.getResp().getOutputStream();
		List<EmployeeEntity> employeeList = employeeInfoService.loadEmployeeExport(this.getEmployeeInfo());
		String[] selectTitleArray = this.getReq().getParameterValues("titleList");
		List<String> selectTitle = Arrays.asList(selectTitleArray);
		//List selectTitleList = Arrays.asList(selectTitle);
		if(employeeList.size() >0 ){
			WorkbookSettings workbookSettings = new WorkbookSettings();
			workbookSettings.setEncoding("UTF-8");
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			WritableSheet sheet = workbook.createSheet("基本信息", 0);
			//title
			String titleList[] ={"部门名称","员工编号","姓名","性别","出生年月","籍贯","民族","政治面貌","第一学历","学位","毕业学校","专业","最高学历",
					"学位","毕业学校","专业","职称","职务","户口所在地","上岗时间","人员构成"};
			if(titleList.length > 0){
				int h = 0;
				for (int i = 0; i<titleList.length; i++){
					if(selectTitle.contains(String.valueOf(i))){
						sheet.addCell(formatCell(h,0,titleList[i],true));
						h++;
					}
				}
			}
			//data
			if(employeeList!=null && employeeList.size()>0){
				for (int i=1; i< employeeList.size(); i++){
					int k =0;
					if(selectTitle.contains(String.valueOf(0))){
						sheet.addCell(formatCell(k,i,employeeList.get(i).getDepartmentName()==null?"未知":employeeList.get(i).getDepartmentName(),false));
						k++;
					}if(selectTitle.contains(String.valueOf(1))){
						sheet.addCell(formatCell(k,i,employeeList.get(i).getCode(),false));
						k++;
					}if(selectTitle.contains(String.valueOf(2))){
						sheet.addCell(formatCell(k,i,employeeList.get(i).getUsername(),false));
						k++;
					}if(selectTitle.contains(String.valueOf(3))){
						sheet.addCell(formatCell(k,i,employeeList.get(i).getSex()==1?"男":"女",false));
						k++;
					}if(selectTitle.contains(String.valueOf(4))){
						sheet.addCell(formatCell(k,i,employeeList.get(i).getBirthday(),false));
						k++;
					}if(selectTitle.contains(String.valueOf(5))){
						sheet.addCell(formatCell(k,i,employeeList.get(i).getNatives(),false));
						k++;
					}if(selectTitle.contains(String.valueOf(6))){
						sheet.addCell(formatCell(k,i,employeeList.get(i).getNation(),false));
						k++;
					}if(selectTitle.contains(String.valueOf(7))){
						sheet.addCell(formatCell(k,i,employeeList.get(i).getPoliticsString(),false));
						k++;
					}if(selectTitle.contains(String.valueOf(8))){
						sheet.addCell(formatCell(k,i,employeeList.get(i).getEducationString(employeeList.get(i).getFirstEducation()),false));
						k++;
					}if(selectTitle.contains(String.valueOf(9))){
						sheet.addCell(formatCell(k,i,employeeList.get(i).getFirstDegree(),false));
						k++;
					}if(selectTitle.contains(String.valueOf(10))){
						sheet.addCell(formatCell(k,i,employeeList.get(i).getFirstSchool(),false));
						k++;
					}if(selectTitle.contains(String.valueOf(11))){
						sheet.addCell(formatCell(k,i,employeeList.get(i).getFirstMajor(),false));
						k++;
					}if(selectTitle.contains(String.valueOf(12))){
						sheet.addCell(formatCell(k,i,employeeList.get(i).getEducationString(employeeList.get(i).getEducation()),false));
						k++;
					}if(selectTitle.contains(String.valueOf(13))){
						sheet.addCell(formatCell(k,i,employeeList.get(i).getDegree(),false));
						k++;
					}if(selectTitle.contains(String.valueOf(14))){
						sheet.addCell(formatCell(k,i,employeeList.get(i).getSchool(),false));
						k++;
					}if(selectTitle.contains(String.valueOf(15))){
						sheet.addCell(formatCell(k,i,employeeList.get(i).getMajor(),false));
						k++;
					}if(selectTitle.contains(String.valueOf(16))){
						sheet.addCell(formatCell(k,i,employeeList.get(i).getJobTitleString(),false));
						k++;
					}if(selectTitle.contains(String.valueOf(17))){
						sheet.addCell(formatCell(k,i,employeeList.get(i).getPoliticsString(),false));
						k++;
					}if(selectTitle.contains(String.valueOf(18))){
						sheet.addCell(formatCell(k,i,employeeList.get(i).getRegisteredLocal(),false));
						k++;
					}if(selectTitle.contains(String.valueOf(19))){
						sheet.addCell(formatCell(k,i,employeeList.get(i).getOnboardTime(),false));
						k++;
					}if(selectTitle.contains(String.valueOf(20))){
						sheet.addCell(formatCell(k,i,employeeList.get(i).getEmployeeCompositionString(),false));
						k++;
					}
				}
			}
			workbook.write();
			workbook.close();
			os.close();

		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return EMPLOYEE_EXPORT;
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
	/**
	 * 
	* <p>方法名称: goBack|描述: 返回会员列表页面</p>
	* @return
	* @throws Exception
	 */
	public String goBack() throws Exception {
		this.setEmployeeInfo(new EmployeeEntity());
		return execute();
	}
	
	/**
	 * 
	* <p>方法名称: toLoadEmployeeInfo|描述: </p>
	* @return
	 */
	public String toLoadEmployeeInfo() throws Exception{
		List<DepartmentEntity> departmentList = employeeInfoService.loadDepartmentList();
		this.getRequest().put("departmentList", departmentList);
		return EMPLOYEE_LOAD;
	}
	/**
	 * 
	* <p>方法名称: toLoadEmployeeInfo|描述: </p>
	* @return
	 */
	public String loadEmployeeInfo(){
		Workbook workbook = null;
		InputStream is = null;
		try{
			StringBuffer msgsb = new StringBuffer();
			if(fileName!=null &&!"".equals(fileName)){
				WorkbookSettings workbookSettings = new WorkbookSettings();
				workbookSettings.setEncoding("UTF-8");
				is = new FileInputStream(fileName);
				workbook = Workbook.getWorkbook(is);
				Sheet sheet  = workbook.getSheet(0);
				int rows = sheet.getRows();
				int columns = sheet.getColumns();
				Cell cell = null;
				List<EmployeeEntity> employList = null;
				boolean isGood = true;
				if(rows > 1 && columns > 0){
					employList = new ArrayList<EmployeeEntity>();
					List<DepartmentEntity> departmentList = employeeInfoService.loadDepartmentList();
					HashMap<String,Long> departmentHash = this.listToHash(departmentList);
					//the first row is title, no need to load
					for (int i = 1; i <rows;i++){
						EmployeeEntity employeeEntity = new EmployeeEntity();
						employeeEntity.setStatus(1);
						for(int j = 0; j<= columns-1; j++){
							cell = sheet.getCell(j, i);
							String value = cell.getContents().trim();
								switch(j){
								//部门名称
								case 0:
									if(!isNullOrEmpty(value)){
										if (departmentHash.get(value)==null){
											isGood = false;
											msgsb.append(appendMsg(i,j,"部门名称",false));
										}else{
											employeeEntity.setDiId(departmentHash.get(value));
										}
									}else{
										isGood = false;
										msgsb.append(appendMsg(i,j,"部门名称",true));
									}
									break;
								//员工编号
								case 1:
									employeeEntity.setCode(value);
									break;
									//姓名
								case 2:
									if(!isNullOrEmpty(value)){
										employeeEntity.setUsername(value);
									}else{
										isGood = false;
										msgsb.append(appendMsg(i,j,"姓名",true));
									}
									break;
								//性别
								case 3:
									if(!isNullOrEmpty(value)){
										if(employeeEntity.getSexIdbyString(value)==-1){
											isGood = false;
											msgsb.append(appendMsg(i,j,"性别",false));
										}else{
											employeeEntity.setSex(employeeEntity.getSexIdbyString(value));
										}
									}else{
										isGood = false;
										msgsb.append(appendMsg(i,j,"性别",true));
									}
									break;
								//出生年月
								case 4:
									if(!isNullOrEmpty(value)){
										employeeEntity.setBirthday(value);
									}else{
										isGood = false;
										msgsb.append(appendMsg(i,j,"出生年月",true));
									}
									break;
								//籍贯
								case 5: 
									if(!isNullOrEmpty(value)){
										employeeEntity.setNatives(value);
									}else{
										isGood = false;
										msgsb.append(appendMsg(i,j,"籍贯",true));
									}
									break;
								//民族
								case 6: 
									if(!isNullOrEmpty(value)){
										employeeEntity.setNation(value);
									}else{
										isGood = false;
										msgsb.append(appendMsg(i,j,"民族",true));
									}
									break;
								//政治面貌
								case 7: 
									if(!isNullOrEmpty(value)){
										employeeEntity.setPolitics(value=="党员"?2:1);
									}else{
										isGood = false;
										msgsb.append(appendMsg(i,j,"党员",true));
									}
									break;
								//第一学历
								case 8: 
									if(!isNullOrEmpty(value)){
										employeeEntity.setFirstEducation(employeeEntity.getEducationIdbyString(value));
									}
									break;
								//第一学位
								case 9: 
									if(!isNullOrEmpty(value)){
										employeeEntity.setFirstDegree(value);
									}
									break;
								//第一毕业学校
								case 10: 
									if(!isNullOrEmpty(value)){
										employeeEntity.setFirstSchool(value);
									}
									break;
								//第一专业
								case 11: 
									if(!isNullOrEmpty(value)){
										employeeEntity.setFirstMajor(value);
									}
									break;
								//第二学历
								case 12: 
									if(!isNullOrEmpty(value)){
										if(employeeEntity.getEducationIdbyString(value)==-1){
											employeeEntity.setSecondEducation(employeeEntity.getEducationIdbyString(value));
										}else{
											isGood = false;
											msgsb.append(appendMsg(i,j,"第二学历",true));
										}
									}
									break;
								//第二学位
								case 13: 
									if(!isNullOrEmpty(value)){
										employeeEntity.setSecondDegree(value);
									}
									break;
								//第二毕业学校
								case 14: 
									if(!isNullOrEmpty(value)){
										employeeEntity.setSecondSchool(value);
									}
									break;
								//第二专业
								case 15: 
									if(!isNullOrEmpty(value)){
										employeeEntity.setSecondMajor(value);
									}
									break;
								//第三学历
								case 16: 
									if(!isNullOrEmpty(value)){
										if(employeeEntity.getEducationIdbyString(value)==-1){
											employeeEntity.setThirdEducation(employeeEntity.getEducationIdbyString(value));
										}else{
											isGood = false;
											msgsb.append(appendMsg(i,j,"第三学历",true));
										}
									}
									break;
								//第三学位
								case 17: 
									employeeEntity.setThirdDegree(value);
									break;
								//第三毕业学校
								case 18: 
									employeeEntity.setThirdSchool(value);
									break;
								//第三专业
								case 19: 
									employeeEntity.setThirdMajor(value);
									break;
								//最高学历
								case 20: 
									if(!isNullOrEmpty(value)){
										if(employeeEntity.getEducationIdbyString(value)==-1){
											isGood = false;
											msgsb.append(appendMsg(i,j,"最高学历",false));
										}else{
											employeeEntity.setEducation(employeeEntity.getEducationIdbyString(value));
										}
									}else{
										isGood = false;
										msgsb.append(appendMsg(i,j,"最高学历",true));
									}
									break;
								//最高学位
								case 21: 
									if(!isNullOrEmpty(value)){
										employeeEntity.setDegree(value);
									}else{
										isGood = false;
										msgsb.append(appendMsg(i,j,"最高学位",true));
									}
									break;
								//最高毕业学校
								case 22: 
									if(!isNullOrEmpty(value)){
										employeeEntity.setSchool(value);
									}else{
										isGood = false;
										msgsb.append(appendMsg(i,j,"最高毕业学校",true));
									}
									break;
								//最高专业
								case 23: 
									if(!isNullOrEmpty(value)){
										employeeEntity.setMajor(value);
									}else{
										isGood = false;
										msgsb.append(appendMsg(i,j,"最高专业",true));
									}
									break;
								//职称
								case 24: 
									if(!isNullOrEmpty(value)){
									if(employeeEntity.getJobTitleIdbyString(value) == -1){
										isGood = false;
										msgsb.append(appendMsg(i,j,"职称",false));
									}else{
										employeeEntity.setJobTitle(employeeEntity.getJobTitleIdbyString(value));
									}
									}else{
										isGood = false;
										msgsb.append(appendMsg(i,j,"职称",true));
									}
									break;
								//职务
								case 25: 
									if(!isNullOrEmpty(value)){
									if(employeeEntity.getPositionIdbyString(value) == -1){
										isGood = false;
										msgsb.append(appendMsg(i,j,"职务",false));
									}else{
										employeeEntity.setPosition(employeeEntity.getPositionIdbyString(value));
									}
									}else{
										isGood = false;
										msgsb.append(appendMsg(i,j,"职务",true));
									}
									break;
								//户口所在地
								case 26: 
									if(!isNullOrEmpty(value)){
										employeeEntity.setRegisteredLocal(value);
									} else{
										isGood = false;
										msgsb.append(appendMsg(i,j,"户口所在地",true));
									}
									break;
								//上岗时间
								case 27:
									if(!isNullOrEmpty(value)){
										employeeEntity.setOnboardTime(value);
									} else{
										isGood = false;
										msgsb.append(appendMsg(i,j,"上岗时间",true));
									}
									break;
								//人员构成
								case 28: 
									if(!isNullOrEmpty(value)){
										if(employeeEntity.getEmployeeCompositionIdbyString(value) == -1){
											isGood = false;
											msgsb.append(appendMsg(i,j,"人员构成",false));
										}else{
											employeeEntity.setEmployeeComposition(employeeEntity.getEmployeeCompositionIdbyString(value));
										}
									}else{
										isGood = false;
										msgsb.append(appendMsg(i,j,"人员构成",true));
									}
									break;
								default: 
									break;
								}	
						}
						if(isGood){
							employList.add(employeeEntity);
						}
					}
					if(employList.size()> 0&& isGood){
						if(employeeInfoService.loadEmployeeInfo(employList)){
							this.setMsg("导入成功！");
						}
					}else{
						this.setMsg(msgsb.toString());
					}
				}else{
					this.setMsg("文件为空，请确认！");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			workbook.close();
		}
		
		return EMPLOYEE_LOAD;
	}
	private HashMap<String,Long> listToHash(List<DepartmentEntity> list){
		HashMap<String,Long> departHash = null;
		if(list.size() >0){
			departHash = new HashMap<String,Long>();
			for(DepartmentEntity departEntity:list){
				departHash.put(departEntity.getName(), departEntity.getDiId());
			}
		}
		return departHash;
	}
	private String appendMsg(int i,int j,String columnName,Boolean isNull){
		StringBuffer msgsb = new StringBuffer();
		if(isNull){
			msgsb.append("第");
			msgsb.append(i+1);
			msgsb.append("行，第");
			msgsb.append(j+1);
			msgsb.append("列");
			msgsb.append(columnName);
			msgsb.append("为空，请确认\\n");
		}else{
			msgsb.append("第");
			msgsb.append(i+1);
			msgsb.append("行，第");
			msgsb.append(j+1);
			msgsb.append("列");
			msgsb.append(columnName);
			msgsb.append("不正确，请确认\\n");
		}
		return msgsb.toString();
	}
//	/**
//	 * ------------------------getter/setter--------------------
//	 */
	public EmployeeInfoService getEmployeeInfoService() {
		return employeeInfoService;
	}
	public void setEmployeeInfoService(EmployeeInfoService employeeInfoService) {
		this.employeeInfoService = employeeInfoService;
	}
	public EmployeeEntity getEmployeeInfo() {
		return employeeInfo;
	}
	public void setEmployeeInfo(EmployeeEntity employeeInfo) {
		this.employeeInfo = employeeInfo;
	}

	public File getFileName() {
		return fileName;
	}

	public void setFileName(File fileName) {
		this.fileName = fileName;
	}
	
}
