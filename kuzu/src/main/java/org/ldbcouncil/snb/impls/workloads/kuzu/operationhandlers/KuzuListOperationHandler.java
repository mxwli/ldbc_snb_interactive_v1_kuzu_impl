package org.ldbcouncil.snb.impls.workloads.kuzu.operationhandlers;

import org.ldbcouncil.snb.driver.DbException;
import org.ldbcouncil.snb.driver.Operation;
import org.ldbcouncil.snb.driver.ResultReporter;
import org.ldbcouncil.snb.impls.workloads.kuzu.KuzuDbConnectionState;
import org.ldbcouncil.snb.impls.workloads.operationhandlers.ListOperationHandler;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.kuzudb.*;

public abstract class KuzuListOperationHandler<TOperation extends Operation<List<TOperationResult>>, TOperationResult>
        implements ListOperationHandler<TOperationResult,TOperation,KuzuDbConnectionState>
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
            final KuzuQueryResult result = connection.query(query);
            final List<TOperationResult> results = new ArrayList<>();
            while (result.hasNext()) {
                final KuzuFlatTuple next = result.getNext();
                try {
                    results.add(toResult(next));
                } catch (ParseException e) {
                    throw new DbException();
                }
            }
            resultReporter.report(results.size(), results, operation);
        } catch (KuzuObjectRefDestroyedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(query);
        }
    }
}
