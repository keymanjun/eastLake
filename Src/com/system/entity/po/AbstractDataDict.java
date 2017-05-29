package com.system.entity.po;

/**
 * 
* <p>版权所有:(C)2003-2010 北京才友莱信息技术有限公司</p>
* @作者: allen
* @日期: Nov 30, 2010 10:37:02 PM
* @描述: [AbstractDataDict]数据字典类
 */
public abstract class AbstractDataDict {
	
	/**
	 * 主键标识
	 */
	private Integer dataid;
	
	/**
	 * 字典编号
	 */
	private String datacode;
	
	/**
	 * 父字典编号
	 */
	private String pdatacode;
	
	/**
	 * 数据值
	 */
	private String datavalue;
	
	/**
	 * 字典类别
	 */
	private String category;
	
	/**
	 * 备注
	 */
	private String sdesc;
	
	/**
	 * 可用标记
	 */
	private Integer enable;

	public Integer getDataid() {
		return dataid;
	}

	public void setDataid(Integer dataid) {
		this.dataid = dataid;
	}

	public String getDatacode() {
		return datacode;
	}

	public void setDatacode(String datacode) {
		this.datacode = datacode;
	}

	public String getPdatacode() {
		return pdatacode;
	}

	public void setPdatacode(String pdatacode) {
		this.pdatacode = pdatacode;
	}

	public String getDatavalue() {
		return datavalue;
	}

	public void setDatavalue(String datavalue) {
		this.datavalue = datavalue;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSdesc() {
		return sdesc;
	}

	public void setSdesc(String sdesc) {
		this.sdesc = sdesc;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}
}
