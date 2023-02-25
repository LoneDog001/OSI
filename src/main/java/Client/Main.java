package Client;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        String host = "127.0.0.1";
        int port = 8195;
        String message;
        boolean isNameEntered = false;
        boolean isAgeEntered = false;
        boolean isSurnameEntered = false;

        while (true) {
            try (Socket clientSocket = new Socket(host, port);
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
            ) {
                if (!isNameEntered) {
                    System.out.println("Write your name");
                    message = scr.nextLine();
                    isNameEntered = true;
                } else if (!isSurnameEntered) {
                    System.out.println("Write your surname");
                    message = scr.nextLine();
                    isSurnameEntered = true;
                } else if (!isAgeEntered) {
                    System.out.println("Are you child? (yes/no)");
                    message = scr.nextLine();
                    isAgeEntered = true;
                } else {
                    System.out.println("Thank you for you answers");
                    break;
                }
                out.println(message);
            } catch (Exception e) {
                System.out.println("Client side error " + e.getMessage());
            }
        }
    }
}




