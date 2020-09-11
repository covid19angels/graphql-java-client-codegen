package com.shopify.graphql.support;

// Helpful to have this common base class for Relay-compatible schemas. Unused otherwise.
public interface Node {
    ID getId();
}
