
public class test {
	
	
	public static String getSmallestAndLargest(String s, int k) {
        String smallest = "";
        String largest = "";
        
        char[] array = s.toCharArray();
        int size = (s.length()/k);
        
        String[] w = new String[size];
        
        
        int count = 0;
        int posicion = 0;
        String t = "";
        for( char a : array){
            
            if( count == (k-1)){
                w[posicion]= t;
                posicion++;
                t = "";    
            }else{
                t = t + a;
                count++;
            }
        }
        
       // Collections.sort(w);
        
        smallest = w[0];
        largest = w[ w.length -1];
        
        // Complete the function
        // 'smallest' must be the lexicographically smallest substring of length 'k'
        // 'largest' must be the lexicographically largest substring of length 'k'
        
        return smallest + "\n" + largest;
    }


}
