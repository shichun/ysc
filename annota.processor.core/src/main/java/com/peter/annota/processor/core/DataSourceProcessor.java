package com.peter.annota.processor.core;

import org.apache.olingo.odata2.api.processor.ODataSingleProcessor;

import com.peter.annota.processor.core.datasource.DataSource;
import com.peter.annota.processor.core.datasource.ValueAccess;

public class DataSourceProcessor extends ODataSingleProcessor {
    protected final DataSource dataSource;
    protected final ValueAccess valueAccess;

    /**
     * Initialize a {@link DataSourceProcessor} in combination with given
     * {@link DataSource} (providing data objects) and {@link ValueAccess}
     * (accessing values of data objects).
     * 
     * @param dataSource
     *            used for accessing the data objects
     * @param valueAccess
     *            for accessing the values provided by the data objects
     */
    public DataSourceProcessor(final DataSource dataSource,
            final ValueAccess valueAccess) {
        this.dataSource = dataSource;
        this.valueAccess = valueAccess;
    }
}
