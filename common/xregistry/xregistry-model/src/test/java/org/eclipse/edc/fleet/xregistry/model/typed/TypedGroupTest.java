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

package org.eclipse.edc.fleet.xregistry.model.typed;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.edc.fleet.xregistry.model.definition.GroupDefinition;
import org.eclipse.edc.fleet.xregistry.model.definition.ResourceDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.eclipse.edc.fleet.xregistry.model.typed.TestSerializations.TYPED_GROUP;

class TypedGroupTest {
    private ObjectMapper mapper;
    private TypeFactoryImpl typeFactory;

    @Test
    @SuppressWarnings("unchecked")
    void verify_typedGroup() throws JsonProcessingException {
        var untyped = mapper.readValue(TYPED_GROUP, Map.class);

        var group = TypedGroup.Builder.newInstance()
                .untyped(untyped)
                .definition(GroupDefinition.Builder.newInstance()
                        .singular("testgroup")
                        .plural("testgroups")
                        .resource(ResourceDefinition.Builder.newInstance()
                                .singular("entry")
                                .plural("entries")
                                .build())
                        .build())
                .typeFactory(typeFactory)
                .build();

        TypedMockResource resource = group.getResource("entry1");
        assertThat(resource).isNotNull();

        Collection<TypedMockResource> resources = group.getResourcesOfType(TypedMockResource.class);
        assertThat(resources.size()).isEqualTo(1);
        assertThat(resources.iterator().next().getId()).isNotNull();

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
        typeFactory.registerResource("entry", TypedMockResource::new);

    }

}