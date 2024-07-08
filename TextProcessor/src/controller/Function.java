package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import views.MainView;

public class Function {
	
	private MainView mainview = new MainView();

	public void saveDocument() {
		JFileChooser j = new JFileChooser();
		j.setDialogTitle("Guarde el archivo");
		j.setFileFilter(new FileNameExtensionFilter("Documentos de Word", "docx"));

		int userSelection = j.showSaveDialog(null);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileSave = j.getSelectedFile();
			String filePath = fileSave.getAbsolutePath();
			if (!filePath.endsWith(".docx")) {
				filePath += ".docx";
			}
			saveTextAsDocx(filePath);
		}
	}

	private void saveTextAsDocx(String filePath) {
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

}
