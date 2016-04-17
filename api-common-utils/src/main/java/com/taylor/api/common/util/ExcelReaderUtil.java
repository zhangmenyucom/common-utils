package com.taylor.api.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * @notes:读取excl
 * @author henry
 *
 * 2015年12月30日		上午11:20:48
 */
public class ExcelReaderUtil{

	/** 总行数 */
	private int totalRows = 0;
	/** 总列数 */
	private int totalCells = 0;
	/** 错误信息 */
	private String errorInfo;
	/** 构造方法 */
	public ExcelReaderUtil(){

	}
	/**
	 * @notes:得到总行数
	 * @return: int:
	 * @author: henry
	 *
	 * 2015年12月30日		上午11:21:20
	 */
	public int getTotalRows(){
		return totalRows;
	}

	/**
	 * @notes:得到总列数
	 * @return: int:
	 * @author: henry
	 *
	 * 2015年12月30日		上午11:21:40
	 */
	public int getTotalCells(){
		return totalCells;
	}

	/**
	 * @notes:得到错误信息
	 * @return: String:
	 * @author: henry
	 *
	 * 2015年12月30日		上午11:22:13
	 */

	public String getErrorInfo(){
		return errorInfo;
	}

	/**
	 * @notes:得到错误信息
	 * @param filePath
	 * @return: boolean:
	 * @author: henry
	 *
	 * 2015年12月30日		上午11:22:27
	 */
	public boolean validateExcel(String filePath){
		/** 检查文件名是否为空或者是否是Excel格式的文件 */
		if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))){
			errorInfo = "文件名不是excel格式";
			return false;
		}
		/** 检查文件是否存在 */
		File file = new File(filePath);
		if (file == null || !file.exists()){
			errorInfo = "文件不存在";
			return false;
		}
		return true;
	}
	/**
	 * @notes:根据文件名读取excel文件
	 * @param filePath
	 * @return: List<List<String>>:
	 * @author: henry
	 *
	 * 2015年12月30日		上午11:23:17
	 */

	public List<List<List<String>>> read(String filePath){
	    List<List<List<String>>> dataLst = new ArrayList<List<List<String>>>();
		InputStream is = null;
		try{
			/** 验证文件是否合法 */
			if (!validateExcel(filePath)){
				System.out.println(errorInfo);
				return null;
			}
			/** 判断文件的类型，是2003还是2007 */
			boolean isExcel2003 = true;
			if (isExcel2007(filePath)){
				isExcel2003 = false;
			}
			/** 调用本类提供的根据流读取的方法 */
			File file = new File(filePath);
			is = new FileInputStream(file);
			dataLst = read(is, isExcel2003);
			is.close();
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
		finally{
			if (is != null){
				try{
					is.close();
				}
				catch (IOException e){
					is = null;
					e.printStackTrace();
				}
			}
		}
		/** 返回最后读取的结果 */
		return dataLst;
	}

	/**
	 * @notes:根据文件静读取excl
	 * @param inputStream
	 * @param isExcel2003
	 * @return: List<List<String>>:
	 * @author: henry
	 *
	 * 2015年12月30日		上午11:24:40
	 */
	public List<List<List<String>>> read(InputStream inputStream, boolean isExcel2003){
	    List<List<List<String>>> dataLst = null;
		try{
			/** 根据版本选择创建Workbook的方式 */
			Workbook wb = null;
			if (isExcel2003){
				wb = new HSSFWorkbook(inputStream);
			}
			else{
				wb = new XSSFWorkbook(inputStream);
			}
			dataLst = read(wb);
		}
		catch (IOException e){
			e.printStackTrace();
		}
		return dataLst;
	}

	/**
	 * @param wb
	 * @return: List<List<String>>:
	 * @author: henry
	 *
	 * 2015年12月30日		上午11:25:20
	 */
	private List<List<List<String>>> read(Workbook wb){
	    List<List<List<String>>> resultList = new ArrayList< List<List<String>>>();
		/** 得到第一个shell */
		for(int i=0;i<wb.getNumberOfSheets();i++){
		    List<List<String>> dataLst = new ArrayList<List<String>>();
    		Sheet sheet = wb.getSheetAt(i);
    		/** 得到Excel的行数 */
    		this.totalRows = sheet.getPhysicalNumberOfRows();
    		/** 得到Excel的列数 */
    		if (this.totalRows >= 1 && sheet.getRow(0) != null){
    			this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
    		}
    		/** 循环Excel的行 */
    		for (int r = 0; r < this.totalRows; r++){
    			Row row = sheet.getRow(r);
    			if (row == null){
    				continue;
    			}
    			List<String> rowLst = new ArrayList<String>();
    			/** 循环Excel的列 */
    			for (int c = 0; c < this.getTotalCells(); c++){
    				Cell cell = row.getCell(c);
    				String cellValue = "";
    				if (null != cell){
    					// 以下是判断数据的类型
    					switch (cell.getCellType()){
    					case HSSFCell.CELL_TYPE_NUMERIC: // 数字
    						cellValue = cell.getNumericCellValue() + "";
    						break;
    					case HSSFCell.CELL_TYPE_STRING: // 字符串
    						cellValue = cell.getStringCellValue();
    						break;
    					case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
    						cellValue = cell.getBooleanCellValue() + "";
    						break;
    					case HSSFCell.CELL_TYPE_FORMULA: // 公式
    						cellValue = cell.getCellFormula() + "";
    						break;
    					case HSSFCell.CELL_TYPE_BLANK: // 空值
    						cellValue = "";
    						break;
    					case HSSFCell.CELL_TYPE_ERROR: // 故障
    						cellValue = "非法字符";
    						break;
    					default:
    						cellValue = "未知类型";
    						break;
    					}
    				}
    				rowLst.add(cellValue);
    			}
    			/** 保存第r行的第c列 */
    			dataLst.add(rowLst);
    		}
    		resultList.add(dataLst);
        }
		return resultList;
	}

    /**
     * @notes:是否是2003的excel，返回true是2003
     * @param filePath
     * @return: boolean:
     * @author: henry
     *
     * 2015年12月30日		上午11:27:30
     */
    public static boolean isExcel2003(String filePath){
    	return filePath.matches("^.+\\.(?i)(xls)$");
    }
    /**
     * @notes:是否是2007的excel，返回true是2007
     * @param filePath
     * @return: boolean:
     * @author: henry
     *
     * 2015年12月30日		上午11:27:47
     */
    public static boolean isExcel2007(String filePath){
    	return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

    /**
     * @notes:main测试方法
     * @param args
     * @throws Exception: void:
     * @author: henry
     *
     * 2015年12月30日      上午11:26:30
     */
    public static void main(String[] args) throws Exception{
        ExcelReaderUtil poi = new ExcelReaderUtil();
        List<List<List<String>>> sheetList = poi.read("D:/data.xlsx");
        if (sheetList != null){
            for (int k = 0; k < sheetList.size(); k++){
                List<List<String>> sheet = sheetList.get(k);
                System.out.print("第" + (k) + "个sheet");
                for (int i = 0; i < sheet.size(); i++){
                    System.out.print("第" + (i) + "行");
                    List<String> cellList = sheet.get(i);
                    for (int j = 0; j < cellList.size(); j++){
                        System.out.print("    " + cellList.get(j));
                    }
                    System.out.println();
                }
            }
        }
    }
}
