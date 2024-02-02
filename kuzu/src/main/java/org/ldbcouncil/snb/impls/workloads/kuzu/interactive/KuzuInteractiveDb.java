package org.ldbcouncil.snb.impls.workloads.kuzu.interactive;

import org.ldbcouncil.snb.driver.DbException;
import org.ldbcouncil.snb.driver.control.LoggingService;
import org.ldbcouncil.snb.driver.workloads.interactive.LdbcQuery1;
import org.ldbcouncil.snb.driver.workloads.interactive.LdbcQuery10;
import org.ldbcouncil.snb.driver.workloads.interactive.LdbcQuery11;
import org.ldbcouncil.snb.driver.workloads.interactive.LdbcQuery12;
import org.ldbcouncil.snb.driver.workloads.interactive.LdbcQuery13;
import org.ldbcouncil.snb.driver.workloads.interactive.LdbcQuery14;
import org.ldbcouncil.snb.driver.workloads.interactive.LdbcQuery2;
import org.ldbcouncil.snb.driver.workloads.interactive.LdbcQuery3;
import org.ldbcouncil.snb.driver.workloads.interactive.LdbcQuery4;
import org.ldbcouncil.snb.driver.workloads.interactive.LdbcQuery5;
import org.ldbcouncil.snb.driver.workloads.interactive.LdbcQuery6;
import org.ldbcouncil.snb.driver.workloads.interactive.LdbcQuery7;
import org.ldbcouncil.snb.driver.workloads.interactive.LdbcQuery8;
import org.ldbcouncil.snb.driver.workloads.interactive.LdbcQuery9;
import org.ldbcouncil.snb.driver.workloads.interactive.LdbcShortQuery1PersonProfile;
import org.ldbcouncil.snb.driver.workloads.interactive.LdbcShortQuery2PersonPosts;
import org.ldbcouncil.snb.driver.workloads.interactive.LdbcShortQuery3PersonFriends;
import org.ldbcouncil.snb.driver.workloads.interactive.LdbcShortQuery4MessageContent;
import org.ldbcouncil.snb.driver.workloads.interactive.LdbcShortQuery5MessageCreator;
import org.ldbcouncil.snb.driver.workloads.interactive.LdbcShortQuery6MessageForum;
import org.ldbcouncil.snb.driver.workloads.interactive.LdbcShortQuery7MessageReplies;
import org.ldbcouncil.snb.driver.workloads.interactive.LdbcUpdate1AddPerson;
import org.ldbcouncil.snb.driver.workloads.interactive.LdbcUpdate2AddPostLike;
import org.ldbcouncil.snb.driver.workloads.interactive.LdbcUpdate3AddCommentLike;
import org.ldbcouncil.snb.driver.workloads.interactive.LdbcUpdate4AddForum;
import org.ldbcouncil.snb.driver.workloads.interactive.LdbcUpdate5AddForumMembership;
import org.ldbcouncil.snb.driver.workloads.interactive.LdbcUpdate6AddPost;
import org.ldbcouncil.snb.driver.workloads.interactive.LdbcUpdate7AddComment;
import org.ldbcouncil.snb.driver.workloads.interactive.LdbcUpdate8AddFriendship;
import org.ldbcouncil.snb.impls.workloads.kuzu.KuzuDb;

import java.util.Map;

public class KuzuInteractiveDb extends KuzuDb
{

    @Override
    protected void onInit( Map<String, String> properties, LoggingService loggingService ) throws DbException
    {
        super.onInit( properties, loggingService );

        registerOperationHandler( LdbcQuery1.class, InteractiveQuery1.class );
        registerOperationHandler( LdbcQuery2.class, InteractiveQuery2.class );
        registerOperationHandler( LdbcQuery3.class, InteractiveQuery3.class );
        registerOperationHandler( LdbcQuery4.class, InteractiveQuery4.class );
        registerOperationHandler( LdbcQuery5.class, InteractiveQuery5.class );
        registerOperationHandler( LdbcQuery6.class, InteractiveQuery6.class );
        registerOperationHandler( LdbcQuery7.class, InteractiveQuery7.class );
        registerOperationHandler( LdbcQuery8.class, InteractiveQuery8.class );
        registerOperationHandler( LdbcQuery9.class, InteractiveQuery9.class );
        registerOperationHandler( LdbcQuery10.class, InteractiveQuery10.class );
        registerOperationHandler( LdbcQuery11.class, InteractiveQuery11.class );
        registerOperationHandler( LdbcQuery12.class, InteractiveQuery12.class );
        registerOperationHandler( LdbcQuery13.class, InteractiveQuery13.class );
        registerOperationHandler( LdbcQuery14.class, InteractiveQuery14.class );

        registerOperationHandler( LdbcShortQuery1PersonProfile.class, ShortQuery1PersonProfile.class );
        registerOperationHandler( LdbcShortQuery2PersonPosts.class, ShortQuery2PersonPosts.class );
        registerOperationHandler( LdbcShortQuery3PersonFriends.class, ShortQuery3PersonFriends.class );
        registerOperationHandler( LdbcShortQuery4MessageContent.class, ShortQuery4MessageContent.class );
        registerOperationHandler( LdbcShortQuery5MessageCreator.class, ShortQuery5MessageCreator.class );
        registerOperationHandler( LdbcShortQuery6MessageForum.class, ShortQuery6MessageForum.class );
        registerOperationHandler( LdbcShortQuery7MessageReplies.class, ShortQuery7MessageReplies.class );

        registerOperationHandler( LdbcUpdate1AddPerson.class, Update1AddPerson.class );
        registerOperationHandler( LdbcUpdate2AddPostLike.class, Update2AddPostLike.class );
        registerOperationHandler( LdbcUpdate3AddCommentLike.class, Update3AddCommentLike.class );
        registerOperationHandler( LdbcUpdate4AddForum.class, Update4AddForum.class );
        registerOperationHandler( LdbcUpdate5AddForumMembership.class, Update5AddForumMembership.class );
        registerOperationHandler( LdbcUpdate6AddPost.class, Update6AddPost.class );
        registerOperationHandler( LdbcUpdate7AddComment.class, Update7AddComment.class );
        registerOperationHandler( LdbcUpdate8AddFriendship.class, Update8AddFriendship.class );
    }
}
