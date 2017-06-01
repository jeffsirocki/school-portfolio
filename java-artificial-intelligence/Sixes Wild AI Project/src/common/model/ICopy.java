package common.model;

import java.io.Serializable;

/**
 * Interface to show copyable behavior.
 * @author njpanzarino
 * @author jasirocki - jdoc
 * @author jmmckinney
 *
 * @param <T>
 */
public interface ICopy extends Serializable{
	
	/**
	 * Serializable ID.
	 */
	public static final long serialVersionUID = -8303954416976988023L;

	/**
	 * Copy method without references.
	 * @return T
	 */
	public <T extends ICopy> void copy(T object);
	
	/**
	 * Make a copy without references and return it.
	 * @return
	 */
	public <T extends ICopy> T makeCopy();
}
