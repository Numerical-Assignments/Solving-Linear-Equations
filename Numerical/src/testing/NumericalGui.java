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
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.SpinnerNumberModel;

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
		MethodsComboBox.setBounds(101, 343, 248, 20);
		MethodsComboBox.setModel(new DefaultComboBoxModel(new String[] {"Choose Method?", "Gauss Elimination.", "Gauss Elimination using pivoting.", "Gauss Jordan.", "LU Decomposition.", "Gauss Seidil.", "Jacobi Iteration"}));
		frame.getContentPane().add(MethodsComboBox);
		
		JComboBox ParametersComboBox = new JComboBox();
		ParametersComboBox.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		ParametersComboBox.setBounds(132, 396, 191, 20);
		ParametersComboBox.setModel(new DefaultComboBoxModel(new String[] {"Choose Paremeter?"}));
		frame.getContentPane().add(ParametersComboBox);
		
		JButton btnLoadFile = new JButton("Load File");
		btnLoadFile.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		btnLoadFile.setBounds(10, 11, 109, 23);
		frame.getContentPane().add(btnLoadFile);
		
		JButton btnOk = new JButton("Ok");
		btnOk.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		btnOk.setBounds(373, 343, 89, 73);
		frame.getContentPane().add(btnOk);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 87, 452, 160);
		frame.getContentPane().add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
		scrollPane.setViewportView(textArea);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(472, 87, 288, 329);
		frame.getContentPane().add(scrollPane_1);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("Monospaced", Font.PLAIN, 18));
		scrollPane_1.setViewportView(textArea_1);
		
		JLabel lblMethod = new JLabel("Method :");
		lblMethod.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		lblMethod.setForeground(Color.YELLOW);
		lblMethod.setBounds(10, 346, 81, 14);
		frame.getContentPane().add(lblMethod);
		
		JLabel lblParameters = new JLabel("Parameters :");
		lblParameters.setForeground(Color.YELLOW);
		lblParameters.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		lblParameters.setBounds(10, 399, 105, 14);
		frame.getContentPane().add(lblParameters);
		
		JLabel lblEquations = new JLabel("Equations:");
		lblEquations.setForeground(Color.YELLOW);
		lblEquations.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		lblEquations.setBounds(10, 62, 81, 14);
		frame.getContentPane().add(lblEquations);
		
		JLabel lblSteps = new JLabel("Steps:");
		lblSteps.setForeground(Color.YELLOW);
		lblSteps.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		lblSteps.setBounds(472, 62, 81, 14);
		frame.getContentPane().add(lblSteps);
		
		JLabel lblInitialGuess = new JLabel("Initial Guess :");
		lblInitialGuess.setForeground(Color.YELLOW);
		lblInitialGuess.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		lblInitialGuess.setBounds(10, 258, 204, 14);
		frame.getContentPane().add(lblInitialGuess);
		
		JLabel lblParameter = new JLabel("parameter");
		lblParameter.setForeground(Color.YELLOW);
		lblParameter.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		lblParameter.setBounds(257, 258, 204, 14);
		frame.getContentPane().add(lblParameter);
		
		JLabel lblOutput = new JLabel("Output:");
		lblOutput.setForeground(Color.YELLOW);
		lblOutput.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		lblOutput.setBounds(10, 438, 105, 14);
		frame.getContentPane().add(lblOutput);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(10, 463, 750, 73);
		frame.getContentPane().add(scrollPane_4);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setFont(new Font("Monotype Corsiva", Font.BOLD | Font.ITALIC, 28));
		scrollPane_4.setViewportView(textArea_2);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(4), new Integer(0), null, new Integer(1)));
		spinner.setBounds(268, 13, 55, 20);
		frame.getContentPane().add(spinner);
		
		JLabel lblPrecision = new JLabel("precision:");
		lblPrecision.setForeground(Color.YELLOW);
		lblPrecision.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		lblPrecision.setBounds(183, 15, 81, 14);
		frame.getContentPane().add(lblPrecision);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 283, 204, 32);
		frame.getContentPane().add(scrollPane_2);
		
		JTextArea textArea_3 = new JTextArea();
		textArea_3.setText("1,1,1");
		textArea_3.setFont(new Font("Monospaced", Font.PLAIN, 18));
		scrollPane_2.setViewportView(textArea_3);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(257, 283, 205, 32);
		frame.getContentPane().add(scrollPane_3);
	}
}
