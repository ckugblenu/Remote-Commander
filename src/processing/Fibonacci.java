/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processing;

/**
 *
 * @author makafui
 */
public class Fibonacci {
    
    private long loopCount;

    public Fibonacci(String loopCount) {
        try {
            this.loopCount = Long.parseLong(loopCount);
            
        } catch (NumberFormatException nfe) {
            System.out.println("NumberFormatException: " + nfe.getMessage());
        }
    }
    
 // Method-2: Java program for Fibonacci number using Loop.
 	public String fibonacciLoop() {
 		
 		if (loopCount == 1 || loopCount == 2) {
 			return "1";
 		}
 		long fibo1 = 1, fibo2 = 1, fibonacci = 1;
 		for (long i = 3; i <= loopCount; i++) {
 			fibonacci = fibo1 + fibo2; // Fibonacci loopCount is sum of previous two Fibonacci loopCount
 			fibo1 = fibo2;
 			fibo2 = fibonacci;
  
 		}
 		return Long.toString(fibonacci);
 	}

}
