package io.github.violaceusflame.mapper;

import java.util.function.Function;

public interface MessageMapper<T extends RuntimeException> extends Function<T, String> {
}
