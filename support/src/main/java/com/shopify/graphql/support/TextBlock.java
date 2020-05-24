package com.shopify.graphql.support;


public class TextBlock {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private StringBuilder stringBuilder;

        Builder() {
            stringBuilder = new StringBuilder();
        }

        public Builder l(String line) {
            if (line == null) {
                throw new NullPointerException("line must be non-null but is null");
            } else {
                this.stringBuilder.append(line).append("\n");
                return this;
            }
        }

        public String build() {
            return stringBuilder.toString();
        }

        public String toString() {
            return stringBuilder.toString();
        }
    }
}
