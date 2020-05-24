/*
 *   The MIT License (MIT)
 *
 *   Copyright (c) 2015 Shopify Inc.
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in
 *   all copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *   THE SOFTWARE.
 */

package com.shopify.graphql.support;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;


public final class Utils {
//  private static final String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ssZ";
//  private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

    private static final DateTimeFormatter RFC3339_DATE_TIME_FORMATTER;

    public static OffsetDateTime parseRfc3339DateTime(String rfc3339Dt) {

        return OffsetDateTime.from(RFC3339_DATE_TIME_FORMATTER.parse(rfc3339Dt));
    }

    static {
        final DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .appendLiteral('T')
                .appendPattern("HH:mm:ss")
                .optionalStart()
                .appendFraction(ChronoField.NANO_OF_SECOND, 0, 9, true).parseDefaulting(ChronoField.NANO_OF_SECOND, 0)
                .optionalEnd()
                .appendOffset("+HH:mm", "Z");
        RFC3339_DATE_TIME_FORMATTER = builder.toFormatter();
    }

    public static DateTimeFormatter getRfc3339DateTimeFormatter() {
        return RFC3339_DATE_TIME_FORMATTER;
    }

    private Utils() {
    }
}