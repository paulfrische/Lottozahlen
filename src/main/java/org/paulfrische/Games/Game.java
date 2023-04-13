package org.paulfrische.Games;

import java.util.ArrayList;
import java.util.List;

public interface Game {
    public List<Short> generateNumbers();
    public List<Short> getBadNumbers();
}
