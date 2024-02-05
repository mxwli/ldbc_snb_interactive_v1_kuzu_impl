package org.ldbcouncil.snb.impls.workloads.kuzu;

import com.google.common.collect.ImmutableMap;
import org.ldbcouncil.snb.driver.DbException;
import org.ldbcouncil.snb.driver.control.LoggingService;
import org.ldbcouncil.snb.driver.workloads.interactive.*;
import org.ldbcouncil.snb.impls.workloads.QueryType;
import org.ldbcouncil.snb.impls.workloads.kuzu.converter.KuzuConverter;
import org.ldbcouncil.snb.impls.workloads.kuzu.operationhandlers.KuzuIC13OperationHandler;
import org.ldbcouncil.snb.impls.workloads.kuzu.operationhandlers.KuzuListOperationHandler;
import org.ldbcouncil.snb.impls.workloads.kuzu.operationhandlers.KuzuSingletonOperationHandler;
import org.ldbcouncil.snb.impls.workloads.kuzu.operationhandlers.KuzuUpdateOperationHandler;
import org.ldbcouncil.snb.impls.workloads.db.BaseDb;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.kuzudb.*;

public class KuzuDb extends BaseDb<KuzuQueryStore>
{

    KuzuQueryStore queryStore;

    @Override
    protected void onInit( Map<String, String> properties, LoggingService loggingService ) throws DbException
    {

        dcs = new KuzuDbConnectionState<>(properties, new KuzuQueryStore(properties.get("queryDir")));
        queryStore = new KuzuQueryStore( properties.get( "queryDir" ) );
    }

    // Interactive complex reads

    public static class InteractiveQuery1 extends KuzuListOperationHandler<LdbcQuery1,LdbcQuery1Result>
    {

        @Override
        public String getQueryString(KuzuDbConnectionState state, LdbcQuery1 operation) {
            return state.getQueryStore().getParameterizedQuery(QueryType.InteractiveComplexQuery1);
        }

        @Override
        public Map<String, Object> getParameters(KuzuDbConnectionState state, LdbcQuery1 operation) {
            return state.getQueryStore().getQuery1Map(operation);
        }

        @Override
        public LdbcQuery1Result toResult( KuzuFlatTuple record ) throws ParseException, KuzuObjectRefDestroyedException
        {

            List<String> emails;
            if ( !record.getValue( 8 ).isNull() )
            {
                emails = record.getValue( 8 ).getValue();
            }
            else
            {
                emails = new ArrayList<>();
            }

            List<String> languages;
            if ( !record.getValue( 9 ).isNull() )
            {
                languages = record.getValue( 9 ).getValue();
            }
            else
            {
                languages = new ArrayList<>();
            }

            List<LdbcQuery1Result.Organization> universities;
            if ( !record.getValue( 11 ).isNull() )
            {
                universities = KuzuConverter.asOrganization(record.getValue( 11 ).getValue());
            }
            else
            {
                universities = new ArrayList<>();
            }

            List<LdbcQuery1Result.Organization> companies;
            if ( !record.getValue( 12 ).isNull() )
            {
                companies = KuzuConverter.asOrganization(record.getValue( 12 ).getValue());
            }
            else
            {
                companies = new ArrayList<>();
            }

            long friendId = record.getValue( 0 ).getValue();
            String friendLastName = record.getValue( 1 ).getValue();
            int distanceFromPerson = record.getValue( 2 ).getValue();
            long friendBirthday = record.getValue( 3 ).getValue();
            long friendCreationDate = record.getValue( 4 ).getValue();
            String friendGender = record.getValue( 5 ).getValue();
            String friendBrowserUsed = record.getValue( 6 ).getValue();
            String friendLocationIp = record.getValue( 7 ).getValue();
            String friendCityName = record.getValue( 10 ).getValue();
            return new LdbcQuery1Result(
                    friendId,
                    friendLastName,
                    distanceFromPerson,
                    friendBirthday,
                    friendCreationDate,
                    friendGender,
                    friendBrowserUsed,
                    friendLocationIp,
                    emails,
                    languages,
                    friendCityName,
                    universities,
                    companies );
        }
    }

