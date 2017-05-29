package com.framework.components.report.jfreecharts.bar;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

//import com.iss.report.jfreechart.commom.DateSetList;

/**
 * 
 * @author dafei
 * 
 */
public class BarEntity {
	private String barTitle = "";
	// x轴标题
	private String xAxisTitle = "";
	// y轴标题
	private String yAxisTitle = "";
	// 行列标题
	private String[] rowKeys = null;
	// 列维度标题
	private String[] columnLabels = null;
	// 一维数据,只用于一维柱状型时使用
	private double[] dataset = null;
	// 多维数据
	private double[][] data = null;

	private int nWidth = 210;
	private int nHeight = 160;
	// 是否保存为png格式的图片，否则为jpeg格式的图片
	private boolean isSavePngImg = false;
	private String imageFilename = "";

	// allen
	private List dataList;
	//private DateSetList dateSetList;

	public String getBarTitle() {
		return this.barTitle;
	}

	public String getXAxisTitle() {
		return this.xAxisTitle;
	}

	public String getYAxisTitle() {
		return this.yAxisTitle;
	}

	public String[] getRowKeys() {
		return this.rowKeys;
	}

	public String[] getColumnLabels() {
		return this.columnLabels;
	}

	public double[][] getData() {
		return this.data;
	}

	public int getWidth() {
		return this.nWidth;
	}

	public int getHeight() {
		return this.nHeight;
	}

	public String getImageFilename() {
		return this.imageFilename;
	}

	public void setBarTitle(String barTitle) {
		this.barTitle = barTitle;
	}

	public void setXAxisTitle(String axisTitle) {
		this.xAxisTitle = axisTitle;
	}

	public void setYAxisTitle(String axisTitle) {
		this.yAxisTitle = axisTitle;
	}

	public void setRowKeys(String[] rowKeys) {
		this.rowKeys = rowKeys;
	}

	public void setColumnLabels(String[] columnLabels) {
		this.columnLabels = columnLabels;
	}

	public void setData(double[][] data) {
		this.data = data;
	}

	public void setWidth(int width) {
		this.nWidth = width;
	}

	public void setHeight(int height) {
		this.nHeight = height;
	}

	public void setImageFilename(String imageFilename) {
		this.imageFilename = imageFilename;
	}

	public boolean getIsSavePngImg() {
		return this.isSavePngImg;
	}

	public void setIsSavePngImg(boolean isSavePngImg) {
		this.isSavePngImg = isSavePngImg;
	}

	public double[] getDataset() {
		return this.dataset;
	}

	public void setDataset(double[] dataset) {
		this.dataset = dataset;
	}

