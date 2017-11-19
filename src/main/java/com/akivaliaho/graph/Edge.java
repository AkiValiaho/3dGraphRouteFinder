package com.akivaliaho.graph;

import com.akivaliaho.threed.DirectionalCylinder;
import javafx.scene.shape.Sphere;

import java.util.List;
import java.util.Map;

/**
 * Created by Aki on 19.11.2017.
 */
public class Edge {
    private final Sphere sphere;
    private final List<DirectionalCylinder> verticesTo;

    public Edge(Map.Entry<Sphere, List<DirectionalCylinder>> sphereListEntry) {
        this.sphere = sphereListEntry.getKey();
        this.verticesTo = sphereListEntry.getValue();
    }
}
