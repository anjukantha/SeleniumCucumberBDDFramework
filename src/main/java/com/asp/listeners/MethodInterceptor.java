/**
 * 
 */
package com.asp.listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import com.asp.utils.ExcelUtils;

/**
 * Implements {@link org.testng.IMethodInterceptor} to leverage the abstract
 * methods, mostly used to read the data from excel and decide on which test
 * needs to run
 * 
 * Please make sure to add the listener details on {@link testng.xml}
 * 
 * @author Anjan S P
 *
 */
public class MethodInterceptor implements IMethodInterceptor {

	@Override
	public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
		List<Map<String, String>> list = ExcelUtils.getAllDataOfSheet("Master");
		List<IMethodInstance> result = new ArrayList<>();
		for (int i = 0; i < methods.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				if (methods.get(i).getMethod().getMethodName().equalsIgnoreCase(list.get(j).get("Test Case Name"))
						&& list.get(j).get("Execute").equalsIgnoreCase("yes")) {
					result.add(methods.get(i));
				}
			}
		}
		return result;
	}

}
