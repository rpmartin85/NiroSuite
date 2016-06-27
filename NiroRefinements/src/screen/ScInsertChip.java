package screen;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import conn.ConnectionMySQL;
import logical.Chip;
import logical.FileAprov;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;

public class ScInsertChip extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCode;
	private JTextField txtResults;
	private JButton btnSave;
	private JButton btnCancel;
	private JButton btnClear;
	private ScInsertPatient scIPat;
	private ScEditPatient scEPat;
	private JLabel lblType;
	private JRadioButton rdbtnSanger;
	private JRadioButton rdbtnPanel;
	private JRadioButton rdbtnExome;
	private JFileChooser fc1;

	/**
	 * Create the frame.
	 */
	public ScInsertChip(Rectangle r) {

		scIPat = null;
		scEPat = null;

		setResizable(false);
		setTitle("Insert New Chip");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds((int)r.getX(), (int)r.getY(), 450, 190);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
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

		JLabel lblFolder = new JLabel("Results:");
		lblFolder.setBounds(164, 21, 61, 16);
		contentPane.add(lblFolder);

		txtResults = new JTextField();
		txtResults.setBounds(242, 16, 141, 26);
		contentPane.add(txtResults);
		txtResults.setColumns(10);

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String results = txtResults.getText();

				int code = Integer.parseInt(txtCode.getText());

				if (results.equals("")) {
					JOptionPane.showMessageDialog(null, "Chip has not been created!\nInsert a valid results.json file");
				}

				else {
					Chip ch = new Chip(code, results);

					if (rdbtnExome.isSelected()) {
						ch.setType(rdbtnExome.getText());

					} else {
						if (rdbtnPanel.isSelected()) {
							ch.setType(rdbtnPanel.getText());
						} else {
							ch.setType(rdbtnSanger.getText());
						}
					}

					ConnectionMySQL c = StartScreen.getConn();
					boolean created = false;
					created = c.createChip(ch);
					ch = null;

					if (created) {
						JOptionPane.showMessageDialog(null, "Chip successfully created!");
						setVisible(false);
						clear();

						if (scIPat != null) {
							scIPat.refreshChip(code);

						}
						
						
						
						if (scEPat != null){
							scEPat.refreshChip(code);
						}
						
						

						dispose();

					}

					else
						JOptionPane.showMessageDialog(null, "Chip has not been created!");

				}

			}
		});
		btnSave.setBounds(28, 121, 117, 29);
		contentPane.add(btnSave);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(293, 121, 117, 29);
		contentPane.add(btnCancel);

		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtResults.setText("");

			}
		});
		btnClear.setBounds(164, 121, 117, 29);
		contentPane.add(btnClear);

		lblType = new JLabel("Type:");
		lblType.setBounds(28, 73, 61, 16);
		contentPane.add(lblType);

		rdbtnExome = new JRadioButton("Exome");
		rdbtnExome.setSelected(true);
		rdbtnExome.setBounds(96, 69, 109, 23);
		contentPane.add(rdbtnExome);

		rdbtnPanel = new JRadioButton("Panel");
		rdbtnPanel.setBounds(204, 69, 117, 23);
		contentPane.add(rdbtnPanel);

		rdbtnSanger = new JRadioButton("Sanger");
		rdbtnSanger.setBounds(320, 69, 124, 23);
		contentPane.add(rdbtnSanger);

		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnExome);
		bg.add(rdbtnPanel);
		bg.add(rdbtnSanger);

		fc1 = new JFileChooser();
		fc1.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON files", "json", "txt");
		fc1.addChoosableFileFilter(filter);

		JButton btnAddFolder = new JButton("+");
		btnAddFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int returnVal = fc1.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc1.getSelectedFile();
					String ext = getExtension(file);
					if (FileAprov.json(ext)) {
						txtResults.setText(file.getAbsolutePath());
					} else {
						JOptionPane.showMessageDialog(null, "Please select a json type file.");
					}
				} else {
					System.out.println("Calcelled by user.");

				}

			}
		});
		btnAddFolder.setBounds(388, 16, 56, 29);
		contentPane.add(btnAddFolder);

		int codeTemp = StartScreen.getConn().getMaxCod("CHIP", "cod_chip");

		txtCode.setText(codeTemp + 1 + "");

	}

	public static String getExtension(File f) {
		String ext = null;
		String s = f.getName();
		int i = s.lastIndexOf('.');

		if (i > 0 && i < s.length() - 1) {
			ext = s.substring(i + 1).toLowerCase();
		}
		return ext;
	}

	public void setScInsertPatient(ScInsertPatient scIPat) {

		this.scIPat = scIPat;

	}
	
	public void setScEditPatient(ScEditPatient scEPat) {

		this.scEPat = scEPat;

	}
	

	public void clear() {
		txtResults.setText("");
	}
}