    public static class InteractiveQuery2 extends KuzuListOperationHandler<LdbcQuery2,LdbcQuery2Result>
    {
        @Override
        public String getQueryString(KuzuDbConnectionState state, LdbcQuery2 operation) {
            return state.getQueryStore().getParameterizedQuery(QueryType.InteractiveComplexQuery2);
        }

        @Override
        public Map<String, Object> getParameters(KuzuDbConnectionState state, LdbcQuery2 operation) {
            return state.getQueryStore().getQuery2Map(operation);
        }

        @Override
        public LdbcQuery2Result toResult( KuzuFlatTuple record ) throws ParseException, KuzuObjectRefDestroyedException
        {
            long personId = record.getValue( 0 ).getValue();
            String personFirstName = record.getValue( 1 ).getValue();
            String personLastName = record.getValue( 2 ).getValue();
            long messageId = record.getValue( 3 ).getValue();
            String messageContent = record.getValue( 4 ).getValue();
            long messageCreationDate = record.getValue( 5 ).getValue();

            return new LdbcQuery2Result(
                    personId,
                    personFirstName,
                    personLastName,
                    messageId,
                    messageContent,
                    messageCreationDate );
        }
    }

    public static class InteractiveQuery3 extends KuzuListOperationHandler<LdbcQuery3,LdbcQuery3Result>
    {
        @Override
        public String getQueryString(KuzuDbConnectionState state, LdbcQuery3 operation) {
            return state.getQueryStore().getParameterizedQuery(QueryType.InteractiveComplexQuery3);
        }

        @Override
        public Map<String, Object> getParameters(KuzuDbConnectionState state, LdbcQuery3 operation) {
            return state.getQueryStore().getQuery3Map(operation);
        }

        @Override
        public LdbcQuery3Result toResult( KuzuFlatTuple record ) throws KuzuObjectRefDestroyedException
        {
            long personId = record.getValue( 0 ).getValue();
            String personFirstName = record.getValue( 1 ).getValue();
            String personLastName = record.getValue( 2 ).getValue();
            long xCount = record.getValue( 3 ).getValue();
            long yCount = record.getValue( 4 ).getValue();
            long count = record.getValue( 5 ).getValue();
            return new LdbcQuery3Result(
                    personId,
                    personFirstName,
                    personLastName,
                    xCount,
                    yCount,
                    count );
        }
    }

    public static class InteractiveQuery4 extends KuzuListOperationHandler<LdbcQuery4,LdbcQuery4Result>
    {
        @Override
        public String getQueryString(KuzuDbConnectionState state, LdbcQuery4 operation) {
            return state.getQueryStore().getParameterizedQuery(QueryType.InteractiveComplexQuery4);
        }

        @Override
        public Map<String, Object> getParameters(KuzuDbConnectionState state, LdbcQuery4 operation) {
            return state.getQueryStore().getQuery4Map(operation);
        }

        @Override
        public LdbcQuery4Result toResult( KuzuFlatTuple record ) throws KuzuObjectRefDestroyedException
        {
            String tagName = record.getValue( 0 ).getValue();
            long postCount = record.getValue( 1 ).getValue();
            return new LdbcQuery4Result( tagName, (int)postCount );
        }
    }

    public static class InteractiveQuery5 extends KuzuListOperationHandler<LdbcQuery5,LdbcQuery5Result>
    {
        @Override
        public String getQueryString(KuzuDbConnectionState state, LdbcQuery5 operation) {
            return state.getQueryStore().getParameterizedQuery(QueryType.InteractiveComplexQuery5);
        }

        @Override
        public Map<String, Object> getParameters(KuzuDbConnectionState state, LdbcQuery5 operation) {
            return state.getQueryStore().getQuery5Map(operation);
        }

        @Override
        public LdbcQuery5Result toResult( KuzuFlatTuple record ) throws KuzuObjectRefDestroyedException
        {
            String forumTitle = record.getValue( 0 ).getValue();
            long postCount = record.getValue( 1 ).getValue();
            return new LdbcQuery5Result( forumTitle, (int)postCount );
        }
    }

