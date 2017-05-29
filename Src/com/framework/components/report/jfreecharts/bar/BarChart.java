package com.framework.components.report.jfreecharts.bar;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;

//import com.iss.report.jfreechart.commom.ImageInfo;


public class BarChart {
	
	/**
     * 配置字体
     * @param chart JFreeChart 对象  
     */  
    private static void configFont(JFreeChart chart){   
        // 配置字体   
        Font xfont = new Font("宋体",Font.PLAIN,12) ;// X轴   
        Font yfont = new Font("宋体",Font.PLAIN,9) ;// Y轴   
        Font kfont = new Font("宋体",Font.PLAIN,9) ;// 底部   
        Font titleFont = new Font("宋体", Font.BOLD , 15) ; // 图片标题
        CategoryPlot plot = chart.getCategoryPlot();// 图形的绘制结构对象
        // 目录轴网格是否可见
        plot.setDomainGridlinesVisible(false);
        // 目录轴网格线的颜色
        plot.setDomainGridlinePaint(Color.red);
        // 数据轴网格是否可见
        plot.setRangeGridlinesVisible(true);
        // 数据轴网格线的颜色
        plot.setRangeGridlinePaint(Color.blue);
        // 设置图像区域背景颜色
        //plot.setBackgroundPaint(Color.white);
        plot.setBackgroundAlpha(0f);   
        // 图片标题   
        chart.setTitle(new TextTitle(chart.getTitle().getText(),titleFont)); 
         
        chart.setBackgroundPaint(new Color(206,235,255));
        
        // 底部   
        chart.getLegend().setItemFont(kfont);
		// 数据轴精度 
	    //NumberAxis vn = (NumberAxis) plot.getRangeAxis(); 
	    // 设置最大值是1 
	    //vn.setUpperBound(1);	   
	   // DecimalFormat df = new DecimalFormat("0.00%"); 
	   // vn.setNumberFormatOverride(df); // 数据轴数据标签的显示格式 
		
        // X 轴
        CategoryAxis domainAxis = plot.getDomainAxis(); 
        domainAxis.setLabelFont(xfont);// 轴标题   
        domainAxis.setTickLabelFont(xfont);// 轴数值     
        domainAxis.setMaximumCategoryLabelWidthRatio(1.0f);// 横轴上的 Lable 是否完整显示 
        domainAxis.setTickLabelPaint(Color.BLACK) ; // 字体颜色   
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45); // 横轴上的label斜显示    
        // 倾斜（2）Lable（Math.PI 3.0）度倾斜 
		// domainAxis.setCategoryLabelPositions(CategoryLabelPositions 
		// .createUpRotationLabelPositions(Math.PI / 3.0));
        plot.setDomainAxis(domainAxis); 
        
        // Y 轴
        ValueAxis rangeAxis = plot.getRangeAxis();      
        rangeAxis.setLabelFont(yfont);    
        //rangeAxis.setLabelPaint(Color.BLUE) ; // 字体颜色         
        // 设置最高的一个 Item 与图片顶端的距离 
	    //rangeAxis.setUpperMargin(0.15); 
	    // 设置最低的一个 Item 与图片底端的距离 
		//rangeAxis.setLowerMargin(0.15); 
        rangeAxis.setTickLabelFont(yfont);
        plot.setRangeAxis(rangeAxis); 
        
