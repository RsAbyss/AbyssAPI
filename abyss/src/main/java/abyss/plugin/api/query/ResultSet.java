package abyss.plugin.api.query;

import abyss.plugin.api.Vector3i;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface ResultSet<T> extends Iterable<T> {

    Optional<T> first();
    Optional<T> last();

    List<T> sort(Comparator<T> comparator);

    Optional<T> get(int index);

    Stream<T> stream();

}