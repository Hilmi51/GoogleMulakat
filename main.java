import java.lang.reflect.Array;
import java.util.ArrayList;

public class main {

    public static boolean arraylistContains(int[] islandCoordinates, ArrayList<int[]> islands){
        for(int[] island: islands) {
            if(island[0] == islandCoordinates[0] && island[1] == islandCoordinates[1])
                return true;
        }
        return false;
    }

    public static ArrayList<int[]> findBorders(int[][] array, ArrayList<int[]> islands) {
        ArrayList<int[]> borders = new ArrayList<>();

        // 1s in the first row
        for(int i = 0; i < array[0].length; i++) {
            if(array[0][i] == 1) {
                int[] border = {0,i};
                borders.add(border);
                islands.add(border);
            }
        }

        // 1s in last row
        for(int i = 0; i < array[array.length - 1].length; i++) {
            if(array[array.length-1][i] == 1) {
                int[] border = {array.length-1, i};
                borders.add(border);
                islands.add(border);
            }
        }

        // 1s in left column
        for(int i = 1; i < array.length - 1; i++){
            if(array[i][0] == 1) {
                int[] border = {i,0};
                borders.add(border);
                islands.add(border);
            }
        }

        // 1s in the right column
        for(int i = 1; i < array.length - 1; i++) {
            if(array[i][array.length - 1] == 1) {
                int[] border = {i, array.length - 1};
                borders.add(border);
                islands.add(border);
            }
        }
        return borders;
    }

    public static void findPath(int x, int y, int[][] array, ArrayList<int[]> islands) {
        // if the above element is 1
        if(x > 0 && array[x-1][y] == 1) {
            int[] point = { x-1, y };
            if(!arraylistContains(point,islands)) {
                islands.add(point);
                findPath(x-1, y, array, islands);
            }
        }
        // if the following element is 1
        if(x < array.length - 1 && array[x+1][y] == 1) {
            int[] point = { x+1, y };
            if(!arraylistContains(point,islands)) {
                islands.add(point);
                findPath(x+1, y, array, islands);
            }
        }
        // if the element on the left is 1
        if(y > 0 && array[x][y-1] == 1) {
            int[] point = { x, y-1 };
            if(!arraylistContains(point,islands)) {
                islands.add(point);
                findPath(x, y-1, array, islands);
            }
        }
        // if the element on the right is 1
        if(y < array[x].length - 1 && array[x][y+1] == 1) {
            int[] point = { x, y+1 };
            if(!arraylistContains(point,islands)) {
                islands.add(point);
                findPath(x, y+1, array, islands);
            }
        }
    }

    public static int[][] removeIslands(int[][] array) {
        // arraylist that will hold the coordinates of all 1's that will not be deleted
        ArrayList<int[]> islands = new ArrayList<>();
        // arraylist that will hold the coordinates of the 1s in the bounds
        ArrayList<int[]> borders = findBorders(array,islands);

        for(int[] border: borders) {
            findPath(border[0], border[1], array, islands);
        }

        int[][] result = new int[array.length][array[0].length];
        for(int[] island: islands) {
            result[island[0]][island[1]] = 1;
        }

        return result;

    }

    public static void main(String[] args) {
        int[][] island = {
            {1,0,0,0,0,0},
            {0,1,0,1,1,1},
            {0,0,1,0,1,0},
            {1,1,0,0,1,0},
            {1,0,1,1,0,0},
            {1,0,0,0,0,1}
        };

        int[][] result = removeIslands(island);

        for (int[] ints : result) {
            for (int anInt : ints) {
                System.out.print(anInt);
            }
            System.out.println();
        }

    }
}