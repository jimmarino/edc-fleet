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

import java.util.HashMap;
import java.util.Map;

/**
 * Default implementation.
 */
public class TypeFactoryImpl implements TypeFactory {
    @SuppressWarnings("rawtypes")
    private Map<InstantiatorKey, Instantiator> instantiators = new HashMap<>();

    @Override
    @SuppressWarnings("unchecked")
    public <T extends AbstractType<D>, D extends AbstractTypeDefinition> T instantiate(Class<T> type, Map<String, Object> untyped, D definition) {
        return (T) instantiators
                .computeIfAbsent(new InstantiatorKey<>(type, definition.getSingular()), k -> {
                    throw new IllegalArgumentException("Unknown type: " + type);
                })
                .instantiate(untyped, definition, this);
    }

    @Override
    public void registerGroup(String name, Instantiator<GroupDefinition> instantiator) {
        instantiators.put(new InstantiatorKey<>(TypedGroup.class, name), instantiator);
    }

    @Override
    public void registerResource(String name, Instantiator<ResourceDefinition> instantiator) {
        instantiators.put(new InstantiatorKey<>(TypedResource.class, name), instantiator);
    }

    private record InstantiatorKey<T extends AbstractType<?>>(Class<T> singular, String name) {
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;

            InstantiatorKey<?> that = (InstantiatorKey<?>) o;
            return name.equals(that.name) && singular.equals(that.singular);
        }

        public int hashCode() {
            int result = singular.hashCode();
            result = 31 * result + name.hashCode();
            return result;
        }
    }

}
