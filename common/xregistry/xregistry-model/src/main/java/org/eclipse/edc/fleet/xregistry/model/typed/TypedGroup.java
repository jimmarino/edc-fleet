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

import org.eclipse.edc.fleet.xregistry.model.definition.GroupDefinition;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * A typed view of an XRegistry group.
 */
public abstract class TypedGroup extends AbstractType<GroupDefinition> {
    private Map<String, TypedResource> typedResources = new HashMap<>();

    @SuppressWarnings("unchecked")
    public TypedGroup(Map<String, Object> untyped, GroupDefinition definition, TypeFactory typeFactory) {
        super(untyped, definition, typeFactory);
        this.definition = definition;

        definition.getResources().values().forEach(resourceDefinition -> {
            Map<String, Object> resources = (Map<String, Object>) untyped.get(resourceDefinition.getPlural());
            if (resources == null) {
                return;
            }
            resources.forEach((key, resource) -> {
                var typedResource = typeFactory.instantiate(TypedResource.class, (Map<String, Object>) resource, resourceDefinition);
                typedResources.put(typedResource.getId(), typedResource);
            });
        });
    }

    public Map<String, TypedResource> getResources() {
        return typedResources;
    }

    @SuppressWarnings("unchecked")
    public <T extends TypedResource> T getResource(String name) {
        return (T) typedResources.get(name);
    }

    @SuppressWarnings("unchecked")
    public <T extends TypedResource> Collection<T> getResourcesOfType(Class<T> type) {
        return (Collection<T>) typedResources.values().stream()
                .filter(typedResource -> typedResource.getClass().equals(type))
                .toList();
    }

    public static class Builder extends AbstractType.Builder<GroupDefinition, Builder> {

        public static Builder newInstance() {
            return new Builder();
        }

        public Builder group(String name, TypedGroup group) {
            checkModifiableState();
            throw new UnsupportedOperationException();
        }

        public Builder deleteGroup(String name) {
            checkModifiableState();
            throw new UnsupportedOperationException();
        }

        private Builder() {
        }
    }

}
