package com.baidu.dao;

import com.baidu.model.Reconciliation;
import org.springframework.stereotype.Repository;

/**
 * Created by 67545 on 2017/11/14.
 */

@Repository
public interface IReconciliationDao {
    Reconciliation insetProdut(Reconciliation reconciliation);
    Reconciliation selectProdut(String parentCode);
}