	/**
	 * 根据字段名反射取出实体内相应的属性值,将实体值转换为简单柱状图数组数据格式
	 * 
	 * @param list
	 * @param columns
	 *            columns数据元素顺序是字符串为前,值为后,例:[getTopic,getPointNum]
	 * @return
	 * @throws Exception
	 */
	public static void conv2SampleBarData(List list, String[] columns,
			BarEntity barEntity) throws Exception {
		if (list == null || list.size() < 1)
			list = new ArrayList();
		try {
			String[] columnLabels = new String[list.size()];
			double[] values = new double[list.size()];
			for (int i = 0; i < list.size(); i++) {
				Object bean = list.get(i);
				if (bean == null)
					continue;
				for (int j = 0; j < columns.length; j++) {
					Method method = bean.getClass().getMethod(columns[j], null);
					Object obj = method.invoke(bean, null);
					if (j == 0) {
						columnLabels[i] = (obj == null ? "" : obj.toString());
						continue;
					}
					values[i] = obj == null ? 0 : Double.parseDouble(obj
							.toString());
				}
			}
			barEntity.setColumnLabels(columnLabels);
			barEntity.setDataset(values);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 转换多维度柱状图数据
	 * 
	 * @param list
	 * @param columns
	 * @return
	 * @throws Exception
	 */
	public static void conv2MuiltBarData(List list, String[] columns,
			BarEntity barEntity) throws Exception {
		if (list == null || list.size() < 1 || barEntity.getRowKeys() == null)
			return;
		try {
			String[] columnLabels = new String[list.size()];
			double[][] values = new double[barEntity.getRowKeys().length][columnLabels.length];
			for (int i = 0; i < columns.length; i++) {
				for (int j = 0; j < list.size(); j++) {
					Object bean = list.get(j);
					if (bean == null)
						continue;
					Method method = bean.getClass().getMethod(columns[i], null);
					Object obj = method.invoke(bean, null);
					if (i == 0) {
						columnLabels[j] = (obj == null ? "" : obj.toString());
						continue;
					}
					values[i - 1][j] = obj == null ? 0 : Double.parseDouble(obj
							.toString());
				}
			}
			barEntity.setColumnLabels(columnLabels);
			barEntity.setData(values);
		} catch (Exception e) {
			throw e;
		}
	}

	/*public static void conv2MuiltBarDataset(DateSetList lists,
			String[] columns, BarEntity barEntity) throws Exception {

		if (lists == null || lists.size() < 1 || barEntity.getRowKeys() == null)
			return;
		String[] columnLabels = null;
		double[][] values = null;
		try {
			int rownum = barEntity.getRowKeys().length;
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
			}*/
			/*
			 * if(list==null || list.size()<1 || barEntity.getRowKeys()==null)
			 * return; try { int rownum = barEntity.getRowKeys().length;
			 * String[] columnLabels=new String[list.size()]; int colnum =
			 * columnLabels.length/rownum; double[][] values=new
			 * double[rownum][colnum]; for(int i=0;i<list.size();i++) { Object
			 * bean=list.get(i); if(bean==null) continue; for(int j=0;j<columns.length;j++) {
			 * Method method=bean.getClass().getMethod(columns[j], null); Object
			 * obj=method.invoke(bean, null); if(j==0){
			 * columnLabels[j]=(obj==null?"":obj.toString()); continue; } int
			 * row = i/colnum; int col = i-row*rownum;
			 * 
			 * values[row][col]=obj==null?0:Double.parseDouble(obj.toString()); } }
			 */
			/*
			 * for(int i=0;i<columns.length;i++) { for(int j=0;j<list.size();j++) {
			 * Object bean=list.get(j); if(bean==null) continue; Method
			 * method=bean.getClass().getMethod(columns[i], null); Object
			 * obj=method.invoke(bean, null); if(i==0){
			 * columnLabels[j]=(obj==null?"":obj.toString()); continue; }
			 * values[i-1][j]=obj==null?0:Double.parseDouble(obj.toString()); } }
			 */
		/*	barEntity.setColumnLabels(columnLabels);
			barEntity.setData(values);
		} catch (Exception e) {
			throw e;
		}
	}*/
	
	public String[] getExValue(String key, boolean flag) throws Exception{
		String[] arrayExValue = null;
		
		if(dataList != null && dataList.size() > 0) {	
			arrayExValue = new String[dataList.size()];
			if(flag) {
				arrayExValue = new String[dataList.size()*2];
			}
			for(int i=0; i<dataList.size(); i++) {
				Object bean = dataList.get(i);
				Method method = bean.getClass().getMethod(key, null);
				Object obj = method.invoke(bean, null);	
				int index = i;
				if(flag) {
					index = (dataList.size()-i)*2 - 1;
				}	
				if(flag) {
					arrayExValue[index] = (obj==null?"":obj.toString());	
					arrayExValue[index-1] = arrayExValue[index];
				}else {
					arrayExValue[index] = (obj==null?"":obj.toString());	
				}
			}							
		}
		return arrayExValue;
	}

	/*public DateSetList getDateSetList() {
		return dateSetList;
	}

	public void setDateSetList(DateSetList dateSetList) {
		this.dateSetList = dateSetList;
	}*/
	
	public List getDataList() {
		return dataList;
	}

	public void setDataList(List dataList) {
		this.dataList = dataList;
	}
}
