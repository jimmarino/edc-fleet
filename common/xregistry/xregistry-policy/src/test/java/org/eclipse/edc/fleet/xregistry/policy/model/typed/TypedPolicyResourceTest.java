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
import org.eclipse.edc.fleet.xregistry.model.definition.ResourceDefinition;
import org.eclipse.edc.fleet.xregistry.model.typed.TypeFactoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.eclipse.edc.fleet.xregistry.policy.model.fixture.TestSerializations.POLICY_RESOURCE;

class TypedPolicyResourceTest {
    private ObjectMapper mapper;
    private TypeFactoryImpl typeFactory;

    @Test
    @SuppressWarnings("unchecked")
    void verify_typedGroup() throws JsonProcessingException {
        var untyped = mapper.readValue(POLICY_RESOURCE, Map.class);

        var resource = TypedPolicyResource.Builder.newInstance()
                .untyped(untyped)
                .definition(ResourceDefinition.Builder.newInstance()
                        .singular("policy")
                        .plural("policies")
                        .build())
                .typeFactory(typeFactory)
                .build();

        assertThat(resource.getId()).isNotNull();
        assertThat(resource.getSelf()).isNotNull();
        assertThat(resource.getXid()).isNotNull();

        Collection<TypedPolicyVersion> versions = resource.getVersions().values();
        assertThat(versions.size()).isEqualTo(1);
        assertThat(versions.iterator().next().getPolicyDefinition()).isNotNull();
        assertThat(versions.iterator().next().isControlPolicy()).isFalse();
        assertThat(versions.iterator().next().isAccessPolicy()).isFalse();
    }

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        typeFactory = new TypeFactoryImpl();
    }

}