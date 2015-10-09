package com.cdthgk.bmp.core.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.cdthgk.platform.base.service.GenericService;

import ec.common.PageSortModel;

/**
 * <p>
 * BmpServiceTemplate
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 * @param <E> 实体类型
 * @param <ID> ID类型
 *
 * @author 钟冀
 */
public interface BmpServiceTemplate<E, ID extends Serializable> extends GenericService<E, ID>{

	/**
	 * <p>
	 * 保存
	 * </p>
	 * @param e e
	 */
	E save(E e);

	/**
	 * <p>
	 * 更新
	 * </p>
	 * @param e e
	 */
	E update(E e);

	/**
	 * <p>
	 * 保存或更新
	 * </p>
	 * @param e e
	 */
	E saveOrUpdate(E e);

	/**
	 * <p>
	 * 删除
	 * </p>
	 * @param e e
	 */
	E delete(E e);

	/**
	 * <p>
	 * 删除
	 * </p>
	 * @param id id
	 */
	E delete(ID id);

	/**
	 * <p>
	 * 批量删除
	 * </p>
	 * @param entityList 实体集合
	 */
	void deleteBatch(List<E> entityList);

	/**
	 * <p>
	 * 批量删除
	 * </p>
	 * @param idList id集合
	 */
	void deleteBatchIdList(List<ID> idList);

	/**
	 * <p>
	 * 批量删除
	 * </p>
	 * @param deleteIds 用,号隔开的一系列ID
	 */
	void deleteBatchIds(String deleteIds);

	/**
	 * <p>
	 * 读取实体
	 * </p>
	 * @param id id
	 * @return 实体
	 */
	E get(ID id);

	/**
	 * <p>
	 * 读取实体
	 * </p>
	 * @param <T> 泛型
	 * @param id id
	 * @param type 类型
	 * @return 实体
	 */
	<T> T get(Serializable id, Class<T> type);

	/**
	 * <p>
	 * 读取实体，并从会话中移出
	 * </p>
	 * @param id id
	 * @return 实体
	 */
	E read(ID id);

	/**
	 * <p>
	 * 读取实体，并从会话中移出
	 * </p>
	 * @param <T> 泛型
	 * @param id id
	 * @param type 类型
	 * @return 实体
	 */
	<T> T read(Serializable id, Class<T> type);

	/**
	 * <p>
	 * getList
	 * </p>
	 * @param hql hql
	 * @param psm psm
	 * @param params 参数
	 * @return 列表
	 */
	@Deprecated
	List<E> getList(final String hql, PageSortModel<E> psm, final Map<String, Object> params);

	/**
	 * <p>
	 * 列表方法
	 * </p>
	 * @param hql hql
	 * @param start 开始位置
	 * @param limit 最大返回值
	 * @param params 参数
	 * @return 列表
	 */
	@Deprecated
	List<E> getList(final String hql, final int start, final int limit, final Map<String, Object> params);

	/**
	 * <p>
	 * 列表方法
	 * </p>
	 * @param hql hql
	 * @param params 参数
	 * @return 列表
	 */
	@Deprecated
	List<E> getList(final String hql, final Map<String, Object> params);

	/**
	 * <p>
	 * 查询唯一返回值
	 * </p>
	 * @param hql hql
	 * @param params 参数
	 * @return 唯一值
	 */
	E unique(String hql, Map<String, Object> params);

	/**
	 * <p>
	 * 查询唯一返回值（数值）
	 * </p>
	 * @param hql hql
	 * @param params 参数
	 * @return 唯一值
	 */
	Object count(String hql, Map<String, Object> params);

	/**
	 * <p>
	 * 批量保存
	 * </p>
	 * @param list 实体列表
	 */
	void saveBatch(Collection<E> list);

	/**
	 * <p>
	 * 批量保存
	 * </p>
	 * @param entityList 实体集合
	 */
	void updateBatch(Collection<E> entityList);
}
