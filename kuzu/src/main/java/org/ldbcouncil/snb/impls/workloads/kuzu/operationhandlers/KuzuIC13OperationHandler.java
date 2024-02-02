package org.ldbcouncil.snb.impls.workloads.kuzu.operationhandlers;
/**
 * KuzuIC13OperationHandler.java
 * 
 * This class handles LdbcQuery13 operation and result. It is almost the same as the
 * @see @link {KuzuSingletonOperationHandler}, but the result in case of zero 
 * results is different: instead of null it returns an object with an result -1.
 */
import org.ldbcouncil.snb.driver.DbException;
import org.ldbcouncil.snb.driver.ResultReporter;
import org.ldbcouncil.snb.driver.workloads.interactive.LdbcQuery13;
import org.ldbcouncil.snb.driver.workloads.interactive.LdbcQuery13Result;
import org.ldbcouncil.snb.impls.workloads.kuzu.KuzuDbConnectionState;
import org.ldbcouncil.snb.impls.workloads.operationhandlers.SingletonOperationHandler;

import java.text.ParseException;
import java.util.Map;

import com.kuzudb.*;

public abstract class KuzuIC13OperationHandler
        implements SingletonOperationHandler<LdbcQuery13Result, LdbcQuery13, KuzuDbConnectionState>
{
    public abstract LdbcQuery13Result toResult( KuzuFlatTuple record ) throws ParseException, KuzuObjectRefDestroyedException;

    public abstract Map<String, Object> getParameters(KuzuDbConnectionState state, LdbcQuery13 operation );

    @Override
    public void executeOperation( LdbcQuery13 operation, KuzuDbConnectionState state,
                                  ResultReporter resultReporter ) throws DbException
    {
        String query = getQueryString(state, operation);
        final Map<String, Object> parameters = getParameters(state, operation );
        for(String i: parameters.keySet()) {
            query = query.replace("$"+i, (String)parameters.get(i));
        }
        try {
            final KuzuConnection connection = state.getConnection();
            final KuzuQueryResult result = connection.query(query);
            if (result.hasNext()) {
                try {
                    resultReporter.report(1, toResult(result.getNext()), operation);
                } catch (ParseException e) {
                    throw new DbException(e);
                }
            } else {
                resultReporter.report(1, new LdbcQuery13Result(-1), operation);
            }
        } catch (KuzuObjectRefDestroyedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(query);
        }
    }
}