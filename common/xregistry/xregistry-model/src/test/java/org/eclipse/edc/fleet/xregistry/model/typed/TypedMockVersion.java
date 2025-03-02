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

import org.eclipse.edc.fleet.xregistry.model.definition.VersionDefinition;

import java.util.Map;

public class TypedMockVersion extends TypedVersion {

    protected TypedMockVersion(Map<String, Object> untyped, VersionDefinition definition, TypeFactory typeFactory) {
        super(untyped, definition, typeFactory);
    }
}
