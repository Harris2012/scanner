package com.google.zxing.client.android.formatter;

import java.util.regex.Pattern;

/**
 * Created by HarrisZhang on 2018/4/6.
 */
public class MECARDFieldFormatter implements Formatter {
    private static final Pattern RESERVED_MECARD_CHARS = Pattern.compile("([\\\\:;])");
    private static final Pattern NEWLINE = Pattern.compile("\\n");

    @Override
    public CharSequence format(CharSequence value, int index) {
        return ':' + NEWLINE.matcher(RESERVED_MECARD_CHARS.matcher(value).replaceAll("\\\\$1")).replaceAll("");
    }
}