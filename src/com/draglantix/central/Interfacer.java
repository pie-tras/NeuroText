package com.draglantix.central;

public class Interfacer {

	public Interfacer(double a, double b) {
		System.out.println("NeuroText v" + Config.VERSION + " now booting...");
//		String raw = Reader.loadFileAsString("config.dat");
//		String[] tokens = raw.split("\\s+");
//		Config.MODE = Reader.parseInt(tokens[0]);
//		Config.prompt = Reader.loadFileAsString(tokens[1]);
//		Config.network = tokens[2];
		
		NeuralNetwork nn = new NeuralNetwork(1000000, 0.1);
		
		double[] input = new double[2];
		
		input[0] = a;
		input[1] = b;
		
		System.out.println(nn.compute(input)[0]);
//		
//		if(Config.MODE == 0) {
//			write();
//		}else {
//			learn();
//		}
	}
	
//	private void write() {
//		System.out.println("Loading Neural Network...");
//		String raw = Reader.loadFileAsString(Config.network + ".ntwk");
//		String[] tokens = raw.split("\\s+");
//	}
//	
//	private void learn() {
//		
//	}
	
	public static void main(String[] args) {
		new Interfacer(Double.parseDouble(args[0]), Double.parseDouble(args[1]));
	}
	
}
