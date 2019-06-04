package com.ajdi.yassin.newsreader.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author Yassin Ajdi
 * @since 6/3/2019.
 */
public class StringUtils {

    public static String sha1(String text) {
        return DigestUtils.sha1Hex(text);
    }
}
