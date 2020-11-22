package calc.com;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator extends JPanel implements ActionListener {
	private static final long serialVersionUID = 7977205969711110128L;

	public static final int WIDTH = 320;
	public static final int HEIGHT = 480;

	private GridBagLayout layout;
	private JButton[] numbuttons;
	private GridBagConstraints gbc;
	private JButton[] OPbuttons;
	private JTextField field;
	private Double num1, num2, ans;
	private int OP;
	// [0] = gridX | [1] = gridY | [2] = grid_WIDTH | [3] = grid_HEIGHT
	private int[][] numConstraints = new int[][] { { 0, 5, 2, 1 }, { 0, 4, 1, 1 }, { 1, 4, 1, 1 }, { 2, 4, 1, 1 },
			{ 0, 3, 1, 1 }, { 1, 3, 1, 1 }, { 2, 3, 1, 1 }, { 0, 2, 1, 1 }, { 1, 2, 1, 1 }, { 2, 2, 1, 1 },

	};
	private int[][] OPConstraints = new int[][] { { 2, 5, 1, 1 }, { 3, 4, 1, 2 }, { 3, 3, 1, 1 }, { 3, 2, 1, 1 },
			{ 3, 1, 1, 1 }, { 2, 1, 1, 1 }, { 1, 1, 1, 1 }, { 0, 1, 1, 1 }, };

	public Calculator() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		layout = new GridBagLayout();
		layout.columnWidths = new int[] { 80, 80, 80, 80 };
		layout.rowHeights = new int[] { 80, 80, 80, 80, 80, 80 };
		setLayout(layout);
		gbc = new GridBagConstraints();

		numbuttons = new JButton[10];
		for (int i = 0; i < numbuttons.length; i++) {
			numbuttons[i] = new JButton("" + i);
			numbuttons[i].addActionListener(this);
			gbc.gridx = numConstraints[i][0];
			gbc.gridy = numConstraints[i][1];
			gbc.gridwidth = numConstraints[i][2];
			gbc.gridheight = numConstraints[i][3];
			gbc.fill = GridBagConstraints.BOTH;
			gbc.insets = new Insets(2, 2, 2, 2);
			add(numbuttons[i], gbc);
		}
		;
		OPbuttons = new JButton[8];
		OPbuttons[0] = new JButton(".");
		OPbuttons[1] = new JButton("=");
		OPbuttons[2] = new JButton("+");
		OPbuttons[3] = new JButton("-");
		OPbuttons[4] = new JButton("*");
		OPbuttons[5] = new JButton("/");
		OPbuttons[6] = new JButton("+/-");
		OPbuttons[7] = new JButton("Clear");
		for (int i = 0; i < OPbuttons.length; i++) {
			gbc.gridx = OPConstraints[i][0];
			gbc.gridy = OPConstraints[i][1];
			gbc.gridwidth = OPConstraints[i][2];
			gbc.gridheight = OPConstraints[i][3];
			OPbuttons[i].addActionListener(this);
			add(OPbuttons[i], gbc);
		}
		;
		field = new JTextField();
		field.setEditable(false);
		field.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		field.setBackground(Color.WHITE);
		field.setFont(new Font("Italic", Font.PLAIN, 50));

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 4;
		gbc.gridheight = 1;
		add(field, gbc);

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Calculator");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.add(new Calculator(), BorderLayout.CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null);

	}

	public void actionPerformed(ActionEvent e) {

		for (int i = 0; i < numbuttons.length; i++) {
			if (e.getSource() == numbuttons[i]) {
				field.setText(field.getText() + i);
			}
			;
		}
		;
		if (e.getSource() == OPbuttons[0] && !field.getText().contains(".")) {
			field.setText(field.getText() + ".");
		}
		;
		if (e.getSource() == OPbuttons[6]) {
			field.setText("" + (-1 * Integer.parseInt(field.getText())));
		}
		;
		if (e.getSource() == OPbuttons[7]) {
			field.setText("");
		}
		;
		if (e.getSource() == OPbuttons[2]) {
			num1 = Double.parseDouble(field.getText());
			OP = 1;
			field.setText("");
		}
		;
		if (e.getSource() == OPbuttons[3]) {
			num1 = Double.parseDouble(field.getText());
			OP = 2;
			field.setText("");
		}
		;

		if (e.getSource() == OPbuttons[4]) {
			num1 = Double.parseDouble(field.getText());
			OP = 3;
			field.setText("");
		}
		;
		if (e.getSource() == OPbuttons[5]) {
			num1 = Double.parseDouble(field.getText());
			OP = 4;
			field.setText("");
		}
		;
		if (e.getSource() == OPbuttons[1]) {
			num2 = Double.parseDouble(field.getText());
			if (OP == 1) {
				ans = num1 + num2;
			} else if (OP == 2) {
				ans = num1 - num2;
			} else if (OP == 3) {
				ans = num1 * num2;
			} else if (OP == 4) {
				ans = num1 / num2;
			}
			;
			OP = 0;
			field.setText("" + ans);
		}
		;
	}

}
