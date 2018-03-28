package calculatorrmi;

import java.io.*;
import java.util.*;
import javafx.util.Pair;


public class KMeans {

    public static void main(String[] args) {

        ArrayList<Pair<Float,Float>> points=new ArrayList<>();
        try{
            File file=new File("data-01");
            BufferedReader br=new BufferedReader(new FileReader(file));

            
            
            String st;
            while ((st = br.readLine()) != null)
            {
                //System.out.println(st);
                String s[]=st.split("\t");
                //System.out.println(s[2]+","+s[3]);
                
                //Pair<Integer,Integer> p=new Pair(Integer.parseInt(s[2]),Integer.parseInt(s[3]));
                //System.out.println(p.getKey()+","+p.getValue());
                
                points.add(new Pair(Float.parseFloat(s[2]),Float.parseFloat(s[3])));
            }
            
            
        }
        catch(Exception e){
            System.err.println(e.toString());
        }
        
        
        System.out.print("Enter the number of clusters: ");
        Scanner s=new Scanner(System.in);
        int k=s.nextInt();
        
        ArrayList<Pair<Float,Float>> centers=new ArrayList<>();
        for (int i=0; i<k; i++)
        {
            Random r=new Random();
            int n=r.nextInt(points.size());
            Pair<Float,Float> p=new Pair(points.get(n).getKey(),points.get(n).getValue());
            if (!centers.contains(p))
                centers.add(new Pair(points.get(n).getKey(),points.get(n).getValue()));
            else
                i--;
            //System.out.println(n);
        }
        //for (int i=0; i<k; i++)
            //System.out.println(centers.get(i).getKey()+","+centers.get(i).getValue());
        
        ArrayList<Integer> clusters=new ArrayList<>(Collections.nCopies(points.size(), 0));
        ArrayList<Pair<Float,Float>> oldCenters=new ArrayList<>();
        for (int i=0; i<k; i++)
            oldCenters.add(new Pair(0,0));
        
        while(!oldCenters.equals(centers))
        {
//                System.out.println(centers);
//                System.out.println(oldCenters);
//                System.out.println(oldCenters.equals(centers));
            
            //oldCenters=centers;
            
            oldCenters.clear();
            for (int i=0; i<k; i++)
                oldCenters.add(centers.get(i));
            
//                System.out.println(centers);
//                System.out.println(oldCenters);
//                System.out.println(oldCenters.equals(centers));
            
            for (int i=0; i<points.size(); i++)
            {
                float x1=points.get(i).getKey(), y1=points.get(i).getValue();
                double minDist=-1;
                int cluster=-1;
                
                for (int j=0; j<k; j++)
                {
                    float x2=centers.get(j).getKey(), y2=centers.get(j).getValue();
                    double dist=Math.sqrt(Math.pow((x1-x2),2) + Math.pow((y1-y2),2));
                    
                    if (minDist==-1 || dist<minDist)
                    {
                        minDist=dist;
                        cluster=j;
                    }
                }
                clusters.set(i, cluster);
            }
            
            ArrayList<ArrayList<Float>> calcCenters=new ArrayList<>();
            for (int i=0; i<k; i++)
            {
                ArrayList<Float> a=new ArrayList();
                a.add(0f);
                a.add(0f);
                a.add(0f);
                calcCenters.add(a);
            }
            
            for (int i=0; i<points.size(); i++)
            {
                ArrayList<Float> c=new ArrayList<>();
                float a=calcCenters.get(clusters.get(i)).get(0);
                float b=points.get(i).getKey();
                c.add(a+b);
                c.add(calcCenters.get(clusters.get(i)).get(1)+points.get(i).getValue());
                c.add(calcCenters.get(clusters.get(i)).get(2)+1);
                
                calcCenters.set(clusters.get(i), c);
            }
            for (int i=0; i<k; i++)
            {
                float x=calcCenters.get(i).get(0)/calcCenters.get(i).get(2);
                float y=calcCenters.get(i).get(1)/calcCenters.get(i).get(2);
                
                centers.set(i,new Pair(x,y));
            }
        }
        
        System.out.println("The centers are: ");
        System.out.println(centers);
    
    }
    
}
