package screen;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import conn.ConnectionMySQL;
import logical.GeneList;
import logical.Patient;
import logical.Vcf;
import logical.WriteVCF;
import javax.swing.event.ChangeEvent;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class ScRefineAll extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JSlider sldCutOff;
	private JTextField txtCutOff;
	private JTextField txtFileOut;
	private JFileChooser fc;
	private static File file;
	private static final String NO_FOLDER_SELECTED = "No folder was selected";
	private Vcf v;
	private double cutOff;
	private Patient[] pa;
	private int i;
	private String line;
	private PrintWriter write;
	private String path;
	private JList<String> listPatient;
	private JList<String> listPatientSelected;
	private JScrollPane jscroll;
	private JScrollPane nRJS;
	private JScrollPane rJS;
	private JScrollPane jscrollRelated;
	private DefaultListModel<String> listModel;
	private DefaultListModel<String> listModelSelected;
	private boolean first;
	private JCheckBox chkbSelGeneList;
	private JLabel lblList;
	private JComboBox<String> cmbGeneList;
	private JButton btnInsertGeneList;
	private Rectangle rec;;
	private ScInsertGeneList scIGL;
	private boolean clear;
	private static Comparator<GeneList> ascCod;
	
	static {
		ascCod = new Comparator<GeneList>(){
			@Override
			public int compare(GeneList g1, GeneList g2){
				if (g1.getCod() == g2.getCod())
					return 0;
				return g1.getCod() < g2.getCod() ? -1 : 1;
				
			}
		};
	}

	/**
	 * Create the frame.
	 */
	public ScRefineAll(Rectangle r) {

		rec = r;

		first = true;

		clear = true;

		listModel = new DefaultListModel<String>();
		listModelSelected = new DefaultListModel<String>();

		setResizable(false);
		setTitle("Refine");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds((int) r.getX(), (int) r.getY(), 540, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCutoff = new JLabel("Cut-Off:");
		lblCutoff.setBounds(30, 25, 96, 16);
		contentPane.add(lblCutoff);

		txtCutOff = new JTextField();
		txtCutOff.setEnabled(false);
		txtCutOff.setEditable(false);
		txtCutOff.setHorizontalAlignment(SwingConstants.CENTER);
		txtCutOff.setBounds(138, 19, 88, 28);
		contentPane.add(txtCutOff);

		sldCutOff = new JSlider();
		sldCutOff.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {

				txtCutOff.setText(sldCutOff.getValue() + " %");

			}
		});
		sldCutOff.setValue(80);
		sldCutOff.setBounds(333, 25, 190, 29);
		contentPane.add(sldCutOff);

		fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		fc.setDialogType(JFileChooser.SAVE_DIALOG);
		fc.setApproveButtonText("Choose");

		JButton btnChooseFolder = new JButton("Choose Folder");
		btnChooseFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int returnVal = fc.showSaveDialog(ScRefineAll.this);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					file = fc.getSelectedFile();

					if (!file.exists()) {
						file = file.getParentFile();
					}

					String path = file.getPath();

					txtFileOut.setText(path + "/");

				}

				else {
					txtFileOut.setText(NO_FOLDER_SELECTED);
				}

			}
		});
		btnChooseFolder.setBounds(185, 111, 184, 29);
		contentPane.add(btnChooseFolder);

		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				path = txtFileOut.getText();
				
				

				if (!path.equals(NO_FOLDER_SELECTED)) {

				//	ConnectionMySQL c = StartScreen.getConn();

					
					
					
					
					pa = getSelectedPatients();

					FileWriter file;
					try {
						file = new FileWriter(path + "RefinementMetrics_NIRO.xls");
						write = new PrintWriter(file);

						line = "NameBarcode\tTotalAmount\tRefinedAmount\n";

						write.print(line);

						cutOff = (double) sldCutOff.getValue() / 100;

						for (i = 0; i < pa.length; i++) {
							
							ConnectionMySQL c = new ConnectionMySQL();
							
							
							int list = 0;
							
							if (chkbSelGeneList.isSelected()){
								
								String temp = cmbGeneList.getSelectedItem().toString();
								
								list = Integer.parseInt(temp.substring(0, temp.indexOf(" - ")));
								
							}
							

							v = c.mountVcf(pa[i], cutOff, list);

							line = v.getData().replace("\n", "") + "\t" + v.getTotalAmount() + "\t"
									+ v.getRefinedAmount() + "\n";

							write.print(line);

							// System.out.println("Imprimindo linhas do
							// vcf");

							// v.printLine();

							try {

								// System.out.println("gravando vcf");

								WriteVCF.write(v, path);

								

							} catch (IOException e1) {
								e1.printStackTrace();
							}
							
							line = null;
							v = null;
							
							c.close();

							System.gc();

						}
						
						file.close();

					} catch (IOException e2) {
						e2.printStackTrace();
					}
					
					dispose();

				}
				
				

				else {
					JOptionPane.showMessageDialog(null, "Please select the destination folder!");
				}

				

			}
		});
		btnOk.setBounds(30, 413, 117, 29);
		contentPane.add(btnOk);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sldCutOff.setValue(80);
				txtFileOut.setText(NO_FOLDER_SELECTED);

				clear = true;
				refreshPatient();
				cmbGeneList.setSelectedIndex(-1);
				chkbSelGeneList.setSelected(false);
				refreshChkGeneList();
				
				
				
				
			}
		});
		btnClear.setBounds(210, 413, 117, 29);
		contentPane.add(btnClear);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(403, 413, 117, 29);
		contentPane.add(btnCancel);

		txtFileOut = new JTextField();
		txtFileOut.setBounds(88, 71, 435, 28);
		contentPane.add(txtFileOut);
		txtFileOut.setColumns(10);
		txtFileOut.setText(NO_FOLDER_SELECTED);

		JLabel lblPath = new JLabel("Path:");
		lblPath.setBounds(30, 77, 96, 16);
		contentPane.add(lblPath);

		JSeparator separator = new JSeparator();
		separator.setBounds(6, 141, 517, 16);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(6, 329, 517, 16);
		contentPane.add(separator_1);

		JButton btnRemove = new JButton(">>");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int selected[] = listPatientSelected.getSelectedIndices();

				int size = selected.length;

				int k = 0;

				for (int i = size - 1; i >= 0; i--) {

					System.out.println(selected[i]);

					listModel.add(k++, listPatientSelected.getModel().getElementAt(selected[i]));

					sort(listModel);

					((DefaultListModel<String>) listPatientSelected.getModel()).removeElementAt(selected[i]);

				}

				refreshPatient();

			}
		});
		btnRemove.setBounds(111, 300, 73, 29);
		contentPane.add(btnRemove);

		JButton btnInsert = new JButton("<<");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selected[] = listPatient.getSelectedIndices();

				int size = selected.length;

				int k = 0;

				for (int i = size - 1; i >= 0; i--) {

					System.out.println(selected[i]);

					listModelSelected.add(k++, listPatient.getModel().getElementAt(selected[i]));

					sort(listModelSelected);

					((DefaultListModel<String>) listPatient.getModel()).removeElementAt(selected[i]);

				}

				refreshPatient();
				
		
			}
		});
		btnInsert.setBounds(355, 300, 73, 29);
		contentPane.add(btnInsert);

		rJS = new JScrollPane();
		rJS.setBounds(30, 200, 236, 100);
		contentPane.add(rJS);

		nRJS = new JScrollPane();
		nRJS.setBounds(278, 200, 236, 100);
		contentPane.add(nRJS);

		JLabel lblPatients = new JLabel("Patients");
		lblPatients.setHorizontalAlignment(SwingConstants.CENTER);
		lblPatients.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblPatients.setBounds(78, 152, 382, 26);
		contentPane.add(lblPatients);

		JLabel lblSelectedPatients = new JLabel("Selected");
		lblSelectedPatients.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectedPatients.setBounds(53, 181, 187, 16);
		contentPane.add(lblSelectedPatients);

		JLabel lblNotSelected = new JLabel("Not Selected");
		lblNotSelected.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotSelected.setBounds(317, 181, 187, 16);
		contentPane.add(lblNotSelected);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(6, 393, 517, 16);
		contentPane.add(separator_2);

		lblList = new JLabel("Gene List:");
		lblList.setEnabled(false);
		lblList.setBounds(30, 371, 82, 16);
		contentPane.add(lblList);

		chkbSelGeneList = new JCheckBox("Select a Gene List");
		chkbSelGeneList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				refreshChkGeneList();
				

			}
		});
		chkbSelGeneList.setHorizontalAlignment(SwingConstants.CENTER);
		chkbSelGeneList.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		chkbSelGeneList.setBounds(78, 335, 382, 26);
		contentPane.add(chkbSelGeneList);

		cmbGeneList = new JComboBox<String>();
		cmbGeneList.setEnabled(false);
		cmbGeneList.setBounds(124, 365, 313, 27);
		contentPane.add(cmbGeneList);
		
		refreshGeneList();
		
		cmbGeneList.setSelectedIndex(-1);

		btnInsertGeneList = new JButton("+");
		btnInsertGeneList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				scIGL = new ScInsertGeneList(rec);
				scIGL.setVisible(true);

			}
		});
		btnInsertGeneList.setEnabled(false);
		btnInsertGeneList.setBounds(459, 365, 64, 29);
		contentPane.add(btnInsertGeneList);

		refreshPatient();

	}

	public void refreshPatient() {

		nRJS.setVisible(false);
		rJS.setVisible(false);


		if (clear) {

			Patient[] allPatients = StartScreen.getConn().getAllPatient();

			listModel.clear();

			listModelSelected.clear();

			for (int i = 0; i < allPatients.length; i++) {

				listModelSelected.add(i, allPatients[i].getCod() + " - " + allPatients[i].getName());

			}

			clear = false;

		}

		if (first) {

			listPatient = new JList<String>(listModel);
			jscroll = new JScrollPane(listPatient);
			jscroll.setBounds(nRJS.getBounds());
			contentPane.add(jscroll);
			listPatientSelected = new JList<String>(listModelSelected);
			jscrollRelated = new JScrollPane(listPatientSelected);
			jscrollRelated.setBounds(rJS.getBounds());
			contentPane.add(jscrollRelated);
			first = false;

		}

		else {

			listPatient.setModel(listModel);
			listPatientSelected.setModel(listModelSelected);

		}

	}

	public void sort(DefaultListModel<String> lm) {

		GeneList[] list = new GeneList[lm.getSize()];

		for (int i = 0; i < list.length; i++) {

			String temp = lm.getElementAt(i);
			
			int cod = Integer.parseInt(temp.substring(0, temp.indexOf(" - ")));
			
			String name = temp.substring(temp.indexOf(" - ")+3);
			
			list[i] = new GeneList(cod, name);

		}

		Arrays.sort(list, ascCod);
			

		lm.clear();

		for (int i = 0; i < list.length; i++) {
			
			lm.add(i, list[i].getCod() + " - " + list[i].getName());
	
		}

	}
	
	public void refreshChkGeneList(){
		
		lblList.setEnabled(chkbSelGeneList.isSelected());
		cmbGeneList.setEnabled(chkbSelGeneList.isSelected());
		btnInsertGeneList.setEnabled(chkbSelGeneList.isSelected());
		
	}
	
	public void refreshGeneList() {

		GeneList[] allGL = StartScreen.getConn().getAllGeneList();
		
		
		
		cmbGeneList.removeAllItems();
		
		
		if (allGL != null) {

			for (int i = 0; i < allGL.length; i++) {

				cmbGeneList.addItem(allGL[i].getCod() + " - " + allGL[i].getName());

			}

		}
	}
	
	public Patient[] getSelectedPatients(){
		
		int size = listPatientSelected.getModel().getSize();
		
		Patient pat[] = new Patient[size];
		
		for (int i = 0; i < size; i++){
			
			String temp = listPatientSelected.getModel().getElementAt(i);
			
			System.out.println(temp);
			
			int codSelectedPatient = Integer.parseInt(temp.substring(0, temp.indexOf(" - ")));
			
			pat[i] = new Patient(codSelectedPatient);
			
		}
		
		return pat;
		
		
	}
	
	

}
