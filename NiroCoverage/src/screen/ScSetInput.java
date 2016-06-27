package screen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import logical.FileAprov;
import logical.Parameters;

public class ScSetInput
  extends JFrame
{
  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
  private JFileChooser fc1;
  private JFileChooser fc2;
  private JLabel lblFile1;
  private JLabel lblFile2;
  private final String NO_FILE_SELECTED = "No file was Selected, please choose one to continue.";
  public String getNO_FILE_SELECTED() {
	return NO_FILE_SELECTED;
}

public Parameters param;
  
  public void setParam(Parameters param)
  {
    this.param = param;
    if (param.getInputPathCoverAna() != null) {
      this.lblFile1.setText(param.getInputPathCoverAna());
    }
    if (param.getInputPathVarCaller() != null) {
      this.lblFile2.setText(param.getInputPathVarCaller());
    }
  }
  
  public ScSetInput()
  {
    setTitle("Set Input Files");
    setDefaultCloseOperation(3);
    setBounds(100, 100, 570, 360);
    this.contentPane = new JPanel();
    this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(this.contentPane);
    this.contentPane.setLayout(null);
    
    JLabel lblSetInput = new JLabel(
      "Set the input files to merge them into a single xls file.");
    lblSetInput.setBounds(31, 20, 380, 16);
    this.contentPane.add(lblSetInput);
    
    JLabel lblSet = new JLabel("Set Cover Analysis File Path:");
    lblSet.setBounds(31, 78, 207, 16);
    this.contentPane.add(lblSet);
    
    JLabel lblSetVariantCaller = new JLabel("Set Variant Caller File Path:");
    lblSetVariantCaller.setBounds(31, 163, 207, 16);
    this.contentPane.add(lblSetVariantCaller);
    
    this.fc1 = new JFileChooser();
    this.fc1.setFileSelectionMode(0);
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
      "XLS files", new String[] { "xls", "xlsx", "xlsm", "xlsb", "xltx", "xltm", 
      "xlt", "xml", "xlam", "xla", "xlw" });
    this.fc1.addChoosableFileFilter(filter);
    this.fc2 = new JFileChooser();
    this.fc2.setFileSelectionMode(0);
    this.fc2.addChoosableFileFilter(filter);
    
    JButton btnChooseFile1 = new JButton("Choose File");
    btnChooseFile1.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        int returnVal = ScSetInput.this.fc1.showOpenDialog(ScSetInput.this);
        if (returnVal == 0)
        {
          File file = ScSetInput.this.fc1.getSelectedFile();
          String ext = ScSetInput.getExtension(file);
          if (FileAprov.excel(ext)) {
            ScSetInput.this.lblFile1.setText(file.getAbsolutePath());
          } else {
            JOptionPane.showMessageDialog(null, 
              "Please select an excel file type.");
          }
        }
        else
        {
          System.out.println("Calcelled by user.");
        }
      }
    });
    btnChooseFile1.setBounds(241, 73, 117, 29);
    this.contentPane.add(btnChooseFile1);
    
    this.lblFile1 = new JLabel("No file was Selected, please choose one to continue.");
    this.lblFile1.setBounds(77, 104, 455, 16);
    this.contentPane.add(this.lblFile1);
    
    this.lblFile2 = new JLabel("No file was Selected, please choose one to continue.");
    this.lblFile2.setBounds(77, 191, 441, 16);
    this.contentPane.add(this.lblFile2);
    
    JButton btnChooseFile2 = new JButton("Choose File");
    btnChooseFile2.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        int returnVal = ScSetInput.this.fc2.showOpenDialog(ScSetInput.this);
        if (returnVal == 0)
        {
          File file = ScSetInput.this.fc2.getSelectedFile();
          String ext = ScSetInput.getExtension(file);
          if (FileAprov.excel(ext)) {
            ScSetInput.this.lblFile2.setText(file.getAbsolutePath());
          } else {
            JOptionPane.showMessageDialog(null, 
              "Please select an excel file type.");
          }
        }
        else
        {
          System.out.println("Cancelled by user.");
        }
      }
    });
    btnChooseFile2.setBounds(241, 158, 117, 29);
    this.contentPane.add(btnChooseFile2);
    
    JButton btnOk = new JButton("OK");
    btnOk.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        if (ScSetInput.this.lblFile1.getText().equals("No file was Selected, please choose one to continue."))
        {
          ScSetInput.this.param.setInputPathCoverAna(null);
          StartScreen.clearMenu();
        }
        else
        {
          ScSetInput.this.param.setInputPathCoverAna(ScSetInput.this.lblFile1.getText());
        }
        if (ScSetInput.this.lblFile2.getText().equals("No file was Selected, please choose one to continue."))
        {
          ScSetInput.this.param.setInputPathVarCaller(null);
          StartScreen.disableStart();
        }
        else
        {
          ScSetInput.this.param.setInputPathVarCaller(ScSetInput.this.lblFile2.getText());
        }
        if (!ScSetInput.this.lblFile2.getText().equals("No file was Selected, please choose one to continue.")) {
          StartScreen.enableStart();
        }
        if (!ScSetInput.this.lblFile1.getText().equals("No file was Selected, please choose one to continue.")) {
          StartScreen.enableStartMR();
        } else {
          StartScreen.disableStart();
        }
        ScSetInput.this.dispose();
      }
    });
    btnOk.setBounds(98, 251, 117, 29);
    this.contentPane.add(btnOk);
    
    JButton btnCancel = new JButton("Cancel");
    btnCancel.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        ScSetInput.this.dispose();
      }
    });
    btnCancel.setBounds(347, 251, 117, 29);
    this.contentPane.add(btnCancel);
    
    JButton btnClear = new JButton("Clear");
    btnClear.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        ScSetInput.this.lblFile1.setText("No file was Selected, please choose one to continue.");
        ScSetInput.this.lblFile2.setText("No file was Selected, please choose one to continue.");
        ScSetInput.this.param.setInputPathCoverAna(null);
        ScSetInput.this.param.setInputPathVarCaller(null);
        StartScreen.clearMenu();
      }
    });
    btnClear.setBounds(227, 251, 117, 29);
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
