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

package org.eclipse.edc.fleet.xregistry.model;

import java.util.HashSet;

import static java.util.stream.Collectors.joining;
import static org.eclipse.edc.fleet.xregistry.model.ValueType.BOOLEAN;
import static org.eclipse.edc.fleet.xregistry.model.ValueType.MAP;
import static org.eclipse.edc.fleet.xregistry.model.ValueType.UINTEGER;

/**
 * Defines an XRegistry resource.
 */
public class ResourceDefinition extends AbstractTypeDefinition {

    @Override
    protected void setContext(String context) {
        super.setContext(context + "." + getSingular());
    }

    private ResourceDefinition() {
    }

    public static class Builder extends AbstractTypeDefinition.Builder<ResourceDefinition, Builder> {

        public static Builder newInstance() {
            return new Builder();
        }

        public ResourceDefinition build() {
            var result = super.build();

            addOptionalAttribute("maxversions", UINTEGER);
            addOptionalAttribute("setversionid", BOOLEAN);
            addOptionalAttribute("setdefaultversionsticky", BOOLEAN);
            addOptionalAttribute("hasdocument", BOOLEAN);
            addOptionalAttribute("typemap", MAP);
            addOptionalAttribute("metaattributes", MAP);
            addOptionalAttribute("labels", MAP);

            var attributeDefinition = AttributeDefinition.Builder.newInstance()
                    .clientRequired(true)
                    .serverRequired(true)
                    .name(definition.singular + "id").build();
            attribute(attributeDefinition);

            var intersection = new HashSet<>(definition.attributes.values());
            intersection.retainAll(definition.metaAttributes.values());
            if (!intersection.isEmpty()) {
                throw new IllegalArgumentException("Duplicate attribute definitions found: " + intersection.stream()
                        .map(AttributeDefinition::getName)
                        .collect(joining(",")));
            }
            return result;
        }

        public Builder metaAttribute(AttributeDefinition attributeDefinition) {
            definition.metaAttributes.put(attributeDefinition.getName(), attributeDefinition);
            return this;
        }

        private Builder() {
            super(new ResourceDefinition());
        }
    }
}

