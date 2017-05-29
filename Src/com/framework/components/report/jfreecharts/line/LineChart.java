package com.framework.components.report.jfreecharts.line;

import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;

//import com.iss.report.jfreechart.commom.ImageInfo;

/**
 * 
 * @author dafei
 *
 */
public final class LineChart 
{
	private LineChart(){} 
	
	/**
     * 配置字体
     * @param chart JFreeChart 对象  
     */  
    private static void configFont(JFreeChart chart){   
        // 配置字体   
        Font xfont = new Font("宋体",Font.PLAIN,12) ;// X轴   
        Font yfont = new Font("宋体",Font.PLAIN,12) ;// Y轴   
        Font kfont = new Font("宋体",Font.PLAIN,12) ;// 底部   
        //Font titleFont = new Font("隶书", Font.BOLD , 25) ; // 图片标题
        Font titleFont = new Font("宋体", Font.BOLD , 15) ; // 图片标题
        CategoryPlot plot = chart.getCategoryPlot();// 图形的绘制结构对象           
        // 图片标题   
        chart.setTitle(new TextTitle(chart.getTitle().getText(),titleFont)); 
        chart.setBackgroundPaint(new Color(206,235,255));
        // 底部   
        chart.getLegend().setItemFont(kfont);   
        plot.setBackgroundAlpha(0f);   
        // X 轴
        CategoryAxis domainAxis = plot.getDomainAxis();      
        domainAxis.setLabelFont(xfont);// 轴标题   
        domainAxis.setTickLabelFont(xfont);// 轴数值     
        domainAxis.setTickLabelPaint(Color.BLACK) ; // 字体颜色   
        //domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45); // 横轴上的label斜显示    
           
             
        // Y 轴
        ValueAxis rangeAxis = plot.getRangeAxis();      
        rangeAxis.setLabelFont(yfont);  
        rangeAxis.setLabelPaint(Color.BLUE) ; // 字体颜色   
        rangeAxis.setTickLabelFont(yfont);     
           
    } 
    
    private static void configFont1(JFreeChart chart)
    {
    	chart.setBackgroundPaint(new Color(193, 193, 193));//设置背景色   
        chart.getLegend().setItemFont((new Font("宋体", Font.PLAIN, 12)));//图示字体大小   
           
        //获得plot对象   
        XYPlot xyplot = chart.getXYPlot();   
        xyplot.setBackgroundPaint(new Color(233, 233, 233));// 设置数据区（中间部分背景色）   
        xyplot.setDomainGridlinesVisible(true);// x轴分类轴网格是否可见   
        xyplot.setDomainGridlinePaint(Color.BLACK);//x轴虚线色彩   
        xyplot.setRangeGridlinesVisible(true);// y轴数据轴网格是否可见   
        xyplot.setRangeGridlinePaint(Color.red);//y轴虚线色彩   
        xyplot.setDomainGridlinesVisible(true);// x轴分类轴网格是否可见   
        xyplot.setDomainGridlinePaint(Color.WHITE);// 虚线色彩   
        xyplot.setRangeGridlinesVisible(true);// y轴数据轴网格是否可见   
        xyplot.setRangeGridlinePaint(Color.BLACK);// 虚线色彩   
           
        //获取LegendTitle对象(就是图示那个区域对象)   
        LegendTitle lenged = chart.getLegend();   
        lenged.setPosition(RectangleEdge.RIGHT);   
        lenged.setBackgroundPaint(Color.WHITE);//背景色   
        lenged.setHeight(100.00);//高度   
        lenged.setItemLabelPadding(RectangleInsets.ZERO_INSETS);   
        lenged.setItemPaint(Color.blue);   
        lenged.setItemFont(new Font("宋体",Font.PLAIN,12));   
        //lenged.setLegendItemGraphicEdge(RectangleEdge.RIGHT);//图例小图形的位置   
        //lenged.setLegendItemGraphicLocation(RectangleAnchor.BOTTOM_RIGHT);//图例小图形的位置   
        //lenged.setLegendItemGraphicPadding(RectangleInsets.ZERO_INSETS);   
           
  
          //配置字体         
        Font xlabelfont = new Font("宋体",Font.PLAIN,12) ;// x轴标题字体      
        Font xtickfont = new Font("宋体",Font.PLAIN,12) ;// x轴刻度字体      
        Font ylabelfont = new Font("宋体",Font.PLAIN,12) ;// Y轴标题字体      
        Font ytickfont = new Font("宋体",Font.PLAIN,12) ;// Y轴刻度字体      
        Font titleFont = new Font("宋体", Font.PLAIN , 25) ; // 图片标题       
        Font itemfont = new Font("宋体",Font.PLAIN,15) ;// 图示字体       
        xyplot.getDomainAxis().setLabelFont(xlabelfont); //x轴标题字体      
        xyplot.getDomainAxis().setTickLabelFont(xtickfont); //x轴刻度字体      
        xyplot.getRangeAxis().setLabelFont(ylabelfont);  //y轴外围字体      
        xyplot.getRangeAxis().setTickLabelFont(ytickfont); //y轴标题字体      
        chart.getTitle().setFont(titleFont);   //设置标题字体   
        chart.getLegend().setItemFont(itemfont); //设置最底下方框内的字体（图示Legend）      
        //抗锯齿关闭      
        chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);      
  
