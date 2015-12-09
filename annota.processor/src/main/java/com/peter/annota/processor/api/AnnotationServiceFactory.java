package com.peter.annota.processor.api;


import java.util.Collection;

import org.apache.olingo.odata2.api.ODataService;
import org.apache.olingo.odata2.api.exception.ODataException;

/**
* AnnotationServiceFactory which provides an AnnotationService which handles java beans (classes)
* annotated with annotation from <code>org.apache.olingo.olingo-odata2-api-annotation</code> module
* (see package <code>org.apache.olingo.odata2.api.annotation.edm</code>).
*/
public abstract class AnnotationServiceFactory {

 private static final String IMPLEMENTATION =
     "org.apache.olingo.odata2.annotation.processor.core.rt.AnnotationServiceFactoryImpl";

 /**
  * Create a runtime delegate instance from the core library. The core
  * library (org.apache.olingo.odata2.annotation.processor.core) needs to be included into the classpath
  * of the using application.
  * @return implementation instance
  */
 private static AnnotationServiceFactoryInstance getInstance() {
   AnnotationServiceFactoryInstance delegate;

   try {
     final Class<?> clazz = Class.forName(IMPLEMENTATION);
     /*
      * We explicitly do not use the singleton pattern to keep the server state free
      * and avoid class loading issues also during hot deployment.
      */
     final Object object = clazz.newInstance();
     delegate = (AnnotationServiceFactoryInstance) object;

   } catch (final Exception e) {
     throw new RuntimeException(e);
   }
   return delegate;
 }

 /**
  * Interface to be implemented for an instance of a {@link AnnotationServiceFactoryInstance} which
  * provides an {@link ODataService} based on annotation from
  * <code>org.apache.olingo.olingo-odata2-api-annotation</code> module
  * (see package <code>org.apache.olingo.odata2.api.annotation.edm</code>).
  */
 public interface AnnotationServiceFactoryInstance {
   /**
    * Create an {@link ODataService} which is based on an EDM and Processor which are using the annotations from
    * <code>org.apache.olingo.olingo-odata2-api-annotation</code> module
    * (see package <code>org.apache.olingo.odata2.api.annotation.edm</code>) to define the model and access the data.
    * 
    * @param modelPackage package name which is scanned for annotated classes
    * @return service an {@link ODataService} based on on an EDM and Processor which are using annotations
    * for model definition and data access.
    * @throws ODataException if an error during initialization occurs
    */
   public ODataService createAnnotationService(String modelPackage) throws ODataException;

   /**
    * Create an {@link ODataService} which is based on an EDM and Processor which are using the annotations from
    * <code>org.apache.olingo.olingo-odata2-api-annotation</code> module
    * (see package <code>org.apache.olingo.odata2.api.annotation.edm</code>) to define the model and access the data.
    * 
    * @param modelPackage classes (which are annotated) which will be used for EDM definition and data access.
    * @return service an {@link ODataService} based on on an EDM and Processor which are using annotations
    * for model definition and data access.
    * @throws ODataException if an error during initialization occurs
    */
   public ODataService createAnnotationService(Collection<Class<?>> annotatedClasses) throws ODataException;
 }

 /**
  * Create an {@link ODataService} which is based on an EDM and Processor which are using the annotations from
  * <code>org.apache.olingo.olingo-odata2-api-annotation</code> module
  * (see package <code>org.apache.olingo.odata2.api.annotation.edm</code>) to define the model and access the data.
  * 
  * @param modelPackage package name which is scanned for annotated classes
  * @return service an {@link ODataService} based on on an EDM and Processor which are using annotations
  * for model definition and data access.
  * @throws ODataException if an error during initialization occurs
  */
 public static ODataService createAnnotationService(final String modelPackage) throws ODataException {
   return getInstance().createAnnotationService(modelPackage);
 }

 /**
  * Create an {@link ODataService} which is based on an EDM and Processor which are using the annotations from
  * <code>org.apache.olingo.olingo-odata2-api-annotation</code> module
  * (see package <code>org.apache.olingo.odata2.api.annotation.edm</code>) to define the model and access the data.
  * 
  * @param modelPackage classes (which are annotated) which will be used for EDM definition and data access.
  * @return service an {@link ODataService} based on on an EDM and Processor which are using annotations
  * for model definition and data access.
  * @throws ODataException if an error during initialization occurs
  */
 public static ODataService createAnnotationService(final Collection<Class<?>> annotatedClasses)
     throws ODataException {
   return getInstance().createAnnotationService(annotatedClasses);
 }
}

