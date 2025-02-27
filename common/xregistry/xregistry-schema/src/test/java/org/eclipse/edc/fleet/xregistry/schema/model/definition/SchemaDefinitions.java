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

public interface SchemaDefinitions {

    String SIMPLE_SCHEMA_RESOURCE = """
            {
              "specversion": "0.5",
              "registryid": "sample",
              "self": "https://localhost:8080/xregistry",
              "xid": "/xregistry",
              "url": "https://localhost:8080/xregistry",
              "epoch": 1,
              "createdat": "2024-12-19T06:00:00Z",
              "modifiedat": "2024-12-19T06:00:00Z",
              "schemagroupsurl": "http://example.com/schemagroups",
              "schemagroupscount": 1,
               "schemagroups": {
                 "com.example.schemas": {
                   "schemagroupid": "com.example.schemas",
                   "schemasurl": "https://example.com/schemagroups/com.example.schemas/schemas",
                   "schemascount": 5
                 }
               }
            
            }""";

}
