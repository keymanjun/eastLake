package com.framework.components.report.jfreecharts.line;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

//import com.iss.report.jfreechart.commom.DateSetList;


public class LineEntity 
{
	//chart title
	String chartTitle="";
	//x、y轴title
	String xTitle="";
	String yTitle="";
	String filepath="";
	//线名称
	String lineName;
	//X轴各段名称集合
	String[] xAxisName=null;
	
	boolean isSavePngImg=false;
	int width=210;
	int height=160;
	String imgName="";
	
	String[] rowKeys=null;
	String[] columnLabels=null;
	private double[][] data=null;
	
	// allen
	private List<List> dateSetList;
	
	public String[] getRowKeys() {
		return this.rowKeys;
	}
	public void setRowKeys(String[] rowKeys) {
		this.rowKeys = rowKeys;
	}
	
	public String[] getColumnLabels() {
		return this.columnLabels;
	}
	public void setColumnLabels(String[] columnLabels) {
		this.columnLabels = columnLabels;
	}
	public String getChartTitle() {
		return this.chartTitle;
	}
	public String getXTitle() {
		return this.xTitle;
	}
	public String getYTitle() {
		return this.yTitle;
	}
	public String getLineName() {
		return this.lineName;
	}
	public String[] getXAxisName() {
		return this.xAxisName;
	}
	public void setChartTitle(String chartTitle) {
		this.chartTitle = chartTitle;
	}
	public void setXTitle(String title) {
		this.xTitle = title;
	}
	public void setYTitle(String title) {
		this.yTitle = title;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public void setXAxisName(String[] axisName) {
		this.xAxisName = axisName;
	}
	public boolean getIsSavePngImg() {
		return this.isSavePngImg;
	}
	public void setIsSavePngImg(boolean isSavePngImg) {
		this.isSavePngImg = isSavePngImg;
	}
	public int getWidth() {
		return this.width;
	}
	public int getHeight() {
		return this.height;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getImgName() {
		return this.imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public double[][] getData() {
		return this.data;
	}
	public void setData(double[][] data) {
		this.data = data;
	}
	
	/**
	 * 将list集合数据转换为line图所需结构数据
	 * @param list
	 * @param columns
	 * @param lineEntity
	 * @throws Exception
	 */
	public static void conv2MuiltLineData2(List lists,String[] columns,LineEntity lineEntity) throws Exception
	{
	
		
		if (lists == null || lists.size() < 1 || lineEntity.getRowKeys() == null)
			return;
		String[] columnLabels = null;
		double[][] values = null;
		try {
			int rownum = lineEntity.getRowKeys().length;

			int colnum = lists.size();
			columnLabels = new String[colnum];
			values = new double[rownum][colnum];
			for (int k = 0; k < lists.size(); k++) {
				List list = (List) lists.get(k);
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {

						Object bean = list.get(i);
						if (bean == null)
							continue;
						for (int j = 0; j < columns.length; j++) {
							Method method = bean.getClass().getMethod(
									columns[j], null);
							Object obj = method.invoke(bean, null);
							if (j == 0) {
								
								continue;
							}

							values[i][k] = obj == null ? 0 : Double
									.parseDouble(obj.toString());
						}
					}
				}
			}
			//lineEntity.setColumnLabels(columnLabels);
			lineEntity.setData(values);
		} catch (Exception e) {
			throw e;
		}
	
		/*if(list==null || list.size()<1 || lineEntity.getRowKeys()==null) return;
		try
		{
			String[] columnLabels=new String[list.size()];
			double[][] values=new double[lineEntity.getRowKeys().length][columnLabels.length];
			for(int i=0;i<columns.length;i++)
			{
				for(int j=0;j<list.size();j++)
				{
					Object bean=list.get(j);
					if(bean==null) continue;
					Method method=bean.getClass().getMethod(columns[i], null);
					Object obj=method.invoke(bean, null);
					if(i==0){
						columnLabels[j]=(obj==null?"":obj.toString());
						continue;
					}
					values[i-1][j]=obj==null?0:Double.parseDouble(obj.toString());
				}
			}
			lineEntity.setColumnLabels(columnLabels);
			lineEntity.setData(values);
		}
		catch(Exception e){
			throw e;
		}*/
	}
	
	public static void conv2MuiltLineData(List lists,String[] columns,LineEntity lineEntity) throws Exception
	{
		if (lists == null || lists.size() < 1 || lineEntity.getRowKeys() == null)
			return;
		String[] columnLabels = null;
		double[][] values = null;
		try {
			int rownum = lineEntity.getRowKeys().length;
			if (lists.size() > 1) {
				int colnum = ((List) lists.get(0)).size();
				columnLabels = new String[colnum];
				values = new double[rownum][colnum];
				for (int k = 0; k < lists.size(); k++) {
					List list = (List) lists.get(k);
					if (list != null && list.size() > 0) {
						for (int i = 0; i < list.size(); i++) {

							Object bean = list.get(i);
							if (bean == null)
								continue;
							for (int j = 0; j < columns.length; j++) {
								Method method = bean.getClass().getMethod(
										columns[j], null);
								Object obj = method.invoke(bean, null);
								if (j == 0) {
									if (k == 0) {
										columnLabels[i] = (obj == null ? ""
												: obj.toString());
									}
									continue;
								}

								values[k][i] = obj == null ? 0 : Double
										.parseDouble(obj.toString());
							}
						}
					}
				}

			} else {
				int colnum = ((List) lists.get(0)).size();
				columnLabels = new String[colnum];
				values = new double[rownum][colnum];
				for (int i = 0; i < ((List) lists.get(0)).size(); i++) {
					Object bean = ((List) lists.get(0)).get(i);
					if (bean == null)
						continue;
					for (int j = 0; j < columns.length; j++) {
						Method method = bean.getClass().getMethod(columns[j],
								null);
						Object obj = method.invoke(bean, null);
						if (j == 0) {
							columnLabels[i] = (obj == null ? "" : obj
									.toString());
							continue;
						}

						values[j - 1][i] = obj == null ? 0 : Double
								.parseDouble(obj.toString());
					}
				}
			}
			lineEntity.setColumnLabels(columnLabels);
			lineEntity.setData(values);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<List> getDateSetList() {
		return dateSetList;
	}
	public void setDateSetList(List<List> dateSetList) {
		this.dateSetList = dateSetList;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}


	
}
