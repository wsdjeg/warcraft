/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.collection;

import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

/**
 * Provides utility functions for working with Collections.
 *
 * @author Blair Butterworth
 */
public class CollectionUtils
{
    private CollectionUtils() {
    }

    public static <T> boolean containsAny(Collection<T> collection, Predicate<T> condition) {
        return collection.stream().anyMatch(condition);
    }

    public static <T> boolean containsAll(Collection<T> collection, Predicate<T> condition) {
        return collection.stream().allMatch(condition);
    }

    public static <T> boolean containsEqual(Collection<T> collection, Predicate<T> conditionA, Predicate<T> conditionB){
        Collection<T> matchesA = collection.stream().filter(conditionA).collect(toList());
        Collection<T> matchesB = collection.stream().filter(conditionB).collect(toList());
        return matchesA.size() == matchesB.size();
    }

    public static <T> List<T> combine(Collection<T> collectionA, Collection<T> collectionB) {
        List<T> result = new ArrayList<>(collectionA.size() + collectionB.size());
        result.addAll(collectionA);
        result.addAll(collectionB);
        return result;
    }

    public static <T> T findFirst(Collection<? extends T> collection, Predicate<T> condition) {
        return collection.stream().filter(condition).findFirst().orElse(null);
    }

    public static <A, B> List<B> flatten(Collection<A> collection, Function<A, Collection<B>> converter) {
        return collection.stream().flatMap(element -> converter.apply(element).stream()).collect(toList());
    }

    public static <A> List<A> flatten(Collection<Array<A>> collection) {
        return collection.stream().flatMap(array -> Arrays.stream(array.items)).collect(toList());
    }

    /**
     * Returns a list consisting of the elements of the given collection that
     * match the given predicate.
     */
    public static <T> List<T> filter(Collection<T> collection, Predicate<T> condition) {
        return collection.stream().filter(condition).collect(toList());
    }

    public static <A, B> List<B> convert(Collection<A> collection, Function<A, B> converter) {
        return collection.stream().map(converter).collect(toList());
    }

    public static <T> int testMatches(T[] collection, Predicate<T> condition) {
        int result = 0;
        for (T element: collection) {
            if (condition.test(element)) {
                result++;
            }
        }
        return result;
    }
}
