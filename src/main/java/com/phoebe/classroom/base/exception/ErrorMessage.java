package com.phoebe.classroom.base.exception;

import java.util.HashMap;
import java.util.Map;

public class ErrorMessage {
    public static final String POST_NOT_FOUND = "Post not found";

    public static final String KEY_POST_NOT_FOUND = "exception.resource.not.found.post";

    public static final String COMMENT_NOT_FOUND = "Comment not found";

    public static final String KEY_COMMENT_NOT_FOUND = "exception.resource.not.found.comment";

    public static final String CLASSROOM_NOT_FOUND = "Classroom not found";

    public static final String KEY_CLASSROOM_NOT_FOUND = "exception.resource.not.found.classroom";

    public static final String USER_NOT_FOUND = "User not found";

    public static final String KEY_USER_NOT_FOUND = "exception.resource.not.found.user";

    public static final String POST_CONTENT_NULL_OR_BLANK = "Post content cannot be null, empty or blank";

    public static final String KEY_POST_CONTENT_NULL_OR_BLANK = "exception.input.validation.post.content.null.or.blank";

    public static final String POST_TITLE_NULL_OR_BLANK = "Post title cannot be null, empty or blank";

    public static final String KEY_POST_TITLE_NULL_OR_BLANK = "exception.input.validation.post.title.null.or.blank";

    public static final String CLASSROOM_ID_NULL_OR_BLANK = "Classroom id cannot be null, empty or blank";

    public static final String KEY_CLASSROOM_ID_NULL_OR_BLANK = "exception.input.validation.classroom.id.null.or.blank";

    public static final String USER_ID_NULL_OR_BLANK = "User id cannot be null, empty or blank";

    public static final String KEY_USER_ID_NULL_OR_BLANK = "exception.input.validation.user.id.null.or.blank";

    public static final String USERNAME_NULL_OR_BLANK = "Username cannot be null, empty or blank";

    public static final String KEY_USERNAME_NULL_OR_BLANK = "exception.input.validation.username.null.or.blank";

    public static final String USERNAME_PASSWORD_INVALID = "Username or password is invalid";

    public static final String KEY_USERNAME_PASSWORD_INVALID = "exception.input.validation.username.password.invalid";

    public static final String UNAUTHORIZED_ACCESS = "Unauthorized Access";
    public static final String KEY_UNAUTHORIZED_ACCESS = "exception.security.unauthorized.access";

    static Map<String, String> errorKeyAndMessageMap() {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put(POST_CONTENT_NULL_OR_BLANK, KEY_POST_CONTENT_NULL_OR_BLANK);
        errorMap.put(POST_TITLE_NULL_OR_BLANK, KEY_POST_TITLE_NULL_OR_BLANK);
        errorMap.put(CLASSROOM_ID_NULL_OR_BLANK, KEY_CLASSROOM_ID_NULL_OR_BLANK);
        errorMap.put(USER_ID_NULL_OR_BLANK, KEY_USER_ID_NULL_OR_BLANK);
        return errorMap;
    }
    private ErrorMessage() {
    }
}
