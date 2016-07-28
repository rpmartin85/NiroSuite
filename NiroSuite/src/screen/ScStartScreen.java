package screen;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JPanel;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.beans.PropertyChangeEvent;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ScStartScreen {

	private JFrame frame;
	private JPanel header;
	private JPanel selected;
	private JLabel lblCoverage;
	private JLabel lblAnnotations;
	private JLabel lblAnalysis;
	private JLabel lblRefinements;
	private JLabel lblAbout;
	private JLabel lblContact;
	private JLabel lblExit;
	private JLabel lblSettings;
	private static JPanel jPAbout;
	private static JPanel jPSettings;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScStartScreen window = new ScStartScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ScStartScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();

		frame.setUndecorated(true);

		frame.getContentPane().setBackground(new Color(230, 230, 230));
		frame.setBounds(100, 100, 800, 600);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().setLayout(null);

		header = new JPanel();
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				clearScreen();

			}
		});
		header.setBackground(new Color(88, 86, 214));
		header.setBounds(0, 0, 800, 115);
		frame.getContentPane().add(header);
		header.setLayout(null);

		JLabel lblNirosuite = new JLabel("NiroSuite");
		lblNirosuite.setFont(new Font("Arial", Font.PLAIN, 27));
		lblNirosuite.setForeground(new Color(255, 255, 255));
		lblNirosuite.setBounds(16, 15, 190, 40);
		header.add(lblNirosuite);

		lblAnalysis = new JLabel("   Analysis");
		lblAnalysis.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				selected.setBounds(lblAnalysis.getX(), lblAnalysis.getY() + lblAnalysis.getHeight() + 8,
						lblAnalysis.getWidth(), 5);

			}
		});
		lblAnalysis.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnalysis.setFont(new Font("Arial", Font.PLAIN, 17));
		lblAnalysis.setForeground(new Color(255, 255, 255));
		lblAnalysis.setIcon(new ImageIcon(ScStartScreen.class.getResource("/img/analysisButton.png")));
		lblAnalysis.setBounds(552, 72, 155, 30);
		header.add(lblAnalysis);

		lblAnnotations = new JLabel("   Annotations");
		lblAnnotations.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				selected.setBounds(lblAnnotations.getX(), lblAnnotations.getY() + lblAnnotations.getHeight() + 8,
						lblAnnotations.getWidth(), 5);

			}
		});
		lblAnnotations.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnotations.setForeground(Color.WHITE);
		lblAnnotations.setFont(new Font("Arial", Font.PLAIN, 17));
		lblAnnotations.setIcon(new ImageIcon(ScStartScreen.class.getResource("/img/annotationsButton.png")));
		lblAnnotations.setBounds(370, 72, 170, 30);
		header.add(lblAnnotations);

		lblCoverage = new JLabel("   Coverage");
		lblCoverage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				selected.setBounds(lblCoverage.getX(), lblCoverage.getY() + lblCoverage.getHeight() + 8,
						lblCoverage.getWidth(), 5);

			}
		});
		lblCoverage.setHorizontalAlignment(SwingConstants.CENTER);
		lblCoverage.setForeground(Color.WHITE);
		lblCoverage.setFont(new Font("Arial", Font.PLAIN, 17));
		lblCoverage.setIcon(new ImageIcon(ScStartScreen.class.getResource("/img/coverageButton.png")));
		lblCoverage.setBounds(6, 72, 170, 30);
		header.add(lblCoverage);

		lblRefinements = new JLabel("   Refinements");
		lblRefinements.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				selected.setBounds(lblRefinements.getX(), lblRefinements.getY() + lblRefinements.getHeight() + 8,
						lblRefinements.getWidth(), 5);

			}
		});
		lblRefinements.setHorizontalAlignment(SwingConstants.CENTER);
		lblRefinements.setFont(new Font("Arial", Font.PLAIN, 17));
		lblRefinements.setForeground(Color.WHITE);
		lblRefinements.setIcon(new ImageIcon(ScStartScreen.class.getResource("/img/refinementsButton.png")));
		lblRefinements.setBounds(188, 72, 170, 30);
		header.add(lblRefinements);

		selected = new JPanel();
		selected.setBackground(new Color(255, 204, 0));
		header.add(selected);

		jPAbout = new JPanel();
		
		

		jPAbout.setBackground(new Color(250, 250, 250));
		frame.getContentPane().add(jPAbout);
		jPAbout.setLayout(null);
		
		jPSettings = new JPanel();
		frame.getContentPane().add(jPSettings);
		jPSettings.setLayout(null);

		
		

		selected.setBounds(lblCoverage.getX(), lblCoverage.getY() + lblCoverage.getHeight() + 8, lblCoverage.getWidth(),
				5);

		lblAbout = new JLabel("");
		lblAbout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				clearScreen();

				hideHeader();

				jPAbout = ScAbout.setAbout(jPAbout, frame, header.getBounds());

				jPAbout.setBounds(15, header.getHeight() + 15, frame.getWidth() - 30,
						frame.getHeight() - 30 - header.getHeight());

				jPAbout.setVisible(true);
				
				lblAbout.setEnabled(false);
				
				

			}
		});
		lblAbout.setIcon(new ImageIcon(ScStartScreen.class.getResource("/img/aboutButton.png")));
		lblAbout.setBounds(frame.getWidth() - (45 + (30 * 2) + (20 * 2)), 15, 30, 30);
		header.add(lblAbout);

		lblContact = new JLabel("");
		lblContact.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				Desktop desktop;
				if (Desktop.isDesktopSupported() && (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
					URI mailto;
					try {
						mailto = new URI("mailto:support@niroproject.com?subject=NiroSuite%20Contact");
						desktop.mail(mailto);
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {

					throw new RuntimeException("desktop doesn't support mailto; mail is dead anyway ;)");
				}
				
				
				
				

			}
		});
		lblContact.setIcon(new ImageIcon(ScStartScreen.class.getResource("/img/contactButton.png")));
		lblContact.setBounds(frame.getWidth() - (45 + (30 * 1) + (20 * 1)), 15, 30, 30);
		header.add(lblContact);

		lblExit = new JLabel("");
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				System.exit(0);
			}
		});
		lblExit.setIcon(new ImageIcon(ScStartScreen.class.getResource("/img/exitButton.png")));
		lblExit.setBounds(frame.getWidth() - (45 + (30 * 0) + (20 * 0)), 15, 30, 30);
		header.add(lblExit);

		lblSettings = new JLabel("");
		lblSettings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				clearScreen();

				hideHeader();
				
				
				jPSettings = ScSettings.setSettings(jPSettings, frame, header.getBounds());

				jPSettings.setBounds(15, header.getHeight() + 15, frame.getWidth() - 30,
						frame.getHeight() - 30 - header.getHeight());

				jPSettings.setVisible(true);
				
				lblSettings.setEnabled(false);
				
				
				
			}
		});
		lblSettings.setIcon(new ImageIcon(ScStartScreen.class.getResource("/img/settingsButton.png")));
		lblSettings.setBounds(frame.getWidth() - (45 + (30 * 3) + (20 * 3)), 15, 30, 30);
		header.add(lblSettings);

		frame.addComponentListener(new ComponentListener() {

			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentResized(ComponentEvent e) {
				// TODO Auto-generated method stub

				header.setBounds(0, 0, frame.getWidth(), header.getHeight());

			}

			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}

	public void clearScreen() {

		jPAbout.setVisible(false);
		jPSettings.setVisible(false);
		header.setBounds(0, 0, frame.getWidth(), 115);
		lblAbout.setEnabled(true);
		lblSettings.setEnabled(true);

	}

	public void hideHeader() {

		header.setBounds(0, 0, frame.getWidth(), 60);

	}

}
