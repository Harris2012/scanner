package com.google.zxing.client.android.formatter;

import java.util.regex.Pattern;

/**
 * Created by HarrisZhang on 2018/4/6.
 */
public class MECARDNameDisplayFormatter implements Formatter {
    private static final Pattern COMMA = Pattern.compile(",");

    @Override
    public CharSequence format(CharSequence value, int index) {
        return COMMA.matcher(value).replaceAll("");
    }
}