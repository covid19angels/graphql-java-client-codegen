package com.shopify.graphql.support;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;

import static org.junit.jupiter.api.Assertions.*;


public class UtilsTest {


    @Test
    public void parse_with9NanoSecondFraction_shouldPass() {
        try {
            String actual = "2017-05-31T20:00:01.123456789+10:01";
            OffsetDateTime o = Utils.parseRfc3339DateTime(actual);
            String of = o.format(Utils.getRfc3339DateTimeFormatter());
            assertEquals(of, actual);
        } catch (final DateTimeParseException ex) {
            fail("9 Nano second fraction should pass");
        }
    }

    @Test
    public void parse_with9NanoSecondFraction_OffsetZeroMinute_shouldPass() {
        try {
            String actual = "2017-05-31T20:00:01.123456789+10:00";
            OffsetDateTime o = Utils.parseRfc3339DateTime(actual);
        } catch (final DateTimeParseException ex) {
            fail("9 Nano second fraction offset zero minute should pass");
        }
    }

    @Test
    public void parse_with3NanoSecondFraction_shouldPass() {
        try {
            Utils.parseRfc3339DateTime("2017-05-31T20:00:01.123+10:00");
        } catch (final DateTimeParseException ex) {
            fail("3 Nano second fraction should pass");
        }
    }

    @Test
    public void parse_withoutSecondFraction_shouldPass() {
        try {
            Utils.parseRfc3339DateTime("2017-05-31T20:00:01+10:00");
        } catch (final DateTimeParseException ex) {
            fail("No second fraction should pass");
        }
    }

    @Test
    public void parse_withZeroOffset_shouldPass() {
        try {
            Utils.parseRfc3339DateTime("2017-05-31T20:00:01.123Z");
        } catch (final DateTimeParseException ex) {
            fail("Zero date-time offset should pass");
        }
    }

    @Test
    public void parse_withoutSecondFractionZeroOffset_shouldPass() {
        try {
            Utils.parseRfc3339DateTime("2017-05-31T23:45:01Z");
        } catch (final DateTimeParseException ex) {
            fail("No second fraction should pass");
        }
    }

    @Test
    public void parse_withNegativeOffset_shouldPass() {
        try {
            Utils.parseRfc3339DateTime("2017-05-31T20:00:01.123456789-05:30");
        } catch (final DateTimeParseException ex) {
            fail("Negative date time offset should pass");
        }
    }

    @Test
    public void parse_shouldReturnSameInstantAsInput() {
        final String datetime = "2017-05-31T20:00:01.123456789Z";
        final Instant instant = Instant.parse(datetime);
        try {
            final TemporalAccessor temporalAccessor = Utils.parseRfc3339DateTime(datetime);
            assertTrue(Instant.from(temporalAccessor).compareTo(instant) == 0);
        } catch (final DateTimeParseException ex) {
            fail("Correct format should be parsedd successfully");
        }

    }

    @Test
    public void parse_withoutSecond_shouldFail() {
        Assertions.assertThrows(DateTimeParseException.class, () ->
                Utils.parseRfc3339DateTime("2017-05-31T23:45Z"));
    }

    @Test
    public void parse_withoutOffset_shouldFail() {
        Assertions.assertThrows(DateTimeParseException.class, () ->
                Utils.parseRfc3339DateTime("2017-05-31T23:45:20.12345"));
    }

    @Test
    public void parse_wrongDateTime_shouldFail() throws DateTimeParseException {
        Assertions.assertThrows(DateTimeParseException.class, () ->
                Utils.parseRfc3339DateTime("2017:05:31T23:45:20.12345Z"));
    }

    @Test
    public void parse_withTimeZone_shouldFail() throws DateTimeParseException {
        Assertions.assertThrows(DateTimeParseException.class, () ->
                Utils.parseRfc3339DateTime("2017-05-31T23:45:20.123Z[Europe/Paris]"));
    }
}