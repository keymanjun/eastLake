
package com.framework.db.sqlmap.exception;
public class SqlmapException extends Exception
{
	private static final long serialVersionUID = 492965906304820995L;
	public SqlmapException() 
	{
		super();
	}
	 
	public SqlmapException(String message) 
	{
		super(message);
	}
	
	public SqlmapException(String message,Throwable cause) {
		super(message,cause);
	}
	
	public SqlmapException( Throwable cause) {
		super(cause);
	}
}
