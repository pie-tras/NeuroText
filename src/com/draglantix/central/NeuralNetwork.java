package com.draglantix.central;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class NeuralNetwork {
	
	public static Random random = new Random();
	
	private int inputCount = 2;
	private int hiddenCount = 2;
	private int outputCount = 1;
	
	private double[] hiddenLayer = new double[hiddenCount];
	private double[] outputLayer = new double[outputCount];
	
	private double[] hiddenLayerBias = new double[hiddenCount];
	private double[] outputLayerBias = new double[outputCount];
	
	private double[][] hiddenLayerWeights = new double[inputCount][hiddenCount];
	private double[][] outputLayerWeights = new double[hiddenCount][outputCount];
	
//	private double error;
//	private int c = 0;
	
	public NeuralNetwork(int epochs, double learnRate) {
		System.out.println("Creating Neural Network...");
		initModel();
		
		double[][] trainInput = new double[][] {{0, 0}, {1, 0}, {0, 1}, {1, 1}};
		double[][] trainOutput = new double[][] {{0}, {1}, {1}, {0}};
		int[] trainOrder = {0, 1, 2, 3};
		
		System.out.println("Learning...");
		
		for(int n = 0; n < epochs; n++) {
			Collections.shuffle(Arrays.asList(trainOrder));
			
			for(int x = 0; x < trainInput.length; x++) {
				int i = trainOrder[x];
				forwardPropagate(trainInput[i]);
				backPropogate(trainInput[i], trainOutput[i], learnRate);
			}
			
//			if(c == 100) {
//				System.out.println(error);
//				c = 0;
//			}
//			c++;
		}
		
		System.out.println("Learning Finished...");
		
	}
	
	public double[] compute(double[] inputs) {
		forwardPropagate(inputs);
		return outputLayer;
	}
	
	private void initModel() {
		System.out.println("Initilizing Model...");
		
		for(int x = 0; x < hiddenLayerWeights.length; x++) {
			for(int y = 0; y < outputLayerWeights[x].length; y++) {
				hiddenLayerWeights[x][y] = initWeight();
			}
		}
		
		for(int x = 0; x < outputLayerWeights.length; x++) {
			hiddenLayerBias[x] = initWeight();
			for(int y = 0; y < outputLayerWeights[x].length; y++) {
				outputLayerWeights[x][y] = initWeight();
			}
		}
		
		for (int i=0; i < outputCount; i++) {
			outputLayerBias[i] = initWeight();
		}
	}
	
	private void forwardPropagate(double[] inputs) {
		//calculate hidden layer activation
		for(int j = 0; j < hiddenCount; j++) {
			double activation = hiddenLayerBias[j];
			for(int k = 0; k < inputCount; k++) {
				activation += inputs[k]*hiddenLayerWeights[k][j];
			}
			hiddenLayer[j] = sigmoid(activation);
		}
		//calculate output layer activation
		for(int j = 0; j < outputCount; j++) {
			double activation = outputLayerBias[j];
			for(int k = 0; k < hiddenCount; k++) {
				activation += hiddenLayer[k]*outputLayerWeights[k][j];
			}
			outputLayer[j] = sigmoid(activation);
		}
	}
	
	private void backPropogate(double[] inputs, double[] outputs, double learnRate) {
		double[] deltaOutput = new double[outputCount];
		//change in output weights
		for(int j = 0; j < outputCount; j++) {
			double dError = (outputs[j]-outputLayer[j]);
			deltaOutput[j] = dError * dSigmoid(outputLayer[j]);
			
			//error = dError;
		}
		
		double[] deltaHidden = new double[hiddenCount];
		//change in hidden weights
		for(int j = 0; j < hiddenCount; j++) {
			double dError = 0;
			for(int k = 0; k < outputCount; k++) {
				dError += deltaOutput[k]*outputLayerWeights[j][k];
			}
			deltaHidden[j] = dError * dSigmoid(hiddenLayer[j]);
		}
		
		//Apply change in weights
		for(int j = 0; j < outputCount; j++) {
			outputLayerBias[j] += deltaOutput[j]*learnRate;
			for(int k = 0; k < hiddenCount; k++) {
				outputLayerWeights[k][j] += hiddenLayer[k]*deltaOutput[j]*learnRate;
			}
		}
		
		for(int j = 0; j < hiddenCount; j++) {
			hiddenLayerBias[j] += deltaHidden[j]*learnRate;
			for(int k = 0; k < inputCount; k++) {
				hiddenLayerWeights[k][j] += inputs[k]*deltaHidden[j]*learnRate;
			}
		}
		
	}
	
	private double sigmoid(double x) {
		return 1 / (1 + Math.exp(-x));
	}
	
	private double dSigmoid(double x) {
		return x * (1 - x);
	}
	
	private double initWeight() {
		return random.nextDouble();
	}
	
}
