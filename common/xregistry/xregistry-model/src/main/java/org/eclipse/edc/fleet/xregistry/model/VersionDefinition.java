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
import static org.eclipse.edc.fleet.xregistry.model.RegistryConstants.CREATED_AT;
import static org.eclipse.edc.fleet.xregistry.model.RegistryConstants.EPOCH;
import static org.eclipse.edc.fleet.xregistry.model.RegistryConstants.ID;
import static org.eclipse.edc.fleet.xregistry.model.RegistryConstants.IS_DEFAULT;
import static org.eclipse.edc.fleet.xregistry.model.RegistryConstants.MODIFIED_AT;
import static org.eclipse.edc.fleet.xregistry.model.RegistryConstants.NAME;
import static org.eclipse.edc.fleet.xregistry.model.RegistryConstants.SELF;
import static org.eclipse.edc.fleet.xregistry.model.RegistryConstants.VERSION;
import static org.eclipse.edc.fleet.xregistry.model.RegistryConstants.VERSIONS;
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
            definition.singular = VERSION;
            definition.plural = VERSIONS;

            var result = super.build();
            addRequiredAttribute(definition.resourceName + ID, STRING);
            addRequiredAttribute(definition.singular + ID, STRING);
            addRequiredAttribute(IS_DEFAULT, BOOLEAN);
            addRequiredAttribute(SELF, STRING);
            addRequiredAttribute(RegistryConstants.XID, XID);
            addRequiredAttribute(EPOCH, UINTEGER);
            addRequiredAttribute(CREATED_AT, TIMESTAMP);
            addRequiredAttribute(MODIFIED_AT, TIMESTAMP);

            addOptionalAttribute(NAME, STRING);

            return result;
        }

        private Builder() {
            super(new VersionDefinition());
        }
    }
}
