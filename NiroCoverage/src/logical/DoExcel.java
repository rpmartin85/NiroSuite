package logical;

import java.io.File;
import java.io.IOException;
import jxl.Cell;
import jxl.CellView;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.Pattern;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class DoExcel
{
  public static void createOutputExcel(String of, String if1, int red, int yellow)
    throws IOException, BiffException, WriteException
  {
    WritableWorkbook wb = Workbook.createWorkbook(new File(of));
    Workbook wb1 = Workbook.getWorkbook(new File(if1));
    
    int numberOfSheet = wb1.getNumberOfSheets();
    
    Sheet[] shWb = new Sheet[numberOfSheet];
    WritableSheet shWbO1 = null;
    for (int sh = 0; sh < shWb.length; sh++)
    {
      shWb[sh] = wb1.getSheet(sh);
      shWbO1 = wb.createSheet(shWb[sh].getName(), sh);
      
      int col = shWb[sh].getColumns();
      int row = shWb[sh].getRows();
      for (int i = 0; i < col; i++)
      {
        Cell cell = shWb[sh].getCell(i, 0);
        Label label = new Label(i, 0, cell.getContents(), getCellFormat(Colour.WHITE, Pattern.GRAY_25));
        shWbO1.addCell(label);
      }
      for (int i = 0; i < row - 1; i++)
      {
        int j = i + 1;
        char flag = 'N';
        
        Cell A = shWb[sh].getCell(0, j);
        Cell B = shWb[sh].getCell(1, j);
        Cell C = shWb[sh].getCell(2, j);
        Cell D = shWb[sh].getCell(3, j);
        Cell E = shWb[sh].getCell(4, j);
        Cell F = shWb[sh].getCell(5, j);
        Cell G = shWb[sh].getCell(6, j);
        Cell H = shWb[sh].getCell(7, j);
        Cell I = shWb[sh].getCell(8, j);
        Cell J = shWb[sh].getCell(9, j);
        Cell K = shWb[sh].getCell(10, j);
        Cell L = shWb[sh].getCell(11, j);
        if ((Integer.parseInt(K.getContents()) <= red) || (Integer.parseInt(L.getContents()) <= red)) {
          flag = 'R';
        } else if ((Integer.parseInt(K.getContents()) <= yellow) || (Integer.parseInt(L.getContents()) <= yellow)) {
          flag = 'Y';
        } else {
          flag = 'G';
        }
        Colour cB;
  
        if (flag == 'R')
        {
          cB = Colour.ROSE;
        }
        else
        {

          if (flag == 'Y')
          {
            cB = Colour.YELLOW2;
          }
          else
          {
 
            if (flag == 'G') {
              cB = Colour.LIGHT_GREEN;
            } else {
              cB = Colour.GRAY_25;
            }
          }
        }
        Label lA = new Label(0, j, A.getContents(), getCellFormat(cB, Pattern.GRAY_25));
        Number lB = new Number(1, j, Long.parseLong(B.getContents()), getCellFormat(cB, Pattern.GRAY_25));
        Number lC = new Number(2, j, Long.parseLong(C.getContents()), getCellFormat(cB, Pattern.GRAY_25));
        Label lD = new Label(3, j, D.getContents(), getCellFormat(cB, Pattern.GRAY_25));
        Label lE = new Label(4, j, E.getContents(), getCellFormat(cB, Pattern.GRAY_25));
        Number lF = new Number(5, j, Integer.parseInt(F.getContents()), getCellFormat(cB, Pattern.GRAY_25));
        Number lG = new Number(6, j, Integer.parseInt(G.getContents()), getCellFormat(cB, Pattern.GRAY_25));
        Number lH = new Number(7, j, Integer.parseInt(H.getContents()), getCellFormat(cB, Pattern.GRAY_25));
        Number lI = new Number(8, j, Integer.parseInt(I.getContents()), getCellFormat(cB, Pattern.GRAY_25));
        Number lJ = new Number(9, j, Integer.parseInt(J.getContents()), getCellFormat(cB, Pattern.GRAY_25));
        Number lK = new Number(10, j, Integer.parseInt(K.getContents()), getCellFormatB(cB, Pattern.GRAY_25));
        Number lL = new Number(11, j, Integer.parseInt(L.getContents()), getCellFormatB(cB, Pattern.GRAY_25));
        
        shWbO1.addCell(lA);
        shWbO1.addCell(lB);
        shWbO1.addCell(lC);
        shWbO1.addCell(lD);
        shWbO1.addCell(lE);
        shWbO1.addCell(lF);
        shWbO1.addCell(lG);
        shWbO1.addCell(lH);
        shWbO1.addCell(lI);
        shWbO1.addCell(lJ);
        shWbO1.addCell(lK);
        shWbO1.addCell(lL);
      }
      sheetAutoFitColumns(shWbO1);
    }
    wb.write();
    
    wb.close();
    wb1.close();
  }
  
  public static void createOutputExcel(String of, String if1, String if2, int red, int yellow)
    throws IOException, BiffException, WriteException
  {
    WritableWorkbook wb = Workbook.createWorkbook(new File(of));
    Workbook wb1 = Workbook.getWorkbook(new File(if1));
    Workbook wb2 = Workbook.getWorkbook(new File(if2));
    Sheet shWb1 = wb1.getSheet(0);
    Sheet shWb2 = wb2.getSheet(0);
    
    WritableSheet shWbO1 = wb.createSheet(shWb1.getName(), 0);
    wb.importSheet(shWb2.getName(), 1, shWb2);
    
    int col = shWb1.getColumns();
    int row = shWb1.getRows();
    for (int i = 0; i < col; i++)
    {
      Cell cell = shWb1.getCell(i, 0);
      Label label = new Label(i, 0, cell.getContents(), getCellFormat(Colour.WHITE, Pattern.GRAY_25));
      shWbO1.addCell(label);
    }
    for (int i = 0; i < row - 1; i++)
    {
      int j = i + 1;
      char flag = 'N';
      
      Cell A = shWb1.getCell(0, j);
      Cell B = shWb1.getCell(1, j);
      Cell C = shWb1.getCell(2, j);
      Cell D = shWb1.getCell(3, j);
      Cell E = shWb1.getCell(4, j);
      Cell F = shWb1.getCell(5, j);
      Cell G = shWb1.getCell(6, j);
      Cell H = shWb1.getCell(7, j);
      Cell I = shWb1.getCell(8, j);
      Cell J = shWb1.getCell(9, j);
      Cell K = shWb1.getCell(10, j);
      Cell L = shWb1.getCell(11, j);
      if ((Integer.parseInt(K.getContents()) <= red) || (Integer.parseInt(L.getContents()) <= red)) {
        flag = 'R';
      } else if ((Integer.parseInt(K.getContents()) <= yellow) || (Integer.parseInt(L.getContents()) <= yellow)) {
        flag = 'Y';
      } else {
        flag = 'G';
      }
      Colour cB;

      if (flag == 'R')
      {
        cB = Colour.ROSE;
      }
      else
      {

        if (flag == 'Y')
        {
          cB = Colour.YELLOW2;
        }
        else
        {

          if (flag == 'G') {
            cB = Colour.LIGHT_GREEN;
          } else {
            cB = Colour.GRAY_25;
          }
        }
      }
      Label lA = new Label(0, j, A.getContents(), getCellFormat(cB, Pattern.GRAY_25));
      Number lB = new Number(1, j, Long.parseLong(B.getContents()), getCellFormat(cB, Pattern.GRAY_25));
      Number lC = new Number(2, j, Long.parseLong(C.getContents()), getCellFormat(cB, Pattern.GRAY_25));
      Label lD = new Label(3, j, D.getContents(), getCellFormat(cB, Pattern.GRAY_25));
      Label lE = new Label(4, j, E.getContents(), getCellFormat(cB, Pattern.GRAY_25));
      Number lF = new Number(5, j, Integer.parseInt(F.getContents()), getCellFormat(cB, Pattern.GRAY_25));
      Number lG = new Number(6, j, Integer.parseInt(G.getContents()), getCellFormat(cB, Pattern.GRAY_25));
      Number lH = new Number(7, j, Integer.parseInt(H.getContents()), getCellFormat(cB, Pattern.GRAY_25));
      Number lI = new Number(8, j, Integer.parseInt(I.getContents()), getCellFormat(cB, Pattern.GRAY_25));
      Number lJ = new Number(9, j, Integer.parseInt(J.getContents()), getCellFormat(cB, Pattern.GRAY_25));
      Number lK = new Number(10, j, Integer.parseInt(K.getContents()), getCellFormatB(cB, Pattern.GRAY_25));
      Number lL = new Number(11, j, Integer.parseInt(L.getContents()), getCellFormatB(cB, Pattern.GRAY_25));
      
      shWbO1.addCell(lA);
      shWbO1.addCell(lB);
      shWbO1.addCell(lC);
      shWbO1.addCell(lD);
      shWbO1.addCell(lE);
      shWbO1.addCell(lF);
      shWbO1.addCell(lG);
      shWbO1.addCell(lH);
      shWbO1.addCell(lI);
      shWbO1.addCell(lJ);
      shWbO1.addCell(lK);
      shWbO1.addCell(lL);
    }
    sheetAutoFitColumns(shWbO1);
    
    wb.write();
    
    wb.close();
    wb1.close();
    wb2.close();
  }
  
  private static WritableCellFormat getCellFormat(Colour colour, Pattern pattern)
    throws WriteException
  {
    WritableFont cellFont = new WritableFont(WritableFont.ARIAL, 12);
    WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
    cellFormat.setBackground(colour, Pattern.SOLID);
    cellFormat.setAlignment(Alignment.CENTRE);
    cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
    
    return cellFormat;
  }
  
  private static WritableCellFormat getCellFormatB(Colour colour, Pattern pattern)
    throws WriteException
  {
    WritableFont cellFont = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD);
    WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
    cellFormat.setBackground(colour, Pattern.SOLID);
    cellFormat.setAlignment(Alignment.CENTRE);
    cellFormat.setBorder(Border.ALL, BorderLineStyle.MEDIUM);
    return cellFormat;
  }
  
  private static void sheetAutoFitColumns(WritableSheet sheet)
  {
    for (int i = 0; i < sheet.getColumns(); i++)
    {
      Cell[] cells = sheet.getColumn(i);
      int longestStrLen = -1;
      if (cells.length != 0)
      {
        for (int j = 0; j < cells.length; j++) {
          if (cells[j].getContents().length() > longestStrLen)
          {
            String str = cells[j].getContents();
            if ((str != null) && (!str.isEmpty())) {
              longestStrLen = str.trim().length();
            }
          }
        }
        if (longestStrLen != -1)
        {
          if (longestStrLen > 255) {
            longestStrLen = 255;
          }
          CellView cv = sheet.getColumnView(i);
          cv.setSize(longestStrLen * 256 + 2000);
          sheet.setColumnView(i, cv);
        }
      }
    }
  }
}