    public static class InteractiveQuery6 extends KuzuListOperationHandler<LdbcQuery6,LdbcQuery6Result>
    {
        @Override
        public String getQueryString(KuzuDbConnectionState state, LdbcQuery6 operation) {
            return state.getQueryStore().getParameterizedQuery(QueryType.InteractiveComplexQuery6);
        }

        @Override
        public Map<String, Object> getParameters(KuzuDbConnectionState state, LdbcQuery6 operation) {
            return state.getQueryStore().getQuery6Map(operation);
        }

        @Override
        public LdbcQuery6Result toResult( KuzuFlatTuple record ) throws KuzuObjectRefDestroyedException
        {
            String tagName = record.getValue( 0 ).getValue();
            long postCount = record.getValue( 1 ).getValue();
            return new LdbcQuery6Result( tagName, (int)postCount );
        }
    }

    public static class InteractiveQuery7 extends KuzuListOperationHandler<LdbcQuery7,LdbcQuery7Result>
    {
        @Override
        public String getQueryString(KuzuDbConnectionState state, LdbcQuery7 operation) {
            return state.getQueryStore().getParameterizedQuery(QueryType.InteractiveComplexQuery7);
        }

        @Override
        public Map<String, Object> getParameters(KuzuDbConnectionState state, LdbcQuery7 operation) {
            return state.getQueryStore().getQuery7Map(operation);
        }

        @Override
        public LdbcQuery7Result toResult( KuzuFlatTuple record ) throws ParseException, KuzuObjectRefDestroyedException
        {
            long personId = record.getValue( 0 ).getValue();
            String personFirstName = record.getValue( 1 ).getValue();
            String personLastName = record.getValue( 2 ).getValue();
            long likeCreationDate = record.getValue( 3 ).getValue();
            long messageId = record.getValue( 4 ).getValue();
            String messageContent = record.getValue( 5 ).getValue();
            long minutesLatency = record.getValue( 6 ).getValue();
            boolean isNew = record.getValue( 7 ).getValue();
            return new LdbcQuery7Result(
                    personId,
                    personFirstName,
                    personLastName,
                    likeCreationDate,
                    messageId,
                    messageContent,
                    (int)minutesLatency,
                    isNew );
        }
    }

    public static class InteractiveQuery8 extends KuzuListOperationHandler<LdbcQuery8,LdbcQuery8Result>
    {
        @Override
        public String getQueryString(KuzuDbConnectionState state, LdbcQuery8 operation) {
            return state.getQueryStore().getParameterizedQuery(QueryType.InteractiveComplexQuery8);
        }

        @Override
        public Map<String, Object> getParameters(KuzuDbConnectionState state, LdbcQuery8 operation) {
            return state.getQueryStore().getQuery8Map(operation);
        }

        @Override
        public LdbcQuery8Result toResult( KuzuFlatTuple record ) throws ParseException, KuzuObjectRefDestroyedException
        {
            long personId = record.getValue( 0 ).getValue();
            String personFirstName = record.getValue( 1 ).getValue();
            String personLastName = record.getValue( 2 ).getValue();
            long commentCreationDate = record.getValue( 3 ).getValue();
            long commentId = record.getValue( 4 ).getValue();
            String commentContent = record.getValue( 5 ).getValue();
            return new LdbcQuery8Result(
                    personId,
                    personFirstName,
                    personLastName,
                    commentCreationDate,
                    commentId,
                    commentContent );
        }
    }

    public static class InteractiveQuery9 extends KuzuListOperationHandler<LdbcQuery9,LdbcQuery9Result>
    {
        @Override
        public String getQueryString(KuzuDbConnectionState state, LdbcQuery9 operation) {
            return state.getQueryStore().getParameterizedQuery(QueryType.InteractiveComplexQuery9);
        }

        @Override
        public Map<String, Object> getParameters(KuzuDbConnectionState state, LdbcQuery9 operation) {
            return state.getQueryStore().getQuery9Map(operation);
        }

