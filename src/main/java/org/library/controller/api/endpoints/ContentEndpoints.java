package org.library.controller.api.endpoints;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ContentEndpoints {

    /**
     * Api version constant.
     */
    public static final String VERSIONED_API_PATH = "/api/v1/";

    /**
     * Content constant.
     */
    public static final String CONTENT = "contents";

    /**
     * Content path.
     */
    public static final String CONTENT_PATH = VERSIONED_API_PATH + CONTENT;

    /**
     * ID Placeholder.
     */
    public static final String ID_RELATIVE_PATH = "/{id}";

    /**
     * Content tags.
     */
    public static final String TAG = "tags";

    /**
     * Tag Placeholder.
     */
    public static final String TAG_RELATIVE_PATH = "/{tag}";

    /**
     * Tag path.
     */
    public static final String TAG_PATH = VERSIONED_API_PATH + TAG;
}
