/**
 * Created by es on 2017/9/2.
 */
//package Client;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

public class Client {
    public static void main(String args[]){
        InputStream s1in = null;
        OutputStream s1out = null;
        Scanner keybroad = new Scanner(System.in);
        try{


            while (true){
                Socket s1 = new Socket("127.0.0.1", 1234);
                s1in = s1.getInputStream();
                s1out = s1.getOutputStream();
                DataInputStream dis = new DataInputStream(s1in);
                DataOutputStream dos = new DataOutputStream(s1out);
                System.out.println("enter:");
                boolean check = false;
                String input = null;
                while (!check){
                    input = keybroad.nextLine();
                    check = checkCommand(input);
                }


                    dos.writeUTF(input);


                String str = null;
                if ((str = dis.readUTF()) !=null){

                    System.out.println(str);
                }
                dis.close();
                s1in.close();
                s1.close();
            }


        }catch (UnknownHostException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    private static boolean checkCommand(String command) {
        try {
            String[] commandsplit = command.split(",");
            switch (commandsplit[0]) {
                case "add":
                    if (commandsplit.length == 3) {
                        return true;
                    } else {
                        System.out.println("Invalid input");
                        return false;
                    }
//                    break;
                case "search":
                    if (commandsplit.length == 2) {
                        return true;
                    } else {
                        System.out.println("Invalid input");
                        return false;
                    }
//                    break;
                case "delete":
                    if (commandsplit.length == 2) {
                        return true;
                    } else {
                        System.out.println("Invalid input");
                        return false;
                    }
//                    break;
                case "close":
                    if (commandsplit.length == 1) {
                        System.exit(0);
                        return true;
                    } else {
                        System.out.println("Invalid input");
                        return false;
                    }
//                    break;
                default:
                    System.out.println("Invalid input");
                    return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return false;
        }
    }
}

