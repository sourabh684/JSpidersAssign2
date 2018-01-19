import java.util.Scanner;

public class operationsOnArray {
	
	
	static int pInd = -1;
	
	static int[] insertion(int arr[], int index, int n) // check array max
	{
		if( (index >= arr.length) || (pInd == arr.length-1) )
		{
			System.out.println("ARRAY MAXIMUM SIZE REACHED");
		}
		else if(index > pInd)  // so that it array does not has null values
			System.out.println("Last Element Entered at index :" + pInd + "\n" + " Enter index less than it");
		else
		{
			for(int  i = arr.length-1 ; i > index ; i--)
			{
				arr[i] = arr[i-1];
			}
			
			arr[index] = n;
			pInd++;
		}
		return arr;
	}
	
	static int[] delete(int arr[], int index)
	{
		if(pInd == -1)
			System.out.println("Empty Array");
		else if(index > pInd)
			System.out.println("NULL elements present...Enter a different index");
		else
		{
			System.out.println("The number deleted from the array is : " + arr[index]);
			for(int i = index ; i<arr.length-1 ; i++)
				arr[i] = arr[i+1];
			pInd--;
		}
		return arr;
	}
	
	public static void main(String[] args)
	{
		int array[] = new int[5];
		array[0] = 23;
		array[1] = 10;
		array[3] = 42;
		int choice, i = 0;
		
		while(array[i] != 0)
			i++;
		pInd = i;
		
		Scanner sc = new Scanner(System.in);
		
		do
		{
			System.out.println("Enter your choice : ");
			System.out.println("1.INSERTION");
			System.out.println("2.DELETION");
			System.out.println("3.DISPLAY");
			System.out.println("4.EXIT");
			
			choice = sc.nextInt();
			
			switch(choice)
			{
				case 1:
				{
					System.out.println("Enter index at which you want to insert : ");
					int ind = sc.nextInt();
					System.out.println("Enter number to insert :");
					int n = sc.nextInt();
					insertion(array, ind, n);
					break;
				}
				case 2:
				{
					System.out.println("Enter index at which you want to delete : ");
					int ind = sc.nextInt();
					delete(array, ind);
					break;
				}
				case 3:
				{
					System.out.println("The Array is :");
					for(i = 0 ; i < array.length ; i++)
					{
						if(array[i] != 0)
							System.out.println(array[i]);
					}
					break;
					
				}
				case 4:
					System.exit(0);
					
				default:
					System.out.println("INVALID KEY");
			}
			
		}while(choice != 0);
		
		sc.close();
		
	}
}
