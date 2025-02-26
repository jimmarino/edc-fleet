/*
 *  Copyright (c) 2025 Cofinity-X
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Cofinity-X - initial API and implementation
 *
 */

package org.eclipse.edc.fleet.xregistry.policy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.edc.fleet.xregistry.library.validation.AttributeValidator;
import org.eclipse.edc.fleet.xregistry.library.validation.GroupValidator;
import org.eclipse.edc.fleet.xregistry.library.validation.RegistryValidator;
import org.eclipse.edc.fleet.xregistry.model.RegistryDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.eclipse.edc.fleet.xregistry.policy.PolicyDefinitions.SIMPLE_POLICY_RESOURCE;
import static org.eclipse.edc.fleet.xregistry.policy.RegistryPolicyDefinitions.createPolicyGroupDefinition;

class RegistryPolicyDefinitionsTest {
    private ObjectMapper mapper;
    private RegistryDefinition registry;
    private RegistryValidator validator;

    @Test
    void verify_createAndValidate() throws JsonProcessingException {
        var policy = mapper.readValue(SIMPLE_POLICY_RESOURCE, Map.class);

        @SuppressWarnings("unchecked")
        var result = validator.validate(policy, registry);

        assertThat(result.valid()).isTrue();
    }

    @Test
    void verify_validateMissingDefinitionProperty_fails() throws JsonProcessingException {
        var policy = mapper.readValue(POLICY_RESOURCE_MISSING_PROPERTY, Map.class);

        @SuppressWarnings("unchecked")
        var result = validator.validate(policy, registry);

        assertThat(result.valid()).isFalse();
        assertThat(result.violations()).allMatch(v -> v.contains("policydefinition"));
    }

    @Test
    void verify_validateInvalidResourceType_fails() throws JsonProcessingException {
        var policy = mapper.readValue(INVALID_POLICY_RESOURCE_TYPE, Map.class);

        @SuppressWarnings("unchecked")
        var result = validator.validate(policy, registry);

        assertThat(result.valid()).isFalse();
        assertThat(result.violations()).allMatch(v -> v.toLowerCase().contains("invalid type"));
    }

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        registry = RegistryDefinition.Builder.newInstance().group(createPolicyGroupDefinition()).build();
        var attributeValidator = new AttributeValidator();
        var groupValidator = new GroupValidator(attributeValidator);
        validator = new RegistryValidator(groupValidator, attributeValidator);
    }


    private static String POLICY_RESOURCE_MISSING_PROPERTY = """
            {
              "specversion": "0.5",
              "registryid": "sample",
              "self": "https://localhost:8080/xregistry",
              "xid": "/xregistry",
              "url": "https://localhost:8080/xregistry",
              "epoch": 1,
              "createdat": "2024-12-19T06:00:00Z",
              "modifiedat": "2024-12-19T06:00:00Z",
              "policygroups": {
                "Corporate.Policies": {
                  "description": "Corporate policies",
                  "policies": {
                    "Corporate.Headquarters.EU": {
                        "policyid":"Corporate.Headquarters.EU"
                    }
                  }
                }
              }
            }""";

    private static String INVALID_POLICY_RESOURCE_TYPE = """
            {
              "specversion": "0.5",
              "registryid": "sample",
              "self": "https://localhost:8080/xregistry",
              "xid": "/xregistry",
              "url": "https://localhost:8080/xregistry",
              "epoch": 1,
              "createdat": "2024-12-19T06:00:00Z",
              "modifiedat": "2024-12-19T06:00:00Z",
              "policygroups": {
                "Corporate.Policies": "string"
              }
            }""";


}