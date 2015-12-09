package com.peter.annota.processor.core.datasource;

import org.apache.olingo.odata2.api.edm.EdmMapping;
import org.apache.olingo.odata2.api.edm.EdmProperty;
import org.apache.olingo.odata2.api.exception.ODataException;

public interface ValueAccess {

    /**
     * Retrieves the value of an EDM property for the given data object.
     * 
     * @param data
     *            the Java data object
     * @param property
     *            the requested {@link EdmProperty}
     * @return the requested property value
     */
    public <T> Object getPropertyValue(final T data, final EdmProperty property)
            throws ODataException;

    /**
     * Sets the value of an EDM property for the given data object.
     * 
     * @param data
     *            the Java data object
     * @param property
     *            the {@link EdmProperty}
     * @param value
     *            the new value of the property
     */
    public <T, V> void setPropertyValue(T data, final EdmProperty property,
            final V value) throws ODataException;

    /**
     * Retrieves the Java type of an EDM property for the given data object.
     * 
     * @param data
     *            the Java data object
     * @param property
     *            the requested {@link EdmProperty}
     * @return the requested Java type
     */
    public <T> Class<?> getPropertyType(final T data, final EdmProperty property)
            throws ODataException;

    /**
     * Retrieves the value defined by a mapping object for the given data
     * object.
     * 
     * @param data
     *            the Java data object
     * @param mapping
     *            the requested {@link EdmMapping}
     * @return the requested value
     */
    public <T> Object getMappingValue(final T data, final EdmMapping mapping)
            throws ODataException;

    /**
     * Sets the value defined by a mapping object for the given data object.
     * 
     * @param data
     *            the Java data object
     * @param mapping
     *            the {@link EdmMapping}
     * @param value
     *            the new value
     */
    public <T, V> void setMappingValue(T data, final EdmMapping mapping,
            final V value) throws ODataException;
}
