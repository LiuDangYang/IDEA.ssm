package com.baidu.service;

import com.baidu.model.Reconciliation;

/**
 * Created by 67545 on 2017/12/15.
 */
public interface IReconciliationService {
     Reconciliation insetProdut(Reconciliation reconciliation);
     Reconciliation selectProdut(String parentCode);
}
