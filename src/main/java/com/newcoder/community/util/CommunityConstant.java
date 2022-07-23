package com.newcoder.community.util;

public interface CommunityConstant {

    /* Activation status */

    int ACTIVATION_SUCCESS = 0;

    int ACTIVATION_REPEAT = 1;

    int ACTIVATION_FAILURE = 2;

    /* Login time */

    int DEFAULT_EXPIRED_SECONDS = 3600 * 12;
    int REMENBER_EXPIRED_SECONDS = 3600 * 12 * 100;
}
