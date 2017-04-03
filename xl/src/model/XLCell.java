package model;

import expr.Environment;

/**
 * Created by cx on 2017-04-02.
 */
// Define what we need XLCell to do:
public interface XLCell<Data> {
    double getValue(Data env);  // get the double real-valued value that XLCell can refer to
    String rawData();           // get the raw data that the cell refers to, i.e "A14=#hello this is a string" for example.
    String interpretedData(Data env); //
}
