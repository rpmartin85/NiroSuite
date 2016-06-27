package screen;

import java.awt.Desktop;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;

public class ScAbout extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public ScAbout(Rectangle r) {
		
		setResizable(false);

		setTitle("About Niro Annotations");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds((int)r.getX(), (int)r.getY(), 578, 504);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblAboutNiro = new JLabel("Niro Annotations 1.0");
		lblAboutNiro.setHorizontalAlignment(SwingConstants.CENTER);
		lblAboutNiro.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblAboutNiro.setBounds(37, 158, 507, 35);
		contentPane.add(lblAboutNiro);

		JLabel lblDevelopers = new JLabel("Developed by:");
		lblDevelopers.setBounds(26, 205, 121, 25);
		contentPane.add(lblDevelopers);

		JLabel lblRenan = new JLabel("Renan Paulo Martin");
		lblRenan.setHorizontalAlignment(SwingConstants.LEFT);
		lblRenan.setBounds(239, 206, 195, 25);
		contentPane.add(lblRenan);

		JLabel lblRafael = new JLabel("Rafael Filippelli da Silva");
		lblRafael.setHorizontalAlignment(SwingConstants.LEFT);
		lblRafael.setBounds(239, 244, 195, 25);
		contentPane.add(lblRafael);

		JButton btnNiro = new JButton("");
		btnNiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String url = "http://www.niroproject.com";
				openWebpage(url);
				
				
			}
		});
		btnNiro.setBounds(218, 6, 146, 119);
		btnNiro.setIcon(new ImageIcon(StartScreen.class.getResource("/img/niroMini.png")));

		contentPane.add(btnNiro);

		JLabel lblNewIonResults = new JLabel("NextGenSequencing Info Reports Optimizer");
		lblNewIonResults.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewIonResults.setBounds(47, 130, 497, 25);
		contentPane.add(lblNewIonResults);
		
		JLabel lblJoao = new JLabel("João Bosco Pesquero");
		lblJoao.setHorizontalAlignment(SwingConstants.LEFT);
		lblJoao.setBounds(239, 282, 195, 25);
		contentPane.add(lblJoao);
		
		JLabel lblSupporters = new JLabel("Supported by:");
		lblSupporters.setBounds(26, 311, 121, 25);
		contentPane.add(lblSupporters);
		
		JLabel lblFapespRPM = new JLabel("FAPESP 2014/20965-3");
		lblFapespRPM.setBounds(26, 441, 195, 25);
		contentPane.add(lblFapespRPM);
		
		JLabel lblFapespTem = new JLabel("FAPESP 2014/27198-8 ");
		lblFapespTem.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFapespTem.setBounds(349, 441, 195, 25);
		contentPane.add(lblFapespTem);
		
		JButton btnFAPESP = new JButton("");
		btnFAPESP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
					String url = "http://www.fapesp.br";
					openWebpage(url);
				
				
				
				
			}
		});
		btnFAPESP.setIcon(new ImageIcon(ScAbout.class.getResource("/img/lg_fapesp.gif")));
		btnFAPESP.setBounds(26, 355, 124, 28);
		contentPane.add(btnFAPESP);
		
		JButton btnUNIFESP = new JButton("");
		btnUNIFESP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String url = "http://www.unifesp.br";
				openWebpage(url);
				
			}
		});
		btnUNIFESP.setIcon(new ImageIcon(ScAbout.class.getResource("/img/logo_unifesp.png")));
		btnUNIFESP.setBounds(431, 331, 113, 67);
		contentPane.add(btnUNIFESP);
		
		JButton btnLabpesq = new JButton("");
		btnLabpesq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String url = "http://www.labjbpesquero.com.br";
				openWebpage(url);
			
				
			}
		});
		btnLabpesq.setIcon(new ImageIcon(ScAbout.class.getResource("/img/jbpesquero_logo.png")));
		btnLabpesq.setBounds(203, 337, 174, 61);
		contentPane.add(btnLabpesq);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		btnClose.setBounds(230, 440, 117, 29);
		contentPane.add(btnClose);
		
		JButton btnLattesrpm = new JButton("");
		btnLattesrpm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String url = "http://lattes.cnpq.br/4990323379616804";
				openWebpage(url);
				
			}
		});
		btnLattesrpm.setIcon(new ImageIcon(ScAbout.class.getResource("/img/plataforma-lattes-logo.jpg")));
		btnLattesrpm.setBounds(456, 205, 21, 25);
		contentPane.add(btnLattesrpm);
		
		JButton btnLattesrfs = new JButton("");
		btnLattesrfs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String url = "http://lattes.cnpq.br/6408852011134966";
				openWebpage(url);
				
			}
		});
		btnLattesrfs.setIcon(new ImageIcon(ScAbout.class.getResource("/img/plataforma-lattes-logo.jpg")));
		btnLattesrfs.setBounds(456, 243, 21, 25);
		contentPane.add(btnLattesrfs);
		
		JButton btnLattesjbp = new JButton("");
		btnLattesjbp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String url = "http://lattes.cnpq.br/0856630824759511";
				openWebpage(url);
			}
		});
		btnLattesjbp.setIcon(new ImageIcon(ScAbout.class.getResource("/img/plataforma-lattes-logo.jpg")));
		btnLattesjbp.setBounds(456, 281, 21, 25);
		contentPane.add(btnLattesjbp);

	}
	
	public static void openWebpage(String urlString) {
	    try {
	        Desktop.getDesktop().browse(new URL(urlString).toURI());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	
}
