package numbersNeuralNetwork;
import java.util.*;
import java.io.*;
import java.math.*;

//Basic Neural Net - 9/2019
public class numbersNeuralNetwork {
	public static void main(String [] args) throws FileNotFoundException {
			Scanner input = new Scanner(System.in);
			numbersNeuralNetworkTranining NNNT = new numbersNeuralNetworkTranining();
			System.out.println("Type '1' to continue");
			
			while(input.nextInt() != 2) {
				System.out.println("Enter 4 input numbers and 1 output number: ");
				double [] newInput = new double [5];
				for(int i=0; i < newInput.length; i++) {
					newInput[i] = input.nextDouble();
				}

				
				double output = NNNT.newInput(newInput);
				//int AIPrediction = AiPredction(output);
				System.out.println("AI Prediction: "+output + "  Actual Answer: " + newInput[4]);
			}
	}


	public static int AiPredction(double output) {
		if(output >= 0.5) {
			return 1;
		}
		else {
			return 0;
		}
	}
}