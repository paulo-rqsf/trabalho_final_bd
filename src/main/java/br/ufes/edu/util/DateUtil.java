package br.ufes.edu.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static Date transformaDataString(String dataNascimento) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(dataNascimento);
    }

    public static Date transformaData(Date dataNascimento) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formatted = formatter.format(dataNascimento);
        return formatter.parse(formatted);
    }

    public static java.sql.Date getDataSql(Date dataNascimento) {
        return new java.sql.Date(dataNascimento.getTime());
    }

    public static Date getDataUtil(java.sql.Date dataNascimento) {
        return new Date(dataNascimento.getTime());
    }
}
