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

import static java.util.Objects.requireNonNull;
import static org.eclipse.edc.fleet.xregistry.model.ValueType.BOOLEAN;
import static org.eclipse.edc.fleet.xregistry.model.ValueType.STRING;
import static org.eclipse.edc.fleet.xregistry.model.ValueType.TIMESTAMP;
import static org.eclipse.edc.fleet.xregistry.model.ValueType.UINTEGER;
import static org.eclipse.edc.fleet.xregistry.model.ValueType.XID;

/**
 * A versioned resource.
 */
public class VersionDefinition extends AbstractTypeDefinition {
    private String resourceName;

    public String getResourceName() {
        return resourceName;
    }

    @Override
    protected void setContext(String context) {
        super.setContext(context + "." + getSingular());
    }

    private VersionDefinition() {
    }

    public static class Builder extends AbstractTypeDefinition.Builder<VersionDefinition, Builder> {

        public static Builder newInstance() {
            return new Builder();
        }

        public Builder resourceName(String resourceName) {
            definition.resourceName = resourceName;
            return this;
        }

        public VersionDefinition build() {
            requireNonNull(definition.resourceName, "resourceName");
            definition.singular = "version";
            definition.plural = "versions";

            var result = super.build();
            addRequiredAttribute(definition.resourceName + "id", STRING);
            addRequiredAttribute(definition.singular + "id", BOOLEAN);
            addRequiredAttribute("isdefault", BOOLEAN);
            addRequiredAttribute("self", STRING);
            addRequiredAttribute("xid", XID);
            addRequiredAttribute("epoch", UINTEGER);
            addRequiredAttribute("createdat", TIMESTAMP);
            addRequiredAttribute("modifiedat", TIMESTAMP);

            addOptionalAttribute("name", STRING);
            return result;
        }

        public Builder metaAttribute(AttributeDefinition attributeDefinition) {
            definition.metaAttributes.put(attributeDefinition.getName(), attributeDefinition);
            return this;
        }

        private Builder() {
            super(new VersionDefinition());
        }
    }
}
