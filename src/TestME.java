import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.pdfbox.pdfparser.PDFParser;
import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.util.PDFTextStripper;

import java.io.File;
import java.io.FileInputStream;

import opennlp.tools.doccat.DoccatFactory;
import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizer;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DocumentSample;
import opennlp.tools.doccat.DocumentSampleStream;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.MarkableFileInputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;
 



public class TestME {
 
	public void analizar() {
 
		try {
			//Cargar Modelo
			InputStream inputStream = null;
			inputStream = new FileInputStream("model/datostesisME.bin");
			DoccatModel model = new DoccatModel(inputStream);
			// test the model file by subjecting it to prediction
			JFileChooser chooser = new JFileChooser("C:\\Users\\guillermo\\workspace\\TFIGuillermoVillamayor\\Libros\\Prueba");	        
//	        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF","pdf");
//	        chooser.setFileFilter(filter);
	        chooser.setMultiSelectionEnabled(true);
//	        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	        int returnVal = chooser.showOpenDialog(null);
	        String texto;
	        if(returnVal == JFileChooser.APPROVE_OPTION) {
	        File folder = chooser.getCurrentDirectory();
	        ClassicNLP.setMensaje(folder.toString());
	        int i=0;
	        ClassicNLP.setMensaje("\n---------------------------------\nCategoría : Probabilidad\n---------------------------------");
	        DocumentCategorizer doccat = new DocumentCategorizerME(model);
	        File[] listOfFiles = folder.listFiles();for (File file : listOfFiles) {
	            if (file.isFile()) {	            	
	            	PDFTextParser parser = new PDFTextParser();
            		texto = parser.pdftoText(listOfFiles[i].toString());           		
	            	i++;
	                ClassicNLP.setMensaje(file.getName());
	            	String[] docWords = texto.replaceAll("[^A-Za-z]", " ").split(" ");
	            	double[] aProbs = doccat.categorize(docWords);
	            	for(int j=0;j<doccat.getNumberOfCategories();j++){
	    				ClassicNLP.setMensaje(doccat.getCategory(j)+" : "+aProbs[j]);
	    				System.out.print(aProbs[j]+"#");
	    			}
	            	System.out.print("\n");
	            	ClassicNLP.setMensaje("---------------------------------");
	            	 
//	    			BiblioEntity.setMensaje("\n"+doccat.getBestCategory(aProbs)+" : es la categoría mas probable para el documento.");
	            	
	            }
	        }
	        }
	        
	        
	       
//			DocumentCategorizer doccat = new DocumentCategorizerME(model);
//			 String content = new String(Files.readAllBytes(Paths.get("work//documento0.txt")));
//			String[] docWords = texto.replaceAll("[^A-Za-z]", " ").split(" ");
			
//			double[] aProbs = doccat.categorize(docWords);
 
			// print the probabilities of the categories
//			BiblioEntity.setMensaje("\n---------------------------------\nCategoría : Probabilidad\n---------------------------------");
//			for(int i=0;i<doccat.getNumberOfCategories();i++){
//				BiblioEntity.setMensaje(doccat.getCategory(i)+" : "+aProbs[i]);
//			}
//			BiblioEntity.setMensaje("---------------------------------");
 
//			BiblioEntity.setMensaje("\n"+doccat.getBestCategory(aProbs)+" : es la categoría mas probable para el documento.");
		}
		catch (IOException e) {		
			ClassicNLP.setMensaje("Se produjo un error en un archivo. Por favor revisar.");
			e.printStackTrace();
		}
	}
}

