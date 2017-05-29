package com.framework.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

/**
 * @author pliu
 *         <p>
 *         尽可能项工具类。
 *         </p>
 *         <p>
 *         这个类中的方法没什么可以分组解释，全是一些开发过程中用到的杂其杂八的方法。
 *         </p>
 */
public final class MiscUtil
{
	public static String formatDigit(long l, int width)
	{
		String t = l + "";
		if (t.length() < width)
		{
			int count = width - t.length();
			for (int i = 0; i < count; ++i)
			{
				t = "0" + t;
			}
		}
		return t;
	}

	public static String formatDigit(int i, int w)
	{
		long l = (long) i;
		return formatDigit(l, w);
	}

	public static String formatDigit(String s, int w)
	{
		long l = 0L;
		try
		{
			l = Long.parseLong(s);
		}
		catch (Exception e)
		{
		}
		return formatDigit(l, w);
	}


	/**
	 * FMT_DATE_YYYY_MM_DD 返回 2003-01-01 FMT_DATE_YYYYMMDD 返回 20030101 the
	 * following const is to define date format.
	 */
	public static final int FMT_DATE_YYYY_MM_DD = 1;

	public static final int FMT_DATE_YYYYMMDD = 2;

	public static final int FMT_DATE_YYMMDD = 3;

	public static final int FMT_DATE_YYYY = 4;

	public static final int FMT_DATE_YYMM = 5;

	public static final int FMT_DATE_YYYYMM = 6;

	public static final int FMT_DATE_YYYYMMDDHHmmss = 7;

	public static final int FMT_DATE_YYYYMMDDHHmmss1 = 8;

	public static final int FMT_DATE_YYYYMMDD9 = 9;

	public static final int FMT_DATE_MMDD = 10;

	public static final int FMT_TIME_HH = 11;

	public static final int FMT_TIME_mm = 12;

	public static final int FMT_DATE_YYYYMM2 = 13;

	/**
	 * this function is to format date into a string @ param date the date to be
	 * formatted.
	 * @param nFmt
	 *            FMT_DATE_YYYYMMDD to format string like 'yyyy-mm-dd' or to
	 *            format string like 'yyyy-mm-dd hh:mm:ss'
	 * @return String
	 */
	public static String formatDate(Date date, int nFmt)
	{
		if (null == date)
		{
			return "";
		}
		SimpleDateFormat fmtDate = null;
		switch (nFmt)
		{
			default :
			case MiscUtil.FMT_DATE_YYYY_MM_DD :
				fmtDate = new SimpleDateFormat("yyyy-MM-dd");
				break;
			case MiscUtil.FMT_DATE_YYYYMMDD :
				fmtDate = new SimpleDateFormat("yyyyMMdd");
				break;
			case MiscUtil.FMT_DATE_YYMMDD :
				fmtDate = new SimpleDateFormat("yyMMdd");
				break;
			case MiscUtil.FMT_DATE_YYYY :
				fmtDate = new SimpleDateFormat("yyyy");
				break;
			case MiscUtil.FMT_DATE_YYMM :
				fmtDate = new SimpleDateFormat("yyMM");
				break;
			case MiscUtil.FMT_DATE_YYYYMM :
				fmtDate = new SimpleDateFormat("yyyyMM");
				break;
			case MiscUtil.FMT_DATE_YYYYMMDDHHmmss :
				fmtDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				break;
			case MiscUtil.FMT_DATE_YYYYMMDDHHmmss1 :
				fmtDate = new SimpleDateFormat("yyyyMMddHHmmss");
				break;
			case MiscUtil.FMT_DATE_YYYYMMDD9 :
				fmtDate = new SimpleDateFormat("yyyy/MM/dd");
				break;
			case MiscUtil.FMT_DATE_MMDD :
				fmtDate = new SimpleDateFormat("MMdd");
				break;
			case MiscUtil.FMT_TIME_HH :
				fmtDate = new SimpleDateFormat("HH");
				break;
			case MiscUtil.FMT_TIME_mm :
				fmtDate = new SimpleDateFormat("mm");
				break;
			case MiscUtil.FMT_DATE_YYYYMM2 :
				fmtDate = new SimpleDateFormat("yyyy/MM");
				break;
		}
		return fmtDate.format(date);
	}

