package com.framework.components.report.jfreecharts.pie;

import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

/**
 * 
 * @author dafei
 *
 */
public final class PieCharts {
	
	//红色、黄色、绿色、橙色、淡红色、蓝色、紫色
	public final static Color[] arrColors={
		Color.red,Color.blue,Color.GREEN,Color.orange,
		Color.pink,new Color(153, 153, 255),new Color(102, 0, 102)
	};
	
	private PieCharts(){}
	
	public static void setNoDataMessage(PiePlot pieplot) 
	{   
        // 设置没有数据时显示的信息
        pieplot.setNoDataMessage("无数据");   
        // 设置没有数据时显示的信息的字体
        pieplot.setNoDataMessageFont(new Font("宋体", Font.ITALIC, 16));   
        // 设置没有数据时显示的信息的颜色
        pieplot.setNoDataMessagePaint(Color.orange);
	 }
	
	/**
	 * 设置饼图扇区颜色
	 * @param pieplot
	 * @param sectionTitles
	 * @param PkSectionTitle
	 * @param colors
	 */
	public static void setSection(PiePlot pieplot,String[] sectionTitles,String PkSectionTitle,int[] colorsIndex) 
	{   
		//设置扇区颜色   
		for(int i=0;i<sectionTitles.length;i++){
			pieplot.setSectionPaint(sectionTitles[i], arrColors[colorsIndex[i]]);   
		}
        //设置扇区分离显示   
        pieplot.setExplodePercent(PkSectionTitle, 0.2D);   
        //设置扇区边框不可见   
        pieplot.setSectionOutlinesVisible(false);   
    }   
	
	private static void configFont(JFreeChart pieChart)
	{
		// 配置字体    
        Font kfont = new Font("宋体",Font.PLAIN,12) ;// 底部   
        Font titleFont = new Font("宋体", Font.BOLD , 14) ; // 图片标题           
        // 图片标题   
        pieChart.setTitle(new TextTitle(pieChart.getTitle().getText(),titleFont));         
        // 底部   
        pieChart.getLegend().setItemFont(kfont);   
        
        // VALUE_TEXT_ANTIALIAS_OFF表示将文字的抗锯齿关闭.   
        pieChart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,   
                RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);   
        //得到3D饼图的plot对象   
        PiePlot3D piePlot = (PiePlot3D) pieChart.getPlot();   
        //设置旋转角度   
        piePlot.setStartAngle(290);   
        //设置旋转方向   
        piePlot.setDirection(Rotation.CLOCKWISE);   
        //设置透明度   
        piePlot.setForegroundAlpha(0.5f);   
        piePlot.setLabelFont(kfont);  
	}
	
	/**
     * 配置字体
     * @param chart JFreeChart 对象  
     */  
    private static void configFont(JFreeChart chart,String[] sectionTitles,String PkSectionTitle,int[] colors)
    {   
        // 配置字体    
        Font kfont = new Font("宋体",Font.PLAIN,12) ;// 底部   
        Font titleFont = new Font("宋体", Font.BOLD , 14) ; // 图片标题           
        // 图片标题   
        chart.setTitle(new TextTitle(chart.getTitle().getText(),titleFont));         
        // 底部   
        chart.getLegend().setItemFont(kfont);   
        
        // VALUE_TEXT_ANTIALIAS_OFF表示将文字的抗锯齿关闭.   
        chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,   
                RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
                
        PiePlot piePlot = (PiePlot)chart.getPlot();
        // 设置图像区域背景颜色
        piePlot.setBackgroundPaint(Color.white); 
        //设置扇区颜色
        setSection(piePlot,sectionTitles,PkSectionTitle,colors);
        
      //设置扇区标签显示格式：关键字：值(百分比)   
        piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}：{1}({2})"));
        //设置扇区标签颜色   
        piePlot.setLabelBackgroundPaint(new Color(220, 220, 220));   
        piePlot.setLabelFont(kfont); 
        
        piePlot.setForegroundAlpha(0.5f);
        
//        //设定背景透明度（0-1.0之间） 
//        piePlot.setBackgroundAlpha(0.6f); 
//        //设定前景透明度（0-1.0之间） 
//        piePlot.setForegroundAlpha(0.8f); 
//        BarRenderer3D renderer = new BarRenderer3D(); 
//        renderer.setWallPaint(Color.lightGray);
    } 
    
    /**
     * 生成图形，保存到指定文件，文件类型为png
     *
     * @param fileName
     *            指定保存文件的路径及文件名
     * @param chart
     *            JFreeChart对象实例
     * @param w
     *            图像宽度
     * @param h
     *            图像高度
     */
    public static void SaveFileAsPNG(String fileName, JFreeChart chart, int w, int h) throws Exception{
        // 生成图形，保存到指定文件，文件类型为png
        try{
            ChartUtilities.saveChartAsPNG(new File(fileName), chart, w, h);
        } catch (IOException e){
            // TODO Auto-generated catch block
        	 throw e;
        }
    }

    /**
     * 生成图形，保存到指定文件，文件类型为jpg
     *
     * @param fileName
     *            指定保存文件的路径及文件名
     * @param chart
     *            JFreeChart对象实例
     * @param w
     *            图像宽度
     * @param h
     *            图像高度
     */
    public static void SaveFileAsJPEG(String fileName, JFreeChart chart, int w, int h) throws Exception{
        // 生成图形，保存到指定文件，文件类型为jpg
        try{
            ChartUtilities.saveChartAsPNG(new File(fileName), chart, w, h);
        } catch (IOException e){
            // TODO Auto-generated catch block
           throw e;
        }
    }

    /**
     * 创建一维柱状图
     * @param entity
     * @throws Exception
     */
    public static void createSamplePie(PieEntity mainEntiy,PieEntity[] entity,String[] sectionTitles,String PkSectionTitle,int[] colorsIndex) throws Exception
	{
    	if(entity==null || entity.length<1) return;
    	try
    	{
    		DefaultPieDataset dataset = new DefaultPieDataset();    		
    		for(int i=0;i<entity.length;i++){
    			if(entity[i].getKey()==null || "".equals(entity[i].getKey())) continue;
    			dataset.setValue(entity[i].getKey(),entity[i].getValue());
    		}

			//创建JFreechart对象,3D状的
	    	JFreeChart chart = ChartFactory.createPieChart3D(mainEntiy.getTitle(),
	    			dataset,true,true,false);
	    	
			//防止中文乱码	
	        configFont(chart,sectionTitles,PkSectionTitle,colorsIndex); 
	        
	        if(mainEntiy.getIsSavePngImg()){
	        	SaveFileAsPNG(mainEntiy.getImageFilename(),chart,mainEntiy.getWidth(),mainEntiy.getHeight());
	        	return;
	        }
	        SaveFileAsJPEG(mainEntiy.getImageFilename(),chart,mainEntiy.getWidth(),mainEntiy.getHeight());
    	}
    	catch(Exception e){
    		throw e;
    	}
	}
    
    
    /**
	 * 获取一个演示用的简单数据集对象
	 * @return
	 */
	private static DefaultPieDataset getDataSet() 
	{
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("苹果",100);
		dataset.setValue("梨子",200);
		dataset.setValue("葡萄",300);
		dataset.setValue("香蕉",400);
		dataset.setValue("荔枝",500);
		return dataset;
	}
    
    public static void main(String[] args) throws IOException
    {
		DefaultPieDataset data = getDataSet();
		JFreeChart chart = ChartFactory.createPieChart3D("水果产量图",  // 图表标题
		data, 
		true, // 是否显示图例
		false,
		false
		);
		//写图表对象到文件，参照柱状图生成源码
	}
	


}
