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
        return ResourceDefinition.Builder.newInstance()
                .singular("policy")
                .plural("policies")
                .metaAttribute(createDefinition("description", STRING))
                .metaAttribute(createDefinition("schemauri", XID))
                .metaAttribute(createDefinition("controlpolicy", BOOLEAN))
                .metaAttribute(createDefinition("accesspolicy", BOOLEAN))
                .metaAttribute(AttributeDefinition.Builder.newInstance()
                        .name("policydefinition")
                        .serverRequired(true)
                        .clientRequired(true)
                        .type(STRING)
                        .build())
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
