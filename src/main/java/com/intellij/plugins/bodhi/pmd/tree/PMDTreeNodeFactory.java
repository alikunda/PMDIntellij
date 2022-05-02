package com.intellij.plugins.bodhi.pmd.tree;

import com.intellij.plugins.bodhi.pmd.PMDResultPanel;
import com.intellij.plugins.bodhi.pmd.core.PMDProcessingError;
import com.intellij.plugins.bodhi.pmd.core.PMDSuppressedViolation;
import com.intellij.plugins.bodhi.pmd.core.PMDViolation;

/**
 * A Factory that creates different types of tree nodes used by PMD plugin.
 * This is a singleton.
 *
 * @author bodhi
 * @version 1.0
 */
public class PMDTreeNodeFactory {

    // The singletone instance
    private static final PMDTreeNodeFactory factory = new PMDTreeNodeFactory();

    /**
     * Prevents instantiation. Only allows potential subclasses.
     */
    protected PMDTreeNodeFactory() {
    }

    /**
     * Get the singletone factory.
     * @return Get the factory
     */
    public static PMDTreeNodeFactory getInstance() {
        return factory;
    }

    /**
     * Creates a branch tree node object
     *
     * @param name the branch node name
     * @return The created node
     */
    public PMDBranchNode createBranchNode(String name) {
        return new PMDBranchNode(name);
    }

    /**
     * Creates a tree leaf node object for the violation
     *
     * @param violation PMDViolation that will be wrapped.
     * @return The created tree node 
     */
    public PMDViolationNode createViolationLeafNode(PMDViolation violation) {
        return new PMDViolationNode(violation);
    }

    /**
     * Creates a tree leaf node object for the suppressed violation
     *
     * @param suppressed the suppressed PMDViolation that will be wrapped.
     * @return The created tree node
     */
    public PMDSuppressedNode createSuppressedLeafNode(PMDSuppressedViolation suppressed) {
        return new PMDSuppressedNode(suppressed);
    }

    /**
     * Creates a tree leaf node object for the processing error
     *
     * @param error the PMDProcessingError that will be wrapped.
     * @return The created tree node
     */
    public PMDErrorNode createErrorLeafNode(PMDProcessingError error) {
        return new PMDErrorNode(error);
    }

    /**
     * Convinience method to create root node for PMD plugin tree.
     *
     * @param resultPanel The Panel where the tree resides
     * @return The tree node to be used as root.
     */
    public PMDRootNode createRootNode(PMDResultPanel resultPanel) {
        return new PMDRootNode(resultPanel);
    }


}
