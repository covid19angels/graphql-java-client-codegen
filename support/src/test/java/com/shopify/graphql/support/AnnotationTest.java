package com.shopify.graphql.support;


import java.lang.reflect.Method;


import com.shopify.graphql.support.Generated;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnnotationTest {
    @Test
    public void testAppliesAnnotation() throws Exception {
        Class<Generated> obj = Generated.class;
        boolean foundNullable = false;
        for (Class klass : obj.getDeclaredClasses()) {
            for (Method method : klass.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Nullable.class)) {
                    foundNullable = true;
                    break;
                }
            }
        }
        assertTrue(foundNullable, "Should have found a class with @Nullable annotation in Generated.java");
    }
}
