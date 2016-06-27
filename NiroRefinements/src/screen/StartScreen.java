package screen;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Desktop;
import javax.swing.ImageIcon;
import conn.ConnectionMySQL;
import screen.ScAbout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import logical.Login;
import logical.ConfigNiro;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import org.json.simple.parser.ParseException;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Color;

public class StartScreen {

	private JFrame mainWindow;

	private ScInsertPatient scIPat;
	private ScRefineAll scRA;
	private ScSettings scS;
	private ScDelete scD;
	private ScEdit scE;
	private JButton btnInsert;
	private JButton btnDelete;
	private JButton btnEdit;
	private JButton btnAbout;
	private JButton btnRefine;
	private JButton btnContact;
	private JButton btnExit;
	private JButton btnSettings;
	private JButton btnConnect;
	private boolean connected;
	private JLabel lblWarn1;
	private JLabel lblWarn2;

	private static ConnectionMySQL c;

	public Login log;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartScreen window = new StartScreen();
					window.mainWindow.setVisible(true);
					window.mainWindow.setTitle("Niro Refinements ver 1.0");

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StartScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		try {
			ConfigNiro.onStart();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		connected = false;

		c = new ConnectionMySQL(passThis());

		log = new Login();

		mainWindow = new JFrame();
		mainWindow.setResizable(false);
		mainWindow.setTitle("Niro Refinements ver 1.0");
		mainWindow.setBounds(100, 100, 555, 555);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.getContentPane().setLayout(null);

		btnInsert = new JButton("");
		btnInsert.setEnabled(connected);
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				scIPat = new ScInsertPatient(getBounds());
				scIPat.setVisible(true);

			}
		});
		btnInsert.setIcon(new ImageIcon(StartScreen.class.getResource("/img/add.png")));
		btnInsert.setBounds(20, 220, 100, 100);
		mainWindow.getContentPane().add(btnInsert);

		btnDelete = new JButton("");
		btnDelete.setEnabled(connected);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				scD = new ScDelete(getBounds());
				scD.setVisible(true);

			}
		});
		btnDelete.setIcon(new ImageIcon(StartScreen.class.getResource("/img/del.png")));
		btnDelete.setBounds(154, 220, 100, 100);
		mainWindow.getContentPane().add(btnDelete);

		btnEdit = new JButton("");
		btnEdit.setEnabled(connected);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				scE = new ScEdit(getBounds());
				scE.setVisible(true);

			}
		});
		btnEdit.setIcon(new ImageIcon(StartScreen.class.getResource("/img/edit.png")));
		btnEdit.setBounds(296, 220, 100, 100);
		mainWindow.getContentPane().add(btnEdit);

		btnAbout = new JButton("");
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ScAbout ab = new ScAbout(getBounds());
				ab.setVisible(true);
			}
		});
		btnAbout.setIcon(new ImageIcon(StartScreen.class.getResource("/img/about.png")));
		btnAbout.setBounds(20, 384, 100, 100);
		mainWindow.getContentPane().add(btnAbout);

		btnRefine = new JButton("");
		btnRefine.setEnabled(connected);
		btnRefine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				scRA = new ScRefineAll(getBounds());
				scRA.setVisible(true);

			}
		});
		btnRefine.setIcon(new ImageIcon(StartScreen.class.getResource("/img/refine.png")));
		btnRefine.setBounds(433, 220, 100, 100);
		mainWindow.getContentPane().add(btnRefine);

		btnContact = new JButton("");
		btnContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Desktop desktop;
				if (Desktop.isDesktopSupported() && (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
					URI mailto;
					try {
						mailto = new URI("mailto:support@niroproject.com?subject=NiroRefinements%20Contact");
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
		btnContact.setIcon(new ImageIcon(StartScreen.class.getResource("/img/contact.png")));
		btnContact.setBounds(296, 384, 100, 100);
		mainWindow.getContentPane().add(btnContact);

		btnExit = new JButton("");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setIcon(new ImageIcon(StartScreen.class.getResource("/img/exit.png")));
		btnExit.setBounds(433, 384, 100, 100);
		mainWindow.getContentPane().add(btnExit);

		JLabel lblInsert = new JLabel("Insert");
		lblInsert.setHorizontalAlignment(SwingConstants.CENTER);
		lblInsert.setBounds(20, 332, 100, 16);
		mainWindow.getContentPane().add(lblInsert);

		JLabel lblDelete = new JLabel("Delete");
		lblDelete.setHorizontalAlignment(SwingConstants.CENTER);
		lblDelete.setBounds(154, 332, 100, 16);
		mainWindow.getContentPane().add(lblDelete);

		JLabel lblEdit = new JLabel("Edit");
		lblEdit.setHorizontalAlignment(SwingConstants.CENTER);
		lblEdit.setBounds(296, 332, 100, 16);
		mainWindow.getContentPane().add(lblEdit);

		JLabel lblAbout = new JLabel("About");
		lblAbout.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbout.setBounds(20, 498, 100, 16);
		mainWindow.getContentPane().add(lblAbout);

		JLabel lblRefine = new JLabel("Refine");
		lblRefine.setHorizontalAlignment(SwingConstants.CENTER);
		lblRefine.setBounds(433, 334, 100, 16);
		mainWindow.getContentPane().add(lblRefine);

		JLabel lblContact = new JLabel("Contact");
		lblContact.setHorizontalAlignment(SwingConstants.CENTER);
		lblContact.setBounds(296, 498, 100, 16);
		mainWindow.getContentPane().add(lblContact);

		JLabel lblExit = new JLabel("Exit");
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setBounds(433, 496, 100, 16);
		mainWindow.getContentPane().add(lblExit);

		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String url = "http://www.niroproject.com";
				openWebpage(url);

			}
		});
		button.setIcon(new ImageIcon(StartScreen.class.getResource("/img/niroMini.png")));
		button.setBounds(201, 16, 146, 119);
		mainWindow.getContentPane().add(button);

		JLabel label = new JLabel("NextGenSequencing Info Reports Optimizer");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(30, 140, 497, 25);
		mainWindow.getContentPane().add(label);

		JLabel lblNiroRefinements = new JLabel("Niro Refinements 1.0");
		lblNiroRefinements.setHorizontalAlignment(SwingConstants.CENTER);
		lblNiroRefinements.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNiroRefinements.setBounds(20, 168, 507, 35);
		mainWindow.getContentPane().add(lblNiroRefinements);

		btnSettings = new JButton("");
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				scS = new ScSettings(getBounds());
				scS.setVisible(true);
				scS.setsS(passThis());

			}
		});
		btnSettings.setIcon(new ImageIcon(StartScreen.class.getResource("/img/settings.png")));
		btnSettings.setBounds(154, 384, 100, 100);
		mainWindow.getContentPane().add(btnSettings);

		JLabel lblSettings = new JLabel("Settings");
		lblSettings.setHorizontalAlignment(SwingConstants.CENTER);
		lblSettings.setBounds(154, 498, 100, 16);
		mainWindow.getContentPane().add(lblSettings);

		btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setConn();

				try {
					c.getConn().isValid(0);
					connected = true;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					connected = false;
				}

				btnInsert.setEnabled(connected);
				btnDelete.setEnabled(connected);
				btnEdit.setEnabled(connected);
				btnRefine.setEnabled(connected);
				btnConnect.setVisible(!connected);
				lblWarn1.setVisible(!connected);
				lblWarn2.setVisible(!connected);

			}
		});
		btnConnect.setBounds(416, 350, 117, 29);
		mainWindow.getContentPane().add(btnConnect);

		lblWarn1 = new JLabel("Not connected to database. Correct database settings!!! ");
		lblWarn1.setForeground(Color.RED);
		lblWarn1.setHorizontalAlignment(SwingConstants.CENTER);
		lblWarn1.setBounds(20, 200, 513, 16);
		mainWindow.getContentPane().add(lblWarn1);

		lblWarn2 = new JLabel("Click on Connect button when you are done.");
		lblWarn2.setHorizontalAlignment(SwingConstants.LEFT);
		lblWarn2.setForeground(Color.RED);
		lblWarn2.setBounds(20, 356, 394, 16);
		mainWindow.getContentPane().add(lblWarn2);
		btnConnect.setVisible(!connected);
		lblWarn1.setVisible(!connected);
		lblWarn2.setVisible(!connected);

	}

	public Rectangle getBounds() {
		return mainWindow.getBounds();
	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	public static ConnectionMySQL getConn() {
		return c;
	}

	public void setConn() {

		c.close();

		c = new ConnectionMySQL();

	}

	public StartScreen passThis() {

		return this;

	}

	public static void openWebpage(String urlString) {
		try {
			Desktop.getDesktop().browse(new URL(urlString).toURI());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
