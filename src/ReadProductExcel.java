import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class ReadProductExcel {
    public Product[] readProductExcel( InputStream kl) {
        Product products[]=null;
        try {
            XSSFWorkbook xw = new XSSFWorkbook(kl);
            XSSFSheet xs = xw.getSheetAt(0);
            products=new Product[xs.getLastRowNum()];
            for (int j = 1; j <= xs.getLastRowNum(); j++) {
                XSSFRow row = xs.getRow(j);
                Product product = new Product();//每循环一次就把电子表格的一行的数据给对象赋值
                for (int k = 0; k <= row.getLastCellNum(); k++) {
                    XSSFCell cell = row.getCell(k);
                    if (cell == null)
                        continue;
                    if (k == 0) {
                        product.setId(this.getValue(cell));//给username属性赋值
                    } else if (k == 1) {
                        product.setName(this.getValue(cell));//给password属性赋值
                    } else if (k == 2) {
                        product.setPricr(Float.valueOf(this.getValue(cell)));//给address属性赋值
                    } else if (k == 3) {
                        product.setMs(this.getValue(cell));//给phone属性赋值
                    }
                }
               products[j-1]=product;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    public Product readProductId( String vb,InputStream kl) {
        try {
            XSSFWorkbook xw = new XSSFWorkbook(kl);
            XSSFSheet xs = xw.getSheetAt(0);
            for (int j = 1; j <= xs.getLastRowNum(); j++) {
                XSSFRow row = xs.getRow(j);
                Product product = new Product();//每循环一次就把电子表格的一行的数据给对象赋值
                for (int k = 0; k <= row.getLastCellNum(); k++) {
                    XSSFCell cell = row.getCell(k);
                    if (cell == null)
                        continue;
                    if (k == 0) {
                        product.setId(this.getValue(cell));//给username属性赋值
                    } else if (k == 1) {
                        product.setName(this.getValue(cell));//给password属性赋值
                    } else if (k == 2) {
                        product.setPricr(Float.valueOf(this.getValue(cell)));//给address属性赋值
                    } else if (k == 3) {
                        product.setMs(this.getValue(cell));//给phone属性赋值
                    }
                }
                if(vb.equals(product.getId())){
                    return product;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getValue(XSSFCell cell) {
        String value;
        CellType type = cell.getCellTypeEnum();

        switch (type) {
            case STRING:
                value = cell.getStringCellValue();
                break;
            case BLANK:
                value = "";
                break;
            case BOOLEAN:
                value = cell.getBooleanCellValue() + "";
                break;
            case NUMERIC:
                value = cell.getNumericCellValue() + "";
                DecimalFormat df=new DecimalFormat("#");
                value=df.format(cell.getNumericCellValue());
                break;
            case FORMULA:
                value = cell.getCellFormula();
                break;
            case ERROR:
                value = "非法字符";
                break;
            default:
                value = "";
                break;
        }
        return value;
    }
}
