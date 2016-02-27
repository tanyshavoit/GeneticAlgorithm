
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tetiana
 */
public class RunExport {
    GeneticAlgorithm ga;
    //List<String> logData = new ArrayList<>();
    public String f="";

       RunExport(int n,String code, String method, int w_pmax, int w_pm){
           
               
               
        ArrayList<Double> mass_best = new ArrayList<Double>();
        ArrayList<Double> mass_avg = new ArrayList<Double>();
        double avg_avg = 0;
        double best_best = 0;
        for(int number=0;number<10;number++){
        ga=new GeneticAlgorithm(n,code,method,w_pmax,w_pm);
        ga.start();    
        int k_f=0;
        for (int j = 2; j < 10001; j++) {
            
            ga.new_population_phenotype();
            ga.fxi();
            ga.previous_fitness = ga.present_fitness;
            ga.present_fitness = ga.average_f();
            if (ga.previous_fitness == ga.present_fitness) {
                ga.same_a_f = ga.same_a_f + 1;
                if (ga.same_a_f == 5) {
                    System.out.println("Збіжність");
                    break;
                }
            } else {
                ga.same_a_f = 0;
            }
            ga.selection(ga.method);
             ga.mutation();
            
           k_f=j;
            }
        String kf="";
        if(k_f<10000){kf="convergence"+k_f;}
        else{
            kf=kf+k_f;
        }
        int index_of_best_element=ga.tf.indexOf(ga.max_fitness);
        String [] bestel = ga.allCh.get(index_of_best_element);
        String best_el="";
        for(String h : bestel){
            best_el+=h;
        }
        int number_of_one=0;
        for(char char_s:best_el.toCharArray()){
            if(char_s!='0'){number_of_one+=1;}
        }
        //знайдемо евклідову відстань
        double euclidean_space = 0;
        if (number_of_one!=0){
           euclidean_space=Math.sqrt(number_of_one);
        }
        // знайдемо гемінгову відстань
        int hamming_distance = number_of_one;
        //помилки
        double [] interest = {0,0,0,0,0,ga.N};
        if(ga.max_fitness<0.05){
            for(double t:ga.tf){
               if((Math.rint(100.0 * t) / 100.0)==0){interest[0]+=1;
               }
               if((Math.rint(100.0 * t) / 100.0)==0.01){interest[1]+=1;
               }
               if((Math.rint(100.0 * t) / 100.0)==0.02){interest[2]+=1;
               }
               if((Math.rint(100.0 * t) / 100.0)==0.03){interest[3]+=1;
               }
               if((Math.rint(100.0 * t) / 100.0)==0.04){interest[4]+=1;
               }    
            }
           interest[5] = ga.N-(interest[0]+interest[1]+interest[2]+interest[3]+interest[4]);   
        }
        
        f+=kf+";"+ga.max_fitness+";"+ga.present_fitness+";"+euclidean_space+";"+hamming_distance+";"+(interest[0]/ga.N)+";"+(interest[1]/ga.N)+";"+(interest[2]/ga.N)+";"+(interest[3]/ga.N)+";"+(interest[4]/ga.N)+";"+(interest[5]/ga.N)+";";
        mass_best.add(ga.max_fitness);
        mass_avg.add(ga.present_fitness);
        avg_avg+=ga.present_fitness;
        best_best+=ga.max_fitness;
      }
        
        avg_avg = avg_avg/10;
        best_best = best_best/10;
        f+=avg_avg+";"+best_best+";"+Collections.min(mass_best);
       // logData.add(f);
                     
         /*  try {
            TaskLogger.writeToFile(logData,false);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
       }
}
