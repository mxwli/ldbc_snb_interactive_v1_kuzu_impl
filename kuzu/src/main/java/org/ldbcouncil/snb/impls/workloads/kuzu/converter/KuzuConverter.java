package org.ldbcouncil.snb.impls.workloads.kuzu.converter;

import org.ldbcouncil.snb.driver.workloads.interactive.LdbcQuery1Result;
import org.ldbcouncil.snb.driver.workloads.interactive.LdbcUpdate1AddPerson;
import org.ldbcouncil.snb.impls.workloads.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

public class KuzuConverter extends Converter {

    final static String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'+0000'";
    final static String DATE_FORMAT = "yyyy-MM-dd";

    @Override
    public String convertDateTime(Date date) {
        //final SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT);
        //sdf.setTimeZone(TimeZone.getTimeZone("Etc/GMT+0"));
        return ""+date.getTime();
    }

    @Override
    public String convertDate(Date date) {
        //final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        //sdf.setTimeZone(TimeZone.getTimeZone("Etc/GMT+0"));
        return ""+date.getTime();
    }

    public String convertOrganisations(List<LdbcUpdate1AddPerson.Organization> values) {
        String res = "[";
        res += values
                .stream()
                .map(v -> "[" + v.getOrganizationId() + ", " + v.getYear() + "]")
                .collect(Collectors.joining(", "));
        res += "]";
        return res;
    }

    public static List<LdbcQuery1Result.Organization> asOrganization(List<List<Object>> value){
        List<LdbcQuery1Result.Organization> orgs = new ArrayList<>();
        for (List<Object> list : value) {
            orgs.add(new LdbcQuery1Result.Organization((String)list.get(0), ((Long) list.get(1)).intValue(), (String)list.get(2)));
        }
        return orgs;
    }
}
