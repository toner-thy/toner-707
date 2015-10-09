package com.cdthgk.platform.base.service;

import java.io.Serializable;

import com.cdthgk.platform.base.service.GenericService;

/**
 * <p>
 * BaseService
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 * @param <E> 实体类型
 * @param <ID> ID类型
 *
 * @author 汪钟
 */
public interface BaseService<E, ID extends Serializable> extends GenericService<E, ID>{

}
