package screen;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ScAbout
  extends JFrame
{
  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
  
  public ScAbout()
  {
    setTitle("About Niro");
    setDefaultCloseOperation(2);
    setBounds(100, 100, 450, 300);
    this.contentPane = new JPanel();
    this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    this.contentPane.setLayout(null);
    setContentPane(this.contentPane);
    
    JLabel lblAboutNiro = new JLabel("Niro Application ver 0.10");
    lblAboutNiro.setFont(new Font("Lucida Grande", 0, 20));
    lblAboutNiro.setBounds(106, 158, 243, 35);
    this.contentPane.add(lblAboutNiro);
    
    JLabel lblDevelopers = new JLabel("Developed by:");
    lblDevelopers.setBounds(26, 217, 121, 16);
    this.contentPane.add(lblDevelopers);
    
    JLabel lblRenan = new JLabel("Renan Paulo Martin");
    lblRenan.setBounds(174, 217, 195, 16);
    this.contentPane.add(lblRenan);
    
    JLabel lblRafael = new JLabel("Rafael Filippelli da Silva");
    lblRafael.setBounds(174, 245, 195, 16);
    this.contentPane.add(lblRafael);
    
    JLabel lblIconMini = new JLabel("");
    lblIconMini.setBounds(158, 6, 146, 124);
    lblIconMini.setIcon(new ImageIcon(StartScreen.class.getResource("/img/niroMini.png")));
    
    this.contentPane.add(lblIconMini);
    
    JLabel lblNewIonResults = new JLabel("New Ion Results Optimizer");
    lblNewIonResults.setBounds(146, 130, 175, 16);
    this.contentPane.add(lblNewIonResults);
  }
}
