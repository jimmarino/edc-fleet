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

package org.eclipse.edc.fleet.xregistry.policy.model.typed;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.edc.fleet.xregistry.model.definition.GroupDefinition;
import org.eclipse.edc.fleet.xregistry.model.definition.ResourceDefinition;
import org.eclipse.edc.fleet.xregistry.model.typed.TypeFactoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.eclipse.edc.fleet.xregistry.policy.model.fixture.TestSerializations.POLICY_GROUP;

class TypedPolicyGroupTest {
    private ObjectMapper mapper;
    private TypeFactoryImpl typeFactory;

    @Test
    @SuppressWarnings("unchecked")
    void verify_typedGroup() throws JsonProcessingException {
        var untyped = mapper.readValue(POLICY_GROUP, Map.class);

        var group = TypedPolicyGroup.Builder.newInstance()
                .untyped(untyped)
                .definition(GroupDefinition.Builder.newInstance()
                        .singular("policygroup")
                        .plural("policygroups")
                        .resource(ResourceDefinition.Builder.newInstance()
                                .singular("policy")
                                .plural("policies")
                                .build())
                        .build())
                .typeFactory(typeFactory)
                .build();

        TypedPolicyResource resource = group.getResource("Corporate.Headquarters.EU");
        assertThat(resource).isNotNull();

        assertThat(group.getId()).isNotNull();
        assertThat(group.getCreatedAt()).isNotNull();
        assertThat(group.getModifiedAt()).isNotNull();
        assertThat(group.getEpoch()).isGreaterThan(0);
        assertThat(group.getSelf()).isNotNull();
        assertThat(group.getXid()).isNotNull();
    }

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        typeFactory = new TypeFactoryImpl();
        typeFactory.registerResource("policy", TypedPolicyResource::new);
    }

}