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
        var resourceContext = context + "." + getSingular();
        super.setContext(resourceContext);
        versionDefinition.setContext(resourceContext);
    }

    private ResourceDefinition() {
    }

    public static class Builder extends AbstractTypeDefinition.Builder<ResourceDefinition, Builder> {

        public static Builder newInstance() {
            return new Builder();
        }

        public Builder versionDefinition(VersionDefinition versionDefinition) {
            this.definition.versionDefinition = versionDefinition;
            return this;
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

            if (definition.versionDefinition == null) {
                result.versionDefinition = VersionDefinition.Builder.newInstance()
                        .resourceName(definition.singular)
                        .build();
            }

            return result;
        }

        private Builder() {
            super(new ResourceDefinition());
        }
    }
}