        @Override
        public LdbcQuery9Result toResult( KuzuFlatTuple record ) throws ParseException, KuzuObjectRefDestroyedException
        {
            long personId = record.getValue( 0 ).getValue();
            String personFirstName = record.getValue( 1 ).getValue();
            String personLastName = record.getValue( 2 ).getValue();
            long messageId = record.getValue( 3 ).getValue();
            String messageContent = record.getValue( 4 ).getValue();
            long messageCreationDate = record.getValue( 5 ).getValue();
            return new LdbcQuery9Result(
                    personId,
                    personFirstName,
                    personLastName,
                    messageId,
                    messageContent,
                    messageCreationDate );
        }
    }

    public static class InteractiveQuery10 extends KuzuListOperationHandler<LdbcQuery10,LdbcQuery10Result>
    {
        @Override
        public String getQueryString(KuzuDbConnectionState state, LdbcQuery10 operation) {
            return state.getQueryStore().getParameterizedQuery(QueryType.InteractiveComplexQuery10);
        }

        @Override
        public Map<String, Object> getParameters(KuzuDbConnectionState state, LdbcQuery10 operation) {
            return state.getQueryStore().getQuery10Map(operation);
        }

        @Override
        public LdbcQuery10Result toResult( KuzuFlatTuple record ) throws ParseException, KuzuObjectRefDestroyedException
        {
            long personId = record.getValue( 0 ).getValue();
            String personFirstName = record.getValue( 1 ).getValue();
            String personLastName = record.getValue( 2 ).getValue();
            long commonInterestScore = record.getValue( 3 ).getValue();
            String personGender = record.getValue( 4 ).getValue();
            String personCityName = record.getValue( 5 ).getValue();
            return new LdbcQuery10Result(
                    personId,
                    personFirstName,
                    personLastName,
                    (int)commonInterestScore,
                    personGender,
                    personCityName );
        }
    }

    public static class InteractiveQuery11 extends KuzuListOperationHandler<LdbcQuery11,LdbcQuery11Result>
    {
        @Override
        public String getQueryString(KuzuDbConnectionState state, LdbcQuery11 operation) {
            return state.getQueryStore().getParameterizedQuery(QueryType.InteractiveComplexQuery11);
        }

        @Override
        public Map<String, Object> getParameters(KuzuDbConnectionState state, LdbcQuery11 operation) {
            return state.getQueryStore().getQuery11Map(operation);
        }

        @Override
        public LdbcQuery11Result toResult( KuzuFlatTuple record ) throws ParseException, KuzuObjectRefDestroyedException
        {
            long personId = record.getValue( 0 ).getValue();
            String personFirstName = record.getValue( 1 ).getValue();
            String personLastName = record.getValue( 2 ).getValue();
            String organizationName = record.getValue( 3 ).getValue();
            int organizationWorkFromYear = record.getValue( 4 ).getValue();
            return new LdbcQuery11Result(
                    personId,
                    personFirstName,
                    personLastName,
                    organizationName,
                    organizationWorkFromYear );
        }
    }

    public static class InteractiveQuery12 extends KuzuListOperationHandler<LdbcQuery12,LdbcQuery12Result>
    {
        @Override
        public String getQueryString(KuzuDbConnectionState state, LdbcQuery12 operation) {
            return state.getQueryStore().getParameterizedQuery(QueryType.InteractiveComplexQuery12);
        }

        @Override
        public Map<String, Object> getParameters(KuzuDbConnectionState state, LdbcQuery12 operation) {
            return state.getQueryStore().getQuery12Map(operation);
        }

        @Override
        public LdbcQuery12Result toResult( KuzuFlatTuple record ) throws ParseException, KuzuObjectRefDestroyedException
        {
            long personId = record.getValue( 0 ).getValue();
            String personFirstName = record.getValue( 1 ).getValue();
            String personLastName = record.getValue( 2 ).getValue();
            List<String> tagNames = new ArrayList<>();
            if ( !record.getValue( 3 ).isNull() )
            {
                tagNames = record.getValue( 3 ).getValue();
            }
            long replyCount = record.getValue( 4 ).getValue();
            return new LdbcQuery12Result(
                    personId,
                    personFirstName,
                    personLastName,
                    tagNames,
                    (int)replyCount );
        }
    }

