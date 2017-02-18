package db_assignment2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class DB_Assignment2 
{
    static ArrayList<Record> records=new ArrayList<Record>();
    
    public static void main(String[] args) throws IOException 
    {
        System.out.println("Enter how many records do you want? ");
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        System.out.println("Enter Block size that you want? ");
        int blockSize=sc.nextInt();
        String FILENAME = "C:\\Users\\Mamta Sharma\\Documents\\NetBeansProjects\\DB_Assignment2\\src\\db_assignment2\\Dataset.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) {
            int count=1;
            String result="";
            for(int i=0;i<n;i++)
            {
                Random ran=new Random();
                int amount=ran.nextInt(50000)+1;
                int id=i+1;
                SecureRandom random = new SecureRandom();
                String name=new BigInteger(130, random).toString(32);
                name=(String) name.subSequence(1, 4);
                Record r=new Record(id,amount,name);
                records.add(r);
                String s;
                
                if(i<n-1)
                {
                    int iid=r.id;
                    s=Integer.toString(iid);
                    s=s.concat(",");
                    s=s.concat(Integer.toString(r.amount));
                    s=s.concat(",");
                    s=s.concat(name);
                    s=s.concat(",");
                    bw.write(s);
                }
                else
                {
                   int iid=r.id;
                   s=Integer.toString(iid);
                    s=s.concat(",");
                    s=s.concat(Integer.toString(r.amount));
                    s=s.concat(",");
                    s=s.concat(name);
                    bw.write(s);
                }
                if(i%blockSize==0&& i!=0)
                {   //create a new block
                    String file = "C:\\Users\\Mamta Sharma\\Documents\\NetBeansProjects\\DB_Assignment2\\src\\db_assignment2\\Blocks\\file"+count+".txt";
                    BufferedWriter bw1=new BufferedWriter(new FileWriter(file));
                    bw1.write(result);
                    result="";
                    bw1.close();
                    count++;
                }
                result=result.concat(s);
                //System.out.println(result);
                if(i==n-1)
                {
                    String file = "C:\\Users\\Mamta Sharma\\Documents\\NetBeansProjects\\DB_Assignment2\\src\\db_assignment2\\Blocks\\file"+count+".txt";
                    BufferedWriter bw1=new BufferedWriter(new FileWriter(file)); 
                    bw1.write(result);
                    bw1.close();
                }
                
            }
            bw.close();
        }
//  print All records in file         
        for(int i=0;i<n;i++)
        {
            System.out.println(records.get(i).id +" "+records.get(i).amount+" "+records.get(i).name);
        }
             
    }
    
}

class Record
{
    int id;
    int amount;
    String name;
    public Record(){}
    public Record(int a,int b,String c)
    {
        id=a;
        amount=b;
        name=c;
    }
}
