
package model;

public class Region {
private String regionName;
private int [] males;
private int [] females;

public Region(){};

public Region(String regionName,int [] males, int[] females){
    this.regionName=regionName;
    this.males=males;
    this.females=females;
 
}
    //ypologismos tou posostou tou targeted population se mia perioxh
    public double calculatePercentage(double targetedPopulation){
      return (targetedPopulation*100)/this.getTotalPopulation();
    }
    
    //ypologismos tou targeted population se mia perioxh
    public double calculateTargetedPopulation(String gender,int [] ages){
       double targetedPopulation=0;
      if(gender.equalsIgnoreCase("M")){
           targetedPopulation=this.getSpecificPopulation(ages,males);   
      }
      else if(gender.equalsIgnoreCase("F")){
           targetedPopulation=this.getSpecificPopulation(ages,females);  
      }
      else{
           targetedPopulation=this.getSpecificPopulation(ages,males)+this.getSpecificPopulation(ages,females);
      }
      return targetedPopulation;
    }
    

    public  String getRegionName() {
       return regionName;
    }

    public int[] getMales() {
        return males;
    }

    public int[] getFemales() {
        return females;
    }
    
   //get methodoi gia epistrofh diaforwn tupwn pluthismou ths mias perioxhs 
    public double getMalePopulation(){
        double sum=0;
        for(int i=0;i<males.length;i++){
            sum=sum+males[i];
        }
        return sum;    
    }
    
    public double getFemalePopulation(){
        double sum=0;
        for(int i=0;i<females.length;i++){
            sum=sum+females[i];
        }
        return sum;    
    }
    
    public double getTotalPopulation(){
        return this.getMalePopulation()+this.getFemalePopulation();
    }
    

    public int getSpecificPopulation(int [] ages,int [] population){
        int sum=0;
        for(int i=0;i<ages.length;i++){
            sum=sum+population[ages[i]];
        }
        return sum;
    }


}

