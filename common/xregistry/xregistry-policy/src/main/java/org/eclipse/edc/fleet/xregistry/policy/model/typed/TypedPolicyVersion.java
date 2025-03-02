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

import org.eclipse.edc.fleet.xregistry.model.definition.VersionDefinition;
import org.eclipse.edc.fleet.xregistry.model.typed.TypeFactory;
import org.eclipse.edc.fleet.xregistry.model.typed.TypedVersion;

import java.util.Map;

/**
 * A typed view of a policy resource version.
 */
public class TypedPolicyVersion extends TypedVersion {

    protected TypedPolicyVersion(Map<String, Object> untyped, VersionDefinition definition, TypeFactory typeFactory) {
        super(untyped, definition, typeFactory);
    }

    public static class Builder extends TypedVersion.Builder {

        public static Builder newInstance() {
            return new Builder();
        }

        public TypedPolicyVersion build() {
            validate();
            return new TypedPolicyVersion(untyped, definition, typeFactory);
        }

        private Builder() {
            super();
        }
    }
}
