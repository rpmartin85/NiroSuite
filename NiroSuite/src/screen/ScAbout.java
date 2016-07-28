package screen;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ScAbout {

	public static JPanel setAbout(JPanel jPAbout, JFrame frame, Rectangle header) {

		JLabel lblNiroFull;
		JLabel lblNiroShort;
		JLabel label_2;
		JLabel label_3;
		JLabel label_4;
		JLabel label_5;
		JLabel label_6;
		JLabel label_7;
		JLabel label_8;

		

		jPAbout.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(jPAbout);
		jPAbout.setLayout(null);

		jPAbout.setBounds(15, header.height + 15, frame.getWidth() - 30, frame.getHeight() - 30 - header.height);

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
		
		

		label_7 = new JLabel("Processo FAPESP 2014/20965-3");
		label_7.setBounds(20, jPAbout.getHeight() - 35, 250, 25);
		jPAbout.add(label_7);

		label_8 = new JLabel("Processo FAPESP 2014/27198-8");
		label_8.setHorizontalAlignment(SwingConstants.RIGHT);
		label_8.setBounds(550, 466, 250, 25);
		jPAbout.add(label_8);
		
		
		int sumBtnWidth = label_7.getWidth() + label_8.getWidth();
		int diff = jPAbout.getWidth() - sumBtnWidth;
		int space = diff / 3;

		label_7.setBounds((space), label_7.getY(), label_7.getWidth(), label_7.getHeight());
		label_8.setBounds(label_7.getX() + space + label_7.getWidth(),
				label_7.getY() - label_8.getHeight() / 2 + label_7.getHeight() / 2, label_8.getWidth(),
				label_8.getHeight());
		
		
		
		
		
		
		
		
		
		
		
		
		
		

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

		sumBtnWidth = btnFapesp.getWidth() + btnLabPesq.getWidth() + btnUnifesp.getWidth();
		diff = jPAbout.getWidth() - sumBtnWidth;
		space = diff / 4;

		btnFapesp.setBounds((space), btnFapesp.getY(), btnFapesp.getWidth(), btnFapesp.getHeight());
		btnLabPesq.setBounds(btnFapesp.getX() + space + btnFapesp.getWidth(),
				btnFapesp.getY() - btnLabPesq.getHeight() / 2 + btnFapesp.getHeight() / 2, btnLabPesq.getWidth(),
				btnLabPesq.getHeight());
		btnUnifesp.setBounds(btnLabPesq.getX() + space + btnLabPesq.getWidth(),
				btnFapesp.getY() - btnUnifesp.getHeight() / 2 + btnFapesp.getHeight() / 2, btnUnifesp.getWidth(),
				btnUnifesp.getHeight());

		JButton btnLogoNiro = new JButton("");
		btnLogoNiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String url = "http://www.niroproject.com";
				openWebpage(url);

			}
		});
		btnLogoNiro.setIcon(new ImageIcon(ScStartScreen.class.getResource("/img/niroMini.png")));
		btnLogoNiro.setBounds(313, 133, 146, 119);
		btnLogoNiro.setBounds((jPAbout.getWidth() - btnLogoNiro.getWidth()) / 2, 20, 146, 119);

		jPAbout.add(btnLogoNiro);

		lblNiroFull = new JLabel("NextGenSequencing Info Reports Optimizer");
		lblNiroFull.setHorizontalAlignment(SwingConstants.CENTER);
		lblNiroFull.setBounds(140, 135, 497, 25);
		lblNiroFull.setBounds((jPAbout.getWidth() - lblNiroFull.getWidth()) / 2,
				btnLogoNiro.getHeight() + btnLogoNiro.getY() + 5, 497, 25);

		jPAbout.add(lblNiroFull);

		lblNiroShort = new JLabel("Niro Suite 1.0");
		lblNiroShort.setHorizontalAlignment(SwingConstants.CENTER);
		lblNiroShort.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNiroShort.setBounds(140, 166, 507, 35);
		lblNiroShort.setBounds((jPAbout.getWidth() - lblNiroShort.getWidth()) / 2,
				lblNiroFull.getHeight() + lblNiroFull.getY() + 2, 507, 35);

		jPAbout.add(lblNiroShort);

		JButton btnLattesRPM = new JButton("");
		btnLattesRPM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String url = "http://lattes.cnpq.br/4990323379616804";
				openWebpage(url);
			}
		});
		btnLattesRPM.setIcon(new ImageIcon(ScStartScreen.class.getResource("/img/plataforma-lattes-logo.jpg")));
		btnLattesRPM.setBounds((jPAbout.getWidth() - btnLattesRPM.getWidth()) / 2, 233, 21, 25);
		jPAbout.add(btnLattesRPM);

		JButton btnLattesRFS = new JButton("");
		btnLattesRFS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String url = "http://lattes.cnpq.br/6408852011134966";
				openWebpage(url);

			}
		});
		btnLattesRFS.setIcon(new ImageIcon(ScStartScreen.class.getResource("/img/plataforma-lattes-logo.jpg")));
		btnLattesRFS.setBounds((jPAbout.getWidth() - btnLattesRFS.getWidth()) / 2, 271, 21, 25);
		jPAbout.add(btnLattesRFS);

		JButton btnLattesJBP = new JButton("");
		btnLattesJBP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String url = "http://lattes.cnpq.br/0856630824759511";
				openWebpage(url);

			}
		});
		btnLattesJBP.setIcon(new ImageIcon(ScStartScreen.class.getResource("/img/plataforma-lattes-logo.jpg")));
		btnLattesJBP.setBounds((jPAbout.getWidth() - btnLattesJBP.getWidth()) / 2, 309, 21, 25);
		jPAbout.add(btnLattesJBP);

		jPAbout.setVisible(false);

		return jPAbout;

	}

	public static void openWebpage(String urlString) {
		try {
			Desktop.getDesktop().browse(new URL(urlString).toURI());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
