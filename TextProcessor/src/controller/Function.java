package controller;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import views.MainView;

public class Function {

	private MainView mainview;
	
	public Function(MainView mainview) {
        this.mainview = mainview;
    }

	public void saveDocument() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Guardar documento");
		fileChooser.setFileFilter(new FileNameExtensionFilter("Documentos de Word", "docx"));

		int userSelection = fileChooser.showSaveDialog(null);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
			String filePath = fileToSave.getAbsolutePath();
			if (!filePath.endsWith(".docx")) {
				filePath += ".docx";
			}
			saveTextAsDocx(filePath);
		}
	}

	public void saveTextAsDocx(String filePath) {
	    XWPFDocument document = new XWPFDocument();
	    StyledDocument styledDoc = mainview.getTextArea().getStyledDocument();
	    XWPFParagraph paragraph = document.createParagraph();
	    XWPFRun run = paragraph.createRun();

	    try {
	        for (int i = 0; i < styledDoc.getLength(); i++) {
	            String text = styledDoc.getText(i, 1);
	            AttributeSet attributes = styledDoc.getCharacterElement(i).getAttributes();

	            // Aplicar los estilos
	            if (StyleConstants.isBold(attributes)) {
	                run.setBold(true);
	            }
	            if (StyleConstants.isItalic(attributes)) {
	                run.setItalic(true);
	            }
	            if (StyleConstants.isUnderline(attributes)) {
	                run.setUnderline(UnderlinePatterns.SINGLE);
	            }

	            String fontFamily = StyleConstants.getFontFamily(attributes);
	            int fontSize = StyleConstants.getFontSize(attributes);

	            run.setFontFamily(fontFamily);
	            run.setFontSize(fontSize);
	            run.setText(text);

	            // Resetear los estilos para el siguiente caracter
	            run = paragraph.createRun();
	        }

	        try (FileOutputStream out = new FileOutputStream(new File(filePath))) {
	            document.write(out);
	            JOptionPane.showMessageDialog(null, "Documento guardado exitosamente.");
	        }
	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(null, "Error al guardar el documento: " + ex.getMessage());
	        ex.printStackTrace();
	    }
	}

	
	public void uploadDocument() {
	    JFileChooser fileChooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de texto y documentos", "txt", "docx");
	    fileChooser.setFileFilter(filter);

	    int returnValue = fileChooser.showOpenDialog(mainview);
	    if (returnValue == JFileChooser.APPROVE_OPTION) {
	        File selectedFile = fileChooser.getSelectedFile();
	        String extension = getFileExtension(selectedFile);

	        try {
	            JTextPane textArea = mainview.getTextArea();
	            if (extension.equals("txt")) {
	                loadTextFile(selectedFile, textArea);
	            } else if (extension.equals("docx")) {
	                loadDocxFile(selectedFile, textArea);
	            } else {
	                JOptionPane.showMessageDialog(null, "Formato de archivo no compatible");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Error al leer el archivo");
	        }
	    }
	}

	
	public String getFileExtension(File file) {
		String extension = "";
		
		String fileName = file.getName();
		int index = fileName.lastIndexOf('.');
		
		if(index == -1) {
			extension = "";
		}else {
			extension += fileName.substring(index + 1);
		}
		return extension;
	}
	
	public void loadTextFile(File file, JTextPane textArea) throws IOException {
		textArea.setText(""); // Limpiar el JTextArea antes de cargar el archivo

		 BufferedReader br = new BufferedReader(new FileReader(file));
	     textArea.read(br, null);
	     textArea.setFont(new Font("Arial", Font.PLAIN, 12));
	}
	
	public void loadDocxFile(File file, JTextPane textArea) throws IOException {
	    textArea.setText(""); // Limpiar el JTextArea antes de cargar el archivo

	    try (FileInputStream fis = new FileInputStream(file)) {
	        XWPFDocument document = new XWPFDocument(fis);
	        StyledDocument styledDoc = textArea.getStyledDocument();

	        for (XWPFParagraph paragraph : document.getParagraphs()) {
	            for (XWPFRun run : paragraph.getRuns()) {
	                String text = run.getText(0);
	                if (text != null) {
	                    SimpleAttributeSet attributes = new SimpleAttributeSet();

	                    // Aplicar estilos
	                    if (run.isBold()) {
	                        StyleConstants.setBold(attributes, true);
	                    }
	                    if (run.isItalic()) {
	                        StyleConstants.setItalic(attributes, true);
	                    }
	                    if (run.getUnderline() != UnderlinePatterns.NONE) {
	                        StyleConstants.setUnderline(attributes, true);
	                    }

	                    StyleConstants.setFontFamily(attributes, run.getFontFamily());
	                    StyleConstants.setFontSize(attributes, run.getFontSize());

	                    try {
	                        styledDoc.insertString(styledDoc.getLength(), text, attributes);
	                    } catch (BadLocationException e) {
	                        e.printStackTrace();
	                    }
	                }
	            }
	        }

	        // Aplicar estilo de fuente por defecto
	        textArea.setFont(new Font("Arial", Font.PLAIN, 12));
	    }
	}

	
	//Metodo para las alineaciones
	
	public void alignText(int alignment) {
		JTextPane textpane = mainview.getTextArea();
		StyledDocument doc = textpane.getStyledDocument();
		SimpleAttributeSet attributset = new SimpleAttributeSet();
		StyleConstants.setAlignment(attributset, alignment);
		doc.setParagraphAttributes(textpane.getSelectionStart(), textpane.getSelectionEnd() - textpane.getSelectionStart(), attributset, false);
	}
	
	//Metodo para establecer fuentes
	
	public void setFont(String font) {
		JTextPane textPane = mainview.getTextArea();
		StyledDocument doc = textPane.getStyledDocument();
		int start = textPane.getSelectionStart();
		int end = textPane.getSelectionEnd();
		
		if(start == end) {
			SimpleAttributeSet attributeSet = new SimpleAttributeSet();
			StyleConstants.setFontFamily(attributeSet, font);
			textPane.setCharacterAttributes(attributeSet, false);
		}else {
			SimpleAttributeSet attributeSet = new SimpleAttributeSet();
			StyleConstants.setFontFamily(attributeSet, font);
			doc.setCharacterAttributes(start, end - start, attributeSet, false);
		}
	}
	
	//Tamaño de fuente
	
	public void fontSize(JButton button) {
		JTextField fontSize = mainview.getTextField();
		JTextPane textPane = mainview.getTextArea();
		int value = Integer.valueOf(fontSize.getText());
		
		if(button.getText().equals("+")) {
			value++;
			fontSize.setText(String.valueOf(value));
			
			StyledDocument doc = textPane.getStyledDocument();
			int start = textPane.getSelectionStart();
			int end = textPane.getSelectionEnd();
			
			if(start == end) {
				SimpleAttributeSet attributeSet = new SimpleAttributeSet();
				StyleConstants.setFontSize(attributeSet, value);
				textPane.setCharacterAttributes(attributeSet, false);
			}else {
				SimpleAttributeSet attributeSet = new SimpleAttributeSet();
				StyleConstants.setFontSize(attributeSet, value);
				doc.setCharacterAttributes(start, end - start, attributeSet, false);
			}
			
		}
		if(button.getText().equals("-")) {
			if(value > 0) {
				value--;
				fontSize.setText(String.valueOf(value));
				
				StyledDocument doc = textPane.getStyledDocument();
				int start = textPane.getSelectionStart();
				int end = textPane.getSelectionEnd();
				
				if(start == end) {
					SimpleAttributeSet attributeSet = new SimpleAttributeSet();
					StyleConstants.setFontSize(attributeSet, value);
					textPane.setCharacterAttributes(attributeSet, false);
				}else {
					SimpleAttributeSet attributeSet = new SimpleAttributeSet();
					StyleConstants.setFontSize(attributeSet, value);
					doc.setCharacterAttributes(start, end - start, attributeSet, false);
				}
				
			}
		}
	}
	
	public void writeFontSize(String size) {
		JTextPane textPane = mainview.getTextArea();
		int value = Integer.valueOf(size);
		
		StyledDocument doc = textPane.getStyledDocument();
		int start = textPane.getSelectionStart();
		int end = textPane.getSelectionEnd();
		
		if(start == end) {
			SimpleAttributeSet attributeSet = new SimpleAttributeSet();
			StyleConstants.setFontSize(attributeSet, value);
			textPane.setCharacterAttributes(attributeSet, false);
		}else {
			SimpleAttributeSet attributeSet = new SimpleAttributeSet();
			StyleConstants.setFontSize(attributeSet, value);
			doc.setCharacterAttributes(start, end - start, attributeSet, false);
		}
		
	}
	
	//Estilo de fuente
	
	public void fontStyle(JButton button) {
		JTextPane textPane = mainview.getTextArea();
	    StyledDocument doc = textPane.getStyledDocument();
	    int start = textPane.getSelectionStart();
	    int end = textPane.getSelectionEnd();

	    SimpleAttributeSet attributeSet = new SimpleAttributeSet();
	    boolean isSelected = button.getForeground().equals(Color.BLUE);

	    if (button.getName().equals("bold")) {
	        StyleConstants.setBold(attributeSet, !isSelected);
	    } else if (button.getName().equals("italic")) {
	        StyleConstants.setItalic(attributeSet, !isSelected);
	    } else if (button.getName().equals("underline")) {
	        StyleConstants.setUnderline(attributeSet, !isSelected);
	    }

	    if (start == end) {
	        textPane.setCharacterAttributes(attributeSet, false);
	    } else {
	        doc.setCharacterAttributes(start, end - start, attributeSet, false);
	    }

	    if (isSelected) {
	        button.setForeground(Color.BLACK);
	    } else {
	        button.setForeground(Color.BLUE);
	    }
	}

}
