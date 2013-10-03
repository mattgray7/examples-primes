public class PrintPrimes {
	int numberOfPrimes;
	int maxRows;
	int maxColumns;
	int ordMax;
	int listOfPrimes[];
	
	/*placeHolder is not used in this class, need to keep as constructor in case other 
	programs call PrintPrimes with 5 constructors.*/
	int placeHolder;

	public PrintPrimes(int numberOfPrimes, int maxRows, int maxColumns, int placeHolder, int ordMax) {
		this.numberOfPrimes   = numberOfPrimes;
		this.maxRows  = maxRows;
		this.maxColumns  = maxColumns;
		this.placeHolder  = placeHolder;
		this.ordMax = ordMax;
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
		int tempIndex;
		int j = 1;
		int ord = 2;
		int primeSquared = 9;
		int nonPrimes[] = new int[ordMax + 1];

		for(int primesFoundSoFar = 2; primesFoundSoFar <= numberOfPrimes; primesFoundSoFar++) {
			do {
				j = j + 2;
				if (j == primeSquared) {
					ord = ord + 1;
					primeSquared = listOfPrimes[ord] * listOfPrimes[ord];
					nonPrimes[ord - 1] = j;
				}
				tempIndex = 2;
				isJPrime = true;
				
				while (tempIndex < ord && isJPrime) {
					while (nonPrimes[tempIndex] < j){
						nonPrimes[tempIndex] = nonPrimes[tempIndex] + listOfPrimes[tempIndex] + listOfPrimes[tempIndex];
					}
					
					if (nonPrimes[tempIndex] == j){
						isJPrime = false;
					}
					tempIndex = tempIndex + 1;
				}
			} while (!isJPrime);
			listOfPrimes[primesFoundSoFar] = j;
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

					 
