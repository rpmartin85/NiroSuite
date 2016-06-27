package screen;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONObject;

import logical.ConfigNiro;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;

public class ScSettings extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtServer;
	private JTextField txtDataBase;
	private JTextField txtPort;
	private JTextField txtUser;
	private JTextField txtPassword;
	private StartScreen sS;

	
	/**
	 * Create the frame.
	 */
	public ScSettings(Rectangle r) {
		setResizable(false);
		setTitle("Settings");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds((int)r.getX(), (int)r.getY(), 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFillThisForm = new JLabel("Fill this form in order to change database settings.");
		lblFillThisForm.setBounds(16, 16, 406, 16);
		contentPane.add(lblFillThisForm);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				
				JSONObject jO = new JSONObject();
			
				jO.put("serverName", txtServer.getText());
				jO.put("dataBase", txtDataBase.getText());
				jO.put("port", txtPort.getText());
				jO.put("user", txtUser.getText());
				jO.put("password", txtPassword.getText());
				
				ConfigNiro.setJson(jO);
				
				sS.setConn();
				
				
			
				
				dispose();
				
			}
		});
		btnOk.setBounds(16, 243, 117, 29);
		contentPane.add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		btnCancel.setBounds(305, 243, 117, 29);
		contentPane.add(btnCancel);
		
		JButton btnDefault = new JButton("Default");
		btnDefault.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JSONObject jO = new JSONObject();
				jO = ConfigNiro.getJsonDefault();
				txtServer.setText(jO.get("serverName").toString());
				txtDataBase.setText(jO.get("dataBase").toString());
				txtPort.setText(jO.get("port").toString());
				txtUser.setText(jO.get("user").toString());
				txtPassword.setText(jO.get("password").toString());
				
				
				
				
			}
		});
		btnDefault.setBounds(159, 243, 117, 29);
		contentPane.add(btnDefault);
		
		JLabel lblServerAdress = new JLabel("Server Adress:");
		lblServerAdress.setBounds(26, 60, 188, 16);
		contentPane.add(lblServerAdress);
		
		JLabel lblDataBaseName = new JLabel("Data Base Name:");
		lblDataBaseName.setBounds(26, 95, 129, 16);
		contentPane.add(lblDataBaseName);
		
		JLabel lblPort = new JLabel("Port:");
		lblPort.setBounds(26, 130, 61, 16);
		contentPane.add(lblPort);
		
		JLabel lblUserName = new JLabel("User Name:");
		lblUserName.setBounds(26, 165, 129, 16);
		contentPane.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(26, 200, 92, 16);
		contentPane.add(lblPassword);
		
		txtServer = new JTextField();
		txtServer.setBounds(176, 55, 246, 26);
		contentPane.add(txtServer);
		txtServer.setColumns(10);
		
		txtDataBase = new JTextField();
		txtDataBase.setBounds(176, 90, 246, 26);
		contentPane.add(txtDataBase);
		txtDataBase.setColumns(10);
		
		txtPort = new JTextField();
		txtPort.setColumns(10);
		txtPort.setBounds(176, 125, 246, 26);
		contentPane.add(txtPort);
		
		txtUser = new JTextField();
		txtUser.setColumns(10);
		txtUser.setBounds(176, 160, 246, 26);
		contentPane.add(txtUser);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(176, 195, 246, 26);
		contentPane.add(txtPassword);
		
		JSONObject jO = new JSONObject();
		jO = ConfigNiro.getJson();
		txtServer.setText(jO.get("serverName").toString());
		txtDataBase.setText(jO.get("dataBase").toString());
		txtPort.setText(jO.get("port").toString());
		txtUser.setText(jO.get("user").toString());
		txtPassword.setText(jO.get("password").toString());
		
		
	}


	public StartScreen getsS() {
		return sS;
	}


	public void setsS(StartScreen sS) {
		this.sS = sS;
	}
	
	
	
}
