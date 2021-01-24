import java.util.Scanner;
import java.util.*;
public class Sort {
	public static int[] getArrayInputFromUser() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter number of elements:");
		int n = scanner.nextInt();
		while (n <= 1 || n >= 300000) {
			System.out.println("Invalid  range!");
			System.out.println("Enter number of elements:");
			n = scanner.nextInt();
		}
		int[] array = new int[n];
		System.out.println("Enter elements:");
		for (int i = 0; i < n; i++) {
			System.out.print("a[" + i + "]=");
			array[i] = scanner.nextInt();
		}
		System.out.println("Array completed!");
		return array;

	}
	public static void printArray(int array[]) {
		for (int i = 0; i < array.length; i++) {
			System.out.println("array[" + i + "]=" + array[i]);
		}
	}
	public static int checkChanges(int array[]) {
		int[] rawArray = array.clone();
		int[] sortedArray = array.clone();
		Arrays.sort(sortedArray);
		int sumOfChanges = 0;
		for (int i = 0; i < array.length; i++) {
			if (sortedArray[i] != rawArray[i]) {
				sumOfChanges++;
			}
		}
		System.out.println("raw Array:");
		printArray(rawArray);
		System.out.println("sorted Array:");
		printArray(sortedArray);
		return sumOfChanges;
	}

	public static void main(String[] args) {
		int[] inputArray=getArrayInputFromUser();
		int sumOfChanges=checkChanges(inputArray);
		System.out.println("Number of changes in array="+sumOfChanges);
	}
}
