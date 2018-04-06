package com.google.zxing.client.android.formatter;

import com.google.zxing.client.android.encode.ContactEncoder;

import java.util.regex.Pattern;

/**
 * Created by HarrisZhang on 2018/4/6.
 */
public class MECARDTelDisplayFormatter implements Formatter {
    private static final Pattern NOT_DIGITS_OR_PLUS = Pattern.compile("[^0-9+]+");

    @Override
    public CharSequence format(CharSequence value, int index) {
        return NOT_DIGITS_OR_PLUS.matcher(ContactEncoder.formatPhone(value.toString())).replaceAll("");
    }
}