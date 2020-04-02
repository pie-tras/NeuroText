package com.draglantix.central;

import java.util.Arrays;

import com.draglantix.util.DataHandler;
import com.draglantix.util.Reader;

public class Interfacer {

	public Interfacer() {
		System.out.println("NeuroText v" + Config.VERSION + " now booting...");
		String raw = Reader.loadFileAsString("config.dat");
		String[] tokens = raw.split("\\s+");
		Config.MODE = Integer.parseInt(tokens[0]);
		Config.prompt = Reader.loadFileAsString(tokens[1]);
		Config.network = tokens[2];
		Config.epochs = Integer.parseInt(tokens[3]);
		Config.learnRate = Double.parseDouble(tokens[4]);
		begin();
	}
	
	private void begin() {
		NeuralNetwork nn;
		
		if(Config.MODE == 0) {
			nn = new NeuralNetwork(Config.epochs, Config.learnRate);
		}else {
			nn = new NeuralNetwork();
		}
		
		double[] result = nn.compute(DataHandler.toArray(Arrays.deepToString(Config.prompt.split("\\s+"))));
		
		System.out.println(Arrays.toString(result));
	}
	
	public static void main(String[] args) {
		new Interfacer();
	}
	
}
