package randomizedselection;

import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;


/**
 * @author Akshat Gupta: 1710110404
 */

public class RandomizedSelection {

    public static int comp=0;
    
    static int[] interchange_elements(int []a, int p, int q){ 
        
        int tmp = a[p]; 
        a[p] = a[q]; 
        a[q] = tmp; 
        return a; 
        
    } 
    
    static int kRandom(int array[], int start, int end, int key){ 
        
        if (key > 0 && key <= end-start+1){ 
            //The random element selection for choosing pivot and making partition.
            int n = end-start+1; 
            int initial_pivot = (int)(Math.random())*(n-1); 
            interchange_elements(array, start + initial_pivot, end);              
            int PivotPosition = quickPartition(array, start, end);  
            
            //Checking and implementing recursive calls. 
            if (PivotPosition-start == key - 1) 
                return array[PivotPosition];
            if (PivotPosition-start > key - 1)
                return kRandom(array, start, PivotPosition - 1, key);  
            else
                return kRandom(array, PivotPosition + 1, end, key - PivotPosition + start - 1); 
        } 
        return -1; 
        
    } 
  
    //Method to create partition just like in quicksort. 
    static int quickPartition(int a[], int l, int r){ 
        
        int t = a[r], p=l; 
        for (int q=l; q<=r-1; q++){ 
            comp++;
            if (a[q]<=t){ 
                interchange_elements(a, p, q); 
                p++; 
            } 
        } 
        interchange_elements(a, p, r); 
        return p; 
        
    }  
    
    
    public static void main(String[] args) throws IOException{
        
        BufferedReader brd = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(brd.readLine());
        String op = "";
        for(int p = 0; p<tc ; p++){
            StringTokenizer s_t = new StringTokenizer(brd.readLine());
            int n, k;
            n = Integer.parseInt(s_t.nextToken());
            k = Integer.parseInt(s_t.nextToken());
            int[] a = new int[n];
            s_t = new StringTokenizer(brd.readLine());
            for (int q = 0; q < a.length; q++) {
                a[q] = Integer.parseInt(s_t.nextToken());
            }
            op = op + kRandom(a, 0, n-1, k) + "\n";
        }
        System.out.println(op);  
        System.out.println("No. of comparisons: "+comp);
        
    }    
}
