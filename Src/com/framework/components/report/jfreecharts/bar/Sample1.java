package com.framework.components.report.jfreecharts.bar;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.Range;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;

public class Sample1 
{
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
    public void SaveFileAsPNG(String fileName, JFreeChart chart, int w, int h) {
        // 生成图形，保存到指定文件，文件类型为png
        try{
            ChartUtilities.saveChartAsPNG(new File(fileName), chart, w, h);
        } catch (IOException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
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
    public void SaveFileAsJPEG(String fileName, JFreeChart chart, int w, int h){
        // 生成图形，保存到指定文件，文件类型为jpg
        try{
            ChartUtilities.saveChartAsPNG(new File(fileName), chart, w, h);
        } catch (IOException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**  
     * 配置字体   
     * @param chart JFreeChart 对象  
     */  
    private void configFont(JFreeChart chart){   
        // 配置字体   
        Font xfont = new Font("宋体",Font.PLAIN,12) ;// X轴   
        Font yfont = new Font("宋体",Font.PLAIN,12) ;// Y轴   
        Font kfont = new Font("宋体",Font.PLAIN,12) ;// 底部   
        Font titleFont = new Font("隶书", Font.BOLD , 25) ; // 图片标题   
        CategoryPlot plot = chart.getCategoryPlot();// 图形的绘制结构对象   
           
        // 图片标题   
        chart.setTitle(new TextTitle(chart.getTitle().getText(),titleFont));   
           
        // 底部   
        chart.getLegend().setItemFont(kfont);   
           
        // X 轴   
        CategoryAxis domainAxis = plot.getDomainAxis();      
        domainAxis.setLabelFont(xfont);// 轴标题   
        domainAxis.setTickLabelFont(xfont);// 轴数值     
        domainAxis.setTickLabelPaint(Color.BLUE) ; // 字体颜色   
        //domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45); // 横轴上的label斜显示    
           
        // Y 轴   
        ValueAxis rangeAxis = plot.getRangeAxis();      
        rangeAxis.setLabelFont(yfont);    
        rangeAxis.setLabelPaint(Color.BLUE) ; // 字体颜色   
        rangeAxis.setTickLabelFont(yfont);     
           
    } 
    
	public JFreeChart createBar1() throws Exception
	{
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(300, "广州", "苹果");
		dataset.addValue(200, "广州", "梨子");
		dataset.addValue(500, "广州", "香蕉");
		dataset.addValue(340, "广州", "芒果");
		dataset.addValue(280, "广州", "提子");
	
		//创建JFreechart对象，平面状的
		//JFreeChart chart = ChartFactory.createBarChart("水果产量统计", "水果", "产量",
         //       dataset, PlotOrientation.VERTICAL, true, true, true);
		
		//创建JFreechart对象,3D状的
		JFreeChart chart = ChartFactory.createBarChart3D("水果销量统计","水果","销量",
		                  dataset,PlotOrientation.VERTICAL,true,true,true);
		//chart.getTitle().setFont(new Font("黑体", Font.BOLD, 20));  
		
		
		// 设置图形显示属性
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        // 目录轴网格是否可见
        plot.setDomainGridlinesVisible(false);
        // 目录轴网格线的颜色
        plot.setDomainGridlinePaint(Color.red);
        // 数据轴网格是否可见
        plot.setRangeGridlinesVisible(true);
        // 数据轴网格线的颜色
        plot.setRangeGridlinePaint(Color.blue);
        // 设置图像区域背景颜色
        plot.setBackgroundPaint(Color.white);
        // 设置柱的透明度
        plot.setForegroundAlpha(0.9f);
        // 设置目录轴和数据轴的显示位置
        plot.setDomainAxisLocation(AxisLocation.TOP_OR_LEFT);
        plot.setRangeAxisLocation(AxisLocation.TOP_OR_LEFT);
        //plot.setDomainAxisLocation(AxisLocation.TOP_OR_RIGHT);
        //plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);

        // 设置数据轴的刻度
        NumberAxis naxis = (NumberAxis) plot.getRangeAxis();
        // 设置数据轴的数据范围
        naxis.setRange(new Range(0, 1000));
        // 设置刻度波动值
        naxis.setTickUnit(new NumberTickUnit(100));

        // 设置图形的宽度
        CategoryAxis caxis = plot.getDomainAxis();
        // 设置最高的一个柱图与图片顶端的距离
        caxis.setUpperMargin(0.15);
        // 设置最低的一个柱图与图片底端的距离
        caxis.setLowerMargin(0.15);

        // 设置柱图属性
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        //
        renderer.setBaseOutlinePaint(Color.black);
        // 设置每个地区代表的柱的颜色
        //renderer.setSeriesPaint(0, Color.pink);
        renderer.setSeriesPaint(1, new Color(0, 100, 155));
        renderer.setSeriesPaint(2, Color.green);
        // 设置每个地区所包含的平行柱的之间距离
        renderer.setItemMargin(0.02);

        configFont(chart);
        return chart;
		//String filename = ServletUtilities.saveChartAsPNG(chart, 500, 300, null, session);
	}
	
	
	public JFreeChart createBar2() throws Exception
	{
		double[][] data = new double[][] {{672, 766, 223, 540, 126}, {325, 521, 210, 340, 106}, {332, 256, 523, 240, 526}};
		String[] rowKeys = {"苹果","梨子","葡萄"};
		String[] columnKeys = {"北京","上海","广州","成都","深圳"};
		CategoryDataset dataset = DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data); 
		
		
		//创建JFreechart对象,3D状的
		JFreeChart chart = ChartFactory.createBarChart3D("水果销量统计","水果","销量",
		                  dataset,PlotOrientation.VERTICAL,true,true,false);
		//防止中文乱码		
        configFont(chart);
        return chart;		
	}

	public static void main(String[] args) throws Exception
	{
		Sample1 sa=new Sample1();
		JFreeChart chart = sa.createBar1();
		//JFreeChart chart = sa.createBar2();
		
		
        // 保存为图像文件
        sa.SaveFileAsJPEG("d:/barchart.jpeg", chart, 400, 300);
        // 显示在GUI上
        ChartFrame frame = new ChartFrame("水果产量", chart);
        frame.pack();
        frame.setVisible(true);		
        
	}
	

}
