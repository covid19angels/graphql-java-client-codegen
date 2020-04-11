package com.shopify.graphql.support;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class QueryTest {
    @Test
    public void testStringEscaping() throws Exception {
        StringBuilder result = new StringBuilder();
        QueryBase.appendQuotedString(result, "\0 \r \n \\ \" c ꝏ");
        assertEquals("\"\\u0000 \\r \\n \\\\ \\\" c ꝏ\"", result.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidAliasWithUnderscore() {
        new QueryBase<QueryBase>(null) {}.withAlias("invalid__alias");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidAliasWithDashes() {
        new QueryBase<QueryBase>(null) {}.withAlias("invalid-alias");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBlankAlias() {
        new QueryBase<QueryBase>(null) {}.withAlias("");
    }
}
