/**
 * Created by es on 2017/9/2.
 */
//package Client;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
//import DictionaryClient.src.*;
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
                    gui.outputWord.setText(str);
                }
                dis.close();
                s1.close();
            }
        }catch (UnknownHostException e){
            e.printStackTrace();
        }catch (SocketException e){
            gui.outputWord.setText("Server has been closed");
            gui.unable();
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
                case "search":
                    if (commandsplit.length == 2) {
                        return true;
                    } else {
                        System.out.println("Invalid input");
                        return false;
                    }
                case "delete":
                    if (commandsplit.length == 2) {
                        return true;
                    } else {
                        System.out.println("Invalid input");
                        return false;
                    }
                default:
                    System.out.println("Invalid input");
                    return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void close(){
        System.exit(0);
    }
}