	/**
	 * 从字段串转化为日期类型格式
	 * 
	 * @param strDate
	 * @return Date
	 */
	public static Date getDateByString(String strDate, int nFmt)
	{
		SimpleDateFormat fmtDate;
		java.util.Date dDate = null;
		switch (nFmt)
		{
			default :
			case MiscUtil.FMT_DATE_YYYY_MM_DD :
				fmtDate = new SimpleDateFormat("yyyy-MM-dd");
				break;
			case MiscUtil.FMT_DATE_YYYYMMDD :
				fmtDate = new SimpleDateFormat("yyyyMMdd");
				break;
			case MiscUtil.FMT_DATE_YYMMDD :
				fmtDate = new SimpleDateFormat("yyMMdd");
				break;
			case MiscUtil.FMT_DATE_YYYY :
				fmtDate = new SimpleDateFormat("yyyy");
				break;
			case MiscUtil.FMT_DATE_YYMM :
				fmtDate = new SimpleDateFormat("yyMM");
				break;
			case MiscUtil.FMT_DATE_YYYYMM :
				fmtDate = new SimpleDateFormat("yyyyMM");
				break;
			case MiscUtil.FMT_DATE_YYYYMMDDHHmmss :
				fmtDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				break;
			case MiscUtil.FMT_DATE_YYYYMMDDHHmmss1 :
				fmtDate = new SimpleDateFormat("yyyyMMddHHmmss");
				break;
			case MiscUtil.FMT_DATE_YYYYMM2 :
				fmtDate = new SimpleDateFormat("yyyy/MM");
				break;
		}
		try
		{
			dDate = fmtDate.parse(strDate);
		}
		catch (Exception e)
		{
			dDate = null;
		}
		return dDate;
	}


	/**
	 * 得到上 N 个月.
	 * 
	 * @author weiliao
	 * @param String
	 *            如 200402
	 * @param int
	 *            如 3
	 * @return String 得到如 200311
	 */
	static public String getPreviousAppointMonth(String strThisMonth, int lAppointMonth)
	{
		String strYear = "";
		String strMonth = "";
		long lYear = 0;
		long lMonth = 0;
		String strReturn = "";
		try
		{
			if (!"".equals(strThisMonth))
			{
				// 转换之前的 year 和 month
				strYear = strThisMonth.substring(0, 4);
				strMonth = strThisMonth.substring(4, 6);
				lYear = Long.valueOf(strYear).longValue();
				lMonth = Long.valueOf(strMonth).longValue();
				// 转换中的
				lMonth = lMonth - lAppointMonth;
				while (lMonth <= 0)
				{
					lMonth = lMonth + 12;
					lYear = lYear - 1;
				}
				// 转换后的
				if (lMonth < 10)
				{
					strMonth = "0" + lMonth;
				}
				else
				{
					strMonth = String.valueOf(lMonth);
				}
				strReturn = String.valueOf(lYear) + strMonth;
			}
		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}
		return strReturn;
	}

