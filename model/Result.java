
package model;

//klash gia apothukeush apotelesmatous tou ypologismou twn kaluterwn perioxwn gia diafimish
public class Result {
     private  double percentageResults[];
     private  double numberResults[];
     private  Region regions[];
     
     public Result(double [] numberResults,double [] percentageResults,Region [] regions){
         this.numberResults=numberResults;
         this.percentageResults=percentageResults;
         this.regions=regions;
     }

     //voithitikh methodos tou SortResult()
     private static void swapDouble(int i,int j,double [] pin){
      double temp;
      temp=pin[i];
      pin[i]=pin[j];
      pin[j]=temp;
     }
     //voithitikh methodos tou SortResult()
     private static void swapRegion(int i,int j,Region [] pin){
      Region temp;
      temp=pin[i];
      pin[i]=pin[j];
      pin[j]=temp;
     }
      
     //methodos gia taximomhsh twn pinakwn me apotelesmata me vash to pososto tou plithismou 
     //pou dhlwthike apo ton xristh sto sunoliko pluthismo mias perioxhs
      public void SortResult(boolean sortByPercentage){
	double temp,max;
        int thesh_max;
        double [] pin;
        if(sortByPercentage){
        pin=percentageResults;
        }
        else{
        pin=numberResults;
        }
	for(int i=0;i<pin.length;i++){
		max=pin[i];
		thesh_max=i;
		for(int j=i+1;j<pin.length;j++){
			if(pin[j]>max){
				max=pin[j];
				thesh_max=j;
			}
			
		}
                swapDouble(i,thesh_max,percentageResults);
		swapDouble(i,thesh_max,numberResults);
                swapRegion(i,thesh_max,regions);
	}
  }
      
    //get methodoi gia epistrofh twn apotelesmatwn sto view mesw tou controller  
    public double[] getPercentageResults() {
        return percentageResults;
    }

    public double[] getNumberResults() {
        return numberResults;
    }

    public Region[] getRegions() {
        return regions;
    }
    
    public String [] getRegionNames(){
        String [] regionNames= new String[regions.length];
        for(int i=0;i<regions.length;i++){
            regionNames[i]=regions[i].getRegionName();
        }
        return regionNames; 
    }
    
    public double [] getMalePopulations(){
        double [] malePopulations= new double[regions.length];
        for(int i=0;i<regions.length;i++){
            malePopulations[i]=regions[i].getMalePopulation();
        }
        return malePopulations; 
    }
    
     public double [] getFemalePopulations(){
        double [] femalePopulations= new double[regions.length];
        for(int i=0;i<regions.length;i++){
            femalePopulations[i]=regions[i].getFemalePopulation();
        }
        return femalePopulations; 
    }
     
     public double [] getTotalPopulations(){
        double [] totalPopulations= new double[regions.length];
        for(int i=0;i<regions.length;i++){
            totalPopulations[i]=regions[i].getMalePopulation()+regions[i].getFemalePopulation();
        }
        return totalPopulations; 
     }
        
     
     
     
     
     
}
