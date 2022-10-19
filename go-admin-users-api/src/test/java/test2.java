


public class test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getSmallestAndLargest("ASDFHDSFHsdlfhsdlfLDFHSDLFHsdlfhsdlhkfsdlfLHDFLSDKFHsdfhsdlkfhsdlfhsLFDLSFHSDLFHsdkfhsdkfhsdkfhsdfhsdfjeaDFHSDLFHDFlajfsdlfhsdlfhDSLFHSDLFHdlfhs"
				,144);
	}

	
	
	public static String getSmallestAndLargest(String s, int k) {
        String smallest = "";
        String largest = "";
        
        java.util.List<String> list = new java.util.ArrayList<String>();
        
        char[] array = s.toCharArray();
        int size = (s.length()/k);
        
        String[] w = new String[size];
        
        
    
        dividirInCadenas(array , k , w);   
        imprimir(w ,list);
        
        
    
        
        
        for( char a : array){   
            array =  nextONePocition(array, array.length);
            dividirInCadenas(array , k , w);  
        	imprimir(w, list);
        }
        
       imprimirFinal(list);
       
       //order
        for(int i = 0; i < list.size()-1; ++i) {  
            for (int j = i + 1; j < list.size(); ++j) {  
               if (list.get(i).compareTo( list.get(j)) > 0) {  
                  String temp = list.get(i); 
                  list.set(i, list.get(j));
                  list.set(j, temp);
               }  
            }  
         }  
        
        smallest = list.get(0);
        largest = list.get(list.size() -1);
        
        //System.out.println("");
        //System.out.println(smallest);
        //System.out.println(largest);
        
        // Complete the function
        // 'smallest' must be the lexicographically smallest substring of length 'k'
        // 'largest' must be the lexicographically largest substring of length 'k'
        
        return smallest + "\n" + largest;
    }
	
	
	public static void addKey( java.util.List<String> keys , String value) {
		if(keys.size() == 0 ) {
			keys.add(value);
			
		}else {
			boolean encontro = false;
			for( String key : keys) {
				if( key.equals(value)) {
					encontro = true;
					break;
				}
			}
			if( !encontro) {
				keys.add(value);
			}
		}
		
	}
	
	public static void dividirInCadenas( char[] array , int size , String[] w ) {
	    int count = 0;
        int posicion = 0;
        String t = "";
        
        
        for( char a : array){      
            if( count == (size)){
                w[posicion]= t;
                posicion++;
                t = String.valueOf(a); 
                count = 1;
            }else{
                t = t + a;
                count++;
            }
        }
	}
	

	
	public static char [] nextONePocition(char [] rr, int size) {
		char [] out = new char [size]; 
		
		int posicion = 0;
		int index = 0;
		for( char s : rr) {
				if(posicion == rr.length-1) {
					out[0]= s;
				}else {
					out[index+1]=s;
					index++;
				}
			
			posicion++;
		}
		
		return out;
	}
	
	public static void imprimir(String [] rr ,java.util.List<String> listFinal ) {
	
		for( String s : rr) {
			addKey(listFinal,s);
			// System.out.print(s + " ");
		}
		//System.out.println("");
		//System.out.println("-----------------------------");
	}
	
	public static void imprimirFinal(java.util.List<String> listFinal ) {
		
		for( String s : listFinal) {
			//addKey(listFinal,s);
			 System.out.print(s + " ");
		}
		System.out.println("");
		System.out.println("-----------------------------");
	}
	
	
}
