package screen;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import org.json.simple.JSONObject;
import conn.ConnectionMySQL;
import logical.ConfigNiro;
import logical.Omim;

import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JSeparator;

public class ScStartAnno extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnAdd;
	private JFileChooser fc1;
	private JTextField txtFilePath;
	private String path;
	private String protocol;
	private String operation;
	private JCheckBox chkRefGene;
	private JCheckBox chkKnownGene;
	private JCheckBox chkEnsGene;
	private JCheckBox chkDBNSFP30a;
	private JCheckBox chkProteinDomain;
	private JCheckBox chkSpliceSite;
	private JCheckBox chkExac03;
	private JCheckBox chk1000g;
	private JCheckBox chkClinvar;
	private JCheckBox chkCosmic;
	private JCheckBox chk129;
	private JCheckBox chk131;
	private JCheckBox chk135;
	private JCheckBox chk138;
	private JCheckBox chk142;
	private JCheckBox chk147;
	private JCheckBox chkSelectAllGeneBased;
	private JCheckBox chkSelectAllFilterBased;
	private JCheckBox chkSelectAllSNP;
	private ConnectionMySQL c;
	private JCheckBox chkSelectAllAdditional;
	private JCheckBox chkOmim;
	private JCheckBox chkHgmd;

	/**
	 * Create the frame.
	 */
	public ScStartAnno(Rectangle r, final boolean multiple) {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds((int) r.getX(), (int) r.getY(), 500, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setTitle("Multiple VCF Annotation");
		setContentPane(contentPane);

		fc1 = new JFileChooser();

		fc1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		JLabel lblVcfFile = new JLabel("VCF folder:");
		lblVcfFile.setBounds(17, 30, 86, 16);
		contentPane.add(lblVcfFile);

		btnAdd = new JButton("+");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int returnVal = fc1.showOpenDialog(contentPane);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc1.getSelectedFile();

					txtFilePath.setText(file.getAbsolutePath());

				} else {
					System.out.println("Calcelled by user.");

				}

			}
		});

		btnAdd.setBounds(433, 25, 50, 29);
		contentPane.add(btnAdd);

		txtFilePath = new JTextField();
		txtFilePath.setBounds(100, 25, 321, 26);
		contentPane.add(txtFilePath);
		txtFilePath.setColumns(10);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JSONObject jO = new JSONObject();
				jO = ConfigNiro.getJson();
				String pathAnnovar = jO.get("path").toString();

				String command = "";

				if (!multiple) {

					String pathVCF = txtFilePath.getText();

					path = pathVCF.substring(0, pathVCF.lastIndexOf("/"));

					String VCF = pathVCF.substring(pathVCF.lastIndexOf("/") + 1, pathVCF.length());

					path = path + "/myAnno";

					command = "mkdir " + path;

					Run(command);

					command = "ls -1 " + path + "/../" + VCF + " >> " + path + "/list.txt";

				}

				else {

					path = txtFilePath.getText();

					path = path + "/myAnno";

					command = "mkdir " + path;

					Run(command);

					command = "ls -1 " + path + "/../*.vcf >> " + path + "/list.txt";

				}

				Run(command);

				path = path + "/list.txt";

				String line = "";

				System.out.println(path);

				String pathVCF = path.substring(0, path.indexOf("/list.txt"));

				if (chkOmim.isSelected()){
					
					c = new ConnectionMySQL();
					
				}
				
				

				try {

					Scanner sc = new Scanner(new File(path));
					while (sc.hasNext()) {

						line = sc.nextLine();

						pathVCF = line.replace("myAnno/../", "");

						String path2 = line.replace("../", "");
						path2 = path2.replace(".vcf", ".avinput");

						// convert vcf4 to avinput format file
						command = "perl " + pathAnnovar + "/convert2annovar.pl -format vcf4 " + pathVCF
								+ " --includeinfo -withzyg -out " + path2;

						System.out.println(command);
						
						//Apagar esse trecho depois
						
						setProtocol();
						
						String command2 = "perl " + pathAnnovar + "/table_annovar.pl " + path2 + " " + pathAnnovar
								+ "/humandb/ -buildver hg19 -otherinfo -out  " + path2 + " -remove -protocol "
								+ protocol + " -operation " + operation;
						
						System.out.println(command2);
						
						// Fim do trecho para ser apagado

						Execute(command);

						setProtocol();

						// perform variation annotations according to the chosen
						// protocols.
						command = "perl " + pathAnnovar + "/table_annovar.pl " + path2 + " " + pathAnnovar
								+ "/humandb/ -buildver hg19 -otherinfo -out  " + path2 + " -remove -protocol "
								+ protocol + " -operation " + operation;

						System.out.println(command);

						Execute(command);

						command = "rm " + path2;

						Execute(command);

						command = "rm " + path2.substring(0, path2.indexOf("myAnno/") + 7) + "list.txt";

						Execute(command);

						// set to the variable path2 the output file address
						// with xls extension
						path2 = path2 + ".hg19_multianno.txt";

						System.out.println(path2);

						getOmim(path2, chkOmim.isSelected());

						// rename output file from txt to xls extension
						command = "rm " + path2;
						Execute(command);

					}

					sc.close();

				} catch (FileNotFoundException ex) {
					System.out.println("Unable to open file '" + path + "'");
				}

			}

		});
		btnSave.setBounds(17, 463, 117, 29);
		contentPane.add(btnSave);

		if (!multiple) {
			fc1.setFileSelectionMode(JFileChooser.FILES_ONLY);
			lblVcfFile.setText("VCF file:");
			setTitle("Single VCF Annotation");

		}

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				txtFilePath.setText("");
				chkSelectAllFilterBased.setSelected(true);
				chkSelectAllGeneBased.setSelected(true);
				chkSelectAllSNP.setSelected(true);

				chk129.setSelected(true);
				chk131.setSelected(true);
				chk135.setSelected(true);
				chk138.setSelected(true);
				chk142.setSelected(true);
				chk147.setSelected(true);

				chkDBNSFP30a.setSelected(true);
				chkProteinDomain.setSelected(true);
				chkSpliceSite.setSelected(true);
				chkCosmic.setSelected(true);
				chkExac03.setSelected(true);
				chk1000g.setSelected(true);
				chkClinvar.setSelected(true);

				chkRefGene.setSelected(true);
				chkKnownGene.setSelected(true);
				chkEnsGene.setSelected(true);

			}
		});
		btnClear.setBounds(188, 463, 117, 29);
		contentPane.add(btnClear);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();

			}
		});
		btnCancel.setBounds(366, 463, 117, 29);
		contentPane.add(btnCancel);

		JLabel lblGenebasedAnnotation = new JLabel("Gene-based annotation:");
		lblGenebasedAnnotation.setBounds(17, 86, 190, 16);
		contentPane.add(lblGenebasedAnnotation);

		chkRefGene = new JCheckBox("refGene");
		chkRefGene.setSelected(true);
		chkRefGene.setBounds(17, 114, 106, 23);
		contentPane.add(chkRefGene);

		chkKnownGene = new JCheckBox("knownGene");
		chkKnownGene.setSelected(true);
		chkKnownGene.setBounds(188, 114, 128, 23);
		contentPane.add(chkKnownGene);

		chkEnsGene = new JCheckBox("ensGene");
		chkEnsGene.setSelected(true);
		chkEnsGene.setBounds(375, 114, 108, 23);
		contentPane.add(chkEnsGene);

		JSeparator separator = new JSeparator();
		separator.setBounds(17, 63, 466, 12);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(17, 149, 466, 16);
		contentPane.add(separator_1);

		JLabel lblFilterbasedAnnotation = new JLabel("Filter-based annotation:");
		lblFilterbasedAnnotation.setBounds(17, 177, 190, 16);
		contentPane.add(lblFilterbasedAnnotation);

		chkDBNSFP30a = new JCheckBox("dbnsfp30a");
		chkDBNSFP30a.setSelected(true);
		chkDBNSFP30a.setBounds(17, 209, 128, 23);
		contentPane.add(chkDBNSFP30a);

		chkProteinDomain = new JCheckBox("protein domain");
		chkProteinDomain.setSelected(true);
		chkProteinDomain.setBounds(154, 209, 151, 23);
		contentPane.add(chkProteinDomain);

		chkSpliceSite = new JCheckBox("splice site prediction");
		chkSpliceSite.setSelected(true);
		chkSpliceSite.setBounds(302, 209, 192, 23);
		contentPane.add(chkSpliceSite);

		chkExac03 = new JCheckBox("exac03");
		chkExac03.setSelected(true);
		chkExac03.setBounds(142, 253, 128, 23);
		contentPane.add(chkExac03);

		chk1000g = new JCheckBox("1000g");
		chk1000g.setSelected(true);
		chk1000g.setBounds(264, 253, 128, 23);
		contentPane.add(chk1000g);

		chkClinvar = new JCheckBox("Clinvar");
		chkClinvar.setSelected(true);
		chkClinvar.setBounds(377, 253, 106, 23);
		contentPane.add(chkClinvar);

		chkCosmic = new JCheckBox("Cosmic");
		chkCosmic.setSelected(true);
		chkCosmic.setBounds(17, 253, 128, 23);
		contentPane.add(chkCosmic);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(17, 288, 466, 16);
		contentPane.add(separator_2);

		JLabel lblSnp = new JLabel("SNP:");
		lblSnp.setBounds(17, 303, 78, 16);
		contentPane.add(lblSnp);

		chk129 = new JCheckBox("129");
		chk129.setSelected(true);
		chk129.setBounds(17, 331, 78, 23);
		contentPane.add(chk129);

		chk131 = new JCheckBox("131");
		chk131.setSelected(true);
		chk131.setBounds(94, 331, 78, 23);
		contentPane.add(chk131);

		chk135 = new JCheckBox("135");
		chk135.setSelected(true);
		chk135.setBounds(170, 331, 78, 23);
		contentPane.add(chk135);

		chk138 = new JCheckBox("138");
		chk138.setSelected(true);
		chk138.setBounds(248, 331, 78, 23);
		contentPane.add(chk138);

		chk142 = new JCheckBox("142");
		chk142.setSelected(true);
		chk142.setBounds(327, 331, 78, 23);
		contentPane.add(chk142);

		chk147 = new JCheckBox("147");
		chk147.setSelected(true);
		chk147.setBounds(406, 331, 78, 23);
		contentPane.add(chk147);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(17, 365, 466, 16);
		contentPane.add(separator_3);

		chkSelectAllGeneBased = new JCheckBox("Select All");
		chkSelectAllGeneBased.setSelected(true);
		chkSelectAllGeneBased.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				chkRefGene.setSelected(chkSelectAllGeneBased.isSelected());
				chkKnownGene.setSelected(chkSelectAllGeneBased.isSelected());
				chkEnsGene.setSelected(chkSelectAllGeneBased.isSelected());

			}
		});
		chkSelectAllGeneBased.setBounds(198, 82, 128, 23);
		contentPane.add(chkSelectAllGeneBased);

		chkSelectAllFilterBased = new JCheckBox("Select All");
		chkSelectAllFilterBased.setSelected(true);
		chkSelectAllFilterBased.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				chkDBNSFP30a.setSelected(chkSelectAllFilterBased.isSelected());
				chkProteinDomain.setSelected(chkSelectAllFilterBased.isSelected());
				chkSpliceSite.setSelected(chkSelectAllFilterBased.isSelected());
				chkCosmic.setSelected(chkSelectAllFilterBased.isSelected());
				chkExac03.setSelected(chkSelectAllFilterBased.isSelected());
				chk1000g.setSelected(chkSelectAllFilterBased.isSelected());
				chkClinvar.setSelected(chkSelectAllFilterBased.isSelected());

			}
		});
		chkSelectAllFilterBased.setBounds(198, 173, 128, 23);
		contentPane.add(chkSelectAllFilterBased);

		chkSelectAllSNP = new JCheckBox("Select All");
		chkSelectAllSNP.setSelected(true);
		chkSelectAllSNP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				chk129.setSelected(chkSelectAllSNP.isSelected());
				chk131.setSelected(chkSelectAllSNP.isSelected());
				chk135.setSelected(chkSelectAllSNP.isSelected());
				chk138.setSelected(chkSelectAllSNP.isSelected());
				chk142.setSelected(chkSelectAllSNP.isSelected());
				chk147.setSelected(chkSelectAllSNP.isSelected());

			}
		});
		chkSelectAllSNP.setBounds(94, 299, 128, 23);
		contentPane.add(chkSelectAllSNP);

		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(17, 447, 466, 16);
		contentPane.add(separator_4);

		JLabel lblAdditionalAnnotation = new JLabel("Additional annotation:");
		lblAdditionalAnnotation.setBounds(17, 384, 190, 16);
		contentPane.add(lblAdditionalAnnotation);

		chkSelectAllAdditional = new JCheckBox("Select All");
		chkSelectAllAdditional.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				chkOmim.setSelected(chkSelectAllAdditional.isSelected());

			}
		});
		chkSelectAllAdditional.setSelected(true);
		chkSelectAllAdditional.setBounds(198, 380, 128, 23);
		contentPane.add(chkSelectAllAdditional);

		chkOmim = new JCheckBox("OMIM");
		chkOmim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (chkOmim.isSelected()) {

					chkRefGene.setSelected(true);

				}

			}
		});
		chkOmim.setSelected(true);
		chkOmim.setBounds(17, 412, 106, 23);
		contentPane.add(chkOmim);

		chkHgmd = new JCheckBox("HGMD (not available)");
		chkHgmd.setEnabled(false);
		chkHgmd.setBounds(291, 412, 192, 23);
		contentPane.add(chkHgmd);

	}

	protected void getOmim(String path2, boolean omim) {

		try {

			String line = "";

			Scanner sc = new Scanner(new File(path2));

			int pos = 0;

			int pos2 = 0;

			String lineAnno[];

			FileWriter file = new FileWriter(path2.substring(0, path2.indexOf(".txt")) + "_NIRO.xls");

			System.out.println(path2.substring(0, path2.indexOf(".txt")) + "_NIRO.xls");

			PrintWriter write = new PrintWriter(file);

			while (sc.hasNext()) {

				line = sc.nextLine();

				lineAnno = line.split("\t");

				if (pos == 0) {

					pos2 = lineAnno.length;

					for (int i = 0; i < lineAnno.length; i++) {

						if (lineAnno[i].equals("Gene.refGene")) {
							pos = i;

						}

					}

					lineAnno[pos2 - 1] = "Genotype";

					line = "";

					for (int i = 0; i < lineAnno.length; i++) {

						line = line + lineAnno[i] + "\t";

					}

					if (omim) {

						line = line + "OMIM phenotype\tOMIM comments\n";

					}
					
					else {
						
						line = line + "\n";
						
					}

					write.print(line);

				}

				else {

					if (omim) {

						line = "";

						Omim o = c.getOmimPhenotype(lineAnno[pos]);

						for (int i = 0; i < lineAnno.length; i++) {

							if (i == pos2) {
								line = line + o.getPhenotype() + "\t";
							}

							else if (i == pos2 + 1) {
								line = line + o.getComments() + "\t";
							}

							else if (i == lineAnno.length - 1) {
								line = line + lineAnno[i] + "\n";
							}

							else {
								line = line + lineAnno[i] + "\t";
							}

						}

					}

					else {

						line = line + "\n";

					}

					write.print(line);

				}

			}

			write.close();
			sc.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getExecussion(String command) {

		try {
			Process proc = Runtime.getRuntime().exec(new String[] { "bash", "-c", command });

			proc.waitFor();

			String saida = inputStreamToString(proc.getInputStream());

			return saida;

			// return proc.getOutputStream();
		} catch (IOException e1) {

			e1.printStackTrace();
			return null;
		} catch (InterruptedException e1) {
			e1.printStackTrace();
			return null;
		}

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

	public boolean Run(String command) {

		Runtime run = Runtime.getRuntime();

		try {

			Process proc = run.exec(new String[] { "bash", "-c", command });
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

	public void setProtocol() {

		String protocol = "";
		String operation = "";

		if (chkRefGene.isSelected()) {

			protocol = "refGene,";
			operation = "g,";

		}
		if (chkKnownGene.isSelected()) {

			protocol = protocol + "knownGene,";
			operation = operation + "g,";

		}
		if (chkEnsGene.isSelected()) {

			protocol = protocol + "ensGene,";
			operation = operation + "g,";

		}

		if (chkDBNSFP30a.isSelected()) {

			protocol = protocol + "dbnsfp30a,";
			operation = operation + "f,";

		}
		if (chkProteinDomain.isSelected()) {

			protocol = protocol + "dbnsfp31a_interpro,";
			operation = operation + "f,";

		}
		if (chkSpliceSite.isSelected()) {

			protocol = protocol + "dbscsnv11,spidex,";
			operation = operation + "f,f,";

		}
		if (chkCosmic.isSelected()) {

			protocol = protocol + "cosmic70,";
			operation = operation + "f,";

		}
		if (chkExac03.isSelected()) {

			protocol = protocol + "exac03,";
			operation = operation + "f,";

		}
		if (chk1000g.isSelected()) {

			protocol = protocol
					+ "1000g2015aug_all,1000g2015aug_afr,1000g2015aug_amr,1000g2015aug_eas,1000g2015aug_eur,1000g2015aug_sas,";
			operation = operation + "f,f,f,f,f,f,";

		}
		if (chkClinvar.isSelected()) {

			protocol = protocol + "clinvar_20160302,";
			operation = operation + "f,";

		}

		if (chk129.isSelected()) {

			protocol = protocol + "snp129,";
			operation = operation + "f,";

		}
		if (chk131.isSelected()) {

			protocol = protocol + "snp131,";
			operation = operation + "f,";

		}
		if (chk135.isSelected()) {

			protocol = protocol + "snp135,";
			operation = operation + "f,";

		}
		if (chk138.isSelected()) {

			protocol = protocol + "snp138,";
			operation = operation + "f,";

		}
		if (chk142.isSelected()) {

			protocol = protocol + "avsnp142,";
			operation = operation + "f,";

		}
		if (chk147.isSelected()) {

			protocol = protocol + "avsnp147,";
			operation = operation + "f,";

		}
		
		if (chkHgmd.isSelected()) {

			protocol = protocol + "hgmd,";
			operation = operation + "f,";

		}
		
		

		if (protocol.length() > 0) {

			this.protocol = protocol.substring(0, protocol.length() - 1);
			this.operation = operation.substring(0, operation.length() - 1);

		}

		// System.out.println(this.protocol);
		// System.out.println(this.operation);

	}

	/*
	 * 
	 * JSONObject jO = new JSONObject(); jO = ConfigNiro.getJson(); String
	 * pathAnnovar = jO.get("path").toString(); String command;
	 * 
	 * command = "perl " + pathAnnovar +
	 * "/annotate_variation.pl -downdb -webfrom annovar refGene --buildver hg19 "
	 * + pathAnnovar + "/humandb/"; Execute(command); command = "perl " +
	 * pathAnnovar +
	 * "/annotate_variation.pl -downdb -webfrom annovar knownGene --buildver hg19 "
	 * + pathAnnovar + "/humandb/"; Execute(command); command = "perl " +
	 * pathAnnovar +
	 * "/annotate_variation.pl -downdb -webfrom annovar ensGene --buildver hg19 "
	 * + pathAnnovar + "/humandb/"; Execute(command); command = "perl " +
	 * pathAnnovar +
	 * "/annotate_variation.pl -downdb -webfrom annovar cosmic70 --buildver hg19 "
	 * + pathAnnovar + "/humandb/"; Execute(command); command = "perl " +
	 * pathAnnovar +
	 * "/annotate_variation.pl -downdb -webfrom annovar snp129 --buildver hg19 "
	 * + pathAnnovar + "/humandb/"; Execute(command); command = "perl " +
	 * pathAnnovar +
	 * "/annotate_variation.pl -downdb -webfrom annovar snp131 --buildver hg19 "
	 * + pathAnnovar + "/humandb/"; Execute(command); command = "perl " +
	 * pathAnnovar +
	 * "/annotate_variation.pl -downdb -webfrom annovar snp135 --buildver hg19 "
	 * + pathAnnovar + "/humandb/"; Execute(command); command = "perl " +
	 * pathAnnovar +
	 * "/annotate_variation.pl -downdb -webfrom annovar dbnsfp30a --buildver hg19 "
	 * + pathAnnovar + "/humandb/"; Execute(command); command = "perl " +
	 * pathAnnovar +
	 * "/annotate_variation.pl -downdb -webfrom annovar dbnsfp31a_interpro --buildver hg19 "
	 * + pathAnnovar + "/humandb/"; Execute(command); command = "perl " +
	 * pathAnnovar +
	 * "/annotate_variation.pl -downdb -webfrom annovar dbscsnv11 --buildver hg19 "
	 * + pathAnnovar + "/humandb/"; Execute(command); command = "perl " +
	 * pathAnnovar +
	 * "/annotate_variation.pl -downdb -webfrom annovar exac03 --buildver hg19 "
	 * + pathAnnovar + "/humandb/"; Execute(command); command = "perl " +
	 * pathAnnovar +
	 * "/annotate_variation.pl -downdb -webfrom annovar 1000g2015aug --buildver hg19 "
	 * + pathAnnovar + "/humandb/"; Execute(command); command = "perl " +
	 * pathAnnovar +
	 * "/annotate_variation.pl -downdb -webfrom annovar clinvar_20160302 --buildver hg19 "
	 * + pathAnnovar + "/humandb/"; Execute(command); command = "perl " +
	 * pathAnnovar +
	 * "/annotate_variation.pl -downdb -webfrom annovar snp138 --buildver hg19 "
	 * + pathAnnovar + "/humandb/"; Execute(command); command = "perl " +
	 * pathAnnovar +
	 * "/annotate_variation.pl -downdb -webfrom annovar avsnp142 --buildver hg19 "
	 * + pathAnnovar + "/humandb/"; Execute(command); command = "perl " +
	 * pathAnnovar +
	 * "/annotate_variation.pl -downdb -webfrom annovar avsnp147 --buildver hg19 "
	 * + pathAnnovar + "/humandb/"; Execute(command);
	 * 
	 * 
	 * public boolean Execute(String command) {
	 * 
	 * try { Process proc = Runtime.getRuntime().exec(command); proc.waitFor();
	 * 
	 * return true; } catch (IOException e1) {
	 * 
	 * e1.printStackTrace(); return false; } catch (InterruptedException e1) {
	 * e1.printStackTrace(); return false; }
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * perl annotate_variation.pl -downdb -webfrom annovar refGene --buildver
	 * hg19 humandb/ perl annotate_variation.pl -downdb -webfrom annovar
	 * knownGene --buildver hg19 humandb/ perl annotate_variation.pl -downdb
	 * -webfrom annovar ensGene --buildver hg19 humandb/ perl
	 * annotate_variation.pl -downdb -webfrom annovar cosmic70 --buildver hg19
	 * humandb/ perl annotate_variation.pl -downdb -webfrom annovar snp129
	 * --buildver hg19 humandb/ perl annotate_variation.pl -downdb -webfrom
	 * annovar snp131 --buildver hg19 humandb/ perl annotate_variation.pl
	 * -downdb -webfrom annovar snp135 --buildver hg19 humandb/ perl
	 * annotate_variation.pl -downdb -webfrom annovar dbnsfp30a --buildver hg19
	 * humandb/ perl annotate_variation.pl -downdb -webfrom annovar
	 * dbnsfp31a_interpro --buildver hg19 humandb/ perl annotate_variation.pl
	 * -downdb -webfrom annovar dbscsnv11 --buildver hg19 humandb/ perl
	 * annotate_variation.pl -downdb -webfrom annovar exac03 --buildver hg19
	 * humandb/ perl annotate_variation.pl -downdb -webfrom annovar 1000g2015aug
	 * --buildver hg19 humandb/ perl annotate_variation.pl -downdb -webfrom
	 * annovar clinvar_20160302 --buildver hg19 humandb/ perl
	 * annotate_variation.pl -downdb -webfrom annovar snp138 --buildver hg19
	 * humandb/ perl annotate_variation.pl -downdb -webfrom annovar avsnp142
	 * --buildver hg19 humandb/ perl annotate_variation.pl -downdb -webfrom
	 * annovar avsnp147 --buildver hg19 humandb/
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	private static String inputStreamToString(InputStream isr) {

		try {

			BufferedReader br = new BufferedReader(new InputStreamReader(isr));
			StringBuilder sb = new StringBuilder();
			String s = null;

			while ((s = br.readLine()) != null) {
				sb.append(s + "\n");
			}

			return sb.toString();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
