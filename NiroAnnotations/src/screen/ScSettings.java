package screen;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONObject;

import logical.ConfigNiro;
import javax.swing.JTextField;

public class ScSettings extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtAnnovarPath;
	private JFileChooser fc1;
	private JLabel lblStatus;
	private JButton btnInstall;

	public ScSettings(Rectangle r) {
		setTitle("Settings");
		setDefaultCloseOperation(2);
		setBounds((int) r.getX(), (int) r.getY(), 408, 225);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(null);

		JLabel lblPrefHead = new JLabel("Set path to annovar folder.");
		lblPrefHead.setHorizontalAlignment(2);
		lblPrefHead.setBounds(34, 30, 444, 16);
		this.contentPane.add(lblPrefHead);

		JButton btnPrefOK = new JButton("OK");
		btnPrefOK.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {

				JSONObject jO = new JSONObject();
				jO.put("path", txtAnnovarPath.getText());
				
				JSONObject jOOld = new JSONObject();
				jOOld = ConfigNiro.getJson();
				String db = jOOld.get("db").toString();
				
				jO.put("db", db);
				
				

				ConfigNiro.setJson(jO);
				
				dispose();

			}
		});
		btnPrefOK.setBounds(34, 156, 95, 29);
		this.contentPane.add(btnPrefOK);

		JButton btnPrefResDef = new JButton("Clear");
		btnPrefResDef.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setAnnovarPath();

			}
		});
		btnPrefResDef.setBounds(166, 156, 95, 29);
		this.contentPane.add(btnPrefResDef);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScSettings.this.dispose();
			}
		});
		btnCancel.setBounds(291, 156, 95, 29);
		this.contentPane.add(btnCancel);

		txtAnnovarPath = new JTextField();
		txtAnnovarPath.setBounds(34, 58, 280, 28);
		contentPane.add(txtAnnovarPath);
		txtAnnovarPath.setColumns(10);
		setAnnovarPath();
		
		fc1 = new JFileChooser();
		
		fc1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		JButton button = new JButton("=");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int returnVal = fc1.showOpenDialog(contentPane);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc1.getSelectedFile();

					txtAnnovarPath.setText(file.getAbsolutePath());

				} else {
					System.out.println("Calcelled by user.");

				}
				
	
				
				
				
				
			}
		});
		button.setBounds(332, 59, 54, 29);
		contentPane.add(button);
		
		lblStatus = new JLabel("Install Annovar Data Base");
		lblStatus.setBounds(34, 112, 224, 16);
		contentPane.add(lblStatus);
		
		btnInstall = new JButton("Install");
		btnInstall.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				
				
				btnInstall.setEnabled(false);
				String pathAnnovar = txtAnnovarPath.getText();
				String command;
				
				boolean db = false;
				
				
				updateStatus(0);
				command = "perl " + pathAnnovar + "/annotate_variation.pl -downdb -webfrom annovar refGene --buildver hg19 " + pathAnnovar + "/humandb/";
				db = Execute(command);
				updateStatus(1);
				command = "perl " + pathAnnovar + "/annotate_variation.pl -downdb -webfrom annovar knownGene --buildver hg19 " + pathAnnovar + "/humandb/";
				db = Execute(command);
				updateStatus(2);
				command = "perl " + pathAnnovar + "/annotate_variation.pl -downdb -webfrom annovar ensGene --buildver hg19 " + pathAnnovar + "/humandb/";
				db = Execute(command);
				updateStatus(3);
				command = "perl " + pathAnnovar + "/annotate_variation.pl -downdb -webfrom annovar cosmic70 --buildver hg19 " + pathAnnovar + "/humandb/";
				db = Execute(command);
				updateStatus(4);
				command = "perl " + pathAnnovar + "/annotate_variation.pl -downdb -webfrom annovar snp129 --buildver hg19 " + pathAnnovar + "/humandb/";
				db = Execute(command);
				updateStatus(5);
				command = "perl " + pathAnnovar + "/annotate_variation.pl -downdb -webfrom annovar snp131 --buildver hg19 " + pathAnnovar + "/humandb/";
				db = Execute(command);
				updateStatus(6);
				command = "perl " + pathAnnovar + "/annotate_variation.pl -downdb -webfrom annovar snp135 --buildver hg19 " + pathAnnovar + "/humandb/";
				db = Execute(command);
				updateStatus(7);
				command = "perl " + pathAnnovar + "/annotate_variation.pl -downdb -webfrom annovar dbnsfp30a --buildver hg19 " + pathAnnovar + "/humandb/";
				db = Execute(command);
				updateStatus(8);
				command = "perl " + pathAnnovar + "/annotate_variation.pl -downdb -webfrom annovar dbnsfp31a_interpro --buildver hg19 " + pathAnnovar + "/humandb/";
				db = Execute(command);
				updateStatus(9);
				command = "perl " + pathAnnovar + "/annotate_variation.pl -downdb -webfrom annovar dbscsnv11 --buildver hg19 " + pathAnnovar + "/humandb/";
				db = Execute(command);
				updateStatus(10);
				command = "perl " + pathAnnovar + "/annotate_variation.pl -downdb -webfrom annovar exac03 --buildver hg19 " + pathAnnovar + "/humandb/";
				db = Execute(command);
				updateStatus(11);
				command = "perl " + pathAnnovar + "/annotate_variation.pl -downdb -webfrom annovar 1000g2015aug --buildver hg19 " + pathAnnovar + "/humandb/";
				db = Execute(command);
				updateStatus(12);
				command = "perl " + pathAnnovar + "/annotate_variation.pl -downdb -webfrom annovar clinvar_20160302 --buildver hg19 " + pathAnnovar + "/humandb/";
				db = Execute(command);
				updateStatus(13);
				command = "perl " + pathAnnovar + "/annotate_variation.pl -downdb -webfrom annovar snp138 --buildver hg19 " + pathAnnovar + "/humandb/";
				db = Execute(command);
				updateStatus(14);
				command = "perl " + pathAnnovar + "/annotate_variation.pl -downdb -webfrom annovar avsnp142 --buildver hg19 " + pathAnnovar + "/humandb/";
				db = Execute(command);
				updateStatus(15);
				command = "perl " + pathAnnovar + "/annotate_variation.pl -downdb -webfrom annovar avsnp147 --buildver hg19 " + pathAnnovar + "/humandb/";
				db = Execute(command);
				lblStatus.setText("DataBase already installed!");
				
				
				
				
				
				if (db){
					
					JSONObject jO = new JSONObject();
					
					
					JSONObject jOOld = new JSONObject();
					jOOld = ConfigNiro.getJson();
					String path = jOOld.get("path").toString();
					
					jO.put("db", "1");
					jO.put("path", path);
					
					
					

					ConfigNiro.setJson(jO);
					
				}
				
				
				
			}
		});
		btnInstall.setBounds(269, 107, 117, 29);
		contentPane.add(btnInstall);
		
		
		if (dbInstalled()){
			
			btnInstall.setEnabled(false);
			lblStatus.setText("DataBase already installed!");
			
		}
		
		
	}

	public void setAnnovarPath() {

		JSONObject jO = new JSONObject();
		jO = ConfigNiro.getJson();
		String pathAnnovar = jO.get("path").toString();
		txtAnnovarPath.setText(pathAnnovar);

	}
	
	
	public boolean dbInstalled(){
		

		JSONObject jO = new JSONObject();
		jO = ConfigNiro.getJson();
		int db = Integer.parseInt(jO.get("db").toString());
		
		if (db == 0) return false;
		else return true;
		
		
		
		
		
	}
	
	
	
	public boolean Execute(String command) {

		try {
			Process proc = Runtime.getRuntime().exec(command);
			proc.waitFor();

			return true;
		} catch (IOException e1) {

			e1.printStackTrace();
			return false;
		} catch (InterruptedException e1) {
			e1.printStackTrace();
			return false;
		}

	}
	
	public void updateStatus(int progress){
		
		int total = 16;
		
		String status = "Added " + progress + " from " + total;  
		
		lblStatus.setText(status);
		
		
	}

	
	
	
	
}
