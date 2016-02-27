/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tetiana Voitovych
 * 2015
 *
 */

//Кодування вузлами дискретизації
public class SamplingUnits {
    double a;//початок відрізку
    double b;//кінець відрізку
    double m;//мінімальна довжина двійкової послідовності
    
    SamplingUnits(){
       a=-5.12;
       b=5.12;
       m=10.0014;
    }
    
    //знаходимо десяткове значення числа, закодованого двійковою послідовністю
    int coding(double x){
        double res;
        res=((x-a)/(b-a))*(Math.pow(2,m)-1);
        int bin10= (int)Math.rint(1 * res) / 1;
        return bin10;
    }
    //знаходимо яке дійсне число з інтервалу кодується десятковим числом
    double decoding(int bin10){
        double res;
        res=a+bin10*((b-a)/(Math.pow(2,m)-1));
        double x=Math.rint(100.0 * res) / 100.0;
        return x;
    }
    
    
    
}
