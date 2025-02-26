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

package org.eclipse.edc.fleet.xregistry.policy;

import org.eclipse.edc.fleet.xregistry.model.AttributeDefinition;
import org.eclipse.edc.fleet.xregistry.model.GroupDefinition;
import org.eclipse.edc.fleet.xregistry.model.ResourceDefinition;
import org.eclipse.edc.fleet.xregistry.model.ValueType;
import org.eclipse.edc.fleet.xregistry.model.VersionDefinition;

import static org.eclipse.edc.fleet.xregistry.model.ValueType.BOOLEAN;
import static org.eclipse.edc.fleet.xregistry.model.ValueType.STRING;
import static org.eclipse.edc.fleet.xregistry.model.ValueType.XID;

/**
 * Creates XRegistry definitions for policy artifacts.
 */
public class RegistryPolicyDefinitions {

    public static GroupDefinition createPolicyGroupDefinition() {
        return GroupDefinition.Builder.newInstance()
                .singular("policy")
                .plural("policies")
                .resource(createPolicyResourceDefinition())
                .build();
    }

    public static ResourceDefinition createPolicyResourceDefinition() {
        var versionDefinition = VersionDefinition.Builder.newInstance()
                .resourceName("policy")
                .attribute(createDefinition("description", STRING))
                .attribute(createDefinition("controlpolicy", BOOLEAN))
                .attribute(createDefinition("accesspolicy", BOOLEAN))
                .attribute(AttributeDefinition.Builder.newInstance()
                        .name("policydefinition")
                        .serverRequired(true)
                        .clientRequired(true)
                        .type(STRING)
                        .build())
                .build();
        return ResourceDefinition.Builder.newInstance()
                .singular("policy")
                .plural("policies")
                .attribute(createDefinition("schemauri", XID))
                .versionDefinition(versionDefinition)
                .build();
    }

    private static AttributeDefinition createDefinition(String name, ValueType type) {
        return AttributeDefinition.Builder.newInstance()
                .name(name)
                .type(type)
                .build();
    }

    private RegistryPolicyDefinitions() {
    }
}
