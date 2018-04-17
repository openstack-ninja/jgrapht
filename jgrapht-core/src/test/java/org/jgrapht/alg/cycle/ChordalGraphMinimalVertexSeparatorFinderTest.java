/*
 * (C) Copyright 2018-2018, by Timofey Chudakov and Contributors.
 *
 * JGraphT : a free Java graph-theory library
 *
 * This program and the accompanying materials are dual-licensed under
 * either
 *
 * (a) the terms of the GNU Lesser General Public License version 2.1
 * as published by the Free Software Foundation, or (at your option) any
 * later version.
 *
 * or (per the licensee's choosing)
 *
 * (b) the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation.
 */
package org.jgrapht.alg.cycle;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;
import org.jgrapht.graph.Pseudograph;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Tests for the {@link ChordalGraphMinimalVertexSeparatorFinder}
 *
 * @author Timofey Chudakov
 */
public class ChordalGraphMinimalVertexSeparatorFinderTest {

    /**
     * Test on empty graph
     */
    @Test
    public void testGetMinimalSeparators1() {
        Graph<Integer, DefaultEdge> graph = new DefaultUndirectedGraph<>(DefaultEdge.class);
        ChordalGraphMinimalVertexSeparatorFinder<Integer, DefaultEdge> finder = new ChordalGraphMinimalVertexSeparatorFinder<>(graph);
        Map<Set<Integer>, Integer> separators = finder.getMinimalSeparators();
        assertEquals(0, separators.size());
    }

    /**
     * Test on small chordal graph
     */
    @Test
    public void testGetMinimalSeparators2() {
        Graph<Integer, DefaultEdge> graph = new DefaultUndirectedGraph<>(DefaultEdge.class);
        Graphs.addEdgeWithVertices(graph, 1, 2);
        Graphs.addEdgeWithVertices(graph, 1, 3);
        Graphs.addEdgeWithVertices(graph, 2, 3);
        Graphs.addEdgeWithVertices(graph, 2, 4);
        Graphs.addEdgeWithVertices(graph, 3, 4);
        ChordalGraphMinimalVertexSeparatorFinder<Integer, DefaultEdge> finder = new ChordalGraphMinimalVertexSeparatorFinder<>(graph);
        Map<Set<Integer>, Integer> separators = finder.getMinimalSeparators();
        assertEquals(1, separators.size());
        assertTrue(separators.containsKey(new HashSet<>(Arrays.asList(2, 3))));
    }

    /**
     * Test on big chordal graph (example from original article)
     */
    @Test
    public void testGetMinimalSeparators3() {
        Graph<Integer, DefaultEdge> graph = new DefaultUndirectedGraph<>(DefaultEdge.class);
        Graphs.addEdgeWithVertices(graph, 1, 2);
        Graphs.addEdgeWithVertices(graph, 1, 3);
        Graphs.addEdgeWithVertices(graph, 2, 3);
        Graphs.addEdgeWithVertices(graph, 3, 4);
        Graphs.addEdgeWithVertices(graph, 3, 5);
        Graphs.addEdgeWithVertices(graph, 3, 6);
        Graphs.addEdgeWithVertices(graph, 3, 8);
        Graphs.addEdgeWithVertices(graph, 3, 10);
        Graphs.addEdgeWithVertices(graph, 3, 11);
        Graphs.addEdgeWithVertices(graph, 4, 5);
        Graphs.addEdgeWithVertices(graph, 4, 6);
        Graphs.addEdgeWithVertices(graph, 5, 6);
        Graphs.addEdgeWithVertices(graph, 6, 7);
        Graphs.addEdgeWithVertices(graph, 6, 8);
        Graphs.addEdgeWithVertices(graph, 6, 10);
        Graphs.addEdgeWithVertices(graph, 6, 11);
        Graphs.addEdgeWithVertices(graph, 7, 8);
        Graphs.addEdgeWithVertices(graph, 7, 10);
        Graphs.addEdgeWithVertices(graph, 8, 9);
        Graphs.addEdgeWithVertices(graph, 8, 10);
        Graphs.addEdgeWithVertices(graph, 9, 10);
        ChordalGraphMinimalVertexSeparatorFinder<Integer, DefaultEdge> finder = new ChordalGraphMinimalVertexSeparatorFinder<>(graph);
        Map<Set<Integer>, Integer> actual = finder.getMinimalSeparators();
        Map<Set<Integer>, Integer> expected = new HashMap<>();
        expected.put(new HashSet<>(Collections.singletonList(3)), 1);
        expected.put(new HashSet<>(new HashSet<>(Arrays.asList(3, 6))), 2);
        expected.put(new HashSet<>(new HashSet<>(Arrays.asList(8, 10))), 1);
        expected.put(new HashSet<>(new HashSet<>(Arrays.asList(6, 8, 10))), 1);
        assertEquals(expected, actual);
    }

