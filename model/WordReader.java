
package model;

import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;



public class WordReader {
    private static String Path;   
    public WordReader(String Path){
        this.Path=Path; 
    }
   
    //methodos gia diavasma table apo word arxeiou kai epistrofh twn pluroforiwn pluthismou
    //gia diavasma twn word arxeiwn xrisimopoitai h org.apache.poi vivliothikh
    //to (boolean kanonikosPinakas) uparxei giati kapoia liga arxeia fenetai na exoun diaforetiko pinaka kai prepei na diavasotoun me allo tropo
    public int [][] readTable(boolean kanonikosPinakas){
        String [] males = new String[11];
        String [] females = new String[11];
        boolean endloop = false;
    try {
	FileInputStream fis = new FileInputStream(Path);
	XWPFDocument xdoc=new XWPFDocument(OPCPackage.open(fis));
	Iterator<IBodyElement> bodyElementIterator = xdoc.getBodyElementsIterator();
	while(bodyElementIterator.hasNext()) {
          IBodyElement element = bodyElementIterator.next();
          if("TABLE".equalsIgnoreCase(element.getElementType().name())) {
             endloop=true;
	     List<XWPFTable> tableList =  element.getBody().getTables();
             XWPFTable table = tableList.get(0);
             getInfoFromWordTable(table,males,females,kanonikosPinakas);
	  }
          if(endloop) break;  
        }
    } catch(Exception ex) {
	ex.printStackTrace();
    } 

    return stringToInt(males,females);
    
   } 
    
    //metatroph 2 String arrays se ena disdiastato int array
    public static int[][] stringToInt(String [] males,String [] females){
    int [][] rpin = new int[2][11];
    for(int i=0;i<2;i++){
        for(int j=0;j<11;j++){
            if(i==0){  
                //System.out.println("males["+j+"]"+males[j]);
                rpin[i][j]= Integer.parseInt(males[j].replace(",",""));
            }
            else{
                //System.out.println("females["+j+"] "+females[j]);
                rpin[i][j]= Integer.parseInt(females[j].replace(",",""));
            }
       }
    }
    return rpin;
        
    }
 
    
    
    //methodos gia emfanish tou pluthismou mias perioxhs (kuriws gia elegxo an diavastike swsta)
    public void Emfanise(boolean kanonikosPinakas){
        String [] males = new String[11];
        String [] females = new String[11];
        boolean endloop = false;
    try {
	FileInputStream fis = new FileInputStream(Path);
	XWPFDocument xdoc=new XWPFDocument(OPCPackage.open(fis));
	Iterator<IBodyElement> bodyElementIterator = xdoc.getBodyElementsIterator();
	while(bodyElementIterator.hasNext()) {
          IBodyElement element = bodyElementIterator.next();
          if("TABLE".equalsIgnoreCase(element.getElementType().name())) {
             endloop=true;
	     List<XWPFTable> tableList =  element.getBody().getTables();
             XWPFTable table = tableList.get(0);
             getInfoFromWordTable(table,males,females,kanonikosPinakas);
             
             for(int i=0;i<males.length;i++){
                     System.out.println(males[i]);
             }
             System.out.println("");
             for(int i=0;i<females.length;i++){
                     System.out.println(females[i]);
             }
	  }
          if(endloop) break;  
        }
    } catch(Exception ex) {
	ex.printStackTrace();
    }
        
    }

//vgazei plhrofories apo pinaka word kai ta vazei se 2 String arrays
//einai toso asxhmh kai megalh h methodos giati oi plirofories pou xriazomai vriskontai se polu perierga kellia ston pinaka otan diavazontai apo word
//kai exei kai 2 diaforetika 'pattern' se kapoia arxeia einai se alla kellia diladi (to elegxo me boolean auto kai allazw ta i kai j...)
    public void getInfoFromWordTable(XWPFTable table,String [] males,String [] females,boolean kanonikosPinakas){
             int m=0,f=0,i1=7,i2=9,i3=10,i4=12,j1=2,j2=5,start1=4,start2=2,end1=20,end2=7;
             if(!kanonikosPinakas){
                 i1=17;
                 i2=19;
                 i3=20;
                 i4=22;
                 j1=4;
                 j2=7;
                 start1=8;
                 start2++;
                 end1=24;
                 end2++; 
             }
		for (int i = start1; i < end1; i++) {     
                     for (int j = start2; j < end2; j++) {
                         if((!(table.getRow(i).getCell(j).getText().equals(""))) ){
                             if((i==i1 || i==i2 || i==i3 || i==i4) && j==j1){
                                   //System.out.println("f is "+f+" m is "+m+ " i is "+i+" j is "+j);  //testing
                                   males[m]=table.getRow(i).getCell(j).getText();
                                   m++;
                             }
                             else if((i==i1 || i==i2 || i==i3 || i==i4) && j==j2){
                                   //System.out.println("f is "+f+" m is "+m+ " i is "+i+" j is "+j);  //testing
                                   females[f]=table.getRow(i).getCell(j).getText();
                                   f++;
                             }
                             else if(!(i==i1 || i==i2 || i==i3 || i==i4)&& j==3 ){
                                   //System.out.println("f is "+f+" m is "+m+ " i is "+i+" j is "+j);  //testing
                                   males[m]=table.getRow(i).getCell(j).getText();
                                   m++;
                                   
                             }
                            else if(!(i==i1 || i==i2 || i==i3 || i==i4) && j==6 ){
                                   //System.out.println("f is "+f+" m is "+m+ " i is "+i+" j is "+j);  //testing
                                   females[f]=table.getRow(i).getCell(j).getText();
                                   f++;
                                 
    
                            }
                            
                         }
					
                     }
                }

    }

}
