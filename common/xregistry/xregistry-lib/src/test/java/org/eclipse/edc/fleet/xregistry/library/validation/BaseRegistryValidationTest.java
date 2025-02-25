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

package org.eclipse.edc.fleet.xregistry.library.validation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.edc.fleet.xregistry.model.GroupDefinition;
import org.eclipse.edc.fleet.xregistry.model.RegistryDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class BaseRegistryValidationTest {
    private static final Set<String> MANDATORY = Set.of("registryid", "specversion", "self", "xid", "url", "epoch", "createdat", "modifiedat");
    private ObjectMapper mapper;
    private RegistryValidator registryValidator;

    @Test
    @SuppressWarnings("unchecked")
    void verify_validateUnknownGroupIgnored() throws JsonProcessingException {
        var data = mapper.readValue(VALID_REGISTRY, Map.class);
        var groupDefinition = GroupDefinition.Builder.newInstance()
                .singular("foo")
                .plural("foo")
                .build();
        var registryDefinition = RegistryDefinition.Builder.newInstance()
                .group(groupDefinition)
                .build();
        var result = registryValidator.validate(data, registryDefinition);

        assertThat(result.valid()).isTrue();
    }

    @Test
    @SuppressWarnings("unchecked")
    void verify_missingMandatory_fails() throws JsonProcessingException {
        var data = mapper.readValue(MISSING_MANDATORY_REGISTRY, Map.class);
        var groupDefinition = GroupDefinition.Builder.newInstance()
                .singular("foo")
                .plural("foo")
                .build();
        var registryDefinition = RegistryDefinition.Builder.newInstance()
                .group(groupDefinition)
                .build();
        var result = registryValidator.validate(data, registryDefinition);

        assertThat(result.valid()).isFalse();
        assertThat(result.violations().size()).isEqualTo(MANDATORY.size());
        assertThat(result.violations()).allMatch(v -> {
            boolean found = false;
            for (var key : MANDATORY) {
                if (v.contains(key)) {
                    found = true;
                    break;
                }
            }
            return found;
        });
    }

    @Test
    @SuppressWarnings("unchecked")
    void verify_invalidTypes_fails() throws JsonProcessingException {
        var data = mapper.readValue(INVALID_TYPES_REGISTRY, Map.class);
        var groupDefinition = GroupDefinition.Builder.newInstance()
                .singular("foo")
                .plural("foo")
                .build();
        var registryDefinition = RegistryDefinition.Builder.newInstance()
                .group(groupDefinition)
                .build();
        var result = registryValidator.validate(data, registryDefinition);

        assertThat(result.valid()).isFalse();
        assertThat(result.violations().size()).isEqualTo(MANDATORY.size());
        assertThat(result.violations()).allMatch(v -> {
            boolean found = false;
            for (var key : MANDATORY) {
                if (v.contains(key)) {
                    found = true;
                    break;
                }
            }
            return found;
        });
    }

    @BeforeEach
    void setUp() {
        var attributeValidator = new AttributeValidator();
        var groupValidator = new GroupValidator(attributeValidator);
        registryValidator = new RegistryValidator(groupValidator, attributeValidator);

        mapper = new ObjectMapper();
    }

    private static final String VALID_REGISTRY = """
            {
              "specversion": "0.5",
              "registryid": "sample",
              "self": "https://localhost:8080/xregistry",
              "xid": "/xregistry",
              "url": "https://localhost:8080/xregistry",
              "epoch": 1,
              "createdat": "2024-12-19T06:00:00Z",
              "modifiedat": "2024-12-19T06:00:00Z",
              "foogroups": {
                "Fabrikam.Type1": {
                  "entries": {
                  }
                }
              }
            }""";

    private static final String INVALID_TYPES_REGISTRY = """
            {
              "specversion": 5,
              "registryid": 1,
              "self": 1,
              "xid": 1,
              "url": 1,
              "epoch": "epoch",
              "createdat": 1,
              "modifiedat":1,
              "foogroups": {
                "Fabrikam.Type1": {
                  "entries": {
                  }
                }
              }
            }""";

    private static final String MISSING_MANDATORY_REGISTRY = """
            {
              "foogroups": {
                "Fabrikam.Type1": {
                  "entries": {
                  }
                }
              }
            }""";
}
