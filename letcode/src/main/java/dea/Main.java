package dea;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

/**
 * @date: 2022-04-24
 * @author: yangniuhaojiang
 * @title: Main
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public class Main {

    static double D78;
    static double E78;
    static double F78;
    static double G78;
    static double H78;
    static double I78;

    static double K78;

    static int[] lArr;

    static double N1 = 0;
    static double N80 = 0;
    static double N78 = 0;
    static double O78 = 0;
    static double P78 = 0;
    static double Q78 = 0;
    static double R78 = 0;
    static double S78 = 0;

    public static void main(String[] args) throws Exception {
        File file = new File("D:\\a=tmm\\cai\\sbm结果计算宏文件1.xlsm");
        FileInputStream fileInputStream = new FileInputStream(file);
        Workbook wb = new XSSFWorkbook(fileInputStream);
        Sheet sheet = wb.getSheet("sbm效率计算");
        int index = 1;
        int maxRow = 71;
        lArr = new int[maxRow - 1];
        //=K78-(D78/D75+E78/E75+F78/F75+G78/G75)/4


        double DIndex = sheet.getRow(2 + index).getCell(3).getNumericCellValue();
        double EIndex = sheet.getRow(2 + index).getCell(4).getNumericCellValue();
        double FIndex = sheet.getRow(2 + index).getCell(5).getNumericCellValue();
        double GIndex = sheet.getRow(2 + index).getCell(6).getNumericCellValue();
        double HIndex = sheet.getRow(2 + index).getCell(7).getNumericCellValue();
        double IIndex = sheet.getRow(2 + index).getCell(8).getNumericCellValue();

        double D75 = DIndex;
        double E75 = EIndex;
        double F75 = FIndex;
        double G75 = GIndex;
        double H75 = HIndex;
        double I75 = IIndex;

        //	7.31096576	1.077002212	1.473508767


        //N80 = 0
        double N80 = K78 + (H78 / H75 + I78 / I75) / 2;
        K78 = -((H78 / H75 + I78 / I75) / 2);

        System.out.println(N80);
        //N78:S78=0
        //double N78 = sumCell(sheet, 3, 4, maxRow) + D78 + (-K78 * D75);
        D78 = -sumCell(sheet, 3, 4, maxRow) - (-K78 * D75);
//        double O78 = sumCell(sheet, 4, 4, maxRow) + E78 + (-K78 * E75);
        E78 = -sumCell(sheet, 4, 4, maxRow) - (-K78 * E75);
//        double P78 = sumCell(sheet, 5, 4, maxRow) + F78 + (-K78 * F75);
        F78 = -sumCell(sheet, 5, 4, maxRow) - (-K78 * F75);
//        double Q78 = sumCell(sheet, 6, 4, maxRow) + G78 + (-K78 * G75);
        G78 = -sumCell(sheet, 6, 4, maxRow) - (-K78 * G75);
//        double R78 = sumCell(sheet, 7, 4, maxRow) + H78 + (-K78 * H75);
        H78 = -sumCell(sheet, 7, 4, maxRow) - (-K78 * H75);
//        double S78 = sumCell(sheet, 8, 4, maxRow) + I78 + (-K78 * I75);
        I78 = -sumCell(sheet, 8, 4, maxRow) - (-K78 * I75);

        N1 = K78 - (D78 / D75 + E78 / E75 + F78 / F75 + G78 / G75) / 4;
        System.out.println(N78);

//        double numericCellValue = sheet.getRow(4).getCell(13).getNumericCellValue();
//        System.out.println(numericCellValue);
//        System.out.println(sheet.getRow(74).getCell(13).getNumericCellValue());
//        sheet.getRow(5).getCell(13).setCellValue(0);
//        System.out.println(sheet.getRow(74).getCell(13).getNumericCellValue());

//        FileOutputStream fileOutputStream = new FileOutputStream(file);
//        wb.write(fileOutputStream);
        System.out.println(N1);
        wb.close();
        fileInputStream.close();
    }

    private static double sumCell(Sheet sheet, int cell, int minRow, int maxRow) {
        double sum = 0;
        for (int row = minRow; row < maxRow; row++) {
            sum += (sheet.getRow(row - 1).getCell(cell).getNumericCellValue() * lArr[row - 4]);
        }
        return sum;
    }

}
