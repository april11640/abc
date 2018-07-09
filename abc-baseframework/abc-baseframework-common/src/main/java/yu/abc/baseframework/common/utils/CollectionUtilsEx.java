package yu.abc.baseframework.common.utils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * extension functions for collection utilities
 *
 * Created by zsp on 2018/7/9.
 */
public class CollectionUtilsEx {

    public static <TType> boolean isEmpty(Collection<TType> col) {
        return col == null || col.size() == 0;
    }

    public static  <TType> boolean isNotEmpty(Collection<TType> col) {
        return !isEmpty(col);
    }

    public static <TType> boolean isEmpty(TType... array) {
        return array == null || array.length == 0;
    }

    public static  <TType> boolean isNotEmpty(TType... array) {
        return !isEmpty(array);
    }

    /**
     *
     */
    public static <TType, TField> Set<TField> listField(Collection<TType> col,
                                                        Function<TType, TField> fieldFetcher) {
        return listField(col, fieldFetcher, null);
    }

    /**
     *
     */
    public static <TType, TField> Set<TField> listField(Collection<TType> col,
                                                        Function<TType, TField> fieldFetcher,
                                                        Predicate<TType>... filters) {
        Stream<TType> stream = buildStreamForlistField(col, fieldFetcher, filters);
        return stream.map(fieldFetcher).distinct().collect(Collectors.toSet());
    }

    /**
     *
     */
    public static <TType, TField> Collection<TField> listField(Collection<TType> col,
                                                         Function<TType, TField> fieldFetcher,
                                                         boolean distinct) {
        return listField(col, fieldFetcher, distinct,null);
    }

    /**
     *
     */
    public static <TType, TField> Collection<TField> listField(Collection<TType> col,
                                                         Function<TType, TField> fieldFetcher,
                                                         boolean distinct,
                                                         Predicate<TType>... filters) {
        Stream<TType> stream = buildStreamForlistField(col, fieldFetcher, filters);
        if (distinct) {
            return stream.map(fieldFetcher).distinct().collect(Collectors.toSet());
        } else {
            return stream.map(fieldFetcher).collect(Collectors.toList());
        }
    }

    private static <TType, TField> Stream<TType> buildStreamForlistField(Collection<TType> col,
                           Function<TType, TField> fieldFetcher,
                           Predicate<TType>... filters) {
        Objects.requireNonNull(col, "The col is null.");
        Objects.requireNonNull(fieldFetcher, "The fieldFetcher is null.");
        Stream<TType> stream = col.stream();
        if (isNotEmpty(filters)) {
            for(Predicate<TType> filter : filters) {
                stream.filter(filter);
            }
        }
        return stream;
    }

}
