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

package org.eclipse.edc.fleet.xregistry.policy.model.fixture;

public interface TestSerializations {
    String POLICY_REGISTRY = """
            {
              "specversion": "0.5",
              "registryid": "sample",
              "self": "https://localhost:8080/xregistry",
              "xid": "/xregistry",
              "epoch": 1,
              "createdat": "2024-12-19T06:00:00Z",
              "modifiedat": "2024-12-19T06:00:00Z",
              "policygroupsurl": "#/policygroups",
              "policygroupscount": 1,
              "policygroups": {
                "Corporate.Policies": {
                  "policygroupid": "Corporate.Policies",
                  "description": "Corporate policies",
                  "self": "#/policygroups/corporate.policies",
                  "xid": "/policygroups/corporate.policies",
                  "epoch": 1,
                  "createdat": "2024-12-19T06:00:00Z",
                  "modifiedat": "2024-12-19T06:00:00Z",
                  "policiesurl": "#/policygroups/corporate.policies/policies",
                  "policiescount": 0,
                  "policies": {
                    "Corporate.Headquarters.EU": {
                      "policyid": "Corporate.Headquarters.EU",
                      "self": "#/policygroups/corporate.policies/policies/corporate.headquarters.eu",
                      "xid": "/policygroups/corporate.policies/policies/corporate.headquarters.eu",
                      "metaurl": "#/policygroups/corporate.policies/policies/corporate.headquarters.eu/meta",
                      "versionsurl": "#/policygroups/corporate.policies/policies/corporate.headquarters.eu/versions",
                      "versionscount": 0,
                      "description:": "AssertscorporateheadquarterlocationisintheEU",
                      "versions": {
                        "1.0": {
                          "policyid": "Corporate.Headquarters.EU",
                          "versionid": "1.0",
                          "self": "#/policygroups/corporate.policies/policies/corporate.headquarters.eu/versions/1.0",
                          "xid": "/policygroups/corporate.policies/policies/corporate.headquarters.eu/versions/1.0",
                          "epoch": 1,
                          "isdefault": true,
                          "createdat": "2024-12-19T06:00:00Z",
                          "modifiedat": "2024-12-19T06:00:00Z",
                          "policydefinition": "{\\"@context\\":{\\"edc\\":\\"https://w3id.org/edc/v0.0.1/ns/\\"},\\"@type\\":\\"PolicyDefinition\\",\\"policy\\":{\\"@context\\":\\"http://www.w3.org/ns/odrl.jsonld\\",\\"@id\\":\\"8c2ff88a-74bf-41dd-9b35-9587a3b95adf\\",\\"duty\\":[{\\"target\\":\\"http://example.com/asset:12345\\",\\"action\\":\\"use\\",\\"constraint\\":{\\"leftOperand\\":\\"headquarter_location\\",\\"operator\\":\\"eq\\",\\"rightOperand\\":\\"EU\\"}}]}\\n}"
                        }
                      }
                    }
                  }
                }
              }
            }""";

    String POLICY_GROUP = """
            {
              "policygroupid": "Corporate.Policies",
              "description": "Corporate policies",
              "self": "#/policygroups/corporate.policies",
              "xid": "/policygroups/corporate.policies",
              "epoch": 1,
              "createdat": "2024-12-19T06:00:00Z",
              "modifiedat": "2024-12-19T06:00:00Z",
              "policiesurl": "#/policygroups/corporate.policies/policies",
              "policiescount": 0,
              "policies": {
                "Corporate.Headquarters.EU": {
                  "policyid": "Corporate.Headquarters.EU",
                  "self": "#/policygroups/corporate.policies/policies/corporate.headquarters.eu",
                  "xid": "/policygroups/corporate.policies/policies/corporate.headquarters.eu",
                  "metaurl": "#/policygroups/corporate.policies/policies/corporate.headquarters.eu/meta",
                  "versionsurl": "#/policygroups/corporate.policies/policies/corporate.headquarters.eu/versions",
                  "versionscount": 0,
                  "description:": "AssertscorporateheadquarterlocationisintheEU",
                  "versions": {
                    "1.0": {
                      "policyid": "Corporate.Headquarters.EU",
                      "versionid": "1.0",
                      "self": "#/policygroups/corporate.policies/policies/corporate.headquarters.eu/versions/1.0",
                      "xid": "/policygroups/corporate.policies/policies/corporate.headquarters.eu/versions/1.0",
                      "epoch": 1,
                      "isdefault": true,
                      "createdat": "2024-12-19T06:00:00Z",
                      "modifiedat": "2024-12-19T06:00:00Z",
                      "policydefinition": "{\\"@context\\":{\\"edc\\":\\"https://w3id.org/edc/v0.0.1/ns/\\"},\\"@type\\":\\"PolicyDefinition\\",\\"policy\\":{\\"@context\\":\\"http://www.w3.org/ns/odrl.jsonld\\",\\"@id\\":\\"8c2ff88a-74bf-41dd-9b35-9587a3b95adf\\",\\"duty\\":[{\\"target\\":\\"http://example.com/asset:12345\\",\\"action\\":\\"use\\",\\"constraint\\":{\\"leftOperand\\":\\"headquarter_location\\",\\"operator\\":\\"eq\\",\\"rightOperand\\":\\"EU\\"}}]}\\n}"
                    }
                  }
                }
              }
             }""";

    String POLICY_RESOURCE = """
            {
              "policyid": "Corporate.Headquarters.EU",
              "self": "#/policygroups/corporate.policies/policies/corporate.headquarters.eu",
              "xid": "/policygroups/corporate.policies/policies/corporate.headquarters.eu",
              "metaurl": "#/policygroups/corporate.policies/policies/corporate.headquarters.eu/meta",
              "versionsurl": "#/policygroups/corporate.policies/policies/corporate.headquarters.eu/versions",
              "versionscount": 0,
              "description:": "AssertscorporateheadquarterlocationisintheEU",
              "versions": {
                "1.0": {
                  "policyid": "Corporate.Headquarters.EU",
                  "versionid": "1.0",
                  "self": "#/policygroups/corporate.policies/policies/corporate.headquarters.eu/versions/1.0",
                  "xid": "/policygroups/corporate.policies/policies/corporate.headquarters.eu/versions/1.0",
                  "epoch": 1,
                  "isdefault": true,
                  "createdat": "2024-12-19T06:00:00Z",
                  "modifiedat": "2024-12-19T06:00:00Z",
                  "policydefinition": "{\\"@context\\":{\\"edc\\":\\"https://w3id.org/edc/v0.0.1/ns/\\"},\\"@type\\":\\"PolicyDefinition\\",\\"policy\\":{\\"@context\\":\\"http://www.w3.org/ns/odrl.jsonld\\",\\"@id\\":\\"8c2ff88a-74bf-41dd-9b35-9587a3b95adf\\",\\"duty\\":[{\\"target\\":\\"http://example.com/asset:12345\\",\\"action\\":\\"use\\",\\"constraint\\":{\\"leftOperand\\":\\"headquarter_location\\",\\"operator\\":\\"eq\\",\\"rightOperand\\":\\"EU\\"}}]}\\n}"
                }
              }
            }
            """;

}
