package com.framework.utils;
/**
 * 
 * @author dafei
 *
 */
public class BeforeDate 
{
	private int thisYear = 0;	//年
	private int thisMonth = 0;	//月	
	private int thisDay = 0;	//日	
	private int number = 0;		//查找天数	
	//构造方法	
	public BeforeDate(String today,int number)
	{		
		this.thisYear = Integer.parseInt(today.substring(0,4));		
		this.thisMonth = Integer.parseInt(today.substring(4,6));		
		this.thisDay = Integer.parseInt(today.substring(6,today.length()));		
		this.number = number;	
	}	
	
	//判断是否为闰年	
	private boolean isLeapYear(int y)
	{		
		boolean leapYear = false;		
		if(y%400==0){			
			leapYear = true;		
		}
		else
		{			
			if(y%4==0 && y%100!=0)
			{				
				leapYear = true;			
			}		
		}		
		return leapYear;	
	}	
	
	//获取前一个月	
	private int getBeforeMonth(int m)
	{		
		int beforeMonth = 0;		
		if(m>1 && m<=12){			
			beforeMonth = m - 1;		
		} 
		else if(m == 1)
		{			
			thisYear = thisYear - 1;			
			beforeMonth = 12;		
		}		
		return beforeMonth;	
	}	
	
	
	private int getDayNumber(int y,int m)
	{		
		if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) 
		{			
			return 31;		
		} 
		else if (m == 4 || m == 6 || m == 9 || m == 11) 
		{			
			return 30;		
		} 
		else if (m == 2) 
		{			// 判断是否为闰年			
			if (isLeapYear(y)) 
			{				
				return 29;			
			} 
			else 
			{				
				return 28;			
			}		
		}		
		return 0;	
	}
	
	public void getBeforeDay()
	{				
		if(number >= thisDay)
		{			
			number = number - thisDay;	//将本月内，当前日期之前的天数【减掉】			
			thisDay = 1;			
			for(;;)
			{
				thisMonth = getBeforeMonth(thisMonth);	
				//获取【前一个】月是几月				
				int dayNumber = getDayNumber(thisYear,thisMonth);	
				//获取前一个月的【天数】				
				if(number < dayNumber){
					//如果前一个月的天数大于剩余的天数，所查日期【在前一个月】					
					thisDay = dayNumber - number;					
					break;				
				} 
				else {										
					//如果前一个月的天数小于剩余的天数，所查日期【不在前一个月】					
					number = number - dayNumber;				
				}			
			}		
		}		
		else
		{			
			thisDay = thisDay - number;	//直接取出日期		
		}
		
	}
	
	public String getBeforeDayString()
	{
		getBeforeDay();
		String m=""+thisMonth;
		String d=""+thisDay;
		if(thisMonth<10) m="0"+thisMonth;
		if(thisDay<10)  d="0"+thisDay;
		return ""+thisYear+m+d;
	}

	public static void main(String[] args)
	{
		String date = "20070430";	//日期		
		int number = 100;			//天数		
		BeforeDate dboh = new BeforeDate(date,number);		
		String str = dboh.getBeforeDayString();		
		System.out.println("日期为："+str);	
	}

}