    public static class InteractiveQuery13 extends KuzuIC13OperationHandler
    {
        @Override
        public String getQueryString(KuzuDbConnectionState state, LdbcQuery13 operation) {
            return state.getQueryStore().getParameterizedQuery(QueryType.InteractiveComplexQuery13);
        }

        @Override
        public Map<String, Object> getParameters(KuzuDbConnectionState state, LdbcQuery13 operation) {
            return state.getQueryStore().getQuery13Map(operation);
        }

        @Override
        public LdbcQuery13Result toResult( KuzuFlatTuple record ) throws KuzuObjectRefDestroyedException
        {
            return new LdbcQuery13Result( record.getValue( 0 ).getValue() );
        }
    }

    public static class InteractiveQuery14 extends KuzuListOperationHandler<LdbcQuery14,LdbcQuery14Result>
    {
        @Override
        public String getQueryString(KuzuDbConnectionState state, LdbcQuery14 operation) {
            return state.getQueryStore().getParameterizedQuery(QueryType.InteractiveComplexQuery14);
        }

        @Override
        public Map<String, Object> getParameters(KuzuDbConnectionState state, LdbcQuery14 operation) {
            return state.getQueryStore().getQuery14Map(operation);
        }

        @Override
        public LdbcQuery14Result toResult( KuzuFlatTuple record ) throws ParseException, KuzuObjectRefDestroyedException
        {
            List<Long> personIdsInPath = new ArrayList<>();
            if ( !record.getValue( 0 ).isNull() )
            {
                personIdsInPath = record.getValue( 0 ).getValue();
            }
            double pathWeight = record.getValue( 1 ).getValue();
            return new LdbcQuery14Result(
                    personIdsInPath,
                    pathWeight );
        }
    }

    // Interactive short reads
    public static class ShortQuery1PersonProfile extends KuzuSingletonOperationHandler<LdbcShortQuery1PersonProfile,LdbcShortQuery1PersonProfileResult>
    {
        @Override
        public String getQueryString(KuzuDbConnectionState state, LdbcShortQuery1PersonProfile operation) {
            return state.getQueryStore().getParameterizedQuery(QueryType.InteractiveShortQuery1);
        }

        @Override
        public Map<String, Object> getParameters(KuzuDbConnectionState state, LdbcShortQuery1PersonProfile operation) {
            return state.getQueryStore().getShortQuery1PersonProfileMap(operation);
        }

        @Override
        public LdbcShortQuery1PersonProfileResult toResult( KuzuFlatTuple record ) throws ParseException, KuzuObjectRefDestroyedException
        {
            String firstName = record.getValue( 0 ).getValue();
            String lastName = record.getValue( 1 ).getValue();
            long birthday = record.getValue( 2 ).getValue();
            String locationIP = record.getValue( 3 ).getValue();
            String browserUsed = record.getValue( 4 ).getValue();
            long cityId = record.getValue( 5 ).getValue();
            String gender = record.getValue( 6 ).getValue();
            long creationDate = record.getValue( 7 ).getValue();
            return new LdbcShortQuery1PersonProfileResult(
                    firstName,
                    lastName,
                    birthday,
                    locationIP,
                    browserUsed,
                    cityId,
                    gender,
                    creationDate );
        }
    }

    public static class ShortQuery2PersonPosts extends KuzuListOperationHandler<LdbcShortQuery2PersonPosts,LdbcShortQuery2PersonPostsResult>
    {
        @Override
        public String getQueryString(KuzuDbConnectionState state, LdbcShortQuery2PersonPosts operation) {
            return state.getQueryStore().getParameterizedQuery(QueryType.InteractiveShortQuery2);
        }

        @Override
        public Map<String, Object> getParameters(KuzuDbConnectionState state, LdbcShortQuery2PersonPosts operation) {
            return state.getQueryStore().getShortQuery2PersonPostsMap(operation);
        }

