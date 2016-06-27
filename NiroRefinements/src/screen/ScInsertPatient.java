package screen;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
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
import javax.swing.SwingConstants;

public class ScInsertPatient extends JFrame {

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
	private JLabel lblChip;
	private JComboBox<String> cmbChip;
	private JButton btnAddChip;
	private JList<String> listProject;
	private JFileChooser fc1;
	private JScrollPane jscroll;
	private DefaultListModel<String> listModel;
	private Vcf v;
	private ConnectionMySQL c;
	private JProgressBar progressBar;
	private JButton btnAddVCF;
	private JLabel lblMetrics;
	private Rectangle rec;

	/**
	 * Create the frame.
	 */
	public ScInsertPatient(Rectangle r) {
		
		setResizable(false);

		rec = r;
		
		listModel = new DefaultListModel<String>();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds((int)r.getX(), (int)r.getY(), 450, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setTitle("Insert New Patient");
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

		int codeTemp = StartScreen.getConn().getMaxCod("PATIENT", "cod_patient");

		txtCode.setText(codeTemp + 1 + "");

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
				
				c.setScIP(passThis());
				
				toogleInsert();

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
												
												toogleInsert();

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

						toogleInsert();

					}

				}.start();

			}
		});
		btnSave.setBounds(28, 371, 117, 29);

		contentPane.add(btnSave);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(293, 371, 117, 29);

		contentPane.add(btnCancel);

		progressBar = new JProgressBar();
		progressBar.setBounds(15, 363, 407, 20);
		contentPane.add(progressBar);
		progressBar.setVisible(false);

		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				clear();

			}
		});

		btnClear.setBounds(164, 371, 117, 29);

		contentPane.add(btnClear);

		lblProject = new JLabel("Project:");
		lblProject.setBounds(28, 186, 117, 16);
		contentPane.add(lblProject);

		btnAddProject = new JButton("+");
		btnAddProject.setBounds(366, 181, 56, 29);
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
		lblVcfFile.setBounds(28, 326, 92, 16);
		contentPane.add(lblVcfFile);

		fc1 = new JFileChooser();
		fc1.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("VCF files", "vcf", "txt");
		fc1.addChoosableFileFilter(filter);

		btnAddVCF = new JButton("+");
		btnAddVCF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int returnVal = fc1.showOpenDialog(ScInsertPatient.this);
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
		btnAddVCF.setBounds(366, 321, 56, 29);
		contentPane.add(btnAddVCF);

		txtVCF = new JTextField();
		txtVCF.setEditable(false);
		txtVCF.setBounds(124, 321, 231, 26);
		contentPane.add(txtVCF);
		txtVCF.setColumns(10);

		lblChip = new JLabel("Chip:");
		lblChip.setBounds(28, 285, 61, 16);
		contentPane.add(lblChip);

		cmbChip = new JComboBox<String>();
		cmbChip.setBounds(124, 281, 231, 27);
		contentPane.add(cmbChip);

		refreshChip();
		cmbChip.setSelectedIndex(-1);

		btnAddChip = new JButton("+");
		btnAddChip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				scIC = new ScInsertChip(rec);
				scIC.setVisible(true);
				passThisChip();

			}
		});
		btnAddChip.setBounds(366, 280, 56, 29);
		contentPane.add(btnAddChip);

		lblMetrics = new JLabel("");
		lblMetrics.setHorizontalAlignment(SwingConstants.CENTER);
		lblMetrics.setBounds(25, 384, 397, 16);
		contentPane.add(lblMetrics);
		lblMetrics.setVisible(false);

		btnAddProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				scIP = new ScInsertProject(rec);
				scIP.setVisible(true);
				passThisP();

			}
		});

		refreshProject();

	}

	public void refresh(String Code) {
		txtCode.setText(Code);

	}

	public void clear() {
		txtAcro.setText("");
		refreshProject();
		txtName.setText("");
		txtVCF.setText("");
		cmbChip.setSelectedIndex(-1);
		cmbFamily.setSelectedIndex(-1);
		v = null;
	}

	public void passThisP() {
		scIP.setScInsertPatient(this);
	}

	public void passThisFam() {
		scIF.setScInsertPatient(this);
	}

	public void passThisChip() {
		scIC.setScInsertPatient(this);
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
				cmbChip.addItem(allChip[i].getCod() + " - " + allChip[i].getType() + " - " + name);

			}

		}
	}

	public void refreshProject() {

		Project[] allProject = StartScreen.getConn().getAllProject();

		listModel.clear();

		if (allProject != null) {

			for (int i = 0; i < allProject.length; i++) {
				listModel.add(i, allProject[i].getCod() + " - " + allProject[i].getName());
			}

			listProject = new JList<String>(listModel);
			jscroll = new JScrollPane(listProject);
			jscroll.setBounds(101, 139, 248, 108);
			contentPane.add(jscroll);

		}
	}

	public int[] getSelectedProjects() {

		List<String> temp = listProject.getSelectedValuesList();

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
	
	
	public void setMax(int max){
		
		progressBar.setMaximum(max);
		
	}
	
	
	

	public void refreshProgress(int i, String progress) {
	
		lblMetrics.setText(progress);
		progressBar.setValue(i);
		
	}

	public void setProgressBar(int max) {
		progressBar.setMaximum(max);
	}

	public void toogleInsert() {

		boolean state = btnSave.isVisible();

		btnSave.setVisible(!state);
		btnClear.setVisible(!state);
		btnCancel.setVisible(!state);
		btnAddChip.setEnabled(!state);
		btnAddFamily.setEnabled(!state);
		btnAddProject.setEnabled(!state);
		btnAddVCF.setEnabled(!state);
		txtName.setEditable(!state);
		cmbFamily.setEnabled(!state);
		jscroll.setEnabled(!state);
		cmbChip.setEnabled(!state);
		progressBar.setVisible(state);
		lblMetrics.setVisible(state);
		jscroll.setVisible(!state);

	}
	
	public ScInsertPatient passThis(){
		return this;
	}
}