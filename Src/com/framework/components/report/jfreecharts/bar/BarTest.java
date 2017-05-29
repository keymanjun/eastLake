package com.framework.components.report.jfreecharts.bar;

import java.util.ArrayList;
import java.util.List;

import com.framework.components.report.jfreecharts.TNewstopic;
import com.framework.components.report.jfreecharts.line.LineChart;
import com.framework.components.report.jfreecharts.line.LineEntity;

/**
 * 测试封装的生成柱状工具类:BarCharts.class
 * @author dafei
 *
 */
public class BarTest {

	public void testSampleBar() throws Exception
	{
		List arrList=new ArrayList();
		TNewstopic netinfo=new TNewstopic();
		netinfo.setTopic("苹果");
		netinfo.setNtNpointnum(new Integer(300));
		arrList.add(netinfo);
		
		netinfo=new TNewstopic();
		netinfo.setTopic("梨子");
		netinfo.setNtNpointnum(new Integer(200));
		arrList.add(netinfo);
		
		netinfo=new TNewstopic();
		netinfo.setTopic("香蕉");
		netinfo.setNtNpointnum(new Integer(500));
		arrList.add(netinfo);
		
		netinfo=new TNewstopic();
		netinfo.setTopic("芒果");
		netinfo.setNtNpointnum(new Integer(340));
		arrList.add(netinfo);
		
		BarEntity bean=new BarEntity();
		String[] rowkeys={"广州"};
		bean.setRowKeys(rowkeys);
		bean.setBarTitle("水果销量统计");
		bean.setXAxisTitle("水果");
		bean.setYAxisTitle("销量");
		bean.setIsSavePngImg(false);
		bean.setImageFilename("d://sampleBar.jpeg");
		
		String[] columns={"getTopic","getPointNum"};
		bean.conv2SampleBarData(arrList, columns, bean);
		BarCharts.createSampleBar(bean);
	   
	}
	
	public void testMuiltBar() throws Exception
	{
		List arrList=new ArrayList();
		TNewstopic netinfo=new TNewstopic();
		netinfo.setTopic("一月");
		netinfo.setNtNpointnum(new Integer(300));
		netinfo.setNtNreviewnum(new Integer(100));
		arrList.add(netinfo);
		
		netinfo=new TNewstopic();
		netinfo.setTopic("二月");
		netinfo.setNtNpointnum(new Integer(200));
		netinfo.setNtNreviewnum(new Integer(120));
		arrList.add(netinfo);
		
		netinfo=new TNewstopic();
		netinfo.setTopic("三月");
		netinfo.setNtNpointnum(new Integer(500));
		netinfo.setNtNreviewnum(new Integer(80));
		arrList.add(netinfo);
		
		netinfo=new TNewstopic();
		netinfo.setTopic("四月");
		netinfo.setNtNpointnum(new Integer(340));
		netinfo.setNtNreviewnum(new Integer(120));
		arrList.add(netinfo);
		
		BarEntity bean=new BarEntity();
		String[] rowkeys={"好评","中评","差评"};
		bean.setRowKeys(rowkeys);
		bean.setBarTitle("水果评论统计");
		bean.setXAxisTitle("月份");
		bean.setYAxisTitle("评论数");
		bean.setIsSavePngImg(false);
		bean.setImageFilename("d://muiltBar.jpeg");
		
		String[] columns={"getTopic","getPointNum","getReviewNum"};
		bean.conv2MuiltBarData(arrList, columns,bean);
		BarCharts.createMuiltBar(bean);
	}
	
	private List getDataList() throws Exception{
		List arrList=new ArrayList();
		TNewstopic netinfo=new TNewstopic();
		netinfo.setTopic("一月");
		netinfo.setNtNpointnum(new Integer(300));
		netinfo.setNtNreviewnum(new Integer(100));
		arrList.add(netinfo);
		
		netinfo=new TNewstopic();
		netinfo.setTopic("二月");
		netinfo.setNtNpointnum(new Integer(200));
		netinfo.setNtNreviewnum(new Integer(120));
		arrList.add(netinfo);
		
		netinfo=new TNewstopic();
		netinfo.setTopic("三月");
		netinfo.setNtNpointnum(new Integer(500));
		netinfo.setNtNreviewnum(new Integer(80));
		arrList.add(netinfo);
		
		netinfo=new TNewstopic();
		netinfo.setTopic("四月");
		netinfo.setNtNpointnum(new Integer(220));
		netinfo.setNtNreviewnum(new Integer(320));
		arrList.add(netinfo);
		return arrList;
	}
	
	public void testStackBar() throws Exception
	{
		List arrList=getDataList();
		
		BarEntity bean=new BarEntity();
		String[] rowkeys={"访问量","评论数"};
		bean.setRowKeys(rowkeys);
		bean.setBarTitle("水果评论统计");
		//bean.setXAxisTitle("月份");
		//bean.setYAxisTitle("评论数");
		bean.setIsSavePngImg(false);
		bean.setImageFilename("d://stackBar.jpeg");
		
		String[] columns={"getNtStopic","getNtNpointnum","getNtNreviewnum"};
		bean.conv2MuiltBarData(arrList, columns,bean);
		BarCharts.createStackedBarChart(bean);
	}
	
	public void createDualStackedBarLine() throws Exception
	{
		List arrList=getDataList();
		BarEntity bean=new BarEntity();
		String[] rowkeys={"访问量","评论数"};
		bean.setRowKeys(rowkeys);
		bean.setBarTitle("水果评论统计");
		//bean.setXAxisTitle("月份");
		//bean.setYAxisTitle("评论数");
		bean.setIsSavePngImg(false);
		bean.setImageFilename("d://linestackBar.jpeg");
		String[] columns={"getNtStopic","getNtNpointnum","getNtNreviewnum"};
		bean.conv2MuiltBarData(arrList, columns,bean);
		
		LineEntity lineEntity=new LineEntity();
		lineEntity.setRowKeys(rowkeys);
		lineEntity.conv2MuiltLineData(arrList, columns, lineEntity);
		
		BarCharts.createDualStackedBarLine(bean,LineChart.getDataSet(lineEntity));
	}
	
	public static void main(String[] args) throws Exception{
		BarTest bartest=new BarTest();
		//测试通过
		//bartest.testSampleBar();
		//测试通过
		bartest.testMuiltBar();
		
		//测试通过
		/*bartest.testStackBar();*/
		
		//测试通过
//		bartest.createDualStackedBarLine();
	}
}
