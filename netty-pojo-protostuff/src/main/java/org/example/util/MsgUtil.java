package org.example.util;

import org.example.domain.MsgInfo;

public class MsgUtil {

    public static Object buildMsg(String channelId, String msgContent) {
        return new MsgInfo(channelId, msgContent);
    }
}
