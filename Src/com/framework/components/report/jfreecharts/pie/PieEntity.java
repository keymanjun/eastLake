package com.framework.components.report.jfreecharts.pie;

public class PieEntity 
{
	String key="";
	Integer value;
	String title="";
	String content="";
	String imageFilename="";
	int width=250;
	int height=220;
	boolean isSavePngImg=false;
	public String getKey() {
		return this.key;
	}
	
	public String getTitle() {
		return this.title;
	}
	public void setKey(String key) {
		this.key = key;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getValue() {
		return this.value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getImageFilename() {
		return this.imageFilename;
	}

	public boolean getIsSavePngImg() {
		return this.isSavePngImg;
	}

	public void setImageFilename(String imageFilename) {
		this.imageFilename = imageFilename;
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

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
