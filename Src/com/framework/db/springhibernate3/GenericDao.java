
package com.framework.db.springhibernate3;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.framework.FrameConstant;
import com.framework.components.pager.PaginationSupport;
import com.framework.db.GenericsUtils;
import com.framework.db.ResultMappingUtil;

@SuppressWarnings("unchecked")
public class GenericDao<T, ID extends Serializable> extends HibernateDaoSupport
{
	private Log logger = LogFactory.getLog(getClass());
	private Class<T> pojoClass;

	public GenericDao() throws Exception {
		// this.pojoClass =
		// (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		this.pojoClass = GenericsUtils.getSuperClassGenricType(getClass());
	}

	public Class<T> getPojoClass() {
		return this.pojoClass;
	}

	public String getPojoClassName() {
		return getPojoClass().getName();
	}

	public List<T> loadAll() {
		return (List<T>) getHibernateTemplate().loadAll(getPojoClass());
	}

	public List find(String hql, Object... values) {
		return getHibernateTemplate().find(hql, values);
	}

	public List<T> findByExample(T instance) {
		List<T> results = (List<T>) getHibernateTemplate().findByExample(instance);
		return results;
	}

	public T findById(ID id, Object entityClass) {
		return (T) getHibernateTemplate().get(entityClass.getClass(), id);
	}

	public List<T> findByProperty(String key, String value, Object entityClass) {
		String queryString = "from " + entityClass.getClass().getName() + " as model where t." + key + "= '" + value + "'";
		return (List<T>) getHibernateTemplate().find(queryString, entityClass);
	}

	public void save(Object obj) {
		getHibernateTemplate().saveOrUpdate(obj);
	}

	public void update(T transientInstance) {
		getHibernateTemplate().update(transientInstance);
	}

	public void deleteObj(Object obj) {
		getHibernateTemplate().delete(obj);
	}

	public void delete(T persistentInstance) {
		getHibernateTemplate().delete(persistentInstance);
	}  

	public PaginationSupport findPageByQuery(final String hql, final int pageSize, final int startIndex, Object... values) {
		int totalCount = getCountByQuery(hql, values);

		if (totalCount < 1)
			return new PaginationSupport(new ArrayList(0), 0);
		Query query = createQuery(hql, values);
		List items = query.setFirstResult(startIndex).setMaxResults(pageSize).list();
		PaginationSupport ps = new PaginationSupport(items, totalCount, pageSize, startIndex);
		return ps;
	}

	public PaginationSupport findPageByQuery(final String hql, final int startIndex, Object... values) {
		return findPageByQuery(hql, PaginationSupport.PAGESIZE, startIndex, values);
	}

	public int getCountByQuery(final String hql, Object... values) {
		String countQueryString = " select count(*) " + removeSelect(removeOrders(hql));
		List countlist = getHibernateTemplate().find(countQueryString, values);
		return countlist == null || countlist.size() < 1 ? 0 : ((Long) countlist.get(0)).intValue();
	}
    
