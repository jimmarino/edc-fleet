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

import org.eclipse.edc.fleet.xregistry.model.definition.ResourceDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * A typed view of an XRegistry resource.
 */
public abstract class TypedResource<V extends TypedVersion> extends AbstractType<ResourceDefinition> {
    protected ResourceDefinition definition;
    protected Map<String, V> versions = new HashMap<>();

    protected TypedResource(Map<String, Object> untyped, ResourceDefinition definition, TypeFactory typeFactory) {
        super(untyped, definition, typeFactory);
        this.definition = definition;
    }

    public static class Builder<V extends TypedVersion> extends AbstractType.Builder<ResourceDefinition, Builder<V>> {

        public Builder<V> version(String name, V typedResource) {
            checkModifiableState();
            throw new UnsupportedOperationException();
        }

        public Builder<V> deleteVersion(String name) {
            checkModifiableState();
            throw new UnsupportedOperationException();
        }

        private Builder() {
        }
    }
}
