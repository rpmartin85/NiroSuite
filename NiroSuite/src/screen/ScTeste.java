package screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;

public class ScTeste extends JFrame {

	private JPanel jPAbout;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScTeste frame = new ScTeste();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ScTeste() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(15, 75, 770, 510);
		jPAbout = new JPanel();
		//jPAbout.setBorder(new EmptyBorder(5, 5, 5, 5));
		jPAbout.setLayout(null);
		setContentPane(jPAbout);
		
		
		//		[x=15,y=75,width=770,height=510]

		
		jPAbout.setBounds(15, 75, 770, 510);
		
		
		
		
		
		JLabel lblNiroFull;
		JLabel lblNiroShort;
		JLabel label_2;
		JLabel label_3;
		JLabel label_4;
		JLabel label_5;
		JLabel label_6;
		JLabel label_7;
		JLabel label_8;
		
		
//		JPanel jPAbout = new JPanel();
		
		jPAbout.setBackground(new Color(250, 250, 250));
//		frame.getContentPane().add(jPAbout);
		jPAbout.setLayout(null);
		
		
		
		label_2 = new JLabel("Developed by:");
		label_2.setBounds(20, 196, 121, 25);
		jPAbout.add(label_2);
		
		label_3 = new JLabel("Renan Paulo Martin");
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setBounds(140, 233, 195, 25);
		jPAbout.add(label_3);
		
		label_4 = new JLabel("Rafael Filippelli da Silva");
		label_4.setHorizontalAlignment(SwingConstants.LEFT);
		label_4.setBounds(140, 271, 195, 25);
		jPAbout.add(label_4);
		
		label_5 = new JLabel("João Bosco Pesquero");
		label_5.setHorizontalAlignment(SwingConstants.LEFT);
		label_5.setBounds(140, 309, 195, 25);
		jPAbout.add(label_5);
		
		label_6 = new JLabel("Supported by:");
		label_6.setBounds(20, 346, 121, 25);
		jPAbout.add(label_6);
		
		label_7 = new JLabel("FAPESP 2014/20965-3");
		label_7.setBounds(20, jPAbout.getHeight()-50, 195, 25);
		jPAbout.add(label_7);
		
