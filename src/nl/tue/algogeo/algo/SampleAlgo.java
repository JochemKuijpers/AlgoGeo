package nl.tue.algogeo.algo;

import nl.tue.algogeo.Dot;
import nl.tue.algogeo.DotMap;

public class SampleAlgo implements DotMapScaler {
    @Override
    public DotMap scaleDotMap(DotMap original, int newWidth, int newHeight) {
        DotMap newMap = new DotMap(newWidth, newHeight);

        for (int y = 0; y < newHeight; y++) {
            for (int x = 0; x < newWidth; x++) {
                Dot dot = original.get(x, y);
                if (dot != null) {
                    newMap.put(x, y, dot);
                }
            }
        }

        return newMap;
    }
}
