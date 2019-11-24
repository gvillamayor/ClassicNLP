import org.pdfbox.cos.COSDocument;
import org.pdfbox.pdfparser.PDFParser;
import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.pdmodel.PDDocumentInformation;
import org.pdfbox.util.PDFTextStripper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.String;




public class ArmarMuestra 
{
    
    PDFParser parser;
    String parsedText;
    PDFTextStripper pdfStripper;
    PDDocument pdDoc;
    COSDocument cosDoc;
    PDDocumentInformation pdDocInfo;
    
    
    
    String pdftoText(String fileName) 
    {
    	try 
        {
    		ClassicNLP.setMensaje("\nAnalizando texto desde el archivo " + fileName + "....");
    		File f = new File(fileName);
    		if (!f.isFile()) 
    		{
    			ClassicNLP.setError("No existe el archivo " + fileName);
    			return null;
    		}
            parser = new PDFParser(new FileInputStream(f));
        } 
        catch (Exception e1) 
        {
        	ClassicNLP.setError("No se puede ejecutar el analizador de PDF.");
            return null;
        }
        
        try 
        {
            parser.parse();
            cosDoc = parser.getDocument();
            pdfStripper = new PDFTextStripper();
            pdDoc = new PDDocument(cosDoc);
            cosDoc.dereferenceObjectStreams();
        }
        catch (Exception e3){ClassicNLP.setError(e3.toString());}
        try 
        {
        	ClassicNLP.setMensaje("Paginas: "+pdDoc.getNumberOfPages());
           
            ClassicNLP.setMensaje("Titulo: "+pdDoc.getDocumentInformation().getTitle());
            ClassicNLP.setMensaje("Autor: "+pdDoc.getDocumentInformation().getAuthor());
            ClassicNLP.setMensaje("Encriptación: "+pdDoc.isEncrypted());
            for (int i=pdDoc.getNumberOfPages()-1;i>4;i--)
            {
            	pdDoc.removePage(i);
            }
            ClassicNLP.setMensaje("Extracción de las "+pdDoc.getNumberOfPages()+" últimas páginas");
        }
        catch(Exception e4)
        {
        	ClassicNLP.setError("Error el documento no contiene información "+e4.toString());
        }
        try
        {
            parsedText = pdfStripper.getText(pdDoc);
        }
        catch(Exception e5){ClassicNLP.setError("Error en el pdfStripper "+e5.toString()+" "+parsedText);}
        try
        {
            pdDoc.close();
            cosDoc.close();   
        } 
        catch (Exception e) 
        {
        	ClassicNLP.setError("Ocurrio un excepcion al analizar el documento PDF.");
            ClassicNLP.setMensaje(e.toString());
            //e.printStackTrace();
            try 
            {
            	if (cosDoc != null) cosDoc.close();
                if (pdDoc != null) pdDoc.close();
            }
            catch (Exception e1) 
            {
            	ClassicNLP.setError(e1.toString());
               e1.printStackTrace();
            }
            return null;
        }      
        ClassicNLP.setMensaje("Done.");
        return parsedText;
    }

    
    
    
    
    
    // Write the parsed text from PDF to a file
    void writeTexttoFile(String pdfText, String fileName) {
    	
    	ClassicNLP.setMensaje("\nWriting PDF text to output text file " + fileName + "....");
    	try {
    		PrintWriter pw = new PrintWriter(new FileWriter(fileName,true));
    		pw.print(pdfText);
    		pw.flush();
    		pw.close();    	
    		ClassicNLP.setMensaje("Done.");
    	} catch (Exception e) {
    		ClassicNLP.setMensaje("An exception occured in writing the pdf text to file.");
    		e.printStackTrace();
    		ClassicNLP.setError(e.toString());
    	}
    	
    }
    
    
    
      public void armarArchivoentrenamiento(String src,String desc)
      {
    	  String etiqueta = "Analitica";
//    	  CreateDialogFromOptionPane dialogo = new CreateDialogFromOptionPane();
//    	  etiqueta = dialogo.getEtiqueta();
    	  try
      	{
    	  PDFTextParser pdfTextParserObj = new PDFTextParser();
  		//create file writer
//  		FileWriter fw=new FileWriter(desc);
//  		String pdfToText = pdfTextParserObj.pdftoText(src);
  		String pdfToText = pdftoText(src);
  		System.out.println(src);
  		pdfToText=pdfToText.replaceAll("\\r", " ");
  		pdfToText="\n"+etiqueta+" "+pdfToText.replaceAll("\\n", "");
  		if (pdfToText == null) 
  		{
  			ClassicNLP.setError("PDF to Text Conversion failed.");
  	    }
  	    else 
  	    {
//  	    	Escribe en la consola el documento parseado
//  	        System.out.println("\nThe text parsed from the PDF Document....\n" + pdfToText);
//  	    	pdfToText.replaceAll("\\p{Punct}","");
//  	    	String cadenaTageada = tagger.tagString(pdfToText);
//  	        pdfTextParserObj.writeTexttoFile(cadenaTageada, desc);
  	    	writeTexttoFile(pdfToText, desc);
  	    }
  	}
  	catch(Exception e){ClassicNLP.setMensaje("Error 1");e.printStackTrace();}

    	  
      }

/*    public void convertPDFToText(String src,String desc)
    {
    	try
    	{
//    		PDFTextParser pdfTextParserObj = new PDFTextParser();
    		//create file writer
//    		FileWriter fw=new FileWriter(desc);
//    		String pdfToText = pdfTextParserObj.pdftoText(src);
    		String pdfToText = pdftoText(src);
    		if (pdfToText == null) 
    		{
    			BiblioEntity.setError("PDF to Text Conversion failed.");
    	    }
    	    else 
    	    {
//    	    	Escribe en la consola el documento parseado
//    	        System.out.println("\nThe text parsed from the PDF Document....\n" + pdfToText);
//    	    	pdfToText.replaceAll("\\p{Punct}","");
//    	    	String cadenaTageada = tagger.tagString(pdfToText);
//    	        pdfTextParserObj.writeTexttoFile(cadenaTageada, desc);
    	    	writeTexttoFile(pdfToText, desc);
    	    }
    	}
    	catch(Exception e){BiblioEntity.setMensaje("Error 1");e.printStackTrace();}

    }*/
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   
}