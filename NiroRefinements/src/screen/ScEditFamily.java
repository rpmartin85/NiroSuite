package screen;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import conn.ConnectionMySQL;
import logical.Family;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ScEditFamily extends JFrame {

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
	private ScInsertPatient scIPat;
	private Family f;
	private ScEdit scE;

	/**
	 * Create the frame.
	 */
	public ScEditFamily(int cod, Rectangle r, ScEdit scE2) {

		scE = scE2;

		scIPat = null;

		ConnectionMySQL c = StartScreen.getConn();

		f = c.getFamily(cod);

		setResizable(false);

		setTitle("Edit Family");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds((int) r.getX(), (int) r.getY(), 450, 165);
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

				String name = txtName.getText();

				int code = Integer.parseInt(txtCode.getText());

				if (name.equals("")) {
					name = "NiroFamily" + code;
				}

				Family f = new Family(name, code);

				ConnectionMySQL c = StartScreen.getConn();
				boolean created = false;

				created = c.updateFamily(f);

				f = null;

				if (created) {
					JOptionPane.showMessageDialog(null, "Family successfully updated!");
					scE.refreshFamily();

					dispose();

					if (scIPat != null) {
						scIPat.refreshFamily(code);

					}

					scE.refreshFamily();

					dispose();

				}

				else
					JOptionPane.showMessageDialog(null, "Family has not been updated!");

			}
		});
		btnSave.setBounds(28, 78, 117, 29);
		contentPane.add(btnSave);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(293, 78, 117, 29);
		contentPane.add(btnCancel);

		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();

			}
		});
		btnClear.setBounds(164, 78, 117, 29);
		contentPane.add(btnClear);

		txtCode.setText(f.getCod() + "");

		txtName.setText(f.getName());

	}

	public void setScInsertPatient(ScInsertPatient scIPat) {

		this.scIPat = scIPat;

	}

	public void clear() {
		txtName.setText(f.getName());
	}
}
