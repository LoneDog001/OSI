package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Server started");
        int port = 8195;
        String nameReceived = "";
        String ageReceived = "";
        String surnameReceived = "";

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    {
                        {
                            if (nameReceived.isEmpty() && surnameReceived.isEmpty() && ageReceived.isEmpty()) {
                                nameReceived = in.readLine();
                            } else if (!nameReceived.isEmpty() && surnameReceived.isEmpty() && ageReceived.isEmpty()) {
                                surnameReceived = in.readLine();
                            } else if (!nameReceived.isEmpty() && !surnameReceived.isEmpty() && ageReceived.isEmpty()) {
                                ageReceived = in.readLine();
                                if (ageReceived.equalsIgnoreCase("yes")) {
                                    String welcomeMessage = "Welcome to the kids area,%s %s! Let's play!";
                                    System.out.printf(welcomeMessage, nameReceived, surnameReceived);
                                    System.out.println();
                                    break;
                                } else if (ageReceived.equalsIgnoreCase("no")) {
                                    String welcomeMessage = "Welcome to the adult zone, %s %s! Have a good rest, or a good working day!";
                                    System.out.printf(welcomeMessage, nameReceived, surnameReceived);
                                    System.out.println();
                                    break;
                                } else {
                                    System.out.println("Не правильно введены данные, повторите попытку.");
                                    break;
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Server side error " + e.getMessage());
                }
            }
        }
        System.out.println("Server stopped");
    }
}
