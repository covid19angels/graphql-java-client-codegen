package com.shopify.graphql.support;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class TextBlockTest {


    @Test
    public void building_textblock_shouldbe_multi_lines() {
        try {
            String expected = "aaaaaaaaa\n" +
                    "  bbbbbbb\n" +
                    "     cccccc\n";
            String actual = TextBlock.builder()
                    .l("aaaaaaaaa")
                    .l("  bbbbbbb")
                    .l("     cccccc")
                    .build();
            assertEquals(expected, actual);
        } catch (final DateTimeParseException ex) {
            fail("text block should be multi lines should pass");
        }
    }

}