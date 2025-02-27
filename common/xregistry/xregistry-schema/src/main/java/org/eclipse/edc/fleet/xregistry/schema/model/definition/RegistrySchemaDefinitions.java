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

package org.eclipse.edc.fleet.xregistry.schema.model.definition;

import org.eclipse.edc.fleet.xregistry.model.definition.AttributeDefinition;
import org.eclipse.edc.fleet.xregistry.model.definition.GroupDefinition;
import org.eclipse.edc.fleet.xregistry.model.definition.ResourceDefinition;
import org.eclipse.edc.fleet.xregistry.model.definition.ValueType;

import static org.eclipse.edc.fleet.xregistry.model.definition.ValueType.STRING;

/**
 * Creates XRegistry definitions for schema artifacts.
 */
public class RegistrySchemaDefinitions {

    public static GroupDefinition createPolicyGroupDefinition() {
        return GroupDefinition.Builder.newInstance()
                .singular("policy")
                .plural("policies")
                .resource(createSchemaResourceDefinition())
                .build();
    }

    public static ResourceDefinition createSchemaResourceDefinition() {
        return ResourceDefinition.Builder.newInstance()
                .singular("schema")
                .plural("schemas")
                .attribute(createDefinition("format", STRING))
                .build();
    }

    private static AttributeDefinition createDefinition(String name, ValueType type) {
        return AttributeDefinition.Builder.newInstance()
                .name(name)
                .type(type)
                .build();
    }


}
