package logical;

public class FileAprov
{
  public static final String[] excel = { "xls", "xlsx", "xlsm", "xlsb", "xltx", "xltm", "xlt", "xml", "xlam", "xla", "xlw", "tsv" };
  
  public static boolean excel(String ext)
  {
    for (int i = 0; i < excel.length; i++) {
      if (ext.equals(excel[i]))
      {
        System.out.println("true");
        return true;
      }
    }
    System.out.println("false");
    return false;
  }
}
