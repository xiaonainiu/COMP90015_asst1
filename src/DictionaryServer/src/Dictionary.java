/**
 * Created by es on 2017/9/2.
 */

import java.util.concurrent.*;
import java.util.*;
public class Dictionary {
    ConcurrentHashMap<String,String> chm;

    public Dictionary (String text) {
        chm = toHashMap(text);
    }

    public Boolean checkIfexsist(String targetWord){
        return chm.containsKey(targetWord);
    }

    public boolean addWord(String word,String explanation){
        if (checkIfexsist(word)){
            return false;
        }else {
            chm.put(word,explanation);
//            System.out.println("**in func addWord**");
//            System.out.println("chm is:"+toString(chm));
//            System.out.println("**out func addWord**");
            return true;
        }
    }

    public boolean deleteWord(String word){
        if (checkIfexsist(word)){
            chm.remove(word);
            return true;
        }else {
            return false;
        }
    }

    public String searchWord(String word){
        if (checkIfexsist(word)){
            String explanation = chm.get(word);
            return explanation;
        }else {
            return null;
        }
    }

    public String getUpdateFile(){
        return toString(chm);
    }

    private ConcurrentHashMap toHashMap(String file){
        ConcurrentHashMap<String,String> dic = new ConcurrentHashMap<String,String>();

        try{
            String[] file_element = file.split(",");
            if (file_element.length > 1){
                for (int i=0;i<file_element.length;i=i+2){
                    dic.put(file_element[i],file_element[i+1]);
            }
//            System.out.print("file element length: "+file_element.length);

            }
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.print("**error in load dictionary**");
            e.printStackTrace();
        }



        return dic;
    }

    private String toString (ConcurrentHashMap dic){
        String file = new String();
//        String newfile = new String();
        Set<String> keys = dic.keySet();
//        System.out.println("**in func toString**");
//        System.out.println("The keys set is :"+keys);
        for (String key : keys){

            String addtofile = key + "," + dic.get(key) + ",";
//            System.out.println("add to file: "+addtofile);
            file = file.concat(addtofile);
//            System.out.println("after added:"+file);
        }
//        System.out.println("in toString: "+ file);
//        System.out.println("**out func toString**");
        return file;
    }

}
