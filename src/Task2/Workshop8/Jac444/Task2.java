/*
Stduent: Mitchell Culligan
Workshop 8
id: 1616293170
email: mculligan@myseneca.ca
Professor: Mahboob Ali
Date: March 27th, 2020
 */
package Task2.Workshop8.Jac444;
import java.util.Scanner;

public class Task2 {

    public static final ArrayProcessor GET_MAX = (array)->{
      double max=0.0;


      for(double i: array)
          if(i>max) max=i;

        return max;
    };

    public static final ArrayProcessor GET_MIN = (array)->{
        double min=0.0;

        for(double i: array)
            if(i<min) min=i;

        return min;
    };

    public static final ArrayProcessor GET_SUM = (array)->{
        double sum =0.0;

        for(double i: array)
            sum+=i;
        return sum;
    };

    public static final ArrayProcessor GET_AVG = (array)-> Task2.GET_SUM.apply(array)/array.length;

    public static ArrayProcessor counter(double value){
        return (array)->{
            double count=0;
            for(double i:array){
                if(value==i)count++;
            }
            return count;
        };
    }
    public static void main(String[] args){
        double value=0.0;
        double[] array;
        int arraySize=0;
        boolean flag;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the size of the array: ");
        do {
            flag = false;
            if (input.hasNextInt()) {
                arraySize = Math.abs(input.nextInt());
            } else {
                System.out.println("Invalid input");
                flag = true;
                input.next();
            }
            input.nextLine();
        }  while(flag);

        array = new double[arraySize];
        do{
            System.out.print("Enter the values: ");
            flag=false;
            for(int i=0;i<array.length&& !flag;i++){
                if(input.hasNextDouble()){
                    array[i] = input.nextDouble();
                } else{
                    flag=true;
                    System.out.println("Invalid input");
                    input.next();

               }
            }

            input.nextLine();
        }while(flag);
        System.out.print("Enter the value to count: ");
        do {
            flag = false;
            if (input.hasNextDouble()) {
                value = input.nextDouble();
            } else {
                System.out.println("Invalid input");
                flag = true;
                input.nextLine();
            }
        }  while(flag);


        System.out.printf("The number of %.2f in the array is %d times \n",value,(int)counter(value).apply(array) );


    }
}
