import java.util.*;
public class Matrix
{

	public static int[][] rotateMatrix(int[][] matrix,int row,int col)
	{
		int[][] newMatrix = new int[row][col];
		for (int i = 0; i < row  ; i++) 
		{
			for (int j= 0 ;j <col ;j++ )
			 {
					newMatrix[i][j] = matrix[row - j -1][i];
			}
		}

		return newMatrix;
	}

	public static void main(String[] args)
	{
		int row=3,col=3,count=1;
		Scanner scan = new Scanner(System.in);
		int[][] matrix = new int[row][col];
		int[][] newMatrix = new int[row][col];

		for(int i = 0; i < row ; i++)
		{
			for(int j = 0; j < col ; j++)
			{
				matrix[i][j] = count++;
			}
		}
        newMatrix =matrix;
		for(int i=0;i < 3 ; i++)
		{
			newMatrix = rotateMatrix(newMatrix,row,col);
		}

		System.out.println("Roatated matrix ");
		
		for (int i = 0; i < row  ;i++ ) 
		{
			for (int j= 0 ;j <col ;j++ )
			 {
					System.out.print(newMatrix[i][j]);
			}
			System.out.println();
		}		

	}
	
}