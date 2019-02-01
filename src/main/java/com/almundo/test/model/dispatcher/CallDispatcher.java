package com.almundo.test.model.dispatcher;

import com.almundo.test.model.Call;

/**
 * 
 * @author Daniel Sarmiento
 *
 */
public interface CallDispatcher {

	/**
	 * 
	 * @param call
	 * @param idCall
	 */
	void dispatchCall(Call call,Integer idCall);
	
}
