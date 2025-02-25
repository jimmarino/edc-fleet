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

            addRequiredAttribute("specversion", STRING);
            addRequiredAttribute("registryid", STRING);
            addRequiredAttribute("self", STRING);
            addRequiredAttribute("xid", XID);
            addRequiredAttribute("url", URL);
            addRequiredAttribute("epoch", UINTEGER);
            addRequiredAttribute("createdat", TIMESTAMP);
            addRequiredAttribute("modifiedat", TIMESTAMP);
            addOptionalAttribute("description", STRING);
            addOptionalAttribute("documentation", URL);
            addOptionalAttribute("labels", MAP);
            addOptionalAttribute("name", STRING);
            addOptionalAttribute("shortself", URL);
            createCapabilities();
            createModel();

            result.setContext("Registry");
            return result;
        }

        private Builder() {
            super(new RegistryDefinition());
            definition.singular = "registry";
            definition.plural = "registries";
        }

        private void createCapabilities() {
            var capabilities = AttributeDefinition.Builder
                    .newInstance()
                    .name("capabilities")
                    .attribute(createDefinition("enforcecompatibility", BOOLEAN))
                    .attribute(createDefinition("pagination", BOOLEAN))
                    .attribute(createDefinition("shortself", BOOLEAN))
                    .attribute(createDefinition("flags", STRING))
                    .attribute(createDefinition("mutable", STRING))
                    .attribute(createDefinition("schemas", STRING))
                    .attribute(createDefinition("specversions", STRING))
                    .type(MAP)
                    .build();
            definition.attributes.put("capabilities", capabilities);
        }

        private void createModel() {
            var model = AttributeDefinition.Builder
                    .newInstance()
                    .name("model")
                    .attribute(createDefinition("labels", MAP))
                    .type(MAP)
                    .build();
            definition.attributes.put("model", model);
        }


    }
}