        // 获取 x轴的操作：   
        ValueAxis valueAxis = xyplot.getDomainAxis();   
        valueAxis.setTickMarksVisible(false);   
        valueAxis.setTickMarkPaint(Color.red);//设置x轴刻度尺颜色   
        valueAxis.setLabelAngle(45.00d);//x轴标题旋转角度   
        valueAxis.setTickMarkInsideLength(5.2f);//刻度尺的长度   
        valueAxis.setTickMarkOutsideLength(5.5f);//刻度尺的长度   
  
        // 获取 y 轴操作：   
        NumberAxis numberAxis = (NumberAxis) xyplot.getRangeAxis();   
        numberAxis.setTickMarkPaint(Color.blue);   
        numberAxis.setTickMarksVisible(true);//设置图示是否显示   
        numberAxis.setLowerMargin(0.01);// 设置距离图片左端距离此时为10%   
        numberAxis.setUpperMargin(0.01);// 设置距离图片右端距离此时为百分之10   
        numberAxis.setLabelFont(new Font("黑体", Font.BOLD, 12));// Y轴标题字体   
        numberAxis.setTickLabelFont(new Font("黑体", Font.BOLD, 12));   
        NumberFormat numberformat = new DecimalFormat("00%");   
        numberAxis.setNumberFormatOverride(numberformat);// 设置y轴以百分比方式显示   
  
