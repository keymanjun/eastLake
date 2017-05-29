
package com.framework.db.hibernate3;

/**
 * @author dafei
 * 
 */
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Types;

import org.apache.commons.lang.ObjectUtils;
import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

/**
 * Based on community area design patterns on Hibernate site. Maps java.sql.Blob
 * to a String special casing for Oracle drivers.
 * 
 * @author Ali Ibrahim, Scott Miller
 */
public class ByteArrayBlobType implements UserType {

	/**
	 * Name of the oracle driver -- used to support Oracle blobs as a special
	 * case
	 */
	private static final String ORACLE_DRIVER_NAME = "Oracle JDBC driver";
	private static final String SQLSERVER_DRIVER_NAME = "SQLServer";

	/** Version of the oracle driver being supported with blob. */
	private static final int ORACLE_DRIVER_MAJOR_VERSION = 9;
	private static final int ORACLE_DRIVER_MINOR_VERSION = 0;
	private static boolean IS_SQLSERVER = false;

	public int[] sqlTypes() {
		return new int[] { IS_SQLSERVER ? Types.LONGVARBINARY : Types.BLOB };
	}

	public Class returnedClass() {
		return String.class;
	}

	public boolean equals(Object x, Object y) {
		return ObjectUtils.equals(x, y);
	}

	public Object nullSafeGet(ResultSet rs, String[] names, Object owner)
			throws HibernateException, SQLException {
		InputStream blobReader = rs.getBinaryStream(names[0]);
		if (blobReader == null)
			return null;
		byte[] b = new byte[1024];

		ByteArrayOutputStream os = new ByteArrayOutputStream();

		try {
			while ((blobReader.read(b)) != -1)
				os.write(b);
		} catch (IOException e) {
			throw new SQLException(e.toString());
		} finally {
			try {
				os.close();
			} catch (IOException e) {
			}
		}
		return os.toByteArray();
	}

	public void nullSafeSet(PreparedStatement st, Object value, int index)
			throws HibernateException, SQLException {
		DatabaseMetaData dbMetaData = st.getConnection().getMetaData();
		if (value == null) {
			if (SQLSERVER_DRIVER_NAME.equals(dbMetaData.getDriverName()))
				IS_SQLSERVER = true;
			st.setNull(index, sqlTypes()[0]);
		} else if (ORACLE_DRIVER_NAME.equals(dbMetaData.getDriverName())) {
			if ((dbMetaData.getDriverMajorVersion() >= ORACLE_DRIVER_MAJOR_VERSION)
					&& (dbMetaData.getDriverMinorVersion() >= ORACLE_DRIVER_MINOR_VERSION)) {
				try {
					// Code compliments of Scott Miller
					// support oracle blobs without requiring oracle libraries
					// at compile time
					// Note this assumes that if you are using the Oracle
					// Driver.
					// then you have access to the oracle.sql.BLOB class

					// First get the oracle blob class
					Class oracleBlobClass = Class.forName("oracle.sql.BLOB");

					// Get the oracle connection class for checking
					Class oracleConnectionClass = Class
							.forName("oracle.jdbc.OracleConnection");

					// now get the static factory method
					Class partypes[] = new Class[3];
					partypes[0] = Connection.class;
					partypes[1] = Boolean.TYPE;
					partypes[2] = Integer.TYPE;
					Method createTemporaryMethod = oracleBlobClass
							.getDeclaredMethod("createTemporary", partypes);
					// now get ready to call the factory method
					Field durationSessionField = oracleBlobClass
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
					arglist[2] = durationSessionField.get(null); // null is
																	// valid
																	// because
																	// of static
																	// field

					// Create our BLOB
					Object tempBlob = createTemporaryMethod.invoke(null,
							arglist); // null is valid because of static
										// method

					// get the open method
					partypes = new Class[1];
					partypes[0] = Integer.TYPE;
					Method openMethod = oracleBlobClass.getDeclaredMethod(
							"open", partypes);

					// prepare to call the method
					Field modeReadWriteField = oracleBlobClass
							.getField("MODE_READWRITE");
					arglist = new Object[1];
					arglist[0] = modeReadWriteField.get(null); // null is valid
																// because of
																// static field

					// call open(BLOB.MODE_READWRITE);
					openMethod.invoke(tempBlob, arglist);

					// get the getCharacterOutputStream method
					Method getBinaryOutputStreamMethod = oracleBlobClass
							.getDeclaredMethod("getBinaryOutputStream", null);

					// call the getCharacterOutpitStream method
					OutputStream tempBlobOutputStream = (OutputStream) getBinaryOutputStreamMethod
							.invoke(tempBlob, null);

					// write the string to the blob
					tempBlobOutputStream.write((byte[]) value);
					tempBlobOutputStream.flush();
					tempBlobOutputStream.close();

					// get the close method
					Method closeMethod = oracleBlobClass.getDeclaredMethod(
							"close", null);

					// call the close method
					closeMethod.invoke(tempBlob, null);

					// add the blob to the statement
					st.setBlob(index, (Blob) tempBlob);
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
						"No BLOBS support. Use driver version "
								+ ORACLE_DRIVER_MAJOR_VERSION + ", minor "
								+ ORACLE_DRIVER_MINOR_VERSION);
			}
		} else {
			byte[] str = (byte[]) value;
			st
					.setBinaryStream(index, new ByteArrayInputStream(str),
							str.length);
		}
	}

	public Object deepCopy(Object value) {
		if (value == null)
			return null;
		return (byte[]) value;
	}

	public boolean isMutable() {
		return false;
	}

	public Object assemble(Serializable arg0, Object arg1)
			throws HibernateException {
		return null;
	}

	public int hashCode(Object arg0) throws HibernateException {
		return 0;
	}

	public Object replace(Object arg0, Object arg1, Object arg2)
			throws HibernateException {
		return null;
	}

	public Serializable disassemble(Object arg0) throws HibernateException {
		return null;
	}

}
