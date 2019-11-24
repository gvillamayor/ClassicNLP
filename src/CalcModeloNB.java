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
import opennlp.tools.ml.AbstractTrainer;
import opennlp.tools.ml.naivebayes.NaiveBayesTrainer;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.MarkableFileInputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;
 
/**
 * oepnnlp version 1.7.2
 * Training of Document Categorizer using Maximum Entropy Model in OpenNLP
 * @author www.tutorialkart.com
 */
public class CalcModeloNB {
 
	public void calcular() {
 
		try {
			// leer el archivo de entrenamiento
			InputStreamFactory dataIn = new MarkableFileInputStreamFactory(new File("train"+File.separator+"datostesis.train"));
			ObjectStream lineStream = new PlainTextByLineStream(dataIn, "UTF-8");
			ObjectStream sampleStream = new DocumentSampleStream(lineStream);
 
			// parámetros de entrenamiento
			TrainingParameters params = new TrainingParameters();
			params.put(TrainingParameters.ITERATIONS_PARAM, 10+"");
			params.put(TrainingParameters.CUTOFF_PARAM, 0+"");
			params.put(AbstractTrainer.ALGORITHM_PARAM, NaiveBayesTrainer.NAIVE_BAYES_VALUE);
 
			// armar el modelo
			DoccatModel model = DocumentCategorizerME.train("en", sampleStream, params, new DoccatFactory());
			ClassicNLP.setMensaje("\nEl modelo se armó correctamente.");
 
			// guardar el modelo 
			BufferedOutputStream modelOut = new BufferedOutputStream(new FileOutputStream("model"+File.separator+"datostesisNB.bin"));
			model.serialize(modelOut);
			ClassicNLP.setMensaje("\nArchivo con el modelo : "+"model"+File.separator+"datostesisNB.bin");

		}
		catch (IOException e) {
			ClassicNLP.setMensaje("An exception in reading the training file. Please check.");
			e.printStackTrace();
		}
	}
}