        @Override
        public LdbcShortQuery2PersonPostsResult toResult( KuzuFlatTuple record ) throws ParseException, KuzuObjectRefDestroyedException
        {
            long messageId = record.getValue( 0 ).getValue();
            String messageContent = record.getValue( 1 ).getValue();
            long messageCreationDate = record.getValue( 2 ).getValue();
            long originalPostId = record.getValue( 3 ).getValue();
            long originalPostAuthorId = record.getValue( 4 ).getValue();
            String originalPostAuthorFirstName = record.getValue( 5 ).getValue();
            String originalPostAuthorLastName = record.getValue( 6 ).getValue();
            return new LdbcShortQuery2PersonPostsResult(
                    messageId,
                    messageContent,
                    messageCreationDate,
                    originalPostId,
                    originalPostAuthorId,
                    originalPostAuthorFirstName,
                    originalPostAuthorLastName );
        }
    }

    public static class ShortQuery3PersonFriends extends KuzuListOperationHandler<LdbcShortQuery3PersonFriends,LdbcShortQuery3PersonFriendsResult>
    {
        @Override
        public String getQueryString(KuzuDbConnectionState state, LdbcShortQuery3PersonFriends operation) {
            return state.getQueryStore().getParameterizedQuery(QueryType.InteractiveShortQuery3);
        }

        @Override
        public Map<String, Object> getParameters(KuzuDbConnectionState state, LdbcShortQuery3PersonFriends operation) {
            return state.getQueryStore().getShortQuery3PersonFriendsMap(operation);
        }

        @Override
        public LdbcShortQuery3PersonFriendsResult toResult( KuzuFlatTuple record ) throws ParseException, KuzuObjectRefDestroyedException
        {
            long personId = record.getValue( 0 ).getValue();
            String firstName = record.getValue( 1 ).getValue();
            String lastName = record.getValue( 2 ).getValue();
            long friendshipCreationDate = record.getValue( 3 ).getValue();
            return new LdbcShortQuery3PersonFriendsResult(
                    personId,
                    firstName,
                    lastName,
                    friendshipCreationDate );
        }
    }

    public static class ShortQuery4MessageContent extends KuzuSingletonOperationHandler<LdbcShortQuery4MessageContent,LdbcShortQuery4MessageContentResult>
    {
        @Override
        public String getQueryString(KuzuDbConnectionState state, LdbcShortQuery4MessageContent operation) {
            return state.getQueryStore().getParameterizedQuery(QueryType.InteractiveShortQuery4);
        }

        @Override
        public Map<String, Object> getParameters(KuzuDbConnectionState state, LdbcShortQuery4MessageContent operation) {
            return state.getQueryStore().getShortQuery4MessageContentMap(operation);
        }

        @Override
        public LdbcShortQuery4MessageContentResult toResult( KuzuFlatTuple record ) throws ParseException, KuzuObjectRefDestroyedException
        {
            // Pay attention, the spec's and the implementation's parameter orders are different.
            long messageCreationDate = record.getValue( 0 ).getValue();
            String messageContent = record.getValue( 1 ).getValue();
            return new LdbcShortQuery4MessageContentResult(
                    messageContent,
                    messageCreationDate );
        }
    }

    public static class ShortQuery5MessageCreator extends KuzuSingletonOperationHandler<LdbcShortQuery5MessageCreator,LdbcShortQuery5MessageCreatorResult>
    {
        @Override
        public String getQueryString(KuzuDbConnectionState state, LdbcShortQuery5MessageCreator operation) {
            return state.getQueryStore().getParameterizedQuery(QueryType.InteractiveShortQuery5);
        }

        @Override
        public Map<String, Object> getParameters(KuzuDbConnectionState state, LdbcShortQuery5MessageCreator operation) {
            return state.getQueryStore().getShortQuery5MessageCreatorMap(operation);
        }

