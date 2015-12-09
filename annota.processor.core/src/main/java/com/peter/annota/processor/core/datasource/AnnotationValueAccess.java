package com.peter.annota.processor.core.datasource;

import org.apache.olingo.odata2.api.edm.EdmMapping;
import org.apache.olingo.odata2.api.edm.EdmProperty;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.exception.ODataNotImplementedException;

import com.peter.annota.processor.core.edm.util.AnnotationHelper;

public class AnnotationValueAccess implements ValueAccess {
    private final AnnotationHelper annotationHelper = new AnnotationHelper();

    /**
     * Retrieves the value of an EDM property for the given data object.
     * @param data the Java data object
     * @param property the requested {@link EdmProperty}
     * @return the requested property value
     */
    public <T> Object getPropertyValue(final T data, final EdmProperty property) throws ODataException {
      if (data == null) {
        return null;
      } else if (annotationHelper.isEdmAnnotated(data)) {
        return annotationHelper.getValueForProperty(data, property.getName());
      }
      throw new ODataNotImplementedException(ODataNotImplementedException.COMMON);
    }

    /**
     * Sets the value of an EDM property for the given data object.
     * @param data the Java data object
     * @param property the {@link EdmProperty}
     * @param value the new value of the property
     */
    public <T, V> void setPropertyValue(final T data, final EdmProperty property, final V value) throws ODataException {
      if (data != null) {
        if (annotationHelper.isEdmAnnotated(data)) {
          annotationHelper.setValueForProperty(data, property.getName(), value);
        } else {
          throw new ODataNotImplementedException(ODataNotImplementedException.COMMON);
        }
      }
    }

    /**
     * Retrieves the Java type of an EDM property for the given data object.
     * @param data the Java data object
     * @param property the requested {@link EdmProperty}
     * @return the requested Java type
     */
    public <T> Class<?> getPropertyType(final T data, final EdmProperty property) throws ODataException {
      if (data == null) {
        return null;
      } else if (annotationHelper.isEdmAnnotated(data)) {
        Class<?> fieldType = annotationHelper.getFieldTypeForProperty(data, property.getName());
        if (fieldType == null) {
          throw new ODataException("No field type found for property " + property);
        }
        return fieldType;
      }
      throw new ODataNotImplementedException(ODataNotImplementedException.COMMON);
    }

    /**
     * Retrieves the value defined by a mapping object for the given data object.
     * @param data the Java data object
     * @param mapping the requested {@link EdmMapping}
     * @return the requested value
     */
    public <T> Object getMappingValue(final T data, final EdmMapping mapping) throws ODataException {
      if (mapping != null && mapping.getMediaResourceMimeTypeKey() != null) {
        return annotationHelper.getValueForProperty(data, mapping.getMediaResourceMimeTypeKey());
      }
      return null;
    }

    /**
     * Sets the value defined by a mapping object for the given data object.
     * @param data the Java data object
     * @param mapping the {@link EdmMapping}
     * @param value the new value
     */
    public <T, V> void setMappingValue(final T data, final EdmMapping mapping, final V value) throws ODataException {
      if (mapping != null && mapping.getMediaResourceMimeTypeKey() != null) {
        annotationHelper.setValueForProperty(data, mapping.getMediaResourceMimeTypeKey(), value);
      }
    }
  }

