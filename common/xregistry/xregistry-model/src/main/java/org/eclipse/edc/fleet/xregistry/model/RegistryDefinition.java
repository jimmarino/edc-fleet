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

import java.util.LinkedHashMap;
import java.util.Map;

import static org.eclipse.edc.fleet.xregistry.model.RegistryConstants.CAPABILITIES;
import static org.eclipse.edc.fleet.xregistry.model.RegistryConstants.COUNT;
import static org.eclipse.edc.fleet.xregistry.model.RegistryConstants.CREATED_AT;
import static org.eclipse.edc.fleet.xregistry.model.RegistryConstants.DESCRIPTION;
import static org.eclipse.edc.fleet.xregistry.model.RegistryConstants.DOCUMENTATION;
import static org.eclipse.edc.fleet.xregistry.model.RegistryConstants.ENFORCE_COMPAT;
import static org.eclipse.edc.fleet.xregistry.model.RegistryConstants.EPOCH;
import static org.eclipse.edc.fleet.xregistry.model.RegistryConstants.FLAGS;
import static org.eclipse.edc.fleet.xregistry.model.RegistryConstants.LABELS;
import static org.eclipse.edc.fleet.xregistry.model.RegistryConstants.MODEL;
import static org.eclipse.edc.fleet.xregistry.model.RegistryConstants.MODIFIED_AT;
import static org.eclipse.edc.fleet.xregistry.model.RegistryConstants.MUTABLE;
import static org.eclipse.edc.fleet.xregistry.model.RegistryConstants.NAME;
import static org.eclipse.edc.fleet.xregistry.model.RegistryConstants.PAGINATION;
import static org.eclipse.edc.fleet.xregistry.model.RegistryConstants.REGISTRIES;
import static org.eclipse.edc.fleet.xregistry.model.RegistryConstants.REGISTRY;
import static org.eclipse.edc.fleet.xregistry.model.RegistryConstants.REGISTRY_ID;
import static org.eclipse.edc.fleet.xregistry.model.RegistryConstants.SCHEMAS;
import static org.eclipse.edc.fleet.xregistry.model.RegistryConstants.SELF;
import static org.eclipse.edc.fleet.xregistry.model.RegistryConstants.SHORT_SELF;
import static org.eclipse.edc.fleet.xregistry.model.RegistryConstants.SPEC_VERSION;
import static org.eclipse.edc.fleet.xregistry.model.RegistryConstants.SPEC_VERSIONS;
import static org.eclipse.edc.fleet.xregistry.model.ValueType.BOOLEAN;
import static org.eclipse.edc.fleet.xregistry.model.ValueType.MAP;
import static org.eclipse.edc.fleet.xregistry.model.ValueType.STRING;
import static org.eclipse.edc.fleet.xregistry.model.ValueType.TIMESTAMP;
import static org.eclipse.edc.fleet.xregistry.model.ValueType.UINTEGER;
import static org.eclipse.edc.fleet.xregistry.model.ValueType.URL;
import static org.eclipse.edc.fleet.xregistry.model.ValueType.XID;

/**
 * Defines an XRegistry model.
 */
public class RegistryDefinition extends AbstractTypeDefinition {
    private Map<String, GroupDefinition> groups = new LinkedHashMap<>();

    public Map<String, GroupDefinition> getGroups() {
        return groups;
    }

    @Override
    protected void setContext(String context) {
        super.setContext(context);
        groups.values().forEach(group -> group.setContext(context));
    }

    private RegistryDefinition() {
    }

    public static class Builder extends AbstractTypeDefinition.Builder<RegistryDefinition, Builder> {
        public static Builder newInstance() {
            return new Builder();
        }

        public Builder group(GroupDefinition group) {
            definition.groups.put(group.getPlural(), group);
            return this;
        }

        public RegistryDefinition build() {
            var result = super.build();

            addRequiredAttribute(SPEC_VERSION, STRING);
            addRequiredAttribute(REGISTRY_ID, STRING);
            addRequiredAttribute(SELF, STRING);
            addRequiredAttribute(RegistryConstants.XID, XID);
            addRequiredAttribute(RegistryConstants.URL, URL);
            addRequiredAttribute(EPOCH, UINTEGER);
            addRequiredAttribute(CREATED_AT, TIMESTAMP);
            addRequiredAttribute(MODIFIED_AT, TIMESTAMP);

            definition.groups.values().forEach(group -> {
                addRequiredAttribute(group.getPlural() + RegistryConstants.URL, URL);
                addRequiredAttribute(group.getPlural() + COUNT, UINTEGER);
            });

            addOptionalAttribute(DESCRIPTION, STRING);
            addOptionalAttribute(DOCUMENTATION, URL);
            addOptionalAttribute(LABELS, MAP);
            addOptionalAttribute(NAME, STRING);
            addOptionalAttribute(SHORT_SELF, URL);
            createCapabilities();
            createModel();

            result.setContext("Registry");
            return result;
        }

        private Builder() {
            super(new RegistryDefinition());
            definition.singular = REGISTRY;
            definition.plural = REGISTRIES;
        }

        private void createCapabilities() {
            var capabilities = AttributeDefinition.Builder
                    .newInstance()
                    .name(CAPABILITIES)
                    .attribute(createDefinition(ENFORCE_COMPAT, BOOLEAN))
                    .attribute(createDefinition(PAGINATION, BOOLEAN))
                    .attribute(createDefinition(SHORT_SELF, BOOLEAN))
                    .attribute(createDefinition(FLAGS, STRING))
                    .attribute(createDefinition(MUTABLE, STRING))
                    .attribute(createDefinition(SCHEMAS, STRING))
                    .attribute(createDefinition(SPEC_VERSIONS, STRING))
                    .type(MAP)
                    .build();
            definition.attributes.put(CAPABILITIES, capabilities);
        }

        private void createModel() {
            var model = AttributeDefinition.Builder
                    .newInstance()
                    .name(MODEL)
                    .attribute(createDefinition(LABELS, MAP))
                    .type(MAP)
                    .build();
            definition.attributes.put(MODEL, model);
        }
    }
}
