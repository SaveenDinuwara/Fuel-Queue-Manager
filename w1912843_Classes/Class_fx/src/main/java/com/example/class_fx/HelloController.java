package com.example.class_fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.FileReader;
import java.util.Scanner;

public class HelloController {


    @FXML
    public TextArea txtarea;


    @FXML
    public TextArea txtarea2;

    @FXML
    public TextField txtfield;

    @FXML
    protected void click(){
        try{
            FileReader read = new FileReader("NewFile.txt");
            int data = read.read();
            while(data != -1){
                txtarea.appendText(String.valueOf((char)data));
                data=read.read();

            }
            read.close();
        }catch(Exception e){
            System.out.println("Can't find the file");
        }

    }

    @FXML
    protected void search(){
        String[][][] lists = new String[5][6][4];
        try{
            FileReader reader = new FileReader("NewFile.txt");
            Scanner cat = new Scanner(reader);
            for(int i=0;i<5;i++){
                for (int j=0;j<6;j++){
                    lists[i][j][0]=cat.nextLine();
                    lists[i][j][1]=cat.nextLine();
                    lists[i][j][2]=cat.nextLine();
                    lists[i][j][3]=cat.nextLine();

                }
            }
            reader.close();

            for(int k =0; k< lists.length; k++){
                for(int s=0;s<6;s++) {
                    if (lists[k][s][0].equals(txtfield.getText())) {
                        txtarea2.appendText(lists[k][s][0]+"\n");
                        txtarea2.appendText(lists[k][s][1]+"\n");
                        txtarea2.appendText(lists[k][s][2]+"\n");
                        txtarea2.appendText(lists[k][s][3]+"\n");
                    }
                }

            }

        }catch(Exception e){
            System.out.println("Can't find the file");
        }
    }

    }

