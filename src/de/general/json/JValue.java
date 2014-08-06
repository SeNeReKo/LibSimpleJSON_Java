package de.general.json;


import java.io.*;
import java.util.*;


/**
 *
 * @author knauth
 */
public class JValue extends JToken
{

	////////////////////////////////////////////////////////////////
	// Properties
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	Object value;

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

	/**
	 * Constructor.
	 */
	public JValue(String value)
	{
		this.value = value;
	}

	/**
	 * Constructor.
	 */
	public JValue(boolean value)
	{
		this.value = value;
	}

	/**
	 * Constructor.
	 */
	public JValue(float value)
	{
		this.value = value;
	}

	/**
	 * Constructor.
	 */
	public JValue(double value)
	{
		this.value = value;
	}

	/**
	 * Constructor.
	 */
	public JValue(int value)
	{
		this.value = value;
	}

	/**
	 * Constructor.
	 */
	public JValue(long value)
	{
		this.value = value;
	}

	/**
	 * Constructor.
	 */
	public JValue(char value)
	{
		this.value = value;
	}

	/**
	 * Constructor.
	 */
	public JValue(Object value)
	{
		if ((value == null)
			|| (value instanceof Boolean)
			|| (value instanceof Number)
			|| (value instanceof String)) {
			this.value = value;
		} else {
			throw new RuntimeException("Invalid value specified: " + value);
		}
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	public Object getValue()
	{
		return value;
	}

	@Override
	public String toString()
	{
		if (value == null) return "(null)";
		if (value instanceof String) return (String)value;
		return value.toString();
	}

	@Override
	public String toJSON()
	{
		if (value == null) return "null";
		if (value instanceof Boolean) return value.toString();
		if (value instanceof Number) return JSONHelper.numberToString((Number)value);
		if (value instanceof String) return JSONHelper.quote((String)value);
		else throw new RuntimeException("Can't convert value: " + value);
	}

	/**
	 * Perform shallow cloning.
	 */
	@Override
	public JToken cloneObject()
	{
		return new JValue(value);
	}

	public boolean equals(Object obj)
	{
		if (obj instanceof JValue) {
			JValue v = (JValue)obj;
			if (value == null) {
				if (v.value == null) return true;
				return false;
			} else {
				if (v.value == null) return false;
				return value.equals(v.value);
			}
		} else {
			return false;
		}
	}

}
