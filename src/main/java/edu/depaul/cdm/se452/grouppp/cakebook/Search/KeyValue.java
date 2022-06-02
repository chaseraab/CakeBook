package edu.depaul.cdm.se452.grouppp.cakebook.Search;

import java.util.ArrayList;

public class KeyValue {
    public String key;
    public ArrayList<String> value = new ArrayList<>();
 
    public KeyValue (String key, String value) {
        this.key = key;
        this.value.add(value);
    }

}
