package edu.chalmers.grapefruit.Model.Json;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.io.InputStream;
import java.util.Scanner;

/**
 * This class uses Gson for converting a Json String into a Java "game board map" object.
 *
 * @author Elvina Fahlgren
 */
public class JsonHandler {
   private JsonBoardReader boardReader;
   private static Gson gson = new Gson();

   /**
    * @param filePath is the filepath to the json file
    */
   public JsonHandler(String filePath){
      readJson(filePath);
   }

   /**
    * Reads filePath and parses json string.
    * @param filePath is the filepath to the json file
    */
   private void readJson(String filePath){
      Scanner s = new Scanner(getJSONFile(filePath)).useDelimiter("\\A");
      String result = s.hasNext() ? s.next() : "";
      try {
         boardReader = gson.fromJson(result, JsonBoardReader.class);
      }
      catch (JsonParseException e) {
         throw new IllegalArgumentException("Incorrect json format!");
      }

      if (boardReader.Neighbours.size() != boardReader.PositionList.size())
         throw new IllegalArgumentException("Neighbour list and PositionList in json file doesn't match!");
   }

   public JsonBoardReader getJsonBoardReader(){
      return boardReader;
   }

   /**
    * Returns the stream that holds the json file, which sets up the game's board.
    * Finds the json file by using the right path.
    * If the json file couldn't be found, the method will throw an IllegalArgumentException.
    * @return the stream that holds the json file
    */
   private InputStream getJSONFile(String filePath) {
      ClassLoader classLoader = getClass().getClassLoader();
      InputStream inputStream = classLoader.getResourceAsStream(filePath);

      // the stream holding the file content
      if (inputStream == null) {
         throw new IllegalArgumentException("JSON file not found!");
      } else {
         return inputStream;
      }
   }
}