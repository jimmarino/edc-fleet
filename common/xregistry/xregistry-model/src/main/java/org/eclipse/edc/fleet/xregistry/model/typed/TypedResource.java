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

import org.eclipse.edc.fleet.xregistry.model.definition.ResourceDefinition;

import java.util.Map;
import java.util.Set;

import static java.util.Collections.emptyMap;
import static java.util.stream.Collectors.toMap;
import static org.eclipse.edc.fleet.xregistry.model.definition.RegistryConstants.VERSIONS;

/**
 * A typed view of an XRegistry resource.
 */
public abstract class TypedResource<V extends TypedVersion> extends AbstractType<ResourceDefinition> {
    protected ResourceDefinition definition;

    @SuppressWarnings("unchecked")
    public Map<String, V> getVersions() {
        var untypedVersions = (Map<String, Map<String, Object>>) this.untyped.get(VERSIONS);
        if (untypedVersions != null) {
            Set<Map.Entry<String, Map<String, Object>>> entries = untypedVersions.entrySet();
            return entries.stream().map(entry -> {
                return createVersion(entry.getValue());
            }).collect(toMap(AbstractType::getId, v -> v));
        }
        return emptyMap();
    }

    public ResourceDefinition getDefinition() {
        return definition;
    }

    protected TypedResource(Map<String, Object> untyped, ResourceDefinition definition, TypeFactory typeFactory) {
        super(untyped, definition, typeFactory);
        this.definition = definition;
    }

    protected abstract V createVersion(Map<String, Object> untypedVersion);

    public static class Builder<V extends TypedVersion, B extends Builder<V, B>> extends AbstractType.Builder<ResourceDefinition, B> {

        public B version(String name, V typedResource) {
            checkModifiableState();
            throw new UnsupportedOperationException();
        }

        @SuppressWarnings("unchecked")
        public B removeVersion(String name) {
            @SuppressWarnings("unchecked")
            var untypedVersions = (Map<String, Map<String, Object>>) this.untyped.get(VERSIONS);
            if (untypedVersions == null) {
                return (B) this;
            }
            untypedVersions.remove(name);
            return (B) this;
        }


        protected Builder() {
        }
    }
}
