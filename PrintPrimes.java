public class PrintPrimes {
	int numberOfPrimes;
	int maxRows;
	int maxColumns;
	int WW;
	int ORDMAX;
	int listOfPrimes[];

	public PrintPrimes(int numberOfPrimes, int maxRows, int maxColumns, int WW, int ORDMAX) {
		this.numberOfPrimes   = numberOfPrimes;
		this.maxRows  = maxRows;
		this.maxColumns  = maxColumns;
		this.WW  = WW;
		this.ORDMAX = ORDMAX;
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

	private void calculateOddPrimes() {
		boolean isJPrime;
		int N;
		int nonPrimes[] = new int[ORDMAX + 1];

		int J = 1;
		int ORD = 2;
		int primeSquared = 9;

		for(int primesFoundSoFar = 2; primesFoundSoFar <= numberOfPrimes; primesFoundSoFar++) {
			do {
				J = J + 2;
				if (J == primeSquared) {
					ORD = ORD + 1;
					primeSquared = listOfPrimes[ORD] * listOfPrimes[ORD];
					nonPrimes[ORD - 1] = J;
				}
				N = 2;
				isJPrime = true;
				
				while (N < ORD && isJPrime) {
					while (nonPrimes[N] < J){
						nonPrimes[N] = nonPrimes[N] + listOfPrimes[N] + listOfPrimes[N];
					}
					
					if (nonPrimes[N] == J){
						isJPrime = false;
					}
					N = N + 1;
				}
			} while (!isJPrime);
			listOfPrimes[primesFoundSoFar] = J;
		}
	}

	public void printPrimes() {
		int pageNumber = 1;
		int pageOffset = 1;
		
		while (pageOffset <= numberOfPrimes) {
			System.out.println("The First " + numberOfPrimes +
                               " Prime Numbers --- Page " + pageNumber);
			System.out.println("");
			
			for (int rowOffset = pageOffset; rowOffset < pageOffset + maxRows; rowOffset++){
				for (int cols = 0; cols < maxColumns; cols++){	
					if (rowOffset + cols * maxRows <= numberOfPrimes){
						System.out.format("%10d", listOfPrimes[rowOffset + cols * maxRows]);
					}
				}
				System.out.println("");
			}
			
			System.out.println("\f");
			pageNumber = pageNumber + 1;
			pageOffset = pageOffset + maxRows * maxColumns;
        }
	}
}

					 