	/**
	 * 得到上一个月.
	 * 
	 * @author weiliao
	 * @param String
	 *            如 200401
	 * @return String 得到如 200312
	 */
	static public String getPreviousMonth(String strThisMonth)
	{
		String strYear = "";
		String strMonth = "";
		String strReturn = "";
		try
		{
			if (!"".equals(strThisMonth))
			{
				strYear = strThisMonth.substring(0, 4);
				strMonth = strThisMonth.substring(4, 6);
				// strReturn = strYear + strMonth;
				if ("01".equals(strMonth))
				{
					strMonth = "12";
					strYear = String.valueOf(Long.valueOf(strYear).longValue() - 1);
				}
				else
				{
					strMonth = String.valueOf(Long.valueOf(strMonth).longValue() - 1);
					if (strMonth.length() < 2)
					{
						strMonth = "0" + strMonth;
					}
				}
			}
			strReturn = strYear + strMonth;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return strReturn;
	}

	/**
	 * 得到去年的１２月份.
	 * 
	 * @author weiliao
	 * @param String
	 *            如 200401
	 * @return String 得到如 200312
	 */
	static public String getPreviousDec(String strThisMonth)
	{
		return (Long.parseLong(strThisMonth.substring(0, 4)) - 1) + "12";
	}

	/**
	 * 计算N天之后的日期
	 * 
	 * @param bgdate
	 * @param days
	 * @return
	 */
	public static Date getAfterDate(Date bgdate, int days)
	{
		long Time = (bgdate.getTime() / 1000) + 60 * 60 * 24 * days;
		Date ret = new Date();
		ret.setTime(Time * 1000);
		return ret;
	}	
	
	/**
	 * 计算N天之前的日期
	 * @return
	 */
    public static Date getBeforeDay(String cdate,int days) throws Exception
    {
    	if(days<1) return new Date();
    	BeforeDate bfdate=new BeforeDate(cdate.replaceAll("-", ""),days);
    	return getDateByString(bfdate.getBeforeDayString(),2);    	
    }
	
	/**
	 * 得到二个日期这之间的天数相隔
	 * 
	 * @param rq1
	 * @param rq2
	 * @return
	 */
	public static long DaysBetween(Date bgdate, Date enddate)
	{
		long beginTime = bgdate.getTime();
		long endTime = enddate.getTime();
		long days = (long) ((endTime - beginTime) / (1000 * 60 * 60 * 24) + 0.5);
		return days;
	}

	/**
	 * 得到周日期
	 * @param sdate
	 * @return
	 * @throws Exception
	 */
	public static String[] getWeekDate(String sdate) throws Exception
	{
		String[] dates=new String[2];
		int wdnum=MiscUtil.getWeekNum(sdate);
		if(wdnum==1){
			dates[0]=sdate;
			dates[1]=MiscUtil.formatDate(MiscUtil.getAfterDate(MiscUtil.getDateByString(sdate, 1),6),1);
		}else{
			dates[0]=MiscUtil.formatDate(MiscUtil.getBeforeDay(sdate,wdnum-1),1);
			dates[1]=MiscUtil.formatDate(MiscUtil.getAfterDate(MiscUtil.getDateByString(dates[0], 1),6),1);
		}
		return dates;
	}
	
	/**
	 * 得到当前系统时间的分钟
	 * 
	 * @return
	 */
	public static int getIntMinuteByToday()
	{

		Date dtToday = getDateByString(formatDate(new Date(), FMT_DATE_YYYYMMDDHHmmss), FMT_DATE_YYYYMMDDHHmmss);
		Calendar cal = Calendar.getInstance();
		cal.setTime(dtToday);
		int hour = cal.get(Calendar.MINUTE);
		return hour;
	}

	/**
	 * 根据传进来的日期格式字符串得到星期几
	 * 
	 * @param dt
	 * @return
	 */
	public static String getWeekByString(String dt)
	{
		Date dtToday = getDateByString(dt, FMT_DATE_YYYYMMDD);
		Calendar cal = Calendar.getInstance();
		cal.setTime(dtToday);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		return getWeekNum(day);
	}

	public static int getWeekNum(String dt){
		Date dtToday = getDateByString(dt, FMT_DATE_YYYYMMDD);
		Calendar cal = Calendar.getInstance();
		cal.setTime(dtToday);
		return cal.get(Calendar.DAY_OF_WEEK);
	}
	
	/**
	 * 根据传进来的日期格式得到星期几
	 * 
	 * @param dt
	 * @return
	 */
	public static String getWeekByDate(Date dt)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		return getWeekNum(day);
	}

