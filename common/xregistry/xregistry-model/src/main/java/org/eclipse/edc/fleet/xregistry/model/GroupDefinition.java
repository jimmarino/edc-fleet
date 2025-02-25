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

import static org.eclipse.edc.fleet.xregistry.model.ValueType.MAP;

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

    public String getGroupName() {
        return getSingular() + "groups";
    }

    protected void setContext(String parent) {
        super.setContext(parent + "." + getGroupName());
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
            addOptionalAttribute("labels", MAP);
            return result;
        }

        private Builder() {
            super(new GroupDefinition());
        }
    }
}
