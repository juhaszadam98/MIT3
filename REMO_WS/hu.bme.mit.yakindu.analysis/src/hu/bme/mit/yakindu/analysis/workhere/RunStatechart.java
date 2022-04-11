package hu.bme.mit.yakindu.analysis.workhere;

import java.io.IOException;
import java.util.Scanner;

import hu.bme.mit.yakindu.analysis.RuntimeService;
 import hu.bme.mit.yakindu.analysis.TimerService;
import hu.bme.mit.yakindu.analysis.example.ExampleStatemachine;
import hu.bme.mit.yakindu.analysis.example.IExampleStatemachine;



public class RunStatechart {
	
	public static void main(String[] args) throws IOException {
		ExampleStatemachine s = new ExampleStatemachine();
		s.setTimer(new TimerService());
		RuntimeService.getInstance().registerStatemachine(s, 200);
		s.init();
		s.enter();
		s.runCycle();
		System.out.println("Alap állás: ");
		print(s);
		
		String in = new String();
		Scanner scanner = new Scanner(System.in);
		
		while(in != "exit"){
			in = scanner.nextLine();
			switch (in) {
			case "start":
				s.raiseStart();
				break;
			case "black":
				s.raiseWhite();
				break;
			case "white":
				s.raiseBlack();
				break;
			case "exit":
				scanner.close();
				System.exit(0);
			default:
				break;
			}
			s.runCycle();
			print(s);
		}
		scanner.close();
		System.exit(0);
	}

	public static void print(IExampleStatemachine s) {
		System.out.println("W = " + s.getSCInterface().getWhiteTime());
		System.out.println("B = " + s.getSCInterface().getBlackTime());
	}
}
