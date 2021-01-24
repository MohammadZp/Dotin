package systemMessages;

public class Message {
    public final static String START_WORK_TIME="8:00";
    public final static String FINISH_WORK_TIME="16:00";
    public final static String NOT_SEEN="Not Seen";
    public final static String LEAVE_DATE_EXCEPTION="LEAVE DATE SHOULD BE IN FUTURE!NOT PAST!";
    public static final String NEVER_MODIFIED_MESSAGE = "Never Modified Before";
    public static final String UPDATE_EXCEPTION_MESSAGE = "Update failed! try again!";

    public static String deActive(String id) {
        return "the user with id="+id+" is not active!";
    }

    public static Object disable(String id) {
        return "the user with id="+id+" is disabled!";
    }
}
