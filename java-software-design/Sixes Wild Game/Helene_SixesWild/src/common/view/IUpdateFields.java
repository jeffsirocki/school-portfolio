package common.view;

import builder.controllers.BuilderViewReference;

/**
 * Interface for all classes that share behavior to update themselves.
 * @author npanzarino
 * @author jasirocki - jdoc
 *
 */
@SuppressWarnings("unused")
public interface IUpdateFields {

	/**
	 * causes all fields to update to reflect a change in model state.
	 * After a controller performs a change on the BuilderModel, it should call this function on the BuilderView.
	 */
	void updateFields();
}
