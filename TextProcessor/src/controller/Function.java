package controller;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

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
		XWPFParagraph paragraph = document.createParagraph();
		paragraph.createRun().setText(mainview.getTextArea().getText());

		try (FileOutputStream out = new FileOutputStream(new File(filePath))) {
			document.write(out);
			JOptionPane.showMessageDialog(null, "Documento guardado exitosamente.");
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Error al guardar el documento: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	public void uploadDocument() {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de texto y documentos", "txt", "docx");
		fileChooser.setFileFilter(filter);
		
		int returnValue = fileChooser.showOpenDialog(mainview);
		if(returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			String extension = getFileExtension(selectedFile);
			
			try {
				JTextArea textArea = mainview.getTextArea();
				if(extension.equals("txt")) {
					loadTextFile(selectedFile, textArea);
				}else if(extension.equals("docx")) {
					loadDocxFile(selectedFile, textArea);
				}else {
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
	
	public void loadTextFile(File file, JTextArea textArea) throws IOException {
		textArea.setText(""); // Limpiar el JTextArea antes de cargar el archivo

	    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
	        String line;
	        while ((line = br.readLine()) != null) {
	            textArea.append(line + "\n");
	        }
	        // Aplicar estilo de fuente por defecto
	        textArea.setFont(new Font("Arial", Font.PLAIN, 12));
	    }
	}
	
	public void loadDocxFile(File file, JTextArea textArea) throws IOException {
		textArea.setText(""); // Limpiar el JTextArea antes de cargar el archivo

	    try (FileInputStream fis = new FileInputStream(file)) {
	        XWPFDocument document = new XWPFDocument(fis);
	        XWPFWordExtractor extractor = new XWPFWordExtractor(document);
	        textArea.setText(extractor.getText());
	        // Aplicar estilo de fuente por defecto
	        textArea.setFont(new Font("Arial", Font.PLAIN, 12));
	    }
	}

}
