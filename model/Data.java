
package model;
   
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Data {
     //Arxikopoish metavlitwn kai antoikimenwn pou xriazontai gia thn vash kai to programma genika
    
/* 
    topikh vash
   private static String     driverClassName = "org.apache.derby.jdbc.ClientDriver" ;
   private static String     url = "jdbc:derby://localhost:1527/Diafimish" ;
   private static String     username = "AppAdmin";
   private static String     passwd = "12345"; 
    
    // mysql se allo server (einai argos)
    static String  url = "jdbc:mysql://db4free.net:3306/diafimish?autoReconnect=true&useSSL=false" ;
    private static String     username = "ebereza";
    private static String     passwd = "12345678";
*/

    private static Connection dbConnection    = null;
    static String  driverClassName = "com.mysql.jdbc.Driver" ;
    private static PreparedStatement insertData = null;
    private static PreparedStatement selectData = null;
    private static ResultSet rsForRegion= null;
    static String  url = "jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7271608?autoReconnect=true&useSSL=false" ;
    private static String     username = "sql7271608";
    private static String     passwd = "6ERb4ndzW9";
    
    
    
 

     //pinakas me onomata olwn twn perioxwn tou londinou      
    private static String namesOfRegions [] ={"Barking and Dagenham","Barnet","Brent","Bromley","Broxbourne","Camden","City of London","City of Westminster","Croydon","Ealing",
                           "Elmbridge","Enfield","Epsom and Ewell","Greenwich","Hackney","Hammersmith and Fulham","Haringey","Harrow","Havering","Hertsmere","Hillingdon","Hounslow","Islington",
                           "Kensington and Chelsea","Kingston upon Thames","Lambeth","Lewisham","Merton","Newham","Redbridge","Richmond upon Thames","Southwark",
                           "Spelthorne","Sutton","Three Rivers","Tower Hamlets","Waltham Forest","Wandsworth","Watford"};

    
    public Data(){};
    
    //methodos gia diavasma mias perioxhs apo thn vash
    public Region readRegion(int regionNumber){
        String selectString = "Select GENDER,NUMBER from POPULATION WHERE REGION = ?";
        String gender="",age="";
        int number=0,i=0,j=0;
        int [] males=new int[11];
        int [] females=new int[11];
        try {
            Class.forName (driverClassName);
            dbConnection = DriverManager.getConnection (url, username, passwd);
            System.out.println("connection done");
            selectData=dbConnection.prepareStatement(selectString);
            selectData.setString(1,namesOfRegions[regionNumber-1]);
            System.out.println("prepaired done");
            rsForRegion=selectData.executeQuery();
            System.out.println("rs set done");
            if (rsForRegion.next() == false){
             System.out.println("Error ResultSet is empty");
             return null;
            }  
            else{
                do{ 
                   gender=rsForRegion.getString("gender");
                   number=rsForRegion.getInt("number");
                   if(gender.equals("M")){
                    males[i]=number;
                    i++;
                   }
                   else{
                    females[j]=number;
                    j++;
                   }
               }while(rsForRegion.next());
            }
           
             dbConnection.close();
              System.out.println("connection closed");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.toString());
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.toString());
        }
       return new Region(namesOfRegions[regionNumber-1],males,females);
    }

    public static String[] getNamesOfRegions() {
        return namesOfRegions;
    }
    
    
    
  
// main gia eisagwgh dedomenwn apo arxeia word sthn vash(javaDB)    
 /*
    public static void main(String args[]){
   
        try {
            Class.forName (driverClassName);
            dbConnection = DriverManager.getConnection (url, username, passwd);
            
           
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.toString());
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.toString());
        }
         
       
    
        insertMultipeRegions(namesOfRegions);
 
   
    
    }
       
     */    
    
    //methodos  gia mazikh eisagwgh dedomenwn sthn vash
    //diavazei ta dedeomena apo arxeia word mesw klashs WordReader
    //to 'periergo' if..else uparxei giati ta arxeia (1,2,5,9,27,33) prepei na diavastoun me allo tropo apo ta upoloipa
    private static void insertMultipeRegions(String [] namesOfRegions){
         for(int i=0;i<namesOfRegions.length;i++){
           String path = "C:\\Users\\public.pbl\\Desktop\\london\\"+(i+1)+".docx";
           WordReader myReader = new WordReader(path);
           System.out.println("start of file "+(i+1));
           int [][] regionData;
           if(i==1 || i==2 || i==5 || i==9 || i==11 ||i==27 || i==33){
              regionData=myReader.readTable(false);
            }
           else{
              regionData=myReader.readTable(true);
            }
           insertOneRegion(namesOfRegions[i],regionData);
           System.out.println("end of file "+(i+1));
         }
    }
    
    
    
    //methodos gia eisagwgh sthn vash dedomenwn kai gia 2 fula se mia perioxh
     private static void insertOneRegion(String regionName,int [][] regionData){
         insertOneGender(regionName,regionData,"M");
         insertOneGender(regionName,regionData,"F");
     }
    
   //methodos gia eisagwgh sthn vash grammwn me dedomena gia ena fulo se mia perioxh
    private static void insertOneGender(String regionName,int [][] regionData,String gender){
        String [] ageCategory = {"0-2","3-5","6-11","12-17","18-24","25-34","35-44","45-54","55-64","65-74","75+"};
        int i=0;
        if(gender.equals("F")){
            i=1;
        }
        for(int j=0;j<regionData[i].length;j++){
            insertOneLine(regionName,gender,ageCategory[j],regionData[i][j]);
        } 
    }
    
    //methodos gia eisagwgh sthn vash mias grammhs sthn vash
    private static void insertOneLine(String region,String gender,String age,int number){
        String insertString = "INSERT INTO POPULATION (REGION,GENDER,AGE,NUMBER) VALUES (?, ?, ?, ?)"; 
        try {
            insertData = dbConnection.prepareStatement(insertString);
            insertData.setString(1, region);
            insertData.setString(2, gender);
            insertData.setString(3, age);
            insertData.setInt(4, number);
            insertData.executeUpdate();     
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.toString());
        }
        
    }
 
}
