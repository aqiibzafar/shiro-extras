package eu.flatwhite.shiro.spatial.finite;

import eu.flatwhite.shiro.spatial.AbstractSpace;
import eu.flatwhite.shiro.spatial.Spatial;

/**
 * A space defined on n-tree (just like URI or FS paths are). The space between
 * two nodes is defined only if they lay on same "path" (a sequence of nodes
 * from root to the node being last node of the biggest path, biggest chain of
 * nodes). Hence, one of the two compared path must contain the other as
 * "prefix".
 * 
 * @author cstamas
 */
public class NodeSpace extends AbstractSpace {
    public Node getOrigin() {
	return new Node(this);
    }

    public boolean isContaining(Spatial spatial) {
	return spatial instanceof Node;
    }

    @Override
    protected double calculateDistance(Spatial s1, Spatial s2) {
	Node p1 = (Node) s1;

	Node p2 = (Node) s2;

	// I am going the "easy" way ;)
	// not this
	// http://www.topcoder.com/tc?module=Static&d1=tutorials&d2=lowestCommonAncestor

	// so, my assumption is, that this and spatial should be on same chain
	// (chain := a list of nodes from root
	// to the node of Node having longer path, meaning, either 'this' is
	// included in spatial, or spatial is
	// included in 'this'). For nodes lying on different chains, i will say
	// NaN for now.

	// Append an additional PATH_SEPARATOR to make sure we don't consider 
	// /a/foo and /a/fo on the same path
	final String thisPathString = p1.getPathString() + Node.PATH_SEPARATOR;
	final String thatPathString = p2.getPathString() + Node.PATH_SEPARATOR;

	// both begins with ROOT, so check for inclusion from one side
	if (thisPathString.startsWith(thatPathString)
		|| thatPathString.startsWith(thisPathString)) {
	    // this path includes spatial path
	    return Math.abs(p1.getPath().size() - p2.getPath().size());
	} else {
	    return Double.NaN;
	}
    }
}
