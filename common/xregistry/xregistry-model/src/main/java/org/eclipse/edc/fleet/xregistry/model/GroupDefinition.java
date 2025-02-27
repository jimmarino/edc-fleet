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

import java.util.HashMap;
import java.util.Map;

import static org.eclipse.edc.fleet.xregistry.model.RegistryConstants.COUNT;
import static org.eclipse.edc.fleet.xregistry.model.RegistryConstants.CREATED_AT;
import static org.eclipse.edc.fleet.xregistry.model.RegistryConstants.DESCRIPTION;
import static org.eclipse.edc.fleet.xregistry.model.RegistryConstants.EPOCH;
import static org.eclipse.edc.fleet.xregistry.model.RegistryConstants.ID;
import static org.eclipse.edc.fleet.xregistry.model.RegistryConstants.LABELS;
import static org.eclipse.edc.fleet.xregistry.model.RegistryConstants.MODIFIED_AT;
import static org.eclipse.edc.fleet.xregistry.model.RegistryConstants.NAME;
import static org.eclipse.edc.fleet.xregistry.model.RegistryConstants.SELF;
import static org.eclipse.edc.fleet.xregistry.model.RegistryConstants.SHORT_SELF;
import static org.eclipse.edc.fleet.xregistry.model.ValueType.MAP;
import static org.eclipse.edc.fleet.xregistry.model.ValueType.STRING;
import static org.eclipse.edc.fleet.xregistry.model.ValueType.TIMESTAMP;
import static org.eclipse.edc.fleet.xregistry.model.ValueType.UINTEGER;
import static org.eclipse.edc.fleet.xregistry.model.ValueType.URL;
import static org.eclipse.edc.fleet.xregistry.model.ValueType.XID;

/**
 * Defines an XRegistry group.
 */
public class GroupDefinition extends AbstractTypeDefinition {
    private Map<String, ResourceDefinition> resources = new HashMap<>();

    public Map<String, ResourceDefinition> getResources() {
        return resources;
    }

    public ResourceDefinition getResource(String name) {
        return resources.get(name);
    }

    protected void setContext(String parent) {
        super.setContext(parent + "." + getPlural());
        resources.values().forEach(r -> r.setContext(context));
    }

    private GroupDefinition() {
    }

    public static class Builder extends AbstractTypeDefinition.Builder<GroupDefinition, Builder> {

        public static Builder newInstance() {
            return new Builder();
        }

        public Builder resource(ResourceDefinition resource) {
            definition.resources.put(resource.getPlural(), resource);
            return this;
        }

        public GroupDefinition build() {
            var result = super.build();
            addRequiredAttribute(definition.singular + ID, STRING);
            addRequiredAttribute(SELF, URL);
            addRequiredAttribute(RegistryConstants.XID, XID);
            addRequiredAttribute(EPOCH, UINTEGER);
            addRequiredAttribute(CREATED_AT, TIMESTAMP);
            addRequiredAttribute(MODIFIED_AT, TIMESTAMP);

            definition.resources.values().forEach(resource -> {
                addRequiredAttribute(resource.getPlural() + RegistryConstants.URL, URL);
                addRequiredAttribute(resource.getPlural() + COUNT, UINTEGER);
            });

            addOptionalAttribute(SHORT_SELF, URL);
            addOptionalAttribute(NAME, STRING);
            addOptionalAttribute(DESCRIPTION, STRING);
            addOptionalAttribute(LABELS, MAP);
            return result;
        }

        private Builder() {
            super(new GroupDefinition());
        }
    }
}
