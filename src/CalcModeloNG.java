import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
import opennlp.tools.doccat.DoccatFactory;
import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizer;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DocumentSample;
import opennlp.tools.doccat.DocumentSampleStream;
import opennlp.tools.doccat.FeatureGenerator;
import opennlp.tools.doccat.NGramFeatureGenerator;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.MarkableFileInputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;
 

public class CalcModeloNG {
 
	public void calcular() {
 
		try {
			// leer el archivo de entrenamiento
			InputStreamFactory dataIn = new MarkableFileInputStreamFactory(new File("train"+File.separator+"datostesis.train"));
			ObjectStream lineStream = new PlainTextByLineStream(dataIn, "UTF-8");
			ObjectStream sampleStream = new DocumentSampleStream(lineStream);
 
			// par�metros de entrenamiento
			TrainingParameters params = new TrainingParameters();
			params.put(TrainingParameters.ITERATIONS_PARAM, 10+"");
			params.put(TrainingParameters.CUTOFF_PARAM, 0+"");
			
			
			//Features Generator
			FeatureGenerator[] featureGenerators = { new NGramFeatureGenerator(1,1), new NGramFeatureGenerator(2,3) };
		    DoccatFactory factory = new DoccatFactory(featureGenerators);
 
			// armar el modelo
		    DoccatModel model = DocumentCategorizerME.train("en", sampleStream, params, factory);
			ClassicNLP.setMensaje("\nEl modelo se arm� correctamente.");
 
			// guardar el modelo 
			BufferedOutputStream modelOut = new BufferedOutputStream(new FileOutputStream("model"+File.separator+"datostesisNG.bin"));
			model.serialize(modelOut);
			ClassicNLP.setMensaje("\nArchivo con el modelo : "+"model"+File.separator+"datostesisNG.bin");
/* 
			// test the model file by subjecting it to prediction
			DocumentCategorizer doccat = new DocumentCategorizerME(model);
			String[] docWords = "Aqui va el texto".replaceAll("[^A-Za-z]", " ").split(" ");
			double[] aProbs = doccat.categorize(docWords);
 
			// print the probabilities of the categories
			System.out.println("\n---------------------------------\nCategory : Probability\n---------------------------------");
			for(int i=0;i<doccat.getNumberOfCategories();i++){
				System.out.println(doccat.getCategory(i)+" : "+aProbs[i]);
			}
			System.out.println("---------------------------------");
 
			System.out.println("\n"+doccat.getBestCategory(aProbs)+" : is the predicted category for the given sentence.");*/
		}
		catch (IOException e) {
			ClassicNLP.setMensaje("An exception in reading the training file. Please check.");
			e.printStackTrace();
		}
	}
}

