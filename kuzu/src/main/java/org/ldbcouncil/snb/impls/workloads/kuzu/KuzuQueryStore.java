package org.ldbcouncil.snb.impls.workloads.kuzu;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

import com.google.common.collect.ImmutableMap;

import org.ldbcouncil.snb.driver.DbException;
import org.ldbcouncil.snb.driver.workloads.interactive.*;
import org.ldbcouncil.snb.impls.workloads.QueryStore;
import org.ldbcouncil.snb.impls.workloads.converter.Converter;
import org.ldbcouncil.snb.impls.workloads.kuzu.converter.KuzuConverter;

public class KuzuQueryStore extends QueryStore
{
    public KuzuQueryStore( String path )  throws DbException
    {
        super(path, ".cypher");
    }

    protected Converter getConverter() {
        return new KuzuConverter();
    }

    static protected Date addDays( Date startDate, int days )
    {
        final Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        cal.setTime( startDate );
        cal.add( Calendar.DATE, days );
        return cal.getTime();
    }
}
