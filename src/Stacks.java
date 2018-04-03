//Kevin Jimenez
//Cs2302
//Lab 4

import java.io.*;
import java.lang.*;
import java.util.*;

class Stacks
{	
	static public Stack A = new Stack();
	static public Stack B = new Stack();
	static public Stack C = new Stack();
	static int countA = 0;
	static int countB = 0;
	static int countC = 0;
	public static void main(String[] args) {
		Scanner input= new Scanner (System.in);
		System.out.print("\nEnter the number of discs: ");

		int discs = input.nextInt();
		if (discs <= 1 || discs >= 13)// computer cannot afford more than 13
		{
			System.out.println("Please enter between 2 - 12");
		}
		for (int i = discs; i >= 1; i--)// push numbers into first stack
			A.push(i);
		countA = A.size();
		countB = B.size();
		countC = C.size();
		PrintStacks();
		SolveTOH(discs, A, B, C);
		while (C.size() > 0){
			C.pop();
		}

		System.out.println();
		System.out.println("How many numbers would you like to create and sort?");
		int choice=input.nextInt();
		int [] X = new int [choice];
		for ( int i =0; i<X.length; i++){
			X[i]=(int) (Math.random()*10);
			System.out.println(X[i]);
		}

		QuickSort(X);
	}

	public static void Solve2DiscsTOH(Stack source, Stack temp, Stack dest)
	{            
		temp.push(source.pop());
		PrintStacks();
		dest.push(source.pop());
		PrintStacks();
		dest.push(temp.pop());
		PrintStacks();
	}

	static public int SolveTOH(int nDiscs, Stack source, Stack temp, Stack dest)

	{
		if (nDiscs <= 4)
		{
			if ((nDiscs % 2) == 0)//if pair number of disks
			{
				Solve2DiscsTOH(source, temp, dest);
				nDiscs = nDiscs - 1;
				if (nDiscs == 1)
					return 1;

				temp.push(source.pop());
				PrintStacks();
				//new source is dest, new temp is source, new dest is temp;
				Solve2DiscsTOH(dest, source, temp);
				dest.push(source.pop());
				PrintStacks();
				//new source is temp, new temp is source, new dest is dest;
				SolveTOH(nDiscs, temp, source, dest);
			}
			else// if impair number of disks
			{
				if (nDiscs == 1)
					return -1;
				Solve2DiscsTOH(source, dest, temp);
				nDiscs = nDiscs - 1;
				dest.push(source.pop());
				PrintStacks();
				Solve2DiscsTOH(temp, source, dest);
			}
			return 1;
		}
		else if (nDiscs >= 5)
		{
			SolveTOH(nDiscs - 2, source, temp, dest);
			temp.push(source.pop());
			PrintStacks();
			SolveTOH(nDiscs - 2, dest, source, temp);
			dest.push(source.pop());
			PrintStacks();
			SolveTOH(nDiscs - 1, temp, source, dest);
		}
		return 1;
	}



	static public void PrintStacks()
	{

		System.out.println();


		PrintStack(A);
		System.out.print(" , ");
		PrintStack(B);
		System.out.print(" , ");
		PrintStack(C);

	}

	static public void PrintStack(Stack s)
	{
		System.out.print(s.toString());
	}


	public static void QuickSort(int[] X) { 
		Stack<Integer> S = new Stack<Integer>();
		S.push(0);
		S.push(X.length);
		while (!S.isEmpty()) {  // to create stack activation records
			int end = S.pop();
			int start = S.pop();
			if (end - start < 2) 
				continue;
			int p = start + ((end-start)/2);
			p = partition(X,p,start,end);

			S.push(p+1);
			S.push(end);

			S.push(start);
			S.push(p);

		}
		System.out.println("Sorted Array");
		for (int i=0; i<X.length;i++){//print sorted array
			System.out.println(X[i]);
		}
	}

	private static int partition(int[] arr, int p, int start, int end) {
		int low = start;
		int high = end - 2;
		int pivot = arr[p];

		int temp = arr[p];//swap p with n-1
		arr[p] = arr[end-1];
		arr[end-1] = temp;

		while (low < high) {
			if (arr[low] < pivot) {
				low++;// move low pointer one to the right
			} 
			else if (arr[high] >= pivot) { 
				high--;// move high pointer one to the left
			} 
			else { //swap low with high
				int t = arr[low];
				arr[low] = arr[high];
				arr[high] = t;
			}
		}
		int x = high;
		if (arr[high] < pivot)
			x++;
		//swap end-1,x
		int cur = arr[end-1];
		arr[end-1] = arr[x];
		arr[x] = cur;

		return x;
	}
}
