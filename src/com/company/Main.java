package com.company;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Stack;

public class Main {
    
    private static  int fileCount = 0;
    private static  Boolean completed = false;
    private static String[][] map = {{"1","1","0","0"},
            {"0","1","0","0"},
            {"1","1","1","0"},
            {"1","1","0","1"}};
    private static String obstacle;
    private static  String track = "1";
    private static Stack<int[]> route;
    private static ArrayList<int[]> visited;
    private static int column = 0;
    private static int row = 0;

    public static void main(String[] args) {
        route = new Stack<>();
        visited = new ArrayList<>();
        tracker(0,0);
//	    // write your code here
//        var file = new File(System.getenv("TEMP"));
//        //Delete user temporary files
//        System.out.println(tempFileCleaner(file));
//        //Delete System
//        file = new File(System.getenv().getOrDefault("SystemRoot","C:\\Windows")+"\\Temp");
//        System.out.println(tempFileCleaner(file));
//
//        //Recycle bin
//        file = new File(System.getenv().getOrDefault("HOMEDRIVE", "C:")+"\\RECYCLER");
//        System.out.println(file.exists());

    }

    /**
     * This method deletes a temporary file from the system.
     * @param file this is a file object.
     */
    private static Boolean tempFileCleaner(File file){
        if(file.exists()){
            var files = file.listFiles();
            if(files != null){
                fileCount += files.length;
                for (File value : files) {
                    if (value.isDirectory()) {
                        tempFileCleaner(value);
                        completed = value.delete();
                    } else {
                        completed = value.delete();
                    }
                }
            }
        }
        return completed;
    }

    private static void traceRow(){

    }

    /**
     *
     */
    private static void traceColumn(){
        System.out.println("coordinate: column = "+column);
        if(column < map[row].length){
            String value = map[row][column];
            int[] coordinate = {row, column};
            if(!visited.contains(coordinate)){
                if (value.equals(track)){
                    coordinate[0] = row;
                    coordinate[1] = column;
                    route.push(coordinate);
                    visited.add(coordinate);
                    column += 1;
                    traceColumn();
                }else {
                    coordinate[0] = row;
                    coordinate[1] = column;
                    visited.add(coordinate);
                    coordinate = route.pop();
                    tracker(coordinate[0], coordinate[1]);
                }
            }else{
                coordinate[0] = row+1;
                coordinate[1] = column;
                if(!visited.contains(coordinate)){
                    if (row < map.length - 2){
                        tracker(coordinate[0], coordinate[1]);
                    }else{
                        coordinate[0] = row-1;
                        coordinate[1] = column;
                        if (!visited.contains(coordinate)){
                            tracker(coordinate[0], coordinate[1]);
                        }else{
                            coordinate = route.pop();
                            tracker(coordinate[0], coordinate[1]);
                        }

                    }
                }

            }


        }

    }
}
