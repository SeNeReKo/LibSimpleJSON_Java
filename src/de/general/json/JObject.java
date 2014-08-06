package de.general.json;


import java.io.*;
import java.util.HashMap;
import java.util.Iterator;


/**
 *
 * @author knauth
 */
public class JObject extends JContainer
{

	////////////////////////////////////////////////////////////////
	// Properties
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	HashMap<String, JProperty> properties;

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

	/**
	 * Constructor.
	 */
	public JObject()
	{
		this.properties = new HashMap<String, JProperty>();
	}

	/**
	 * Constructor.
	 */
	public JObject(JProperty property)
	{
		this.properties = new HashMap<String, JProperty>();
		this.properties.put(property.getName(), property);
	}

	/**
	 * Constructor.
	 */
	public JObject(JProperty p1, JProperty p2)
	{
		this.properties = new HashMap<String, JProperty>();
		this.properties.put(p1.getName(), p1);
		this.properties.put(p2.getName(), p2);
	}

	/**
	 * Constructor.
	 */
	public JObject(JProperty p1, JProperty p2, JProperty p3)
	{
		this.properties = new HashMap<String, JProperty>();
		this.properties.put(p1.getName(), p1);
		this.properties.put(p2.getName(), p2);
		this.properties.put(p3.getName(), p3);
	}

	/**
	 * Constructor.
	 */
	public JObject(JProperty p1, JProperty p2, JProperty p3, JProperty p4)
	{
		this.properties = new HashMap<String, JProperty>();
		this.properties.put(p1.getName(), p1);
		this.properties.put(p2.getName(), p2);
		this.properties.put(p3.getName(), p3);
		this.properties.put(p4.getName(), p4);
	}

	/**
	 * Constructor.
	 */
	public JObject(JProperty[] properties)
	{
		for (JProperty property : properties) {
			this.properties = new HashMap<String, JProperty>();
			this.properties.put(property.getName(), property);
		}
	}

