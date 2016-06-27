package screen;

import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import logical.Gene;
import logical.GeneList;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ScInsertGeneList extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCode;
	private JTextField txtName;
	private JTextField txtGeneName;
	private JScrollPane rJS;
	private JScrollPane nRJS;
	private JList<String> listPatient;
	private JList<String> listPatientSelected;
	private JScrollPane jscroll;
	private JScrollPane jscrollRelated;
	private DefaultListModel<String> listModelSelected;
	private DefaultListModel<String> listModelSearch;
	private boolean first;
	
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
	public ScInsertGeneList(Rectangle r) {
		
		
		

		first = true;

		listModelSelected = new DefaultListModel<String>();
		listModelSearch = new DefaultListModel<String>();
		
		setResizable(false);
		setTitle("Create a Gene List");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds((int) r.getX(), (int) r.getY(), 545, 420);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtCode = new JTextField();
		txtCode.setEnabled(false);
		txtCode.setEditable(false);
		txtCode.setColumns(10);
		txtCode.setBounds(79, 16, 51, 26);
		contentPane.add(txtCode);
		
		JLabel label = new JLabel("Code:");
		label.setBounds(24, 21, 61, 16);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Name:");
		label_1.setBounds(142, 21, 61, 16);
		contentPane.add(label_1);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(190, 16, 124, 26);
		contentPane.add(txtName);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSave.setBounds(24, 357, 117, 29);
		contentPane.add(btnSave);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtName.setText("");
				
			}
		});
		btnClear.setBounds(209, 357, 117, 29);
		contentPane.add(btnClear);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
			}
		});
		btnCancel.setBounds(397, 357, 117, 29);
		contentPane.add(btnCancel);
		
		int codeTemp = StartScreen.getConn().getMaxCod("LIST", "cod_list");
	
		
		txtCode.setText(codeTemp+1+"");
		
		JLabel lblGene = new JLabel("Gene:");
		lblGene.setBounds(326, 20, 61, 16);
		contentPane.add(lblGene);
		
		txtGeneName = new JTextField();
		txtGeneName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				String name = txtGeneName.getText();
				
				
				if (name.length() > 0){
					
					
					
					refreshGene(name);
					
				}
				
				
				
			}
		});
		txtGeneName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
				
			}
		});
		txtGeneName.setBounds(372, 16, 142, 26);
		contentPane.add(txtGeneName);
		txtGeneName.setColumns(10);
		
		JButton btnAddGene = new JButton("+");
		btnAddGene.setBounds(230, 316, 75, 29);
		contentPane.add(btnAddGene);
		
		
		rJS = new JScrollPane();
		rJS.setBounds(30, 82, 236, 222);
		contentPane.add(rJS);

		nRJS = new JScrollPane();
		nRJS.setBounds(278, 82, 236, 222);
		contentPane.add(nRJS);

		JLabel lblSelectedPatients = new JLabel("Selected");
		lblSelectedPatients.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectedPatients.setBounds(51, 54, 187, 16);
		contentPane.add(lblSelectedPatients);

		JLabel lblNotSelected = new JLabel("Not Selected");
		lblNotSelected.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotSelected.setBounds(315, 54, 187, 16);
		contentPane.add(lblNotSelected);

		
		JButton btnRemove = new JButton(">>");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int selected[] = listPatientSelected.getSelectedIndices();

				int size = selected.length;

				int k = 0;

				for (int i = size - 1; i >= 0; i--) {

					System.out.println(selected[i]);

					listModelSelected.add(k++, listPatientSelected.getModel().getElementAt(selected[i]));

					sort(listModelSelected);

					((DefaultListModel<String>) listPatientSelected.getModel()).removeElementAt(selected[i]);

				}

		//		refreshGene();

			}
		});
		btnRemove.setBounds(107, 316, 73, 29);
		contentPane.add(btnRemove);

		JButton btnInsert = new JButton("<<");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selected[] = listPatient.getSelectedIndices();

				int size = selected.length;

				int k = 0;

				for (int i = size - 1; i >= 0; i--) {

					System.out.println(selected[i]);

					listModelSearch.add(k++, listPatient.getModel().getElementAt(selected[i]));

					sort(listModelSearch);

					((DefaultListModel<String>) listPatient.getModel()).removeElementAt(selected[i]);

				}

	//			refreshGene();
				
		
			}
		});
		btnInsert.setBounds(359, 316, 73, 29);
		contentPane.add(btnInsert);

		
		
		
		
		
		
		
		
	}
	
	public void refreshGene(String geneName) {

		nRJS.setVisible(false);
		rJS.setVisible(false);
		
	//	System.out.println(clear);
		
		Gene[] allGenes = StartScreen.getConn().getAllGenes(geneName);
		
		//listModelSelected.clear();

		listModelSearch.clear();
		
		for (int i = 0; i < allGenes.length; i++){
			
	//	System.out.println(allGenes[i].getName());
			
			String geneData = allGenes[i].getCod() + " - " + allGenes[i].getName();
			
			boolean add = true;
			
			if (listModelSearch.getSize() == 0){
				add = false;
			}
			
			for (int j = 0; i < listModelSelected.getSize(); i++){
				
				String temp = listModelSelected.getElementAt(j);
				
				if (temp.equals(geneData)){
					
					add = false;
					
				}
				
			}
			
			if (add){
				
				listModelSearch.add(i, geneData);
				add = false;
				
			}
		
			
			
			
		}
		
	

		if (first) {

			listPatient = new JList<String>(listModelSelected);
			jscroll = new JScrollPane(listPatient);
			jscroll.setBounds(nRJS.getBounds());
			contentPane.add(jscroll);
			listPatientSelected = new JList<String>(listModelSearch);
			jscrollRelated = new JScrollPane(listPatientSelected);
			jscrollRelated.setBounds(rJS.getBounds());
			contentPane.add(jscrollRelated);
			first = false;

		}

		else {

			listPatient.setModel(listModelSelected);
			listPatientSelected.setModel(listModelSearch);

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
}
