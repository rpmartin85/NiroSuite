package screen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import conn.ConnectionMySQL;
import logical.Barcode;
import logical.Chip;
import logical.Family;
import logical.FileAprov;
import logical.GetBarcode;
import logical.Patient;
import logical.Project;
import logical.ReadVCF;
import logical.Vcf;
import javax.swing.JList;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.Rectangle;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;


public class ScEditPatient extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCode;
	private JTextField txtAcro;
	private JButton btnSave;
	private JButton btnCancel;
	private JButton btnClear;
	private JLabel lblProject;
	private JButton btnAddProject;
	private JLabel lblFamily;
	private JComboBox<String> cmbFamily;
	private JButton btnAddFamily;
	private ScInsertProject scIP;
	private ScInsertFamily scIF;
	private ScInsertChip scIC;
	private JTextField txtName;
	private JTextField txtVCF;
	private JButton btnAddChip;
	private JList<String> listProject;
	private JList<String> listProjectSelected;
	private JList<String> listProjectRelated;
	private JFileChooser fc1;
	private JScrollPane jscroll;
	private JScrollPane nRJS;
	private JScrollPane rJS;
	private JScrollPane jscrollRelated;
	private DefaultListModel<String> listModel;
	private DefaultListModel<String> listModelRelated;
	private Vcf v;
	private ConnectionMySQL c;
	private JButton btnAddVCF;
	private Patient pat;
	private Patient patBackup;
	private JButton btnInsert;
	private boolean first;
	private JComboBox<String> cmbChip;
	private JTextArea txtAChip;
	private JScrollPane scrollPane;
	private Rectangle rec;

	/**
	 * Create the frame.
	 */
	public ScEditPatient(int cod, Rectangle r) {

		first = true;
		
		rec = r;

		c = StartScreen.getConn();

		listModel = new DefaultListModel<String>();
		listModelRelated = new DefaultListModel<String>();

		setResizable(false);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds((int)r.getX(), (int)r.getY(), 450, 570);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setTitle("Edit Patient");
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

		txtCode.setText("");

		JLabel lblAcro = new JLabel("Acronym:");
		lblAcro.setBounds(175, 21, 85, 16);
		contentPane.add(lblAcro);

		txtAcro = new JTextField();
		txtAcro.setEnabled(false);
		txtAcro.setEditable(false);
		txtAcro.setBounds(261, 16, 161, 26);
		contentPane.add(txtAcro);
		txtAcro.setColumns(10);

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				c = StartScreen.getConn();

				c.setScEP(passThis());

				new Thread() {

					public void run() {

						Vcf v;
						String path = txtVCF.getText();

						v = ReadVCF.read(path);

						int code = Integer.parseInt(txtCode.getText());

						String name = txtName.getText();

						if (name.equals("")) {
							name = "NiroPatient" + code;
						}

						String acro = txtAcro.getText();

						if (!acro.equals("")) {

							if (cmbFamily.getSelectedIndex() != -1) {
								String family = cmbFamily.getSelectedItem().toString();
								String subF = family.substring(0, family.indexOf(" - "));
								int fam = Integer.parseInt(subF);

								Family f = new Family(fam);

								int[] projects = getSelectedProjects();

								if (projects != null) {

									if (cmbChip.getSelectedIndex() != -1) {

										String chip = cmbChip.getSelectedItem().toString();
										String subC = chip.substring(0, chip.indexOf(" - "));
										Chip[] ch = new Chip[1];
										ch[0] = new Chip(Integer.parseInt(subC));

										String chipR = c.getChipResults(ch[0].getCod());

										Barcode bcJO[] = GetBarcode.barcode(chipR);

										boolean find = false;

										double cover20x = 0;

										String nameBC = "";

										for (int i = 0; i < bcJO.length; i++) {

											if (bcJO[i].getAcro().contains(acro) && !find) {

												cover20x = bcJO[i].getCover20x();
												nameBC = bcJO[i].getName();
												find = true;

											}

										}

										if (find) {

											Patient pa = new Patient(code, name, acro, f);

											boolean created = false;

											String header = v.getHeader() + v.getHeader2();

											int codBC = c.getMaxCod("BARCODE", "cod_barcode") + 1;

											Barcode[] bc = new Barcode[1];
											bc[0] = new Barcode(codBC, nameBC, cover20x, ch[0], header, pa);

											Vcf[] vcf = new Vcf[1];
											vcf[0] = v;

											created = c.createPatient(pa, ch, projects, bc, vcf);

											header = header + "Acro: " + acro + " - 20x: " + cover20x + " - BarCode: "
													+ nameBC + " - Chip: " + ch[0].getCod() + " - Patient: "
													+ pa.getCod();

											pa = null;

											if (created) {
												JOptionPane.showMessageDialog(null, "Patient successfully created!");
												setVisible(false);

												dispose();

											}

											else {

												JOptionPane.showMessageDialog(null, "Patient has not been created!");

											}

										}

										else {

											JOptionPane.showMessageDialog(null,
													"Barcode not found on the selected Chip!\n Please change either the chip or the vcf file!");
										}

									}

									else {

										JOptionPane.showMessageDialog(null,
												"Patient has not been created!\nPlease select a chip!");

									}

								}

								else {

									JOptionPane.showMessageDialog(null,
											"Patient has not been created!\nPlease select at least one project!");

								}

							}

							else {

								JOptionPane.showMessageDialog(null,
										"Patient has not been created!\nPlease select a family!");

							}

						}

						else {

							JOptionPane.showMessageDialog(null,
									"Patient has not been created!\nPlease indicate a valid vcf file!");

						}

					}

				}.start();

			}
		});
		btnSave.setBounds(28, 513, 117, 29);

		contentPane.add(btnSave);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(293, 513, 117, 29);

		contentPane.add(btnCancel);

		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				clear();

			}
		});

		btnClear.setBounds(164, 513, 117, 29);

		contentPane.add(btnClear);

		lblProject = new JLabel("Not Related");
		lblProject.setHorizontalAlignment(SwingConstants.CENTER);
		lblProject.setBounds(235, 168, 187, 16);
		contentPane.add(lblProject);

		btnAddProject = new JButton("+");
		btnAddProject.setBounds(200, 289, 56, 29);
		contentPane.add(btnAddProject);

		lblFamily = new JLabel("Family:");
		lblFamily.setBounds(28, 104, 117, 16);
		contentPane.add(lblFamily);

		cmbFamily = new JComboBox<String>();
		cmbFamily.setSelectedIndex(-1);
		cmbFamily.setBounds(124, 100, 231, 27);
		contentPane.add(cmbFamily);

		refreshFamily();

		cmbFamily.setSelectedIndex(-1);

		btnAddFamily = new JButton("+");
		btnAddFamily.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				scIF = new ScInsertFamily(rec);
				scIF.setVisible(true);
				passThisFam();

			}
		});
		btnAddFamily.setBounds(366, 99, 56, 29);

		contentPane.add(btnAddFamily);

		listProject = new JList<String>();

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(28, 63, 85, 16);
		contentPane.add(lblName);

		txtName = new JTextField();
		txtName.setBounds(124, 58, 298, 26);
		contentPane.add(txtName);
		txtName.setColumns(10);

		JLabel lblVcfFile = new JLabel("VCF file:");
		lblVcfFile.setBounds(28, 484, 92, 16);
		contentPane.add(lblVcfFile);

		fc1 = new JFileChooser();
		fc1.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("VCF files", "vcf", "txt");
		fc1.addChoosableFileFilter(filter);

		btnAddVCF = new JButton("+");

		btnAddVCF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int returnVal = fc1.showOpenDialog(ScEditPatient.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc1.getSelectedFile();
					String ext = getExtension(file);
					if (FileAprov.vcf(ext)) {
						txtVCF.setText(file.getAbsolutePath());

						String path = txtVCF.getText();
						v = ReadVCF.read(path);
						String data = v.getData();

						data = data.replace("\n", "");
						txtAcro.setText(data);
					} else {
						JOptionPane.showMessageDialog(null, "Please select a vcf type file.");
					}
				} else {
					System.out.println("Calcelled by user.");

				}

			}
		});
		btnAddVCF.setBounds(366, 479, 56, 29);
		contentPane.add(btnAddVCF);

		txtVCF = new JTextField();
		txtVCF.setEditable(false);
		txtVCF.setBounds(124, 479, 231, 26);
		contentPane.add(txtVCF);
		txtVCF.setColumns(10);

		JLabel lblChip = new JLabel("Chip:");
		lblChip.setBounds(28, 443, 61, 16);
		contentPane.add(lblChip);

		cmbChip = new JComboBox<String>();
		cmbChip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txtAChip != null && cmbChip.getSelectedIndex() > 0) {

					String related = txtAChip.getText();

					if (!related.contains(cmbChip.getSelectedItem().toString())) {

						btnAddVCF.setEnabled(true);
						btnSave.setEnabled(false);

					}
					
					else{
						
						btnAddVCF.setEnabled(false);
						btnSave.setEnabled(true);
						
					}

				}
				
				else{
					
					btnAddVCF.setEnabled(false);
					btnSave.setEnabled(true);
					
				}

			}
		});
		cmbChip.setBounds(124, 439, 231, 27);
		contentPane.add(cmbChip);

		refreshChip();

		cmbChip.setSelectedIndex(-1);

		btnAddVCF.setEnabled(false);

		btnAddChip = new JButton("+");
		btnAddChip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				scIC = new ScInsertChip(rec);
				scIC.setVisible(true);
				passThisChip();

			}
		});
		btnAddChip.setBounds(366, 438, 56, 29);
		contentPane.add(btnAddChip);

		btnAddProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				scIP = new ScInsertProject(rec);
				scIP.setVisible(true);
				passThisP();

			}
		});

		scrollPane = new JScrollPane();
		scrollPane.setBounds(164, 362, 255, 62);
		contentPane.add(scrollPane);

		txtAChip = new JTextArea();
		scrollPane.setViewportView(txtAChip);
		txtAChip.setEnabled(false);
		txtAChip.setEditable(false);

		if (c.getPatient(cod) != null) {

			pat = c.getPatient(cod);
			patBackup = new Patient(pat.getName(), pat.getAcro(), pat.getCod(), pat.getF(), pat.getP(), pat.getC());

			refreshForm();

		}

		else {
			dispose();
		}

		rJS = new JScrollPane();
		rJS.setBounds(28, 189, 187, 100);
		contentPane.add(rJS);

		nRJS = new JScrollPane();
		nRJS.setBounds(235, 189, 187, 100);
		contentPane.add(nRJS);

		JLabel lblProjects = new JLabel("Projects");
		lblProjects.setHorizontalAlignment(SwingConstants.CENTER);
		lblProjects.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblProjects.setBounds(28, 139, 382, 26);
		contentPane.add(lblProjects);

		JSeparator separator = new JSeparator();
		separator.setBounds(6, 131, 438, 12);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(6, 317, 438, 12);
		contentPane.add(separator_1);

		JLabel lblRelated = new JLabel("Related");
		lblRelated.setHorizontalAlignment(SwingConstants.CENTER);
		lblRelated.setBounds(28, 168, 187, 16);
		contentPane.add(lblRelated);

		JButton btnRemove = new JButton(">>");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int selected[] = listProjectRelated.getSelectedIndices();

				int size = selected.length;

				for (int i = size - 1; i >= 0; i--) {

					System.out.println(selected[i]);

					((DefaultListModel<String>) listProjectRelated.getModel()).removeElementAt(selected[i]);

				}

				ListModel<String> lm = listProjectRelated.getModel();

				int[] projects = new int[lm.getSize()];

				for (int i = 0; i < projects.length; i++) {

					String subTemp = lm.getElementAt(i).substring(0, lm.getElementAt(i).indexOf(" - "));

					projects[i] = Integer.parseInt(subTemp);

				}

				Project p[] = c.getProjects(projects);

				pat.setP(p);

				refreshProject();

			}
		});
		btnRemove.setBounds(71, 289, 73, 29);
		contentPane.add(btnRemove);

		btnInsert = new JButton("<<");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<String> li = listProject.getSelectedValuesList();

				int[] projects = new int[li.size()];

				for (int i = 0; i < projects.length; i++) {

					String subTemp = li.get(i).substring(0, li.get(i).indexOf(" - "));

					projects[i] = Integer.parseInt(subTemp);

				}

				int[] projects2 = new int[projects.length + pat.getP().length];

				int i = 0;

				for (i = 0; i < projects.length; i++) {

					projects2[i] = projects[i];

				}

				for (int j = 0; j < pat.getP().length; j++) {

					projects2[i] = pat.getP()[j].getCod();
					i++;

				}

				Arrays.sort(projects2);

				Project p[] = c.getProjects(projects2);

				pat.setP(p);

				refreshProject();

			}
		});
		btnInsert.setBounds(309, 289, 73, 29);
		contentPane.add(btnInsert);

		JLabel lblChips = new JLabel("Chips");
		lblChips.setHorizontalAlignment(SwingConstants.CENTER);
		lblChips.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblChips.setBounds(28, 324, 382, 26);
		contentPane.add(lblChips);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(6, 463, 438, 12);
		contentPane.add(separator_2);

		JLabel lblRelatedChips = new JLabel("Related Chips:");
		lblRelatedChips.setBounds(28, 380, 137, 16);
		contentPane.add(lblRelatedChips);

		refreshProject();

	}

	public void refreshForm() {

		txtCode.setText(pat.getCod() + "");
		txtAcro.setText(pat.getAcro());
		txtName.setText(pat.getName());
		cmbFamily.setSelectedIndex(getFamilyIndex());
		

		String chips = getRelatedChips();
		
		chips = chips + "flag";
		
		String sub = chips.substring(0, chips.indexOf("\nflag"));
		txtAChip.setText(sub);
	}

	public String getRelatedChips() {

		int size = pat.getC().length;

		int code[] = new int[size];

		for (int i = 0; i < size; i++) {
			code[i] = pat.getC()[i].getCod();
		}

		int chips[] = new int[cmbChip.getItemCount()];

		int chipsIndex[] = new int[size];

		String relatedChips = "";

		for (int i = 0; i < chips.length; i++) {

			String temp = cmbChip.getItemAt(i);
			temp = temp.substring(0, temp.indexOf(" - "));

			chips[i] = Integer.parseInt(temp);

			for (int k = 0; k < size; k++) {

				if (code[k] == chips[i]) {
					
					
					relatedChips = relatedChips + cmbChip.getItemAt(i) + "\n";
					chipsIndex[k] = i;

				}

			}

		}

		return relatedChips;

	}

	public int getFamilyIndex() {

		int code = pat.getF().getCod();

		int families[] = new int[cmbFamily.getItemCount()];

		int index = -1;

		for (int i = 0; i < families.length; i++) {

			String temp = cmbFamily.getItemAt(i);
			temp = temp.substring(0, temp.indexOf(" - "));

			families[i] = Integer.parseInt(temp);

			if (code == families[i]) {
				index = i;
			}

		}

		return index;

	}

	public void refresh(String Code) {
		txtCode.setText(Code);

	}

	public void clear() {

		pat.setP(patBackup.getP());

		refreshForm();

		refreshProject();

		txtVCF.setText("");
		cmbChip.setSelectedIndex(-1);
		cmbFamily.setSelectedIndex(getFamilyIndex());
		v = null;
		btnAddVCF.setEnabled(false);
	}

	public void passThisP() {
		scIP.setScEditPatient(this);
	}

	public void passThisFam() {
		scIF.setScEditPatient(this);
	}

	public void passThisChip() {
		scIC.setScEditPatient(this);
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

	public void refreshFamily(int code) {

		refreshFamily();

		for (int i = 0; i < cmbFamily.getItemCount(); i++) {
			String temp = cmbFamily.getItemAt(i);
			String subTemp = temp.substring(0, temp.indexOf(" - "));
			int indexTemp = Integer.parseInt(subTemp);

			if (indexTemp == code) {

				cmbFamily.setSelectedIndex(code - 1);
			}

		}

	}

	public void refreshFamily() {

		Family[] allFamily = StartScreen.getConn().getAllFamily();
		cmbFamily.removeAllItems();
		if (allFamily != null) {

			for (int i = 0; i < allFamily.length; i++) {

				cmbFamily.addItem(allFamily[i].getCod() + " - " + allFamily[i].getName());

			}

		}
	}

	public void refreshChip(int code) {

		refreshChip();

		for (int i = 0; i < cmbChip.getItemCount(); i++) {
			String temp = cmbChip.getItemAt(i);
			String subTemp = temp.substring(0, temp.indexOf(" - "));
			int indexTemp = Integer.parseInt(subTemp);

			if (indexTemp == code) {

				cmbChip.setSelectedIndex(code - 1);
			}

		}

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

				String chip = allChip[i].getCod() + " - " + allChip[i].getType() + " - " + name;

				cmbChip.addItem(chip);

			}

		}
	}

	public void refreshProject() {

		nRJS.setVisible(false);
		rJS.setVisible(false);

		int k = 0;

		Project[] allProject = StartScreen.getConn().getAllProject();

		listModel.clear();

		listModelRelated.clear();

		Project p[] = pat.getP();

		for (int i = 0; i < p.length; i++) {

			listModelRelated.add(i, p[i].getCod() + " - " + p[i].getName());

		}

		if (allProject != null) {

			for (int i = 0; i < allProject.length; i++) {

				boolean found = false;

				for (int j = 0; j < p.length; j++) {

					if (p[j].getCod() == allProject[i].getCod()) {
						found = true;
					}
				}

				if (!found) {

					listModel.add(k, allProject[i].getCod() + " - " + allProject[i].getName());
					k++;

				}
			}

		}

		if (first) {
			listProject = new JList<String>(listModel);
			jscroll = new JScrollPane(listProject);
			jscroll.setBounds(nRJS.getBounds());
			contentPane.add(jscroll);
			listProjectRelated = new JList<String>(listModelRelated);
			jscrollRelated = new JScrollPane(listProjectRelated);
			jscrollRelated.setBounds(rJS.getBounds());
			contentPane.add(jscrollRelated);
			first = false;

		}

		else {
			listProject.setModel(listModel);
			listProjectRelated.setModel(listModelRelated);

		}

	}

	public int[] getSelectedProjects() {

		List<String> temp = listProjectSelected.getSelectedValuesList();

		if (temp.size() > 0) {

			int[] projects = new int[temp.size()];

			for (int i = 0; i < temp.size(); i++) {

				String subTemp = temp.get(i).substring(0, temp.get(i).indexOf(" - "));

				projects[i] = Integer.parseInt(subTemp);

			}

			return projects;

		}

		else
			return null;

	}

	public ScEditPatient passThis() {
		return this;
	}
}