        //绘制单元的设置   
        XYLineAndShapeRenderer XYLineAndShapeRenderer = (XYLineAndShapeRenderer) xyplot.getRenderer();   
        XYLineAndShapeRenderer.setBaseShapesVisible(true); // series点（即数据点）可见   
        XYLineAndShapeRenderer.setBaseLinesVisible(true); // series点（即数据点）间有连线可见   
        XYLineAndShapeRenderer.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator());   
        XYLineAndShapeRenderer.setBaseItemLabelsVisible(true);//显示折点数据   
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
    
    public static String SaveFileAsPNG4Web(JFreeChart chart, int w, int h, ChartRenderingInfo info) throws Exception{
        // 生成图形，保存到指定文件，文件类型为png
        try{
        	return ServletUtilities.saveChartAsPNG(chart, w, h, info,
					null);// 生成图片
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
    
    public static String SaveFileAsJPEG(String file,JFreeChart chart, int width, int height, ChartRenderingInfo info) throws Exception{
    	 // 生成图形，保存到指定文件，文件类型为jpg
        try{
        	if (chart == null) {
                throw new IllegalArgumentException("Null 'chart' argument.");
            }        
           
            File tempFile = File.createTempFile("jfreechart-", ".jpeg",
                    new File(file));
            ChartUtilities.saveChartAsJPEG(tempFile, chart, width, height, info);
           
            return tempFile.getName();   
        } catch (IOException e){
            // TODO Auto-generated catch block
           throw e;
        }
    }
    
    public static String SaveFileAsJPEG4Web(JFreeChart chart, int w, int h, ChartRenderingInfo info) throws Exception{
        // 生成图形，保存到指定文件，文件类型为jpg
        try{
        	return ServletUtilities.saveChartAsPNG(chart, w, h, info,
					null);// 生成图片
        } catch (IOException e){
            // TODO Auto-generated catch block
           throw e;
        }
    }
    
    public static CategoryDataset getDataSet(LineEntity entity) throws Exception
    {
    	CategoryDataset dataset=null;
    	if(entity.getData()==null || entity.getData().length<1) return null;
    	try
    	{
    		dataset = DatasetUtilities.createCategoryDataset(
    				entity.getRowKeys(), entity.getColumnLabels(), entity.getData());
    	}
    	catch(Exception e){
    		throw e;
    	}
    	return dataset;
    }
    
    /**
     * 创建一维柱状图
     * @param entity
     * @throws Exception
     */
/*public static ImageInfo createLine(LineEntity entity) throws Exception
	{
    	ImageInfo imageInfo = new ImageInfo();
    	
    	try
    	{   		
    		CategoryDataset dataset = getDataSet(entity);
    		if(dataset==null || dataset.getRowCount()<1) return null;
    		
    		//创建JFreechart对象
		   JFreeChart chart = ChartFactory.createLineChart(    
				   entity.getChartTitle(),       // chart title    
				   entity.getXTitle(),                    // domain axis label    
				   entity.getYTitle(),                   // range axis label    
			        dataset,                   // data    
			        PlotOrientation.VERTICAL,  // orientation    
			        true,                      // include legend    
			        true,                      // tooltips    
			        false                      // urls    
			    ); 

			
			//防止中文乱码		
		   configFont(chart); 
	        
	        if(entity.getIsSavePngImg()){
	        	SaveFileAsPNG(entity.getImgName(),chart,entity.getWidth(),entity.getHeight());
	        	return;
	        }
	        SaveFileAsJPEG(entity.getImgName(),chart,entity.getWidth(),entity.getHeight());
	        ChartRenderingInfo info = new ChartRenderingInfo(
    				new StandardEntityCollection());
	        String filename = null;
	        String strMap = null;
	        if(entity.getIsSavePngImg()){	        	
	        	filename = SaveFileAsPNG4Web(chart,entity.getWidth(),entity.getHeight(), info);	        	
	        }else {
	        	filename = SaveFileAsJPEG(ImageInfo.getFilepath(),chart,entity.getWidth(),entity.getHeight(), info);
	        }
	        imageInfo.setFilename(filename);
        	strMap = ChartUtilities.getImageMap(filename, info);
        	imageInfo.setStrMap(strMap);
    	}
    	catch(Exception e){
    		throw e;
    	}
    	return imageInfo;
	}*/
	
    public static String createLine(LineEntity entity) throws Exception{
  	  String filename="";
       try{   		
      		CategoryDataset dataset = getDataSet(entity);
      		if(dataset==null || dataset.getRowCount()<1) return null;
      		
      		//创建JFreechart对象
  		   JFreeChart chart = ChartFactory.createLineChart(    
  				   entity.getChartTitle(),       // chart title    
  				   entity.getXTitle(),                    // domain axis label    
  				   entity.getYTitle(),                   // range axis label    
  			        dataset,                   // data    
  			        PlotOrientation.VERTICAL,  // orientation    
  			        true,                      // include legend    
  			        true,                      // tooltips    
  			        false                      // urls    
  			    ); 
  			//防止中文乱码		
  		   configFont(chart); 
  		   filename = LineChart.SaveFileAsJPEG(entity.getFilepath(), chart, 800, 300, null);
      	}catch(Exception e){
      		e.printStackTrace();
      		throw e;
      	}
      	return filename;
  	}
    
	public static void main(String[] args) throws Exception
	{    	   
	//  各曲线名称    
	    String series1 = "冰箱";    
	    String series2 = "彩电";    
	    String series3 = "洗衣机";    
	   
	   
	//  横轴名称(列名称)    
	    String type1 = "1月";    
	    String type2 = "2月";    
	    String type3 = "3月";    
	    String type4 = "4月";    
	    String type5 = "5月";    
	    String type6 = "6月";    
	    String type7 = "7月";    
	    String type8 = "8月";    
	   
	   
	//  建立dataset    
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();    
	//  以下可以动态添加数据    
	    dataset.addValue(1.3, series1, type1);    
	    dataset.addValue(4.2, series1, type2);    
	    dataset.addValue(3.9, series1, type3);    
	    dataset.addValue(5.5, series1, type4);    
	    dataset.addValue(5.3, series1, type5);    
	    dataset.addValue(7.1, series1, type6);    
	    dataset.addValue(7.9, series1, type7);    
	    dataset.addValue(9.0, series1, type8);    
	   
	    dataset.addValue(5.3, series2, type1);    
	    dataset.addValue(7.1, series2, type2);    
	    dataset.addValue(6.6, series2, type3);    
	    dataset.addValue(8.0, series2, type4);    
	    dataset.addValue(4.8, series2, type5);    
	    dataset.addValue(4.5, series2, type6);    
	    dataset.addValue(3.7, series2, type7);    
	    dataset.addValue(4.2, series2, type8);    
	   
	    dataset.addValue(4.3, series3, type1);    
	    dataset.addValue(6.4, series3, type2);    
	    dataset.addValue(3.5, series3, type3);    
	    dataset.addValue(4.0, series3, type4);    
	    dataset.addValue(3.6, series3, type5);    
	    dataset.addValue(3.9, series3, type6);    
	    dataset.addValue(2.5, series3, type7);    
	    dataset.addValue(3.1, series3, type8);    
	    dataset.addValue(4.0, "预警点", type4);    
	    dataset.addValue(6.4, "预警点", type2);    
	    dataset.addValue(4.3, "预警点2", type1);
	    LineEntity entity = new LineEntity();
	    
	    String[] rowKeys ={"普通会员"," 黄金会员","白金会员","钻石会员","总会员数"}; 
	    entity.setRowKeys(rowKeys);
	    String[] columnKeys = {"2008"," 2009","2010"};
	    entity.setColumnLabels(columnKeys);
	    double[][] data = {{22,33,44},{22,33,44},{22,33,44},{22,33,44},{22,33,44}};
	    entity.setData(data);
	    CategoryDataset Cdate = getDataSet(entity);
	     
	//  建立chart    
	    JFreeChart chart = ChartFactory.createLineChart(    
	        "2005年家电商品销售曲线图",       // chart title    
	        "时间",                    // domain axis label    
	        "销售额(百万)",                   // range axis label    
	        Cdate,                   // data    
	        PlotOrientation.VERTICAL,  // orientation    
	        true,                      // include legend    
	        true,                      // tooltips    
	        false                      // urls    
	    );    
	    configFont(chart);
	    SaveFileAsJPEG("d:/pie_map",chart,400,300);
	    
	//   NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...    
	    CategoryPlot plot = chart.getCategoryPlot();    
	//   customise the range axis...    
	    NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();    
	    rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());    
	    rangeAxis.setAutoRangeIncludesZero(true);    
	    rangeAxis.setUpperMargin(0.20);    
	    rangeAxis.setLabelAngle(Math.PI / 2.0);    
	   
	    LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();    
	//  renderer.setBaseShape(new Rectangle2D.Double(-1.5, -1.5, 3, 3), true);  //设置点    
	    renderer.setShape(new Rectangle2D.Double(-1.5, -1.5, 3, 3));    
	    renderer.setShapesVisible(true);    
	    renderer.setSeriesPaint(3,Color.red);    
	    renderer.setSeriesLinesVisible(3,false);    
	   
	   
	    renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());//显示折点数据    
	    renderer.setItemLabelsVisible(true);    
	        
	    ChartFrame lineFrame = new ChartFrame("折线图预警图",chart);    
	    lineFrame.pack();    
	    lineFrame.setVisible(true);    
	}     
}