    /** *
     * <pre>
     * dao.getQuery(hql).setMaxResult(100).setCacheable(true).list();
     * </pre>
     * <pre>
     *        dao.createQuery(hql)
     *        dao.createQuery(hql,arg0);
     *        dao.createQuery(hql,arg0,arg1);
     *        dao.createQuery(hql,new Object[arg0,arg1,arg2])
     * </pre>
     *
     * @param values
     */
	public Query createQuery(String hql, Object... values) {
		Query query = getSession().createQuery(hql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}

	private static String removeSelect(String hql) {
		int beginPos = hql.toLowerCase().indexOf("from");
		return hql.substring(beginPos);
	}

	private static String removeOrders(String hql) {
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}
    
 
	private List transformResults(List items) {
		if (items.size() > 0) {
			if (items.get(0) instanceof Map) {
				ArrayList list = new ArrayList(items.size());
				for (int i = 0; i < items.size(); i++) {
					Map map = (Map) items.get(i);
					list.add(map.get(CriteriaSpecification.ROOT_ALIAS));
				}
				return list;
			} else if (items.get(0) instanceof Object[]) {
				ArrayList list = new ArrayList(items.size());
				int pos = 0;
				for (int i = 0; i < ((Object[]) items.get(0)).length; i++) {
					if (((Object[]) items.get(0))[i].getClass() == getPojoClass()) {
						pos = i;
						break;
					}
				}
				for (int i = 0; i < items.size(); i++) {
					list.add(((Object[]) items.get(i))[pos]);
				}
				return list;
			} else
				return items;
		} else
			return items;
	}
	
	/**
	 * @param hql
	 * @param values
	 * @return int
	 */
	public int updateByHql(String hql, Object... values) {
		Query query = this.createQuery(hql, values);
		int count = query.executeUpdate();
		logger.info("update count : " + count);
		return count;
	}

	public int batchUpdateByHql(String hql) {
		Query query = getSession().createQuery(hql);
		int count = query.executeUpdate();
		logger.info("update count : " + count);
		return count;
	}

	public int batchUpdateBySql(String sql) {
		SQLQuery query = getSession().createSQLQuery(sql);
		int count = query.executeUpdate();
		logger.info("update count : " + count);
		return count;
	}
    
    /**
     * 
     * @param sql
     * @parm sqlType 1:Long;2:Double;3:String
     * @param values
     * @return
     */
	public Object getTotalBySqlQuery(final String sql, int sqlType, Object... values) {
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		if (sqlType == 1) {
			sqlQuery.addScalar("totalrow", Hibernate.LONG);
		} else if (sqlType == 2) {
			sqlQuery.addScalar("totalrow", Hibernate.DOUBLE);
		} else if (sqlType == 3) {
			sqlQuery.addScalar("totalrow", Hibernate.STRING);
		}
		return sqlQuery.uniqueResult();
	}
    
	/**
	 * create by dafei at 2009-8-10
	 * 
	 * @param sql
	 * @param values
	 * @return
	 */
	public int getCountBySqlQuery(final String sql, Object... values) {
		String countQueryString = " select count(*) as totalrow from (" + removeOrders(sql) + ") t";
		SQLQuery sqlQuery = getSession().createSQLQuery(countQueryString);
		sqlQuery.addScalar("totalrow", Hibernate.LONG);
		Long totalrow = (Long) sqlQuery.list().get(0);
		return totalrow.intValue();
	}

	/**
	 * dafei
	 * 
	 * @param sql
	 * @param targetEntityName
	 * @return
	 * @throws Exception
	 */
	public List loadResultsForSql(String sql, String targetEntityName) throws Exception {
		List list = null;
		try {
			SQLQuery sqlQuery = getSession().createSQLQuery(sql);
			sqlQuery.addEntity(Class.forName(targetEntityName));
			list = sqlQuery.list();
		} catch (Exception e) {
			throw e;
		}
		return list;
	}

	/**
	 * dafei
	 * 
	 * @param sql
	 * @param targetEntityName
	 * @return
	 * @throws Exception
	 */
	public List loadResults2BeanForSql(String sql, String beanName, String[] values) throws Exception {
		List items = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			SQLQuery sqlQuery = getSession().createSQLQuery(sql);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					sqlQuery.setParameter(i + 1, values[i]);
				}
			}
			conn = getSession().connection();
			ps = conn.prepareStatement(sql);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					ps.setObject(i + 1, values[i]);
				}
			}
			rs = ps.executeQuery();
			items = ResultMappingUtil.mappintEntityList(rs, beanName, ps);
		} catch (Exception e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return items;
	}

	/**
	 * sql
	 * 
	 * @param sql
	 * @param pageSize
	 * @param startIndex
	 * @param values
	 * @return
	 */
	public PaginationSupport findPageBySQLQuery(final String sql, String targetEntityName, final int pageSize, final int startIndex, Object... values)
			throws Exception {
		int totalCount = getCountBySqlQuery(sql, values);
		if (totalCount < 1)
			return new PaginationSupport(new ArrayList(0), 0);
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				sqlQuery.setParameter(i + 1, values[i]);
			}
		}
		sqlQuery.addEntity(Class.forName(targetEntityName));
		List items = sqlQuery.setFirstResult(startIndex).setMaxResults(pageSize).list();
		PaginationSupport ps = new PaginationSupport(items, totalCount, pageSize, startIndex);
		return ps;
	}
    
	/**
	 * sql
	 * 
	 * @param sql
	 * @param beanName
	 * @param values
	 * @param pageSize
	 * @param startIndex
	 * @return
	 * @throws Exception
	 */
	public PaginationSupport findPageBySQLQuery(String sql, String beanName, Object[] values, final int pageSize, final int startIndex)
			throws Exception {
		PaginationSupport pager = null;
		int totalCount = getCountBySqlQuery(sql, values);
		if (totalCount < 1)
			return new PaginationSupport(new ArrayList(0), 0);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			SQLQuery sqlQuery = getSession().createSQLQuery(sql);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					sqlQuery.setParameter(i + 1, values[i]);
				}
			}
			conn = getSession().connection();
			sql = getPagerSql(sql, pageSize, startIndex);
			ps = conn.prepareStatement(sql);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					ps.setObject(i + 1, values[i]);
				}
			}
			rs = ps.executeQuery();
			List items = ResultMappingUtil.mappintEntityList(rs, beanName, null);
			pager = new PaginationSupport(items, totalCount, pageSize, startIndex);
		} catch (Exception e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return pager;
	}

	public PaginationSupport findPageBySQLQueryForClob(String sql, String beanName, Object[] values, final int pageSize, final int startIndex)
			throws Exception {
		PaginationSupport pager = null;
		int totalCount = getCountBySqlQuery(sql, values);
		if (totalCount < 1)
			return new PaginationSupport(new ArrayList(0), 0);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			SQLQuery sqlQuery = getSession().createSQLQuery(sql);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					sqlQuery.setParameter(i + 1, values[i]);
				}
			}
			conn = getSession().connection();
			conn.setAutoCommit(false);
			sql = getPagerSql(sql, pageSize, startIndex);
			ps = conn.prepareStatement(sql);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					ps.setObject(i + 1, values[i]);
				}
			}
			rs = ps.executeQuery();
			List items = ResultMappingUtil.mappintEntityList(rs, beanName, ps);
			pager = new PaginationSupport(items, totalCount, pageSize, startIndex);
		} catch (Exception e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.commit();
				conn.close();
			}
		}
		return pager;
	}

	/**
	 * create by feiluo at 2009-8-10
	 * 
	 * @param databasename
	 * @param sql
	 * @param pageSize
	 * @param startIndex
	 * @return
	 * @throws Exception
	 */
	private String getPagerSql(String sql, int pageSize, int startIndex) throws Exception {
		if (FrameConstant.DATABASE_NAME.equals("mysql")) {
			sql = "select t.* from(" + sql + ") t limit " + startIndex + "," + pageSize;
			logger.info(sql);
		} else if (FrameConstant.DATABASE_NAME.equals("oracle")) {

		}
		return sql;
	}
}
