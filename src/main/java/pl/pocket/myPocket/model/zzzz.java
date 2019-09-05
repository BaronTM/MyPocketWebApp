package pl.pocket.myPocket.model;

import java.util.HashMap;
import java.util.Map;

public class zzzz {

    public static void main(String[] args) {
        Map map = new HashMap<String, String>();
        map.put("ola", "kot");
        map.put("romek", "pies");
        map.put("ola", "pies");
        System.out.println(map);
    }
}
