package br.ufes.edu.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static Date transformaData(String dataNascimento) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(dataNascimento);
    }
}
