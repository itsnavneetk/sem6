import java.util.ArrayList;
import java.util.Scanner;

public class apriori {
    
    public static void main(String[] args){
        Scanner s=new Scanner(System.in);
        
        System.out.print("Enter the number of transactions: ");
        int tNum=s.nextInt();
        
        ArrayList<ArrayList<String>> transactions=new ArrayList<>();
        for (int i=0; i<tNum; i++)
        {
            System.out.print("\nEnter the number of items in transaction T"+i+": ");
            int n=s.nextInt();
            System.out.println("Enter the items:");
            ArrayList<String> a=new ArrayList<>();
            
            for (int j=0; j<n; j++)
            {
                String item=s.next();
                if (a.contains(item))
                {
                    System.err.println(item+" already added");
                    j--;
                }
                else
                    a.add(item);
            }
            transactions.add(a);
        }
        
        System.out.println(transactions);
        
        System.out.print("Enter the minimum support: ");
        int minSup=s.nextInt();
        
        if (minSup<0 || minSup>transactions.size())
        {
            System.err.println("Invalid minimum support");
            System.exit(0);
        }
        
        ArrayList<ArrayList<String>> frequentList=new ArrayList<>();
        ArrayList<ArrayList<String>> candidate=new ArrayList<>();
        ArrayList<ArrayList<String>> large=new ArrayList<>();
        
        for (int i=0; i<tNum; i++)
        {
            ArrayList<String> t=transactions.get(i);
            int n=t.size();
            for (int j=0; j<n; j++)
            {
                String item=t.get(j);
                ArrayList<String> a=new ArrayList<>();
                a.add(item);
                if (!candidate.contains(a))
                    candidate.add(a);
            }
        }
        
        int itrNo=1;
        while(true)
        {
            large.clear();
            
            for (int i=0; i<candidate.size(); i++)
            {
                ArrayList<String> a=candidate.get(i);
                int ct=0;
                for (int j=0; j<transactions.size(); j++)
                {
                    boolean p=true;
                    ArrayList<String> b=transactions.get(j);
                    for (int k=0; k<a.size(); k++)
                    {
                        if (!b.contains(a.get(k)))
                        {
                            p=false;
                            break;
                        }
                    }
                    if (p)
                        ct++;
                }
                if (ct>=minSup)
                {
                    large.add(a);
                    frequentList.add(a);
                }
            }
            
            if (large.isEmpty())
                break;
            
            candidate.clear();
            itrNo++;
            
            for (int i=0; i<large.size()-1; i++)
            {
                ArrayList<String> a=large.get(i);
                for (int j=i+1; j<large.size(); j++)
                {
                    ArrayList<String> b=large.get(j);
                    if (arrSublist(a,0,itrNo-2).equals(arrSublist(b,0,itrNo-2)))
                    {
                        ArrayList<String> c=new ArrayList<>();
                        for (int m=0; m<a.size(); m++)
                            c.add(a.get(m));
                        for (int m=itrNo-2; m<b.size(); m++)
                            c.add(b.get(m));
                        candidate.add(c);
                    }
                }
            }
            
            if (candidate.isEmpty())
                break;
        }
        
        System.out.println("The list of frequent itemsets is:");
        System.out.println(frequentList);
    }

    protected static ArrayList<String> arrSublist(ArrayList<String> a, int start, int end)
    {
        ArrayList<String> b=new ArrayList<>();
        for (int i=start; i<end; i++)
            b.add(a.get(i));
        return b;
    }
}