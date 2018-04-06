/*
 * Copyright (C) 2011 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.zxing.client.android.encode;

import com.google.zxing.client.android.formatter.Formatter;
import com.google.zxing.client.android.formatter.MECARDFieldFormatter;
import com.google.zxing.client.android.formatter.MECARDNameDisplayFormatter;
import com.google.zxing.client.android.formatter.MECARDTelDisplayFormatter;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Encodes contact information according to the MECARD format.
 *
 * @author Sean Owen
 */
final class MECARDContactEncoder extends ContactEncoder {

    private static final char TERMINATOR = ';';

    @Override
    public String[] encode(List<String> names,
                           String organization,
                           List<String> addresses,
                           List<String> phones,
                           List<String> phoneTypes,
                           List<String> emails,
                           List<String> urls,
                           String note) {
        StringBuilder newContents = new StringBuilder(100);
        newContents.append("MECARD:");

        StringBuilder newDisplayContents = new StringBuilder(100);

        Formatter fieldFormatter = new MECARDFieldFormatter();

        appendUpToUnique(newContents, newDisplayContents, "N", names, 1, new
                MECARDNameDisplayFormatter(), fieldFormatter, TERMINATOR);

        append(newContents, newDisplayContents, "ORG", organization, fieldFormatter, TERMINATOR);

        appendUpToUnique(newContents, newDisplayContents, "ADR", addresses, 1, null, fieldFormatter, TERMINATOR);

        appendUpToUnique(newContents, newDisplayContents, "TEL", phones, Integer.MAX_VALUE,
                new MECARDTelDisplayFormatter(), fieldFormatter, TERMINATOR);

        appendUpToUnique(newContents, newDisplayContents, "EMAIL", emails, Integer.MAX_VALUE, null,
                fieldFormatter, TERMINATOR);

        appendUpToUnique(newContents, newDisplayContents, "URL", urls, Integer.MAX_VALUE, null,
                fieldFormatter, TERMINATOR);

        append(newContents, newDisplayContents, "NOTE", note, fieldFormatter, TERMINATOR);

        newContents.append(';');

        return new String[]{newContents.toString(), newDisplayContents.toString()};
    }
}
