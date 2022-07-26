package abyss.plugin.api.query.results;

import abyss.plugin.api.game.chat.ChatMessage;
import kotlin.random.Random;
import kotlin.random.RandomKt;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ChatResultSet implements ResultSet<ChatMessage> {

    private final List<ChatMessage> results;

    public ChatResultSet(List<ChatMessage> list) {
        this.results = list;
    }

    @Override
    public Optional<ChatMessage> first() {
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    @Override
    public Optional<ChatMessage> last() {
        if(results.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(results.get(results.size() - 1));
    }

    @Override
    public List<ChatMessage> sort(Comparator<ChatMessage> comparator) {
        return stream().sorted(comparator).collect(Collectors.toList());
    }

    @Override
    public Optional<ChatMessage> get(int index) {
        if(results.isEmpty() || index < 0 || index >= results.size()) {
            return Optional.empty();
        }
        return Optional.of(results.get(index));
    }

    @Override
    public Optional<ChatMessage> random() {
        if(results.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(results.get(random.nextInt(0, results.size())));
    }

    @Override
    public Stream<ChatMessage> stream() {
        return results.stream();
    }

    @NotNull
    @Override
    public Iterator<ChatMessage> iterator() {
        return results.iterator();
    }
}