package edu.chalmers.grapefruit.Model.Json;

import com.google.gson.Gson;
import java.io.InputStream;
import java.util.Scanner;

public class JsonHandler {
   private JsonMap map;
   private static Gson gson = new Gson();

   public JsonHandler(String file){
      Scanner s = new Scanner(getJSONFile(file)).useDelimiter("\\A");
      String result = s.hasNext() ? s.next() : "";
      readJson(result);
   }

   private void readJson(String from){
      map = gson.fromJson(from, JsonMap.class);
   }

   public JsonMap getJsonMap(){
      return map;
   }

   /**
    * Returns the stream that holds the needed json file which sets up the game's board.
    * Finds the json file using the right path.
    * If the json file couldn't be found, the method will throw an IllegalArgumentException.
    * @return the stream that holds the json file "board.json"
    */
   private InputStream getJSONFile(String from) {
      ClassLoader classLoader = getClass().getClassLoader();
      InputStream inputStream = classLoader.getResourceAsStream(from);

      // the stream holding the file content
      if (inputStream == null) {
         throw new IllegalArgumentException("JSON file not found!");
      } else {
         return inputStream;
      }
   }
}
