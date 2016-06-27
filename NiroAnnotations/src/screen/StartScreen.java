package screen;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;



public class StartScreen {
	private JFrame frmNiroAnnotations;
	private ScAbout ab;
	private ScSettings scP;
	private ScStartAnno scSA;
	private static JButton btnPreferences;
	private static JButton btnSetInput;
	private static JButton btnStart;
	private static JButton btnStartSR;
	private JLabel lblLogo;
	private JLabel lblSingleRun;
	private JLabel lblMultipleRun;
	private JLabel lblPreferences;
	private JLabel lblAbout;
	private JLabel lblExit;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartScreen window = new StartScreen();
					window.frmNiroAnnotations.setVisible(true);
					window.frmNiroAnnotations.setTitle("Niro Annotations");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StartScreen() {
		initialize();
	}

	private void initialize() {
		

		this.frmNiroAnnotations = new JFrame();
		frmNiroAnnotations.setTitle("Niro Annotations");
		this.frmNiroAnnotations.setBounds(100, 100, 453, 520);
		this.frmNiroAnnotations.setDefaultCloseOperation(3);
		frmNiroAnnotations.getContentPane().setLayout(null);
		
		
		

		lblLogo = new JLabel("");
		lblLogo.setBounds(155, 26, 146, 119);
		lblLogo.setIcon(new ImageIcon(StartScreen.class.getResource("/img/niroMini.png")));
		frmNiroAnnotations.getContentPane().add(lblLogo);

		JButton btnAbout = new JButton("");
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ab = new ScAbout(getBounds());
				ab.setVisible(true);
			}
		});
		btnAbout.setIcon(new ImageIcon(StartScreen.class.getResource("/img/about.png")));
		btnAbout.setBounds(180, 340, 100, 100);
		frmNiroAnnotations.getContentPane().add(btnAbout);



		JButton btnClose = new JButton("");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnClose.setIcon(new ImageIcon(StartScreen.class.getResource("/img/exit.png")));
		btnClose.setBounds(320, 340, 100, 100);
		frmNiroAnnotations.getContentPane().add(btnClose);

		btnPreferences = new JButton("");
		btnPreferences.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scP = new ScSettings(getBounds());
				
				scP.setVisible(true);
			}
		});
		btnPreferences.setIcon(new ImageIcon(StartScreen.class.getResource("/img/settings.png")));
		btnPreferences.setBounds(33, 340, 100, 100);
		frmNiroAnnotations.getContentPane().add(btnPreferences);

		lblSingleRun = new JLabel("Single VCF Annotation");
		lblSingleRun.setHorizontalAlignment(SwingConstants.CENTER);
		lblSingleRun.setBounds(18, 292, 205, 16);
		frmNiroAnnotations.getContentPane().add(lblSingleRun);

		lblMultipleRun = new JLabel("Multiple VCF Annotation");
		lblMultipleRun.setHorizontalAlignment(SwingConstants.CENTER);
		lblMultipleRun.setBounds(224, 292, 212, 16);
		frmNiroAnnotations.getContentPane().add(lblMultipleRun);

		lblPreferences = new JLabel("Settings");
		lblPreferences.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreferences.setBounds(6, 451, 158, 16);
		frmNiroAnnotations.getContentPane().add(lblPreferences);

		lblAbout = new JLabel("About");
		lblAbout.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbout.setBounds(132, 451, 194, 16);
		frmNiroAnnotations.getContentPane().add(lblAbout);

		lblExit = new JLabel("Exit");
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setBounds(279, 451, 176, 16);
		frmNiroAnnotations.getContentPane().add(lblExit);
		
		JButton btnStartSingle = new JButton("");
		btnStartSingle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				scSA = new ScStartAnno(getBounds(), false);
				scSA.setVisible(true);
			}
		});
		btnStartSingle.setIcon(new ImageIcon(StartScreen.class.getResource("/img/single.png")));
		btnStartSingle.setBounds(75, 180, 100, 100);
		frmNiroAnnotations.getContentPane().add(btnStartSingle);
		
		JButton btnStartMultiple = new JButton("");
		btnStartMultiple.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				scSA = new ScStartAnno(getBounds(), true);
				scSA.setVisible(true);
			}
		});
		btnStartMultiple.setIcon(new ImageIcon(StartScreen.class.getResource("/img/multiple.png")));
		btnStartMultiple.setBounds(279, 180, 100, 100);
		frmNiroAnnotations.getContentPane().add(btnStartMultiple);
	}

	public Rectangle getBounds() {
		return frmNiroAnnotations.getBounds();
	}

	public static void logged() {
	}

	public static void loggedA() {

		btnPreferences.setEnabled(true);
		btnSetInput.setEnabled(true);
	}

	public static void enableStart() {
		btnStart.setEnabled(true);
	}

	public static void disableStart() {
		btnStart.setEnabled(false);
	}

	public static void enableStartMR() {
		btnStartSR.setEnabled(true);
	}

	public static void disableStartMR() {
		btnStartSR.setEnabled(false);
	}

	public static void clearMenu() {
		disableStart();
		disableStartMR();
	}
}
