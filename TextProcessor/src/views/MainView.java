package views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import controller.Function;
import javax.swing.JToolBar;

public class MainView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JTextField tfName;
	private JLabel lblName;
	private JSeparator separator;
	private JTextArea textArea;
	private JButton btnSave;
	private JButton btnUpload;
	
	private Function f;

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
		f = new Function(this);
		setTitle("Procesador de texto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 907, 808);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
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
		btnSave.setBackground(new Color(255, 255, 255));
		btnSave.setBorderPainted(false);
		btnSave.setIcon(new ImageIcon("images/disco-flexible.png"));
		btnSave.setBounds(10, 10, 30, 21);
		toolsPanel.add(btnSave);
		
		btnUpload = new JButton("");
		btnUpload.setBorderPainted(false);
		btnUpload.setBackground(Color.WHITE);
		btnUpload.setIcon(new ImageIcon("images/cloud-upload-alt.png"));
		btnUpload.setBounds(48, 10, 30, 21);
		toolsPanel.add(btnUpload);
		
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
		
		
		//ActionsListeners
		
		btnSave.addActionListener(new Buttons());
		btnUpload.addActionListener(new Buttons());
	}
	
	private class Buttons implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton)e.getSource();
			if(button == btnSave) {
				f.saveDocument();
			}else if(button == btnUpload) {
				f.uploadDocument();
			}
		}
		
	}
	
	//GETTERS Y SETTERS
	
	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}
}
