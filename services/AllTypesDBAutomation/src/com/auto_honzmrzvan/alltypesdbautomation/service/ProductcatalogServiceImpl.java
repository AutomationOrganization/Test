/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.auto_honzmrzvan.alltypesdbautomation.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.wavemaker.runtime.data.dao.WMGenericDao;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;

import com.auto_honzmrzvan.alltypesdbautomation.Productcatalog;


/**
 * ServiceImpl object for domain model class Productcatalog.
 *
 * @see Productcatalog
 */
@Service("AllTypesDBAutomation.ProductcatalogService")
@Validated
public class ProductcatalogServiceImpl implements ProductcatalogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductcatalogServiceImpl.class);


    @Autowired
    @Qualifier("AllTypesDBAutomation.ProductcatalogDao")
    private WMGenericDao<Productcatalog, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Productcatalog, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "AllTypesDBAutomationTransactionManager")
    @Override
	public Productcatalog create(Productcatalog productcatalog) {
        LOGGER.debug("Creating a new Productcatalog with information: {}", productcatalog);

        Productcatalog productcatalogCreated = this.wmGenericDao.create(productcatalog);
        return productcatalogCreated;
    }

	@Transactional(readOnly = true, value = "AllTypesDBAutomationTransactionManager")
	@Override
	public Productcatalog getById(Integer productcatalogId) throws EntityNotFoundException {
        LOGGER.debug("Finding Productcatalog by id: {}", productcatalogId);
        Productcatalog productcatalog = this.wmGenericDao.findById(productcatalogId);
        if (productcatalog == null){
            LOGGER.debug("No Productcatalog found with id: {}", productcatalogId);
            throw new EntityNotFoundException(String.valueOf(productcatalogId));
        }
        return productcatalog;
    }

    @Transactional(readOnly = true, value = "AllTypesDBAutomationTransactionManager")
	@Override
	public Productcatalog findById(Integer productcatalogId) {
        LOGGER.debug("Finding Productcatalog by id: {}", productcatalogId);
        return this.wmGenericDao.findById(productcatalogId);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "AllTypesDBAutomationTransactionManager")
	@Override
	public Productcatalog update(Productcatalog productcatalog) throws EntityNotFoundException {
        LOGGER.debug("Updating Productcatalog with information: {}", productcatalog);
        this.wmGenericDao.update(productcatalog);

        Integer productcatalogId = productcatalog.getProductId();

        return this.wmGenericDao.findById(productcatalogId);
    }

    @Transactional(value = "AllTypesDBAutomationTransactionManager")
	@Override
	public Productcatalog delete(Integer productcatalogId) throws EntityNotFoundException {
        LOGGER.debug("Deleting Productcatalog with id: {}", productcatalogId);
        Productcatalog deleted = this.wmGenericDao.findById(productcatalogId);
        if (deleted == null) {
            LOGGER.debug("No Productcatalog found with id: {}", productcatalogId);
            throw new EntityNotFoundException(String.valueOf(productcatalogId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "AllTypesDBAutomationTransactionManager")
	@Override
	public Page<Productcatalog> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Productcatalogs");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "AllTypesDBAutomationTransactionManager")
    @Override
    public Page<Productcatalog> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Productcatalogs");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "AllTypesDBAutomationTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service AllTypesDBAutomation for table Productcatalog to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "AllTypesDBAutomationTransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "AllTypesDBAutomationTransactionManager")
	@Override
    public Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable) {
        return this.wmGenericDao.getAggregatedValues(aggregationInfo, pageable);
    }



}

