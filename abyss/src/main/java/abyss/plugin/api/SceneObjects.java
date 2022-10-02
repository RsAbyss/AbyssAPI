package abyss.plugin.api;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * A provider of objects.
 * <p>
 * These methods may increase RAM usage significantly due to the amount of objects available
 * in the scene.
 */
public final class SceneObjects {

    private SceneObjects() {
    }

    /**
     * Retrieves all objects.
     *
     * @return All objects in the scene.
     */
    public static native SceneObject[] all();

    /**
     * Retrieves all effects that match the provided filter.
     *
     * @return All effects that match the provided filter.
     */
    public static SceneObject[] all(Predicate<SceneObject> filter) {
        List<SceneObject> filtered = new LinkedList<>();
        for (SceneObject o : all()) {
            if (filter.test(o)) {
                filtered.add(o);
            }
        }
        return filtered.toArray(new SceneObject[0]);
    }

    /**
     * Finds the closest object matching the provided filter.
     *
     * @param filter The filter objects must pass through in order to be accepted.
     * @return The found object, or NULL if one was not found.
     */
    public static SceneObject closest(Predicate<SceneObject> filter) {
        Player self = Players.self();
        if (self == null) {
            return null;
        }

        Vector3 center = self.getScenePosition();
        SceneObject closest = null;
        float closestDistance = 0.f;
        for (SceneObject o : all(filter)) {
            float distance = o.getScenePosition().distance(center);
            if (closest == null || distance < closestDistance) {
                closest = o;
                closestDistance = distance;
            }
        }

        return closest;
    }

    /**
     * Iterates over each object.
     *
     * @param cb The callback for invoke for each object.
     */
    public static void forEach(Consumer<SceneObject> cb) {
        for (SceneObject o : all()) {
            cb.accept(o);
        }
    }

}