package com.sagar.twitter;

import java.util.List;

/**
 * The interface Hash tag counter.
 */
public interface HashTagCounter {
    /**
     * Add hash tag.
     *
     * @param value the value
     */
    void addHashTag(String value);

    /**
     * Gets top hash tags.
     *
     * @param limit the limit
     * @return the top hash tags
     */
    List<HashTag> getTopHashTags(int limit);
}
