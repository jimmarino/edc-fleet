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

import static org.eclipse.edc.fleet.xregistry.model.definition.RegistryConstants.VERSIONS;

public class TypedMockResource extends TypedResource<TypedMockVersion> {

    public TypedMockResource(Map<String, Object> untyped, ResourceDefinition definition, TypeFactory typeFactory) {
        super(untyped, definition, typeFactory);
    }

    @Override
    protected TypedMockVersion createVersion(Map<String, Object> untypedVersion) {
        return new TypedMockVersion(untypedVersion, definition.getVersionDefinition(), typeFactory);
    }

    public Builder toBuilder() {
        return Builder.newInstance()
                .untyped(untyped)
                .definition(definition)
                .typeFactory(typeFactory);
    }

    public static class Builder extends AbstractType.Builder<ResourceDefinition, Builder> {

        public Builder removeVersion(String name){
            @SuppressWarnings("unchecked")
            var untypedVersions = (Map<String, Map<String, Object>>) this.untyped.get(VERSIONS);
            if (untypedVersions == null) {
                return this;
            }
            untypedVersions.remove(name);
            return this;
        }

        public static Builder newInstance() {
            return new Builder();
        }

        public TypedMockResource build() {
            validate();
            return new TypedMockResource(untyped, definition, typeFactory);
        }
    }

}
