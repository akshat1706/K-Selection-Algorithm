package median.of.median;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

/**
 *
 * @author Akshat Gupta: 1710110404
 */

public class MedianOfMedian {

    public static int comp=0;
    public static int MedianOfMedian=0;

    static int kMom(int array[], int start, int end, int key){ 
        
        if (key>0 && key<= end-start+1){ 
            int n = end-start+1; 
            
            //Array to store medians of subgroups
            int ml[];
            ml = new int[(n+(16/4))/5]; 
            int i=0; 
            
            //Calculating medians of subgroups
            for (i = 0; i < n/5; i++){    
                ml[i] = takeMedian(array,start + (i)*5, 5);
            } 
            if (i*5 < n){ 
                ml[i] = takeMedian(array,start + (i)*5, n%5);  
                i++; 
            }  
            
            if(i==1){
               MedianOfMedian =  ml[i-1];
            }
            else
               MedianOfMedian = kMom(ml, 0, i - 1, 2*i/4);
                
            //Pivoting around the True Median of Median        
            int PivotPosition = quickPartition(array, start, end, MedianOfMedian); 
            
            //Calling Recursive calls 
            if (PivotPosition-start == key - 1) 
                return array[PivotPosition];
            if (PivotPosition-start > key - 1)
                return kMom(array, start, PivotPosition - 1, key);  
            return kMom(array, PivotPosition + 1, end, key - PivotPosition + start - 1); 
        } 

        return -1; 
    } 
    
    //Median Finding Method for smaller subgroups
    static int takeMedian(int a[], int k,int t){
       
        if(k <= t) 
            Arrays.sort(a, k, k+t); 
	else
            Arrays.sort(a, t, k); 
	return a[k+(t/2)]; 
    } 

    //Quick sort inspired partition around he pivot
    static int quickPartition(int b[], int p, int q, int key ){ 
        int m=0; 
        for (m = p; m< q; m++){ 
            if (b[m] == key) 
            break; 
        }    
        interchange_elements(b, m, q); 

        m=p;  
        for (int n=p; n<=q-1; n++){ 
            comp++;
            if (b[n]<=key){ 
                interchange_elements(b, m, n); 
                m++;  
            } 
        } 
        interchange_elements(b, m, q); 
        return m; 
    } 
     
    //Method to Swap 2 elements in an array
    static int[] interchange_elements(int []a, int p, int q){ 
        int tmp = a[p]; 
        a[p] = a[q]; 
        a[q] = tmp; 
        return a; 
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
            op = op + kMom(a, 0, n-1, k) + "\n";
        }
        System.out.println(op);  
        System.out.println("No. of comparisons: "+comp);
               
    }
    
}
