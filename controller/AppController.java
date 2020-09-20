
package controller;


import model.Data;
import model.Region;
import model.Result;

//methodos gia 
//1)diavasma kai epexergasia dedomenwn apo klaseis Data kai Region
//2)dimiourgia tou andoikimenou Result pou periexei to apotelesma ths epexergasias
//3)epistrofh sto view(UI.java) to apotelesma gia emfanish ston xristh

public class AppController {
    private Data regionsData;
    private Region regions [];
    private Result result;
    
    public AppController(){
    regions=new Region[39];
    regionsData = new Data();
    readRegionsData();
    }    
    
    //divasma twn dedomenwn olwn twn perioxwn apo thn vash mesw antikeimenou regionsData tupou Data 
    //kai ekxwrhsh twn dedomenwn pou diavastikan ston pinaka regions tupou Region
    public void readRegionsData(){
     for(int i=0;i<regions.length;i++){
         regions[i]=regionsData.readRegion(i+1);
     }
    }
    
    //methodos gia ypologismo twn kaluterwn perioxwn gia diafimish me vash eisodou tou xristh 
    //kai dimiourgia/taxinomish antikeimenou Result me apotelesma
    public void calculateResults(String gender,int [] ages,boolean sortByPercentage){
        //this.readRegionsData();
        double numberResults [] = new double[39];
        double percentageResults [] = new double[39];
         for(int i=0;i<regions.length;i++){
             numberResults[i]=regions[i].calculateTargetedPopulation(gender,ages);
             percentageResults[i]=regions[i].calculatePercentage(numberResults[i]);
         }
         result=new Result(numberResults,percentageResults,regions);
         result.SortResult(sortByPercentage);    //taxinomish me vash to pososto tou targeted population ana total population
    }
    

    //get methodoi gia epistrofh sto view tou apotelesmatos apo to antikeimeno Result
    public double [] getNumberResults(){
        return result.getNumberResults();
    }
    
     public double [] getPercentageResults(){
        return result.getPercentageResults();
    }
    
     public String [] getRegionNames(){
         return result.getRegionNames();
     }
     
     public double [] getMalePopulations(){
         return result.getMalePopulations();
     }
     
      public double [] getFemalePopulations(){
         return result.getFemalePopulations();
     }
      
      
      public double [] getTotalPopulations(){
           return result.getTotalPopulations();
      }
      
      public void sortResult(boolean sortByPercentage){
          result.SortResult(sortByPercentage);
      }

}
