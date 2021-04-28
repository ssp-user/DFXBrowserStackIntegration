package com.automation.utils.dataProvider;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import com.automation.utils.log.LogUtils;

/*
 * @Function: Convert Data Range string to Row Number Array
 * @author: David  at Dealer-FX
 * */

public class DataRangeProcessor {


    public static int[] getValidRange(String dataRange) {                    // Convert String dataRange to int Array
//		String dataRange = "1, 3-6, 15-20";
        int[] selectedRange = new int[]{};

        if (!dataRange.isEmpty()) {
            String[] strNumbers = dataRange.split(",");
            ArrayList<String> newDataRange = rangeHandler(strNumbers);


            selectedRange = new int[newDataRange.size()];
            for (int i = 0; i < newDataRange.size(); i++) {
                selectedRange[i] = Integer.parseInt(newDataRange.get(i).trim());
            }
        }


//		System.out.println("============================");
//		for(int x : selectedRange){
//			System.out.println(x);
//		}

        return selectedRange;
    }


    public static int getMaxRowNumFromSelectedData(int[] selectedRow) {        // Return Max Selected Row
        return selectedRow[selectedRow.length - 1] - 1;
    }

    public static int getMinRowNumFromSelectedData(int[] selectedRow) {        // Return Min Selected Row
        return selectedRow[0];
    }

    public static int[] removeDuplicateNum(int[] selectedRow) {                // Return Non-Duplicated Row Numbers
        Set<Integer> generated = new LinkedHashSet<Integer>();

        for (int i = 0; i < selectedRow.length; i++) {
            generated.add(selectedRow[i]);
        }
        int newArray[] = new int[generated.size()];
        Iterator<Integer> gen = generated.iterator();
        int i = 0;
        while (gen.hasNext()) {
            newArray[i] = gen.next() - 1;
            i++;
        }
        LogUtils.logMsgOnly("::::::::::: Data Range :::::::::::");
        for (int y : newArray) {
            LogUtils.logMsgOnly(String.valueOf(y));
        }
        return newArray;
    }


    private static ArrayList<String> rangeHandler(String[] range) {                    // Convert range from "2-5" to "2, 3, 4,5"
        ArrayList<String> processedDataRange = new ArrayList<String>();
        int index = 0, from = 0, to = 0;
        for (int i = 0; i < range.length; i++) {
            String r = range[i];
            if (r.contains("-")) {
                index = r.indexOf("-");
                from = Integer.parseInt(r.substring(0, index).trim());
                to = Integer.parseInt(r.substring(index + 1).trim());
                LogUtils.logMsgOnly("DataRange from : " + from + ", To : " + to);
                for (int y = from; y <= to; y++) {
                    processedDataRange.add(String.valueOf(y));
                }
            } else {
                processedDataRange.add(r);
            }
        }
        return processedDataRange;
    }


}
