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

import java.util.Map;

/**
 * A typed view of an XRegistry resource.
 */
public abstract class TypedResource extends AbstractType<ResourceDefinition> {
    protected ResourceDefinition definition;

    public TypedResource(Map<String, Object> untyped, ResourceDefinition definition, TypeFactory typeFactory) {
        super(untyped, definition, typeFactory);
        this.definition = definition;
    }

    public static class Builder extends AbstractType.Builder<ResourceDefinition, Builder> {

        public static Builder newInstance() {
            return new Builder();
        }

        public Builder resource(String name, TypedResource typedResource) {
            checkModifiableState();
            throw new UnsupportedOperationException();
        }

        public Builder deleteResource(String name) {
            checkModifiableState();
            throw new UnsupportedOperationException();
        }

        private Builder() {
        }
    }


}