	/**
	 * 根据数字返回星期几字符串
	 * 
	 * @param day
	 * @return
	 */
	public static String getWeekNum(int day)
	{
		String weekNum = "星期";
		switch (day)
		{
			case 1 :
				weekNum += "一";
				break;
			case 2 :
				weekNum += "二";
				break;
			case 3 :
				weekNum += "三";
				break;
			case 4 :
				weekNum += "四";
				break;
			case 5 :
				weekNum += "五";
				break;
			case 6 :
				weekNum += "六";
				break;
			case 7 :
				weekNum += "日";
				break;
		}
		return weekNum;
	}

	//获取下一周的日期

	public Date nextWeek(Date currentDate) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(currentDate);
		cal.add(GregorianCalendar.DATE, 7);//在日期上加7天
		return cal.getTime();

	}

	 

	//获取本周日的日期
	public Date getSunday(Date monday) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(monday);
		cal.add(GregorianCalendar.DATE, 6);//在日期上加6天
		return cal.getTime();

	}
	
	/**
	 * 转义字符
	 * 
	 * @param str
	 * @return String
	 * @throws Exception
	 */
	public static String transferString(String str) throws Exception
	{
		StringBuffer sbResult = new StringBuffer();
		char[] cFilter = { '\'', '\"', '\\' };
		if ((str != null) && (!str.equals("")))
		{
			for (int i = 0; i < str.length(); i++)
			{
				for (int j = 0; j < cFilter.length; j++)
				{
					if (str.charAt(i) == cFilter[j])
					{
						sbResult.append("\\");
					}
				}
				sbResult.append(str.charAt(i));
			}
		}
		return sbResult.toString();
	}
	

	/**
	 * 格式化列表的金额
	 * 
	 * @param dAmount
	 *            金额
	 * @param lSacle
	 *            jingdu
	 * @return
	 */
	public static String formatAmount(double dAmount, int lSacle)
	{
		String strData = new java.math.BigDecimal(java.lang.Math.abs(dAmount)).setScale(lSacle, java.math.BigDecimal.ROUND_HALF_UP).toString();
		// 将小数点前和后的数据分别取出来
		int nPoint;
		nPoint = strData.indexOf(".");
		String strFront = new String(strData), strEnd = "";
		if (nPoint != -1)
		{
			strFront = strData.substring(0, nPoint);
			strEnd = strData.substring(nPoint + 1, strData.length());
		}
		String strTemp = "";
		// 小数点前面的数据加","
		strTemp = new String(strFront);
		strFront = "";
		int nNum, i;
		nNum = 0;
		for (i = strTemp.length() - 1; i >= 0; i--)
		{
			if (nNum == 3)
			{
				strFront = "," + strFront;
				nNum = 0;
			}
			nNum++;
			char cData;
			cData = strTemp.charAt(i);
			strFront = cData + strFront;
		}

		// 如果需要小数位
		if (lSacle > 0)
		{
			strData = strFront + "." + strEnd;
		}
		// 否则
		else
		{
			strData = strFront;
		}
		if (dAmount < 0 && !strData.equals("0.00"))
		{
			strData = "-" + strData;
		}
		return strData;
	}

	/**
	 * 格式化整数 返回 lScale 位数的整数，不足前面补零 比如 (33,3) 得到 "033"
	 * 
	 * @param dAmount
	 *            金额
	 * @param lSacle
	 *            jingdu
	 * @return
	 */
	public static String formatAmountInt(int dAmount, int lScale)
	{
		String strTmp = "";
		StringBuffer strReturn = new StringBuffer();
		int iTmp = 0;
		strTmp = String.valueOf(dAmount);
		if ((iTmp = strTmp.length()) < lScale)
		{
			for (int i = 0; i < lScale - iTmp; i++)
			{
				strReturn.append("0");
			}
		}
		strReturn.append(strTmp);
		return strReturn.toString();
	}

	/**
	 * 格式化列表的金额(不带千分位",") 主要用于报表的显示
	 * 
	 * @param dAmount
	 *            金额
	 * @param lSacle
	 *            jingdu
	 * @return
	 */
	public static String formatAmount2(double dAmount, int lSacle)
	{
		// 单dAmount==0.0时,返回空串
		if (dAmount > -0.000000001 && dAmount < 0.0000001)
			return "";
		String strData = new java.math.BigDecimal(java.lang.Math.abs(dAmount)).setScale(lSacle, java.math.BigDecimal.ROUND_HALF_UP).toString();
		// 将小数点前和后的数据分别取出来
		int nPoint;
		nPoint = strData.indexOf(".");
		String strFront = new String(strData), strEnd = "";
		if (nPoint != -1)
		{
			strFront = strData.substring(0, nPoint);
			strEnd = strData.substring(nPoint + 1, strData.length());
		}
		String strTemp = "";
		// 小数点前面的数据加","
		strTemp = new String(strFront);
		strFront = "";
		int i;
		for (i = strTemp.length() - 1; i >= 0; i--)
		{
			char cData;
			cData = strTemp.charAt(i);
			strFront = cData + strFront;
		}
		// 如果需要小数位
		if (lSacle > 0)
		{
			strData = strFront + "." + strEnd;
		}
		// 否则
		else
		{
			strData = strFront;
		}
		if (dAmount < 0 && !strData.equals("0.00"))
		{
			strData = "-" + strData;
		}
		return strData;
	}

	/**
	 * 格式化列表的金额,带千分号，如果为零则返回空
	 * 
	 * @author weiliao
	 * @param dAmount
	 *            金额
	 * @param lSacle
	 *            jingdu
	 * @return
	 */
	public static String formatAmount3(double dAmount, int lSacle)
	{
		return (dAmount == 0.0) ? "" : MiscUtil.formatAmount(dAmount, lSacle);
	}

	/**
	 * 反向格式化金额，将","去掉
	 * 
	 * @param strData
	 *            数据
	 */
	public static String reverseFormatAmount(String strData)
	{
		int i;
		String strTemp;
		// 去掉所有的","
		strTemp = new String(strData);
		strData = "";
		for (i = 0; i < strTemp.length(); i++)
		{
			char cData;
			cData = strTemp.charAt(i);
			if (cData != ',')
			{
				strData = strData + cData;
			}
		}
		return strData;
	}

	/**
	 * 反向格式化金额，如果有 "," 将","去掉 返回 double.
	 * 
	 * @param strData
	 *            数据
	 */
	public static double reverseFormatAmountTodouble(String strData)
	{
		if (strData == null || "".equals(strData))
		{
			return 0;
		}
		return Double.parseDouble(reverseFormatAmount(strData));
	}

	/** 小数点后补充修订值 */
	private static double getDouble(int iValue)
	{
		double dTempValue = 1;
		for (int i = 0; i < iValue; i++)
		{
			dTempValue = dTempValue / 10;
		}
		return dTempValue;
	}

	/**
	 * double类型数据格式化（四舍五入）
	 * 
	 * @param dValue
	 *            被四舍五入的值
	 * @param lScale
	 *            精度 Method format.
	 * @param dValue
	 * @param lScale
	 * @return String
	 */
	public static double doubleRound(double dValue, int lScale)
	{
		double dValue1 = Math.abs(dValue);
		BigDecimal bd1 = new BigDecimal(Double.toString(dValue1));
		BigDecimal bd2 = new BigDecimal(Double.toString(getDouble(lScale + 4)));
		BigDecimal bd;
		bd = bd1.add(bd2);
		bd = bd.setScale(lScale, BigDecimal.ROUND_HALF_UP);
		if (dValue >= 0)
			return bd.doubleValue();
		else
			return bd.doubleValue() * -1;
	}

	/**
	 * double类型数据格式化（四舍五入）,反回无小数字符串
	 * 
	 * @param dValue
	 *            被四舍五入的值
	 * @param lScale
	 *            精度 Method format.
	 * @param dValue
	 * @param lScale
	 * @return String
	 */
	public static String stringFromdoubleRound(double dValue, int lScale)
	{
		DecimalFormat df = new DecimalFormat("#####0");
		double dValue1 = Math.abs(dValue);
		BigDecimal bd1 = new BigDecimal(Double.toString(dValue1));
		BigDecimal bd2 = new BigDecimal(Double.toString(getDouble(lScale + 4)));
		BigDecimal bd;
		bd = bd1.add(bd2);
		bd = bd.setScale(lScale, BigDecimal.ROUND_HALF_UP);
		double temp = 0;
		if (dValue >= 0)
			temp = bd.doubleValue();
		else
			temp = bd.doubleValue() * -1;
		return df.format(temp);
	}

	public static double doubleRound2(double dValue, int lScale)
	{
		java.math.BigDecimal bd;
		bd = new java.math.BigDecimal(Double.toString(dValue));
		bd = bd.setScale(lScale, java.math.BigDecimal.ROUND_UP);
		return bd.doubleValue();
	}

	/**
	 * 数字转化为中文大写(只限数字100以内)
	 * 
	 * @param iDigital
	 * @return
	 */
	public static String convertDigToCH(int iDigital)
	{
		String[] strArray = { "一", "二", "三", "四", "五", "六", "七", "八", "九", "十" };
		String strReturn = "";
		if (iDigital <= 10)
		{
			strReturn = "第" + strArray[iDigital - 1] + "条";
		}
		if (iDigital > 10 && iDigital < 100)
		{
			if (iDigital % 10 == 0)
			{
				if (iDigital == 10)
				{
					strReturn = "第十条";
				}
				else
				{
					strReturn = "第" + strArray[(iDigital / 10) - 1] + "十" + "条";
				}
			}
			else
			{
				if (iDigital / 10 < 2)
				{
					strReturn = "第十" + strArray[(iDigital % 10) - 1] + "条";
				}
				else
				{
					strReturn = "第" + strArray[(iDigital / 10) - 1] + "十" + strArray[iDigital % 10] + "条";
				}
			}
		}
		return strReturn;
	}

	/**
	 * 日期格式化: 例如:2003-10-25转化为 公元2003年10月25日
	 * 
	 * @param strDate
	 * @return
	 */
	public static String formatTime(String strDate)
	{
		if (strDate == null || "".equals(strDate))
		{
			return "    年  月  日";
		}
		strDate = strDate.length() >= 10 ? strDate.substring(0, 10) : strDate;
		ArrayList arrayList = new ArrayList();
		String strTemp = "";
		int iTemp = 0;
		StringTokenizer strToken = new StringTokenizer(strDate, "-");
		while (strToken.hasMoreElements())
		{
			arrayList.add((String) strToken.nextElement());
		}
		try
		{
			strTemp += (String) arrayList.get(0) + "年";
			iTemp = Integer.parseInt((String) arrayList.get(1));
			strTemp += iTemp + "月";
			iTemp = Integer.parseInt((String) arrayList.get(2));
			strTemp += iTemp + "日";
		}
		catch (IndexOutOfBoundsException ex)
		{
			System.out.println("++++++++++++++++++++++++IndexOutOfBoundsException: strDate is " + strDate);
			System.out.println("++++++++++++++++++++++++ and return     年  月  日");
		}
		catch (Exception e)
		{
			strTemp = "   年  月  日";
			e.printStackTrace();
		}
		return strTemp;
	}

	public static void main(String[] args) throws Exception{
		args=new String[2];
		int wdnum=MiscUtil.getWeekNum(MiscUtil.formatDate(new Date(),2));
		if(wdnum==1) wdnum=7;
		Date bgdate=getBeforeDay(formatDate(new Date(),1),7-7-2+wdnum);
		args[0]=MiscUtil.formatDate(bgdate,1);
		args[1]=MiscUtil.formatDate(MiscUtil.getAfterDate(MiscUtil.getDateByString(args[0],1),6),1);
		System.out.println(args[0]+","+args[1]);
		
		
	}
}
