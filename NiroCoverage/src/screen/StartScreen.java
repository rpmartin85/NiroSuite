package screen;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import logical.Parameters;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class StartScreen {
	private JFrame frmNiroCoverageanalysisOptimizer;
	private ScAbout ab;
	private ScPreferences scP;
	private ScSetInput scSI;
	private ScStartSR scStSR;
	private ScStart scSt;
	private static JButton btnPreferences;
	private static JButton btnSetInput;
	private static JButton btnStart;
	private static JButton btnStartSR;
	public Parameters param;
	private JLabel lblLogo;
	private JLabel lblSetInput;
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
					window.frmNiroCoverageanalysisOptimizer.setVisible(true);
					window.frmNiroCoverageanalysisOptimizer.setTitle("Niro Application - Ion Data Analyser");
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
		this.param = new Parameters();

		this.frmNiroCoverageanalysisOptimizer = new JFrame();
		frmNiroCoverageanalysisOptimizer.setTitle("Niro Coverage Analysis");
		this.frmNiroCoverageanalysisOptimizer.setBounds(100, 100, 453, 520);
		this.frmNiroCoverageanalysisOptimizer.setDefaultCloseOperation(3);
		frmNiroCoverageanalysisOptimizer.getContentPane().setLayout(null);

		lblLogo = new JLabel("");
		lblLogo.setBounds(155, 26, 146, 119);
		lblLogo.setIcon(new ImageIcon(StartScreen.class.getResource("/img/niroMini.png")));
		frmNiroCoverageanalysisOptimizer.getContentPane().add(lblLogo);

		JButton btnAbout = new JButton("");
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ab = new ScAbout();
				ab.setVisible(true);
			}
		});
		btnAbout.setIcon(new ImageIcon(StartScreen.class.getResource("/img/about.png")));
		btnAbout.setBounds(180, 340, 100, 100);
		frmNiroCoverageanalysisOptimizer.getContentPane().add(btnAbout);

		btnSetInput = new JButton("");
		btnSetInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scSI = new ScSetInput();
				scSI.setVisible(true);
				scSI.setParam(param);
			}
		});
		btnSetInput.setIcon(new ImageIcon(StartScreen.class.getResource("/img/edit.png")));
		btnSetInput.setBounds(33, 180, 100, 100);
		frmNiroCoverageanalysisOptimizer.getContentPane().add(btnSetInput);

		btnStart = new JButton("");
		btnStart.setEnabled(false);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				scSt = new ScStart();
				scSt.setVisible(true);
				scSt.setParam(param);

			}
		});
		btnStart.setIcon(new ImageIcon(StartScreen.class.getResource("/img/multiple.png")));
		btnStart.setBounds(320, 180, 100, 100);
		frmNiroCoverageanalysisOptimizer.getContentPane().add(btnStart);

		btnStartSR = new JButton("");
		btnStartSR.setEnabled(false);
		btnStartSR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			    StartScreen.this.scStSR = new ScStartSR();
		        StartScreen.this.scStSR.setVisible(true);
		        StartScreen.this.scStSR.setParam(StartScreen.this.param);
				
			}
		});
		btnStartSR.setIcon(new ImageIcon(StartScreen.class.getResource("/img/single.png")));
		btnStartSR.setBounds(180, 180, 100, 100);
		frmNiroCoverageanalysisOptimizer.getContentPane().add(btnStartSR);

		JButton btnClose = new JButton("");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnClose.setIcon(new ImageIcon(StartScreen.class.getResource("/img/exit.png")));
		btnClose.setBounds(320, 340, 100, 100);
		frmNiroCoverageanalysisOptimizer.getContentPane().add(btnClose);

		btnPreferences = new JButton("");
		btnPreferences.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scP = new ScPreferences();
				scP.setParam(param);
				scP.setVisible(true);
			}
		});
		btnPreferences.setIcon(new ImageIcon(StartScreen.class.getResource("/img/settings.png")));
		btnPreferences.setBounds(33, 340, 100, 100);
		frmNiroCoverageanalysisOptimizer.getContentPane().add(btnPreferences);

		lblSetInput = new JLabel("Set Input");
		lblSetInput.setHorizontalAlignment(SwingConstants.CENTER);
		lblSetInput.setBounds(6, 292, 158, 16);
		frmNiroCoverageanalysisOptimizer.getContentPane().add(lblSetInput);

		lblSingleRun = new JLabel("Single Run");
		lblSingleRun.setHorizontalAlignment(SwingConstants.CENTER);
		lblSingleRun.setBounds(132, 292, 194, 16);
		frmNiroCoverageanalysisOptimizer.getContentPane().add(lblSingleRun);

		lblMultipleRun = new JLabel("Murged Run");
		lblMultipleRun.setHorizontalAlignment(SwingConstants.CENTER);
		lblMultipleRun.setBounds(279, 292, 176, 16);
		frmNiroCoverageanalysisOptimizer.getContentPane().add(lblMultipleRun);

		lblPreferences = new JLabel("Preferences");
		lblPreferences.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreferences.setBounds(6, 451, 158, 16);
		frmNiroCoverageanalysisOptimizer.getContentPane().add(lblPreferences);

		lblAbout = new JLabel("About");
		lblAbout.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbout.setBounds(132, 451, 194, 16);
		frmNiroCoverageanalysisOptimizer.getContentPane().add(lblAbout);

		lblExit = new JLabel("Exit");
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setBounds(279, 451, 176, 16);
		frmNiroCoverageanalysisOptimizer.getContentPane().add(lblExit);
	}

	public Parameters getParam() {
		return this.param;
	}

	public void setPathParam(String inputPathCoverAna, String inputPathVarCaller) {
		this.param.setInputPathCoverAna(inputPathCoverAna);
		this.param.setInputPathVarCaller(inputPathVarCaller);
	}

	public void setColorParam(int red, int yellow) {
		this.param.setRedParam(red);
		this.param.setYellowParam(yellow);
	}

	public void setOutParam(String output) {
		this.param.setOutputPath(output);
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
