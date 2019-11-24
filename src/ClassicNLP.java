import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.*;

import java.awt.Color;
import java.awt.BorderLayout;

import javax.swing.text.*;

import java.io.IOException;
import java.util.Date;
import java.awt.event.*;







public class ClassicNLP extends JFrame implements ActionListener
{
	static final long serialVersionUID = 0;
	private static JTextPane textpanel;
	private static Style style;
	private static StyledDocument doc;
	private static JMenu examplesMenu, actionMenu, fileMenu, modelMenu, aboutMenu, testMenu;
	private static JMenuItem quitItem;
	private static JMenuItem sentDetItem, detectTokenItem, buNomItem, posTaggerItem, chunkItem, parseItem;
	private static JMenuItem loadItem, clasifMEItem, clasifNBItem, clasifNGItem,testItemME,testItemNB,testItemNG;
	private static JMenuItem archEntItem, listEtqItem, calcModItemME, calcModItemNB, calcModItemNG;
	JScrollPane scrollPane;
	long date1;
	long date2;
	public void createAndShowGUI()
	{
//Menú Archivo		
		JFrame frame = new JFrame("BiblioInfo");
		final JMenuBar menubar= new JMenuBar();
		fileMenu = new JMenu("Archivo");
		quitItem = new JMenuItem("Salir");
		quitItem.addActionListener(this);
		fileMenu.add(quitItem);
		
//Menú Ejemplos		
/*		examplesMenu = new JMenu("Ejemplos");
//		examplesMenu.addActionListener(this);
				
		sentDetItem = new JMenuItem("Detectar Oraciones");
		sentDetItem.addActionListener(this);
		examplesMenu.add(sentDetItem);
				
		detectTokenItem = new JMenuItem("Detectar Tokens");
		detectTokenItem.addActionListener(this);
		examplesMenu.add(detectTokenItem);
				
		buNomItem = new JMenuItem("Buscar Nombres");
		buNomItem.addActionListener(this);
		examplesMenu.add(buNomItem);
				
		posTaggerItem = new JMenuItem("POS Tagger");
		posTaggerItem.addActionListener(this);
		examplesMenu.add(posTaggerItem);
				
		chunkItem = new JMenuItem("Chunker");
		chunkItem.addActionListener(this);
		examplesMenu.add(chunkItem);
				
		parseItem = new JMenuItem("Parser");
		parseItem.addActionListener(this);
		examplesMenu.add(parseItem);
*/		
//Menú Analizar		
		actionMenu = new JMenu("Analizar");
		actionMenu.addActionListener(this);
		
		
		
		clasifMEItem = new JMenuItem("Analizar Doc. ME");
		clasifMEItem.addActionListener(this);
		actionMenu.add(clasifMEItem);
		
		clasifNBItem = new JMenuItem("Analizar Doc. NB");
		clasifNBItem.addActionListener(this);
		actionMenu.add(clasifNBItem);
		
		clasifNGItem = new JMenuItem("Analizar Doc. NG");
		clasifNGItem.addActionListener(this);
		actionMenu.add(clasifNGItem);
		
//Menú Prueba		
		testMenu = new JMenu("Prueba");
		testMenu.addActionListener(this);
				
		testItemME = new JMenuItem("Máxima Entropía");
		testItemME.addActionListener(this);
		testMenu.add(testItemME);
		
		testItemNB = new JMenuItem("Naive Bayes");
		testItemNB.addActionListener(this);
		testMenu.add(testItemNB);
		
		testItemNG = new JMenuItem("N-Gram Feature Ext.");
		testItemNG.addActionListener(this);
		testMenu.add(testItemNG);
				
		
//Menú Modelo	
		modelMenu = new JMenu("Modelo");
//		modelMenu.addActionListener(this);
		
		loadItem = new JMenuItem("Cargar Documento");
		loadItem.addActionListener(this);
		modelMenu.add(loadItem);
		
		archEntItem = new JMenuItem("Archivo Etiquetado");
		archEntItem.addActionListener(this);
		modelMenu.add(archEntItem);
		
		
		calcModItemME = new JMenuItem("Calcular Mod. ME");
		calcModItemME.addActionListener(this);
		modelMenu.add(calcModItemME);
		
		calcModItemNB = new JMenuItem("Calcular Mod. NB");
		calcModItemNB.addActionListener(this);
		modelMenu.add(calcModItemNB);
		
		
		calcModItemNG = new JMenuItem("Calcular Mod. NG");
		calcModItemNG.addActionListener(this);
		modelMenu.add(calcModItemNG);
//Menú Acerca		
		aboutMenu = new JMenu("Acerca");
//		aboutMenu.addActionListener(this);
		
		
		
		
		menubar.add(fileMenu);
//		menubar.add(examplesMenu);
		menubar.add(modelMenu);
		menubar.add(testMenu);
		menubar.add(actionMenu);
		
		menubar.add(aboutMenu);
		

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        textpanel = new JTextPane();
        textpanel = new JTextPane();
        scrollPane = new JScrollPane(textpanel);
        
	
        doc = textpanel.getStyledDocument();
        style = textpanel.addStyle("I'm a Style", null);
        frame.add(menubar, BorderLayout.NORTH);
		frame.add(scrollPane, BorderLayout.CENTER);
        setMensaje("Inicio del Programa");
        
        frame.setSize(900, 300);
        frame.setVisible(true);
        
	}
	
	
	
	
	
	
	public void actionPerformed(ActionEvent e) {

// 		Menu Archivo    	
    	if (e.getSource()==quitItem) {
            setComando("Salir");
            System.exit(0);
        }
    	
//		Menu Prueba    	
        if (e.getSource()==testItemME) {
        	setComando("Test Maxima Entropía");
            TestME testME = new TestME();
            date1 = System.currentTimeMillis();
            testME.analizar();
            date2 = System.currentTimeMillis();
            System.out.println("Tiempo: "+(date2-date1));
        
        }
        
        if (e.getSource()==testItemNB) {
        	setComando("Test Naive Bayes");
        	TestNB testNB = new TestNB();
        	date1 = System.currentTimeMillis();
        	testNB.analizar();      	
        	date2 = System.currentTimeMillis();
            System.out.println("Tiempo: "+(date2-date1));
        }
        
        if (e.getSource()==testItemNG) {
        	setComando("Test N-Gram Feature Extraction");
        	TestNG testNG = new TestNG();
        	date1 = System.currentTimeMillis();
        	testNG.analizar();
        	date2 = System.currentTimeMillis();
            System.out.println("Tiempo: "+(date2-date1));
        }
        
        
        
//		Menu Analizar    	
        if (e.getSource()==loadItem) {
        	setComando("Cargar Documento");
            this.selectPDFFiles("convertir");
        }
            
        if (e.getSource()==clasifMEItem) {
          	setComando("Analizar Documento ME");
          	try
          	{
          		AnalizDocME clasificarME = new AnalizDocME();
          		clasificarME.analizar();
          	}
          	catch(Exception e1){}
        }
        
        if (e.getSource()==clasifNBItem) {
          	setComando("Analizar Documento NB");
          	try
          	{
          		AnalizDocNB clasificarNB = new AnalizDocNB();
          		clasificarNB.analizar();
          	}
          	catch(Exception e1){}
        }
        if (e.getSource()==clasifNGItem) {
          	setComando("Analizar Documento NG");
          	try
          	{
          		AnalizDocNG clasificarNG = new AnalizDocNG();
          		clasificarNG.analizar();
          	}
          	catch(Exception e1){}
        }
//		Menu Aprender
        if (e.getSource()==archEntItem) {
        	setComando("Armar Archivo Etiquetado");
//        	
        	this.selectPDFFiles("muestra");
        } 
        if (e.getSource()==listEtqItem) {
        	setComando("Armar Listado Etiquetas");
//        	LisEtiq listetiq = new LisEtiq();
//        	listetiq.armarHash();
        }
        if (e.getSource()==calcModItemME) {
        	setComando("Calculando Modelo MexEnt");
        	CalcModeloME calcularModelo = new CalcModeloME();
        	date1 = System.currentTimeMillis();
        	calcularModelo.calcular();
        	date2 = System.currentTimeMillis();
            System.out.println("Tiempo: "+(date2-date1));
        	setComando("Listo");
        }
        if (e.getSource()==calcModItemNB) {
        	setComando("Calculando Modelo Naive Bayes");
        	CalcModeloNB calcularModelo = new CalcModeloNB();
        	date1 = System.currentTimeMillis();
        	calcularModelo.calcular();
        	date2 = System.currentTimeMillis();
            System.out.println("Tiempo: "+(date2-date1));
        	setComando("Listo");
        }
        
        if (e.getSource()==calcModItemNG) {
        	setComando("Calculando Modelo N-Gram Feature");
        	CalcModeloNG calcularModeloNG = new CalcModeloNG();
        	date1 = System.currentTimeMillis();
        	calcularModeloNG.calcular();
        	date2 = System.currentTimeMillis();
            System.out.println("Tiempo: "+(date2-date1));
        	setComando("Listo");
        }
    }
    public void selectPDFFiles(String accion)
    {   
    	
    	JFileChooser chooser = new JFileChooser("C:\\Users\\guillermo\\workspace\\TFIGuillermoVillamayor\\Libros\\Entrenamiento");
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF","pdf");
        chooser.setFileFilter(filter);
        chooser.setMultiSelectionEnabled(true);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) 
        {
        	File[] Files=chooser.getSelectedFiles();
        	setMensaje("Please wait...");
            for( int i=0;i<Files.length;i++)
            {
            	textpanel.setText("Convirtiendo");
           	if(accion.equalsIgnoreCase("convertir"))
            	{
            		PDFTextParser pdftextparser = new PDFTextParser();
            		pdftextparser.convertPDFToText(Files[i].toString(),"work/documento"+i+".txt");
            	}
            	else
            	{
            		ArmarMuestra armarmuestra = new ArmarMuestra();
            		armarmuestra.armarArchivoentrenamiento(Files[i].toString(),"datostesis.txt");
            	}
            }
            setMensaje("\nConversión completa");
         }
    }
    
    
    public static void setMensaje(String mensaje)
    {
    	StyleConstants.setForeground(style, Color.black);
    	try { doc.insertString(doc.getLength(), mensaje+"\n",style); }
        catch (BadLocationException e){}
    }
    
    public static void setError(String mensaje)
    {
    	StyleConstants.setForeground(style, Color.red);
    	try { doc.insertString(doc.getLength(), mensaje+"\n",style); }
        catch (BadLocationException e){}
    }
    public static void setComando(String mensaje)
    {
    	StyleConstants.setForeground(style, new Color(0x006600));
    	try { doc.insertString(doc.getLength(), mensaje+"\n",style); }
        catch (BadLocationException e){}
    }
    

    public static void main(String args[]) 
    {	
    	javax.swing.SwingUtilities.invokeLater(new Runnable() 
    	{
            public void run() 
            {
            	ClassicNLP biblioinfo = new ClassicNLP();
                biblioinfo.createAndShowGUI();
            }
        });
    }  
}
