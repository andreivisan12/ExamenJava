package exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PhoneClient {
    public static void main(String[] args)
    {
        String serverAddress = "127.0.0.1";
        int port = 8080;

        String requestSent = "FILTER iOS";
        try (Socket socket = new Socket(serverAddress,port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))){

            System.out.println("Sent Request: "+requestSent);
            out.println(requestSent);

            String serverResponse = in.readLine();
            System.out.println("Server: "+serverResponse);

        }catch (IOException ioException)
        {
            ioException.printStackTrace();
        }
    }
}
