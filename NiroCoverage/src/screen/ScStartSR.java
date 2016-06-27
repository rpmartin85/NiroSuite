package screen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import logical.DoExcel;
import logical.FileAprov;
import logical.Parameters;

public class ScStartSR
  extends JFrame
{
  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
  private JTextField txtFileOut;
  private final String NO_FILE_SELECTED = "No file was Selected, please choose one to continue.";
  public String getNO_FILE_SELECTED() {
	return NO_FILE_SELECTED;
}

private JFileChooser fc;
  private static File file;
  private Parameters param;
  
  public void setParam(Parameters param)
  {
    this.param = param;
  }
  
  public ScStartSR()
  {
    setTitle("Start Analysis");
    setDefaultCloseOperation(2);
    setBounds(100, 100, 450, 270);
    this.contentPane = new JPanel();
    this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(this.contentPane);
    this.contentPane.setLayout(null);
    
    JLabel lblSetTheOutput = new JLabel(
      "Set the output file name wich will contain the murged files.");
    lblSetTheOutput.setBounds(31, 18, 394, 16);
    this.contentPane.add(lblSetTheOutput);
    
    this.txtFileOut = new JTextField();
    this.txtFileOut.setEnabled(false);
    this.txtFileOut.setBounds(31, 80, 383, 28);
    this.contentPane.add(this.txtFileOut);
    this.txtFileOut.setColumns(10);
    this.txtFileOut.setText("No file was Selected, please choose one to continue.");
    
    this.fc = new JFileChooser();
    this.fc.setFileSelectionMode(0);
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
      "XLS files", new String[] { "xls", "xlsx", "xlsm", "xlsb", "xltx", "xltm", 
      "xlt", "xml", "xlam", "xla", "xlw" });
    this.fc.addChoosableFileFilter(filter);
    
    JButton btnChooseFile = new JButton("Choose File");
    btnChooseFile.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        int returnVal = ScStartSR.this.fc.showSaveDialog(ScStartSR.this);
        if (returnVal == 0)
        {
          ScStartSR.file = ScStartSR.this.fc.getSelectedFile();
          String ext = "";
          ext = ext + ScStartSR.getExtension(ScStartSR.file);
          if (ext.equals("")) {
            ext = ".xls";
          }
          if (FileAprov.excel(ext))
          {
            ScStartSR.this.txtFileOut.setText(ScStartSR.file.getAbsolutePath());
          }
          else
          {
            ScStartSR.this.txtFileOut.setText(ScStartSR.file.getAbsolutePath() + ".xls");
            ScStartSR.file.setWritable(true);
          }
        }
        else
        {
          System.out.println("Calcelled by user.");
        }
      }
    });
    btnChooseFile.setBounds(170, 126, 117, 29);
    this.contentPane.add(btnChooseFile);
    
    JButton btnOk = new JButton("OK");
    btnOk.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        File f1 = new File(ScStartSR.this.param.getInputPathCoverAna());
        String of = ScStartSR.file.getAbsolutePath() + ".xls";
        try
        {
          DoExcel.createOutputExcel(of, f1.getAbsolutePath(), ScStartSR.this.param.getRedParam(), ScStartSR.this.param.getYellowParam());
          StartScreen.clearMenu();
          JOptionPane.showMessageDialog(null, "Analisis successfully!!!");
        }
        catch (BiffException e2)
        {
          e2.printStackTrace();
        }
        catch (WriteException e2)
        {
          e2.printStackTrace();
        }
        catch (IOException e2)
        {
          e2.printStackTrace();
        }
        ScStartSR.this.dispose();
      }
    });
    btnOk.setBounds(31, 194, 117, 29);
    this.contentPane.add(btnOk);
    
    JButton btnCancel = new JButton("Cancel");
    btnCancel.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        ScStartSR.this.dispose();
      }
    });
    btnCancel.setBounds(308, 194, 117, 29);
    this.contentPane.add(btnCancel);
    
    JButton btnClear = new JButton("Clear");
    btnClear.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        ScStartSR.this.txtFileOut.setText("No file was Selected, please choose one to continue.");
      }
    });
    btnClear.setBounds(170, 194, 117, 29);
    this.contentPane.add(btnClear);
  }
  
  public static String getExtension(File f)
  {
    String ext = null;
    String s = f.getName();
    int i = s.lastIndexOf('.');
    if ((i > 0) && (i < s.length() - 1)) {
      ext = s.substring(i + 1).toLowerCase();
    }
    return ext;
  }
}