		label_8 = new JLabel("FAPESP 2014/27198-8 ");
		label_8.setHorizontalAlignment(SwingConstants.RIGHT);
		label_8.setBounds(550, 466, 195, 25);
		label_8.setBounds(jPAbout.getWidth() - label_8.getWidth() - 20, jPAbout.getHeight()-50, 195, 25);
		jPAbout.add(label_8);
		
		
		
	
		
		
		
		
		JButton btnFapesp = new JButton("");
		btnFapesp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String url = "http://www.fapesp.br";
				openWebpage(url);
				
			}
		});
		btnFapesp.setIcon(new ImageIcon(ScStartScreen.class.getResource("/img/lg_fapesp.gif")));
		btnFapesp.setBounds(46, 400, 124, 28);
		jPAbout.add(btnFapesp);
		
		JButton btnLabPesq = new JButton("");
		btnLabPesq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String url = "http://www.labjbpesquero.com.br";
				openWebpage(url);
				
			}
		});
		btnLabPesq.setIcon(new ImageIcon(ScStartScreen.class.getResource("/img/jbpesquero_logo.png")));
		btnLabPesq.setBounds(292, 391, 174, 61);
		jPAbout.add(btnLabPesq);
		
		JButton btnUnifesp = new JButton("");
		btnUnifesp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String url = "http://www.unifesp.br";
				openWebpage(url);
				
			}
		});
		btnUnifesp.setIcon(new ImageIcon(ScStartScreen.class.getResource("/img/logo_unifesp.png")));
		btnUnifesp.setBounds(594, 391, 113, 67);
		jPAbout.add(btnUnifesp);
		
		
		
		int sumBtnWidth = btnFapesp.getWidth() + btnLabPesq.getWidth() + btnUnifesp.getWidth();
		int diff = jPAbout.getWidth() - sumBtnWidth;
		int space = diff/4;
		
		System.out.println(space);
		
		btnFapesp.setBounds((space), btnFapesp.getY(), btnFapesp.getWidth(), btnFapesp.getHeight());
		btnLabPesq.setBounds(btnFapesp.getX() + space + btnFapesp.getWidth(), btnFapesp.getY() - btnLabPesq.getHeight()/2 + btnFapesp.getHeight()/2, btnLabPesq.getWidth(), btnLabPesq.getHeight());
		btnUnifesp.setBounds(btnLabPesq.getX() + space + btnLabPesq.getWidth(), btnFapesp.getY() - btnUnifesp.getHeight()/2 + btnFapesp.getHeight()/2, btnUnifesp.getWidth(), btnUnifesp.getHeight());
		
		
		
		
		
		
		
		
		JButton btnLogoNiro = new JButton("");
		btnLogoNiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String url = "http://www.niroproject.com";
				openWebpage(url);
				
				
				
			}
		});
		btnLogoNiro.setIcon(new ImageIcon(ScStartScreen.class.getResource("/img/niroMini.png")));
		btnLogoNiro.setBounds(313, 133, 146, 119);
		btnLogoNiro.setBounds((jPAbout.getWidth()-btnLogoNiro.getWidth())/2, 20, 146, 119);

		jPAbout.add(btnLogoNiro);
		
		
		lblNiroFull = new JLabel("NextGenSequencing Info Reports Optimizer");
		lblNiroFull.setHorizontalAlignment(SwingConstants.CENTER);
		lblNiroFull.setBounds(140, 135, 497, 25);
		lblNiroFull.setBounds((jPAbout.getWidth()-lblNiroFull.getWidth())/2, btnLogoNiro.getHeight() + btnLogoNiro.getY() + 5, 497, 25);

		jPAbout.add(lblNiroFull);
		
		lblNiroShort = new JLabel("Niro Suite 1.0");
		lblNiroShort.setHorizontalAlignment(SwingConstants.CENTER);
		lblNiroShort.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNiroShort.setBounds(140, 166, 507, 35);
		lblNiroShort.setBounds((jPAbout.getWidth()-lblNiroShort.getWidth())/2, lblNiroFull.getHeight() + lblNiroFull.getY() + 2, 507, 35);

		
		jPAbout.add(lblNiroShort);
		
		
		
		JButton btnLattesRPM = new JButton("");
		btnLattesRPM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String url = "http://lattes.cnpq.br/4990323379616804";
				openWebpage(url);
			}
		});
		btnLattesRPM.setIcon(new ImageIcon(ScStartScreen.class.getResource("/img/plataforma-lattes-logo.jpg")));
		btnLattesRPM.setBounds(347, 233, 21, 25);
		jPAbout.add(btnLattesRPM);
		
		JButton btnLattesRFS = new JButton("");
		btnLattesRFS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String url = "http://lattes.cnpq.br/6408852011134966";
				openWebpage(url);
				
			}
		});
		btnLattesRFS.setIcon(new ImageIcon(ScStartScreen.class.getResource("/img/plataforma-lattes-logo.jpg")));
		btnLattesRFS.setBounds(347, 271, 21, 25);
		jPAbout.add(btnLattesRFS);
		
		JButton btnLattesJBP = new JButton("");
		btnLattesJBP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String url = "http://lattes.cnpq.br/0856630824759511";
				openWebpage(url);
				
			}
		});
		btnLattesJBP.setIcon(new ImageIcon(ScStartScreen.class.getResource("/img/plataforma-lattes-logo.jpg")));
		btnLattesJBP.setBounds(347, 309, 21, 25);
		jPAbout.add(btnLattesJBP);
		
		
		jPAbout.setVisible(false);
	

		
		
		
		
		
		
		
	}
	
	public static void openWebpage(String urlString) {
	    try {
	        Desktop.getDesktop().browse(new URL(urlString).toURI());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
