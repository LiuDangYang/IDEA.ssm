package com.baidu.service.impl;

import com.baidu.dao.IReconciliationDao;
import com.baidu.model.Reconciliation;
import com.baidu.service.IReconciliationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by 67545 on 2017/12/15.
 */

@Service("productService")
@Transactional
public class IReconciliationImpl implements IReconciliationService {
    
    @Autowired
    private IReconciliationDao reconciliationDao;

    @Override
    public Reconciliation selectProdut(String parentCode) {
        Reconciliation reconciliation = reconciliationDao.selectProdut(parentCode);
        return reconciliation;
    }

    public Reconciliation insetProdut(Reconciliation reconciliation) {
        Reconciliation reconciliation1 = reconciliationDao.insetProdut(reconciliation);
        return reconciliation1;
    }
}
