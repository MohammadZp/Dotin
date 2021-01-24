import java.util.Scanner;

public class Stock {
	public static int[] getArrayInputfromUser() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter number of days:");
		int n = scanner.nextInt();
		while (n <= 1 || n >= 1000) {
			System.out.println("Invalid  range!");
			System.out.println("Enter number of days:");
			n = scanner.nextInt();
		}
		int[] array = new int[n];
		System.out.println("Enter stock prices:");
		for (int i = 0; i < n; i++) {
			System.out.print("a[" + i + "]=");
			array[i] = scanner.nextInt();
		}
		System.out.println("completed!");
		return array;
	}

	public static int MaxOfProfit(int array[]) {
		int Max = -100;
		int pivot = 0;
		for (int i = 0; i < array.length; i++) {
			pivot = array[i];
			for (int j = i + 1; j < array.length; j++) {
				if (array[j] - pivot > Max) {
					Max = array[j] - pivot;
				}
			}
		}
		return Max;
	}
	public static void printResult(int Max){
		if (Max <= 0) {
			System.out.println("NO Profit!");
		} else {
			System.out.println("your maximum profit is=" + Max);
		}
	}
	public static void main(String[] args) {
		int[] inputArray=getArrayInputfromUser();
		int MaxProfit=MaxOfProfit(inputArray);
		printResult(MaxProfit);
	}
}
