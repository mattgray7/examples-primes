public class PrintPrimes {
	private int numberOfPrimes; 
	private int maxRows;			//maximum rows per page
	private int maxColumns;			//maximum columns on one page
	private int orderMax;			
	private int listOfPrimes[];		//stores the prime numbers found
	
	// placeHolder is not used in this class, need to keep as constructor in case other 
	// programs call PrintPrimes with 5 constructors.
	int placeHolder;

	//initialize local variables
	public PrintPrimes(int numberOfPrimes, int maxRows, int maxColumns, int placeHolder, int orderMax) {
		this.numberOfPrimes   = numberOfPrimes;
		this.maxRows  = maxRows;
		this.maxColumns  = maxColumns;
		this.placeHolder  = placeHolder;
		this.orderMax = orderMax;
		this.listOfPrimes = new int[numberOfPrimes + 1];
	}

	public static void main(String[] args) {
		PrintPrimes printPrimes = new PrintPrimes(300, 50, 4, 10, 30);
		printPrimes.calculatePrimes();
		printPrimes.printPrimes();
	}

	public void calculatePrimes() {
		/* Two is the only even prime. All other prime numbers are odd.
		 * To simplify the code, we simply add 2 as a prime number, and
		 * delegate the task of finding all odd prime numbers to a helper
		 * function.
		 */
		listOfPrimes[1] = 2;
		calculateOddPrimes();
	}

	/*	Calculates odd primes numbers using a variation of the "Sieve of Eratosthenes" 
	 *  algorithm wherein odd multiples of primes already found are marked and stored 
	 *  in the multiplesOfPrimes array. As the variable j increases, it is compared to 
	 *  the elements in multiplesOfPrimes and if a match is found, j is not prime and
	 *  is therefore incremented and compared again to multiplesOfPrimes.
	 */
	private void calculateOddPrimes() {
		boolean isJPrime;
		int j = 1;	//odd counter
		int order = 2;
		int primeSquared = 9;
		int multiplesOfPrimes[] = new int[orderMax + 1];		//stores odd multiple of primes found so far

		for(int primesFoundSoFar = 2; primesFoundSoFar <= numberOfPrimes; primesFoundSoFar++) {
			do {
				//increment counter by 2 since odd numbers only need to be considered, evens are non-prime
				j = j + 2;		
				
				//if j equals a square, add the square to prime multiples array and update primeSquared
				if (j == primeSquared) {
					order++;
					primeSquared = listOfPrimes[order] * listOfPrimes[order];
					multiplesOfPrimes[order - 1] = j;
				}
				
				//check if j is a prime number by comparing to array of prime multiples
				isJPrime = checkJPrime(j, order, multiplesOfPrimes);
			} while (!isJPrime);	
			
			// Only stores when isJPrime is true and no prime multiple is found, meaning that j is a prime 
			// number and should be stored
			listOfPrimes[primesFoundSoFar] = j;		
		}
	}
	
	
	//returns true if J is prime, false if j equals a prime multiple
	public boolean checkJPrime(int j, int order, int[] multiplesOfPrimes){
		
		//starting values for comparison to prime multiples
		int tempIndex = 2;
		boolean isJPrime = true;
		
		//compare j to multiples of primes already found to determine if j is prime
		while (tempIndex < order && isJPrime) {
			
			/* While j is greater than the current composite number being compared, increment the 
			 * composite number by the smallest viable increment and compare again. The smallest 
			 * viable increment is found by adding 2 times the current prime number to the current 
			 * composite number.It is added twice because only odd numbers need to be considered.
			 */
			while (multiplesOfPrimes[tempIndex] < j){
				multiplesOfPrimes[tempIndex] = multiplesOfPrimes[tempIndex] + 2*listOfPrimes[tempIndex];
			}
			
			//if j is a multiple of a prime, set isJPrime to false and exit outer while loop
			if (multiplesOfPrimes[tempIndex] == j){
				isJPrime = false;
			}
			
			//increment temporary index to get next multiple of current prime
			tempIndex++;
		}
		return isJPrime;
	}
	
	
	//prints the primes found in a row-column format
	public void printPrimes() {
		int pageNumber = 1;
		int pageOffset = 1;
		
		while (pageOffset <= numberOfPrimes) {
			
			printPage(pageOffset, pageNumber);
			
			System.out.println("\f");
			pageNumber++;
			pageOffset = pageOffset + maxRows * maxColumns;
        }
	}
	
	
	//prints primes on one page, format depends on maxRows and maxColumns
	public void printPage(int pageOffset, int pageNumber){
		System.out.println("The First " + numberOfPrimes +
                " Prime Numbers --- Page " + pageNumber + "\n");
		
		for (int rowOffset = pageOffset; rowOffset < (pageOffset + maxRows); rowOffset++){
			for (int cols = 0; cols < maxColumns; cols++){	
				int currentIndex = rowOffset + cols*maxRows;
				
				if (currentIndex <= numberOfPrimes){
					System.out.format("%10d", listOfPrimes[currentIndex]);
				}
				
			}
			System.out.println("");		//line break
		}
	}
	
}

					 