	JObject(JSONObject obj) throws Exception
	{
		this.properties = new HashMap<String, JProperty>();
		Iterator iterator = obj.keys();
		while (iterator.hasNext()) {
			String key = (String)(iterator.next());
			properties.put(key, new JProperty(key, obj.get(key)));
		}
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	public void add(String propertyName, JToken value)
	{
		if ((propertyName == null) || (propertyName.length() == 0)
			|| (propertyName.trim().length() != propertyName.length()))
			throw new RuntimeException("Invalid property name specified: " + propertyName);

		JProperty p = properties.get(propertyName);
		if (p == null) {
			p = new JProperty(propertyName, value);
			properties.put(propertyName, p);
		} else {
			p.setValue(value);
		}
	}

	/**
	 * Convenience method to retrieve data from a property referring to a string value.
	 * If the property does not refer to a string value <code>null</code> is returned.
	 */
	public String getPropertyStringValueNormalized(String[] propertyNamePath)
	{
		JObject o = this;
		for (int i = 0; i < propertyNamePath.length - 1; i++) {
			JProperty p = o.getProperty(propertyNamePath[i]);
			if (p == null) return null;
			if (p.getValue() == null) return null;
			if (p.getValue() instanceof JObject) {
				o = (JObject)(p.getValue());
			} else {
				return null;
			}
		}
		return o.getPropertyStringValueNormalized(propertyNamePath[propertyNamePath.length - 1]);
	}

	/**
	 * Convenience method to retrieve data from a property referring to a string value.
	 * If the property does not refer to a string value <code>null</code> is returned.
	 */
	public String[] getPropertyStringListValueNormalized(String[] propertyNamePath)
	{
		JObject o = this;
		for (int i = 0; i < propertyNamePath.length - 1; i++) {
			JProperty p = o.getProperty(propertyNamePath[i]);
			if (p == null) return null;
			if (p.getValue() == null) return null;
			if (p.getValue() instanceof JObject) {
				o = (JObject)(p.getValue());
			} else {
				return null;
			}
		}
		return o.getPropertyStringListValueNormalized(propertyNamePath[propertyNamePath.length - 1]);
	}

	/**
	 * Convenience method to retrieve data from a property referring to a string value.
	 * If the property does not refer to a string value <code>null</code> is returned.
	 */
	public JObject[] getPropertyObjectList(String[] propertyNamePath)
	{
		JObject o = this;
		for (int i = 0; i < propertyNamePath.length - 1; i++) {
			JProperty p = o.getProperty(propertyNamePath[i]);
			if (p == null) return null;
			if (p.getValue() == null) return null;
			if (p.getValue() instanceof JObject) {
				o = (JObject)(p.getValue());
			} else {
				return null;
			}
		}
		return o.getPropertyObjectList(propertyNamePath[propertyNamePath.length - 1]);
	}

	/**
	 * Convenience method to retrieve data from a property referring to a string value.
	 * If the property does not refer to a string value <code>null</code> is returned.
	 */
	public Integer getPropertyIntValue(String[] propertyNamePath)
	{
		JObject o = this;
		for (int i = 0; i < propertyNamePath.length - 1; i++) {
			JProperty p = o.getProperty(propertyNamePath[i]);
			if (p == null) return null;
			if (p.getValue() == null) return null;
			if (p.getValue() instanceof JObject) {
				o = (JObject)(p.getValue());
			} else {
				return null;
			}
		}
		return o.getPropertyIntValue(propertyNamePath[propertyNamePath.length - 1]);
	}

	/**
	 * Convenience method to retrieve data from a property referring to a string value.
	 * If the property does not refer to a string value <code>null</code> is returned.
	 */
	public String getPropertyStringValueNormalized(String propertyName)
	{
		JProperty p = getProperty(propertyName);
		if (p == null) return null;
		if (p.getValue() == null) return null;
		if (p.getValue() instanceof JValue) {
			JValue v = (JValue)(p.getValue());
			if (v.getValue() == null) return null;
			if (v.getValue() instanceof String) {
				String s = (String)(v.getValue());
				s = s.trim();
				if (s.length() == 0) return null;
				return s;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * Convenience method to retrieve data from a property referring to a string value.
	 * If the property does not refer to a string value <code>null</code> is returned.
	 */
	public String[] getPropertyStringListValueNormalized(String propertyName)
	{
		JProperty p = getProperty(propertyName);
		if (p == null) return null;
		if (p.getValue() == null) return null;
		if (p.getValue() instanceof JArray) {
			JArray v = (JArray)(p.getValue());
			if (v == null) return null;
			String[] ret = new String[v.elements.size()];
			for (int i = 0; i < ret.length; i++) {
				if (v.get(i) instanceof JValue) {
					JValue v2 = (JValue)(v.get(i));
					if (v2.getValue() == null) continue;
					if (v2.getValue() instanceof String) {
						String s = (String)(v2.getValue());
						s = s.trim();
						if (s.length() == 0) s = null;
						ret[i] = s;
					} else {
						// invalid type
					}
				} else {
					// invalid type
				}
			}
			return ret;
		} else {
			// invalid type
			return null;
		}
	}

	/**
	 * Convenience method to retrieve data from a property referring to a string value.
	 * If the property does not refer to a string value <code>null</code> is returned.
	 */
	public JObject[] getPropertyObjectList(String propertyName)
	{
		JProperty p = getProperty(propertyName);
		if (p == null) return null;
		if (p.getValue() == null) return null;
		if (p.getValue() instanceof JArray) {
			JArray v = (JArray)(p.getValue());
			if (v == null) return null;
			JObject[] ret = new JObject[v.elements.size()];
			for (int i = 0; i < ret.length; i++) {
				if (v.get(i) instanceof JObject) {
					JObject v2 = (JObject)(v.get(i));
					ret[i] = v2;
				} else {
					// invalid type
				}
			}
			return ret;
		} else {
			// invalid type
			return null;
		}
	}

	/**
	 * Convenience method to retrieve data from a property referring to an integer value.
	 * If the property does not refer to a string value <code>null</code> is returned.
	 */
	public Integer getPropertyIntValue(String propertyName)
	{
		JProperty p = getProperty(propertyName);
		if (p == null) return null;
		if (p.getValue() == null) return null;
		if (p.getValue() instanceof JValue) {
			JValue v = (JValue)(p.getValue());
			if (v.getValue() == null) return null;
			if (v.getValue() instanceof Integer) {
				return (Integer)(v.getValue());
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * Convenience method to retrieve data from a property referring to a string value.
	 * If the property does not refer to a string value an exception is thrown.
	 */
	public String getPropertyStringValueNormalizedE(String propertyName) throws Exception
	{
		JProperty p = getProperty(propertyName);
		if (p == null) return null;
		if (p.getValue() == null) return null;
		if (p.getValue() instanceof JValue) {
			JValue v = (JValue)(p.getValue());
			if (v.getValue() == null) return null;
			if (v.getValue() instanceof String) {
				String s = (String)(v.getValue());
				s = s.trim();
				if (s.length() == 0) return null;
				return s;
			} else {
				throw new Exception("Value is not a String! (" + v.getValue().getClass().getCanonicalName() + ")");
			}
		} else {
			throw new Exception("Member does not refer to a String! (" + p.getValue().getClass().getCanonicalName() + ")");
		}
	}

	/**
	 * Convenience method: Set the property named propertyName to refer to the specified value. If the
	 * specified value is <code>null</code> the property will be removed.
	 */
	public void setStringValueTrim(String propertyName, String s)
	{
		remove(propertyName);
		if (s != null) {
			s = s.trim();
			if (s.length() == 0) s = null;
		}
		if (s == null) return;
		add(propertyName, new JValue(s));
	}

	public JProperty[] getProperties()
	{
		return properties.values().toArray(new JProperty[properties.size()]);
	}

	public JProperty getProperty(String propertyName)
	{
		if ((propertyName == null) || (propertyName.length() == 0)
			|| (propertyName.trim().length() != propertyName.length()))
			throw new RuntimeException("Invalid property name specified: " + propertyName);

		JProperty p = properties.get(propertyName);
		return p;
	}

	public JToken getValue(String propertyName)
	{
		if ((propertyName == null) || (propertyName.length() == 0)
			|| (propertyName.trim().length() != propertyName.length()))
			throw new RuntimeException("Invalid property name specified: " + propertyName);

		JProperty p = properties.get(propertyName);
		if (p == null) return null;
		return p.getValue();
	}

	public boolean remove(String propertyName)
	{
		if ((propertyName == null) || (propertyName.length() == 0)
			|| (propertyName.trim().length() != propertyName.length()))
			throw new RuntimeException("Invalid property name specified: " + propertyName);

		return properties.remove(propertyName) != null;
	}

	@Override
	public boolean hasValues()
	{
		return properties.size() > 0;
	}

	@Override
	public int getCount()
	{
		return properties.size();
	}

	@Override
	public String toString()
	{
		return "JObject[" + properties.size() + " members]";
	}

	/**
	 * Perform a deep cloning.
	 */
	@Override
	public JToken cloneObject()
	{
		JObject ret = new JObject();
		for (JProperty p : properties.values()) {
			ret.add(p.getName(), p.getValue().cloneObject());
		}
		return ret;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof JObject) {
			JObject o = (JObject)obj;

			return (properties.equals(o.properties));
		} else {
			return false;
		}
	}

	@Override
	public String toJSON()
	{
		StringBuilder sb = new StringBuilder();
		sb.append('{');
		boolean bAddComma = false;
		for (String key : properties.keySet()) {
			JProperty p = properties.get(key);

			if (bAddComma) sb.append(',');
			sb.append(p.toJSON());

			bAddComma = true;
		}
		sb.append('}');
		return sb.toString();
	}

}
