package screen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import logical.Parameters;

public class ScPreferences
  extends JFrame
{
  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
  private JSpinner spinnerRed;
  private JSpinner spinnerYellow;
  private Parameters param;
  
  public void setParam(Parameters param)
  {
    this.param = param;
    this.spinnerRed.setValue(Integer.valueOf(this.param.getRedParam()));
    this.spinnerYellow.setValue(Integer.valueOf(this.param.getYellowParam()));
  }
  
  public ScPreferences()
  {
    setTitle("Preferences");
    setDefaultCloseOperation(2);
    setBounds(100, 100, 501, 388);
    this.contentPane = new JPanel();
    this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(this.contentPane);
    this.contentPane.setLayout(null);
    
    JLabel lblPrefHead = new JLabel("Set the analysis paramters by increase or decrease the numbers below.");
    lblPrefHead.setHorizontalAlignment(2);
    lblPrefHead.setBounds(34, 30, 444, 16);
    this.contentPane.add(lblPrefHead);
    
    this.spinnerRed = new JSpinner();
    this.spinnerRed.setBounds(366, 97, 64, 35);
    
    this.contentPane.add(this.spinnerRed);
    
    this.spinnerYellow = new JSpinner();
    this.spinnerYellow.setBounds(366, 149, 64, 35);
    
    this.contentPane.add(this.spinnerYellow);
    
    JLabel lblSetRed = new JLabel("Set the MAX value to highlight in RED");
    lblSetRed.setBounds(78, 106, 260, 16);
    this.contentPane.add(lblSetRed);
    
    JLabel lblSetYellow = new JLabel("Set the MAX value to highlight in YELLOW");
    lblSetYellow.setBounds(78, 158, 276, 16);
    this.contentPane.add(lblSetYellow);
    
    JLabel lblOther = new JLabel("All other values will be highlighted in GREEN");
    lblOther.setBounds(78, 209, 352, 16);
    this.contentPane.add(lblOther);
    
    JButton btnPrefOK = new JButton("OK");
    btnPrefOK.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        int redSpinner = Integer.parseInt(ScPreferences.this.spinnerRed.getValue().toString());
        int yellowSpinner = Integer.parseInt(ScPreferences.this.spinnerYellow.getValue().toString());
        if ((redSpinner < 1) || (yellowSpinner < 1))
        {
          JOptionPane.showMessageDialog(null, "Do not use negative values, please set the values again.");
        }
        else if (yellowSpinner <= redSpinner)
        {
          JOptionPane.showMessageDialog(null, "Do not use YELLOW values lower than RED values.");
        }
        else
        {
          ScPreferences.this.param.setRedParam(redSpinner);
          ScPreferences.this.param.setYellowParam(yellowSpinner);
          ScPreferences.this.dispose();
        }
      }
    });
    btnPrefOK.setBounds(34, 291, 117, 29);
    this.contentPane.add(btnPrefOK);
    
    JButton btnPrefResDef = new JButton("Restore Default");
    btnPrefResDef.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        Parameters param = new Parameters();
        ScPreferences.this.spinnerRed.setValue(Integer.valueOf(param.getRedParam()));
        ScPreferences.this.spinnerYellow.setValue(Integer.valueOf(param.getYellowParam()));
      }
    });
    btnPrefResDef.setBounds(176, 291, 140, 29);
    this.contentPane.add(btnPrefResDef);
    
    JButton btnCancel = new JButton("Cancel");
    btnCancel.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        ScPreferences.this.dispose();
      }
    });
    btnCancel.setBounds(346, 291, 117, 29);
    this.contentPane.add(btnCancel);
  }
}
