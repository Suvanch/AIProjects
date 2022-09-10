package numbersNeuralNetwork;
/*

 This AI program takes 2 forms of input
 -one user fed input from "numbersNeuralNetwork" -- this is for testing the AI
 -one text document with all of the input and output data -- this is for training the AI
 The input values for this program is...
 - 4 random number between 1 and 0 -- EX: .1, .25, .3434, .39439
 The output is the average of all 4 numbers -- EX: 0.2719

 With enough training the weights are supposed to be 0.25 each.
 */

import java.util.*;
import java.io.*;

public class numbersNeuralNetworkTranining {
	
	//Calculates the AI answer depending on a new user input
	public double newInput(double newInt []) throws FileNotFoundException {
		double [] weights = trainWeights();
		double output = output(newInt,weights);
		return output;
	}

	public static double [] trainWeights() throws FileNotFoundException {	
		
		//The training information
		
		double [][] inputValues = loadInput();
			double [] answers = loadOutput();
		//Assigned random doubles between the values [-1,1] for each synaptic weights
		double [] weights = new double[5];
		for(int i = 0; i < weights.length;i++) {
			weights[i] = randomDouble();
		}

		//printing the weight before training 
		//System.out.println(weights[0] +" "+ weights[1]+" "+weights[2]+" "+weights[3]+" "+weights[4]);

		//trains with the data set 10000 times
		for(int b = 0; b < 1000;b++) {
			//Seperating each column in the 2d array of inputs
			for(int a = 0; a < inputValues.length;a++) {
				double output = output(inputValues[a],weights);//calculates output
					//Seperating each integer from the array of inputs
					for(int q = 0;q<weights.length-1;q++) {
						//currentInputValue == inputValues[a][q];
						//a == the row number in the 2d array for inputs
						//q == the integer number in the array of inputs
						//Adjusts the weights depending on the erroe weight
						weights[q] += errorWeight(output,answers,a,inputValues[a][q]);
				}
			}
		}

		//printing weights after training
		//System.out.println(weights[0] +" "+ weights[1]+" "+weights[2]+" "+weights[3]+" "+weights[4]);
		
		return weights;

		
	}

	//Produces the estimated output created by the AI
	public static double output (double [] inputValues,double [] weights) {
		//Finds the total sum for each input * weight
		double summation = 0;
			for(int i=0; i< inputValues.length;i++) {
				summation += inputValues[i] * weights[i];
			}
			//puts the total sum of all 3 inputs into a sigmoid function to get a individual value between [-1,1]
			double output = sigmoid(summation);
			return output;
	}

	//Converts any number into a new unique decimal between [-1,1] 
	public static double sigmoid(double summation) {
		//Equation for the sigmoid function 
		double sigmoid = 1/(1+Math.pow(Math.E, (-summation)));
		return sigmoid;
	}

	//Creates a random double between the values -1 and 1 inclusive
	public static double randomDouble() {
		return Math.random();
	}
	
	//Calculates how much to change each weight by
	public static double errorWeight(double output, double [] answers, int columnLength,double input) {
		double sigmoidCurveGradient = output*(1-output);
		double error = answers[columnLength] - output;
		double change = sigmoidCurveGradient * error* input;
		return change;
	}

	public static double[][] loadInput() throws FileNotFoundException{

		Scanner input= new Scanner(new File("numbersNeuralNetwork/trainingData.txt"));
		double [][] inputValues = new double [countSize()][4];
			for(int x=0;x<inputValues.length;x++){
				for(int y =0;y<inputValues[0].length;y++){
					if(input.hasNext()){
						inputValues[x][y] = input.nextDouble();
					}
				}
				input.nextDouble();

			}
		//printArray(inputValues);
		
		return inputValues;
	}

	public static int countSize() throws FileNotFoundException{

		Scanner input= new Scanner(new File("numbersNeuralNetwork/trainingData.txt"));
		int counter = 0;
		while(input.hasNextLine()){
			counter++;
			input.nextLine();
		}
		//System.out.println(counter);
		return counter;
	}

	public static double[] loadOutput() throws FileNotFoundException{
		Scanner input= new Scanner(new File("numbersNeuralNetwork/trainingData.txt"));
		double [] outputValues = new double [countSize()];
				for(int y =0;y<outputValues.length;y++){
					input.nextDouble();
					input.nextDouble();
					input.nextDouble();
					input.nextDouble();
					if(input.hasNext()){
						outputValues[y] = input.nextDouble();
					}
				}

			
			
		//printArray(outputValues);
		
		return outputValues;

	}

	public static void printArray(double [][] board){

		System.out.println("----------------------------------------------------------------------");
		System.out.println("Printing Array");
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				
				System.out.print(board[row][col] + " ");			
			}
				System.out.println("");

		 }
		 System.out.println("----------------------------------------------------------------------");

	}

}
	