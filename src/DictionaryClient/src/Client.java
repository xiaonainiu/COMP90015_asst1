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
    static Socket s1;
    static DataInputStream dis;
    static DataOutputStream dos;
    public static void main(String args[]){
        DictionaryWindow gui = new DictionaryWindow();
//        gui.start();
        try{
            while (true){
                s1 = new Socket("127.0.0.1", 1234);
                dis = new DataInputStream(s1.getInputStream());
                dos = new DataOutputStream(s1.getOutputStream());
                String str = null;
                if ((str = dis.readUTF()) !=null){
                    System.out.println(str);
//                    System.out.println("**before append**");
//                    gui.outputWord.setText("");
//                    gui.outputWord.append(str);
                    gui.outputWord.setText(str);
//                    System.out.println("**after append**");
                }
                dis.close();
                s1.close();
            }
        }catch (UnknownHostException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static boolean checkCommand(String command) {
        try {
//            System.out.println(command);
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

