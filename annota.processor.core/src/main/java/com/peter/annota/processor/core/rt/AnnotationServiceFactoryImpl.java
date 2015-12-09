package com.peter.annota.processor.core.rt;

import java.util.Collection;

import org.apache.olingo.odata2.api.ODataService;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.rt.RuntimeDelegate;

import com.peter.annota.processor.api.AnnotationServiceFactory.AnnotationServiceFactoryInstance;
import com.peter.annota.processor.core.ListsProcessor;
import com.peter.annota.processor.core.datasource.AnnotationInMemoryDs;
import com.peter.annota.processor.core.datasource.AnnotationValueAccess;
import com.peter.annota.processor.core.edm.AnnotationEdmProvider;

public class AnnotationServiceFactoryImpl implements AnnotationServiceFactoryInstance{

    public ODataService createAnnotationService(String modelPackage)
            throws ODataException {
        AnnotationEdmProvider edmProvider = new AnnotationEdmProvider(modelPackage);
        AnnotationInMemoryDs dataSource = new AnnotationInMemoryDs(modelPackage);
        AnnotationValueAccess valueAccess = new AnnotationValueAccess();

        // Edm via Annotations and ListProcessor via AnnotationDS with AnnotationsValueAccess
        return RuntimeDelegate.createODataSingleProcessorService(edmProvider,
            new ListsProcessor(dataSource, valueAccess));
    }

    public ODataService createAnnotationService(
            Collection<Class<?>> annotatedClasses) throws ODataException {
        AnnotationEdmProvider edmProvider = new AnnotationEdmProvider(annotatedClasses);
        AnnotationInMemoryDs dataSource = new AnnotationInMemoryDs(annotatedClasses);
        AnnotationValueAccess valueAccess = new AnnotationValueAccess();

        // Edm via Annotations and ListProcessor via AnnotationDS with AnnotationsValueAccess
        return RuntimeDelegate.createODataSingleProcessorService(edmProvider,
            new ListsProcessor(dataSource, valueAccess));
    }

}
