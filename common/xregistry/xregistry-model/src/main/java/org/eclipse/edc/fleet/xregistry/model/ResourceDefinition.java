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
import static org.eclipse.edc.fleet.xregistry.model.ValueType.MAP;
import static org.eclipse.edc.fleet.xregistry.model.ValueType.STRING;
import static org.eclipse.edc.fleet.xregistry.model.ValueType.UINTEGER;
import static org.eclipse.edc.fleet.xregistry.model.ValueType.URL;
import static org.eclipse.edc.fleet.xregistry.model.ValueType.XID;

/**
 * Defines an XRegistry resource.
 */
public class ResourceDefinition extends AbstractTypeDefinition {
    private VersionDefinition versionDefinition;

    public VersionDefinition getVersionDefinition() {
        return versionDefinition;
    }

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
            addRequiredAttribute(definition.singular + "id", STRING);
            addRequiredAttribute("self", URL);
            addRequiredAttribute("xid", XID);
            addRequiredAttribute("metaurl", URL);
            addRequiredAttribute("versionsurl", URL);
            addRequiredAttribute("versionscount", UINTEGER);

            addOptionalAttribute("meta", MAP);
//            addOptionalAttribute("maxversions", UINTEGER);
//            addOptionalAttribute("setversionid", BOOLEAN);
//            addOptionalAttribute("setdefaultversionsticky", BOOLEAN);
//            addOptionalAttribute("hasdocument", BOOLEAN);
//            addOptionalAttribute("typemap", MAP);
//            addOptionalAttribute("metaattributes", MAP);
//            addOptionalAttribute("labels", MAP);

            var intersection = new HashSet<>(definition.attributes.values());
            intersection.retainAll(definition.metaAttributes.values());
            if (!intersection.isEmpty()) {
                throw new IllegalArgumentException("Duplicate attribute definitions found: " + intersection.stream()
                        .map(AttributeDefinition::getName)
                        .collect(joining(",")));
            }

            result.versionDefinition = VersionDefinition.Builder.newInstance()
                    .resourceName(definition.singular)
                    .build();
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

