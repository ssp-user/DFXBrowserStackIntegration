package com.automation.utils.dataProvider;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.automation.utils.log.LogUtils;

import static com.automation.utils.dataProvider.DataRangeProcessor.*;

    /*
     * @Function: This is Excel Data Provider, get data from Excel on both xlsx and xls files.
     * This class contains all the excel test data file location.
     * Able to handle the blank row with rows have been set data before.
     * Data Range format can be "2, 4", and "2, 3-5, 7", The Min row number is 2 as the 1st row is header.
     * All data will be treated as text.
     *
     * @author: Tim Wang at Dealer-FX
     * */

    public class ExcelDataProvider {

        static String [][] strList;
        public static	String fileBaseLocation = "./TestData/";
        public static String strRegressionExcelSheet = "sheet1";

        static String dataRange = null;

        static int[] selectedRange;
        static int rowNum, colNum;




        /*public static String[][] getExcelData(String filePath, String strExcelSheet) {			// Main Method to retrieve data from excel file
            try{
                InputStream file = new FileInputStream(filePath);
                Workbook wb = WorkbookFactory.create(file);
                Sheet sh = wb.getSheet(strExcelSheet);

                rowNum = sh.getLastRowNum();
                colNum = sh.getRow(0).getLastCellNum();
                boolean isRowBlank = false;

                LogUtils.logMsgOnly("The valid row number is : " + rowNum);
                do{
                    Row row = sh.getRow(rowNum);
                    for(int c= 0; c < colNum; c++){
                        Cell cell = row.getCell(c, Row.CREATE_NULL_AS_BLANK);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        if(cell.toString().equals("")){
                            isRowBlank = true;
                        } else if(!cell.toString().equals("")){
                            isRowBlank = false;
                            break;
                        }
                    }
                    if(isRowBlank){
                        rowNum--;
                    }

                } while(isRowBlank);

                LogUtils.logMsgOnly("The valid row number is : " + rowNum);

                int[] rows = getSelectedDataRow();

                for(int r = 1; r <= rows.length; r++){
                    Row row = sh.getRow(rows[r-1]);
                    for(int c = 0; c < colNum; c++){
                        Cell cell = row.getCell(c, Row.CREATE_NULL_AS_BLANK);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        strList[r-1][c] = cell.toString();
                    }
                }

            } catch(Exception e){
                e.printStackTrace();
                LogUtils.logError("Reading Excel File Error. " + e.getMessage());
            }

//	for(String[] string : strList){
//		for(String kkk : string){
//			System.out.print(kkk + "\t\t");
//		}
//		System.out.println();
//	}

            return strList;
        }*/

        private static int[] getSelectedDataRow(){						// Return Data Range Selection
            selectedRange = getValidRange(dataRange);
            Arrays.sort(selectedRange);
            int[] result;
            if(selectedRange.length >= 1 && rowNum >= getMaxRowNumFromSelectedData(selectedRange)
                    && getMinRowNumFromSelectedData(selectedRange) > 1){
                LogUtils.logMsgOnly("Provided Data Range is valid");
                result = removeDuplicateNum(selectedRange);
            } else {	// This is selectedRow invalid
                LogUtils.logMsgOnly("Provided Data Range is invalid, taking the full list from Excel Data.");
                result = new int[rowNum];
                for(int i = 1; i <= rowNum; i++){
                    result[i-1] = i;
                }
            }
            strList = new String[result.length][colNum];
            return result;
        }


        public static void setDataRange(String string){					// set Data Row Range
            if(string.equalsIgnoreCase("ALL")){
                dataRange = "";
            } else {
                dataRange = string;
            }
        }




    }
