package numbersNeuralNetwork;
//Apple and Orange - 1/2019
import java.util.*;
import java.io.*;
public class LinearRegression{
    public static void main(String [] args){
        //int [][] array = new int[10][3];
    	int [][] array = {  {15,0,0}, 
                             {7,0,0}, 
                            {55,0,0},
                            {90,0,0},{110,1,0},
    	                     {165,1,1}, {170,1,1}, {190,1,1}, {100,1,1},{70,0,1},};
        double aRGB =0;
        double aTec=0;
        double oRGB =0;
        double oTec =0;

        System.out.println("prediction of apple(a) or orange(O)");
        System.out.println("1.RGB - Apple(0), Orange(165)");
        System.out.println("2.Texture - Smooth(0), Bumpy(1)");

        
        Scanner input = new Scanner(System.in);
        while(true) {
        int nRGB = input.nextInt();
        int nTec = input.nextInt();
        int aCount = 0;
        int oCount = 0;
        
                for(int i=0;i<array.length;i++){
                	// if it is an apple
                   if(array[i][2] == 0){
                	   aCount++;
                        aRGB = (aRGB + array[i][0]);
                        aTec = (aTec + array[i][1]);
                    }
                   else{
                	   oCount++;
                        oRGB = (oRGB + array[i][0]);
                        oTec = (oTec + array[i][1]);
                    }
                   
        }
                aRGB = aRGB /aCount;
                aTec = aTec/aCount;
                oRGB = oRGB/oCount;
                oTec = oTec/oCount;
                
                double diffRGB = Math.abs(aRGB - oRGB);
                double diffTec = Math.abs(aTec - oTec);
                double RGBPersent = Math.abs(diffRGB- nRGB)/diffRGB;
                double TecPersent = Math.abs(diffTec- nTec)/diffTec;
                double TotalPersent = ((RGBPersent + TecPersent)/200) * 10000;
                
                
              //it has RGB of apple
                if(Math.abs(nRGB - aRGB) < Math.abs(nRGB - oRGB) && Math.abs(nTec - aTec) < Math.abs(nTec - oTec)){
             	   aRGB = (aRGB + nRGB)/2;
                    aTec = (aTec + nTec)/2;
                    System.out.println("The AI predicts this is a Apple");
                    System.out.println("The AI is " + TotalPersent + "% confident this is a apple" );
                    
                }
                else{
             	   oRGB = (oRGB + nRGB)/2;
                    oTec = (oTec + nTec)/2;
                    System.out.println("The AI predicts this is a Orange");
                    System.out.println("The AI is " + TotalPersent + "% confident this is a orange" );
                 }
                
                
                
        if(input.next().equals("kill")) {
        	break;
        }
        
    }
}
}