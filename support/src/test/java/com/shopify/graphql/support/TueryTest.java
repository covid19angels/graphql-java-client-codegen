package com.shopify.graphql.support;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TueryTest {
    @Test
    public void testStringEscaping() throws Exception {
        StringBuilder result = new StringBuilder();
        Tuery.appendQuotedString(result, "\0 \r \n \\ \" c ꝏ");
        assertEquals("\"\\u0000 \\r \\n \\\\ \\\" c ꝏ\"", result.toString());
    }

    @Test
    public void testInvalidAliasWithUnderscore() {
        assertThrows(IllegalArgumentException.class, () -> new Tuery<Tuery>(null) {
        }.withAlias("invalid__alias"));
    }

    @Test
    public void testInvalidAliasWithDashes() {
        assertThrows(IllegalArgumentException.class, () -> new Tuery<Tuery>(null) {
        }.withAlias("invalid-alias"));
    }

    @Test
    public void testBlankAlias() {
        assertThrows(IllegalArgumentException.class, () -> new Tuery<Tuery>(null) {
        }.withAlias(""));
    }
}
