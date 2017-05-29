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
	
	//��ɫ����ɫ����ɫ����ɫ������ɫ����ɫ����ɫ
	public final static Color[] arrColors={
		Color.red,Color.blue,Color.GREEN,Color.orange,
		Color.pink,new Color(153, 153, 255),new Color(102, 0, 102)
	};
	
	private PieCharts(){}
	
	public static void setNoDataMessage(PiePlot pieplot) 
	{   
        // ����û������ʱ��ʾ����Ϣ
        pieplot.setNoDataMessage("������");   
        // ����û������ʱ��ʾ����Ϣ������
        pieplot.setNoDataMessageFont(new Font("����", Font.ITALIC, 16));   
        // ����û������ʱ��ʾ����Ϣ����ɫ
        pieplot.setNoDataMessagePaint(Color.orange);
	 }
	
	/**
	 * ���ñ�ͼ������ɫ
	 * @param pieplot
	 * @param sectionTitles
	 * @param PkSectionTitle
	 * @param colors
	 */
	public static void setSection(PiePlot pieplot,String[] sectionTitles,String PkSectionTitle,int[] colorsIndex) 
	{   
		//����������ɫ   
		for(int i=0;i<sectionTitles.length;i++){
			pieplot.setSectionPaint(sectionTitles[i], arrColors[colorsIndex[i]]);   
		}
        //��������������ʾ   
        pieplot.setExplodePercent(PkSectionTitle, 0.2D);   
        //���������߿򲻿ɼ�   
        pieplot.setSectionOutlinesVisible(false);   
    }   
	
	private static void configFont(JFreeChart pieChart)
	{
		// ��������    
        Font kfont = new Font("����",Font.PLAIN,12) ;// �ײ�   
        Font titleFont = new Font("����", Font.BOLD , 14) ; // ͼƬ����           
        // ͼƬ����   
        pieChart.setTitle(new TextTitle(pieChart.getTitle().getText(),titleFont));         
        // �ײ�   
        pieChart.getLegend().setItemFont(kfont);   
        
        // VALUE_TEXT_ANTIALIAS_OFF��ʾ�����ֵĿ���ݹر�.   
        pieChart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,   
                RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);   
        //�õ�3D��ͼ��plot����   
        PiePlot3D piePlot = (PiePlot3D) pieChart.getPlot();   
        //������ת�Ƕ�   
        piePlot.setStartAngle(290);   
        //������ת����   
        piePlot.setDirection(Rotation.CLOCKWISE);   
        //����͸����   
        piePlot.setForegroundAlpha(0.5f);   
        piePlot.setLabelFont(kfont);  
	}
	
	/**
     * ��������
     * @param chart JFreeChart ����  
     */  
    private static void configFont(JFreeChart chart,String[] sectionTitles,String PkSectionTitle,int[] colors)
    {   
        // ��������    
        Font kfont = new Font("����",Font.PLAIN,12) ;// �ײ�   
        Font titleFont = new Font("����", Font.BOLD , 14) ; // ͼƬ����           
        // ͼƬ����   
        chart.setTitle(new TextTitle(chart.getTitle().getText(),titleFont));         
        // �ײ�   
        chart.getLegend().setItemFont(kfont);   
        
        // VALUE_TEXT_ANTIALIAS_OFF��ʾ�����ֵĿ���ݹر�.   
        chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,   
                RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
                
        PiePlot piePlot = (PiePlot)chart.getPlot();
        // ����ͼ�����򱳾���ɫ
        piePlot.setBackgroundPaint(Color.white); 
        //����������ɫ
        setSection(piePlot,sectionTitles,PkSectionTitle,colors);
        
      //����������ǩ��ʾ��ʽ���ؼ��֣�ֵ(�ٷֱ�)   
        piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}��{1}({2})"));
        //����������ǩ��ɫ   
        piePlot.setLabelBackgroundPaint(new Color(220, 220, 220));   
        piePlot.setLabelFont(kfont); 
        
        piePlot.setForegroundAlpha(0.5f);
        
//        //�趨����͸���ȣ�0-1.0֮�䣩 
//        piePlot.setBackgroundAlpha(0.6f); 
//        //�趨ǰ��͸���ȣ�0-1.0֮�䣩 
//        piePlot.setForegroundAlpha(0.8f); 
//        BarRenderer3D renderer = new BarRenderer3D(); 
//        renderer.setWallPaint(Color.lightGray);
    } 
    
    /**
     * ����ͼ�Σ����浽ָ���ļ����ļ�����Ϊpng
     *
     * @param fileName
     *            ָ�������ļ���·�����ļ���
     * @param chart
     *            JFreeChart����ʵ��
     * @param w
     *            ͼ����
     * @param h
     *            ͼ��߶�
     */
    public static void SaveFileAsPNG(String fileName, JFreeChart chart, int w, int h) throws Exception{
        // ����ͼ�Σ����浽ָ���ļ����ļ�����Ϊpng
        try{
            ChartUtilities.saveChartAsPNG(new File(fileName), chart, w, h);
        } catch (IOException e){
            // TODO Auto-generated catch block
        	 throw e;
        }
    }

    /**
     * ����ͼ�Σ����浽ָ���ļ����ļ�����Ϊjpg
     *
     * @param fileName
     *            ָ�������ļ���·�����ļ���
     * @param chart
     *            JFreeChart����ʵ��
     * @param w
     *            ͼ����
     * @param h
     *            ͼ��߶�
     */
    public static void SaveFileAsJPEG(String fileName, JFreeChart chart, int w, int h) throws Exception{
        // ����ͼ�Σ����浽ָ���ļ����ļ�����Ϊjpg
        try{
            ChartUtilities.saveChartAsPNG(new File(fileName), chart, w, h);
        } catch (IOException e){
            // TODO Auto-generated catch block
           throw e;
        }
    }

    /**
     * ����һά��״ͼ
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

			//����JFreechart����,3D״��
	    	JFreeChart chart = ChartFactory.createPieChart3D(mainEntiy.getTitle(),
	    			dataset,true,true,false);
	    	
			//��ֹ��������	
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
	 * ��ȡһ����ʾ�õļ����ݼ�����
	 * @return
	 */
	private static DefaultPieDataset getDataSet() 
	{
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("ƻ��",100);
		dataset.setValue("����",200);
		dataset.setValue("����",300);
		dataset.setValue("�㽶",400);
		dataset.setValue("��֦",500);
		return dataset;
	}
    
    public static void main(String[] args) throws IOException
    {
		DefaultPieDataset data = getDataSet();
		JFreeChart chart = ChartFactory.createPieChart3D("ˮ������ͼ",  // ͼ�����
		data, 
		true, // �Ƿ���ʾͼ��
		false,
		false
		);
		//дͼ������ļ���������״ͼ����Դ��
	}
	


}
