package com.framework.components.report.jfreecharts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.framework.components.report.jfreecharts.bar.BarEntity;

/**
 *  ��״ͼ����ͼ���ͳ��ͼ����
 * @author dafei
 *
 */
public final class DualBarLineChart 
{
	private DualBarLineChart(){}
	
    public static void createStackBarLine(BarEntity entity) throws Exception
    {
    	if(entity.getDataset()==null || entity.getDataset().length<1) return;
    	try
    	{
    		//��������
    		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    		String rowkey=entity.getRowKeys()[0];
    		for(int i=0;i<entity.getDataset().length;i++){
    			dataset.addValue(entity.getDataset()[i],rowkey,entity.getColumnLabels()[i]);
    		}
    		JFreeChart jfreechart=ChartFactory.createBarChart(
    				entity.getBarTitle(),entity.getXAxisTitle(),entity.getYAxisTitle(),   
    				dataset,   PlotOrientation.VERTICAL,   false,   true,   false);   
    		
    	}
    	catch(Exception e){
    		throw e;
    	}
    }
	
}
