package FindTheWay;

import java.io.*;
import java.util.Objects;

/**
 * Created by Владимир on 07.02.2017.
 */
public class ShortestWayFinder {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите длину поля: " );
        Integer length = Integer.parseInt(reader.readLine());
        System.out.print("Введите ширину поля: ");
        Integer width = Integer.parseInt(reader.readLine());
        System.out.print("Введите колчество стенок: ");
        Integer wallCount = Integer.parseInt(reader.readLine());
        String[][] field = new String[width][length];
        Field f = new Field(width, length, wallCount);
        fieldPrint(f.field);
        Integer startLength;
        Integer startWidth;
        while(true){
            System.out.println("Введите кординаты точки начальной точки. Сначала длину потом широту:");
            startLength = Integer.parseInt(reader.readLine());
            startWidth = Integer.parseInt(reader.readLine());
            if (f.field[startWidth][startLength].equals(".")) {
                f.field[startWidth][startLength] = "0";
                break;
            }
        }
        Integer endLength;
        Integer endWidth;
        while(true){
            System.out.println("Введите кординаты точки искомой точки. Сначала длину потом широту:");
            endLength = Integer.parseInt(reader.readLine());
            endWidth = Integer.parseInt(reader.readLine());
            if (f.field[endWidth][endLength].equals(".")) {
                f.field[endWidth][endLength] = "F";
                break;
            }
        }
        for (int w = 0; w < width; w++){
            for(int l = 0; l < length; l++){
                field[w][l] = f.field[w][l];
            }
        }
        waveDisseminator(field, "0", width, length);
        returnWayFinder(field, f.field, width, length);
        fieldPrint(f.field);
    }

    private static void fieldPrint(String[][] field){
        for (String[] mf : field){
            for(String f : mf){
                if(f.length() == 1)
                    System.out.print(f + " ");
                if(f.length() == 2)
                    System.out.print(f);
            }
            System.out.println();
        }
    }

    private static String waveNumber(String str){
        Integer i = Integer.parseInt(str);
        return Integer.toString(i + 1);
    }

    private static void waveDisseminator(String[][] field, String wave, int width, int length){
        while(true){
            for(int w = 0; w < width; w++){
                for(int l = 0; l < length; l++){
                    if (field[w][l].equals(wave)){
                        if(needPointDeterminer(w, l, field) < 5)
                            return;
                        processOfWaveDisseminate(w, l, field, wave);
                    }
                }
            }
            wave = waveNumber(wave);
        }
    }

    private static void processOfWaveDisseminate(int w, int l, String[][] field, String wave){
        wave = waveNumber(wave);
        if(field[w - 1][l].equals(".")){
            field[w - 1][l] = wave;
        }
        if(field[w + 1][l].equals(".")){
            field[w + 1][l] = wave;
        }
        if(field[w][l - 1].equals(".")){
            field[w][l - 1] = wave;
        }
        if(field[w][l + 1].equals(".")){
            field[w][l + 1] = wave;
        }
    }

    private static int needPointDeterminer(int w, int l, String[][] field){
        if(field[w - 1][l].equals("F")){
            return 1;
        }
        if(field[w + 1][l].equals("F")){
            return 2;
        }
        if(field[w][l - 1].equals("F")){
            return 3;
        }
        if(field[w][l + 1].equals("F")){
            return 4;
        }
        else
            return 5;
    }

    private static void returnWayFinder(String[][] field, String[][] mainField, int width, int length){
        for (int w = 0; w < width; w++){
            for (int l = 0; l < length; l++){
                if (field[w][l].equals("F")) {
                    if (!field[w - 1][l].equals("X") && !field[w - 1][l].equals(".")) {
                        processFindingOfReturnWay(field, mainField, w - 1, l);
                        return;
                    }
                    if (!field[w + 1][l].equals("X") && !field[w + 1][l].equals(".")) {
                        processFindingOfReturnWay(field, mainField, w + 1, l);
                        return;
                    }
                    if (!field[w][l - 1].equals("X") && !field[w][l - 1].equals(".")) {
                        processFindingOfReturnWay(field, mainField, w, l - 1);
                        return;
                    }
                    if (!field[w][l + 1].equals("X") && !field[w][l + 1].equals(".")) {
                        processFindingOfReturnWay(field, mainField, w, l + 1);
                        return;
                    }
                }
            }
        }
    }

    private static boolean processFindingOfReturnWay(String[][] field, String[][] mainField, int width, int length) {
        if (mainField[width][length].equals("0")) {
            mainField[width][length] = "S";
            return true;
        }
        else if (!field[width - 1][length].equals("X") && !field[width - 1][length].equals(".") && !field[width - 1][length].equals("F")
                && getWaveNumber(field[width - 1][length]) == getWaveNumber(field[width][length]) - 1) {
            mainField[width][length] = "o";
            return processFindingOfReturnWay(field, mainField, width - 1, length);
        }
        else if (!field[width + 1][length].equals("X") && !field[width + 1][length].equals(".") && !field[width + 1][length].equals("F")
                && getWaveNumber(field[width + 1][length]) == getWaveNumber(field[width][length]) - 1) {
            mainField[width][length] = "o";
            return processFindingOfReturnWay(field, mainField, width + 1, length);
        }
        else if (!field[width][length - 1].equals("X") && !field[width][length - 1].equals(".") && !field[width][length - 1].equals("F")
                && getWaveNumber(field[width][length - 1]) == getWaveNumber(field[width][length]) - 1) {
            mainField[width][length] = "o";
            return processFindingOfReturnWay(field, mainField, width, length - 1);
        }
        else if (!field[width][length + 1].equals("X") && !field[width][length + 1].equals(".") && !field[width][length + 1].equals("F")
                && getWaveNumber(field[width][length + 1]) == getWaveNumber(field[width][length]) - 1) {
            mainField[width][length] = "o";
            return processFindingOfReturnWay(field, mainField, width, length + 1);
        }
        else
            return false;
    }

    private static int getWaveNumber(String str){
        return Integer.parseInt(str);
    }
}
