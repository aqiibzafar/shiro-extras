package eu.flatwhite.shiro.spatial.finite;

import eu.flatwhite.shiro.spatial.Space;
import eu.flatwhite.shiro.spatial.Spatial;
import eu.flatwhite.shiro.spatial.SpatialResolver;

/**
 * Resolves {@code Node} instances from a {@code String}. This implementation
 * delegates to {@link Node#parseString(NodeSpace, String)}.
 * 
 * @author philippe.laflamme@gmail.com
 */
public class NodeResolver implements SpatialResolver {

  private static final long serialVersionUID = 6021348876066661151L;

  @Override
    public Spatial resolveSpatial(Space space, String spatialString) {
	return Node.parseString((NodeSpace) space, spatialString);
    }

}
