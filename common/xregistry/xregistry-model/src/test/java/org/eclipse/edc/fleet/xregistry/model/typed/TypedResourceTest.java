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
import org.eclipse.edc.fleet.xregistry.model.definition.ResourceDefinition;
import org.eclipse.edc.fleet.xregistry.model.definition.VersionDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.eclipse.edc.fleet.xregistry.model.typed.TestSerializations.TYPED_RESOURCE;
import static org.mockito.Mockito.mock;

class TypedResourceTest {
    private TypedMockResource resource;
    private TypeFactory typeFactory;

    @Test
    void verify_typedRegistry() throws JsonProcessingException {
        assertThat(resource.getId()).isNotNull();
        assertThat(resource.getSelf()).isNotNull();
        assertThat(resource.getXid()).isNotNull();

        Collection<TypedMockVersion> versions = resource.getVersions().values();
        assertThat(versions.size()).isEqualTo(1);
        assertThat(versions.iterator().next().getEntryDefinition()).isNotNull();
    }

    @Test
    void verify_modify() {
        resource.toBuilder().removeVersion("1.0").build();

        assertThat(resource.getVersions()).isEmpty();

        var newVersion = TypedMockVersion.Builder.newInstance()
                .typeFactory(typeFactory)
                .untyped(Map.of())
                .definition(VersionDefinition.Builder.newInstance()
                        .resourceName("entry")
                        .build())
                .build();

        resource.toBuilder().version("2.o", newVersion).build();
        assertThat(resource.getVersions().size()).isEqualTo(1);
    }

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setUp() throws JsonProcessingException {
        var mapper = new ObjectMapper();
        typeFactory = mock(TypeFactory.class);
        var untyped = mapper.readValue(TYPED_RESOURCE, Map.class);

        resource = TypedMockResource.Builder.newInstance()
                .untyped(untyped)
                .definition(ResourceDefinition.Builder.newInstance()
                        .singular("entry")
                        .plural("entries")
                        .build())
                .typeFactory(typeFactory)
                .build();

    }

}