package screen;


import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
import java.awt.event.ActionEvent;

public class ScEdit extends JFrame {

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
	private ScEditPatient scEPat;
	private ScEditFamily scEF;
	private ScEditProject scEP;
	private ScEditChip scEC;
	private ScEditUser scEU;
	private Rectangle rec;

	/**
	 * Create the frame.
	 */
	public ScEdit(Rectangle r) {
		rec = r;
		setResizable(false);
		setTitle("Edit");
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

		JButton btnEditPat = new JButton("=");
		btnEditPat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cmbPatient.getSelectedIndex() != -1) {

					String data = cmbPatient.getSelectedItem().toString();
					String subD = data.substring(0, data.indexOf(" - "));
					int cod = Integer.parseInt(subD);

					scEPat = new ScEditPatient(cod, rec);
					scEPat.setVisible(true);

				}

				else {
					JOptionPane.showMessageDialog(null, "Please, select a Patient to edit.");
				}

			}
		});
		btnEditPat.setBounds(371, 24, 56, 29);
		contentPane.add(btnEditPat);

		cmbFamily = new JComboBox<String>();
		cmbFamily.setSelectedIndex(-1);
		cmbFamily.setBounds(129, 78, 231, 27);
		contentPane.add(cmbFamily);

		JButton btnEditFam = new JButton("=");
		btnEditFam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cmbFamily.getSelectedIndex() != -1) {

					String data = cmbFamily.getSelectedItem().toString();
					String subD = data.substring(0, data.indexOf(" - "));
					int cod = Integer.parseInt(subD);

					scEF = new ScEditFamily(cod, rec, passThis());
					scEF.setVisible(true);
				}

				else {
					JOptionPane.showMessageDialog(null, "Please, select a Family to edit.");
				}

				

			}
		});
		btnEditFam.setBounds(371, 77, 56, 29);
		contentPane.add(btnEditFam);

		cmbProject = new JComboBox<String>();
		cmbProject.setSelectedIndex(-1);
		cmbProject.setBounds(129, 137, 231, 27);
		contentPane.add(cmbProject);

		JButton btnEditProj = new JButton("=");
		btnEditProj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (cmbProject.getSelectedIndex() != -1) {

					String data = cmbProject.getSelectedItem().toString();
					String subD = data.substring(0, data.indexOf(" - "));
					int cod = Integer.parseInt(subD);

					scEP = new ScEditProject(cod, rec, passThis());
					scEP.setVisible(true);
					
				}

				else {
					JOptionPane.showMessageDialog(null, "Please, select a Project to edit.");
				}

				
				
				

				

			}
		});
		btnEditProj.setBounds(371, 136, 56, 29);
		contentPane.add(btnEditProj);

		cmbChip = new JComboBox<String>();
		cmbChip.setSelectedIndex(-1);
		cmbChip.setBounds(129, 191, 231, 27);
		contentPane.add(cmbChip);

		JButton btnEditChip = new JButton("=");
		btnEditChip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if (cmbChip.getSelectedIndex() != -1) {

					String data = cmbChip.getSelectedItem().toString();
					String subD = data.substring(0, data.indexOf(" - "));
					int cod = Integer.parseInt(subD);



					scEC = new ScEditChip(cod, rec, passThis());
					scEC.setVisible(true);
					
				}

				else {
					JOptionPane.showMessageDialog(null, "Please, select a Chip to edit.");
				}

				
				
				

			}
		});
		btnEditChip.setBounds(371, 190, 56, 29);
		contentPane.add(btnEditChip);

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

		JButton btnEditUser = new JButton("=");
		btnEditUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (cmbResearcher.getSelectedIndex() != -1) {

					String data = cmbResearcher.getSelectedItem().toString();
					String subD = data.substring(0, data.indexOf(" - "));
					int cod = Integer.parseInt(subD);



					scEU = new ScEditUser(cod, rec, passThis());
					scEU.setVisible(true);
					
				}

				else {
					JOptionPane.showMessageDialog(null, "Please, select a Researcher to edit.");
				}

				
				
				
				
				

				

			}
		});
		btnEditUser.setBounds(371, 246, 56, 29);
		contentPane.add(btnEditUser);

		refresh();

	}

	public void refresh() {

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
	
	public ScEdit passThis(){
		
		return this;
		
	}

}
