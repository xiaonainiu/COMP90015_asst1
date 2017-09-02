/**
 * Created by es on 2017/9/2.
 */
//package Server;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


import javax.net.ServerSocketFactory;

public class Server {
    public static void main (String arg[]){

        System.out.println("**Server Start**");
        Server server = new Server();
        String fileName = "dictionary.txt";
        File fileObject = new File(fileName);
        Dictionary dictionary = null;


        InputStream s1in = null;
        OutputStream s1out = null;
        try {
            //Register service on port 1234
            ServerSocket ss = new ServerSocket(1234);

            while (true){
                try{
                    Socket s1 = ss.accept(); // wait and accept a connection

                    s1out = s1.getOutputStream();
                    s1in = s1.getInputStream();
                    DataOutputStream dos = new DataOutputStream(s1out);
                    DataInputStream dis = new DataInputStream(s1in);
                    String inputStr = null;
                    String outputStr = null;
                    if ((inputStr = dis.readUTF())!=null){
//                        Thread connection = new Thread(new MyTread());
//                        connection.start();

                        System.out.println("From Client"+ s1.getInetAddress()+s1.getPort()+inputStr);
                        try{

                            //initialize dictionary
                            if (server.checkFile(fileObject)){
                                dictionary = new Dictionary(server.read());
                            }else {
                                dictionary = new Dictionary("");
                            }


                            String[] commandsplit = inputStr.split(",");
                            switch (commandsplit[0]){
                                case "add":
                                    boolean addSuccess = false;
                                    addSuccess = dictionary.addWord(commandsplit[1],commandsplit[2]);
//                                    System.out.println("in Server, addSuccess is : "+ addSuccess);
                                    if (addSuccess){
                                        server.write(dictionary.getUpdateFile());
                                        outputStr = "Add success";
                                    }else {
                                        outputStr = "The word is already exist";
                                    }

                                    break;
                                case "search":
                                    String explanation = dictionary.searchWord(commandsplit[1]);
                                    if (explanation != null){
                                        outputStr = "The explanation of is: "+explanation;
                                    }else {
                                        outputStr = "The word is not exist";
                                    }

                                    break;
                                case "delete":
                                    boolean deleteSuccess = dictionary.deleteWord(commandsplit[1]);
                                    if (deleteSuccess){
                                        server.write(dictionary.getUpdateFile());
                                        outputStr = "Delete success";
                                    }else {
                                        outputStr = "The word is not exist";
                                    }

                                    break;
                                default:
                                    outputStr = "Invalid command";
                                    System.out.println("User's command is invalid");
                            }
                        }catch (ArrayIndexOutOfBoundsException e){
                            e.printStackTrace();
                        }
                    }
                    dos.writeUTF("Hi there, your input is: "+ inputStr+"\n"+ "feedback is: "+outputStr);
                    dos.close();
                    s1out.close();
                    s1.close();
                }catch (EOFException e){
                    System.out.println("Client has closed");
                }

            }
        }catch (IOException e) {
//            server.write(dictionary.getUpdateFile());
            System.out.println("dictionary has been update");
            e.printStackTrace();
        }
    }

    private boolean checkFile (File file){
        if (file.exists()){
            return true;
        }else {
            return false;
        }
    }

    private String read() {

        String b = null;

        try {
            ObjectInputStream inputStream =
                    new ObjectInputStream(new FileInputStream("dictionary.txt"));
            b = (String) inputStream.readObject();
            inputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file dictionary.txt");
            System.exit(0);
        } catch (ClassNotFoundException e) {
            System.out.println("Problems with file input.");
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Problems with file input.");
            System.exit(0);
        }
        return b;
    }
    private void write(String a) {

        try {
            ObjectOutputStream outputStream =
                    new ObjectOutputStream(new FileOutputStream("dictionary.txt"));
            outputStream.writeObject(a);
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Error writing to file.");
            System.exit(0);
        }
    }

//    static class MyTread implements Runnable{
//        public void run(){
//            System.out.println("this thread is running");
//        }
//    }

}

