
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
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
public class Main {

    /**
     * @param args the command line arguments
     */
    static List<String> logData = new ArrayList<>();
    static void write(List<String> logData){
        try {
            TaskLogger.writeToFile(logData,false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        int n = 20;//змінюємо n
        // TODO code application logic here
        //розкоментувати для графічної програми
        MainJFrame a = new MainJFrame();
        a.show();
        //для бінарного коду
        //метод рулетка
       
      /*  for(int i=0;i<9;i++){
        RunExport re=new RunExport(n,"binary", "RWS", 0, i);
        logData.add(re.f);
        }
        
        for(int i=0;i<9;i++){
        RunExport re=new RunExport(n,"binary", "RWS", 1, i);
        logData.add(re.f);
        }
        
        //метод стохастничний
        
        for(int i=0;i<9;i++){
        RunExport re=new RunExport(n,"binary", "SUS", 0, i);
        logData.add(re.f);
        }
        
        for(int i=0;i<9;i++){
        RunExport re=new RunExport(n,"binary", "SUS", 1, i);
        logData.add(re.f);
        }
        //для грея коду
        //метод рулетки
        for(int i=0;i<9;i++){
        RunExport re=new RunExport(n,"gray", "RWS", 0, i);
        logData.add(re.f);
        }
        
        for(int i=0;i<9;i++){
        RunExport re=new RunExport(n,"gray", "RWS", 1, i);
        logData.add(re.f);
        }
        
        //метод стохастничний
        
        for(int i=0;i<9;i++){
        RunExport re=new RunExport(n,"gray", "SUS", 0, i);
        logData.add(re.f);
        }
        
        for(int i=0;i<9;i++){
        RunExport re=new RunExport(n,"gray", "SUS", 1, i);
        logData.add(re.f);
        }
        System.out.println("END");
        write(logData);
        */
    }
    
}
