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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.SpinnerNumberModel;

public class NumericalGui {

	private JFrame frame;
	private JScrollPane equationScrollPane;
	private JLabel lblParameter;
	private JScrollPane parameterScrollPane;
	private JLabel lblInitialGuess;
	private JScrollPane initialScrollPane;
	private JComboBox ParametersComboBox;
	private JLabel lblParameters;

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
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(0, 0, 0));
		frame.setBounds(100, 100, 900, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JComboBox MethodsComboBox = new JComboBox();
		MethodsComboBox.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		MethodsComboBox.setBounds(101, 343, 248, 20);
		MethodsComboBox.setModel(new DefaultComboBoxModel(
				new String[] { "Choose Method?", "Gauss Elimination.", "Gauss Elimination using pivoting.",
						"Gauss Jordan.", "LU Decomposition.", "Gauss Seidil.", "Jacobi Iteration" }));
		frame.getContentPane().add(MethodsComboBox);
		MethodsComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String method = MethodsComboBox.getSelectedItem().toString();
				switch (method) {
				/*case "Gauss Elimination.":
					equationScrollPane.setBounds(10, 87, 452, 250);
					ParametersComboBox.setVisible(false);
					lblParameters.setVisible(false);
					break;
				case "Gauss Elimination using pivoting.":
					equationScrollPane.setBounds(10, 87, 452, 250);
					ParametersComboBox.setVisible(false);
					lblParameters.setVisible(false);
					break;
				case "Gauss Jordan.":
					equationScrollPane.setBounds(10, 87, 452, 250);
					ParametersComboBox.setVisible(false);
					lblParameters.setVisible(false);
					break;*/
				case "LU Decomposition.":
					equationScrollPane.setBounds(10, 87, 452, 250);
					ParametersComboBox.setModel(new DefaultComboBoxModel(
							new String[] { "Choose Format?", "Downlittle Form", "Crout Form", "Cholesky Form" }));
					lblParameters.setText("Format:");
					lblParameters.setVisible(true);
					ParametersComboBox.setVisible(true);
					break;
				case "Gauss Seidil.":
					equationScrollPane.setBounds(10, 87, 452, 160);
					lblInitialGuess.setVisible(true);
					initialScrollPane.setVisible(true);
					ParametersComboBox.setModel(new DefaultComboBoxModel(
							new String[] { "Choose Paremeter?", "Number of iterations", "Absolute Relative Error" }));
					lblParameters.setText("Parameters:");
					lblParameters.setVisible(true);
					ParametersComboBox.setVisible(true);
					break;
				case "Jacobi Iteration":
					equationScrollPane.setBounds(10, 87, 452, 160);
					lblInitialGuess.setVisible(true);
					initialScrollPane.setVisible(true);
					ParametersComboBox.setModel(new DefaultComboBoxModel(
							new String[] { "Choose Paremeter?", "Number of iterations", "Absolute Relative Error" }));
					lblParameters.setText("Parameters:");
					lblParameters.setVisible(true);
					ParametersComboBox.setVisible(true);
					break;
				default:
					equationScrollPane.setBounds(10, 87, 452, 250);
					ParametersComboBox.setVisible(false);
					lblParameters.setVisible(false);
				}
				// System.out.println(MethodsComboBox.getSelectedItem().toString());

			}
		});

		ParametersComboBox = new JComboBox();
		ParametersComboBox.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		ParametersComboBox.setBounds(132, 396, 191, 20);
		ParametersComboBox.setModel(new DefaultComboBoxModel(new String[] { "Choose Paremeter?" }));
		ParametersComboBox.setVisible(false);
		frame.getContentPane().add(ParametersComboBox);
		ParametersComboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String parameters = ParametersComboBox.getSelectedItem().toString();
				switch (parameters) {
				case "Number of iterations":
					lblParameter.setText("# Iterations:");
					parameterScrollPane.setVisible(true);
					lblParameter.setVisible(true);
					break;
				case "Absolute Relative Error":
					lblParameter.setText("Relative error:");
					parameterScrollPane.setVisible(true);
					lblParameter.setVisible(true);
					break;
				default:
					parameterScrollPane.setVisible(false);
					lblParameter.setVisible(false);
				}
				
			}
		});

		JButton btnLoadFile = new JButton("Load File");
		btnLoadFile.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		btnLoadFile.setBounds(10, 11, 109, 23);
		frame.getContentPane().add(btnLoadFile);

		JButton btnOk = new JButton("Ok");
		btnOk.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		btnOk.setBounds(373, 343, 89, 73);
		frame.getContentPane().add(btnOk);

		equationScrollPane = new JScrollPane();
		equationScrollPane.setBounds(10, 87, 452, 250);// 250=>160
		frame.getContentPane().add(equationScrollPane);

		JTextArea equationTextArea = new JTextArea();
		equationTextArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
		equationScrollPane.setViewportView(equationTextArea);

		JScrollPane stepsScrollPane = new JScrollPane();
		stepsScrollPane.setBounds(472, 87, 402, 329);
		frame.getContentPane().add(stepsScrollPane);

		JTextArea stepsTextArea = new JTextArea();
		stepsTextArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
		stepsTextArea.setEditable(false);
		stepsScrollPane.setViewportView(stepsTextArea);

		JLabel lblMethod = new JLabel("Method :");
		lblMethod.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		lblMethod.setForeground(Color.YELLOW);
		lblMethod.setBounds(10, 346, 81, 14);
		frame.getContentPane().add(lblMethod);

		lblParameters = new JLabel("Parameters :");
		lblParameters.setForeground(Color.YELLOW);
		lblParameters.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		lblParameters.setBounds(10, 399, 105, 14);
		lblParameters.setVisible(false);
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

		lblInitialGuess = new JLabel("Initial Guess :");
		lblInitialGuess.setForeground(Color.YELLOW);
		lblInitialGuess.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		lblInitialGuess.setBounds(10, 258, 204, 14);
		lblInitialGuess.setVisible(false);
		frame.getContentPane().add(lblInitialGuess);

		lblParameter = new JLabel("parameter");
		lblParameter.setForeground(Color.YELLOW);
		lblParameter.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		lblParameter.setBounds(257, 258, 204, 14);
		lblParameter.setVisible(false);
		frame.getContentPane().add(lblParameter);

		JLabel lblOutput = new JLabel("Output:");
		lblOutput.setForeground(Color.YELLOW);
		lblOutput.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		lblOutput.setBounds(10, 438, 105, 14);
		frame.getContentPane().add(lblOutput);

		JScrollPane outputScrollPane = new JScrollPane();
		outputScrollPane.setBounds(10, 463, 864, 73);
		frame.getContentPane().add(outputScrollPane);

		JTextArea outputTextArea = new JTextArea();
		outputTextArea.setFont(new Font("Monotype Corsiva", Font.BOLD | Font.ITALIC, 28));
		outputTextArea.setEditable(false);
		outputScrollPane.setViewportView(outputTextArea);

		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(4), new Integer(0), null, new Integer(1)));
		spinner.setBounds(268, 13, 55, 20);
		frame.getContentPane().add(spinner);

		JLabel lblPrecision = new JLabel("precision:");
		lblPrecision.setForeground(Color.YELLOW);
		lblPrecision.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		lblPrecision.setBounds(183, 15, 81, 14);
		frame.getContentPane().add(lblPrecision);

		initialScrollPane = new JScrollPane();
		initialScrollPane.setBounds(10, 283, 204, 32);
		initialScrollPane.setVisible(false);
		frame.getContentPane().add(initialScrollPane);

		JTextArea initialTextArea = new JTextArea();
		initialTextArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
		// textArea_3.setVisible(false);
		initialScrollPane.setViewportView(initialTextArea);

		parameterScrollPane = new JScrollPane();
		parameterScrollPane.setBounds(257, 283, 205, 32);
		parameterScrollPane.setVisible(false);
		frame.getContentPane().add(parameterScrollPane);

		JTextArea parameterTextArea = new JTextArea();
		parameterTextArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
		// textArea_4.setVisible(false);
		parameterScrollPane.setViewportView(parameterTextArea);
	}
}
