package exam;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class PhoneManager {
    public PhoneManager() {
    }
    public List<Phone> readFile(){
        List<Phone> phones = new ArrayList<>();
        try(BufferedReader in = new BufferedReader(new FileReader("phones.txt"))){
            Phone p;
            String line;
            String header = in.readLine();
            while((line = in.readLine()) !=null) {
                if(line.trim().isEmpty())
                    continue;
                String[] tokens =line.split(";");
                String id = tokens[0].trim();
                String brand = tokens[1].trim();
                String model = tokens[2].trim();
                String os = tokens[3].trim();
                double price = Double.parseDouble(tokens[4].trim());
                double stock = Double.parseDouble(tokens[5].trim());

                switch (os.toLowerCase())
                {
                    case "android":
                        p = new AndroidPhone(id,brand,model,price,stock,os);
                        break;

                    case "ios":
                        p = new IOSPhone(id,brand,model,price,stock,os);
                        break;

                    default:
                        p = null;
                        System.out.println("Phone os not compatible!\n");
                }
                phones.add(p);
            }
        }catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        return phones;
    }
    public double totalValue(List<Phone> phones)
    {
        return phones.stream().mapToDouble(Phone::totalValue).sum();
    }
    public List<Phone> top2Phones(List<Phone> phones)
    {
        return phones.stream().sorted(Comparator.comparingDouble(Phone::getPrice).reversed())
                .limit(2).collect(Collectors.toList());
    }

    public void writeReport(List<Phone> phones,double totalValue,List<Phone> top2Phones)
    {
        StringBuilder report = new StringBuilder();
        synchronized (this){
            report.append("Total models: ").append(phones.size()).append("\n");
            report.append("Total inventory value: ").append(totalValue).append("\n");
            report.append("Top 2 most expensive phones:\n");
            for(Phone p:top2Phones)
            {
                report.append(p.getId()).append(";").
                        append(p.getBrand()).append(";").
                        append(p.getModel()).append(";").
                        append(p.getPrice()).append("\n");
            }
        }
        try(BufferedWriter out = new BufferedWriter(new FileWriter("report.txt"))){
            out.write(report.toString());
            System.out.println("Report printed succesfully!\n");

        }catch (IOException IOE)
        {
            System.out.println("Couldn't print report!");
            IOE.printStackTrace();
        }
    }
    public void multithreadingTasks(List<Phone> phones){
        ExecutorService executor = Executors.newFixedThreadPool(3);
        try {
            Future<Double> totalFuture = executor.submit(()->totalValue(phones));
            Future<List<Phone>> top2Future = executor.submit(() -> top2Phones(phones));
            executor.submit(()-> {
                try{
                    double sum = totalFuture.get();
                    List<Phone> top2 = top2Future.get();

                    this.writeReport(phones,sum,top2);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            });
        }finally {
            executor.shutdown();
        }
    }
}
