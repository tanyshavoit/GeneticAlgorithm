
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Tetiana Voitovych 2015
 */
//Розглянути окремо задачі для функцій з розмірністю  n=1, 2, 3, 20, 50. 
// 
public class GeneticAlgorithm {
    public double max_fitness;
    public int N;// кількість особин в популяції
    public int l;// довжина ланцюжка
    public ArrayList<String[]> allCh;//усі хромосоми
    public double a = -5.12;//початок інтервалу
    public double b = 5.12;//кінець інтервалу
    public double random;
    public double xi;
    public  SamplingUnits su;
    public  int n;
    public ArrayList<double[]> al;//список усіх фенотипів  
    public  ArrayList<Double> tf;
    public  ArrayList<Double> tf1;//        
    public double sum;
    public double sum1;
    public  ArrayList<Double> px;
    public ArrayList<Double> pxn;
    public ArrayList<Double> intervals;//інтервали для методу рулетки
    public ArrayList<Integer> newpopulation;
    public double pm;// мутація
    public double present_fitness;//теперішнє
    public double previous_fitness;//попереднє середнє здоров'я
    public int same_a_f; // змінна яка рахує скільки раз сер функція здоровя однакова 
    public double Pmax;
    public  GrayCode gray;
    public String code;
    public String method;
 

    GeneticAlgorithm(int n, String code, String method, int w_pmax, int w_pm) {
        this.max_fitness=0;
        this.code = code;
        this.pm = 0;
        this.sum = 0;
        this.sum1 = 0;
        this.n = n;
        this.l = 10 * n;
        this.N = 10 * l;
        this.newpopulation = new ArrayList<Integer>();
        this.allCh = new ArrayList<String[]>();
        this.su = new SamplingUnits();
        this.al = new ArrayList<double[]>();
        this.tf = new ArrayList<Double>();
        this.tf1 = new ArrayList<Double>();
        this.px = new ArrayList<Double>();
        this.pxn = new ArrayList<Double>();
        this.intervals = new ArrayList<Double>();
        this.present_fitness = 0;
        this.previous_fitness = 0;
        this.Pmax = 0;
        this.gray = new GrayCode();
        this.method=method;
        probability_of_mutation(w_pmax,w_pm);
        start();
        
    }
    public void start(){
        String s = "";
        String s1 = "";
        double mass[] = new double[n];
        String mass1[] = new String[n];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < n; j++) {
                s = "";
                s1 = "";
                random = new Random().nextDouble();
                xi = a + (random * (b - a));
                xi = Math.rint(100.0 * xi) / 100.0;
                mass[j] = xi;
                s = Integer.toBinaryString(su.coding(xi));
                if (code.equals("gray")) {
                   // System.out.println(" gray ggg");
                    s = gray.binaryTogray(s);
                }
                if (s.length() < 10) {
                    for (int v = 0; v < (10 - s.length()); v++) {
                        s1 += "0";
                    }
                    s = s1 + s;
                    mass1[j] = s;
                } else {
                    mass1[j] = s;
                }

            }

            al.add(mass.clone());
            
            allCh.add(mass1.clone());

        }
        
        fxi();//рахуємо фітнес
        present_fitness = average_f();//присвоюємо середнє значення 
        selection(method);//відбір
        mutation();//мутація
    }
 
    
    
    //наша цільова функція sum(xi^2)
    // функція, що шукає коефіцієнти пристосованості
    public void fxi() {
        tf.clear();
        sum = 0;
        sum1=0;
        tf1.clear();
        double fxi = 0;
        for (double[] al1 : al) {
            for (double d1 : al1) {
                fxi += d1 * d1;
            }
            tf.add(fxi);
            fxi = 0;
        }
        double max = Collections.max(tf);
        double temporary = 0;
        for (int i = 0; i < tf.size(); i++) {
            //System.out.println("max= "+max);
             temporary = (max + 1) - tf.get(i);
           // System.out.println("tf1= "+tf.get(i));
           // System.out.println("temporary= "+temporary);
            tf1.add(temporary);
           // System.out.println("tf2= "+tf.get(i));
            sum += tf1.get(i);
            sum1+=tf.get(i);
        }
        max_fitness=Collections.min(tf);
        
    }

