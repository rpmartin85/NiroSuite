package screen;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import conn.ConnectionMySQL;
import logical.User;

public class ScEditUser extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNameUser;
	private JTextField txtLoginUser;
	private JPasswordField passPassUser;
	private JRadioButton rdbtnUser;
	private JRadioButton rdbtnAdmin;
	private JButton btnSave;
	private JButton btnCancel;
	private JButton btnClear;
	private JLabel lblCode;
	private JTextField txtCode;
	private ScInsertProject scIP;
	private User u;
	private ScEdit scE;
	
	

	/**
	 * Create the frame.
	 */

	public ScEditUser(int cod, Rectangle r, ScEdit scE2) {
		
		scE = scE2;

		scIP = null;
		
		ConnectionMySQL c = StartScreen.getConn();
		
		u = c.getUser(cod);

		setResizable(false);
		setTitle("Edit Researcher");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds((int)r.getX(), (int)r.getY(), 450, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblUserName = new JLabel("Name:");
		lblUserName.setBounds(177, 33, 90, 16);
		contentPane.add(lblUserName);

		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(33, 75, 61, 16);
		contentPane.add(lblLogin);

		JLabel lblPasswd = new JLabel("Password:");
		lblPasswd.setBounds(218, 75, 78, 16);
		contentPane.add(lblPasswd);

		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(33, 122, 114, 16);
		contentPane.add(lblType);

		txtNameUser = new JTextField();
		txtNameUser.setBounds(255, 27, 160, 28);
		contentPane.add(txtNameUser);
		txtNameUser.setColumns(10);

		txtLoginUser = new JTextField();
		txtLoginUser.setBounds(86, 69, 120, 28);
		contentPane.add(txtLoginUser);
		txtLoginUser.setColumns(10);

		passPassUser = new JPasswordField();
		passPassUser.setBounds(300, 69, 115, 28);
		contentPane.add(passPassUser);

		rdbtnAdmin = new JRadioButton("Administrator");
		rdbtnAdmin.setBounds(133, 118, 153, 23);
		contentPane.add(rdbtnAdmin);
		rdbtnAdmin.setSelected(true);

		rdbtnUser = new JRadioButton("User");
		rdbtnUser.setBounds(324, 118, 68, 23);
		contentPane.add(rdbtnUser);

		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnUser);
		bg.add(rdbtnAdmin);

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = txtNameUser.getText();
				
				int code = Integer.parseInt(txtCode.getText());
				String login = txtLoginUser.getText();
				if (login.equals("")){
					login = "NiroUser" + code; 
				}
				if (name.equals("")){
					name = login;
				}
				char[] passwd = passPassUser.getPassword();
				if (passwd.length < 1){
					passwd = "NO_PASSWD".toCharArray();
				}
				char type = 'U';
						
				if (rdbtnAdmin.isSelected()){
					type = 'A';
				}
				
				
				
		
				
				User u = new User(name, login, passwd, type);
				
				
				ConnectionMySQL c = StartScreen.getConn();
				boolean created = false;
				
				created = c.updateUser(u, code);
				
				u = null;

				if (created) {
					JOptionPane.showMessageDialog(null,
							"User successfully updated!");
					scE.refreshResearcher();
					setVisible(false);
					clear();
					
					if (scIP != null) {
						scIP.refresh(code);
					}
					
					dispose();
					
				}

				else 
					JOptionPane.showMessageDialog(null,
							"User has not been updated!");
					
				
			
			}
		});
		btnSave.setBounds(33, 163, 117, 29);
		contentPane.add(btnSave);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(298, 163, 117, 29);
		contentPane.add(btnCancel);

		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();

			}
		});
		btnClear.setBounds(169, 163, 117, 29);
		contentPane.add(btnClear);

		lblCode = new JLabel("Code:");
		lblCode.setBounds(33, 33, 61, 16);
		contentPane.add(lblCode);

		txtCode = new JTextField();
		txtCode.setEnabled(false);
		txtCode.setEditable(false);
		txtCode.setBounds(86, 28, 61, 26);
		contentPane.add(txtCode);
		txtCode.setColumns(10);
		
		
	
		txtCode.setText(u.getCod());
		txtNameUser.setText(u.getName());
		txtLoginUser.setText(u.getUserName());
		
		char type = u.getUserType();
		
		if (type == 'A'){
			rdbtnAdmin.setSelected(true);
		}
		
		else {
			rdbtnUser.setSelected(true);
		}
		
		
		
		
		
		
		

	}

	public void setScInsertProject(ScInsertProject scIP) {

		this.scIP = scIP;

	}

	public void clear() {
		
		

		txtCode.setText(u.getCod());
		txtNameUser.setText(u.getName());
		txtLoginUser.setText(u.getUserName());
		
		char type = u.getUserType();
		
		if (type == 'A'){
			rdbtnAdmin.setSelected(true);
		}
		
		else {
			rdbtnUser.setSelected(true);
		}
		
		passPassUser.setText("");
		
		
		
		
	}



}
