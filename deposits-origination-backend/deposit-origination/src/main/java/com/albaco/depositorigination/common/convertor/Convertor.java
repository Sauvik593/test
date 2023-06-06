package com.albaco.depositorigination.common.convertor;

import lombok.NonNull;

import java.util.List;
import java.util.function.Function;

public interface Convertor<T, S> {
    default public T convert(S source)
    {
        if(source==null) return null;
        return convertNonNull(source);
    }

    public T convertNonNull(@NonNull S source);
    public default List<T> convertList(java.util.List<S> sourceList) {
        if(sourceList==null) return null;
        return sourceList.stream().map(this::convert).toList();
    }
}
