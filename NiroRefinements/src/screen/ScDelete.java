package screen;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import conn.ConnectionMySQL;
import logical.Chip;
import logical.Family;
import logical.Patient;
import logical.Project;
import logical.User;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;

public class ScDelete extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> cmbResearcher;
	private JComboBox<String> cmbChip;
	private JComboBox<String> cmbProject;
	private JComboBox<String> cmbFamily;
	private JComboBox<String> cmbPatient;
	private String DELETE_CONFIRMATION = "Do you want to delete the selected field.\nAll related data will be deleted.";

	/**
	 * Create the frame.
	 */
	public ScDelete(Rectangle r) {
		setResizable(false);
		setTitle("Delete");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds((int)r.getX(), (int)r.getY(), 450, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPatient = new JLabel("Patient:");
		lblPatient.setBounds(16, 29, 85, 16);
		contentPane.add(lblPatient);

		JLabel lblFamily = new JLabel("Family:");
		lblFamily.setBounds(16, 82, 117, 16);
		contentPane.add(lblFamily);

		JLabel lblProject = new JLabel("Project:");
		lblProject.setBounds(16, 141, 117, 16);
		contentPane.add(lblProject);

		JLabel lblChip = new JLabel("Chip:");
		lblChip.setBounds(16, 195, 61, 16);
		contentPane.add(lblChip);

		cmbPatient = new JComboBox<String>();
		cmbPatient.setSelectedIndex(-1);
		cmbPatient.setBounds(129, 25, 231, 27);
		contentPane.add(cmbPatient);

		JButton btnDelPat = new JButton("-");
		btnDelPat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String table = "Patient";
				
				
				int conf = JOptionPane.showConfirmDialog(null, DELETE_CONFIRMATION, "Delete " + table, JOptionPane.YES_NO_OPTION);
				
				if (conf == JOptionPane.YES_OPTION){
					
					ConnectionMySQL c = new ConnectionMySQL();
					
					String pat = cmbPatient.getSelectedItem().toString();
					String subP = pat.substring(0, pat.indexOf(" - "));
					
					
					c.delete(Integer.parseInt(subP), table);
					
					JOptionPane.showMessageDialog(null, table + " was deleted.");
					
					refresh();
					
					
				}
				
				else{
					
					JOptionPane.showMessageDialog(null, table + " was not deleted.");
					
				}
	
				
			}
		});
		btnDelPat.setBounds(371, 24, 56, 29);
		contentPane.add(btnDelPat);

		cmbFamily = new JComboBox<String>();
		cmbFamily.setSelectedIndex(-1);
		cmbFamily.setBounds(129, 78, 231, 27);
		contentPane.add(cmbFamily);

		JButton btnDelFam = new JButton("-");
		btnDelFam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			String table = "Family";
				
				
				int conf = JOptionPane.showConfirmDialog(null, DELETE_CONFIRMATION, "Delete " + table, JOptionPane.YES_NO_OPTION);
				
				if (conf == JOptionPane.YES_OPTION){
					
					ConnectionMySQL c = new ConnectionMySQL();
					
					String fam = cmbFamily.getSelectedItem().toString();
					String subF = fam.substring(0, fam.indexOf(" - "));
					
					
					c.delete(Integer.parseInt(subF), table);
					
					JOptionPane.showMessageDialog(null, table + " was deleted.");
					
					refresh();
					
					
				}
				
				else{
					
					JOptionPane.showMessageDialog(null, table + " was not deleted.");
					
				}
	
				
				
				
				
			}
		});
		btnDelFam.setBounds(371, 77, 56, 29);
		contentPane.add(btnDelFam);

		cmbProject = new JComboBox<String>();
		cmbProject.setSelectedIndex(-1);
		cmbProject.setBounds(129, 137, 231, 27);
		contentPane.add(cmbProject);

		JButton btnDelProj = new JButton("-");
		btnDelProj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			String table = "Project";
				
				
				int conf = JOptionPane.showConfirmDialog(null, DELETE_CONFIRMATION, "Delete " + table, JOptionPane.YES_NO_OPTION);
				
				if (conf == JOptionPane.YES_OPTION){
					
					ConnectionMySQL c = new ConnectionMySQL();
					
					String proj = cmbProject.getSelectedItem().toString();
					String subP = proj.substring(0, proj.indexOf(" - "));
					
					
					c.delete(Integer.parseInt(subP), table);
					
					JOptionPane.showMessageDialog(null, table + " was deleted.");
					
					refresh();
					
					
				}
				
				else{
					
					JOptionPane.showMessageDialog(null, table + " was not deleted.");
					
				}
	
				
				
				
			}
		});
		btnDelProj.setBounds(371, 136, 56, 29);
		contentPane.add(btnDelProj);

		cmbChip = new JComboBox<String>();
		cmbChip.setSelectedIndex(-1);
		cmbChip.setBounds(129, 191, 231, 27);
		contentPane.add(cmbChip);

		JButton btnDelChip = new JButton("-");
		btnDelChip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			String table = "Chip";
				
				
				int conf = JOptionPane.showConfirmDialog(null, DELETE_CONFIRMATION, "Delete " + table, JOptionPane.YES_NO_OPTION);
				
				if (conf == JOptionPane.YES_OPTION){
					
					ConnectionMySQL c = new ConnectionMySQL();
					
					String chip = cmbChip.getSelectedItem().toString();
					String subC = chip.substring(0, chip.indexOf(" - "));
					
					
					c.delete(Integer.parseInt(subC), table);
					
					JOptionPane.showMessageDialog(null, table + " was deleted.");
					
					refresh();
					
					
				}
				
				else{
					
					JOptionPane.showMessageDialog(null, table + " was not deleted.");
					
				}
	
				
				
				
			}
		});
		btnDelChip.setBounds(371, 190, 56, 29);
		contentPane.add(btnDelChip);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();

			}
		});
		btnClose.setBounds(177, 301, 117, 29);
		contentPane.add(btnClose);

		JLabel lblResearcher = new JLabel("Researcher:");
		lblResearcher.setBounds(16, 251, 142, 16);
		contentPane.add(lblResearcher);

		cmbResearcher = new JComboBox<String>();
		cmbResearcher.setSelectedIndex(-1);
		cmbResearcher.setBounds(129, 247, 231, 27);
		contentPane.add(cmbResearcher);

		JButton btnDelUser = new JButton("-");
		btnDelUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			String table = "User";
				
				
				int conf = JOptionPane.showConfirmDialog(null, DELETE_CONFIRMATION, "Delete Researcher", JOptionPane.YES_NO_OPTION);
				
				if (conf == JOptionPane.YES_OPTION){
					
					ConnectionMySQL c = new ConnectionMySQL();
					
					String user = cmbResearcher.getSelectedItem().toString();
					String subR = user.substring(0, user.indexOf(" - "));
					
					
					c.delete(Integer.parseInt(subR), table);
					
					JOptionPane.showMessageDialog(null, "Researcher was deleted.");
					
					refresh();
					
					
				}
				
				else{
					
					JOptionPane.showMessageDialog(null, "Researcher was not deleted.");
					
				}
	
				
				
				
			}
		});
		btnDelUser.setBounds(371, 246, 56, 29);
		contentPane.add(btnDelUser);
		
		
		refresh();
		

		

	}
	
	
	public void refresh(){
		
		refreshFamily();
		refreshPatient();
		refreshChip();
		refreshProject();
		refreshResearcher();
		
	}
	
	
	public void refreshResearcher() {

		User[] allUser = StartScreen.getConn().getAllUser();
		cmbResearcher.removeAllItems();
		if (allUser != null) {

			for (int i = 0; i < allUser.length; i++) {

				cmbResearcher.addItem(allUser[i].getCod() + " - " + allUser[i].getName());

			}

		}
		
		cmbResearcher.setSelectedIndex(-1);
		
	}
	
	
	
	public void refreshProject() {
		

		Project[] allProject = StartScreen.getConn().getAllProject();
		
		cmbProject.removeAllItems();
		
		if (allProject != null) {

			for (int i = 0; i < allProject.length; i++) {
				
				
				cmbProject.addItem(allProject[i].getCod() + " - " + allProject[i].getName());
				
			}

		}
		
		cmbProject.setSelectedIndex(-1);
		
		

		
		
		
	}
	
	
	
	
	public void refreshChip() {
		

		Chip[] allChip = StartScreen.getConn().getAllChip();
		cmbChip.removeAllItems();
		if (allChip != null) {

			for (int i = 0; i < allChip.length; i++) {

				String folder = allChip[i].getFolder();
				String div = "exportedReports/";
				String name = folder.substring(folder.indexOf(div) + div.length(), folder.length());
				div = "/";
				name = name.substring(0, name.indexOf(div));
				cmbChip.addItem(allChip[i].getCod() + " - " + allChip[i].getType() + " - " + name);

			}

		}
		
		cmbChip.setSelectedIndex(-1);
	}
	
	

	public void refreshFamily() {


		Family[] allFamily = StartScreen.getConn().getAllFamily();
		cmbFamily.removeAllItems();
		if (allFamily != null) {

			for (int i = 0; i < allFamily.length; i++) {

				cmbFamily.addItem(allFamily[i].getCod() + " - " + allFamily[i].getName());

			}

		}

		cmbFamily.setSelectedIndex(-1);

	}
	
	
	public void refreshPatient() {

		
		Patient[] allPatient = StartScreen.getConn().getAllPatient();
		cmbPatient.removeAllItems();
		if (allPatient != null) {

			for (int i = 0; i < allPatient.length; i++) {

				cmbPatient.addItem(allPatient[i].getCod() + " - " + allPatient[i].getName());
				

			}

		}

		cmbPatient.setSelectedIndex(-1);

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
