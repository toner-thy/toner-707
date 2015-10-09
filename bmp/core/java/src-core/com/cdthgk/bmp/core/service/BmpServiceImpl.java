package com.cdthgk.bmp.core.service;

import java.io.Serializable;

import com.cdthgk.platform.base.service.GenericServiceTemplate;

/**
 * <p>
 * BmpServiceImpl
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 * @param <E> 实体类型
 * @param <ID> ID类型
 * @author 钟冀
 */
public class BmpServiceImpl<E, ID extends Serializable> extends GenericServiceTemplate<E, ID> {

	/**
	 */
	public BmpServiceImpl() {

	}

//	/**
//	 * <p>
//	 * 保存
//	 * </p>
//	 * @param e e
//	 */
//	public void save(E e) {
//		BaseDomain bd = null;
//		if (e instanceof BaseDomain) {
//			bd = (BaseDomain) e;
//			bd.setCreateTime(new Date());
//			bd.setModifyTime(new Date());
//			persistProxy.getOrmPersistence().save(bd);
//		} else {
//			persistProxy.getOrmPersistence().save(e);
//		}
//	}
//
//	/**
//	 * <p>
//	 * 更新
//	 * </p>
//	 * @param e e
//	 */
//	public void update(E e) {
//		BaseDomain bd = null;
//		if (e instanceof BaseDomain) {
//			bd = (BaseDomain) e;
//			bd.setModifyTime(new Date());
//			persistProxy.getOrmPersistence().update(bd);
//		} else {
//			persistProxy.getOrmPersistence().update(e);
//		}
//	}
//
//	/**
//	 * <p>
//	 * 保存或更新
//	 * </p>
//	 * @param e e
//	 */
//	public void saveOrUpdate(E e) {
//		BaseDomain bd = null;
//		if (e instanceof BaseDomain) {
//			bd = (BaseDomain) e;
//			bd.setModifyTime(new Date());
//			persistProxy.getOrmPersistence().saveOrUpdate(bd);
//		} else {
//			persistProxy.getOrmPersistence().saveOrUpdate(e);
//		}
//	}
//
//	/**
//	 * <p>
//	 * 删除
//	 * </p>
//	 * @param e e
//	 */
//	public void delete(E e) {
//		persistProxy.getOrmPersistence().delete(e);
//	}
//
//	/**
//	 * <p>
//	 * 删除
//	 * </p>
//	 * @param id id
//	 */
//	public void delete(ID id) {
//		E e = get(id);
//		if (e != null) {
//			delete(e);
//		}
//	}
//
//	/**
//	 * <p>
//	 * 批量删除
//	 * </p>
//	 * @param entityList 实体集合
//	 */
//	public void deleteBatch(List<E> entityList) {
//		persistProxy.getOrmPersistence().deleteBatch(entityList);
//	}
//
//	/**
//	 * <p>
//	 * 批量删除
//	 * </p>
//	 * @param idList id集合
//	 */
//	public void deleteBatchIdList(List<ID> idList) {
//		if (CollectionUtils.isEmpty(idList)) {
//			return;
//		}
//		for (ID id : idList) {
//			delete(get(id));
//		}
//	}
//
//	/**
//	 * <p>
//	 * 批量删除
//	 * </p>
//	 * @param deleteIds 用,号隔开的一系列ID
//	 */
//	public void deleteBatchIds(String deleteIds) {
//		if (StringUtils.isEmpty(deleteIds)) {
//			return;
//		}
//		String[] ids = deleteIds.split(",");
//		for (String id : ids) {
//			this.delete(get(id, type));
//
//		}
//	}
//
//	/**
//	 * <p>
//	 * 读取实体
//	 * </p>
//	 * @param id id
//	 * @return 实体
//	 */
//	public E get(ID id) {
//		return persistProxy.getOrmPersistence().get(id, type);
//	}
//
//	/**
//	 * <p>
//	 * 读取实体，并从会话中移出
//	 * </p>
//	 * @param id id
//	 * @return 实体
//	 */
//	public E read(ID id) {
//		E e = (E) persistProxy.getOrmPersistence().get(id, type);
//		evict(e);
//		return e;
//	}
//
//	/**
//	 * <p>
//	 * evict
//	 * </p>
//	 * @param o o
//	 */
//	public void evict(Object o) {
//		persistProxy.getOrmPersistence().evict(o);
//	}
//
//	/**
//	 * <p>
//	 * getList
//	 * </p>
//	 * @param hql hql
//	 * @param psm psm
//	 * @param params 参数
//	 * @return 列表
//	 */
//	public List<E> getList(final String hql, PageSortModel<E> psm, final Map<String, Object> params) {
//		int start = -1;
//		int limit = 0;
//		if (!psm.isAll()) {
//			start = psm.getRowStart();
//			limit = psm.getPageSize();
//		}
//		if (start == 0) {
//			start = 1;
//		}
//		Pagination<E> pagination = persistProxy.getOrmPersistence().findPage(hql, params, start, limit);
//		// FIXME 权宜之计...
//		int totalCount = pagination.getTotal();
//		psm.setTotalRows(Integer.parseInt(totalCount + ""));
//		return (List<E>) pagination.getPageResults();
//	}
//
//	/**
//	 * <p>
//	 * 列表方法
//	 * </p>
//	 * @param hql hql
//	 * @param start 开始位置
//	 * @param limit 最大返回值
//	 * @param params 参数
//	 * @return 列表
//	 */
//	public List<E> getList(final String hql, final int start,
//			final int limit, final Map<String, Object> params) {
//		return persistProxy.getOrmPersistence().findList(hql, params, start, limit);
//	}
//
//	/**
//	 * <p>
//	 * 列表方法
//	 * </p>
//	 * @param hql hql
//	 * @param params 参数
//	 * @return 列表
//	 */
//	public List<E> getList(final String hql, final Map<String, Object> params) {
//		return persistProxy.getOrmPersistence().findList(hql, params);
//	}
//
//	/**
//	 * <p>
//	 * 查询唯一返回值
//	 * </p>
//	 * @param hql hql
//	 * @param params 参数
//	 * @return 唯一值
//	 */
//	public E unique(final String hql, final Map<String, Object> params) {
//		return persistProxy.getOrmPersistence().find(hql, params);
//	}
//
//	/**
//	 * <p>
//	 * 查询唯一返回值（数值）
//	 * </p>
//	 * @param hql hql
//	 * @param params 参数
//	 * @return 唯一值
//	 */
//	public Integer count(final String hql, final Map<String, Object> params) {
//		return Integer.parseInt(unique(hql, params) + "");
//	}
//
//	/**
//	 * <p>
//	 * 读取实体
//	 * </p>
//	 * @param <T> 泛型
//	 * @param id id
//	 * @param type 类型
//	 * @return 实体
//	 */
//	public <T> T get(Serializable id, Class<T> type) {
//		return (T) persistProxy.getOrmPersistence().get(id, type);
//	}
//
//	/**
//	 * <p>
//	 * 读取实体，并从会话中移出
//	 * </p>
//	 * @param <T> 泛型
//	 * @param id id
//	 * @param type 类型
//	 * @return 实体
//	 */
//	public <T> T read(Serializable id, Class<T> type) {
//		T t = (T) persistProxy.getOrmPersistence().get(id, type);
//		if (t != null) {
//			evict(t);
//		}
//		return t;
//	}
//
//	/**
//	 * <p>
//	 * 批量保存
//	 * </p>
//	 * @param entityList 实体集合
//	 */
//	public void saveBatch(Collection<E> entityList) {
//		if (entityList != null) {
//			for (E e : entityList) {
//				save(e);
//			}
//		}
//	}
//
//	/**
//	 * <p>
//	 * 批量保存
//	 * </p>
//	 * @param entityList 实体集合
//	 */
//	public void updateBatch(Collection<E> entityList) {
//		if (entityList != null) {
//			for (E e : entityList) {
//				update(e);
//			}
//		}
//	}
//
//
//	// ********************************************************************
//	//	private method
//	// ********************************************************************
//
//	// ********************************************************************
//	//	getter and setter
//	// ********************************************************************
//
//	private PersistDAOProxy persistProxy;
//
//	/**
//	 * 返回persistProxy
//	 * @return persistProxy
//	 */
//	public PersistDAOProxy getPersistProxy() {
//		return persistProxy;
//	}
//
//	/**
//	 * 设置persistProxy
//	 * @param persistProxy persistProxy
//	 */
//	public void setPersistProxy(PersistDAOProxy persistProxy) {
//		this.persistProxy = persistProxy;
//	}

}
