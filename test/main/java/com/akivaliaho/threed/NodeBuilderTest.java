package com.akivaliaho.threed;

import javafx.scene.SubScene;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Created by Aki on 5.12.2017.
 */
@Slf4j
public class NodeBuilderTest {
    private NodeBuilder nodeBuilder;
    private SubScene subScene;

    @Before
    public void setUp() throws Exception {
        this.subScene = mock(SubScene.class);
        this.nodeBuilder = new NodeBuilder(subScene);
    }

    @Test
    public void buildGraph() throws Exception {
        log.info("testing build graph");
        nodeBuilder.buildGraph();
        //verify axis group added to the world
        final Transformer axisGroup = RefletionHelper.getField("axisGroup", nodeBuilder);
        assertEquals(axisGroup.getChildren().size(), 3);
        //Verify graph component group has graph transformer as a child
        //TODO
    }

    @Test
    public void getEdges() throws Exception {
    }

}