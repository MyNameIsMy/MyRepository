package FindTheWay;

import java.util.Random;

/**
 * Created by Владимир on 06.02.2017.
 */
public class Field {
    String[][] field;
    private final String wallMarker = "X";

    Field(int width, int length, int wallCount){
        field = new String[width][length];
        emptyField(field, width, length);
        makeWall(field, width, length, wallCount);
    }

    private void emptyField(String[][] field, int width, int length) {
        for (int i = 0; i < width; i++){
            for (int j = 0; j < length; j++){
                field[i][j] = ".";
            }
        }
    }

    private void makeWall(String[][] field, int width, int length, int wallCount){
        Random random = new Random();
        for (int i = 0; i < wallCount; i++){
            int direction = random.nextInt(4);
            int wallLength;
            if (width > length)
                wallLength = random.nextInt(length -1);
            else
                wallLength = random.nextInt(width - 1);
            int Oy = 1 + random.nextInt(width - 2);
            int Ox = 1 + random.nextInt(length - 2);
            switch(direction) {
                case 0:
                    for (int j = 0; j < wallLength; j++){
                        field[Oy][Ox] = wallMarker;
                        Oy--;
                        if (Oy == 0)
                            break;
                    }
                case 1:
                    for (int j = 0; j < wallLength; j++){
                        field[Oy][Ox] = wallMarker;
                        Ox++;
                        if (Ox == length - 1)
                            break;
                    }
                case 2:
                    for (int j = 0; j < wallLength; j++){
                        field[Oy][Ox] = wallMarker;
                        Oy++;
                        if (Oy == width - 1)
                            break;
                    }
                default:
                    for (int j = 0; j < wallLength; j++){
                        field[Oy][Ox] = wallMarker;
                        Ox--;
                        if (Ox == 0)
                            break;
                    }
            }
        }
        for (int i1 = 0; i1 < width; i1++){
            for(int i2 = 0; i2 < length; i2++){
                if (i1 == 0 || i2 == 0 || i1 == width - 1 || i2 == length - 1)
                    field[i1][i2] = wallMarker;
            }
        }
    }
}