        //设置柱图属性
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setMaximumBarWidth(0.1); 
	    // 设置柱子高度 
	    renderer.setMinimumBarLength(0.1); 
	    //设置柱的边框颜色 
	    renderer.setBaseOutlinePaint(Color.BLACK); 
	    //设置柱的边框可见 
	    renderer.setDrawBarOutline(true);
	    //设置柱的颜色(可设定也可默认) 
        renderer.setSeriesPaint(0, new Color(204, 255, 204)); 
		renderer.setSeriesPaint(1, new Color(255, 204, 153)); 
		renderer.setSeriesPaint(2, Color.green); 
		renderer.setSeriesPaint(3, Color.red); 
		renderer.setSeriesPaint(4, Color.blue); 
		renderer.setSeriesPaint(5, Color.orange); 
		renderer.setSeriesPaint(6, Color.pink); 
        // 设置每个地区所包含的平行柱的之间距离
        renderer.setItemMargin(0.02);
        plot.setRenderer(renderer);         
        // 设置柱的透明度(如果是3D的必须设置才能达到立体效果，如果是2D的设置则使颜色变淡) 
		//plot.setForegroundAlpha(0.5f);
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
    public static void SaveFileAsPNG(String fileName, JFreeChart chart, int w, int h, ChartRenderingInfo info) throws Exception{
        // 生成图形，保存到指定文件，文件类型为png
        try{
            ChartUtilities.saveChartAsPNG(new File(fileName), chart, w, h, info);
        } catch (IOException e){
            // TODO Auto-generated catch block
        	 throw e;
        }
    }
    
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
        // 生成图形，保存到web临时目录中，文件类型为png
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
    public static String SaveFileAsJPEG(String file, JFreeChart chart, int width, int height, ChartRenderingInfo info) throws Exception{
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
    
    public static void SaveFileAsJPEG(String fileName, JFreeChart chart, int w, int h) throws Exception{
        // 生成图形，保存到指定文件，文件类型为jpg
        try{
            ChartUtilities.saveChartAsPNG(new File(fileName), chart, w, h);
        } catch (IOException e){
            // TODO Auto-generated catch block
           throw e;
        }
    }
    
    public static String SaveFileAsJPEG4Web(String fileName, JFreeChart chart, int w, int h, ChartRenderingInfo info) throws Exception{
        // 生成图形，保存到指定文件，文件类型为jpg
        try{
        	return ServletUtilities.saveChartAsJPEG(chart, w, h, info,
					null);// 生成图片
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
   /* public static ImageInfo createSampleBar(BarEntity entity) throws Exception
	{
    	ImageInfo imageInfo = new ImageInfo();
    	
    	if(entity.getDataset()==null || entity.getDataset().length<1) return null;
    	try
    	{
    		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    		String rowkey=entity.getRowKeys()[0];
    		for(int i=0;i<entity.getDataset().length;i++){
    			if(entity.getColumnLabels()[i]==null || "".equals(entity.getColumnLabels()[i])) continue;
    			dataset.addValue(entity.getDataset()[i],rowkey,entity.getColumnLabels()[i]);
    		}

			//创建JFreechart对象,3D状的
	    	JFreeChart chart = ChartFactory.createBarChart3D(
	    				  entity.getBarTitle(),entity.getXAxisTitle(),entity.getYAxisTitle(),
		                  dataset,PlotOrientation.VERTICAL,true,true,false);
			//防止中文乱码		
	        configFont(chart); 
	        
	        ChartRenderingInfo info = new ChartRenderingInfo(
    				new StandardEntityCollection());
	        String filename = null;
	        if(entity.getIsSavePngImg()){	        	
	        	//filename = SaveFileAsPNG(ImageInfo.getFilepath(),chart,entity.getWidth(),entity.getHeight(), info);	        	
	        }else {
	        	filename = SaveFileAsJPEG(ImageInfo.getFilepath(),chart,entity.getWidth(),entity.getHeight(), info);
	        }
	        imageInfo.setFilename(filename);
        	String strMap = ChartUtilities.getImageMap(filename, info);
        	imageInfo.setStrMap(strMap);
    
	    }
    	catch(Exception e){
    		throw e;
    	}
    	return imageInfo;
	}
    */
    
    /**
     * 创建多维柱状图
     * @param entity
     * @throws Exception
     */
  /*  public static ImageInfo createMuiltBar(BarEntity entity) throws Exception
	{
    	ImageInfo imageInfo = new ImageInfo();
    	
    	if(entity.getData()==null || entity.getData().length<1) return  null;
    	try
    	{

			CategoryDataset dataset = DatasetUtilities.createCategoryDataset(
					entity.getRowKeys(), entity.getColumnLabels(), entity.getData()); 
	
			//创建JFreechart对象,3D状的
			JFreeChart chart = ChartFactory.createBarChart3D(
					entity.getBarTitle(),entity.getXAxisTitle(),entity.getYAxisTitle(),
			                  dataset,PlotOrientation.VERTICAL,true,true,false);
			//防止中文乱码	
	        configFont(chart);
	
	        ChartRenderingInfo info = new ChartRenderingInfo(
    				new StandardEntityCollection());
	        String filename = null;
	        if(entity.getIsSavePngImg()){	        	
	        	//filename = SaveFileAsPNG(ImageInfo.getFilepath(),chart,entity.getWidth(),entity.getHeight(), info);	        	
	        }else {
	        	filename = SaveFileAsJPEG(ImageInfo.getFilepath(),chart,entity.getWidth(),entity.getHeight(), info);
	        }
	        imageInfo.setFilename(filename);
        	String strMap = ChartUtilities.getImageMap(filename, info);
        	imageInfo.setStrMap(strMap);
    	}
    	catch(Exception e){
    		throw e;
    	}
    	return imageInfo;
	}
    */
    /**
     * 堆积柱状图
     * @param entity
     * @throws Exception
     */
    public static void createStackedBarChart(BarEntity entity) throws Exception
    { 
    	if(entity.getData()==null || entity.getData().length<1) return;
    	try
    	{
    		CategoryDataset dataset = DatasetUtilities.createCategoryDataset(
    				entity.getRowKeys(), entity.getColumnLabels(), entity.getData());
    		
    		//创建JFreechart对象,3D状的
			JFreeChart chart = ChartFactory.createStackedBarChart(
					entity.getBarTitle(),entity.getXAxisTitle(),entity.getYAxisTitle(),
			                  dataset,PlotOrientation.VERTICAL,true,true,false);
		   configFont(chart);
		   if(entity.getIsSavePngImg()){
	        	SaveFileAsPNG(entity.getImageFilename(),chart,entity.getWidth(),entity.getHeight());
	        	return;
	        }
	        SaveFileAsJPEG(entity.getImageFilename(),chart,entity.getWidth(),entity.getHeight());
    	}
		catch(Exception e){
			throw e;
		}
    }
    
    /**
     * 设置柱状态图与线图的混合分析图
     * @param entity
     * @param lineEntity
     * @throws Exception
     */
    public static void createDualStackedBarLine(BarEntity entity,CategoryDataset lineDataset) throws Exception
    {
    	if(entity.getData()==null || entity.getData().length<1) return;
    	try
    	{
    		CategoryDataset dataset = DatasetUtilities.createCategoryDataset(
    				entity.getRowKeys(), entity.getColumnLabels(), entity.getData());
    		
    		//创建JFreechart对象
			JFreeChart chart = ChartFactory.createStackedBarChart(
					entity.getBarTitle(),entity.getXAxisTitle(),entity.getYAxisTitle(),
			                  dataset,PlotOrientation.VERTICAL,true,true,false);
			configFont(chart);
			
			CategoryPlot   categoryplot =(CategoryPlot)chart.getPlot();
						
			categoryplot.setDataset(1,   lineDataset);
	        categoryplot.mapDatasetToRangeAxis(1,   1);
	        CategoryAxis   categoryaxis   =   categoryplot.getDomainAxis();   
	        categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
	        //设置折线纵坐标
	        NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis(); 
	        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits()); 
	        numberaxis.setAutoRangeIncludesZero(true); 
	        categoryplot.setRangeAxis(1,   numberaxis);
	        
	        LineAndShapeRenderer   lineandshaperenderer   =   new   LineAndShapeRenderer();   
	        lineandshaperenderer.setToolTipGenerator(new   StandardCategoryToolTipGenerator());   
	        categoryplot.setRenderer(1,   lineandshaperenderer);   
	        categoryplot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
	        /*
	        LegendTitle   legendtitle   =   new   LegendTitle(categoryplot.getRenderer(0));   
	        legendtitle.setMargin(new   RectangleInsets(2D,   2D,   2D,   2D));   
	        legendtitle.setBorder(new   BlockBorder());
	        LegendTitle   legendtitle1   =   new   LegendTitle(categoryplot.getRenderer(1));   
	        legendtitle1.setMargin(new   RectangleInsets(2D,   2D,   2D,   2D));   
	        legendtitle1.setBorder(new   BlockBorder());   
	        BlockContainer   blockcontainer   =   new   BlockContainer(new BorderArrangement());   
	        blockcontainer.add(legendtitle,   RectangleEdge.LEFT);   
	        blockcontainer.add(legendtitle1,   RectangleEdge.RIGHT);   
	        
	        blockcontainer.add(new   EmptyBlock(2000D,   0.0D));   
	        CompositeTitle   compositetitle   =   new   CompositeTitle(blockcontainer);   
	        compositetitle.setPosition(RectangleEdge.BOTTOM);   
	        chart.addSubtitle(compositetitle);
	        */
	        
	        //输出成图片
	        if(entity.getIsSavePngImg()){
	        	SaveFileAsPNG(entity.getImageFilename(),chart,entity.getWidth(),entity.getHeight());
	        	return;
	        }
	        SaveFileAsJPEG(entity.getImageFilename(),chart,entity.getWidth(),entity.getHeight());
    	}
    	catch(Exception e){
			throw e;
		}
    }
}

