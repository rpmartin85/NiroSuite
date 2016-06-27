package screen;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import conn.ConnectionMySQL;
import logical.Project;
import logical.User;

import javax.swing.JComboBox;

public class ScInsertProject extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCode;
	private JTextField txtName;
	private JButton btnSave;
	private JButton btnCancel;
	private JButton btnClear;
	private JLabel lblResearcher;
	private JComboBox<String> cmbResearcher;
	private JButton btnAdd;

	private ScInsertPatient scIPat;
	private ScEditPatient scEPat;
	private ScInsertUser scIU;
	private Rectangle rec;

	/**
	 * Create the frame.
	 */
	public ScInsertProject(Rectangle r) {

		scIPat = null;
		scEPat = null;
		
		rec = r;

		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds((int)r.getX(), (int)r.getY(), 450, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setTitle("Insert New Project");
		setContentPane(contentPane);

		JLabel lblCode = new JLabel("Code:");
		lblCode.setBounds(28, 21, 61, 16);
		contentPane.add(lblCode);

		txtCode = new JTextField();
		txtCode.setEnabled(false);
		txtCode.setEditable(false);
		txtCode.setBounds(83, 16, 51, 26);
		contentPane.add(txtCode);
		txtCode.setColumns(10);

		int codeTemp = StartScreen.getConn().getMaxCod("PROJECT", "cod_project");

		txtCode.setText(codeTemp + 1 + "");

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(188, 21, 61, 16);
		contentPane.add(lblName);

		txtName = new JTextField();
		txtName.setBounds(261, 16, 161, 26);
		contentPane.add(txtName);
		txtName.setColumns(10);

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int code = Integer.parseInt(txtCode.getText());
				String name = txtName.getText();
				int user = 0;

				if (cmbResearcher.getSelectedIndex() != -1) {
					String temp = cmbResearcher.getSelectedItem().toString();

					String subTemp = temp.substring(0, temp.indexOf(" - "));
					user = Integer.parseInt(subTemp);

					if (name.equals("")) {
						name = "NiroProject" + code;
					}

					Project p = new Project(name, code, user);

					ConnectionMySQL c = StartScreen.getConn();
					boolean created = false;

					created = c.createProject(p);

					p = null;

					if (created) {
						JOptionPane.showMessageDialog(null, "Project successfully created!");
						setVisible(false);
						clear();

						if (scIPat != null) {
							scIPat.refreshProject();

							dispose();

						}
						
						if (scEPat != null){
							
							scEPat.refreshProject();
							
							dispose();
							
						}
						

					} else
						JOptionPane.showMessageDialog(null, "Project has not been created!");

				}

				else {
					JOptionPane.showMessageDialog(null, "Please select a researcher!");
				}

	
			}
		});
		btnSave.setBounds(28, 120, 117, 29);

		contentPane.add(btnSave);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(293, 120, 117, 29);

		contentPane.add(btnCancel);

		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();

			}
		});

		btnClear.setBounds(164, 120, 117, 29);

		contentPane.add(btnClear);

		lblResearcher = new JLabel("Researcher:");
		lblResearcher.setBounds(28, 66, 117, 16);
		contentPane.add(lblResearcher);

		cmbResearcher = new JComboBox<String>();
		cmbResearcher.setBounds(124, 62, 231, 27);
		contentPane.add(cmbResearcher);
		// cmbResearcher.addItem("Item 1");

		refresh();

		cmbResearcher.setSelectedIndex(-1);

		btnAdd = new JButton("+");
		btnAdd.setBounds(366, 61, 56, 29);
		contentPane.add(btnAdd);

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				scIU = new ScInsertUser(rec);
				scIU.setVisible(true);
				passThis();

			}
		});

	}

	public void setScInsertPatient(ScInsertPatient scIPat) {

		this.scIPat = scIPat;

	}
	
	
	public void setScEditPatient(ScEditPatient scEPat) {

		this.scEPat = scEPat;

	}

	public void refresh(int code) {

		refresh();

		for (int i = 0; i < cmbResearcher.getItemCount(); i++) {
			String temp = cmbResearcher.getItemAt(i);
			String subTemp = temp.substring(0, temp.indexOf(" - "));
			int indexTemp = Integer.parseInt(subTemp);

			if (indexTemp == code) {

				cmbResearcher.setSelectedIndex(code - 1);
			}

		}

	}

	public void refresh() {

		User[] allUser = StartScreen.getConn().getAllUser();
		cmbResearcher.removeAllItems();
		if (allUser != null) {

			for (int i = 0; i < allUser.length; i++) {

				cmbResearcher.addItem(allUser[i].getCod() + " - " + allUser[i].getName());

			}

		}
	}

	public void clear() {
		txtName.setText("");
		cmbResearcher.setSelectedIndex(-1);

	}

	public void passThis() {
		scIU.setScInsertProject(this);
	}

}
