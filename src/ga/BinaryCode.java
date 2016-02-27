
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tetiana Voitovych
 * 2015
 */
public class BinaryCode {
    
    private String s;
    
    BinaryCode(){
        s=""; 
    }
     
    public void erase(){
        s="";
    }
    //функція для переведення в бітовий код для нашої задачі
    public String toBinary(ArrayList<Integer> el){
         String t="";
         String t1="";
         String t2="";
         for(int e: el ){
            t1=Integer.toBinaryString(e); 
            if(t1.length()<10){
                for(int i=0;i<(10-t1.length());i++){
                    t2+="0";   
                }
                t1=t2+t1;
                t2="";
                            }
            s=s+t1;
            
    }
    t=s;
    return t;
}
}