        @Override
        public LdbcShortQuery5MessageCreatorResult toResult( KuzuFlatTuple record ) throws KuzuObjectRefDestroyedException 
        {
            long personId = record.getValue( 0 ).getValue();
            String firstName = record.getValue( 1 ).getValue();
            String lastName = record.getValue( 2 ).getValue();
            return new LdbcShortQuery5MessageCreatorResult(
                    personId,
                    firstName,
                    lastName );
        }
    }

    public static class ShortQuery6MessageForum extends KuzuSingletonOperationHandler<LdbcShortQuery6MessageForum,LdbcShortQuery6MessageForumResult>
    {
        @Override
        public String getQueryString(KuzuDbConnectionState state, LdbcShortQuery6MessageForum operation) {
            return state.getQueryStore().getParameterizedQuery(QueryType.InteractiveShortQuery6);
        }

        @Override
        public Map<String, Object> getParameters(KuzuDbConnectionState state, LdbcShortQuery6MessageForum operation) {
            return state.getQueryStore().getShortQuery6MessageForumMap(operation);
        }

        @Override
        public LdbcShortQuery6MessageForumResult toResult( KuzuFlatTuple record ) throws KuzuObjectRefDestroyedException
        {
            long forumId = record.getValue( 0 ).getValue();
            String forumTitle = record.getValue( 1 ).getValue();
            long moderatorId = record.getValue( 2 ).getValue();
            String moderatorFirstName = record.getValue( 3 ).getValue();
            String moderatorLastName = record.getValue( 4 ).getValue();
            return new LdbcShortQuery6MessageForumResult(
                    forumId,
                    forumTitle,
                    moderatorId,
                    moderatorFirstName,
                    moderatorLastName );
        }
    }

    public static class ShortQuery7MessageReplies extends KuzuListOperationHandler<LdbcShortQuery7MessageReplies,LdbcShortQuery7MessageRepliesResult>
    {
        @Override
        public String getQueryString(KuzuDbConnectionState state, LdbcShortQuery7MessageReplies operation) {
            return state.getQueryStore().getParameterizedQuery(QueryType.InteractiveShortQuery7);
        }

        @Override
        public Map<String, Object> getParameters(KuzuDbConnectionState state, LdbcShortQuery7MessageReplies operation) {
            return state.getQueryStore().getShortQuery7MessageRepliesMap(operation);
        }

        @Override
        public LdbcShortQuery7MessageRepliesResult toResult( KuzuFlatTuple record ) throws ParseException, KuzuObjectRefDestroyedException
        {
            long commentId = record.getValue( 0 ).getValue();
            String commentContent = record.getValue( 1 ).getValue();
            long commentCreationDate = record.getValue( 2 ).getValue();
            long replyAuthorId = record.getValue( 3 ).getValue();
            String replyAuthorFirstName = record.getValue( 4 ).getValue();
            String replyAuthorLastName = record.getValue( 5 ).getValue();
            boolean replyAuthorKnowsOriginalMessageAuthor = record.getValue( 6 ).getValue();
            return new LdbcShortQuery7MessageRepliesResult(
                    commentId,
                    commentContent,
                    commentCreationDate,
                    replyAuthorId,
                    replyAuthorFirstName,
                    replyAuthorLastName,
                    replyAuthorKnowsOriginalMessageAuthor );
        }
    }

    // Interactive inserts

    public static class Update1AddPerson extends KuzuUpdateOperationHandler<LdbcUpdate1AddPerson>
    {
        @Override
        public String getQueryString(KuzuDbConnectionState state, LdbcUpdate1AddPerson operation) {
            return state.getQueryStore().getParameterizedQuery(QueryType.InteractiveUpdate1);
        }

        @Override
        public Map<String, Object> getParameters( KuzuDbConnectionState state, LdbcUpdate1AddPerson operation )
        {
            final List<List<Long>> universities =
                    operation.getStudyAt().stream().map( u -> Arrays.asList( u.getOrganizationId(), (long) u.getYear() ) ).collect( Collectors.toList() );
            final List<List<Long>> companies =
                    operation.getWorkAt().stream().map( c -> Arrays.asList( c.getOrganizationId(), (long) c.getYear() ) ).collect( Collectors.toList() );
            return state.getQueryStore().getUpdate1SingleMap(operation);
        }
    }

