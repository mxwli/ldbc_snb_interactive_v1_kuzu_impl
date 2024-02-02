package org.ldbcouncil.snb.impls.workloads.kuzu;

import org.ldbcouncil.snb.impls.workloads.BaseDbConnectionState;
import org.ldbcouncil.snb.impls.workloads.QueryStore;

import java.io.IOException;
import java.util.Map;

import com.kuzudb.*;

public class KuzuDbConnectionState<TDbQueryStore extends QueryStore> extends BaseDbConnectionState<TDbQueryStore>
{
    protected final KuzuDatabase database;
    protected final KuzuConnection connection;

    public KuzuDbConnectionState( Map<String, String> properties, TDbQueryStore store ) {
        super(properties, store);

        final String location = properties.get( "kuzudblocation" );
        
        database = new KuzuDatabase(location);
        connection = new KuzuConnection(database);
    }

    public KuzuConnection getConnection() {return connection;}

    @Override
    public void close() throws IOException
    {
        try {
        database.destroy();
        } catch(KuzuObjectRefDestroyedException e) {
            throw new IOException("database already destroyed!");
        }
    }
}
