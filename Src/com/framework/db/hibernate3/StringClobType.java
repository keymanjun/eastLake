
package com.framework.db.hibernate3;

/**
 * @author dafei
 *
 */
import java.io.Reader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;


import org.apache.commons.lang.ObjectUtils;
import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

/**
 * Based on community area design patterns on Hibernate site. Maps java.sql.Clob
 * to a String special casing for Oracle drivers.
 * 
 * @author Ali Ibrahim, Scott Miller
 */
public class StringClobType implements UserType {

	/**
	 * Name of the oracle driver -- used to support Oracle clobs as a special
	 * case
	 */
	private static final String ORACLE_DRIVER_NAME = "Oracle JDBC driver";

	private static final String SQLSERVER_DRIVER_NAME = "SQLServer";
	
	/** Version of the oracle driver being supported with clob. */
	private static final int ORACLE_DRIVER_MAJOR_VERSION = 9;

	private static final int ORACLE_DRIVER_MINOR_VERSION = 0;

	private static boolean IS_SQLSERVER = true;
	
	public int[] sqlTypes() {
		return new int[] { IS_SQLSERVER?Types.LONGVARCHAR:Types.CLOB };
	}

	public Class returnedClass() {
		return String.class;
	}

	public boolean equals(Object x, Object y) {
		return ObjectUtils.equals(x, y);
	}

	public Object nullSafeGet(ResultSet rs, String[] names, Object owner)
			throws HibernateException, SQLException {
		Reader clobReader = rs.getCharacterStream(names[0]);
		if (clobReader == null) {
			return null;
		}

		String str = new String();
		BufferedReader bufferedClobReader = new BufferedReader(clobReader);
		try {
			String line = null;
			while ((line = bufferedClobReader.readLine()) != null) {
				str += line;
			}
			bufferedClobReader.close();
		} catch (IOException e) {
			throw new SQLException(e.toString());
		}

		return str;
	}

	public void nullSafeSet(PreparedStatement st, Object value, int index)throws HibernateException, SQLException {
		DatabaseMetaData dbMetaData = st.getConnection().getMetaData();
		if (value == null) {
			if (SQLSERVER_DRIVER_NAME.equals(dbMetaData.getDriverName())) IS_SQLSERVER=true;
			st.setNull(index, sqlTypes()[0]);
		} else if (ORACLE_DRIVER_NAME.equals(dbMetaData.getDriverName())) {
			if ((dbMetaData.getDriverMajorVersion() >= ORACLE_DRIVER_MAJOR_VERSION)
					&& (dbMetaData.getDriverMinorVersion() >= ORACLE_DRIVER_MINOR_VERSION)) {
				try {
					// Code compliments of Scott Miller
					// support oracle clobs without requiring oracle libraries
					// at compile time
					// Note this assumes that if you are using the Oracle
					// Driver.
					// then you have access to the oracle.sql.CLOB class

					// First get the oracle clob class
					Class oracleClobClass = Class.forName("oracle.sql.CLOB");

					// Get the oracle connection class for checking
					Class oracleConnectionClass = Class
							.forName("oracle.jdbc.OracleConnection");

					// now get the static factory method
					Class partypes[] = new Class[3];
					partypes[0] = Connection.class;
					partypes[1] = Boolean.TYPE;
					partypes[2] = Integer.TYPE;
					Method createTemporaryMethod = oracleClobClass
							.getDeclaredMethod("createTemporary", partypes);
					// now get ready to call the factory method
					Field durationSessionField = oracleClobClass
							.getField("DURATION_SESSION");
					Object arglist[] = new Object[3];
					Connection conn = st.getConnection();

					// Make sure connection object is right type
					if (!oracleConnectionClass
							.isAssignableFrom(conn.getClass())) {
						throw new HibernateException(
								"JDBC connection object must be a oracle.jdbc.OracleConnection. "
										+ "Connection class is "
										+ conn.getClass().getName());
					}

					arglist[0] = conn;
					arglist[1] = Boolean.TRUE;
					arglist[2] = durationSessionField.get(null); //null is
					// valid
					// because of
					// static field

					// Create our CLOB
					Object tempClob = createTemporaryMethod.invoke(null,
							arglist); //null is valid because of static method

					// get the open method
					partypes = new Class[1];
					partypes[0] = Integer.TYPE;
					Method openMethod = oracleClobClass.getDeclaredMethod(
							"open", partypes);

					// prepare to call the method
					Field modeReadWriteField = oracleClobClass
							.getField("MODE_READWRITE");
					arglist = new Object[1];
					arglist[0] = modeReadWriteField.get(null); //null is valid
					// because of
					// static field

					// call open(CLOB.MODE_READWRITE);
					openMethod.invoke(tempClob, arglist);

					// get the getCharacterOutputStream method
					Method getCharacterOutputStreamMethod = oracleClobClass
							.getDeclaredMethod("getCharacterOutputStream", null);

					// call the getCharacterOutpitStream method
					Writer tempClobWriter = (Writer) getCharacterOutputStreamMethod
							.invoke(tempClob, null);

					// write the string to the clob
					tempClobWriter.write((String) value);
					tempClobWriter.flush();
					tempClobWriter.close();

					// get the close method
					Method closeMethod = oracleClobClass.getDeclaredMethod(
							"close", null);

					// call the close method
					closeMethod.invoke(tempClob, null);

					// add the clob to the statement
					st.setClob(index, (Clob) tempClob);
				} catch (ClassNotFoundException e) {
					// could not find the class with reflection
					throw new HibernateException(
							"Unable to find a required class.\n"
									+ e.getMessage());
				} catch (NoSuchMethodException e) {
					// could not find the metho with reflection
					throw new HibernateException(
							"Unable to find a required method.\n"
									+ e.getMessage());
				} catch (NoSuchFieldException e) {
					// could not find the field with reflection
					throw new HibernateException(
							"Unable to find a required field.\n"
									+ e.getMessage());
				} catch (IllegalAccessException e) {
					throw new HibernateException(
							"Unable to access a required method or field.\n"
									+ e.getMessage());
				} catch (InvocationTargetException e) {
					throw new HibernateException(e.getMessage());
				} catch (IOException e) {
					throw new HibernateException(e.getMessage());
				}
			} else {
				throw new HibernateException(
						"No CLOBS support. Use driver version "
								+ ORACLE_DRIVER_MAJOR_VERSION + ", minor "
								+ ORACLE_DRIVER_MINOR_VERSION);
			}
		} else {
			try {
				String str = (String) value;
				StringReader r=new StringReader(str);
				//ByteArrayInputStream r=new ByteArrayInputStream(str.getBytes());
				//st.setAsciiStream(index,r,str.length());
				st.setCharacterStream(index,r,str.length());
			} catch (Exception ex) {
				throw new HibernateException("CLOBS reader close error"+ex.getMessage(),ex);
			}
			//out.close();

			/*
			 * StringReader r = new StringReader(str);
			 * st.setCharacterStream(index, r, str.length());
			 * System.out.println("");
			 */
		}
	}

	public Object deepCopy(Object value) {
		if (value == null)
			return null;
		return new String((String) value);
	}

	public boolean isMutable() {
		return false;
	}
	
	public Object assemble(Serializable arg0, Object arg1) throws HibernateException{
		return null;
	}
	 
	public int hashCode(Object arg0) throws HibernateException{
		 return 0;
	 }
	public Object replace(Object arg0, Object arg1, Object arg2) throws HibernateException{
		return null;
	}
	public Serializable disassemble(Object arg0) throws HibernateException{
		return null;
	}

}