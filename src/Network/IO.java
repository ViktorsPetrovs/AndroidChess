package Network;

import java.util.Arrays;

public class IO {
	
	public static String encodeString(int x,int y, int x2, int y2){
		String coordinates;
		coordinates = x +"," +y+","+x2+","+y2;
		System.out.println(coordinates);
		
		
		
		return coordinates;
	}
	
	
public static int[] decodeString(String input){
		String [] parts = input.split(",");
		int[] coordinates = new int[parts.length];
		for(int i=0; i < parts.length; i++)
		{
			coordinates[i]=Integer.parseInt(parts[i]);
		}
		
		System.out.println(Arrays.toString(coordinates));
		
		return coordinates;
	}

}
