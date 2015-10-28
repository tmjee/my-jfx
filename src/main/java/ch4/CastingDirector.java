package ch4;

import java.util.*;

public class CastingDirector {

    private List<Actor> currentCasts;
    private List<Actor> collideCheckList;
    private Set<Actor> removedActors;

    public CastingDirector() {
        currentCasts = new ArrayList<>();
        collideCheckList = new ArrayList<>();
        removedActors = new HashSet<>();
    }

    public void addCurrentCast(Actor... actors) {
        currentCasts.addAll(Arrays.asList(actors));
    }

    public List<Actor> getCurrentCasts() {
        return currentCasts;
    }

    public void removeCurrentCast(Actor... actors) {
        currentCasts.removeAll(Arrays.asList(actors));
    }

    public void resetCurrentCast() {
        currentCasts.clear();
    }

    public List<Actor> getCollideCheckList() {
        return collideCheckList;
    }

    public void resetCollideCheckList() {
        collideCheckList.clear();
        collideCheckList.addAll(currentCasts);
    }

    public void addToRemovedActors(Actor... actors) {
        removedActors.addAll(Arrays.asList(actors));
    }

    public Set<Actor> getRemovedActors() {
        return removedActors;
    }

    public void resetRemovedActors() {
       currentCasts.removeAll(removedActors);
        removedActors.clear();
    }
}
