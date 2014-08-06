/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.general.json;

/**
 *
 * @author knauth
 */
public class JUtils
{

	////////////////////////////////////////////////////////////////
	// Constants
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

	/**
	 * Constructor.
	 */
	private JUtils()
	{
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	public static boolean isValidPath(String path)
	{
		if (!path.startsWith("/")) return false;
		if (path.endsWith("/")) return false;
		return true;
	}

	private static String[] splitPath(String path)
	{
		isValidPathE(path);

		String[] ret = path.substring(1).split("/");
		return ret;
	}

	public static void isValidPathE(String path)
	{
		if (!path.startsWith("/")) throw new RuntimeException("Not a valid path!");
		if (path.endsWith("/")) throw new RuntimeException("Not a valid path!");
	}

	/**
	 * This method returns an array of Strings representing the information stored at the specified path within
	 * the specified object. If the element specified by path is a string value it will be returned as an array of one
	 * element in size. If the element specified by path is an JArray of string values the data is converted to a string
	 * array and returned. In all other cases either <code>null</code> is returned or an exception is thrown.
	 *
	 * @param	obj		The object data is to be retrieved from
	 * @param	path	A path to a node within the specified object, f.e. "/some/element/at/some/leaf"
	 * @return	Returns <code>null</code> if the property does not exist or has no string data.
	 */
	public static String[] getStrArrayByPath(JObject obj, String path)
	{
		String[] pathElements = splitPath(path);

		JObject current = obj;
		for (int i = 0; i < pathElements.length - 1; i++) {
			JProperty p = current.getProperty(pathElements[i]);
			if (p == null) return null;
			if (p.value instanceof JObject) {
				current = (JObject)(p.value);
			} else {
				return null;
			}
		}

		JProperty p2 = current.getProperty(pathElements[pathElements.length - 1]);
		if (p2 == null) return null;

		JToken t = p2.getValue();
		if (t instanceof JValue) {
			JValue v = (JValue)t;
			if (v.getValue() instanceof String) {
				return new String[] { (String)(v.getValue()) };
			} else {
				return null;
			}
		} else
		if (t instanceof JArray) {

			JArray a = (JArray)t;

			String[] ret = new String[a.getCount()];
			for (int i = 0; i < a.getCount(); i++) {
				JToken t2 = a.get(i);
				if (t2 instanceof JValue) {
					JValue v = (JValue)t2;

					if (v.getValue() instanceof String) {
						ret[i] = (String)(v.getValue());
					} else {
						throw new RuntimeException("Array value at index " + i + " not of type String!");
					}
				} else {
					throw new RuntimeException("Array element at index " + i + " not of type JValue!");
				}
			}
			return ret;

		} else {
			return null;
		}
	}

}
