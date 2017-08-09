/*IntelliJ IDEA 2017.1.2
        Build #IC-171.4249.39, built on April 25, 2017
        JRE: 1.8.0_112-release-736-b16 amd64
        JVM: OpenJDK 64-Bit Server VM by JetBrains s.r.o
        Windows 10 10.0*/
package com.thumbtack_2017;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        if(!new File("input.txt").exists()){ //проверка на существование входного файла
            write("Нет входного файла \"input.txt\"");
            return;
        }

        List<String> lines = Files.readAllLines(Paths.get("input.txt"),
                Charset.forName("cp1251")); //чтение данных из файла в стандартной кодировке Windows

        if(lines.size()<2){ //если предложений меньше 2-х общие слова отсутствуют
            write("отсутствует");
            return;
        }

        String string_1=lines.get(0); //первое предложение
        string_1=string_1.replace('.',' '); //замена точек и запятых на пробелы
        string_1=string_1.replace(',',' ');

        String string_2=lines.get(1); //второеое предложение
        string_2=string_2.replace('.',' ');
        string_2=string_2.replace(',',' ');

        String[] words_1=string_1.split(" "); //разделение предложения на слова
        String[] words_2=string_2.split(" ");

        int max_word_length=0; //максимальная длина общего слова
        int max_word_index=0; //индекс общего слова максимальной длины

        for(int i=0;i<words_1.length;i++){
            for(int j=0;j<words_2.length;j++){
                if(words_1[i].equalsIgnoreCase(words_2[j])){ //проверка на общее слово
                    if(words_1[i].length()>max_word_length){ //проверка длины общего слова
                        max_word_length=words_1[i].length();
                        max_word_index=i;
                    }
                }
            }
        }
        if(max_word_length!=0){ //если есть общее слово
            write(words_1[max_word_index]);
        }else {
            write("отсутствует");
        }
    }

    private static void write(String string) throws IOException { //метод для записи в файл
        FileWriter output_file=new FileWriter("output.txt");
        output_file.write(string);
        output_file.flush();
        output_file.close();
    }
}