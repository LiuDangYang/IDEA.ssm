package com.baidu.service;

import com.baidu.model.Reconciliation;
import com.baidu.service.impl.IReconciliationImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.Common;
import utils.Util;

import javax.transaction.Transactional;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Hongten
 * @created 2014-5-20
 */
@Service
@Transactional
public class ReadExcel {

    /**
     * read the Excel file
     *
     * @param path the path of the Excel file
     * @return
     * @throws IOException
     */
    public List<Reconciliation> readExcel(String path) throws IOException {
        if (path == null || Common.EMPTY.equals(path)) {
            return null;
        } else {
            String postfix = Util.getPostfix(path);
            if (!Common.EMPTY.equals(postfix)) {
                if (Common.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
                    return readXls(path);
                } else if (Common.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
                    return readXlsx(path);
                }
            } else {
                System.out.println(path + Common.NOT_EXCEL_FILE);
            }
        }
        return null;
    }
    @Autowired
    private IReconciliationImpl iReconciliation;
    /**
     * Read the Excel 2010
     *
     * @param path the path of the excel file
     * @return
     * @throws IOException
     */
    public List<Reconciliation> readXlsx(String path) throws IOException {
        System.out.println(Common.PROCESSING + path);
        InputStream is = new FileInputStream(path);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        List<Reconciliation> list = new ArrayList<Reconciliation>();
        // Read the Sheet
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            // Read the Row
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow != null) {
                    String parentCode_A = "";
                    String productName_A = "";
                    String sonCode_A = "";
                    try {
                        XSSFCell parentCode = xssfRow.getCell(0);
                        XSSFCell sonCode = xssfRow.getCell(2);
                        XSSFCell productName = xssfRow.getCell(3);
                        parentCode_A = getValue(parentCode);
                        productName_A = getValue(productName);
                        sonCode_A = getValue(sonCode);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (StringUtils.isEmpty(parentCode_A) && StringUtils.isEmpty(productName_A) && StringUtils.isEmpty(sonCode_A)) {
                        Reconciliation reconciliation = getReconciliation();
                        Reconciliation reconciliation1 = iReconciliation.selectProdut(parentCode_A);
                        if (reconciliation1!=null){
                            Reconciliation reconciliation2 = getReconciliation();
                            reconciliation2.setParentCode(reconciliation1.getId());
                            reconciliation2.setProductName(productName_A);
                            reconciliation2.setSonCode(sonCode_A);
                            reconciliation2.setCreateDate(new Date());
                            list.add(reconciliation2);
                        }
                    }

                }
            }
        }
        return list;
    }

    private Reconciliation getReconciliation() {
        return new Reconciliation();
    }

    /**
     * Read the Excel 2003-2007
     *
     * @param path the path of the Excel
     * @return
     * @throws IOException
     */
    public List<Reconciliation> readXls(String path) throws IOException {
        System.out.println(Common.PROCESSING + path);
        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        Reconciliation reconciliation = null;
        List<Reconciliation> list = new ArrayList<Reconciliation>();
        // Read the Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // Read the Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    reconciliation = new Reconciliation();
                    HSSFCell name = hssfRow.getCell(0);
                    HSSFCell mobile = hssfRow.getCell(2);
                    HSSFCell merchant = hssfRow.getCell(3);
                    reconciliation.setParentCode(getValue(name));
                    reconciliation.setProductName(getValue(mobile));
                    reconciliation.setSonCode(getValue(merchant));
                    list.add(reconciliation);
                }
            }
        }
        return list;
    }

    @SuppressWarnings("static-access")
    private String getValue(XSSFCell xssfRow) {
        if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfRow.getBooleanCellValue());
        } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
            return String.valueOf(xssfRow.getNumericCellValue());
        } else {
            return String.valueOf(xssfRow.getStringCellValue());
        }
    }

    @SuppressWarnings("static-access")
    private String getValue(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
}