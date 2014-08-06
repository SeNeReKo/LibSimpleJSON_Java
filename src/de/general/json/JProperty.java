package de.general.json;


/**
 *
 * @author knauth
 */
public class JProperty extends JContainer
{

	////////////////////////////////////////////////////////////////
	// Properties
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	String name;
	JToken value;

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

	/**
	 * Constructor.
	 */
	public JProperty(String name, JToken value)
	{
		this.name = name;
		this.value = value;
	}

	/**
	 * Constructor.
	 */
	public JProperty(String name, Object value) throws Exception
	{
		if (name == null) throw new Exception("Name is null!");
		if (value == null) throw new Exception("Value is null!");

		this.name = name;
		if (value instanceof JToken) {
			this.value = (JToken)value;
		} else
		if (value instanceof JSONObject) {
			this.value = new JObject((JSONObject)value);
		} else
		if (value instanceof JSONArray) {
			this.value = new JArray((JSONArray)value);
		} else
		if (value instanceof Boolean) {
			this.value = new JValue((Boolean)value);
		} else
		if (value instanceof Double) {
			this.value = new JValue((Double)value);
		} else
		if (value instanceof Integer) {
			this.value = new JValue((Integer)value);
		} else
		if (value instanceof Long) {
			this.value = new JValue((Long)value);
		} else
		if (value instanceof String) {
			this.value = new JValue((String)value);
		} else {
			throw new Exception("Unknown value type: " + value.getClass().getName());
		}
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	public String getName()
	{
		return name;
	}

	public JToken getValue()
	{
		return value;
	}

	public void setValue(JToken value)
	{
		this.value = value;
	}

	@Override
	public boolean hasValues()
	{
		return (value != null);
	}

	@Override
	public int getCount()
	{
		if (value != null) return 1; else return 0;
	}

	@Override
	public String toString()
	{
		return name + " : " + value.toString();
	}

	/**
	 * Perform a deep cloning.
	 */
	@Override
	public JToken cloneObject()
	{
		return new JProperty(name, (value != null) ? value.cloneObject() : null);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof JProperty) {
			JProperty p = (JProperty)obj;

			if (name != null) {
				if (p.name == null) return false;
				if (!name.equals(p.name)) return false;
			} else {
				if (p.name != null) return false;
			}

			if (value != null) {
				if (p.value == null) return false;
				if (!value.equals(p.value)) return false;
			} else {
				if (p.value != null) return false;
			}

			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toJSON()
	{
		return JSONHelper.quote(name) + ':' + ((value == null) ? "null" : value.toJSON());
	}

}
