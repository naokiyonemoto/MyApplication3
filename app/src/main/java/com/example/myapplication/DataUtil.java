package com.example.myapplication;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class DataUtil {

    private final static String FILE_NAME = "updatePerson.obj";

    /*
    *データ保存
    * @param context
    * @param object 保存するオブジェクト
     */

    public static void save(Context context, Serializable object){
        try{
            ObjectOutputStream output = new ObjectOutputStream(context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE));
            output.writeObject(object);
            output.close();

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /*
    *データの読み込み
    * @param context
    * @return 保存しているデータ　無い場合はNULL
     */

    public static Object load(Context context){
        Object returnObject = null;
        try{
            ObjectInputStream input = new ObjectInputStream(context.openFileInput(FILE_NAME));
            returnObject = input.readObject();
            input.close();
        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        return returnObject;
    }

}
