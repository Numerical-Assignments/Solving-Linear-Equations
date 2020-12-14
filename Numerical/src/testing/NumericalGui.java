package testing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;

public class NumericalGui {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NumericalGui window = new NumericalGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NumericalGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 0));
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JComboBox MethodsComboBox = new JComboBox();
		MethodsComboBox.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		MethodsComboBox.setBounds(41, 473, 264, 20);
		MethodsComboBox.setModel(new DefaultComboBoxModel(new String[] {"Choose Method?", "Gauss Elimination.", "Gauss Elimination using pivoting.", "Gauss Jordan.", "LU Decomposition.", "Gauss Seidil.", "Jacobi Iteration"}));
		frame.getContentPane().add(MethodsComboBox);
		
		JComboBox ParametersComboBox = new JComboBox();
		ParametersComboBox.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		ParametersComboBox.setBounds(372, 473, 241, 20);
		ParametersComboBox.setModel(new DefaultComboBoxModel(new String[] {"Choose Paremeter?"}));
		frame.getContentPane().add(ParametersComboBox);
		
		JButton btnLoadFile = new JButton("Load File");
		btnLoadFile.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		btnLoadFile.setBounds(10, 11, 109, 23);
		frame.getContentPane().add(btnLoadFile);
		
		JButton btnOk = new JButton("Ok");
		btnOk.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		btnOk.setBounds(652, 512, 89, 23);
		frame.getContentPane().add(btnOk);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 85, 764, 320);
		frame.getContentPane().add(scrollPane);
		
		JTextArea txtConsole = new JTextArea();
		txtConsole.setFont(new Font("Monospaced", Font.PLAIN, 15));
		txtConsole.setText("write here.......");
		scrollPane.setViewportView(txtConsole);
	}
}
