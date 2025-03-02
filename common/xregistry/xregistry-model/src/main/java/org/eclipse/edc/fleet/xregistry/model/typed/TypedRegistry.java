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

import org.eclipse.edc.fleet.xregistry.model.definition.RegistryDefinition;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.emptyMap;
import static org.eclipse.edc.fleet.xregistry.model.definition.RegistryConstants.SELF;

/**
 * A typed view of an XRegistry.
 */
public class TypedRegistry extends AbstractType<RegistryDefinition> {
    private TypeFactory typeFactory;

    private Map<String, Map<String, TypedGroup>> typedGroups = new HashMap<>();

    @SuppressWarnings("unchecked")
    protected TypedRegistry(Map<String, Object> untyped, RegistryDefinition definition, TypeFactory typeFactory) {
        super(untyped,definition, typeFactory);
        this.typeFactory = typeFactory;
        this.definition = definition;
        this.definition.getGroups().forEach((name, groupDefinition) -> {
            var groupContainerName = groupDefinition.getPlural();
            var groupsMap = (Map<String, Map<String, Object>>) untyped.get(groupContainerName);
            groupsMap.forEach((groupName, group) -> {
                var typedGroup = typeFactory.instantiate(TypedGroup.class, group, groupDefinition);
                typedGroups.put(name, Map.of(groupName, typedGroup));
            });
        });
    }

    @SuppressWarnings("unchecked")
    public <T extends TypedGroup> Map<String, T> getGroups(String name) {
        return (Map<String, T>) typedGroups.getOrDefault(name, emptyMap());
    }

    public URL getUrl() {
        return getUrl(SELF); // registriy level self attributes are URLs
    }

    public Builder asBuilder() {
        return Builder.newInstance()
                .untyped(untyped)
                .definition(definition)
                .typeFactory(typeFactory);
    }

    public static class Builder extends AbstractType.Builder<RegistryDefinition, Builder> {

        public static Builder newInstance() {
            return new Builder();
        }

        public Builder group(String name, TypedGroup typedGroup) {
            checkModifiableState();
            throw new UnsupportedOperationException();
        }

        public Builder deleteGroup(String name) {
            checkModifiableState();
            throw new UnsupportedOperationException();
        }

        public TypedRegistry build() {
            validate();
            return new TypedRegistry(untyped, definition, typeFactory);
        }

        private Builder() {
        }
    }

}
