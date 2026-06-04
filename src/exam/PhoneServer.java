package exam;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.stream.Collectors;

public class PhoneServer {
    public PhoneServer() {
    }

    private static String formatPhone(Phone p)
    {
        return p.getId()+";"+p.getBrand()+";"+p.getModel()+";"+p.getPrice();
    }
    public void startServer(List<Phone> phones)
    {
        try(ServerSocket serverSocket = new ServerSocket(8080)) {

            System.out.println("The server is on and listening to port 8080\n");
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    String request = in.readLine();
                    if (request == null) continue;

                    System.out.println("Recieved request: "+request);
                    String answer = "";

                    if (request.equalsIgnoreCase("TOP 2")){
                        answer = phones.stream().sorted((p1,p2)
                                ->Double.compare(p1.getPrice(),p2.getPrice())).limit(2)
                                .map(PhoneServer::formatPhone).collect(Collectors.joining(" | "));
                    }else if(request.toUpperCase().startsWith("FILTER"))
                    {
                        String[] tokens = request.split(" ");
                        if(tokens.length > 1)
                        {
                            String opSys = tokens[1];
                            answer = phones.stream().filter(p ->p.getOs().equalsIgnoreCase(opSys))
                                    .map(PhoneServer::formatPhone).collect(Collectors.joining(" | "));
                        }else answer = "Operating system is not in list\n";
                    }
                    else if (request.equalsIgnoreCase("TOTAL"))
                    {
                        double total = phones.stream().mapToDouble(Phone::getPrice).sum();
                        answer = "Total price of inventory: " + total;
                    }
                    else {
                        answer = "Invalid command!";
                    }
                    out.println(answer);

                }catch (IOException ioe)
                {
                    ioe.printStackTrace();
                }
            }
        }catch(IOException IOE)
        {
            IOE.printStackTrace();
        }
    }
}
