/**
 * DataCleaner (community edition)
 * Copyright (C) 2014 Neopost - Customer Information Management
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 */
package org.datacleaner.api;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used to override the default component distribution and clustering
 * model. Any component (transformer, filter or analyzer) with this annotation
 * can define whether or not it can be distributed in a server cluster or not. A
 * distributed component will have multiple instances, all with the same
 * configuration. But since there are more instances, naturally they need to be
 * either stateless or have a state-logic which supports separate execution.
 * 
 * The default behaviour of the components is:
 * 
 * <ul>
 * <li>Transformers and Filters are by default distributed. The rationale behind
 * this default value is that the invoked methods (transform(...) and
 * categorize(...)) both return their results immidiately and thus a stateless
 * implementation will be the normal scenario.</li>
 * <li>Analyzers are by default <i>not</i> distributed. The rationale behind
 * this default value is that analyzers are expected to build up it's result
 * during execution and thus will typically be stateful. If a Analyzer is to be
 * distributed, it needs, or it's {@link AnalyzerResult} class needs, to specify
 * a {@link AnalyzerResultReducer} value for the {@link #reducer()} field.</li>
 * </ul>
 * 
 * @see Filter
 * @see Transformer
 * @see Analyzer
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@Documented
public @interface Distributed {

    /**
     * Determines whether or not the component with this annotation is
     * distributable.
     * 
     * @return a boolean indicating whether or not distributed execution of the
     *         component is allowed.
     */
    public boolean value() default true;

    /**
     * Gets a reducer class for {@link AnalyzerResult}s generated by this
     * component (applies to Analyzer or AnalyzerResult classes only).
     * 
     * @return a reducer class for results generated by this component.
     */
    public Class<? extends AnalyzerResultReducer<?>> reducer() default NoAnalyzerResultReducer.class;
}
