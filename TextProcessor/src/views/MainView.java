package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MainView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JTextField tfName;
	private JLabel lblName;
	private JSeparator separator;
	private JTextArea textArea;
	private JButton btnSave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 907, 808);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblName = new JLabel("Nombre del documento");
		lblName.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 20));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(133, 10, 246, 38);
		contentPane.add(lblName);
		
		tfName = new JTextField();
		tfName.setBounds(512, 16, 246, 32);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		separator = new JSeparator();
		separator.setBounds(10, 78, 873, 10);
		contentPane.add(separator);
		
		JPanel toolsPanel = new JPanel();
		toolsPanel.setBackground(new Color(192, 192, 192));
		toolsPanel.setBounds(20, 98, 863, 38);
		contentPane.add(toolsPanel);
		toolsPanel.setLayout(null);
		
		btnSave = new JButton("");
		btnSave.setIcon(new ImageIcon("images/disco-flexible.png"));
		btnSave.setBounds(10, 10, 30, 21);
		toolsPanel.add(btnSave);
		
		JScrollPane scrollPane = new JScrollPane();
		
		//Eliminar el scrollbar horizontal
		
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(20, 176, 863, 585);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true); //Habilitar ajuste de linea
		textArea.setWrapStyleWord(true); //Habilitar ajuste de linea por palabra
		textArea.setMargin(new Insets(30, 25, 15, 25));
		scrollPane.setViewportView(textArea);
	}
}