    /**
     * Test on big chordal graph (example from original article)
     */
    @Test
    public void testGetMinimalSeparators4() {
        Graph<Integer, DefaultEdge> graph = new DefaultUndirectedGraph<>(DefaultEdge.class);
        Graphs.addEdgeWithVertices(graph, 1, 2);
        Graphs.addEdgeWithVertices(graph, 2, 8);
        Graphs.addEdgeWithVertices(graph, 2, 9);
        Graphs.addEdgeWithVertices(graph, 3, 8);
        Graphs.addEdgeWithVertices(graph, 3, 9);
        Graphs.addEdgeWithVertices(graph, 4, 6);
        Graphs.addEdgeWithVertices(graph, 4, 8);
        Graphs.addEdgeWithVertices(graph, 5, 6);
        Graphs.addEdgeWithVertices(graph, 5, 8);
        Graphs.addEdgeWithVertices(graph, 6, 7);
        Graphs.addEdgeWithVertices(graph, 6, 8);
        Graphs.addEdgeWithVertices(graph, 6, 9);
        Graphs.addEdgeWithVertices(graph, 7, 8);
        Graphs.addEdgeWithVertices(graph, 7, 9);
        Graphs.addEdgeWithVertices(graph, 8, 9);
        Graphs.addEdgeWithVertices(graph, 8, 10);
        Graphs.addEdgeWithVertices(graph, 8, 11);
        Graphs.addEdgeWithVertices(graph, 8, 12);
        Graphs.addEdgeWithVertices(graph, 9, 10);
        Graphs.addEdgeWithVertices(graph, 9, 11);
        Graphs.addEdgeWithVertices(graph, 9, 12);
        Graphs.addEdgeWithVertices(graph, 10, 11);
        Graphs.addEdgeWithVertices(graph, 11, 12);
        ChordalGraphMinimalVertexSeparatorFinder<Integer, DefaultEdge> finder = new ChordalGraphMinimalVertexSeparatorFinder<>(graph);
        Map<Set<Integer>, Integer> actual = finder.getMinimalSeparators();
        Map<Set<Integer>, Integer> expected = new HashMap<>();
        expected.put(new HashSet<>(Collections.singletonList(2)), 1);
        expected.put(new HashSet<>(Arrays.asList(6, 8)), 2);
        expected.put(new HashSet<>(Arrays.asList(8, 9)), 3);
        expected.put(new HashSet<>(Arrays.asList(8, 9, 11)), 1);
        assertEquals(expected, actual);
    }

    /**
     * Test on not chordal graph
     */
    @Test
    public void testGetMinimalSeparators5() {
        Graph<Integer, DefaultEdge> graph = new DefaultUndirectedGraph<>(DefaultEdge.class);
        Graphs.addEdgeWithVertices(graph, 1, 2);
        Graphs.addEdgeWithVertices(graph, 1, 3);
        Graphs.addEdgeWithVertices(graph, 2, 4);
        Graphs.addEdgeWithVertices(graph, 3, 4);
        ChordalGraphMinimalVertexSeparatorFinder<Integer, DefaultEdge> finder = new ChordalGraphMinimalVertexSeparatorFinder<>(graph);
        Map<Set<Integer>, Integer> actual = finder.getMinimalSeparators();
        assertNull(actual);
    }

    /**
     * Test on pseudograph
     */
    @Test
    public void testGetMinimalSeparators6() {
        Graph<Integer, DefaultEdge> graph = new Pseudograph<>(DefaultEdge.class);
        Graphs.addEdgeWithVertices(graph, 1, 1);
        Graphs.addEdgeWithVertices(graph, 1, 1);
        Graphs.addEdgeWithVertices(graph, 1, 2);
        Graphs.addEdgeWithVertices(graph, 2, 3);
        Graphs.addEdgeWithVertices(graph, 2, 3);
        Graphs.addEdgeWithVertices(graph, 2, 3);
        Graphs.addEdgeWithVertices(graph, 2, 5);
        Graphs.addEdgeWithVertices(graph, 3, 3);
        Graphs.addEdgeWithVertices(graph, 3, 4);
        Graphs.addEdgeWithVertices(graph, 5, 3);
        Graphs.addEdgeWithVertices(graph, 5, 3);
        Graphs.addEdgeWithVertices(graph, 5, 4);
        ChordalGraphMinimalVertexSeparatorFinder<Integer, DefaultEdge> finder = new ChordalGraphMinimalVertexSeparatorFinder<>(graph);
        Map<Set<Integer>, Integer> actual = finder.getMinimalSeparators();
        Map<Set<Integer>, Integer> expected = new HashMap<>();
        expected.put(new HashSet<>(Collections.singletonList(2)), 1);
        expected.put(new HashSet<>(Arrays.asList(3, 5)), 1);
        assertEquals(expected, actual);
    }

}
