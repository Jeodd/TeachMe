package fr.utbm.TeachMe.models;

import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;

import java.io.Serializable;
import java.util.Date;

public class Session implements Serializable {

    //Class properties
    Integer id;
    Date start_date;
    Date end_date; // TODO : verifier que le type correspond bien avec la BDD
    Integer max;
    Integer locationId;
    String courseId;

}