//середнє значення функціх здоров'я
    public double average_f() {
        return sum1 / tf.size();
    }

 //пошук ймовірностей
   public  void pxi() {
        px.clear();
        double t;
        for (double f : tf1) {
            px.add(f / sum);
        }
    }

    //ймовірність на N   
    public void pxiN() {
        px.clear();
        for (double f : tf1) {
            px.add(((f / sum) * N));
        }
    }

    // інтервали для методу рулетки  
    public void intervals(ArrayList<Double> p) {
        intervals.clear();
        double temp = p.get(0);
        intervals.add(temp);
        for (int j = 1; j < p.size(); j++) {
            intervals.add((temp + p.get(j)));
            temp += p.get(j);
        }
    }

    public void selection(String method) {
        if (method.equals("RWS")) {
            selectMembersUsingRouletteWheel();
           // System.out.println("RWS");
        }
        if (method.equals("SUS")) {
            SUS();
            // System.out.println("SUS");
        }

    }

//метод рулетки  
    public void selectMembersUsingRouletteWheel() {
       // System.out.println("метод рулетки ");
        pxi();
        intervals(px);
        newpopulation.clear();
        double p = 0;
        for (int x = N - 1; x >= 0; x--) {
            p = Math.random();
            if (p >= 0 && p < intervals.get(0)) {
                newpopulation.add(0);
            } else {
                int i = 0;
                while (true) {
                    if ((p > intervals.get(i)) && (p < intervals.get(i + 1))) {
                        newpopulation.add(i + 1);
                        break;
                    }
                    i++;
                }

            }
        }
    }

    public void SUS() {
       // System.out.println("SUS");
        pxiN();
        intervals(px);
        newpopulation.clear();
        double p = 0;
        p = Math.random();

        for (int x = N - 1; x >= 0; x--) {
            if (p >= 0 && p < intervals.get(0)) {
                newpopulation.add(0);
            } else {
                int j = 0;
                while (true) {
                    if ((p > intervals.get(j)) && (p < intervals.get(j + 1))) {
                        newpopulation.add(j + 1);
                        break;
                    }
                    j++;
                }
            }
        }
    }

    public void mutation() {
        int number_with_mutation = 0;
        double n1 = N * l * pm;
        number_with_mutation = (int) (Math.rint(1.0 * n1) / 1.0);
        int r = 0;
        int r1 = 0;
        int r2 = 0;
        String line = "";
        char[] char_mass;
        String[] str;
        for (int i = 0; i < number_with_mutation - 1; i++) {
            r = (int) (Math.random() * 100);
            r1 = (int) (Math.random() * n);
            r2 = (int) (Math.random() * 10);
            char_mass = allCh.get(r)[r1].toCharArray();
            if (char_mass[r2] == '0') {
                char_mass[r2] = '1';
            } else {
                char_mass[r2] = '0';
            }
            for (char cm : char_mass) {
                line = line + cm;
            }
            str = allCh.get(r);
            str[r1] = line;
            allCh.set(r, str);
            line = "";
        }
    }

    public void new_population_phenotype() {
        double gen_mass[] = new double[n];
        int j = 0;
        for (int i = 0; i < N; i++) {
            for (String gen1 : allCh.get(i)) {
                if (code.equals("gray")) {
                    gen_mass[j] = su.decoding(Integer.parseInt(gray.grayTobinary(gen1), 2));
                }
                if (code.equals("binary")) {
                    gen_mass[j] = su.decoding(Integer.parseInt(gen1, 2));
                }
                j = j + 1;
            }
            j = 0;
            al.set(i, gen_mass.clone());
        }
    }

   public  void probability_of_mutation(int number1, int number2) {
        if (number1 == 0) {
            double delta = 0.5 * (l + 1);
            Pmax = Math.log(delta) / l;
           // System.out.println("Pmax " + Pmax);
            diff_mutation(number2);
        }
        if (number1 == 1) {
            Pmax = (1.0 / l);
           // System.out.println("Pmax 2 = " + Pmax);
            diff_mutation(number2);
        }

    }
    
    public void diff_mutation(int number) {
        switch (number) {
            case 0:
                pm = Pmax;
                break;
            case 1:
                pm = 0.1 * Pmax;
                break;
            case 2:
                pm = 10 * Pmax;
                break;
            case 3:
                pm = 0.2 * Pmax;
                break;
            case 4:
                pm = 5 * Pmax;
                break;
            case 5:
                pm = Pmax - (0.2 * Pmax);
                break;
            case 6:
                pm = Pmax + (0.2 * Pmax);
                break;
            case 7:
                pm = Pmax - (0.1 * Pmax);
                break;
            case 8:
                pm = Pmax + (0.1 * Pmax);
                break;
        }
    }
    
    

    
  
}
