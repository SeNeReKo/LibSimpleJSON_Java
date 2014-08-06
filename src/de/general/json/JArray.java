package de.general.json;


import java.util.*;


/**
 *
 * @author knauth
 */
public class JArray extends JContainer
{

	////////////////////////////////////////////////////////////////
	// Properties
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	ArrayList<JToken> elements;

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

	/**
	 * Constructor.
	 */
	public JArray()
	{
		elements = new ArrayList<JToken>();
	}

	/**
	 * Constructor.
	 */
	public JArray(JToken[] items)
	{
		elements = new ArrayList<JToken>();
		addAll(items);
	}

	public JArray(JSONArray array) throws Exception
	{
		elements = new ArrayList<JToken>();

		for (int i = 0; i < array.length(); i++) {
			Object value = array.get(i);
			if (value instanceof JToken) {
				add((JToken)value);
			} else
			if (value instanceof JSONObject) {
				add(new JObject((JSONObject)value));
			} else
			if (value instanceof JSONArray) {
				add(new JArray((JSONArray)value));
			} else
			if (value instanceof Boolean) {
				add(new JValue((Boolean)value));
			} else
			if (value instanceof Double) {
				add(new JValue((Double)value));
			} else
			if (value instanceof Integer) {
				add(new JValue((Integer)value));
			} else
			if (value instanceof Long) {
				add(new JValue((Long)value));
			} else
			if (value instanceof String) {
				add(new JValue((String)value));
			} else {
				throw new Exception("Unknown value type: " + value.getClass().getName());
			}
		}
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	public void add(JToken item)
	{
		elements.add(item);
	}

	public void addAll(JToken[] items)
	{
		for (int i = 0; i < items.length; i++) {
			elements.add(items[i]);
		}
	}

	public void clear()
	{
		elements.clear();
	}

	@Override
	public boolean hasValues()
	{
		return elements.size() > 0;
	}

	@Override
	public int getCount()
	{
		return elements.size();
	}

	public int indexOf(JToken item)
	{
		return elements.indexOf(item);
	}

	public void insert(int index, JToken item)
	{
		elements.add(index, item);
	}

	public void remove(JToken token)
	{
		String s = token.toString();
		for (int i = 0; i < elements.size(); i++) {
			if (s.equals(elements.get(i).toString())) {
				elements.remove(i);
				return;
			}
		}
	}

	public void removeAt(int index)
	{
		elements.remove(index);
	}

	public void remove(int index)
	{
		elements.remove(index);
	}

	public JToken get(int index)
	{
		return elements.get(index);
	}


	@Override
	public String toString()
	{
		return "JArray[" + elements.size() + " elements]";
	}

	/**
	 * Perform a deep cloning.
	 */
	@Override
	public JToken cloneObject()
	{
		JArray ret = new JArray();
		for (JToken p : elements) {
			ret.add((p != null) ? p.cloneObject() : null);
		}
		return ret;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof JArray) {
			JArray a = (JArray)obj;
			return elements.equals(a.elements);
		} else {
			return false;
		}
	}

	@Override
	public String toJSON()
	{
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		boolean bAddComma = false;
		for (JToken t : elements) {

			if (bAddComma) sb.append(',');
			sb.append(t.toJSON());

			bAddComma = true;
		}
		sb.append(']');
		return sb.toString();
	}

}
