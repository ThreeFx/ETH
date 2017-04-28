import java.util.Random;

public class Main {
	public static void main(String[] args) {
		long starttime = System.nanoTime();
		long runs = 100000000;
		long sum = 0;
		Random myRandom = new Random();
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		int[] Numbers = new int[100];

		for (int i=0; i<runs; i++){
			int x = 2;
			while (myRandom.nextBoolean()) {
				x+=2;
			}
			//System.out.println(x);
			sum+=x;
			if (x<min) {
				min=x;
			}
			if (x>max) {
				max=x;
			}
			Numbers[x]++;
		}
		System.out.println("Average = "+(sum*1.0/runs));
		System.out.println("Max = "+max);
		System.out.println("Min = "+min);
		for (int i = 2; i<Numbers.length; i+=2) {
			//Numbers[i]=Numbers[i]/(runs/5000);
			System.out.print(i+":");
//			for(int c=0; c<Numbers[i]; c++){
//				System.out.print("X");
//			}
			System.out.println(Numbers[i]);
		}
		System.out.println("Time = "+(System.nanoTime()-starttime)/1000000.0+" ms");
	}
}
