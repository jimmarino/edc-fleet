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
import org.eclipse.edc.fleet.xregistry.model.definition.RegistryDefinition;
import org.eclipse.edc.fleet.xregistry.model.definition.ResourceDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.eclipse.edc.fleet.xregistry.model.typed.TestSerializations.TYPED_REGISTRY;

class TypeManipulationTest {
    private static final String TESTGROUP = "testgroup";
    private static final String TESTGROUPS = "testgroups";
    private static final String ENTRY = "entry";
    private static final String ENTRIES = "entries";

    private TypeFactoryImpl typeFactory;
    private ObjectMapper mapper;

    private RegistryDefinition registryDefinition;

    @Test
    @SuppressWarnings("unchecked")
    void verify_readRegistry() throws JsonProcessingException {
        var entry = mapper.readValue(TYPED_REGISTRY, Map.class);

        var registry = TypedRegistry.Builder.newInstance()
                .untyped(entry)
                .definition(registryDefinition)
                .typeFactory(typeFactory)
                .build();

        Map<String, TypedGroup> testGroups = registry.getGroups(TESTGROUPS);
        assertThat(testGroups.size()).isEqualTo(1);

        TypedGroup typedGroup = testGroups.get("test.group1");
        assertThat(typedGroup.getResources().size()).isEqualTo(1);

        TypedMockResource resource = typedGroup.getResource("entry1");
        assertThat(resource.getId()).isEqualTo("entry1");
    }

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        typeFactory = new TypeFactoryImpl();
        typeFactory.registerResource(ENTRY, TypedMockResource::new);

        var groupDefinition = GroupDefinition.Builder.newInstance()
                .singular(TESTGROUP)
                .plural(TESTGROUPS)
                .resource(ResourceDefinition.Builder.newInstance().singular(ENTRY).plural(ENTRIES).build())
                .build();
        registryDefinition = RegistryDefinition.Builder.newInstance()
                .group(groupDefinition)
                .build();

    }


}

