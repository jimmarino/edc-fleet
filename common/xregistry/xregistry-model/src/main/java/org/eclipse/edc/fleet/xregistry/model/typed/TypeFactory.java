/*
 *  Copyright (c) 2025 Metaform Systems, Inc.
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Metaform Systems, Inc. - initial API and implementation
 *
 */

package org.eclipse.edc.fleet.xregistry.model.typed;

import org.eclipse.edc.fleet.xregistry.model.definition.AbstractTypeDefinition;
import org.eclipse.edc.fleet.xregistry.model.definition.GroupDefinition;
import org.eclipse.edc.fleet.xregistry.model.definition.ResourceDefinition;

import java.util.Map;

/**
 * Delegates to {@link TypeFactory.Instantiator}s to create typed views of XRegistry data.
 */
public interface TypeFactory {

    /**
     * Instantiates a typed view for the XRegistry data.
     */
    @FunctionalInterface
    interface Instantiator<T extends AbstractTypeDefinition> {
        AbstractType<?> instantiate(Map<String, Object> untyped, T definition, TypeFactory typeFactory);
    }

    /**
     * Instantiates an XRegistry type.
     *
     * @param type       the type to instantiate
     * @param untyped    the untyped data
     * @param definition the type definition
     * @return the instance
     */
    <T extends AbstractType<D>, D extends AbstractTypeDefinition> T instantiate(Class<T> type, Map<String, Object> untyped, D definition);

    /**
     * Registers a group {@link Instantiator}.
     */
    void registerGroup(String name, Instantiator<GroupDefinition> instantiator);

    /**
     * Registers a resource {@link Instantiator}.
     */
    void registerResource(String name, Instantiator<ResourceDefinition> instantiator);

}
