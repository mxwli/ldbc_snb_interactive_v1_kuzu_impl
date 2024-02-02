package org.ldbcouncil.snb.impls.workloads.kuzu.operationhandlers;

import org.ldbcouncil.snb.driver.DbException;
import org.ldbcouncil.snb.driver.Operation;
import org.ldbcouncil.snb.driver.ResultReporter;
import org.ldbcouncil.snb.driver.workloads.interactive.LdbcNoResult;
import org.ldbcouncil.snb.impls.workloads.kuzu.KuzuDbConnectionState;
import org.ldbcouncil.snb.impls.workloads.operationhandlers.UpdateOperationHandler;

import java.util.Map;

import com.kuzudb.*;

public abstract class KuzuUpdateOperationHandler<TOperation extends Operation<LdbcNoResult>>
        implements UpdateOperationHandler<TOperation,KuzuDbConnectionState>
{
    @Override
    public String getQueryString( KuzuDbConnectionState state, TOperation operation )
    {
        return null;
    }

    public abstract Map<String, Object> getParameters( KuzuDbConnectionState state,TOperation operation );


    @Override
    public void executeOperation( TOperation operation, KuzuDbConnectionState state,
                                  ResultReporter resultReporter ) throws DbException
    {
        String query = getQueryString(state, operation);
        final Map<String, Object> parameters = getParameters( state, operation );
        for (String i : parameters.keySet()) {
            query = query.replace(i, (String)parameters.get(i));
        }

        KuzuConnection connection = state.getConnection();
        try {
            KuzuQueryResult result = connection.query(query);
        } catch (KuzuObjectRefDestroyedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(query);
        }

        resultReporter.report( 0, LdbcNoResult.INSTANCE, operation );
    }
}
