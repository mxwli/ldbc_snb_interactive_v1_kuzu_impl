package org.ldbcouncil.snb.impls.workloads.kuzu.operationhandlers;

import org.ldbcouncil.snb.driver.DbException;
import org.ldbcouncil.snb.driver.Operation;
import org.ldbcouncil.snb.driver.ResultReporter;
import org.ldbcouncil.snb.impls.workloads.kuzu.KuzuDbConnectionState;
import org.ldbcouncil.snb.impls.workloads.operationhandlers.SingletonOperationHandler;

import java.text.ParseException;
import java.util.Map;

import com.kuzudb.*;

public abstract class KuzuSingletonOperationHandler<TOperation extends Operation<TOperationResult>, TOperationResult>
        implements SingletonOperationHandler<TOperationResult,TOperation,KuzuDbConnectionState>
{
    public abstract TOperationResult toResult( KuzuFlatTuple record ) throws ParseException, KuzuObjectRefDestroyedException;

    public abstract Map<String, Object> getParameters(KuzuDbConnectionState state, TOperation operation );

    @Override
    public void executeOperation( TOperation operation, KuzuDbConnectionState state,
                                  ResultReporter resultReporter ) throws DbException
    {
        String query = getQueryString(state, operation);
        final Map<String, Object> parameters = getParameters(state, operation );
        for(String i: parameters.keySet()) {
            query = query.replace("$"+i, (String)parameters.get(i));
        }
        try {
            final KuzuConnection connection = state.getConnection();
            KuzuQueryResult result = connection.query(query);
            if (result.hasNext()) {
                try {
                    resultReporter.report(1, toResult (result.getNext()), operation);
                } catch (ParseException e) {
                    throw new DbException(e);
                }
            } else {
                resultReporter.report(0, null, operation);
            }
        } catch (KuzuObjectRefDestroyedException e) {
            e.printStackTrace();
            System.out.println(query);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(query);
        }

    }
}
