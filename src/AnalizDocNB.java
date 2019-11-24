import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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
 



public class AnalizDocNB {
 
	public void analizar() {
 
		try {
			//Cargar Modelo
			InputStream inputStream = null;
			inputStream = new FileInputStream("model/datostesisNB.bin");
			DoccatModel model = new DoccatModel(inputStream);
			// test the model file by subjecting it to prediction
			DocumentCategorizer doccat = new DocumentCategorizerME(model);
			 String content = new String(Files.readAllBytes(Paths.get("work//documento0.txt")));
			String[] docWords = content.replaceAll("[^A-Za-z]", " ").split(" ");
			
			double[] aProbs = doccat.categorize(docWords);
 
			// print the probabilities of the categories
			ClassicNLP.setMensaje("\n---------------------------------\nCategoría : Probabilidad\n---------------------------------");
			for(int i=0;i<doccat.getNumberOfCategories();i++){
				ClassicNLP.setMensaje(doccat.getCategory(i)+" : "+aProbs[i]);
			}
			ClassicNLP.setMensaje("---------------------------------");
 
			ClassicNLP.setMensaje("\n"+doccat.getBestCategory(aProbs)+" : es la categoría mas probable para el documento.");
		}
		catch (IOException e) {
			ClassicNLP.setMensaje("Se produjo un error al leer el archivo de entrenamiento. Por favor revisar.");
			e.printStackTrace();
		}
	}
}