    public static class Update2AddPostLike extends KuzuUpdateOperationHandler<LdbcUpdate2AddPostLike>
    {
        @Override
        public String getQueryString(KuzuDbConnectionState state, LdbcUpdate2AddPostLike operation) {
            return state.getQueryStore().getParameterizedQuery(QueryType.InteractiveUpdate2);
        }

        @Override
        public Map<String, Object> getParameters( KuzuDbConnectionState state, LdbcUpdate2AddPostLike operation )
        {
            return state.getQueryStore().getUpdate2Map(operation);
        }
    }

    public static class Update3AddCommentLike extends KuzuUpdateOperationHandler<LdbcUpdate3AddCommentLike>
    {
        @Override
        public String getQueryString(KuzuDbConnectionState state, LdbcUpdate3AddCommentLike operation) {
            return state.getQueryStore().getParameterizedQuery(QueryType.InteractiveUpdate3);
        }

        @Override
        public Map<String, Object> getParameters( KuzuDbConnectionState state, LdbcUpdate3AddCommentLike operation )
        {
            return state.getQueryStore().getUpdate3Map(operation);
        }
    }

    public static class Update4AddForum extends KuzuUpdateOperationHandler<LdbcUpdate4AddForum>
    {
        @Override
        public String getQueryString(KuzuDbConnectionState state, LdbcUpdate4AddForum operation) {
            return state.getQueryStore().getParameterizedQuery(QueryType.InteractiveUpdate4);
        }

        @Override
        public Map<String, Object> getParameters( KuzuDbConnectionState state, LdbcUpdate4AddForum operation )
        {
            return state.getQueryStore().getUpdate4SingleMap(operation);
        }
    }

    public static class Update5AddForumMembership extends KuzuUpdateOperationHandler<LdbcUpdate5AddForumMembership>
    {
        @Override
        public String getQueryString(KuzuDbConnectionState state, LdbcUpdate5AddForumMembership operation) {
            return state.getQueryStore().getParameterizedQuery(QueryType.InteractiveUpdate5);
        }

        @Override
        public Map<String, Object> getParameters( KuzuDbConnectionState state, LdbcUpdate5AddForumMembership operation )
        {
            return state.getQueryStore().getUpdate5Map(operation);
        }
    }

    public static class Update6AddPost extends KuzuUpdateOperationHandler<LdbcUpdate6AddPost>
    {
        @Override
        public String getQueryString(KuzuDbConnectionState state, LdbcUpdate6AddPost operation) {
            return state.getQueryStore().getParameterizedQuery(QueryType.InteractiveUpdate6);
        }

        @Override
        public Map<String, Object> getParameters( KuzuDbConnectionState state, LdbcUpdate6AddPost operation )
        {
            return state.getQueryStore().getUpdate6SingleMap(operation);
        }
    }

    public static class Update7AddComment extends KuzuUpdateOperationHandler<LdbcUpdate7AddComment>
    {
        @Override
        public String getQueryString(KuzuDbConnectionState state, LdbcUpdate7AddComment operation) {
            return state.getQueryStore().getParameterizedQuery(QueryType.InteractiveUpdate7);
        }

        @Override
        public Map<String, Object> getParameters( KuzuDbConnectionState state, LdbcUpdate7AddComment operation )
        {
            return state.getQueryStore().getUpdate7SingleMap(operation);
        }
    }

    public static class Update8AddFriendship extends KuzuUpdateOperationHandler<LdbcUpdate8AddFriendship>
    {
        @Override
        public String getQueryString(KuzuDbConnectionState state, LdbcUpdate8AddFriendship operation) {
            return state.getQueryStore().getParameterizedQuery(QueryType.InteractiveUpdate8);
        }

        @Override
        public Map<String, Object> getParameters( KuzuDbConnectionState state, LdbcUpdate8AddFriendship operation )
        {
            return state.getQueryStore().getUpdate8Map(operation);
        }
    }
}
