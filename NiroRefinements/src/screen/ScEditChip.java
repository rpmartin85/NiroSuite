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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class ScEditChip extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCode;
	private JTextArea txtFolder;
	private JButton btnSave;
	private JButton btnCancel;
	private JButton btnClear;
	private ScInsertPatient scIPat;
	private JLabel lblType;
	private JRadioButton rdbtnSanger;
	private JRadioButton rdbtnPanel;
	private JRadioButton rdbtnExome;
	private JFileChooser fc1;
	private ScEdit scE;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_2;
	private String type;

	/**
	 * Create the frame.
	 */
	public ScEditChip(int cod, Rectangle r, ScEdit scE2) {

		setResizable(false);

		scIPat = null;

		scE = scE2;

		setTitle("Edit Chip");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds((int) r.getX(), (int) r.getY(), 450, 300);
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

		JLabel lblFolder = new JLabel("Folder:");
		lblFolder.setBounds(164, 21, 61, 16);
		contentPane.add(lblFolder);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(237, 21, 186, 55);
		contentPane.add(scrollPane_2);

		txtFolder = new JTextArea();
		scrollPane_2.setViewportView(txtFolder);
		txtFolder.setEditable(false);
		txtFolder.setColumns(10);

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int code = Integer.parseInt(txtCode.getText());

				Chip ch = new Chip(code);

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
				created = c.updateChip(ch);
				ch = null;

				if (created) {
					JOptionPane.showMessageDialog(null, "Chip successfully updated!");
					setVisible(false);
					clear();

					if (scIPat != null) {
						scIPat.refreshChip(code);

					}
					
					scE.refreshChip();

					dispose();

				}

				else
					JOptionPane.showMessageDialog(null, "Chip has not been updated!");

			}
		});
		btnSave.setBounds(28, 222, 117, 29);
		contentPane.add(btnSave);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(293, 222, 117, 29);
		contentPane.add(btnCancel);

		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (type.equals("Exome")) {

					rdbtnExome.setSelected(true);

				}

				else {

					if (type.equals("Panel")) {

						rdbtnPanel.setSelected(true);

					}

					else {

						rdbtnSanger.setSelected(true);

					}

				}

			}
		});
		btnClear.setBounds(164, 222, 117, 29);
		contentPane.add(btnClear);

		lblType = new JLabel("Type:");
		lblType.setBounds(28, 174, 61, 16);
		contentPane.add(lblType);

		rdbtnExome = new JRadioButton("Exome");
		rdbtnExome.setSelected(true);
		rdbtnExome.setBounds(96, 170, 109, 23);
		contentPane.add(rdbtnExome);

		rdbtnPanel = new JRadioButton("Panel");
		rdbtnPanel.setBounds(204, 170, 117, 23);
		contentPane.add(rdbtnPanel);

		rdbtnSanger = new JRadioButton("Sanger");
		rdbtnSanger.setBounds(320, 170, 124, 23);
		contentPane.add(rdbtnSanger);

		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnExome);
		bg.add(rdbtnPanel);
		bg.add(rdbtnSanger);

		fc1 = new JFileChooser();
		fc1.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON files", "json", "txt");
		fc1.addChoosableFileFilter(filter);

		txtCode.setText(cod + "");

		ConnectionMySQL c = StartScreen.getConn();

		Chip cp = c.getChip(Integer.parseInt(txtCode.getText()));

		txtFolder.setText(cp.getFolder());

		scrollPane = new JScrollPane();
		scrollPane.setBounds(110, 88, 313, 71);
		contentPane.add(scrollPane);

		JTextArea txtResults = new JTextArea();
		scrollPane.setViewportView(txtResults);
		txtResults.setLineWrap(true);
		txtResults.setEditable(false);

		txtResults.setText(cp.getjO());

		JLabel lblResults = new JLabel("Results:");
		lblResults.setBounds(28, 112, 106, 16);
		contentPane.add(lblResults);

		type = cp.getType();

		if (type.equals("Exome")) {

			rdbtnExome.setSelected(true);

		}

		else {

			if (type.equals("Panel")) {

				rdbtnPanel.setSelected(true);

			}

			else {

				rdbtnSanger.setSelected(true);

			}

		}

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

	public void clear() {
		txtFolder.setText("");
	}
}
