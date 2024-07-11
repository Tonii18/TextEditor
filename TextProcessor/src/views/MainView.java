package views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BoxView;
import javax.swing.text.ComponentView;
import javax.swing.text.Element;
import javax.swing.text.IconView;
import javax.swing.text.LabelView;
import javax.swing.text.ParagraphView;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;

import controller.Function;
import javax.swing.JLabel;

public class MainView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JSeparator separator;
    private JTextPane textArea;
    private JButton btnSave;
    private JButton btnUpload;
    private Function f;
    private JButton btnRight;
    private JButton btnCenter;
    private JButton btnLeft;
    private JButton higher;
    private JButton lower;
    private JTextField fontSize;
    private JButton bold;
    private JButton italic;
    private JButton underline;
    private JLabel title;
    private JComboBox<Font> fontsSelection;
    
    private List<String> fonts = new ArrayList<>();
    
    private static final Font FONT = new Font("Serif", Font.PLAIN, 20);
    

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
        
        ImageIcon icon = new ImageIcon("images/agregar-archivo.png");
        setIconImage(icon.getImage());
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 907, 808);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        setFontsArray(fonts); //Añadir distintas fuentes a la Lista

        setContentPane(contentPane);
        contentPane.setLayout(null);

        separator = new JSeparator();
        separator.setBounds(10, 78, 873, 10);
        contentPane.add(separator);

        JPanel toolsPanel = new JPanel();
        toolsPanel.setBackground(new Color(44, 203, 207));
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

        btnRight = new JButton("");
        btnRight.setName("right");
        btnRight.setBackground(new Color(255, 255, 255));
        btnRight.setBorderPainted(false);
        btnRight.setBorder(null);
        btnRight.setIcon(new ImageIcon("images/right.png"));
        btnRight.setBounds(798, 10, 55, 21);
        toolsPanel.add(btnRight);

        btnCenter = new JButton("");
        btnCenter.setName("center");
        btnCenter.setBorder(null);
        btnCenter.setBackground(new Color(255, 255, 255));
        btnCenter.setBorderPainted(false);
        btnCenter.setIcon(new ImageIcon("images/center.png"));
        btnCenter.setBounds(733, 10, 55, 21);
        toolsPanel.add(btnCenter);

        btnLeft = new JButton("");
        btnLeft.setName("left");
        btnLeft.setBorder(null);
        btnLeft.setBorderPainted(false);
        btnLeft.setBackground(new Color(255, 255, 255));
        btnLeft.setIcon(new ImageIcon("images/left.png"));
        btnLeft.setBounds(668, 10, 55, 21);
        toolsPanel.add(btnLeft);
        
        fontsSelection = new JComboBox(fonts.toArray(new String[0]));
        fontsSelection.setBackground(new Color(255, 255, 255));
        fontsSelection.setBounds(140, 10, 146, 21);
        toolsPanel.add(fontsSelection);
        
        fontSize = new JTextField();
        fontSize.setText("20");
        fontSize.setHorizontalAlignment(SwingConstants.CENTER);
        fontSize.setBounds(366, 10, 48, 21);
        toolsPanel.add(fontSize);
        fontSize.setColumns(10);
        
        higher = new JButton("+");
        higher.setBounds(424, 10, 42, 21);
        toolsPanel.add(higher);
        
        lower = new JButton("-");
        lower.setBounds(315, 10, 42, 21);
        toolsPanel.add(lower);
        
        bold = new JButton("B");
        bold.setName("bold");
        bold.setBorder(null);
        bold.setBackground(new Color(255, 255, 255));
        bold.setFont(new Font("Tahoma", Font.BOLD, 12));
        bold.setBounds(500, 10, 45, 21);
        toolsPanel.add(bold);
        
        italic = new JButton("I");
        italic.setName("italic");
        italic.setBorder(null);
        italic.setBackground(new Color(255, 255, 255));
        italic.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
        italic.setBounds(544, 10, 45, 21);
        toolsPanel.add(italic);
        
        underline = new JButton("U");
        underline.setName("underline");
        underline.setBorder(null);
        underline.setFont(new Font("Tahoma", Font.PLAIN, 12));
        underline.setBackground(new Color(255, 255, 255));
        underline.setBounds(588, 10, 45, 21);
        toolsPanel.add(underline);

        JScrollPane scrollPane = new JScrollPane();

        // Eliminar el scrollbar horizontal
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(20, 176, 863, 585);
        contentPane.add(scrollPane);

        textArea = new JTextPane();
        textArea.setMargin(new Insets(30, 50, 30, 50));
        textArea.setFont(FONT);

        // Asegurarse de que el JTextPane ajuste su contenido
        textArea.setEditorKit(new WrapEditorKit());

        scrollPane.setViewportView(textArea);

        // ActionsListeners
        btnSave.addActionListener(new Buttons());
        btnUpload.addActionListener(new Buttons());
        btnLeft.addActionListener(new Buttons());
        btnRight.addActionListener(new Buttons());
        btnCenter.addActionListener(new Buttons());
        fontsSelection.addActionListener(new Buttons());
        higher.addActionListener(new Buttons());
        lower.addActionListener(new Buttons());
        fontSize.addActionListener(new Buttons());
        bold.addActionListener(new Buttons());
        italic.addActionListener(new Buttons());
        underline.addActionListener(new Buttons());
        
        List<JButton> buttons = new ArrayList<>();
        buttons.add(btnLeft);
        buttons.add(btnRight);
        buttons.add(btnCenter);
        buttons.add(bold);
        buttons.add(italic);
        buttons.add(underline);
        
        title = new JLabel("Microsoft Word");
        title.setFont(new Font("Rockwell Condensed", Font.BOLD, 60));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(10, 10, 873, 58);
        contentPane.add(title);
        
        for(int i = 0; i < buttons.size(); i++) {
        	buttons.get(i).addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					JButton button = (JButton)e.getSource();
					button.setBackground(new Color(219, 219, 219));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					JButton button = (JButton)e.getSource();
					button.setBackground(Color.WHITE);
				}
        		
        	});
        }
    }

    private class Buttons implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if(source instanceof JButton) {
            	if (source == btnSave) {
                    f.saveDocument();
                } else if (source == btnUpload) {
                    f.uploadDocument();
                } else if (source == btnLeft) {
                    f.alignText(StyleConstants.ALIGN_LEFT);
                } else if (source == btnCenter) {
                    f.alignText(StyleConstants.ALIGN_CENTER);
                } else if (source == btnRight) {
                    f.alignText(StyleConstants.ALIGN_RIGHT);
                }else if(source == higher) {
                	f.fontSize(higher);
                }else if(source == lower) {
                	f.fontSize(lower);
                }else if(source == bold || source == italic || source == underline) {
                	f.fontStyle((JButton)source);
                }
            }else if(source instanceof JComboBox) {
            	if(source == fontsSelection) {
            		f.setFont((String)fontsSelection.getSelectedItem());
            	}
            }else if(source instanceof JTextField) {
            	if(source == fontSize) {
            		f.writeFontSize(fontSize.getText());
            	}
            }
        }
    }

    // GETTERS Y SETTERS
    public JTextPane getTextArea() {
        return textArea;
    }

    public void setTextArea(JTextPane textArea) {
        this.textArea = textArea;
    }
    
    public JTextField getTextField() {
    	return fontSize;
    }
    
    public void setFontSize(JTextField fontSize) {
    	this.fontSize = fontSize;
    }

    // EditorKit personalizado para ajustar el texto automáticamente
    private class WrapEditorKit extends StyledEditorKit {
        private ViewFactory defaultFactory = new WrapColumnFactory();

        @Override
        public ViewFactory getViewFactory() {
            return defaultFactory;
        }

        class WrapColumnFactory implements ViewFactory {
            @Override
            public View create(Element elem) {
                String kind = elem.getName();
                if (kind != null) {
                    if (kind.equals(AbstractDocument.ContentElementName)) {
                        return new WrapLabelView(elem);
                    } else if (kind.equals(AbstractDocument.ParagraphElementName)) {
                        return new ParagraphView(elem);
                    } else if (kind.equals(AbstractDocument.SectionElementName)) {
                        return new BoxView(elem, View.Y_AXIS);
                    } else if (kind.equals(StyleConstants.ComponentElementName)) {
                        return new ComponentView(elem);
                    } else if (kind.equals(StyleConstants.IconElementName)) {
                        return new IconView(elem);
                    }
                }
                return new LabelView(elem);
            }
        }

        class WrapLabelView extends LabelView {
            public WrapLabelView(Element elem) {
                super(elem);
            }

            @Override
            public float getMinimumSpan(int axis) {
                if (axis == View.X_AXIS) {
                    return 0;
                } else {
                    return super.getMinimumSpan(axis);
                }
            }
        }
    }
    
    public void setFontsArray(List<String> array) {
    	array.add(new String("Serif"));
    	array.add(new String("Segoe UI"));
    	array.add(new String("Arial"));
    	array.add(new String("Calibri"));
    	array.add(new String("Times New Roman"));
    	array.add(new String("Verdana"));
    	array.add(new String("Lucida Sans"));
    	array.add(new String("Comic Sans MS"));
    	array.add(new String("Helvetica"));
    	array.add(new String("Tahoma"));
    }
}

